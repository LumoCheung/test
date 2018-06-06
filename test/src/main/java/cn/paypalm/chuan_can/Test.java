package cn.paypalm.chuan_can;

import java.math.BigDecimal;

/**
 * @author zhangzilu02
 * @create 2018/6/6
 **/
public class Test {

    public static void main(String[] args){
        Test t=new Test();
        Test.A a=t.new A();
        a.setA(new BigDecimal("223"));
        t.test1(a);
        System.out.println(a.getA());
    }

    public void test1(A a){//形参：
        //直接设置a对象的属性值，可以改变a的属性值，因为a的地址没有变，改的是a的地址那部分内容
        a.setA(new BigDecimal("123"));
        Test.A b=new Test().new A();
        b.setA(new BigDecimal("323"));
        //形参：a=b,将b的地址给a，因为是形参，所以调用的a值不变
        a=b;
    }

    public class A{
        BigDecimal a;

        public BigDecimal getA() {
            return a;
        }

        public void setA(BigDecimal a) {
            this.a = a;
        }
    }

}
