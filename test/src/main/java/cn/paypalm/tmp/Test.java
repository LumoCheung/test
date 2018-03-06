package cn.paypalm.tmp;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author zhangzilu02
 * @create 2018/2/7
 **/
public class Test {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");

        System.out.println(sdf.parse("20180207").getTime());
    }

}
