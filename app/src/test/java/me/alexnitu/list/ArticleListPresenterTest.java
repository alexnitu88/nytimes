package me.alexnitu.list;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;
import me.alexnitu.common.schedulers.TestSchedulers;
import me.alexnitu.model.Article;
import me.alexnitu.model.MostViewedRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArticleListPresenterTest {

    private static final List<Article> ARTICLE_LIST;

    static {
        ARTICLE_LIST = Arrays.asList(new Article("1"), new Article("2"), new Article("3"), new Article("4"));
    }

    @Mock
    private MostViewedRepository repository;

    @Mock
    private ArticleListContract.View view;

    private ArticleListPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        presenter = new ArticleListPresenter(repository, new TestSchedulers());
    }

    @Test
    public void loadArticlesSuccessful_showsArticles() {
        when(repository.getMostViewed()).thenReturn(Single.just(ARTICLE_LIST));

        presenter.attach(view);

        verify(view).showLoading(true);
        verify(view).showLoading(false);

        ArgumentCaptor<List> articleListCaptor = ArgumentCaptor.forClass(List.class);
        verify(view).showArticleList(articleListCaptor.capture());
        assertEquals(articleListCaptor.getValue().size(), ARTICLE_LIST.size());
    }

    @Test
    public void loadArticlesError_showsError() {
        when(repository.getMostViewed()).thenReturn(Single.error(new Throwable()));

        presenter.attach(view);

        verify(view).showLoading(true);
        verify(view).showLoading(false);
        verify(view).showLoadError();
    }

    @Test
    public void loadEmptyArticleList_showsEmptyList() {
        when(repository.getMostViewed()).thenReturn(Single.just(new ArrayList<>()));

        presenter.attach(view);

        verify(view).showLoading(true);
        verify(view).showLoading(false);
        verify(view).showEmptyList();
    }

    @Test
    public void clickOnArticle_showsArticleDetails() {
        Article article = new Article("1");

        when(repository.getMostViewed()).thenReturn(Single.just(ARTICLE_LIST));
        presenter.attach(view);
        presenter.openArticleDetails(article);

        ArgumentCaptor<String> articleIdCaptor = ArgumentCaptor.forClass(String.class);
        verify(view).showArticleDetails(articleIdCaptor.capture());
        assertEquals(articleIdCaptor.getValue(), article.getId());
    }

}
