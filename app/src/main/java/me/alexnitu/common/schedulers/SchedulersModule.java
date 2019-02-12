package me.alexnitu.common.schedulers;

import dagger.Module;
import dagger.Provides;

@Module
public class SchedulersModule {

    @Provides
    Schedulers provideSchedulers() {
        return new AppSchedulers();
    }
}
