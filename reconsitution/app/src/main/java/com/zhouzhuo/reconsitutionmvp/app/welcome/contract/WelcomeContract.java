package com.zhouzhuo.reconsitutionmvp.app.welcome.contract;

import com.zhouzhuo.reconsitutionmvp.app.base.BaseModel;
import com.zhouzhuo.reconsitutionmvp.app.base.BasePresenter;
import com.zhouzhuo.reconsitutionmvp.app.base.BaseView;
import com.zhouzhuo.reconsitutionmvp.app.welcome.bean.WelcomeBean;
import com.zhouzhuo.reconsitutionmvp.app.welcome.bean.WelcomeBeans;

import rx.Observable;

/**
 * Created by zhouzhuo on 2019/3/5.
 */

public interface WelcomeContract {
     interface Model extends BaseModel {
        Observable<WelcomeBean> loadWelcome();
    }

     interface View extends BaseView {
        void returnBg(WelcomeBean fileBean);
    }

     abstract class Preserent extends BasePresenter<View,Model> {
        public abstract void loadWelcome();
    }
}
