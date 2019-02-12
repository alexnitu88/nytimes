package me.alexnitu.details;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

import static me.alexnitu.details.ArticleDetailsActivity.EXTRA_ARTICLE_ID;

@Module
public abstract class ArticleDetailsModule {

    @ContributesAndroidInjector
    abstract ArticleDetailsFragment provideArticleDetailsFragment();

    @Binds
    abstract ArticleDetailsContract.Presenter presenter(ArticleDetailsPresenter presenter);

    @Provides
    static String provideArticleId(ArticleDetailsActivity activity) {
        return activity.getIntent().getStringExtra(EXTRA_ARTICLE_ID);
    }

}
