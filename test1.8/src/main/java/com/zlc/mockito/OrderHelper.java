package com.zlc.mockito;

import org.springframework.stereotype.Component;

/**
 * @author zhangzilu02
 * @create 2018/3/30
 **/
@Component
public class OrderHelper {

    public String resolve() {
        return "resolve order";
    }
}
