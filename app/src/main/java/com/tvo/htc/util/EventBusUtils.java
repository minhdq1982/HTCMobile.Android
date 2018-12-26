package com.tvo.htc.util;

import org.greenrobot.eventbus.EventBus;

/**
 * Create by Ngocji on 10/24/2018
 **/


public class EventBusUtils {
    public static void register(Object target) {
        EventBus.getDefault().register(target);
    }

    public static void unregister(Object target) {
        EventBus.getDefault().unregister(target);
    }

    public static void postEvent(Object o) {
        EventBus.getDefault().post(o);
    }

    public static void postEventSticky(Object o) {
        EventBus.getDefault().postSticky(o);
    }
}
