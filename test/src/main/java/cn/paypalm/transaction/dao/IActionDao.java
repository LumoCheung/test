package cn.paypalm.transaction.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.paypalm.transaction.bean.ActionBean;

@Repository
public interface IActionDao {
	/**
	 * 插入通知成功的信息
	 * insertNotifySuccessMessage
	 * 方法描述: 
	 * 逻辑描述: 
	 * @param actionBean
	 * @since Ver 1.00
	 */
	public int insertNotifySuccessMessage(ActionBean actionBean);
	/**
	 * 获取所有未做过三个系统统一的订单
	 * selectActionBeans
	 * 方法描述: 
	 * 逻辑描述: 
	 * @return 
	 * @since Ver 1.00
	 */
	public ActionBean selectActionBean(long orderNo);
	/**	 
	 * 更新查询是否统一的结果
	 * updateIsAgreeMentByOrderno
	 * 方法描述: 
	 * 逻辑描述: 
	 * @param actionBean
	 * @since Ver 1.00
	 */
	public int updateIsAgreeMentByOrderno(ActionBean actionBean);

	int insertMysql(ActionBean actionBean);

	ActionBean selectMysql(long orderNo);
}
