package me.alexnitu.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.alexnitu.details.ArticleDetailsActivity;
import me.alexnitu.details.ArticleDetailsModule;
import me.alexnitu.list.ArticleListActivity;
import me.alexnitu.list.ArticleListModule;

@Module
public abstract class ActivityInjectModule {

    @ContributesAndroidInjector(modules = {ArticleListModule.class})
    abstract ArticleListActivity bindArticleListActivity();

    @ContributesAndroidInjector(modules = {ArticleDetailsModule.class})
    abstract ArticleDetailsActivity bindArticleDetailsActivity();
}
