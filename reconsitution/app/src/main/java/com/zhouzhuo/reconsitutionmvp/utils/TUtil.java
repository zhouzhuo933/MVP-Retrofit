package com.zhouzhuo.reconsitutionmvp.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by zhouzhuo on 2017/11/3.
 * 类转换初始化
 */

public class TUtil {
    private TUtil(){}
    public static <T> T getT(Object o,int i){
        try {
            /*Type type = o.getClass().getGenericSuperclass();
            // 强转为“参数化类型”
            //ParameterizedType参数化类型，即泛型
            ParameterizedType pt = (ParameterizedType) type; // BaseDao
            // 获取参数化类型中，实际类型的定义
            Type[] ts = pt.getActualTypeArguments();
            // 转换
            Class c= (Class) ts[i];
            return (Class<T> c).newInstance(）;*/
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }
}
