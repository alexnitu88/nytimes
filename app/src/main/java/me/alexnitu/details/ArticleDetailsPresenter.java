package me.alexnitu.details;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import me.alexnitu.common.schedulers.Schedulers;
import me.alexnitu.model.MostViewedRepository;

public class ArticleDetailsPresenter implements ArticleDetailsContract.Presenter {

    private ArticleDetailsContract.View view;

    private String articleId;

    private MostViewedRepository repository;
    private Schedulers schedulers;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public ArticleDetailsPresenter(MostViewedRepository repository, Schedulers schedulers, String articleId) {
        this.repository = repository;
        this.schedulers = schedulers;
        this.articleId = articleId;
    }

    @Override
    public void attach(ArticleDetailsContract.View view) {
        this.view = view;
        loadArticleDetails();
    }

    @Override
    public void detach() {
        disposable.clear();
        this.view = null;
    }

    private void loadArticleDetails() {
        view.showLoading(true);

        disposable.add(
                repository.getById(articleId)
                        .subscribeOn(schedulers.io())
                        .observeOn(schedulers.mainThread())
                        .subscribe(article -> {
                            view.showLoading(false);
                            view.showArticle(article);
                        }, throwable -> {
                            view.showLoading(false);
                            view.showLoadError();
                        }));
    }
}
