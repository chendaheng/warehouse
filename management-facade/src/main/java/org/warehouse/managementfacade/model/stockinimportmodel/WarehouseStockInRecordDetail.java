package org.warehouse.managementfacade.model.stockinimportmodel;

import io.swagger.annotations.ApiModel;

@ApiModel("入库记录明细表")
public class WarehouseStockInRecordDetail {

    private int id;
    private String entrySerialNo;
    private String materialCode;
    private String batchCode;
    private int unitId;
    private int entryQuantity;
    private int price;
    private int taxPrice;
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

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getEntryQuantity() {
        return entryQuantity;
    }

    public void setEntryQuantity(int entryQuantity) {
        this.entryQuantity = entryQuantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(int taxPrice) {
        this.taxPrice = taxPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
