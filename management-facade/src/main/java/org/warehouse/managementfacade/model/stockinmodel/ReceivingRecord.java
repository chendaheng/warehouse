package org.warehouse.managementfacade.model.stockinmodel;

import io.swagger.annotations.ApiModel;

import java.sql.Timestamp;

@ApiModel("收货记录表")
public class ReceivingRecord {

    private int id;
    private String receivingSerialNo;
    private String planSerialNo;
    private String vouchSerialNo;
    private int vouchType;
    private int entryType;
    private int deliveryId;
    private int deliveryAddrId;
    private Timestamp receivingDate;
    private int warehouseId;
    private int receivingAddrId;
    private int operUserId;
    private int attachmentId;
    private int receivingStatus;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceivingSerialNo() {
        return receivingSerialNo;
    }

    public void setReceivingSerialNo(String receivingSerialNo) {
        this.receivingSerialNo = receivingSerialNo;
    }

    public String getPlanSerialNo() {
        return planSerialNo;
    }

    public void setPlanSerialNo(String planSerialNo) {
        this.planSerialNo = planSerialNo;
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

    public int getEntryType() {
        return entryType;
    }

    public void setEntryType(int entryType) {
        this.entryType = entryType;
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

    public Timestamp getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(Timestamp receivingDate) {
        this.receivingDate = receivingDate;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public int getReceivingStatus() {
        return receivingStatus;
    }

    public void setReceivingStatus(int receivingStatus) {
        this.receivingStatus = receivingStatus;
    }
}
