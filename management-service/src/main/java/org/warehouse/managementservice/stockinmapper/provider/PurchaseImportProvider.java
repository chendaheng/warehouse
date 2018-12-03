package org.warehouse.managementservice.stockinmapper.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author cplayer on 2018/9/24.
 * @version 1.0
 */

public class PurchaseImportProvider {

    public String updateStockInPlanByParams(Map<String, Object> params){
        // 更新入库计划 计划状态 备注
        int status = (int) params.get("planStatus");
        String planSerialNo = (String) params.get("planSerialNo");
        return new SQL(){
            {
                UPDATE("warehouseStockInPlan");
                SET("status" + "=" + status);
                if (params.containsKey("note")){
                    String note = (String) params.get("note");
                    SET("note" + "='" + note + "'");
                }
                WHERE("planSerialNo=" + "'"  + planSerialNo + "'");
            }
        }.toString();
    }

    public String searchStockInPlanResultByParams (Map<String, Object> params){
        // 根据条件搜索相应的入库计划
        String[] keyList = {"vouchSerialNo", "deliveryId", "DateStart", "DateEnd", "planSerialNo" ,"warehouseId", "entryType", "vouchType"}; //supplier供应商先当发起人onwerId
        return new SQL(){
            {
                SELECT("*");
                FROM("warehouseStockInPlan");
                for (String key : keyList){
                    if (params.containsKey(key)){
                        Object value = params.get(key);
                        if (key == "vouchSerialNo"){
                            WHERE(key + "='" + value + "'");
                        }
                        if (key == "deliveryId"){
                            WHERE("deliveryId" + "=" + value);
                        }
                        if (key ==  "DateStart"){
                            WHERE("startDate>='" + value + "'");
                        }
                        if (key == "DateEnd"){
                            WHERE("startDate<='" + value + "'");
                        }
                        if (key == "planSerialNo"){
                            WHERE(key + "='" + value + "'");
                        }
                        if (key == "warehouseId"){
                            WHERE(key + "=" + value);
                        }
                        if (key == "entryType"){
                            WHERE(key + "=" + value);
                        }
                        if (key == "vouchType"){
                            WHERE(key + "=" + value);
                        }
                    }
                }
            }
        }.toString();
    }

    public String searchStockInRecordResultByParams(Map<String, Object> params){
        // 根据条件搜索相应的入库记录
        String[] keyList = {"entrySerialNo", "DateStart", "DateEnd", "warehouseId"};
        return new SQL(){
            {
                SELECT("*");
                FROM("warehouseStockInRecord");
                for (String key : keyList){
                    if (params.containsKey(key)) {
                        Object value = params.get(key);
                        if (key == "entrySerialNo"){
                            WHERE(key + "='" + value + "'");
                        }
                        if (key == "deliveryId"){
                            WHERE("entryDate" + "=" + value);
                        }
                        if (key ==  "DateStart"){
                            WHERE("entryDate>='" + value + "'");
                        }
                        if (key == "warehouseId"){
                            WHERE(key + "=" + value);
                        }
                    }
                }

            }
        }.toString();
    }


    public String searchStockInRecordDetailResultByParams(Map<String, Object> params){
        // 根据与物料相关的搜索条件搜索相应的入库记录明细
        String materialCode = (String) params.get("materialCode");
        return new SQL(){
            {
                SELECT("*");
                FROM("warehouseStockInRecordDetail");
                WHERE("materialCode=" + "'"  + materialCode + "'");
            }
        }.toString();
    }

