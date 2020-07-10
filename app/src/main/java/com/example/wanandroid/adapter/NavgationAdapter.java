package com.example.wanandroid.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.NavgationBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NavgationAdapter extends RecyclerView.Adapter<NavgationAdapter.ViewHolder>{
    private Context mContext;
    private List<NavgationBean.DataBean> konwledgeHireList;

    public NavgationAdapter(RecyclerView recyclerView, List<NavgationBean.DataBean> articles) {
        mContext = recyclerView.getContext();
        konwledgeHireList = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_knowledge_hier, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (konwledgeHireList != null) {
            NavgationBean.DataBean item = konwledgeHireList.get(position);
            holder.title.setText(item.getName());
            holder.updateTagsFlowLayout(item.getArticles());
        }
    }

    @Override
    public int getItemCount() {
        return konwledgeHireList != null ? konwledgeHireList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final List<NavgationBean.DataBean.ArticlesBean> secondaryArticleDirectoryList;
        private final TextView title;
        private final TagFlowLayout tagFlowLayout;

        public ViewHolder(@NonNull View view) {
            super(view);
            secondaryArticleDirectoryList = new ArrayList();
            title = view.findViewById(R.id.title);
            tagFlowLayout = view.findViewById(R.id.flowLayout_tags);

            tagFlowLayout.setAdapter(new TagAdapter<NavgationBean.DataBean.ArticlesBean>(secondaryArticleDirectoryList) {
                @Override
                public View getView(FlowLayout parent, int position, NavgationBean.DataBean.ArticlesBean o) {
                    TextView tv = (TextView) LayoutInflater.from(view.getContext()).inflate(R.layout.item_flow_tags, tagFlowLayout, false);
                    tv.setText(o.getTitle());
                    return tv;
                }
            });
        }

        public void updateTagsFlowLayout(List<NavgationBean.DataBean.ArticlesBean> data) {
            secondaryArticleDirectoryList.clear();
            secondaryArticleDirectoryList.addAll(data);
            tagFlowLayout.getAdapter().notifyDataChanged();
        }
    }
}


