package me.alexnitu.list;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ArticleListModule {

    @ContributesAndroidInjector
    abstract ArticleListFragment provideArticleListFragment();

    @Binds
    abstract ArticleListContract.Presenter presenter(ArticleListPresenter presenter);

}
