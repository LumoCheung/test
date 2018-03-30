package com.zlc.mockito;

import com.zlc.TestBase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * 代码来自网上
 * @see http://xiahaihuje.iteye.com/blog/2174150
 * @author zhangzilu02
 * @create 2018/3/30
 **/
public class MockSpringTest extends TestBase {

    @InjectMocks
    private OrderCreate orderCreate = mock(OrderCreate.class);

    @Mock
    private OrderHelper orderHelper;

    @Before
    public void initMocks() throws Exception {
        //OrderCreate 的 getAmt 方法和 OrderHelper 的 resolve 方法被成功 mock 掉。
        //
        //说明一下：@InjectMocks 会给 OrderCreate 装配 orderHelper 属性，其实就是根据 @Mock 来着啊。在下面这句代码执行完后
        MockitoAnnotations.initMocks(this);
        doReturn(11).when(orderCreate).getAmt();
        doReturn("success").when(orderHelper).resolve();
        doCallRealMethod().when(orderCreate).create();
    }

    @Test
    public void create() {
        System.out.println("start mock...");
        orderCreate.create();
    }
}
