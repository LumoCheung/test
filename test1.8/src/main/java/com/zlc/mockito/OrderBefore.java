package com.zlc.mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangzilu02
 * @create 2018/3/30
 **/
@Component
public class OrderBefore {

    @Autowired
    private OrderStart orderStart;

    public void before(){
        System.out.println("before...");
        orderStart.start();
    }
}