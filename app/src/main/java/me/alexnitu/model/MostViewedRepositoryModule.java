package me.alexnitu.model;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.alexnitu.network.Api;
import me.alexnitu.network.most_viewed.MostViewedService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MostViewedRepositoryModule {

    @Provides
    @Singleton
    MostViewedRepository provideRepository() {
        MostViewedService service = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Api.BASE_URL)
                .build()
                .create(MostViewedService.class);

        return new MostViewedRepository(service);
    }
}
