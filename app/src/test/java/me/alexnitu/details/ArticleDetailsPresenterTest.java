package me.alexnitu.details;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;
import me.alexnitu.common.schedulers.TestSchedulers;
import me.alexnitu.model.Article;
import me.alexnitu.model.MostViewedRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArticleDetailsPresenterTest {

    private static final String ARTICLE_ID = "991";
    private static final Article ARTICLE = new Article(ARTICLE_ID);

    @Mock
    private MostViewedRepository repository;

    @Mock
    private ArticleDetailsContract.View view;

    private ArticleDetailsPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        presenter = new ArticleDetailsPresenter(repository, new TestSchedulers(), ARTICLE_ID);
    }

    @Test
    public void loadDetailsSuccessful_showsDetails() {
        when(repository.getById(ARTICLE_ID)).thenReturn(Single.just(ARTICLE));

        presenter.attach(view);

        verify(view).showLoading(true);
        verify(view).showLoading(false);

        ArgumentCaptor<Article> articleCaptor = ArgumentCaptor.forClass(Article.class);
        verify(view).showArticle(articleCaptor.capture());
        assertEquals(articleCaptor.getValue(), ARTICLE);
    }

    @Test
    public void loadDetailsError_showsError() {
        when(repository.getById(ARTICLE_ID)).thenReturn(Single.error(new Throwable()));

        presenter.attach(view);

        verify(view).showLoading(true);
        verify(view).showLoading(false);
        verify(view).showLoadError();
    }
}
