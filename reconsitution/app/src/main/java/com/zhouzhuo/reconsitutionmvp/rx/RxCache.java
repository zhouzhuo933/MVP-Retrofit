package com.zhouzhuo.reconsitutionmvp.rx;

import android.content.Context;


import com.zhouzhuo.reconsitutionmvp.utils.ACache;

import java.io.Serializable;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhouzhuo on 2017/11/3.
 * 处理服务器数据的缓存
 */

public class RxCache {
    public static <T> Observable<T> load(final Context context,
                                         final String cacheKey,
                                         final int expireTime,
                                         Observable<T> fromNetWork,
                                         boolean forceRefresh){
        Observable<T> fromCache = Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                //获取缓存
                T cache = (T) ACache.get(context).getAsObject(cacheKey);
                if(cache !=null){
                    subscriber.onNext(cache);
                }else {
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        //这里的fromNetwork 不需要指定Schedule,在handlerRequest中已经变换了
        fromNetWork = fromNetWork.map(new Func1<T, T>() {
            @Override
            public T call(T result) {
                ACache.get(context).put(cacheKey,(Serializable)result,expireTime);
                return result;
            }
        });
        if(forceRefresh){
            return fromNetWork;
        }else {
            //将缓存和网络的数据合并
            return  Observable.concat(fromCache,fromNetWork).first();
        }

    }
}
