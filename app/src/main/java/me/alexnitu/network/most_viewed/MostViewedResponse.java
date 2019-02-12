package me.alexnitu.network.most_viewed;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.annotation.VisibleForTesting;
import me.alexnitu.model.Article;

public class MostViewedResponse {

    @SerializedName("results")
    private List<Article> articles;

    @VisibleForTesting
    public MostViewedResponse(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticleList() {
        return articles;
    }
}
