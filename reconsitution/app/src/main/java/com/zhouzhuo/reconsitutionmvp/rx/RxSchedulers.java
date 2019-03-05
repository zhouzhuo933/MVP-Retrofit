package com.zhouzhuo.reconsitutionmvp.rx;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhouzhuo on 2017/11/3.
 * RxJava 线程调度管理
 */

public class RxSchedulers {
    public static <T> Observable.Transformer<T,T> io_main(){
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
 }
