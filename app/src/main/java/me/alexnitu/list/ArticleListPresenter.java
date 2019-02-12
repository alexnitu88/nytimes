package me.alexnitu.list;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import me.alexnitu.common.schedulers.Schedulers;
import me.alexnitu.helper.EspressoIdlingResource;
import me.alexnitu.model.Article;
import me.alexnitu.model.MostViewedRepository;

public class ArticleListPresenter implements ArticleListContract.Presenter {

    private ArticleListContract.View view;

    private MostViewedRepository repository;
    private Schedulers schedulers;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    ArticleListPresenter(MostViewedRepository repository, Schedulers schedulers) {
        this.repository = repository;
        this.schedulers = schedulers;
    }

    @Override
    public void attach(ArticleListContract.View view) {
        this.view = view;
        getArticles();
    }

    @Override
    public void detach() {
        disposable.clear();
        this.view = null;
    }

    @Override
    public void getArticles() {
        EspressoIdlingResource.INSTANCE.increment();

        view.showLoading(true);
        disposable.add(
                repository.getMostViewed()
                        .subscribeOn(schedulers.io())
                        .observeOn(schedulers.mainThread())
                        .doFinally(() -> {
                            if (!EspressoIdlingResource.INSTANCE.isIdleNow()) {
                                EspressoIdlingResource.INSTANCE.decrement();
                            }
                        })
                        .subscribe(articles -> {
                            view.showLoading(false);

                            if (articles.isEmpty()) {
                                view.showEmptyList();
                            } else {
                                view.showArticleList(articles);
                            }
                        }, throwable -> {
                            view.showLoading(false);
                            view.showLoadError();
                        }));
    }

    @Override
    public void openArticleDetails(Article article) {
        view.showArticleDetails(article.getId());
    }

}
