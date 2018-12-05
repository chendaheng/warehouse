package org.warehouse.managementfacade.model.stockinmodel;


import io.swagger.annotations.ApiModel;

import java.sql.Timestamp;

@ApiModel("入库计划表")
public class WarehouseStockInPlan {

    private int id;
    private String planSerialNo;
    private int entryType;
    private String vouchSerialNo;
    private int vouchType;
    private int deptId;
    private int onwerId;
    private Timestamp startDate;
    private Timestamp arrivalDate;
    private int deliveryId;
    private int deliveryAddrId;
    private int warehouseId;
    private int receivingAddrId;
    private int operUserId;
    private int status;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanSerialNo() {
        return planSerialNo;
    }

    public void setPlanSerialNo(String planSerialNo) {
        this.planSerialNo = planSerialNo;
    }

    public int getEntryType() {
        return entryType;
    }

    public void setEntryType(int entryType) {
        this.entryType = entryType;
    }

    public String getVouchSerialNo() {
        return vouchSerialNo;
    }

    public void setVouchSerialNo(String vouchSerialNo) {
        this.vouchSerialNo = vouchSerialNo;
    }

    public int getVouchType() {
        return vouchType;
    }

    public void setVouchType(int vouchType) {
        this.vouchType = vouchType;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getOnwerId() {
        return onwerId;
    }

    public void setOnwerId(int onwerId) {
        this.onwerId = onwerId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getDeliveryAddrId() {
        return deliveryAddrId;
    }

    public void setDeliveryAddrId(int deliveryAddrId) {
        this.deliveryAddrId = deliveryAddrId;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getReceivingAddrId() {
        return receivingAddrId;
    }

    public void setReceivingAddrId(int receivingAddrId) {
        this.receivingAddrId = receivingAddrId;
    }

    public int getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(int operUserId) {
        this.operUserId = operUserId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
