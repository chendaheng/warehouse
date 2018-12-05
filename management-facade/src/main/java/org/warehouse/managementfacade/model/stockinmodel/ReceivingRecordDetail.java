package org.warehouse.managementfacade.model.stockinmodel;

import io.swagger.annotations.ApiModel;

@ApiModel("收货记录明细表")
public class ReceivingRecordDetail {

    private int id;
    private String receivingSerialNo;
    private String planSerialNo;
    private String materialCode;
    private int unitId;
    private int receivingQuantity;
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

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getReceivingQuantity() {
        return receivingQuantity;
    }

    public void setReceivingQuantity(int receivingQuantity) {
        this.receivingQuantity = receivingQuantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
