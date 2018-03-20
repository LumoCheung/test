package com.zlc.mockito;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.annotation.Repeat;

/**
 * @author zhangzilu02
 * @create 2018/3/8
 **/
public class TestSample {

    private Calculator calculator = new Calculator();

    @Before
    public void setUp() throws Exception
    {
        calculator.clear();
    }

    @Test
    public void testAdd()
    {
        calculator.add(1);
        calculator.add(2);
        int result = calculator.getResult();
        Assertions.assertThat(result).isEqualTo(3); //断言
    }

    @Ignore
    @Test
    public void testSubstract()
    {
        calculator.add(10);
        calculator.substract(2);
        int result = calculator.getResult();
        Assertions.assertThat(result).isEqualTo(8); //断言
    }

    @Repeat(3)
    @Test(timeout=3000)
    public void testDivide()
    {
        calculator.add(10);
        calculator.divide(2);
        int result = calculator.getResult();
        Assertions.assertThat(result).isEqualTo(5);
    }

}
