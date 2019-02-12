package me.alexnitu;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import me.alexnitu.di.DaggerAppComponent;

public class App extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .create(this);
    }
}
