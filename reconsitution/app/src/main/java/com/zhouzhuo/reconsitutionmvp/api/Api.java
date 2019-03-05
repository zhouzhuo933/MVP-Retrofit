package com.zhouzhuo.reconsitutionmvp.api;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhouzhuo on 2017/11/2.
 */

public class Api {
    public  static ApiService  getApiService(){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.urlHeader)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        return apiService;
    }


    private OkHttpClient.Builder getOkhttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.authenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                //call API to refresh token
               /* Call<Token> call = getAssistantServiceApi().refreshToken(
                        AssistantService.GRANT_TPYE_REFRESH_TOKEN,
                        SharedPreferenceUtils.getToken().getRefresh_token(),
                        AssistantService.CLIENT_ID,
                        AssistantService.CLIENT_SECRET);
                //get the new token.
                Token freshToken = call.execute().body();
                if (freshToken == null) {
                    return null;
                }
                //save token and retry the previous request.
                SharedPreferenceUtils.setToken(freshToken);*/
                return response.request().newBuilder()
                        .removeHeader("Authorization")
                        .addHeader("Authorization", ""/*StringUtil.getTokenHeader()*/)
                        .build();
            }


        });

        return builder;

    }

}
