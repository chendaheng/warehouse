package org.warehouse.managementfacade.model.stockinimportmodel;

import io.swagger.annotations.ApiModel;

@ApiModel("检验记录明细表")
public class QualityTestRecordDetail {

    private int id;
    private String qualityTestSerialNo;
    private String receivingSerialNo;
    private String materialCode;
    private int unitId;
    private int qualityTestQuantity;
    private String qualityTestMethod;
    private String qualityTestStandard;
    private int entryQuantity;
    private int returnQuantity;
    private String reason;
    private String result;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQualityTestSerialNo() {
        return qualityTestSerialNo;
    }

    public void setQualityTestSerialNo(String qualityTestSerialNo) {
        this.qualityTestSerialNo = qualityTestSerialNo;
    }

    public String getReceivingSerialNo() {
        return receivingSerialNo;
    }

    public void setReceivingSerialNo(String receivingSerialNo) {
        this.receivingSerialNo = receivingSerialNo;
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

    public int getQualityTestQuantity() {
        return qualityTestQuantity;
    }

    public void setQualityTestQuantity(int qualityTestQuantity) {
        this.qualityTestQuantity = qualityTestQuantity;
    }

    public String getQualityTestMethod() {
        return qualityTestMethod;
    }

    public void setQualityTestMethod(String qualityTestMethod) {
        this.qualityTestMethod = qualityTestMethod;
    }

    public String getQualityTestStandard() {
        return qualityTestStandard;
    }

    public void setQualityTestStandard(String qualityTestStandard) {
        this.qualityTestStandard = qualityTestStandard;
    }

    public int getEntryQuantity() {
        return entryQuantity;
    }

    public void setEntryQuantity(int entryQuantity) {
        this.entryQuantity = entryQuantity;
    }

    public int getReturnQuantity() {
        return returnQuantity;
    }

    public void setReturnQuantity(int returnQuantity) {
        this.returnQuantity = returnQuantity;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
