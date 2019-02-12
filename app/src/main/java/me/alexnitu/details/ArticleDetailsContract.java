package me.alexnitu.details;

import me.alexnitu.common.BasePresenter;
import me.alexnitu.common.BaseView;
import me.alexnitu.model.Article;

public interface ArticleDetailsContract {

    interface View extends BaseView {
        void showLoading(boolean show);

        void showArticle(Article article);

        void showLoadError();
    }

    interface Presenter extends BasePresenter<View> {
    }
}
