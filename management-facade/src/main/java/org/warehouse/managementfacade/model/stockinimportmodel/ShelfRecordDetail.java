package org.warehouse.managementfacade.model.stockinimportmodel;

import io.swagger.annotations.ApiModel;

@ApiModel("上架明细表")
public class ShelfRecordDetail {

    private int id;
    private String shelfSerialNo;
    private String materialCode;
    private String batchCode;
    private int unitId;
    private int locationId;
    private int shelfQuantity;
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

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getShelfQuantity() {
        return shelfQuantity;
    }

    public void setShelfQuantity(int shelfQuantity) {
        this.shelfQuantity = shelfQuantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
