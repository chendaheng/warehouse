package org.warehouse.managementservice.service.stockinimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.warehouse.managementfacade.model.stockinmodel.*;

import org.warehouse.managementfacade.stockinservice.PurchaseImportService;
import org.warehouse.managementservice.stockinmapper.PurchaseImportMapper;

import java.util.List;
import java.util.Map;

/**
 * @author cplayer on 2018/9/24.
 * @version 1.0
 */

@Component
public class PurchaseImportServiceImpl implements PurchaseImportService {
    @Autowired
    public PurchaseImportMapper purchaseImportMapper;

    @Override
    public List<WarehouseStockInPlan> getAllStockInPlanByEntryType(int entryType) {
        // 获取所有入库记录
        List <WarehouseStockInPlan> AllStockInPlanResult = purchaseImportMapper.getAllStockInPlanResult(entryType);
//        List <Object> result = new ArrayList<>();
//        result.add(AllStockInPlanResult);
        return AllStockInPlanResult;
    }

    @Override
    public List<WarehouseStockInPlan> getStockInPlanByPage(int page, int number, int entryType) {
        // 根据页码获取入库计划
        page = page - 1;
        int offset = page * number;
        return purchaseImportMapper.getStockInPlanResultByOffset(offset, number, entryType);
    }

    @Override
    public int updateStockInPlan(Map<String, Object> params) {
        // 更新入库计划
        return purchaseImportMapper.updateStockInPlanByParams(params);
//        String planSerialNo = (String) params.get("planSerialNo");
//        List <WarehouseStockInPlan> StockInPlanResult = purchaseImportMapper.getStockInPlanResultByPlanSerialNo(planSerialNo);
//        if (StockInPlanResult.size() == 0){
//            return 0;
//        }
//        else {
//            params.remove("planSerialNo");
//            return purchaseImportMapper.updateStockInPlanByParams(planSerialNo,params);
//        }
    }

    @Override
    public List<WarehouseStockInPlan> searchStockInPlanByParams(Map<String, Object> params) {
        // 根据条件搜索相应的入库计划
        return purchaseImportMapper.searchStockInPlanResultByParams(params);
    }

    @Override
    public List<WarehouseStockInPlanDetail> searchStockInPlanDetailByParams(Map<String, Object> params) {
        // 根据与物料相关的搜索条件搜索相应的入库计划明细
        return purchaseImportMapper.searchStockInPlanDetailResultByParams(params);
    }

    @Override
    public List<WarehouseStockInPlanDetail> getStockInPlanDetailByPlanSerialNo(String planSerialNo) {
        // 根据入库计划流水号获取相应的入库计划明细
        List <WarehouseStockInPlanDetail> StockInPlanDetailPlanResult = purchaseImportMapper.getStockInPlanDetailResultByPlanSerialNo(planSerialNo);
//        List <Object> result = new ArrayList<>();
//        result.add(StockInPlanDetailPlanResult);
        return StockInPlanDetailPlanResult;

    }

    @Override
    public List<WarehouseStockInPlan> getStockInPlanByPlanSerialNo(String planSerialNo) {
        // 根据入库计划流水号获取相应的入库计划
        List <WarehouseStockInPlan> StockInPlanResult = purchaseImportMapper.getStockInPlanResultByPlanSerialNo(planSerialNo);
//        List <Object> result = new ArrayList<>();
//        result.add(StockInPlanResult);
        return StockInPlanResult;
    }

    @Override
    public List<WarehouseStockInPlan> getStockInPlanByVouchSerialNo(String vouchSerialNo) {
        // 根据关联单据获取相应的入库计划
        List <WarehouseStockInPlan> StockInPlanResult = purchaseImportMapper.getStockInPlanResultByVouchSerialNo(vouchSerialNo);
        return StockInPlanResult;
    }

    @Override
    public List<WarehouseStockInPlanDetail> getStockInPlanDetailByPlanSerialNoAndMaterialCode(String planSerialNo, String materialCode) {
        // 根据入库计划流水号和物料编码获取入库计划明细
        List<WarehouseStockInPlanDetail> StockInPlanDetail = purchaseImportMapper.
                getStockInPlanDetailByPlanSerialNoAndMaterialCode(planSerialNo, materialCode);
        return StockInPlanDetail;
    }

