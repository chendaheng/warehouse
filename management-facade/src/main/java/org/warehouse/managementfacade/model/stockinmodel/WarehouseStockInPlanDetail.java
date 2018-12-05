package org.warehouse.managementfacade.model.stockinmodel;

import io.swagger.annotations.ApiModel;

@ApiModel("入库计划明细表")
public class WarehouseStockInPlanDetail {

    private int id;
    private String planSerialNo;
    private String materialCode;
    private int unitId;
    private int planQuantity;
    private int arrivalQuantity;
    private int price;
    private int taxPrice;
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

    public int getPlanQuantity() {
        return planQuantity;
    }

    public void setPlanQuantity(int planQuantity) {
        this.planQuantity = planQuantity;
    }

    public int getArrivalQuantity() {
        return arrivalQuantity;
    }

    public void setArrivalQuantity(int arrivalQuantity) {
        this.arrivalQuantity = arrivalQuantity;
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
