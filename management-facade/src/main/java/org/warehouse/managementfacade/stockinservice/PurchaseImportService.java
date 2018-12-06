package org.warehouse.managementfacade.stockinservice;

import org.springframework.stereotype.Service;
import org.warehouse.managementfacade.model.stockinmodel.*;

import java.util.List;
import java.util.Map;

/**
 * @author cplayer on 2018/9/24.
 * @version 1.0
 */

@Service
public interface PurchaseImportService {

    // 获取该入库类型下所有入库计划
    List <WarehouseStockInPlan> getAllStockInPlanByEntryType(int entryType);

    // 根据页码获取入库计划
    List <WarehouseStockInPlan> getStockInPlanByPage(int page, int number, int entryType);

    // 更新入库计划
    int updateStockInPlan(Map<String, Object> params);

    // 根据条件搜索相应的入库计划
    List <WarehouseStockInPlan> searchStockInPlanByParams(Map<String, Object> params);

    // 根据与物料相关的搜索条件搜索相应的入库计划明细
    List <WarehouseStockInPlanDetail> searchStockInPlanDetailByParams(Map<String, Object> params);

    // 更新入库计划明细
    int updateStockInPlanDetail(Map<String, Object> params);

    // 根据入库计划流水号获取相应的入库计划
    List <WarehouseStockInPlan> getStockInPlanByPlanSerialNo(String planSerialNo);

    // 根据关联单据获取相应的入库计划
    List <WarehouseStockInPlan> getStockInPlanByVouchSerialNo(String vouchSerialNo);

    // 根据入库计划流水号获取相应的入库计划明细
    List <WarehouseStockInPlanDetail> getStockInPlanDetailByPlanSerialNo(String planSerialNo);

    // 根据入库计划流水号和物料编码获取入库计划明细
    List <WarehouseStockInPlanDetail> getStockInPlanDetailByPlanSerialNoAndMaterialCode(String planSerialNo, String materialCode);

    // 新增收货记录
    int addReceivingRecord(Map<String, Object> params);

    // 获取最后一条收货记录
    ReceivingRecord getLastReceivingRecord();

    // 获取最后一条收货记录明细
    ReceivingRecordDetail getLastReceivingRecordDetail();

    // 更新收货记录
    int updateReceivingRecord(Map<String, Object> params);

    // 新增收货记录明细
    int addReceivingRecordDetail(Map<String, Object> params);

    // 根据入库计划流水号获取信息用于收货画面
    List <WarehouseStockInPlan> getReceivingRecordShowDataByPlanSerialNo(String planSerialNo);

    // 根据入库计划流水号获取收货记录明细
    List <ReceivingRecordDetail> getReceivingRecordDetailByPlanSerialNo(String planSerialNo);

    // 新增检验记录
    int addQualityTestRecord(Map<String, Object> params);

    // 获取最后一条检验记录
    QualityTestRecord getLastQualityTestRecord();

    // 新增检验记录明细
    int addQualityTestRecordDetail(Map<String, Object> params);

    // 根据条件搜索相应的检验记录
    List <QualityTestRecord> searchQualityTestRecordByParams(Map<String, Object> params);

    // 根据条件搜索相应的检验记录明细
    List <QualityTestRecordDetail> searchQualityTestRecordDetailByParams(Map<String, Object> params);

    // 删除检验记录明细
    int deleteQualityTestRecordDetail(Map<String, Object> params);

    // 获取所有检验记录
    List <QualityTestRecord> getAllQualityTestRecord();

    // 根据页码获取检验记录
    List <QualityTestRecord> getQualityTestRecordByPage(int page, int number);

    // 更新检验记录
    int updateQualityTestRecord(Map<String, Object> params);

    // 更新检验记录明细
    int updateQualityTestRecordDetail(Map<String, Object> params);

    //根据检验单号获取对应的检验记录明细
    List <QualityTestRecordDetail> getQualityTestRecordDetailByQualityTestSerialNo(String qualityTestSerialNo);

    // 新增入库记录
    int addWarehouseStockInRecord(Map<String, Object> params);

    // 获取所有入库记录
    List <WarehouseStockInRecord> getAllWarehouseStockInRecord(int entryType);

    // 根据页码获取入库记录
    List <WarehouseStockInRecord> getWarehouseStockInRecordByPage(int page, int number);

    // 根据条件搜索相应的入库记录
    List <WarehouseStockInRecord> searchStockInRecordByParams(Map<String, Object> params);

    // 根据条件搜索相应的入库记录明细
    List <WarehouseStockInRecordDetail> searchStockInRecordDetailByParams(Map<String, Object> params);

    // 新增入库记录明细
    int addWarehouseStockInRecordDetail(Map<String, Object> params);

    // 根据入库单号获取所有入库记录明细
    List <WarehouseStockInRecordDetail> getStockInRecordDetailByEntrySerialNo(String entrySerialNo);

    // 根据入库单号和物料编码获取入库记录明细
    List <WarehouseStockInRecordDetail> getStockInRecordDetailByEntrySerialNoAndMaterialCode(String entrySerialNo, String materialCode);

    // 更新入库记录
    int updateWarehouseStockInRecord(Map<String, Object> params);

    // 新增上架记录
    int addShelfRecord(Map<String, Object> params);

    // 根据上架单号获取上架记录
    List <ShelfRecord> getShelfRecordByshelfSerialNo(String shelfSerialNo);

    // 新增上架明细
    int addShelfRecordDetail(Map<String, Object> params);

    // 删除上架明细
    int deleteShelfRecordDetail(Map<String, Object> params);

}
