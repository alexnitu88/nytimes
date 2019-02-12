package me.alexnitu.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.alexnitu.R;
import me.alexnitu.model.Article;

class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private ItemClickListener itemClickListener;

    private List<Article> articleList;

    ListAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_article, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = articleList.get(position);

        holder.titleTv.setText(article.getTitle());
        holder.authorNameTv.setText(article.getAuthorName());
        holder.dateTv.setText(article.getDate());
    }

    @Override
    public int getItemCount() {
        return articleList != null ? articleList.size() : 0;
    }

    interface ItemClickListener {
        void onItemClick(Article article);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.title_tv)
        TextView titleTv;
        @BindView(R.id.author_name_tv)
        TextView authorNameTv;
        @BindView(R.id.date_tv)
        TextView dateTv;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(articleList.get(getAdapterPosition()));
                }
            });
        }
    }
}