    public String searchStockInPlanDetailResultByParams (Map<String, Object> params){
        // 根据与物料编码搜索相应的入库计划明细
        String materialCode = (String) params.get("materialCode");
        return new SQL(){
            {
                SELECT("*");
                FROM("warehouseStockInPlanDetail");
                WHERE("materialCode=" + "'"  + materialCode + "'");
            }
        }.toString();
    }

//    public String getWareHouseEntryWithParams(Map<String, Object> params){
//        String[] keyList = {"vouchSerialNo", "DateStart", "DateEnd" };
//        return new SQL(){
//            {
//                SELECT("*");
//                FROM("warehouseEntry");
//                for (String key : keyList){
//                    if (params.containsKey(key)){
//                        Object value = params.get(key);
//                        if (key == "vouchSerialNo"){
//                            WHERE(key + "='" + value + "'");
//                        }
//                        else if (key == "DateStart"){
//                            WHERE("entryDate>='" + value + "'");
//                        }
//                        else if (key == "DateEnd"){
//                            WHERE("entryDate<='" + value + "'");
//                        }
//                    }
//                }
//            }
//        }.toString();
//    }

    public String updateWarehouseStockInPlanDetailByParams(Map<String, Object> params){
        // 更新入库计划明细里的已到货数量 和备注
        String planSerialNo = (String) params.get("planSerialNo");
        String materialCode = (String) params.get("materialCode");
        int arrivalQuantity = (int) params.get("arrivalQuantity");
        int receivingQuantity = (int) params.get("receivingQuantity");
        int arrivalQuantityNew = arrivalQuantity + receivingQuantity;
        int planQuantity = (int) params.get("planQuantity");
        int status;
        if (arrivalQuantityNew == planQuantity){
            status = 2; //全部到货
        }
        else if (arrivalQuantityNew > 0 && arrivalQuantityNew < planQuantity){
            status = 1; //部分到货
        }
        else if (arrivalQuantityNew > planQuantity){
            status = 3; //超量到货
        }
        else {
            status = 0; //未到货
        }
        return new SQL(){
            {
                UPDATE("warehouseStockInPlanDetail");
                SET("arrivalQuantity" + "=" + arrivalQuantityNew); //已到货数量
                SET("status" + "=" + status); // 物料状态
                if (status == 3){
                    int excessQuantity = arrivalQuantityNew - planQuantity;//超过计划所收的数量
                    String str = "已到货数量超过计划数" + excessQuantity;
                    SET("note" + "='" + str + "'"); // 备注
                }
                WHERE("planSerialNo=" + "'"  + planSerialNo + "'");
                WHERE("materialCode=" + "'"  + materialCode + "'");
            }
        }.toString();

    }

    public String addReceivingRecordByParams(String receivingSerialNo, String planSerialNo, int entryType, Map<String, Object> params){
        // 增加收货记录
        String[] StrList = {"vouchSerialNo", "receivingDate", "note"};
        String[] IntList = {"vouchType", "deliveryId", "deliveryAddrId", "warehouseId", "receivingAddrId", "operUserId", "attachmentId", "receivingStatus"};
        return new SQL(){
            {
                INSERT_INTO("receivingRecord");
                VALUES("receivingSerialNo", "'" + receivingSerialNo + "'");
                VALUES("planSerialNo", "'" + planSerialNo + "'");
                VALUES("entryType", Integer.toString(entryType));
                for (String key : StrList){
                    if (params.containsKey(key)){
                        String column = key;
                        String value = params.get(key).toString();
                        VALUES(column, "'" + value + "'");
                    }
                }
                for (String key : IntList){
                    if (params.containsKey(key)){
                        String column = key;
                        String value = params.get(key).toString();
                        VALUES(column, value);
                    }
                }
            }
        }.toString();
    }

    public String updateReceivingRecordByParams(Map<String, Object> params){
        // 更新收货记录
        String receivingSerialNo = (String) params.get("receivingSerialNo");
        String planSerialNo = (String) params.get("planSerialNo");
        int receivingStatus = (int) params.get("receivingStatus");
        return new SQL(){
            {
                UPDATE("receivingRecord");
                SET("receivingStatus" + "=" + receivingStatus);
                WHERE("planSerialNo=" + "'"  + planSerialNo + "'");
                WHERE("receivingSerialNo=" + "'"  + receivingSerialNo + "'");

            }
        }.toString();
    }

