package org.warehouse.managementweb.web.stockinmodel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.warehouse.managementfacade.model.stockinmodel.*;
import org.warehouse.managementservice.stockincrossModule.CrossModuleClient;
import org.warehouse.managementservice.service.stockinimpl.*;

import java.util.*;
import java.text.SimpleDateFormat;

/**
 * @author cplayer on 2018/9/18.
 * @version 1.0
 * 入库部分的Controller
 */

@RestController
@RequestMapping("/wareHouse/stockIn")
@Api(value = "采购订单入库", description = "采购订单入库")
public class PurchaseImportController {
    @Autowired
    private PurchaseImportServiceImpl purchaseImportService;

    private final static Logger logger = LoggerFactory.getLogger("warehouseLogger");

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/getAllStockInPlanByEntryType")
    @ApiOperation(value = "获取该入库类型下所有入库计划", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List <WarehouseStockInPlan> getAllStockInPlanByEntryType (@RequestBody Map<String, Object> params) {
        if (params.containsKey("entryType")){
            int entryType = (int)params.get("entryType");
            return purchaseImportService.getAllStockInPlanByEntryType(entryType);
        }
        else {
            logger.debug("入库类型不存在");
            return null;
        }

    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/getStockInPlanByPage")
    @ApiOperation(value = "根据页码获取入库计划", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List <WarehouseStockInPlan> getStockInPlanByPage (@RequestBody Map<String, Object> params) {
        if (params.containsKey("page") && params.containsKey("number") && params.containsKey("entryType")){
            int page = (int)params.get("page");
            int number = (int)params.get("number");
            int entryType = (int)params.get("entryType");
            if (page < 1){
                logger.debug("页数不能小于1");
                return null;
            }
            else {
                return purchaseImportService.getStockInPlanByPage(page, number, entryType);
            }
        }
        else {
            logger.debug("传入参数有误");
            return null;
        }

    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/getStockInPlanDetailByPlanSerialNo") // 一对多 用于前端展示 (缺少规格)
    @ApiOperation(value = "根据入库计划流水号获取相应的入库计划明细", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Object> getStockInPlanDetailByPlanSerialNo(@RequestBody Map<String, Object> params) {
        List <Object> showDataList = new ArrayList<>();
        if (params.containsKey("planSerialNo")){
            String planSerialNo = (String) params.get("planSerialNo");
            List <WarehouseStockInPlanDetail> stockInPlanDetailResult = purchaseImportService.getStockInPlanDetailByPlanSerialNo(planSerialNo);
            for (WarehouseStockInPlanDetail stockInPlanDetail : stockInPlanDetailResult){
                HashMap < String, Object> planDetailShowData = new HashMap<String, Object>();
                String materialCode = stockInPlanDetail.getMaterialCode();
                int unitId = stockInPlanDetail.getUnitId();
                int planQuantity = stockInPlanDetail.getPlanQuantity();
                int price = stockInPlanDetail.getPrice();
                int taxPrice = stockInPlanDetail.getTaxPrice();
                String note = stockInPlanDetail.getNote();
                planDetailShowData.put("materialCode", materialCode);
                planDetailShowData.put("unitId", unitId);
                planDetailShowData.put("planQuantity", planQuantity);
                planDetailShowData.put("price", price);
                planDetailShowData.put("taxPrice", taxPrice);
                planDetailShowData.put("note", note);
                // 跨模块获取规格
                CrossModuleClient crossModuleClient = new CrossModuleClient();
                String url_getBaseInfo = "http://localhost:8180/materialmanagement/getBaseInfo";
                String spuCode = crossModuleClient.getSpuCodeByMaterialCode(url_getBaseInfo, materialCode); // 根据物料编码获取spu编码
                int materialCatId = crossModuleClient.getMaterialCatIdBySpucode(url_getBaseInfo ,spuCode);
                String url_getSpecification = "http://localhost:8180/materialmanagement/getMaterialBaseByCatIdAndType";
                String specification = crossModuleClient.getSpecificationByMaterialCatId(url_getSpecification,materialCatId);// 获取规格
                planDetailShowData.put("specification", specification);
                showDataList.add(planDetailShowData);
            }
            return showDataList;
        }
        else {
            logger.debug("参数不存在入库计划流水号");
            return null;
        }

    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/getStockInPlanByPlanSerialNo")
    @ApiOperation(value = "根据入库计划流水号获取相应的入库计划", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<WarehouseStockInPlan> getStockInPlanByPlanSerialNo(@RequestParam String planSerialNo) {
        return purchaseImportService.getStockInPlanByPlanSerialNo(planSerialNo);
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/getStockInPlanByVouchSerialNo")
    @ApiOperation(value = "根据关联单据获取相应的入库计划", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<WarehouseStockInPlan> getStockInPlanByVouchSerialNo(@RequestParam String vouchSerialNo){
        return purchaseImportService.getStockInPlanByVouchSerialNo(vouchSerialNo);
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/getStockInPlanDetailByVouchSerialNo")
    @ApiOperation(value = "根据关联单据获取相应的入库计划明细", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List <WarehouseStockInPlanDetail> getStockInPlanDetailByVouchSerialNo(@RequestParam String vouchSerialNo){
        // 先去找到入库计划流水号
        List<WarehouseStockInPlan> stockInPlanResult = purchaseImportService.getStockInPlanByVouchSerialNo(vouchSerialNo);
        if (stockInPlanResult.size() > 1){ //暂时先考虑1-1对应的情况
            logger.debug("该关联单据对应多条入库计划");
            return null;
        }
        else {
            WarehouseStockInPlan stockInPlan = stockInPlanResult.get(0);
            String planSerialNo = stockInPlan.getPlanSerialNo();
            return purchaseImportService.getStockInPlanDetailByPlanSerialNo(planSerialNo);
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/updateStockInPlanByParams")
    @ApiOperation(value = "更新入库计划", produces = MediaType.APPLICATION_JSON_UTF8_VALUE) // 主要为了更新到货时间 计划状态 备注
    public int updateStockInPlanByParams(@RequestBody Map<String, Object> params){
        if (params.containsKey("planSerialNo") && params.containsKey("operation")){ // 操作 0：收货
            int operation = (int) params.get("operation");
            String planSerialNo = (String) params.get("planSerialNo");
            int count = 0;
            int planStatus = 0;
            if (operation == 0){
                List <WarehouseStockInPlanDetail> StockInPlanDetailResult = purchaseImportService.getStockInPlanDetailByPlanSerialNo(planSerialNo);
                int size = StockInPlanDetailResult.size();
                for (WarehouseStockInPlanDetail stockInPlanDetail : StockInPlanDetailResult){
                    if (stockInPlanDetail.getStatus() == 2 || stockInPlanDetail.getStatus() == 3){
                        count += 1;
                    }
                }
                if (count == size){
                    planStatus = 1;
                    params.put("planStatus", planStatus);
                    return purchaseImportService.updateStockInPlan(params);

                }
                else if (count < size){
                    planStatus = 0;
                    params.put("planStatus", planStatus);
                    return purchaseImportService.updateStockInPlan(params);
                }
                else {
                    return -1;
                }
            }
            else {
                return -1;
            }

        }
        else {
            logger.debug("数据不符合规范,该条数据非空字段缺失,无法创建收货记录明细");
            return -1;
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/updateStockInPlanDetailByParams")
    @ApiOperation(value = "更新入库计划明细", produces = MediaType.APPLICATION_JSON_UTF8_VALUE) // 更新已到货数量 物料状态 备注
    public int updateStockInPlanDetailByParams(@RequestBody Map<String, Object> params){ // 从收货记录明细中传来 更新计划明细中的已到货数量
        if (params.containsKey("data")){
            List <Map<String, Object>> receivingRecordDetailData = (List<Map<String, Object>>) params.get("data");
            int size = receivingRecordDetailData.size();
            int count = 0;
            for (Map<String, Object> data :receivingRecordDetailData){
                if (data.containsKey("planSerialNo") && data.containsKey("materialCode") && data.containsKey("receivingQuantity")){
                    String planSerialNo = (String) data.get("planSerialNo");
                    String materialCode = (String) data.get("materialCode");
                    List<WarehouseStockInPlanDetail> StockInPlanDetailResult = purchaseImportService.
                            getStockInPlanDetailByPlanSerialNoAndMaterialCode(planSerialNo,materialCode);
                    if (StockInPlanDetailResult.size() == 1){
                        WarehouseStockInPlanDetail StockInPlanDetail = StockInPlanDetailResult.get(0);
                        int arrivalQuantity = StockInPlanDetail.getArrivalQuantity(); // 已到货数量
                        int planQuantity = StockInPlanDetail.getPlanQuantity(); // 计划到货数量
                        data.put("arrivalQuantity", arrivalQuantity);
                        data.put("planQuantity", planQuantity);
                        count += purchaseImportService.updateStockInPlanDetail(data);
                    }
                    else {
                        return -1;
                    }
                }
                else {
                    logger.debug("数据不符合规范,该条数据非空字段缺失,无法创建收货记录明细");
                    return -1;
                }
            }
            if (count == size){
                return count;
            }
            else {
                logger.debug("多条数据未全部更新,请检查数据库");
                return -1;
            }
        }
        else if (params.containsKey("planSerialNo") && params.containsKey("materialCode") && params.containsKey("receivingQuantity")){
            String planSerialNo = (String) params.get("planSerialNo");
            String materialCode = (String) params.get("materialCode");
            List<WarehouseStockInPlanDetail> StockInPlanDetailResult = purchaseImportService.
                    getStockInPlanDetailByPlanSerialNoAndMaterialCode(planSerialNo,materialCode);
            if (StockInPlanDetailResult.size() == 1){
                WarehouseStockInPlanDetail StockInPlanDetail = StockInPlanDetailResult.get(0);
                int arrivalQuantity = StockInPlanDetail.getArrivalQuantity(); // 已到货数量
                int planQuantity = StockInPlanDetail.getPlanQuantity(); // 计划到货数量
                params.put("arrivalQuantity", arrivalQuantity);
                params.put("planQuantity", planQuantity);
                return purchaseImportService.updateStockInPlanDetail(params);
            }
            else {
                return -1;
            }
        }
        else {
            logger.debug("入库计划流水号和物料编码缺失,无法确定要更新的入库计划明细表中的数据");
            return -1;
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/searchStockInPlanByParams")
    @ApiOperation(value = "根据条件搜索相应的入库计划", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List <WarehouseStockInPlan> searchStockInPlanByParams (@RequestBody Map<String, Object> params) {
        CrossModuleClient crossModuleClient = new CrossModuleClient();
        List <WarehouseStockInPlan> stockInPlanSearchResultList = new ArrayList<>();
        if (params.size()>0){
            if (params.containsKey("DateStart") && params.containsKey("DateEnd")){
                String StartDate = params.get("DateStart").toString();
                String EndDate = params.get("DateEnd").toString();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date DateStart = format.parse(StartDate);
                    Date DateEnd = format.parse(EndDate);
                    if (DateStart.getTime() > DateEnd.getTime()){
                        logger.debug("截止日期大于起始日期,请重新输入");
                        return null;
                    }
                }
                catch (Exception ParseException){
                    ParseException.printStackTrace();
                    return null;
                }
            }
            if (params.containsKey("spuName") || params.containsKey("materialCode")){
                if (params.containsKey("materialCode")){ // 既包含spu名称 又包含物料编码 按照物料编码来 但先判断该spu名称 是不是有对应的物料编码
                    if (params.containsKey("spuName")){
                        String materialCode = (String) params.get("materialCode");
                        String url = "http://localhost:8180/materialmanagement/getBaseInfo"; //获取spuCode
                        String spuName = (String) params.get("spuName");
                        String url2 = "http://localhost:8180/materialmanagement/getMaterialInfo";
                        String spuCode = crossModuleClient.getSpuCodeBySpuName(url,spuName);
                        List <Object> materialCodeList = crossModuleClient.getMaterialCodeBySpuCode(url2, spuCode);
                        int count = 0;
                        for (int i = 0 ; i < materialCodeList.size() ; i++){
                            String materialCodeBySpuName = (String) materialCodeList.get(i);
                            if (materialCode.equals(materialCodeBySpuName) == true){
                                count += 1;
                            }
                        }
                        if (count < 1){
                            logger.debug("所输入的spuName和materialCode无法对应");
                            return null;
                        }
                    }
                    List <String> planSerialNoList = new ArrayList<String>();
                    List <WarehouseStockInPlanDetail> stockInPlanDetailResult = purchaseImportService.searchStockInPlanDetailByParams(params);
                    for (WarehouseStockInPlanDetail stockInPlanDetail : stockInPlanDetailResult){
                        String planSerialNo = stockInPlanDetail.getPlanSerialNo();
                        planSerialNoList.add(planSerialNo);
                    }
                    // 对planSerialNoList里的计划流水号进行去重
                    HashSet hashSet = new HashSet(planSerialNoList);
                    planSerialNoList.clear();
                    planSerialNoList.addAll(hashSet);
                    for (int i = 0 ; i < planSerialNoList.size() ; i++){
                        String planSerialNo = (String) planSerialNoList.get(i);
                        if (params.containsKey("planSerialNo")){
                            params.remove("planSerialNo");
                        }
                        params.put("planSerialNo", planSerialNo);
                        List <WarehouseStockInPlan> stockInPlanSearchResult = purchaseImportService.searchStockInPlanByParams(params);
                        if (stockInPlanSearchResult.size()!=0){ //如果有符合条件的结果 1条
                            stockInPlanSearchResultList.add(stockInPlanSearchResult.get(0));
                        }
                    }
                    return stockInPlanSearchResultList;
                }
                else if (params.containsKey("spuName")){  //只包含spu名称
                    String url = "http://localhost:8180/materialmanagement/getBaseInfo"; //获取spuCode
                    String spuName = (String) params.get("spuName");
                    String spuCode = crossModuleClient.getSpuCodeBySpuName(url,spuName);
                    String url2 = "http://localhost:8180/materialmanagement/getMaterialInfo";
                    List <Object> materialCodeList = crossModuleClient.getMaterialCodeBySpuCode(url2, spuCode);
                    params.remove("spuName");
                    List <String> planSerialNoList = new ArrayList<String>();
                    for (int i = 0 ; i < materialCodeList.size() ; i++){
                        String materialCode = (String)materialCodeList.get(i);
                        if (params.containsKey("materialCode")){
                            params.remove("materialCode");
                        }
                        params.put("materialCode", materialCode);
                        List <WarehouseStockInPlanDetail> stockInPlanDetailResult = purchaseImportService.searchStockInPlanDetailByParams(params);
                        for (WarehouseStockInPlanDetail stockInPlanDetail : stockInPlanDetailResult){
                            String planSerialNo = stockInPlanDetail.getPlanSerialNo();
                            planSerialNoList.add(planSerialNo);
                        }
                    }
                    HashSet hashSet = new HashSet(planSerialNoList);
                    planSerialNoList.clear();
                    planSerialNoList.addAll(hashSet);

                    for (int i = 0 ; i < planSerialNoList.size() ; i++){
                        String planSerialNo = (String) planSerialNoList.get(i);
                        if (params.containsKey("planSerialNo")){
                            params.remove("planSerialNo");
                        }
                        params.put("planSerialNo", planSerialNo);
                        List <WarehouseStockInPlan> stockInPlanSearchResult = purchaseImportService.searchStockInPlanByParams(params);
                        if (stockInPlanSearchResult.size()!=0){ // 搜索条件包含物料信息 那么每一个入库计划流水号对应的入库计划唯一确定
                            stockInPlanSearchResultList.add(stockInPlanSearchResult.get(0));
                        }
                    }
                    return stockInPlanSearchResultList;
                }
                else {
                    List <WarehouseStockInPlan> stockInPlanSearchResult = purchaseImportService.searchStockInPlanByParams(params);
                    for (int i = 0; i < stockInPlanSearchResult.size(); i++){
                        stockInPlanSearchResultList.add(stockInPlanSearchResult.get(i));
                    }
                    return stockInPlanSearchResultList;
                }
            }
            else {
                List <WarehouseStockInPlan> stockInPlanSearchResult = purchaseImportService.searchStockInPlanByParams(params);
                for (int i = 0; i < stockInPlanSearchResult.size(); i++){
                    stockInPlanSearchResultList.add(stockInPlanSearchResult.get(i));
                }
                return stockInPlanSearchResultList;
            }
        }
        else {
            logger.debug("params不存在");
            return null;
        }
    }


    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/getReceivingRecordShowDataByPlanSerialNo") //返回的是StockInPlan 从中获取需要的数据
    @ApiOperation(value = "根据入库计划流水号获取入库计划信息用于收货画面", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<WarehouseStockInPlan> getReceivingRecordShowDataByPlanSerialNo (@RequestParam String planSerialNo) {
        return purchaseImportService.getReceivingRecordShowDataByPlanSerialNo(planSerialNo);
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/getReceivingRecordDetailShowDataByParams") //返回的是StockInPlan 从中获取需要的数据
    @ApiOperation(value = "获取入库计划明细信息用于收货画面", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Object> getReceivingRecordDetailShowDataByParams(@RequestParam String planSerialNo){
        List <Object> showDataList = new ArrayList<>();
        List <WarehouseStockInPlanDetail> stockInPlanDetailResult = purchaseImportService.getStockInPlanDetailByPlanSerialNo(planSerialNo);
        for (WarehouseStockInPlanDetail stockInPlanDetail : stockInPlanDetailResult) {
            HashMap < String, Object> receivingRecordDetailShowData = new HashMap<String, Object>();
            String materialCode = stockInPlanDetail.getMaterialCode();// 物料编码
            int unitId = stockInPlanDetail.getUnitId();// 计量单位 还是int
            int planQuantity = stockInPlanDetail.getPlanQuantity(); //计划到货数量
            int price = stockInPlanDetail.getPrice();// 物料单价
            int taxPrice = stockInPlanDetail.getTaxPrice(); // 含税单价
            receivingRecordDetailShowData.put("materialCode", materialCode);
            receivingRecordDetailShowData.put("unitId", unitId);
            receivingRecordDetailShowData.put("planQuantity", planQuantity);
            receivingRecordDetailShowData.put("price", price);
            receivingRecordDetailShowData.put("taxPrice", taxPrice);
            CrossModuleClient crossModuleClient = new CrossModuleClient();
            // 跨模块获取规格
            String url_getBaseInfo = "http://localhost:8180/materialmanagement/getBaseInfo";
            String spuCode = crossModuleClient.getSpuCodeByMaterialCode(url_getBaseInfo, materialCode); // 根据物料编码获取spu编码
            int materialCatId = crossModuleClient.getMaterialCatIdBySpucode(url_getBaseInfo ,spuCode);
            String url_getSpecification = "http://localhost:8180/materialmanagement/getMaterialBaseByCatIdAndType";
            String specification = crossModuleClient.getSpecificationByMaterialCatId(url_getSpecification,materialCatId);// 获取规格
            receivingRecordDetailShowData.put("specification",specification);
            showDataList.add(receivingRecordDetailShowData);
        }
        return showDataList;
    }


    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/addReceivingRecord")
    @ApiOperation(value = "增加收货记录", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int addReceivingRecord(@RequestBody Map<String, Object> params) { // 前端发送的数据缺少发货单位id
        if (params.containsKey("receivingSerialNo") && params.containsKey("planSerialNo") && params.containsKey("entryType")){
            String planSerialNo = (String) params.get("planSerialNo");
            List <WarehouseStockInPlan> StockInPlanResult = purchaseImportService.getStockInPlanByPlanSerialNo(planSerialNo);
            if (StockInPlanResult.size() == 0){
                logger.debug("该入库计划流水号对应的入库计划不存在");
                return -1;
            }
            else if (StockInPlanResult.size() == 1){
                try {
                    WarehouseStockInPlan StockInPlan = (WarehouseStockInPlan)StockInPlanResult.get(0); // 增加deliveryId
                    int deliveryId = StockInPlan.getDeliveryId();
                    params.put("deliveryId", deliveryId);
                }
                catch (ClassCastException e){
                    e.printStackTrace();
                    return -1;
                }
                return purchaseImportService.addReceivingRecord(params);
            }
            else {
                return -1;
            }
        }
        else {
            logger.debug("非空字段缺失,无法创建收货记录");
            return -1;
        }

    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/getLastReceivingRecordId")
    @ApiOperation(value = "获取最后一条收货记录的id", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int getLastReceivingRecordId(){
        ReceivingRecord lastReceivingRecord = purchaseImportService.getLastReceivingRecord();
        if (lastReceivingRecord == null){
            return 0;
        }
        else {
            return lastReceivingRecord.getId();
        }

    }

//    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
//            methods = {RequestMethod.POST},
//            origins = "*")
//    @PostMapping(value = "/getLastReceivingRecordDetailId")
//    @ApiOperation(value = "获取最后一条收货记录明细的id", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public int getLastReceivingRecordDetailId(){
//        ReceivingRecordDetail lastReceivingRecordDetail = purchaseImportService.getLastReceivingRecordDetail();
//        if (lastReceivingRecordDetail == null){
//            return 0;
//        }
//        else {
//            return lastReceivingRecordDetail.getId();
//        }
//    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/updateReceivingRecord")
    @ApiOperation(value = "更新收货记录", produces = MediaType.APPLICATION_JSON_UTF8_VALUE) //params 和增加收货记录明细时的一样
    public int updateReceivingRecord(@RequestBody Map<String, Object> params) {
        if (params.containsKey("data")){
            List<Map<String, Object>> receivingRecordDetailData = (List<Map<String, Object>>) params.get("data");
            int size = receivingRecordDetailData.size();
            int count = 0;
            String planSerialNo = null;
            String receivingSerialNo = null;
            int receivingStatus = 0;
            for (Map<String, Object> data : receivingRecordDetailData){
                if (data.containsKey("receivingSerialNo") && data.containsKey("planSerialNo") && data.containsKey("materialCode")){
                    int receivingQuantity = (int) data.get("receivingQuantity");
                    planSerialNo = (String) data.get("planSerialNo");
                    receivingSerialNo = (String) data.get("receivingSerialNo");
                    String materialCode = (String) data.get("materialCode");
                    List<WarehouseStockInPlanDetail> StockInPlanDetailResult = purchaseImportService.
                            getStockInPlanDetailByPlanSerialNoAndMaterialCode(planSerialNo, materialCode);
                    if (StockInPlanDetailResult.size() != 1){
                        return -1;
                    }
                    WarehouseStockInPlanDetail stockInPlanDetail = StockInPlanDetailResult.get(0);
                    int planQuantity = stockInPlanDetail.getPlanQuantity();
                    if (receivingQuantity >= planQuantity){
                        count += 1;
                    }
                }
                else {
                    logger.debug("非空字段缺失,无法更新收货记录");
                    return -1;
                }
            }
            if (count == size){
                receivingStatus = 2; //完全到货
            }
            else if (count >= 0 && count < size){
                receivingStatus = 1;
            }
            HashMap <String,Object> newparams = new HashMap();
            newparams.put("receivingStatus", receivingStatus);
            newparams.put("planSerialNo", planSerialNo);
            newparams.put("receivingSerialNo", receivingSerialNo);
            return purchaseImportService.updateReceivingRecord(newparams);
        }

        else if (params.containsKey("receivingSerialNo") && params.containsKey("planSerialNo") && params.containsKey("materialCode")){
            String planSerialNo = (String) params.get("planSerialNo");
            String materialCode = (String) params.get("materialCode");
            int receivingQuantity = (int) params.get("receivingQuantity");
            int receivingStatus = 0;
            List<WarehouseStockInPlanDetail> StockInPlanDetailResult = purchaseImportService.
                    getStockInPlanDetailByPlanSerialNoAndMaterialCode(planSerialNo, materialCode);
            if (StockInPlanDetailResult.size() == 1) {
                WarehouseStockInPlanDetail stockInPlanDetail = StockInPlanDetailResult.get(0);
                int planQuantity = stockInPlanDetail.getPlanQuantity();
                if (receivingQuantity >= planQuantity) {
                    receivingStatus = 2;
                } else {
                    receivingStatus = 1;
                }
                params.put("receivingStatus", receivingStatus);
                return purchaseImportService.updateReceivingRecord(params);
            }
            else {
                return -1;
            }
        }
        else {
            return -1;
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/addReceivingRecordDetail")
    @ApiOperation(value = "增加收货记录明细", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int addReceivingRecordDetail(@RequestBody Map<String, Object> params){
        if(params.containsKey("data")){
            List <Map<String, Object>> receivingRecordDetailData = (List<Map<String, Object>>) params.get("data");
            int size = receivingRecordDetailData.size();
            int count = 0;
            for (Map<String, Object> data : receivingRecordDetailData ){
                if (data.containsKey("receivingSerialNo") && data.containsKey("planSerialNo") && data.containsKey("materialCode") &&
                        data.containsKey("unitId") && data.containsKey("receivingQuantity")){
                        count += purchaseImportService.addReceivingRecordDetail(data);
                }
                else {
                    logger.debug("数据不符合规范,该条数据非空字段缺失,无法创建收货记录明细");
                    return -1;
                }
            }
            if (count == size){
                return count;
            }
            else{
                logger.debug("创建出现问题");
                return -1;
            }

        }
        else if (params.containsKey("receivingSerialNo") && params.containsKey("planSerialNo") && params.containsKey("materialCode") &&
                params.containsKey("unitId") && params.containsKey("receivingQuantity")){
            return purchaseImportService.addReceivingRecordDetail(params);
        }
        else{
            logger.debug("数据不符合规范,非空字段缺失,无法创建收货记录明细");
            return -1;
        }

    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/addQualityTestRecord")
    @ApiOperation(value = "增加检验记录", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int addQualityTestRecord(@RequestBody Map<String, Object> params){
        if (params.containsKey("qualityTestSerialNo") && params.containsKey("receivingSerialNo")){
            return purchaseImportService.addQualityTestRecord(params);
        }
        else {
            logger.debug("数据不符合规范，无法创建检验记录");
            return -1;
        }

    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/getLastQualityTestRecordId")
    @ApiOperation(value = "获取最后一条检验记录的id", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int getLastQualityTestRecordId(){
        QualityTestRecord lastQualityTestRecord = purchaseImportService.getLastQualityTestRecord();
        if (lastQualityTestRecord == null){
            return 0;
        }
        else {
            return lastQualityTestRecord.getId();
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/addQualityTestRecordDetail")
    @ApiOperation(value = "增加检验记录明细", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int addQualityTestRecordDetail(@RequestBody Map<String, Object> params){
        if(params.containsKey("data")){
            List <Map<String, Object>> TestRecordDetailData = (List<Map<String, Object>>)params.get("data");
            int size = TestRecordDetailData.size();
            int count = 0;
            for (Map<String, Object> data : TestRecordDetailData){
                if(params.containsKey("qualityTestSerialNo") && params.containsKey("receivingSerialNo") && params.containsKey("materialCode")){
                    count += purchaseImportService.addQualityTestRecordDetail(data);
                }
            }
            if (count == size){
                return 1;
            }
            else{
                logger.debug("创建出现问题");
                return -1;
            }
        }
        else if(params.containsKey("qualityTestSerialNo") && params.containsKey("receivingSerialNo") && params.containsKey("materialCode")){
            return purchaseImportService.addQualityTestRecordDetail(params);
        }
        else {
            logger.debug("数据不符合规范,非空字段缺失,无法创建检验记录明细");
            return -1;
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/getAllQualityTestRecord")
    @ApiOperation(value = "获取所有的检验记录", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<QualityTestRecord> getAllQualityTestRecord(){
        return purchaseImportService.getAllQualityTestRecord();
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/getQualityTestRecordByPage")
    @ApiOperation(value = "根据页码获取检验记录", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<QualityTestRecord> getQualityTestRecordByPage(@RequestBody Map<String, Object> params){
        if (params.containsKey("page") && params.containsKey("number")){
            int page = (int)params.get("page");
            int number = (int)params.get("number");
            if (page < 1){
                logger.debug("页数不能小于1");
                return null;
            }
            else {
                return purchaseImportService.getQualityTestRecordByPage(page, number);
            }
        }
        else {
            logger.debug("传入参数有误");
            return null;
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/searchQualityTestRecordByParams")
    @ApiOperation(value = "根据条件搜索相应的检验记录", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<QualityTestRecord> searchQualityTestRecordByParams(@RequestBody Map<String, Object> params){
        CrossModuleClient crossModuleClient = new CrossModuleClient();
        List <QualityTestRecord> qualityTestRecordSearchResultList = new ArrayList<>();
        if (params.size()>0){
            if (params.containsKey("DateStart") && params.containsKey("DateEnd")){
                String StartDate = params.get("DateStart").toString();
                String EndDate = params.get("DateEnd").toString();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date DateStart = format.parse(StartDate);
                    Date DateEnd = format.parse(EndDate);
                    if (DateStart.getTime() > DateEnd.getTime()){
                        logger.debug("截止日期大于起始日期,请重新输入");
                        return null;
                    }
                }
                catch (Exception ParseException){
                    ParseException.printStackTrace();
                    return null;
                }
            }
            if (params.containsKey("spuName") || params.containsKey("materialCode")){
                if (params.containsKey("materialCode")){
                    if (params.containsKey("spuName")){
                        String materialCode = (String) params.get("materialCode");
                        String url = "http://localhost:8180/materialmanagement/getBaseInfo"; //获取spuCode
                        String spuName = (String) params.get("spuName");
                        String url2 = "http://localhost:8180/materialmanagement/getMaterialInfo";
                        String spuCode = crossModuleClient.getSpuCodeBySpuName(url,spuName);
                        List <Object> materialCodeList = crossModuleClient.getMaterialCodeBySpuCode(url2, spuCode);
                        int count = 0;
                        for (int i = 0 ; i < materialCodeList.size() ; i++){
                            String materialCodeBySpuName = (String) materialCodeList.get(i);
                            if (materialCode.equals(materialCodeBySpuName) == true){
                                count += 1;
                            }
                        }
                        if (count < 1){
                            logger.debug("所输入的spuName和materialCode无法对应");
                            return null;
                        }
                    }
                    List <String> qualityTestSerialNoList = new ArrayList<String>();
                    List <QualityTestRecordDetail> qualityTestRecordDetailResult = purchaseImportService.searchQualityTestRecordDetailByParams(params);
                    for (QualityTestRecordDetail qualityTestRecordDetail : qualityTestRecordDetailResult){
                        String qualityTestSerialNo = qualityTestRecordDetail.getQualityTestSerialNo();
                        qualityTestSerialNoList.add(qualityTestSerialNo);
                    }

                    HashSet hashSet = new HashSet(qualityTestSerialNoList);
                    qualityTestSerialNoList.clear();
                    qualityTestSerialNoList.addAll(hashSet);

                    for (int i = 0; i < qualityTestSerialNoList.size(); i++){
                        String qualityTestSerialNo = (String) qualityTestSerialNoList.get(i);
                        if (params.containsKey("qualityTestSerialNo")){
                            params.remove("qualityTestSerialNo");
                        }
                        params.put("qualityTestSerialNo", qualityTestSerialNo);
                        List <QualityTestRecord> qualityTestRecordResult = purchaseImportService.searchQualityTestRecordByParams(params);
                        if (qualityTestRecordResult.size() != 0){
                            qualityTestRecordSearchResultList.add(qualityTestRecordResult.get(0));
                        }
                    }
                    return qualityTestRecordSearchResultList;
                }
                else if (params.containsKey("spuName")){
                    String url = "http://localhost:8180/materialmanagement/getBaseInfo"; //获取spuCode
                    String spuName = (String) params.get("spuName");
                    String spuCode = crossModuleClient.getSpuCodeBySpuName(url,spuName);
                    String url2 = "http://localhost:8180/materialmanagement/getMaterialInfo";
                    List <Object> materialCodeList = crossModuleClient.getMaterialCodeBySpuCode(url2, spuCode);
                    params.remove("spuName");
                    List <String> qualityTestSerialNoList = new ArrayList<String>();
                    for (int i = 0 ; i < materialCodeList.size() ; i++){
                        String materialCode = (String)materialCodeList.get(i);
                        if (params.containsKey("materialCode")){
                            params.remove("materialCode");
                        }
                        params.put("materialCode", materialCode);
                        List <QualityTestRecordDetail> qualityTestRecordDetailResult = purchaseImportService.searchQualityTestRecordDetailByParams(params);
                        for (QualityTestRecordDetail ualityTestRecordDetail : qualityTestRecordDetailResult){
                            String qualityTestSerialNo = ualityTestRecordDetail.getQualityTestSerialNo();
                            qualityTestSerialNoList.add(qualityTestSerialNo);
                        }
                    }
                    HashSet hashSet = new HashSet(qualityTestSerialNoList);
                    qualityTestSerialNoList.clear();
                    qualityTestSerialNoList.addAll(hashSet);
                    for (int i =0 ; i < qualityTestSerialNoList.size(); i++){
                        String qualityTestSerialNo = (String) qualityTestSerialNoList.get(i);
                        if (params.containsKey("qualityTestSerialNo")){
                            params.remove("qualityTestSerialNo");
                        }
                        params.put("qualityTestSerialNo", qualityTestSerialNo);
                        List<QualityTestRecord> qualityTestRecordResult = purchaseImportService.searchQualityTestRecordByParams(params);
                        if (qualityTestRecordResult.size() != 0){
                            qualityTestRecordSearchResultList.add(qualityTestRecordResult.get(0));
                        }
                    }
                    return qualityTestRecordSearchResultList;
                }
                else {
                    List<QualityTestRecord> qualityTestRecordResult = purchaseImportService.searchQualityTestRecordByParams(params);
                    if (qualityTestRecordResult.size() != 0){
                        qualityTestRecordSearchResultList.add(qualityTestRecordResult.get(0));
                    }
                    return qualityTestRecordSearchResultList;
                }
            }
            else {
                List<QualityTestRecord> qualityTestRecordResult = purchaseImportService.searchQualityTestRecordByParams(params);
                if (qualityTestRecordResult.size() != 0){
                    qualityTestRecordSearchResultList.add(qualityTestRecordResult.get(0));
                }
                return qualityTestRecordSearchResultList;
            }
        }
        else {
            logger.debug("params不存在");
            return null;
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/deleteQualityTestRecordDetail")
    @ApiOperation(value = "删除检验记录明细", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int deleteQualityTestRecordDetail(@RequestBody Map<String, Object> params){
        if (params.containsKey("data")){
            List <Map<String, Object>> deleteData = (List<Map<String, Object>>)params.get("data");
            int size = deleteData.size();
            int count = 0;
            for (Map<String, Object> data : deleteData){
                if (data.containsKey("qualityTestSerialNo") && data.containsKey("materialCode")){
                    count += purchaseImportService.deleteQualityTestRecordDetail(data);
                }
            }
            if (count == size){
                return count;
            }
            else {
                logger.debug("多条记录删除不完全,本次总共删除" + count + "条检验记录明细");
                return -1;
            }
        }
        else if (params.containsKey("qualityTestSerialNo") && params.containsKey("materialCode")){
            return purchaseImportService.deleteShelfRecordDetail(params);
        }
        else {
            logger.debug("数据不符合规范，无法删除检验记录明细");
            return -1;
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/updateQualityTestRecord")
    @ApiOperation(value = "更新检验记录", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int updateQualityTestRecord (@RequestBody Map<String, Object> params){
        if (params.containsKey("qualityTestSerialNo") && params.containsKey("receivingSerialNo")){
            return purchaseImportService.updateQualityTestRecord(params);
        }
        else {
            logger.debug("数据不符合规范，无法更新检验记录");
            return -1;
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/updateQualityTestRecordDetail")
    @ApiOperation(value = "更新检验记录明细", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int updateQualityTestRecordDetail (@RequestBody Map<String, Object> params){
        if(params.containsKey("data")){
            List <Map<String, Object>> QualityTestRecordDetailData = (List<Map<String, Object>>) params.get("data");
            int size = QualityTestRecordDetailData.size();
            int count = 0;
            for (Map<String, Object> data : QualityTestRecordDetailData){
                if ((data.containsKey("qualityTestSerialNo") && data.containsKey("materialCode"))
                        ||(data.containsKey("receivingSerialNo") && data.containsKey("materialCode"))){
                    count += purchaseImportService.updateQualityTestRecordDetail(data);
                }
            }
            if (count == size){
                return count;
            }
            else {
                logger.debug("多条数据更新不完全,本次总共更新" + count + "条检验记录明细");
                return -1;
            }
        }
        else if ((params.containsKey("qualityTestSerialNo") && params.containsKey("materialCode"))
                ||(params.containsKey("receivingSerialNo") && params.containsKey("materialCode"))){
            return purchaseImportService.updateQualityTestRecordDetail(params);
        }
        else {
            return -1;
        }

    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/getQualityTestRecordDetailByQualityTestSerialNo")
    @ApiOperation(value = "根据检验单号获取对应的检验记录明细", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<QualityTestRecordDetail> getQualityTestRecordDetailByQualityTestSerialNo(@RequestBody Map<String, Object> params){
        if (params.containsKey("qualityTestSerialNo")){
            String qualityTestSerialNo = (String) params.get("qualityTestSerialNo");
            return purchaseImportService.getQualityTestRecordDetailByQualityTestSerialNo(qualityTestSerialNo);
        }
        else {
            logger.debug("传入数据缺少检验单号");
            return null;
        }

    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/isAllMaterialGetTest")
    @ApiOperation(value = "判断一条检验记录的物料是否全部检验", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int isAllMaterialGetTest(@RequestBody Map<String, Object> params){
        String qualityTestSerialNo = (String) params.get("qualityTestSerialNo");
        int count = 0;
        int testCount = 0;
        List <QualityTestRecordDetail> qualityTestRecordDetailResult = purchaseImportService.
                getQualityTestRecordDetailByQualityTestSerialNo(qualityTestSerialNo);
        for (QualityTestRecordDetail testRecordDetail : qualityTestRecordDetailResult){
            count = count + 1;
            if (testRecordDetail.getEntryQuantity() != 0 || testRecordDetail.getReturnQuantity() != 0){
                testCount = testCount + 1;
            }
            else {
                logger.debug("物料:"+ testRecordDetail.getMaterialCode() + "  未检验");
            }
        }
        if (testCount == count){
            return 1;
        }
        else {
            return 0;
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/addWarehouseStockInRecord")
    @ApiOperation(value = "增加入库记录", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int addWarehouseStockInRecord(@RequestBody Map<String, Object> params){
        if (params.containsKey("entrySerialNo") && params.containsKey("entryType") && params.containsKey("result")){
            return purchaseImportService.addWarehouseStockInRecord(params);
        }
        else {
            logger.debug("数据不符合规范，无法创建检验记录");
            return -1;
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/addWarehouseStockInRecordDetail")
    @ApiOperation(value = "增加入库记录明细", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int addWarehouseStockInRecordDetail (@RequestBody Map<String, Object> params){
        if (params.containsKey("data")){
            List <Map<String, Object>> StockInRecordDetailData = (List<Map<String, Object>>)params.get("data");
            int size = StockInRecordDetailData.size();
            int count = 0;
            for (Map<String, Object> data : StockInRecordDetailData){
                if (data.containsKey("entrySerialNo") && data.containsKey("materialCode") && data.containsKey("unitId") &&
                        data.containsKey("entryQuantity") && data.containsKey("price") && data.containsKey("taxPrice")){
                        count += purchaseImportService.addWarehouseStockInRecordDetail(data);
                }
            }
            if (count == size){
                return 1;
            }
            else {
                return -1;
            }
        }
        else if ((params.containsKey("entrySerialNo") && params.containsKey("materialCode") && params.containsKey("unitId") &&
                params.containsKey("entryQuantity") && params.containsKey("price") && params.containsKey("taxPrice"))){
            return purchaseImportService.addWarehouseStockInRecordDetail(params);
        }
        else {
            return -1;
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/updateWarehouseStockInRecord")
    @ApiOperation(value = "更新入库记录", produces = MediaType.APPLICATION_JSON_UTF8_VALUE) // 主要是为了更新入库记录中的入库结果
    public int updateWarehouseStockInRecord(@RequestBody Map<String, Object> params){
        if (params.containsKey("entrySerialNo") && params.containsKey("entryType") && params.containsKey("result")){
            return purchaseImportService.updateWarehouseStockInRecord(params);
        }
        else {
            return -1;
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/getAllWarehouseStockInRecord")
    @ApiOperation(value = "获取所有入库记录", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<WarehouseStockInRecord> getAllWarehouseStockInRecord(){
        return purchaseImportService.getAllWarehouseStockInRecord();
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/getWarehouseStockInRecordByPage")
    @ApiOperation(value = "根据页码入库记录", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<WarehouseStockInRecord> getWarehouseStockInRecordByPage(@RequestParam int page, @RequestParam int number){
        if (page < 1){
            logger.debug("页数不能小于1");
            return null;
        }
        else {
            return purchaseImportService.getWarehouseStockInRecordByPage(page, number);
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/searchStockInRecordByParams")
    @ApiOperation(value = "根据条件搜索相应的入库记录", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<WarehouseStockInRecord> searchStockInRecordByParams(@RequestBody Map<String, Object> params){
        List <WarehouseStockInRecord> warehouseStockInRecordSearchResultList = new ArrayList<>();
        if (params.size()>0){
            if (params.containsKey("DateStart") && params.containsKey("DateEnd")){
                String StartDate = params.get("DateStart").toString();
                String EndDate = params.get("DateEnd").toString();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date DateStart = format.parse(StartDate);
                    Date DateEnd = format.parse(EndDate);
                    if (DateStart.getTime() > DateEnd.getTime()){
                        logger.debug("截止日期大于起始日期,请重新输入");
                        return null;
                    }
                }
                catch (Exception ParseException){
                    ParseException.printStackTrace();
                    return null;
                }
            }
            if (params.containsKey("materialCode")){
                List <String> entrySerialNoList = new ArrayList<String>();
                List <WarehouseStockInRecordDetail> stockInRecordDetailResult = purchaseImportService.searchStockInRecordDetailByParams(params);
                for (WarehouseStockInRecordDetail stockInRecordDetail : stockInRecordDetailResult){
                    String entrySerialNo = stockInRecordDetail.getEntrySerialNo();
                    entrySerialNoList.add(entrySerialNo);
                }
                HashSet hashSet = new HashSet(entrySerialNoList);
                entrySerialNoList.clear();
                entrySerialNoList.addAll(hashSet);
                for (int i = 0; i < entrySerialNoList.size(); i++){
                    String entrySerialNo = (String) entrySerialNoList.get(i);
                    if (params.containsKey("entrySerialNo")){
                        params.remove("entrySerialNo");
                    }
                    params.put("entrySerialNo", entrySerialNo);
                    List <WarehouseStockInRecord> warehouseStockInRecordResult = purchaseImportService.searchStockInRecordByParams(params);
                    if (warehouseStockInRecordResult.size() != 0){
                        warehouseStockInRecordSearchResultList.add(warehouseStockInRecordResult.get(0));
                    }
                }
                return warehouseStockInRecordSearchResultList;
            }
            else {
                List <WarehouseStockInRecord> warehouseStockInRecordResult = purchaseImportService.searchStockInRecordByParams(params);
                if (warehouseStockInRecordResult.size() != 0){
                    warehouseStockInRecordSearchResultList.add(warehouseStockInRecordResult.get(0));
                }
                return warehouseStockInRecordSearchResultList;
            }

        }
        else {
            logger.debug("params不存在");
            return null;
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/getWarehouseStockInRecordDetailByEntrySerialNo")
    @ApiOperation(value = "根据入库单号获取入库记录明细", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<WarehouseStockInPlanDetail> getWarehouseStockInRecordDetailByEntrySerialNo(@RequestParam String entrySerialNo){
        return purchaseImportService.getStockInPlanDetailByPlanSerialNo(entrySerialNo);
    }



    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/addShelfRecord")
    @ApiOperation(value = "新增上架记录", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int addShelfRecord(@RequestBody Map<String, Object> params){
        if (params.containsKey("entrySerialNo") && params.containsKey("operUserId") && params.containsKey("shelfDate")
                && params.containsKey("result")){
            return purchaseImportService.addShelfRecord(params);
        }
        else {
            return -1;
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/addShelfRecordDetail")
    @ApiOperation(value = "新增上架记录明细", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int addShelfRecordDetail(@RequestBody Map<String, Object> params){
        if (params.containsKey("shelfSerialNo") && params.containsKey("materialCode") &&
                params.containsKey("locationId") && params.containsKey("shelfQuantity")){
            // 前端参数缺少unitId
            String shelfSerialNo = (String)params.get("shelfSerialNo");
            String materialCode = (String)params.get("materialCode");
            List <ShelfRecord> ShelfRecordResult = purchaseImportService.getShelfRecordByshelfSerialNo(shelfSerialNo);
            if (ShelfRecordResult.size() == 1){
                ShelfRecord shelfRecord = (ShelfRecord) ShelfRecordResult.get(0);
                String entrySerialNo = shelfRecord.getEntrySerialNo();
                List <WarehouseStockInRecordDetail> StockInRecordDetail = purchaseImportService.getStockInRecordDetailByEntrySerialNoAndMaterialCode(entrySerialNo, materialCode);
                if (StockInRecordDetail.size() == 1){
                    WarehouseStockInRecordDetail stockInRecordDetail = (WarehouseStockInRecordDetail) StockInRecordDetail.get(0);
                    int unitId = stockInRecordDetail.getUnitId();
                    params.put("unitId", unitId);
                    return purchaseImportService.addShelfRecordDetail(params);
                }
                else if (StockInRecordDetail.size() == 0){
                    logger.debug("入库记录明细中不存在该物料");
                    return -1;
                }
                else {
                    logger.debug("入库记录明细表中一种物料对应多条记录,请检验数据表");
                    return -1;
                }
            }
            else if (ShelfRecordResult.size() == 0) {
                logger.debug("该上架单号对应的上架记录不存在");
                return -1;
            }
            else {
                logger.debug("上架记录表中上架单号有重复,请检验数据表");
                return -1;
            }
        }
        else{
            logger.debug("前端数据非空字段缺失");
            return -1;
        }

    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/deleteShelfRecordDetail")
    @ApiOperation(value = "删除上架明细", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int deleteShelfRecordDetail(@RequestBody Map<String, Object> params){
        if (params.containsKey("shelfSerialNo") && params.containsKey("materialCode")){
            return purchaseImportService.deleteShelfRecordDetail(params);
        }
        else {
            logger.debug("上架单号和物料编码属性不能缺失");
            return -1;
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
            methods = {RequestMethod.POST},
            origins = "*")
    @PostMapping(value = "/TestHttp")
    @ApiOperation(value = "跨模块调度测试", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List <Object> TestHttp () {

//        String url = "http://localhost:8180/materialmanagement/getBaseInfo"; //获取spuCode
//        String spuName = "自动化机器";
//        String spuCode = crossModuleClient.getSpuCodeBySpuName(url,spuName);
//        System.out.println(spuCode);

        CrossModuleClient crossModuleClient = new CrossModuleClient();
//        String url = "http://localhost:8180/materialmanagement/getMaterialInfo";
//        String spuCode = "110101";
//        String url = "http://localhost:8180/materialmanagement/getBaseInfo";
//        String materialCode = "11101";
//        String spuCode = crossModuleClient.getSpuCodeByMaterialCode(url,materialCode);
//        System.out.println(spuCode);

        String url = "http://localhost:8180/materialmanagement/getMaterialBaseByCatIdAndType";
        int materialCatId = 7;
        String str = crossModuleClient.getSpecificationByMaterialCatId(url,materialCatId);
        System.out.println(str);

        return null;

    }


//
//    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*",
//            methods = {RequestMethod.POST},
//            origins = "*")
//    @PostMapping(value = "/getWareHouseEntryByParams")
//    @ApiOperation(value = "根据给定参数获取入库记录接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public List<Object> getWareHouseEntryByParams (@RequestBody Map<String, Object> params) {
//        if (params.size()>0){
//            if ((params.get("DateStart")!=null)&&(params.get("DateEnd")!=null)){
//                String StartDate = params.get("DateStart").toString();
//                String EndDate = params.get("DateEnd").toString();
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                try {
//                    Date DateStart = format.parse(StartDate);
//                    Date DateEnd = format.parse(EndDate);
//                    if (DateStart.getTime()>DateEnd.getTime()){
//                        logger.debug("截止日期大于起始日期,请重新输入");
//                        return null;
//                    }
//                }
//                catch (Exception ParseException){
//                    ParseException.printStackTrace();
//                    return null;
//                }
//            }
//
//            return purchaseImportService.getWareHouseEntryByParams(params);
//        }
//
//        else {
//            return null;
//        }
//    }
//


}
