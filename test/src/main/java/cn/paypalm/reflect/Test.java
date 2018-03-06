package cn.paypalm.reflect;

import java.lang.reflect.Field;

/**
 * @author zhangzilu02
 * @create 2018/3/6
 **/
public class Test {

    public static void main(String[] args){
        Obj obj1=new Obj();
        Obj2 obj2=new Obj2();
        obj2.setName3("123");

        obj1.setName1(obj2.getName3());

        for(Field field:obj1.getClass().getDeclaredFields()){
            System.out.println("//"+field.getName());
        }
    }

}
