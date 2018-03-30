package com.zlc.mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangzilu02
 * @create 2018/3/30
 **/
@Component
public class OrderCreate {

    @Autowired
    private OrderHelper orderHelper;

    public void create() {
        System.out.println(getAmt());
        System.out.println(orderHelper.resolve());
    }

    public int getAmt() {
        return 10;
    }
}