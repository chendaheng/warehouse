package org.warehouse.managementfacade.model.stockinmodel;

import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;

@ApiModel("检验记录表")
public class QualityTestRecord {
    private int id;
    private String qualityTestSerialNo;
    private String receivingSerialNo;
    private Timestamp qualityTestDate;
    private String qualityTestAddr;
    private int deptId;
    private int pickerId;
    private int operUserId;
    private int affirmantId;
    private int status;
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

    public Timestamp getQualityTestDate() {
        return qualityTestDate;
    }

    public void setQualityTestDate(Timestamp qualityTestDate) {
        this.qualityTestDate = qualityTestDate;
    }

    public String getQualityTestAddr() {
        return qualityTestAddr;
    }

    public void setQualityTestAddr(String qualityTestAddr) {
        this.qualityTestAddr = qualityTestAddr;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getPickerId() {
        return pickerId;
    }

    public void setPickerId(int pickerId) {
        this.pickerId = pickerId;
    }

    public int getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(int operUserId) {
        this.operUserId = operUserId;
    }

    public int getAffirmantId() {
        return affirmantId;
    }

    public void setAffirmantId(int affirmantId) {
        this.affirmantId = affirmantId;
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
