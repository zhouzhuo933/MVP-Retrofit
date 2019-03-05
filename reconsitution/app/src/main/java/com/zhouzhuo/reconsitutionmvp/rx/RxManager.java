package com.zhouzhuo.reconsitutionmvp.rx;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhouzhuo on 2017/11/2.
 */

public class RxManager {
    public RxBus mRxBus = RxBus.getInstance();
    //管理rxbus订阅
    private Map<String, Observable<?>> mObservables = new HashMap<>();
    //管理Observables 和 Subscribers 订阅
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public <T>void on(String eventName, Action1<T> action1){
        Observable<T> mObservable = mRxBus.register(eventName);
        mObservables.put(eventName,mObservable);
        //订阅管理
        mCompositeSubscription.add(mObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(action1, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        }));
    }
    //单纯的Observables 和Subscribres 管理
    public void add(Subscription m){
        //订阅管理
        mCompositeSubscription.add(m);
    }

    //单个presenter 生命周期结束,取消订阅所有rxbus 观察
    public void clear(){
        //取消所有订阅
        mCompositeSubscription.unsubscribe();
        for (Map.Entry<String,Observable<?>> entry:mObservables.entrySet()){
            //移除rxbus观察
            mRxBus.unregister(entry.getKey(),entry.getValue());
        }
    }

    //发送rxbus
    public void post(Object tag,Object content){
        mRxBus.post(tag,content);
    }
    
}
