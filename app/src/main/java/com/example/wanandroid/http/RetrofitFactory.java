package com.example.wanandroid.http;

import com.example.wanandroid.base.BaseObeserver;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.BaseBean;
import com.example.wanandroid.bean.CoinBean;
import com.example.wanandroid.bean.KonwledgeHireBean;
import com.example.wanandroid.bean.MainArticleBean;
import com.example.wanandroid.bean.NavgationBean;
import com.example.wanandroid.bean.OffAccountBean;
import com.example.wanandroid.bean.UserBean;
import com.example.wanandroid.bean.WXArticleBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private final ApiService mApiService;

    private static RetrofitFactory mRetrofitFactory;

    private RetrofitFactory() {
        //创建日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//设定日志级别
        //创建OkHttpClient
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(ApiService.HTTP_TIME, TimeUnit.SECONDS)
                .readTimeout(ApiService.HTTP_TIME, TimeUnit.SECONDS)
                .writeTimeout(ApiService.HTTP_TIME, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)//添加拦截器
                .addInterceptor(new ReceivedCookiesInterceptor())
                .addInterceptor(new AddCookiesInterceptor())
                .build();

        //创建Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava2转换器
                .client(mOkHttpClient)
                .build();

        //创建接口实现类
        mApiService = retrofit.create(ApiService.class);

    }

    public static RetrofitFactory getInstence() {
        if (mRetrofitFactory == null) {
            synchronized (RetrofitFactory.class) {
                if (mRetrofitFactory == null)
                    mRetrofitFactory = new RetrofitFactory();
            }
        }
        return mRetrofitFactory;
    }
    public ObservableTransformer threadTransformer() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    public ApiService API() {
        return mApiService;
    }
    public void login(String username, String  password, BaseObeserver<UserBean.DataBean> scheduler) {
        API()
                .login(username,password)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void loginOut(BaseObeserver<BaseBean.DataBean> scheduler) {
        API()
                .loginOut()
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void loadCoinInfo(BaseObeserver<CoinBean.DataBean> scheduler) {
        API()
                .loadCoinInfo()
                .compose(threadTransformer())
                .subscribe(scheduler);
    }

    public void loadBanner(BaseObeserver<List<BannerBean.DataBean>> scheduler) {
        API()
                .loadBanner()
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void loadMainArticle(int page,BaseObeserver<MainArticleBean.DataBean> scheduler) {
        API()
                .loadArticle(page)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void refreshMainArticle(BaseObeserver<MainArticleBean.DataBean> scheduler) {
        API()
                .refreshMainArticle()
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void loadOffAccount(BaseObeserver<List<OffAccountBean.DataBean>> scheduler) {
        API()
                .loadOffAccount()
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void loadWXMainArticle(int chapter, int page,BaseObeserver<WXArticleBean.DataBean> scheduler) {
        API()
                .loadWXArticle(chapter,page)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void refreshWXMainArticle(int chapter, BaseObeserver<WXArticleBean.DataBean> scheduler) {
        API()
                .refreshWXArticle(chapter)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }

    public void loadKonwledgeHeir(BaseObeserver<List<KonwledgeHireBean.DataBean>> scheduler) {
        API()
                .loadKonwledgeHeir()
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void loadNavgation(BaseObeserver<List<NavgationBean.DataBean>> scheduler) {
        API()
                .loadNavgation()
                .compose(threadTransformer())
                .subscribe(scheduler);
    }

}
