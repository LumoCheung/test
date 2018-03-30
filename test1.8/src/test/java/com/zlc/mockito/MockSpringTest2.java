package com.zlc.mockito;

import com.zlc.TestBase;
import com.zlc.utils.AopTargetUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

/**
 * https://www.cnblogs.com/zishi/p/6780719.html
 * Mockito讲解比较详细
 * @author zhangzilu02
 * @create 2018/3/30
 **/
public class MockSpringTest2 extends TestBase {

    @Autowired
    private OrderBefore orderBefore;

    @Autowired
    private OrderStart orderStart;

    @InjectMocks
    private OrderCreate orderCreate = mock(OrderCreate.class);

    @Mock
    private OrderHelper orderHelper;

    @Before
    public void initMocks() throws Exception {
        MockitoAnnotations.initMocks(this);


        /**@see http://www.cnblogs.com/syxchina/p/4150879.html*/
        //查看这部分代码，代理注入了mock对象
        ReflectionTestUtils.setField(AopTargetUtils.getTarget(orderStart), "orderCreate", orderCreate);
        doReturn(11).when(orderCreate).getAmt();
        doReturn("success").when(orderHelper).resolve();
        doCallRealMethod().when(orderCreate).create();
    }

    @Test
    public void create() {
        System.out.println("start mock...");
        orderBefore.before();
    }

}
