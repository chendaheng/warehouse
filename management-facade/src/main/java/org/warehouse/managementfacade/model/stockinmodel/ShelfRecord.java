package org.warehouse.managementfacade.model.stockinmodel;

import io.swagger.annotations.ApiModel;

import java.sql.Timestamp;

@ApiModel("上架记录表")
public class ShelfRecord {

    private int id;
    private String shelfSerialNo;
    private String entrySerialNo;
    private int operUserId;
    private Timestamp shelfDate;
    private String result;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShelfSerialNo() {
        return shelfSerialNo;
    }

    public void setShelfSerialNo(String shelfSerialNo) {
        this.shelfSerialNo = shelfSerialNo;
    }

    public String getEntrySerialNo() {
        return entrySerialNo;
    }

    public void setEntrySerialNo(String entrySerialNo) {
        this.entrySerialNo = entrySerialNo;
    }

    public int getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(int operUserId) {
        this.operUserId = operUserId;
    }

    public Timestamp getShelfDate() {
        return shelfDate;
    }

    public void setShelfDate(Timestamp shelfDate) {
        this.shelfDate = shelfDate;
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
