package com.example.wanandroid.contract;

import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.KonwledgeHireBean;

import java.util.List;

public class KonwledgeHierContract {
    public interface IKonwledgeHireView{
        public void updateDetailKnowledgeHier(List<KonwledgeHireBean.DataBean> beanList);
    }
    public interface IKonwledgeHirePresenter{
        public void getDetailKnowledgeHier();
    }
    public interface IKonwledgeHireModel{
        public void getDetailKnowledgeHierFromIn(OnLoadDatasListener<List<KonwledgeHireBean.DataBean>> onLoadDatasListener);
    }
}
