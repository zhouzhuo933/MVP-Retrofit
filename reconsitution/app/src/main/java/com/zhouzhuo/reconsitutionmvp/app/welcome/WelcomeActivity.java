package com.zhouzhuo.reconsitutionmvp.app.welcome;

import android.util.Log;
import android.widget.ImageView;

import com.zhouzhuo.reconsitutionmvp.R;
import com.zhouzhuo.reconsitutionmvp.app.base.BaseActivity;
import com.zhouzhuo.reconsitutionmvp.app.welcome.bean.WelcomeBean;
import com.zhouzhuo.reconsitutionmvp.app.welcome.contract.WelcomeContract;
import com.zhouzhuo.reconsitutionmvp.app.welcome.model.WelcomeModel;
import com.zhouzhuo.reconsitutionmvp.app.welcome.presenter.WelcomePresenter;

/**
 * Created by zhouzhuo on 2019/3/5.
 */

public class WelcomeActivity extends BaseActivity<WelcomePresenter,WelcomeModel>
        implements WelcomeContract.View{
    private ImageView rl_rootview;
    @Override
    protected void initView() {
        rl_rootview = findViewById(R.id.rl_rootview);
        Log.e("WelcomePresenter"," 开始 WelcomePresenter 111:");
        mPresenter.loadWelcome();
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void doBeforeSetContentView() {

    }


    @Override
    public void returnBg(WelcomeBean fileBean) {

    }
}
