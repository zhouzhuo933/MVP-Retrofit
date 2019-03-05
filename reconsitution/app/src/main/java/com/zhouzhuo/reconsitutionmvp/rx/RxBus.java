package com.zhouzhuo.reconsitutionmvp.rx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * Created by zhouzhuo on 2017/11/2.
 * 用RXJava实现EventBus
 */

public class RxBus {
    private static RxBus instance;
    private ConcurrentHashMap<Object,List<Subject>> subjectMapper = new ConcurrentHashMap<>();
    private RxBus(){}

    public static synchronized RxBus getInstance(){
        if(instance ==null){
            instance = new RxBus();
        }
        return  instance;
    }

    //注册事件源
    public  <T> Observable<T> register(Object tag){
        List<Subject> subscriberList = subjectMapper.get(tag);
        if(subscriberList == null){
            subscriberList = new ArrayList<>();
            subjectMapper.put(tag,subscriberList);
        }
        Subject<T,T> subject;
        subscriberList.add(subject = PublishSubject.create());
        return subject;
    }

    //取消监听
    public RxBus unregister(Object tag,Observable<?> observable){
        if(observable == null){
            return getInstance();
        }else {
            List<Subject> subjects = subjectMapper.get(tag);
            if(subjects!=null && subjects.size()>0){
                subjects.remove(observable);
            }else {
                subjectMapper.remove(tag);
            }
            return getInstance();
        }
    }

    public void post(Object object){
        post(object.getClass().getName(),object);
    }
    //触发事件
    public void post(Object name, Object content) {
        List<Subject> subjectList = subjectMapper.get(name);
        if(subjectList!=null&&subjectList.size()>0){
            for (Subject subject:subjectList){
                subject.onNext(content);
            }
        }
    }
}
