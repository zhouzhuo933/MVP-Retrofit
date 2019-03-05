package com.zhouzhuo.reconsitutionmvp.app.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.zhouzhuo.reconsitutionmvp.rx.RxManager;
import com.zhouzhuo.reconsitutionmvp.utils.TUtil;

/**
 * Created by zhouzhuo on 2019/3/5.
 */

public abstract class BaseActivity<T extends BasePresenter,E extends BaseModel> extends FragmentActivity {
    public T mPresenter;
    public E mModel;
    public Context mContext;
    public RxManager mRxManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxManager = new RxManager();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        doBeforeSetContentView();
        setContentView(getLayoutId());
        mContext = this;
        mPresenter = TUtil.getT(this,0);
        if(mPresenter!=null){
            mPresenter.context = this;
        }
        mModel = TUtil.getT(this,1);
        this.initPresenter();
        this.initView();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter !=null){
            mPresenter.onDestroy();
        }
        if(mRxManager !=null){
            mRxManager.clear();
        }
    }


    /******************子类实现********************/
    protected abstract void initView();
    protected abstract void initPresenter();
    protected abstract int getLayoutId();
    protected abstract void doBeforeSetContentView();
    /******************子类实现********************/
}
