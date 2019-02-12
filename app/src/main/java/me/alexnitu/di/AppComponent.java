package me.alexnitu.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import me.alexnitu.App;
import me.alexnitu.common.schedulers.SchedulersModule;
import me.alexnitu.model.MostViewedRepositoryModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityInjectModule.class,
        MostViewedRepositoryModule.class,
        SchedulersModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
    }
}
