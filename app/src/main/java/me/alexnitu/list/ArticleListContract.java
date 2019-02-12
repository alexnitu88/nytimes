package me.alexnitu.list;

import java.util.List;

import me.alexnitu.common.BasePresenter;
import me.alexnitu.common.BaseView;
import me.alexnitu.model.Article;

public interface ArticleListContract {

    interface View extends BaseView {
        void showLoading(boolean show);

        void showArticleList(List<Article> articles);

        void showEmptyList();

        void showLoadError();

        void showArticleDetails(String articleId);
    }

    interface Presenter extends BasePresenter<View> {
        void getArticles();

        void openArticleDetails(Article article);
    }
}
