package com.zhouzhuo.reconsitutionmvp.app.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhouzhuo.reconsitutionmvp.rx.RxManager;
import com.zhouzhuo.reconsitutionmvp.utils.TUtil;

/**
 * Created by zhouzhuo on 2019/3/5.
 */

public abstract class BaseFragment<T extends BasePresenter,E extends BaseModel> extends Fragment {
    private View rootView;
    public T mPresenter;
    public E mModel;
    public RxManager mRxManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView == null){
            rootView = inflater.inflate(getLayoutResource(),container,false);
        }
        mRxManager = new RxManager();
        bindView(rootView);
        mPresenter = TUtil.getT(this,0);
        mModel = TUtil.getT(this,1);
        if(mPresenter!=null){
            mPresenter.context = getActivity();
        }
        initPresenter();
        initView();
        return rootView;
    }
    /***************子类实现*************************/
    protected abstract void initView();
    protected abstract void initPresenter();
    protected abstract void bindView(View rootView);
    protected abstract int getLayoutResource();
    /***************子类实现*************************/
}
