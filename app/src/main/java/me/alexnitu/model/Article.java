package me.alexnitu.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Article {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("byline")
    private String authorName;
    @SerializedName("published_date")
    private String date;

    public Article(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
