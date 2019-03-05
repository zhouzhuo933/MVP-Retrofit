package com.zhouzhuo.reconsitutionmvp.app.welcome.model;

import android.util.Log;

import com.zhouzhuo.reconsitutionmvp.api.Api;
import com.zhouzhuo.reconsitutionmvp.app.welcome.bean.WelcomeBean;
import com.zhouzhuo.reconsitutionmvp.app.welcome.bean.WelcomeBeans;
import com.zhouzhuo.reconsitutionmvp.app.welcome.contract.WelcomeContract;
import com.zhouzhuo.reconsitutionmvp.rx.RxSchedulers;

import java.util.HashMap;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zhouzhuo on 2019/3/5.
 */

public class WelcomeModel implements WelcomeContract.Model{
    @Override
    public Observable<WelcomeBean> loadWelcome() {
        HashMap<String,String> jsonObjectNew = getHashMap(new HashMap<String, String>());
        //配合RxJava 操作符做一些处理
        return Api.getApiService().getWelcome(jsonObjectNew).map(new Func1<WelcomeBeans, WelcomeBean>() {
            @Override
            public WelcomeBean call(WelcomeBeans welcomebean) {
                //TODO 对数据做一些处理 过滤的操作
                Log.e("WelcomeModel","WelcomeModel:"+welcomebean.toString());
                Log.e("WelcomeModel","WelcomeModel data:"+welcomebean.data.toString());
                return welcomebean.data;
            }
        }).compose(RxSchedulers.<WelcomeBean>io_main());

    }

    private  HashMap<String,String> getHashMap(HashMap<String,String> jsonObject){
        jsonObject.put("token", "");
        jsonObject.put("sid", "");
        jsonObject.put("appType","Android");
        jsonObject.put("appPlatform","3");
        jsonObject.put("umengchannel", "youMeng");
        jsonObject.put("appVersion", "1.0.0");
        return jsonObject;

    }
}
