package me.alexnitu.model;

import android.text.TextUtils;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.VisibleForTesting;
import io.reactivex.Observable;
import io.reactivex.Single;
import me.alexnitu.network.most_viewed.MostViewedResponse;
import me.alexnitu.network.most_viewed.MostViewedService;

public class MostViewedRepository {

    private MostViewedService service;

    private List<Article> cache;

    @Inject
    MostViewedRepository(MostViewedService service) {
        this.service = service;
    }

    public void invalidateCache() {
        cache = null;
    }

    @VisibleForTesting
    boolean hasCachedData() {
        return cache != null;
    }

    public Single<List<Article>> getMostViewed() {
        if (cache != null) {
            return Single.just(cache);
        }
        return service.getMostViewed()
                .map(MostViewedResponse::getArticleList)
                .doOnSuccess(articles -> cache = articles);
    }

    public Single<Article> getById(String id) {
        return getMostViewed()
                .flatMapObservable(Observable::fromIterable)
                .filter(article -> TextUtils.equals(article.getId(), id))
                .firstOrError();
    }
}