    public String addReceivingRecordDetailByParams (Map<String, Object> params){
        // 增加收货记录明细
        String[] StrList = {"receivingSerialNo", "planSerialNo", "materialCode", "note"};
        String[] IntList = {"unitId", "receivingQuantity"};
        return new SQL(){
            {
                INSERT_INTO("receivingRecordDetail");
                for (String key : StrList){
                    if (params.containsKey(key)){
                        String column = key;
                        String value = params.get(key).toString();
                        VALUES(column, "'" + value + "'");
                    }
                }
                for (String key : IntList){
                    if (params.containsKey(key)){
                        String column = key;
                        String value = params.get(key).toString();
                        VALUES(column, value);
                    }
                }
            }
        }.toString();
    }

    public String addQualityTestRecordByParams(String qualityTestSerialNo, String receivingSerialNo, Map<String, Object> params){
        // 新增检验记录
        String[] StrList = {"qualityTestDate", "qualityTestAddr", "note"};
        String[] IntList = {"deptId", "pickerId", "operUserId", "affirmantId", "status"};
        return new SQL() {
            {
                INSERT_INTO("qualityTestRecord");
                VALUES("qualityTestSerialNo", "'" + qualityTestSerialNo + "'");
                VALUES("receivingSerialNo", "'" + receivingSerialNo + "'");
                for (String key : StrList){
                    if (params.containsKey(key)){
                        String column = key;
                        String value = params.get(key).toString();
                        VALUES(column, "'" + value + "'");
                    }
                }
                for (String key : IntList){
                    if (params.containsKey(key)){
                        String column = key;
                        String value = params.get(key).toString();
                        VALUES(column, value);
                    }
                }
            }
        }.toString();
    }

    public String addQualityTestRecordDetailByParams(Map<String, Object> params){
        // 新增检验记录明细
        String[] StrList = {"qualityTestSerialNo", "receivingSerialNo", "materialCode", "qualityTestMethod", "qualityTestStandard", "result"};
        String[] IntList = {"unitId", "qualityTestQuantity", "entryQuantity", "returnQuantity"};
        return new SQL() {
            {
                INSERT_INTO("qualityTestRecordDetail");
                for (String key : StrList){
                    if(params.containsKey(key)){
                        String column = key;
                        String value = params.get(key).toString();
                        VALUES(column, "'" + value + "'");
                    }

                }
                for (String key : IntList){
                    if(params.containsKey(key)){
                        String column = key;
                        String value = params.get(key).toString();
                        VALUES(column, value);
                    }
                }
                if (params.containsKey("reason")) {
                    String value = params.get("reason").toString();
                    VALUES("reason","'" + value + "'");
                }
                if (params.containsKey("note")){
                    String value = params.get("reason").toString();
                    VALUES("reason","'" + value + "'");
                }
            }
        }.toString();
    }

    public String deleteQualityTestRecordDetailByParams(Map<String, Object> params){
        // 删除检验记录明细
        String qualityTestSerialNo = (String) params.get("qualityTestSerialNo");
        String materialCode = (String) params.get("materialCode");
        return new SQL(){
            {
                DELETE_FROM("qualityTestRecordDetail");
                WHERE("qualityTestSerialNo = " + qualityTestSerialNo);
                WHERE("materialCode = " + materialCode);
            }
        }.toString();
    }

    public String updateQualityTestRecordByParams(Map<String, Object> params){
        // 更新检验记录
        String qualityTestSerialNo = (String) params.get("qualityTestSerialNo");
        String receivingSerialNo = (String) params.get("receivingSerialNo");
        String[] StrList = {"qualityTestDate", "qualityTestAddr", "note"};
        String[] IntList = {"deptId", "pickerId", "operUserId", "affirmantId", "status"};
        return new SQL(){
            {
                UPDATE("qualityTestRecord");
                for (String key : StrList){
                    if (params.containsKey(key)){
                        Object value = params.get(key);
                        SET(key + "='" + value + "'");
                    }
                }
                for (String key : IntList){
                    if (params.containsKey(key)){
                        Object value = params.get(key);
                        SET(key + "=" + value);
                    }
                }
                WHERE("qualityTestSerialNo=" + "'" + qualityTestSerialNo + "'");
                WHERE("receivingSerialNo=" + "'" + receivingSerialNo + "'");
            }
        }.toString();
    }

