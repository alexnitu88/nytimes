package me.alexnitu.network.most_viewed;

import io.reactivex.Single;
import me.alexnitu.network.Api;
import retrofit2.http.GET;

public interface MostViewedService {

    @GET("mostpopular/v2/mostviewed/all-sections/7.json?api-key=" + Api.API_KEY)
    Single<MostViewedResponse> getMostViewed();
}
