package cn.paypalm.redpackalg;

import java.util.Random;

public class RedPackAlg {
    final static Random r=new Random();

    public static Long alg(Long account,Integer num,Double key,Long max,Long min){
        if(num==1) {
            System.out.print(account+" ");
            return 0L;
        }
        Long avg=account/num;

        double m=avg*key;

        Long money=Double.valueOf(r.nextDouble()*m).longValue();

        if(money>max) money=max;
        else if(money<min) money=min;

        if((num-1)*min>account-money) money=min;
        else if((num-1)*max<account-money) money=max;

        System.out.print(money+" ");

        return account-money;
    }

    public static void main(String[] args){
        for(int i=0;i<100;i++){
            //满足max+min=account/num*2 最好
            long account=10000;
            int num=5;
            long max=4000;
            long min=100;
            double key=2;

            for(int j=0;j<num;j++){
                account=alg(account,num-j,key,max,min);
            }
            System.out.println("");
        }
    }

}
