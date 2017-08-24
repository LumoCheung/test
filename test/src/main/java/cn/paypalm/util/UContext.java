package cn.paypalm.util;

import org.springframework.context.ApplicationContext;

public class UContext {

    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    public static void setContext(ApplicationContext contextx){
        context = contextx;
    }

}