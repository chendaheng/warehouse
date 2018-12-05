package org.warehouse.managementfacade.model.stockinmodel;

import io.swagger.annotations.ApiModel;
import java.sql.Timestamp;

@ApiModel("入库记录表")
public class WarehouseStockInRecord {

    private int id;
    private String entrySerialNo;
    private int entryType;
    private String vouchSerialNo;
    private int vouchType;
    private int warehouseId;
    private int deptId;
    private int operUserId;
    private int deliveryId;
    private Timestamp entryDate;
    private int result;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntrySerialNo() {
        return entrySerialNo;
    }

    public void setEntrySerialNo(String entrySerialNo) {
        this.entrySerialNo = entrySerialNo;
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

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(int operUserId) {
        this.operUserId = operUserId;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Timestamp getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Timestamp entryDate) {
        this.entryDate = entryDate;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}


