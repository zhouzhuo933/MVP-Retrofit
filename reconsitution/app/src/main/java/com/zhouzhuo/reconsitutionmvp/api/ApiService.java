package com.zhouzhuo.reconsitutionmvp.api;


import com.zhouzhuo.reconsitutionmvp.app.welcome.bean.WelcomeBean;
import com.zhouzhuo.reconsitutionmvp.app.welcome.bean.WelcomeBeans;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by zhouzhuo on 2017/11/2.
 */

public interface ApiService {

    @GET("/moduleload/getConfigurationList")
    Observable<WelcomeBeans> getWelcome();

    @FormUrlEncoded
    @POST("/moduleload/getConfigurationList")
    Observable<WelcomeBeans> getWelcome(@FieldMap Map<String, String> fields);

}
