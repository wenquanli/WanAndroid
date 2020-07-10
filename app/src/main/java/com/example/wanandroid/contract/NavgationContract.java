package com.example.wanandroid.contract;




import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.NavgationBean;

import java.util.List;

public class NavgationContract {
    public interface INavgationView{
        public void updateDetailNavgation(List<NavgationBean.DataBean> beanList);
    }
    public interface INavgationPresenter{
        public void getDetailNavgation();
    }
    public interface INavgationModel{
        public void getDetailNavgation(OnLoadDatasListener<List<NavgationBean.DataBean>> onLoadDatasListener);
    }
}