    @Override
    public int updateStockInPlanDetail(Map<String, Object> params) {
        // 更新入库计划明细 params 从收货记录明细中传来
        return purchaseImportMapper.updateWarehouseStockInPlanDetailByParams(params);

    }

    @Override
    public int addReceivingRecord(Map<String, Object> params) {
        // 新增收货记录
        String receivingSerialNo = (String) params.get("receivingSerialNo");
        String planSerialNo = (String) params.get("planSerialNo");
        int entryType = (int) params.get("entryType");
        params.remove("receivingSerialNo");
        params.remove("planSerialNo");
        params.remove("entryType");
        return purchaseImportMapper.addReceivingRecordByParams(receivingSerialNo,planSerialNo,entryType,params);
    }

    @Override
    public ReceivingRecord getLastReceivingRecord() {
        // 获取最后一条收货记录
        return purchaseImportMapper.getLastReceivingRecord();
    }

    @Override
    public ReceivingRecordDetail getLastReceivingRecordDetail() {
        // 获取最后一条收货记录明细
        return purchaseImportMapper.getLastReceivingRecordDetail();
    }

    @Override
    public List<WarehouseStockInPlan> getReceivingRecordShowDataByPlanSerialNo(String planSerialNo) {
        // 根据入库计划流水号获取信息用于收货画面
        List<WarehouseStockInPlan> StockInPlanResult = purchaseImportMapper.getStockInPlanResultByPlanSerialNo(planSerialNo);
//        List <Object> result = new ArrayList<>();
//        result.add(StockInPlanResult);
        return StockInPlanResult;

    }

    @Override
    public int addReceivingRecordDetail(Map<String, Object> params) { //这边传来的params只包含一组数据
        // 新增收货记录明细
        return purchaseImportMapper.addReceivingRecordDetailByParams(params);
    }

    @Override
    public List<ReceivingRecordDetail> getReceivingRecordDetailByPlanSerialNo(String planSerialNo) {
        // 根据入库计划流水号获取收货记录明细
        List <ReceivingRecordDetail> ReceivingRecordDetailResult = purchaseImportMapper.getReceivingRecordDetailResultByPlanSerialNo(planSerialNo);
        return ReceivingRecordDetailResult;
    }

    @Override
    public int updateReceivingRecord(Map<String, Object> params) {
        // 更新收货记录
        return purchaseImportMapper.updateReceivingRecordByParams(params);
    }

    @Override
    public int addQualityTestRecord(Map<String, Object> params) {
        // 增加检验记录
        String qualityTestSerialNo = (String) params.get("qualityTestSerialNo");
        String receivingSerialNo = (String) params.get("receivingSerialNo");
        params.remove("qualityTestSerialNo");
        params.remove("receivingSerialNo");
        return purchaseImportMapper.addQualityTestRecordByParams(qualityTestSerialNo, receivingSerialNo, params);
    }

    @Override
    public QualityTestRecord getLastQualityTestRecord() {
        // 获取最后一条检验记录
        return purchaseImportMapper.getLastQualityTestRecord();
    }

    @Override
    public int addQualityTestRecordDetail(Map<String, Object> params){
        // 增加检验记录明细
        return purchaseImportMapper.addQualityTestRecordDetailByParams(params);
    }

    @Override
    public List<QualityTestRecord> searchQualityTestRecordByParams(Map<String, Object> params) {
        // 根据条件搜索相应的检验记录
        return purchaseImportMapper.searchQualityTestRecordResultByParams(params);
    }

    @Override
    public List<QualityTestRecordDetail> searchQualityTestRecordDetailByParams(Map<String, Object> params) {
        // 根据条件搜索相应的检验记录明细
        return purchaseImportMapper.searchQualityTestRecordDetailResultByParams(params);
    }

    @Override
    public int deleteQualityTestRecordDetail(Map<String, Object> params) {
        // 删除检验记录明细
        return purchaseImportMapper.deleteShelfRecordDetailByParams(params);
    }

    @Override
    public List<QualityTestRecord> getAllQualityTestRecord() {
        // 获取所有的检验记录
        List <QualityTestRecord> QualityTestRecordResult = purchaseImportMapper.getAllQualityTestRecordResult();
//        List <Object> result = new ArrayList<>();
//        result.add(QualityTestRecordResult);
        return QualityTestRecordResult;
    }

