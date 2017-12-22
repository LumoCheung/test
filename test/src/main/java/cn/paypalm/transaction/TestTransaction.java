package cn.paypalm.transaction;

import cn.paypalm.transaction.bean.ActionBean;
import cn.paypalm.transaction.servcie.TransactionService;
import cn.paypalm.util.UGetAddress;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class TestTransaction {
	private final static SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");

	/** 
	 * main
	 * 方法描述: 
	 * 逻辑描述: 
	 * @param args
	 * @since Ver 1.00
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext  context = new ClassPathXmlApplicationContext(new String[]{"classpath*:transaction/mybatis-config.xml"});
//        context.getEnvironment().setActiveProfiles("oracle");

        context.getEnvironment().setActiveProfiles("mysql");
        context = new ClassPathXmlApplicationContext(new String[]{"classpath*:transaction/spring.xml"},true,context);
//        context.refresh();

        context.start();

		ActionBean bean=new ActionBean();
		bean.setActionType(1);
		bean.setActionTime(Long.parseLong(sdf.format(new Date())));
		try {
			bean.setIp(UGetAddress.getAddress());
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		bean.setOrderNo(986532);
		bean.setIsSuccess(0);
		bean.setIsRefund(0);
		bean.setMerId("1234");
		bean.setRspCode("0b0000");
		bean.setIsAgreeMent(0);
		bean.setRspDesc("你懂得");
		bean.setTranAmt(22);
		bean.setMsgId(UUID.randomUUID().toString().replace("-",""));
		System.out.println(bean);

		TransactionService service=context.getBean("transactionService", TransactionService.class);
		//初始化-事务调用非事务
		service.insert(bean);
        //传播机制
//		service.Update(bean);

        //乐观锁
//        service.update(bean);

        //无事务方法调用有事务方法
//        service.updateA(bean);
	}

}
