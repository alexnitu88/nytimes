package me.alexnitu.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;
import me.alexnitu.R;
import me.alexnitu.details.ArticleDetailsActivity;
import me.alexnitu.model.Article;

public class ArticleListFragment extends DaggerFragment implements ArticleListContract.View {

    @Inject
    ArticleListContract.Presenter presenter;

    @BindView(R.id.article_rv)
    RecyclerView articleRv;
    @BindView(R.id.progress_bar)
    View progressBar;
    @BindView(R.id.empty_list_tv)
    View emptyListTv;

    private Unbinder unbinder;

    private ListAdapter rvAdapter = new ListAdapter(
            article -> presenter.openArticleDetails(article));

    static ArticleListFragment newInstance() {
        return new ArticleListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.VERTICAL);

        articleRv.setLayoutManager(llm);
        articleRv.setAdapter(rvAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attach(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showLoading(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showArticleList(List<Article> articles) {
        emptyListTv.setVisibility(View.GONE);
        articleRv.setVisibility(View.VISIBLE);

        rvAdapter.setArticleList(articles);
    }

    @Override
    public void showEmptyList() {
        emptyListTv.setVisibility(View.VISIBLE);
        articleRv.setVisibility(View.GONE);
    }

    @Override
    public void showLoadError() {
    }

    @Override
    public void showArticleDetails(String articleId) {
        Intent intent = new Intent(getActivity(), ArticleDetailsActivity.class);
        intent.putExtra(ArticleDetailsActivity.EXTRA_ARTICLE_ID, articleId);
        startActivity(intent);
    }
}
