package com.zhouzhuo.reconsitutionmvp.base;

import java.io.Serializable;

/**
 * Created by zhouzhuo on 2017/11/3.
 */

public class BaseResponse<T> implements Serializable {
    public String code;
    public String msg;
    public T data;
    public boolean success(){
        return "1".equals(code);
    }
}
