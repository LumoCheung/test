package com.zlc.mockito;

/**
 * @author zhangzilu02
 * @create 2018/3/8
 **/
public class Calculator {

    private static int result; // 静态变量，用于存储运行结果

    public void add(int n) {
        result += n;
    }

    public void substract(int n) {
        result -= n;
    }

    public void multiply(int n) {
        result *= n;
    }

    public void divide(int n) {
        result /= n;
    }

    public void square(int n) {
        result = n * n;
    }

    public void clear() {// 将结果清零
        result = 0;
    }

    public int getResult() {
        return result;
    }

}