    @Override
    public List<QualityTestRecord> getQualityTestRecordByPage(int page, int number) {
        // 根据页码获取检验记录
        page = page - 1;
        int offset = page * number;
        return purchaseImportMapper.getQualityTestRecordResultByOffset(offset, number);
    }

    @Override
    public int updateQualityTestRecord(Map<String, Object> params) {
        // 更新检验记录
        return purchaseImportMapper.updateQualityTestRecordByParams(params);
    }

    @Override
    public int updateQualityTestRecordDetail(Map<String, Object> params) {
        // 更新检验记录明细
        List<QualityTestRecordDetail> QualityTestRecordDetailResult= purchaseImportMapper.getQualityTestRecordDetailByParams(params);
        if (QualityTestRecordDetailResult.size()>0) {
            return purchaseImportMapper.updateQualityTestRecordDetailByParams(params);
        }
        else {
            return 0;
        }
    }

    @Override
    public List<QualityTestRecordDetail> getQualityTestRecordDetailByQualityTestSerialNo(String qualityTestSerialNo) {
        // 根据检验单号获取检验记录明细
        List <QualityTestRecordDetail> AllStockInPlanResult = purchaseImportMapper.getQualityTestRecordDetailResultByQualityTestSerialNo(qualityTestSerialNo);
//        List <Object> result = new ArrayList<>();
//        result.add(AllStockInPlanResult);
        return AllStockInPlanResult;
    }

    @Override
    public List<WarehouseStockInRecord> getAllWarehouseStockInRecord(int entryType) {
        // 获取所有入库记录
        return purchaseImportMapper.getAllWarehouseStockInRecordResultByEntryType(entryType);
    }

    @Override
    public List<WarehouseStockInRecord> getWarehouseStockInRecordByPage(int page, int number) {
        // 根据页码获取入库记录
        page = page - 1;
        int offset = page * number;
        return purchaseImportMapper.getWarehouseStockInRecordResultByOffset(offset, number);
    }

    @Override
    public int addWarehouseStockInRecord(Map<String, Object> params) {
        // 增加入库记录
        return purchaseImportMapper.addWarehouseStockInRecordByParams(params);
    }


    @Override
    public List<WarehouseStockInRecord> searchStockInRecordByParams(Map<String, Object> params) {
        // 根据条件搜索相应的入库记录
        return purchaseImportMapper.searchStockInRecordResultByParams(params);
    }

    @Override
    public List<WarehouseStockInRecordDetail> searchStockInRecordDetailByParams(Map<String, Object> params) {
        // 根据条件搜索相应的入库记录明细
        return purchaseImportMapper.searchStockInRecordDetailResultByParams(params);
    }

    @Override
    public int addWarehouseStockInRecordDetail(Map<String, Object> params) {
        // 增加入库记录明细
        return purchaseImportMapper.addWarehouseStockInRecordDetailByParams(params);
    }

    @Override
    public List<WarehouseStockInRecordDetail> getStockInRecordDetailByEntrySerialNo(String entrySerialNo) {
        // 根据入库单号获取入库记录明细
        return purchaseImportMapper.getStockInRecordDetailByEntrySerial(entrySerialNo);
    }

    @Override
    public List<WarehouseStockInRecordDetail> getStockInRecordDetailByEntrySerialNoAndMaterialCode(String entrySerialNo, String materialCode) {
        // 根据入库单号和物料编码获取入库记录明细
        List <WarehouseStockInRecordDetail> StockInRecordDetail = purchaseImportMapper.
                getStockInRecordDetailByEntrySerialNoAndMaterialCode(entrySerialNo, materialCode);
//        List <Object> result = new ArrayList<>();
//        result.add(StockInRecordDetail);
        return StockInRecordDetail;
    }

    @Override
    public int updateWarehouseStockInRecord(Map<String, Object> params) {
        // 更新入库记录
        return purchaseImportMapper.updateWarehouseStockInRecordByParams(params);
    }

    @Override
    public int addShelfRecord(Map<String, Object> params) {
        // 新增上架记录
        return purchaseImportMapper.addShelfRecordByParams(params);
    }

