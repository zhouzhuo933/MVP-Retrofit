package com.zhouzhuo.reconsitutionmvp.app.base;

import android.content.Context;

import com.zhouzhuo.reconsitutionmvp.rx.RxManager;

/**
 * Created by zhouzhuo on 2019/3/5.
 */

public abstract class BasePresenter<T,E>{
    public Context context;
    public T view;
    public E model;
    public RxManager rxManager = new RxManager();
    public void setVM(T view,E model){
        this.view = view;
        this.model = model;
        this.onStart();

    }
    //可以做一些监听的操作，配合EventBus使用
    private void onStart() {

    }


    public void onDestroy(){
        rxManager.clear();
    }
}
