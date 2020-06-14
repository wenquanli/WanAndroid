package com.example.wanandroid.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.MainArticleBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainArticleAdapter extends RecyclerView.Adapter<MainArticleAdapter.ViewHolder> {
    private Context mContext;
    private List<MainArticleBean.DataBean.DatasBean> articleList;
    public MainArticleAdapter(RecyclerView recyclerView) {
        mContext = recyclerView.getContext();
    }

    public void setmBean(MainArticleBean bean) {
        this.articleList = bean.getData().getDatas();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.article_item, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(articleList != null) {
            MainArticleBean.DataBean.DatasBean article = articleList.get(position);
            holder.mArticleTitle.setText(Html.fromHtml(article.getTitle(),Html.FROM_HTML_MODE_COMPACT));
            holder.mArticleTime.setText(article.getNiceDate());
            if((article.getAuthor().length() == 0) || (article.getAuthor() == null)) {
                holder.mArticleAuthor.setText(String.format(mContext.getResources().getString(R.string.article_sharer),article.getShareUser()));
            }else {
                holder.mArticleAuthor.setText(String.format(mContext.getResources().getString(R.string.article_author),article.getAuthor()));
            }

            String category = String.format(mContext.getResources().getString(R.string.article_category),article.getSuperChapterName(),article.getChapterName());
            holder.mArticleClassify.setText(Html.fromHtml(category, Html.FROM_HTML_MODE_COMPACT));
        }


    }

    @Override
    public int getItemCount() {
        return articleList != null ? articleList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.article_title)
        TextView mArticleTitle;
        @BindView(R.id.article_author)
        TextView mArticleAuthor;
        @BindView(R.id.article_classify)
        TextView mArticleClassify;
        @BindView(R.id.article_collection)
        ImageView mArticleCollection;
        @BindView(R.id.article_time)
        TextView mArticleTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
