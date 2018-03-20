package com.zlc.littlegirl;

import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangzilu02
 * @create 2018/3/20
 **/
public class Reckon {
    /***/
    private static final double chaX=0.3333;
    /***/
    private static final double sumX=0.8;
    /***/
    private static final double nX=0.6667;

    private static final String filePath="/Users/zlc/tmp/littlegirl.txt";

    public static void main(String[] args) throws IOException {
        List<String> lines=FileUtils.readLines(new File(filePath));
        BigDecimal[] iis=new BigDecimal[lines.size()];
        int[] is=new int[lines.size()];
        for(int i=0;i<lines.size();i++){
            if(StringUtils.isEmpty(lines.get(i))){
                System.err.println("错误出现空值"+lines);
            }
            iis[i]=new BigDecimal(lines.get(i));
            s=s.add(iis[i]);
        }
        reckon(iis,is,0,new BigDecimal(0),0);
        System.out.println("总额为："+s);
    }

    private static BigDecimal cha=new BigDecimal(15*chaX);
    private static BigDecimal s=new BigDecimal(0);

    private static void reckon(BigDecimal[] iis,int[] is,int i,BigDecimal sum,int n){

        if(iis.length<=i){
            BigDecimal b=sum.subtract(
                    sum.divide(new BigDecimal(30),0, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal(30)));
            if(n>iis.length*nX &&
                    b.abs().compareTo(cha)<=0&&sum.compareTo(s.multiply(new BigDecimal(sumX)))>=0
            ) {
//                cha=b.abs().min(cha);
                System.out.println(n + "/" + b + "/" + sum + Arrays.toString(is));
            }
            return;
        }

        is[i]=0;
        reckon(iis,is,i+1,sum,n);
        is[i]=1;
        reckon(iis,is,i+1,sum.add(iis[i]),n+1);
    }

}