    public String getQualityTestRecordDetailByParams(Map<String, Object> params){
        // 根据参数查询相应的检验记录明细 （用于更新）
        String materialCode = (String) params.get("materialCode");
        return new SQL(){
            {
                SELECT("*");
                FROM("qualityTestRecordDetail");
                WHERE("materialCode" + "='" + materialCode + "'");
                if(params.containsKey("qualityTestSerialNo")){
                    String qualityTestSerialNo = (String) params.get("qualityTestSerialNo");
                    WHERE("qualityTestSerialNo" + "='" + qualityTestSerialNo + "'");
                }
                if (params.containsKey("receivingSerialNo")){
                    String receivingSerialNo = (String) params.get("receivingSerialNo");
                    WHERE("receivingSerialNo" + "='" + receivingSerialNo + "'");
                }
            }
        }.toString();
    }

    public String updateQualityTestRecordDetailByParams(Map<String, Object> params){
        // 更新检验记录明细
        String materialCode = (String) params.get("materialCode");
        String[] StrList = {"qualityTestMethod", "qualityTestStandard", "reason", "result", "note"};
        String[] IntList = {"unitId", "qualityTestQuantity", "entryQuantity", "returnQuantity"};
        return new SQL(){
            {
                UPDATE("qualityTestRecordDetail");
                for (String key : StrList){
                    if (params.containsKey(key)){
                        Object value = params.get(key);
                        SET(key + "='" + value + "'");
                    }
                }
                for (String key : IntList){
                    if (params.containsKey(key)){
                        Object value = params.get(key);
                        SET(key + "=" + value);
                    }
                }
                WHERE("materialCode=" + "'" + materialCode + "'");
                if(params.containsKey("qualityTestSerialNo")){
                    String qualityTestSerialNo = (String) params.get("qualityTestSerialNo");
                    WHERE("qualityTestSerialNo" + "='" + qualityTestSerialNo + "'");
                }
                if (params.containsKey("receivingSerialNo")){
                    String receivingSerialNo = (String) params.get("receivingSerialNo");
                    WHERE("receivingSerialNo" + "='" + receivingSerialNo + "'");
                }
            }
        }.toString();
    }

    public String addWarehouseStockInRecordByParams(Map<String, Object> params){
        // 新增入库记录
        String[] StrList = {"entrySerialNo", "vouchSerialNo", "entryDate", "note"};
        String[] IntList = {"entryType", "vouchType", "warehouseId", "deptId", "operUserId", "deliveryId", "result"};
        return new SQL(){
            {
                INSERT_INTO("warehouseStockInRecord");
                for (String key : StrList){
                    if (params.containsKey(key)){
                        String column = key;
                        String value = params.get(key).toString();
                        VALUES(column, "'" + value + "'");
                    }
                }
                for (String key : IntList){
                    if (params.containsKey(key)){
                        String column = key;
                        String value = params.get(key).toString();
                        VALUES(column, value);
                    }
                }
            }
        }.toString();
    }

    public String addWarehouseStockInRecordDetailByParams(Map<String, Object> params){
        // 新增入库记录明细
        String[] StrList = {"entrySerialNo", "materialCode", "batchCode", "note"};
        String[] IntList = {"unitId", "entryQuantity", "price", "taxPrice"};
        return new SQL(){
            {
                INSERT_INTO("warehouseStockInRecordDetail");
                for (String key : StrList){
                    if (params.containsKey(key)){
                        String column = key;
                        String value = params.get(key).toString();
                        VALUES(column, "'" + value + "'");
                    }
                }
                for (String key : IntList){
                    if (params.containsKey(key)){
                        String column = key;
                        String value = params.get(key).toString();
                        VALUES(column, value);
                    }
                }
            }
        }.toString();
    }


