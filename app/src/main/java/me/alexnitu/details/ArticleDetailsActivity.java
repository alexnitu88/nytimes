package me.alexnitu.details;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import me.alexnitu.R;

public class ArticleDetailsActivity extends DaggerAppCompatActivity {

    public static final String EXTRA_ARTICLE_ID = "article_id";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addDetailsFragment();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void addDetailsFragment() {
        if (getSupportFragmentManager().findFragmentById(R.id.details_fragment_container) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.details_fragment_container, ArticleDetailsFragment.newInstance())
                    .commit();
        }
    }
}
