package com.baiwanlu.rxbus;

import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by lufei on 6/24/16.
 */
public class RxBus {
    private static RxBus ourInstance = new RxBus();

    private final Subject bus;
    private final Subject stickyBus;

    public static RxBus getDefault() {
        return ourInstance;
    }

    private RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
        stickyBus = new SerializedSubject<>(BehaviorSubject.create());
    }

    public void post (Object o) {
        bus.onNext(o);
    }

    public void postSticky (Object o) {
        stickyBus.onNext(o);
    }

    public <T> Subscription subscribe(Class<T> eventType, Action1<T> action, Action1<Throwable> throwableAction) {
        if (null == action) {
            action = new Action1<T>() {
                @Override
                public void call(T t) {
                    //do nothing
                }
            };
        }
        if (null == throwableAction) {
            throwableAction = new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    //do nothing
                }
            };
        }
        return bus.ofType(eventType).subscribe(action, throwableAction);
    }

    public <T> Subscription subscribeSticky(Class<T> eventType, Action1<T> action, Action1<Throwable> throwableAction) {
        if (null == action) {
            action = new Action1<T>() {
                @Override
                public void call(T t) {
                    //do nothing
                }
            };
        }
        if (null == throwableAction) {
            throwableAction = new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    //do nothing
                }
            };
        }
        return stickyBus.ofType(eventType).subscribe(action, throwableAction);
    }

}
