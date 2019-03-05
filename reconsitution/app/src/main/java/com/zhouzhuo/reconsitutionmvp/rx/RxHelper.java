package com.zhouzhuo.reconsitutionmvp.rx;



import com.zhouzhuo.reconsitutionmvp.base.BaseResponse;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhouzhuo on 2017/11/3.
 * 对服务器返回数据成功和失败处理
 */

public class RxHelper {
    //对服务器返回数据进行预处理
    public static <T> Observable.Transformer<BaseResponse<T>,T> handlerResult(){
        return new Observable.Transformer<BaseResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseResponse<T>> baseResponseObservable) {
                return baseResponseObservable.flatMap(new Func1<BaseResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseResponse<T> result) {
                        if(result.success()){
                            return createData(result.data);
                        }else {
                            return Observable.error(new Exception());
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };

    }

    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                }catch (Exception e){
                    subscriber.onError(e);
                }

            }
        });
    }
}
