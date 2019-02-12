package me.alexnitu.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import me.alexnitu.network.most_viewed.MostViewedResponse;
import me.alexnitu.network.most_viewed.MostViewedService;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class MostViewedRepositoryTest {

    private static final List<Article> ARTICLE_LIST;
    private static final String ARTICLE_ID_1 = "1";
    private static final Article ARTICLE_1 = new Article(ARTICLE_ID_1);

    static {
        ARTICLE_LIST = Arrays.asList(ARTICLE_1, new Article("2"), new Article("3"), new Article("4"));
    }

    private MostViewedRepository repository;

    @Mock
    private MostViewedService service;

    private TestObserver<List<Article>> articleListTestObserver;
    private TestObserver<Article> singleArticleTestObserver;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        articleListTestObserver = new TestObserver<>();
        singleArticleTestObserver = new TestObserver<>();

        repository = new MostViewedRepository(service);
    }

    @Test
    public void loadSuccessful_cachesArticleList() {
        when(service.getMostViewed()).thenReturn(Single.just(new MostViewedResponse(ARTICLE_LIST)));

        repository.invalidateCache();
        repository.getMostViewed()
                .subscribe(articleListTestObserver);

        articleListTestObserver.assertNoErrors();
        verify(service).getMostViewed();
        assertTrue(repository.hasCachedData());
    }

    @Test
    public void loadArticlesError_returnsError() {
        Throwable error = new Throwable();
        when(service.getMostViewed()).thenReturn(Single.error(error));

        repository.getMostViewed()
                .subscribe(articleListTestObserver);

        articleListTestObserver.assertError(error);
    }

    @Test
    public void loadArticleById_found_returnsArticle() {
        when(service.getMostViewed()).thenReturn(Single.just(new MostViewedResponse(ARTICLE_LIST)));

        repository.getById(ARTICLE_ID_1)
                .subscribe(singleArticleTestObserver);

        singleArticleTestObserver.assertNoErrors();
        singleArticleTestObserver.assertValue(ARTICLE_1);
    }

    @Test
    public void loadArticleById_notFound_returnsError() {
        when(service.getMostViewed()).thenReturn(Single.just(new MostViewedResponse(ARTICLE_LIST)));

        repository.invalidateCache();
        repository.getById("randomId")
                .subscribe(singleArticleTestObserver);

        singleArticleTestObserver.assertError(Throwable.class);
    }

    @Test
    public void loadArticleByIdError_returnsError() {
        Throwable error = new Throwable();
        when(service.getMostViewed()).thenReturn(Single.error(error));

        repository.getById("")
                .subscribe(singleArticleTestObserver);

        singleArticleTestObserver.assertError(error);
    }

    @Test
    public void getArticles_whenCached_DoesNotCallService() {
        when(service.getMostViewed()).thenReturn(Single.just(new MostViewedResponse(ARTICLE_LIST)));

        repository.invalidateCache();
        repository.getMostViewed()
                .subscribe(articleListTestObserver);

        verify(service).getMostViewed();

        repository.getMostViewed()
                .subscribe(articleListTestObserver);

        verifyNoMoreInteractions(service);
    }

}
