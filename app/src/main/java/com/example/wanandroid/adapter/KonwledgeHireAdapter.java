package com.example.wanandroid.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.KonwledgeHireBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class KonwledgeHireAdapter extends RecyclerView.Adapter<KonwledgeHireAdapter.ViewHolder> {
    private Context mContext;
    private List<KonwledgeHireBean.DataBean> konwledgeHireList;

    public KonwledgeHireAdapter(RecyclerView recyclerView, List<KonwledgeHireBean.DataBean> articles) {
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
        if(konwledgeHireList != null) {
            KonwledgeHireBean.DataBean item = konwledgeHireList.get(position);
            holder.title.setText(item.getName());
            holder.updateTagsFlowLayout(item.getChildren());
        }
    }

    @Override
    public int getItemCount() {
        return konwledgeHireList!= null ? konwledgeHireList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View konwledgeHireView;
        private final List<KonwledgeHireBean.DataBean.ChildrenBean> secondaryArticleDirectoryList;
        private final TextView title;
        private final TagFlowLayout tagFlowLayout;

        public ViewHolder(@NonNull View view) {
            super(view);
            konwledgeHireView = view;
            secondaryArticleDirectoryList = new ArrayList();
            title = view.findViewById(R.id.title);
            tagFlowLayout = view.findViewById(R.id.flowLayout_tags);
            tagFlowLayout.setAdapter(new TagAdapter<KonwledgeHireBean.DataBean.ChildrenBean>(secondaryArticleDirectoryList) {
                @Override
                public View getView(FlowLayout parent, int position, KonwledgeHireBean.DataBean.ChildrenBean o) {
                    TextView tv = (TextView) LayoutInflater.from(view.getContext()).inflate(R.layout.item_flow_tags, tagFlowLayout, false);
                    tv.setText(o.getName());
                    return tv;
                }
            });
        }

        public void updateTagsFlowLayout(List<KonwledgeHireBean.DataBean.ChildrenBean> data) {
            secondaryArticleDirectoryList.clear();
            secondaryArticleDirectoryList.addAll(data);
            tagFlowLayout.getAdapter().notifyDataChanged();
        }
    }
}