    @Override
    public List<ShelfRecord> getShelfRecordByshelfSerialNo(String shelfSerialNo) {
        // 根据上架单号获取上架记录
        List <ShelfRecord> ShelfRecordResult = purchaseImportMapper.getShelfRecordByshelfSerialNo(shelfSerialNo);
//        List <Object> result = new ArrayList<>();
//        result.add(ShelfRecordResult);
        return ShelfRecordResult;
    }

    @Override
    public int addShelfRecordDetail(Map<String, Object> params) {
        // 新增上架明细
        return purchaseImportMapper.addShelfRecordByParams(params);
    }

    @Override
    public int deleteShelfRecordDetail(Map<String, Object> params) {
        // 删除上架明细
        return purchaseImportMapper.deleteShelfRecordDetailByParams(params);
    }

    //    @Override
//    public List<Object> getWareHouseEntryByParams (Map<String, Object> params) {
//        // 根据搜索条件查询入库记录(关联单号；日期范围)
//        List <WareHouseEntry> EntryResult = purchaseImportMapper.getWareHouseEntryWithParams(params);
//        List <Object> result = new ArrayList<>();
//        result.add(EntryResult);
//        return result;
//    }
//
//    @Override
//    public List<Object> getWareHouseEntryByEntrySerialNo (String entrySerialNo) {
//        // 根据入库单号查询入库记录(用于update)
//        List<WareHouseEntry> WareHouseEntryResult = purchaseImportMapper.getWareHouseEntryWithSerialNo(entrySerialNo);
//        List <Object> result = new ArrayList<>();
//        result.add(WareHouseEntryResult);
//        return result;
//    }
//
//    @Override
//    public List<Object> getWareHouseEntryDetailByEntrySerialNo(String entrySerialNo) {
//        // 根据入库单号查询入库记录明细(用于update)
//        List <WareHouseEntryDetail> EntryDetailResult = purchaseImportMapper.getWareHouseEntryDetailWithEntrySerialNo(entrySerialNo);
//        List <Object> result = new ArrayList<>();
//        result.add(EntryDetailResult);
//        return result;
//    }
//
//    @Override
//    public List<Object> getWareHouseEntryDetailByMaterialCode(String materialCode) {
//        // 根据物料编码查询入库记录明细
//        List <WareHouseEntryDetail> EntryDetailResult = purchaseImportMapper.getWareHouseEntryDetailWithMaterialCode(materialCode);
//        List <Object> result = new ArrayList<>();
//        result.add(EntryDetailResult);
//        return result;
//    }
//
//    @Override
//    public List<Object> getWareHouseEntryDetailByEntrySerialNoAndMaterialCode(String entrySerialNo, String materialCode) {
//        List <WareHouseEntryDetail> EntryDetailResult = purchaseImportMapper.getWareHouseEntryDetailWithEntrySerialNoAndMaterialCode(entrySerialNo,materialCode);
//        List <Object> result = new ArrayList<>();
//        result.add(EntryDetailResult);
//        return result;
//    }
//
//    @Override
//    public int updateWareHouseEntry (Map<String, Object> params) {
//        // 更新入库记录
//        String entrySerialNo = params.get("entrySerialNo").toString();
//        List<WareHouseEntry> WareHouseEntryList = purchaseImportMapper.getWareHouseEntryWithSerialNo(entrySerialNo);
//        if (WareHouseEntryList!= null && WareHouseEntryList.size()!=0){
//            params.remove("entrySerialNo");
//            return purchaseImportMapper.updateWareHouseEntryBySerialNo(entrySerialNo,params);
//        }
//        else
//            return 0;
//    }
//
//    //更新成功则返回更新记录数，失败则返回0
//    @Override
//    public int updateWareHouseEntryDetail (Map<String, Object> params) {
//        // 更新入库记录明细
//        String entrySerialNo = params.get("entrySerialNo").toString();
//        String materialCode = params.get("materialCode").toString();
//        List<WareHouseEntryDetail> wareHouseEntryDetailList = purchaseImportMapper.getWareHouseEntryDetailWithEntrySerialNoAndMaterialCode(entrySerialNo,materialCode);
//        if (wareHouseEntryDetailList != null && wareHouseEntryDetailList.size() != 0) {
//            params.remove("entrySerialNo");
//            params.remove("materialCode");
//            return purchaseImportMapper.updateWareHouseEntryDetailBySerialNo(entrySerialNo, materialCode, params);
//        } else {
//            return 0;
//        }
//    }

}
