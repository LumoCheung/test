package cn.paypalm.transaction.bean;

public class ActionBean {
	/**
	 * 消息码
	 */
	private String msgId;
	/**
	 * 本机ip地址
	 */
	private String ip;
    /**
     * 通知成功时间
     */
    private long actionTime;
    /**
     * 通知类型(1.实时通知2.异步通知3.商户查询)
     */
    private int actionType;
    /**
     * 商户编号
     */
    private String merId;
    
    /**
     * 响应码
     */
    private String rspCode;

    /**
     * 响应描述
     */
    private String rspDesc;
    /**
     * 订单号
     */
	private long orderNo;
	/**
	 * 平台批次号
	 */
	private long batchNo;
	/**
	 * 交易是否成功
	 */
	private int isSuccess;
	/**
	 * 三个系统是否同步
	 */
	private int isAgreeMent;
	/**
	 * 金额
	 */
	private long tranAmt;
	/**
	 * 是否退款
	 */
	private int isRefund;

	private Long actionSn;

	private Integer oldIsAgreeMent;

	public Integer getOldIsAgreeMent() {
		return oldIsAgreeMent;
	}

	public void setOldIsAgreeMent(Integer oldIsAgreeMent) {
		this.oldIsAgreeMent = oldIsAgreeMent;
	}

	public Long getActionSn() {
		return actionSn;
	}

	public void setActionSn(Long actionSn) {
		this.actionSn = actionSn;
	}

	public long getTranAmt() {
		return tranAmt;
	}

	public void setTranAmt(long tranAmt) {
		this.tranAmt = tranAmt;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the actionTime
	 */
	public long getActionTime() {
		return actionTime;
	}

	/**
	 * @param actionTime the actionTime to set
	 */
	public void setActionTime(long actionTime) {
		this.actionTime = actionTime;
	}

	/**
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}

	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	/**
	 * @return the merId
	 */
	public String getMerId() {
		return merId;
	}

	/**
	 * @param merId the merId to set
	 */
	public void setMerId(String merId) {
		this.merId = merId;
	}

	/**
	 * @return the respCode
	 */
	public String getRspCode() {
		return rspCode;
	}

	/**
	 * @param respCode the respCode to set
	 */
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}

	/**
	 * @return the respDesc
	 */
	public String getRspDesc() {
		return rspDesc;
	}

	/**
	 * @param respDesc the respDesc to set
	 */
	public void setRspDesc(String rspDesc) {
		this.rspDesc = rspDesc;
	}

	/**
	 * @return the orderNo
	 */
	public long getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return the batchNo
	 */
	public long getBatchNo() {
		return batchNo;
	}

	/**
	 * @param batchNo the batchNo to set
	 */
	public void setBatchNo(long batchNo) {
		this.batchNo = batchNo;
	}

	/**
	 * @return the isSuccess
	 */
	public int getIsSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * @return the isAgreeMent
	 */
	public int getIsAgreeMent() {
		return isAgreeMent;
	}

	/**
	 * @param isAgreeMent the isAgreeMent to set
	 */
	public void setIsAgreeMent(int isAgreeMent) {
		this.isAgreeMent = isAgreeMent;
	}
	
	/**
	 * @return the actionType
	 */
	public int getActionType() {
		return actionType;
	}

	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	/**
	 * @return the isRefund
	 */
	public int getIsRefund() {
		return isRefund;
	}

	/**
	 * @param isRefund the isRefund to set
	 */
	public void setIsRefund(int isRefund) {
		this.isRefund = isRefund;
	}

	/**
	 * <p>Title: toString</p>
	 * <p>Description:</p>
	 * @return 
	 * @see java.lang.Object#toString() 
	 */ 
	@Override
	public String toString() {
		return "ActionBean [msgId=" + msgId + ", ip=" + ip + ", actionTime="
				+ actionTime + ", merId=" + merId + ", rspCode=" + rspCode
				+ ", rspDesc=" + rspDesc + ", orderNo=" + orderNo
				+ ", batchNo=" + batchNo + ", isSuccess=" + isSuccess
				+ ", isAgreeMent=" + isAgreeMent + ", tranAmt=" + tranAmt + "]";
	}
	
}
