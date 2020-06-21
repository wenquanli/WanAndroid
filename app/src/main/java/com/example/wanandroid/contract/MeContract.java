package com.example.wanandroid.contract;

import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.BaseBean;
import com.example.wanandroid.bean.UserBean;

public class MeContract {
    public interface IMeView {
        /***
         * 登出成功
         */
        void loginOutSuccess(BaseBean.DataBean dataBean);

        /***
         * 登出失败
         */
        void LoginOutFail();
    }
    public interface IMePresenter {
        public void loginOut();
    }
    public interface IMeModel {
        public void handleLoginOut(OnLoadDatasListener<BaseBean.DataBean> onLoadDatasListener);
    }
}