    public String searchQualityTestRecordResultByParams(Map<String, Object> params){
        // 根据条件搜索相应的检验记录
        String[] keyList = {"operUserId", "DateStart", "DateEnd", "qualityTestSerialNo"};
        return new SQL(){
            {
                SELECT("*");
                FROM("qualityTestRecord");
                for (String key : keyList){
                    if (params.containsKey(key)){
                        Object value = params.get(key);
                        if (key == "operUserId"){
                            WHERE(key + "=" + value);
                        }
                        if (key ==  "DateStart"){
                            WHERE("qualityTestDate>='" + value + "'");
                        }
                        if (key == "DateEnd"){
                            WHERE("qualityTestDate<='" + value + "'");
                        }
                        if (key == "qualityTestSerialNo"){
                            WHERE(key + "='" + value + "'");
                        }

                    }
                }
            }
        }.toString();
    }

    public String searchQualityTestRecordDetailResultByParams (Map<String, Object> params){
        // 根据与物料相关的搜索条件搜索相应的检验记录明细
        String materialCode = (String) params.get("materialCode");
        return new SQL(){
            {
                SELECT("*");
                FROM("qualityTestRecordDetail");
                WHERE("materialCode=" + "'"  + materialCode + "'");
            }
        }.toString();
    }

    public String updateWarehouseStockInRecordByParams(Map<String, Object> params){
        // 更新入库记录
        String entrySerialNo = (String) params.get("entrySerialNo");
        String entryType = (String) params.get("entryType");
        int result = (int) params.get("result");
        String[] StrList = {"vouchSerialNo", "entryDate", "note"};
        String[] IntList = {"vouchType", "warehouseId", "deptId", "operUserId", "deliveryId"};
        return new SQL(){
            {
                UPDATE("warehouseStockInRecord");
                SET("result" + "=" + result);
                for (String key : StrList){
                    if (params.containsKey(key)){
                        Object value = params.get(key);
                        SET(key + "='" + value + "'");
                    }
                }
                for (String key : IntList){
                    if (params.containsKey(key)){
                        Object value = params.get(key);
                        SET(key + "=" + value);
                    }
                }
                WHERE("entrySerialNo=" + "'" + entrySerialNo + "'");
                WHERE("entryType=" + "'" + entryType + "'");
            }
        }.toString();
    }

    public String addShelfRecord(Map<String, Object> params){
        // 新增上架记录
        String[] StrList = {"entrySerialNo", "shelfDate", "result", "note"};
        return new SQL(){
            {
                INSERT_INTO("shelfRecord");
                for (String key : StrList){
                    if (params.containsKey(key)){
                        String column = key;
                        String value = params.get(key).toString();
                        VALUES(column, "'" + value + "'");
                    }
                }
                VALUES("operUserId", params.get("operUserId").toString());

            }
        }.toString();
    }

    public String addShelfRecordDetailByParams(Map<String, Object> params){
        // 新增上架明细
        String[] StrList = {"shelfSerialNo", "materialCode", "batchCode", "note"};
        String[] IntList = {"unitId", "locationId", "shelfQuantity"};
        return new SQL(){
            {
                INSERT_INTO("shelfRecordDetail");
                for (String key : StrList){
                    if (params.containsKey(key)){
                        String column = key;
                        String value = params.get(key).toString();
                        VALUES(column, "'" + value + "'");
                    }
                }
                for (String key : IntList){
                    if (params.containsKey(key)){
                        String column = key;
                        String value = params.get(key).toString();
                        VALUES(column, value);
                    }
                }
            }
        }.toString();
    }

    public String deleteShelfRecordDetailByParams(Map<String, Object> params){
        // 删除上架明细
        String shelfSerialNo = (String) params.get("shelfSerialNo");
        String materialCode = (String) params.get("materialCode");
        return new SQL(){
            {
                DELETE_FROM("shelfRecordDetail");
                WHERE("shelfSerialNo = " + shelfSerialNo);
                WHERE("materialCode = " + materialCode);
            }
        }.toString();
    }

}
