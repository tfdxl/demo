package com.example.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tianfeng on 2017/7/10.
 */
@Entity
@Table(name="fcm_car_deal")
public class Trace {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long carTraceId;
    private String vin; // 车架号
    private Long carDealId; // 车辆交易编号
    private String saleOrderNo; // 销售订单编号
    private Integer actionType; // 动作类型 @see TraceActionTypeEnum
    private String actionDesc; // 动作描述
    private Integer resultStatus; // 结果状态 @see CarDealStatusEnum
    private String resultStatusDesc; // 结果状态描述
    private String msgId; // 涉及的消息ID
    private String userId; // 用户编号
    private String userName; // 用户姓名
    private String userPhone; // 用户手机号
    private Date dateHappen; // 发生时间

    public Long getCarTraceId() {
        return carTraceId;
    }

    public void setCarTraceId(Long carTraceId) {
        this.carTraceId = carTraceId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Long getCarDealId() {
        return carDealId;
    }

    public void setCarDealId(Long carDealId) {
        this.carDealId = carDealId;
    }

    public String getSaleOrderNo() {
        return saleOrderNo;
    }

    public void setSaleOrderNo(String saleOrderNo) {
        this.saleOrderNo = saleOrderNo;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public String getActionDesc() {
        return actionDesc;
    }

    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc;
    }

    public Integer getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(Integer resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getResultStatusDesc() {
        return resultStatusDesc;
    }

    public void setResultStatusDesc(String resultStatusDesc) {
        this.resultStatusDesc = resultStatusDesc;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Date getDateHappen() {
        return dateHappen;
    }

    public void setDateHappen(Date dateHappen) {
        this.dateHappen = dateHappen;
    }
}
