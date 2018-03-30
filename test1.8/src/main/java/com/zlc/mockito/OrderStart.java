package com.zlc.mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangzilu02
 * @create 2018/3/30
 **/
@Component
public class OrderStart {

    @Autowired
    private OrderCreate orderCreate;

    public void start(){
        System.out.println("start...");
        orderCreate.create();
    }
}