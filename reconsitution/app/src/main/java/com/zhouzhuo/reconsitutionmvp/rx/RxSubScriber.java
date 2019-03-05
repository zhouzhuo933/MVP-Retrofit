package com.zhouzhuo.reconsitutionmvp.rx;

import android.content.Context;


import rx.Subscriber;

/**
 * Created by zhouzhuo on 2017/11/3.
 * 订阅的封装
 */

public abstract class RxSubScriber<T> extends Subscriber<T> {
    private Context mContext;
    private String msg;
    private boolean showDialog;

    public RxSubScriber(){

    }

    public RxSubScriber(Context context){
        this.mContext = context;
    }

    public RxSubScriber(Context context,boolean showDialog){
        this.mContext = context;
        this.showDialog = showDialog;
    }
    @Override
    public void onStart() {
        super.onStart();
        if(showDialog){
            //TODO  显示对话框
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onCompleted() {
        if(showDialog){
            //TODO 关闭对话框
        }
    }

    @Override
    public void onError(Throwable e) {
        _onError(e.getMessage());
    }

    protected abstract void _onError(String message);

    protected abstract void _onNext(T t);
}
