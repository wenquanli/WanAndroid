package com.example.wanandroid.contract;

import com.example.wanandroid.bean.OffAccountBean;
import com.example.wanandroid.bean.WXArticleBean;

import io.reactivex.Observable;

public class OffAccountContract {
    //TODO IBaseView 要再向上封装一层，包括MainContract里面那个
    public interface IBaseView {
        /**
         * <p>加载错误回调</p>
         * @param e Throwable
         */
        void onError(Throwable e);

        /**
         * <p>加载完成</p>
         */
        void onComplete();
    }
    public interface IOffAccountModel{
        Observable<OffAccountBean> loadOffAccount();
    }
    public interface IOffAccountView {
        void loadOffAccount(OffAccountBean offAccountBean);
    }
    public interface IOffAccountPresenter {
        void loadOffAccount();
    }

    public interface IWXArticleModel{
        Observable<WXArticleBean> loadWXArticle(int chapter, int page);
        Observable<WXArticleBean> refreshWXArticle(int chapter);
    }
    public interface IWXArticleView extends IBaseView{
        void loadWXArticle(WXArticleBean wXArticleBean);
        void refreshWXArticle(WXArticleBean wXArticleBean);
    }
    public interface IWXArticlePresenter{
        void loadWXArticle(int chapter, int page);
        void refreshWXArticle(int chapter);
    }
}
