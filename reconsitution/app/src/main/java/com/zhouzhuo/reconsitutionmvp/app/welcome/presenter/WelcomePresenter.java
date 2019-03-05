package com.zhouzhuo.reconsitutionmvp.app.welcome.presenter;

import android.util.Log;

import com.zhouzhuo.reconsitutionmvp.app.welcome.bean.WelcomeBean;
import com.zhouzhuo.reconsitutionmvp.app.welcome.bean.WelcomeBeans;
import com.zhouzhuo.reconsitutionmvp.app.welcome.contract.WelcomeContract;
import com.zhouzhuo.reconsitutionmvp.rx.RxSubScriber;


/**
 * Created by zhouzhuo on 2019/3/5.
 */

public class WelcomePresenter extends WelcomeContract.Preserent{
    @Override
    public void loadWelcome() {
        Log.e("WelcomePresenter"," 开始 WelcomePresenter:");
        rxManager.add(model.loadWelcome().subscribe(new RxSubScriber<WelcomeBean>() {
            @Override
            protected void _onError(String message) {
                Log.e("WelcomePresenter"," onError WelcomePresenter:"+message);
            }

            @Override
            protected void _onNext(WelcomeBean welcomeBean) {
                view.returnBg(welcomeBean);
                Log.e("WelcomePresenter","WelcomePresenter:"+welcomeBean.toString());

            }
        }));
    }
}
