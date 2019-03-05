package com.zhouzhuo.reconsitutionmvp.app.welcome.bean;

/**
 * Created by zhouzhuo on 2019/3/5.
 */

public class WelcomeBeans {
    @Override
    public String toString() {
        return "WelcomeBean{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", data=" + data +
                '}';
    }

    public String status;
   public String message;
   public String timestamp;
   public String secretKey;
   public WelcomeBean data;

}
