package com.example.wanandroid.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.wanandroid.R;
import com.example.wanandroid.activity.WebViewActivity;
import com.example.wanandroid.bean.WXArticleBean.DataBean.DatasBean;

import java.util.List;

public class WXArticleAdapter extends RecyclerView.Adapter<WXArticleAdapter.ViewHolder> {
    private Context mContext;
    private List<DatasBean> articleList;


    public WXArticleAdapter(RecyclerView recyclerView, List<DatasBean> articles) {
        mContext = recyclerView.getContext();
        articleList = articles;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.article_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(articleList != null) {
            DatasBean article = articleList.get(position);
            holder.mArticleTitle.setText(Html.fromHtml(article.getTitle(),Html.FROM_HTML_MODE_COMPACT));
            holder.mArticleTime.setText(article.getNiceDate());
            holder.mArticleAuthor.setText(String.format(mContext.getResources().getString(R.string.article_author),article.getAuthor()));

            String category = String.format(mContext.getResources().getString(R.string.article_category),article.getSuperChapterName(),article.getChapterName());
            holder.mArticleClassify.setText(Html.fromHtml(category, Html.FROM_HTML_MODE_COMPACT));
            holder.articleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatasBean article = articleList.get(position);
                    WebViewActivity.actionStart(mContext, article.getTitle(), article.getLink());

                }
            });
            holder.mArticleCollection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"you clicked collection", Toast.LENGTH_SHORT).show();
                    Log.d("MainArticleAdapter", "clicked collection successed");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return articleList != null ? articleList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View articleView;
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
            articleView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
