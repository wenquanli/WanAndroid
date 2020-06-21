package com.example.wanandroid.contract;

import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.OffAccountBean;
import com.example.wanandroid.bean.WXArticleBean;

import java.util.List;

import io.reactivex.Observable;

public class OffAccountContract {
    //TODO IBaseView 要再向上封装一层，包括MainContract里面那个
    public interface IBaseView {
        /**
         * <p>加载错误回调</p>
         * @param e Throwable
         */
        void onError(String e);

        /**
         * <p>加载完成</p>
         */
        void onComplete();
    }
    public interface IOffAccountModel{
        public void loadOffAccount(OnLoadDatasListener<List<OffAccountBean.DataBean>> onLoadDatasListener);
    }
    public interface IOffAccountView {
        void loadOffAccount(List<OffAccountBean.DataBean> offAccountBean);
    }
    public interface IOffAccountPresenter {
        void loadOffAccount();
    }

    public interface IWXArticleModel{
        public void loadWXArticle(int chapter, int page, OnLoadDatasListener<WXArticleBean.DataBean> onLoadDatasListener);
        public void refreshWXArticle(int chapter, OnLoadDatasListener<WXArticleBean.DataBean> onLoadDatasListener);
    }
    public interface IWXArticleView extends IBaseView{
        void loadWXArticle(WXArticleBean.DataBean wXArticleBean);
        void refreshWXArticle(WXArticleBean.DataBean wXArticleBean);
    }
    public interface IWXArticlePresenter{
        void loadWXArticle(int chapter, int page);
        void refreshWXArticle(int chapter);
    }
}
