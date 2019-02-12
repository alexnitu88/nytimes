package me.alexnitu.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;
import me.alexnitu.R;
import me.alexnitu.model.Article;

public class ArticleDetailsFragment extends DaggerFragment implements ArticleDetailsContract.View {

    @Inject
    ArticleDetailsContract.Presenter presenter;

    @BindView(R.id.article_details_container)
    View detailsContainer;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.author_name_tv)
    TextView authorNameTv;
    @BindView(R.id.date_tv)
    TextView dateTv;
    @BindView(R.id.error_tv)
    View errorTv;
    @BindView(R.id.progress_bar)
    View progressBar;

    private Unbinder unbinder;

    static ArticleDetailsFragment newInstance() {
        return new ArticleDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
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
    public void showArticle(Article article) {
        detailsContainer.setVisibility(View.VISIBLE);
        errorTv.setVisibility(View.GONE);

        titleTv.setText(article.getTitle());
        authorNameTv.setText(article.getAuthorName());
        dateTv.setText(article.getDate());
    }

    @Override
    public void showLoadError() {
        errorTv.setVisibility(View.VISIBLE);
        detailsContainer.setVisibility(View.GONE);
    }
}
