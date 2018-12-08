package org.warehouse.managementservice.stockinmapper;

import org.apache.ibatis.annotations.*;
import org.warehouse.managementservice.stockinmapper.provider.PurchaseImportProvider;
import org.warehouse.managementfacade.model.stockinmodel.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface PurchaseImportMapper {

    // 获取该入库类型下所有入库计划
    @Select("SELECT * FROM warehouseStockInPlan WHERE entryType=#{entryType};")
    List<WarehouseStockInPlan> getAllStockInPlanResult(int entryType);

    // 根据页码获取入库计划
    @Select("SELECT * FROM warehouseStockInPlan WHERE entryType=#{entryType} limit #{offset},#{number};")
    List<WarehouseStockInPlan> getStockInPlanResultByOffset(@Param("offset") int offset, @Param("number") int number, @Param("entryType") int entryType);

    // 更新入库计划
    @UpdateProvider(type = PurchaseImportProvider.class,
            method = "updateStockInPlanByParams")
    int updateStockInPlanByParams(Map<String, Object> params);

    // 根据条件搜索相应的入库计划
    @SelectProvider(type = PurchaseImportProvider.class,
            method = "searchStockInPlanResultByParams")
    List<WarehouseStockInPlan> searchStockInPlanResultByParams (Map<String, Object> params);

    // 根据与物料相关的搜索条件搜索相应的入库计划明细
    @SelectProvider(type = PurchaseImportProvider.class,
            method = "searchStockInPlanDetailResultByParams")
    List<WarehouseStockInPlanDetail> searchStockInPlanDetailResultByParams (Map<String, Object> params);

    // 根据入库计划流水号查询对应的入库明细
    @Select("SELECT * FROM warehouseStockInPlanDetail WHERE planSerialNo=#{planSerialNo};")
    List<WarehouseStockInPlanDetail> getStockInPlanDetailResultByPlanSerialNo(String planSerialNo);

    // 根据入库计划流水号查询对应的入库计划
    @Select("SELECT * FROM warehouseStockInPlan WHERE planSerialNo=#{planSerialNo};")
    List<WarehouseStockInPlan> getStockInPlanResultByPlanSerialNo(String planSerialNo);

    // 根据关联单据获取相应的入库计划
    @Select("SELECT * FROM warehouseStockInPlan WHERE vouchSerialNo=#{vouchSerialNo};")
    List<WarehouseStockInPlan> getStockInPlanResultByVouchSerialNo(String vouchSerialNo);

    // 更新入库计划明细
    @UpdateProvider(type = PurchaseImportProvider.class,
            method = "updateWarehouseStockInPlanDetailByParams")
    int updateWarehouseStockInPlanDetailByParams(Map<String, Object> params);

    // 根据入库计划流水号和物料编码获取入库计划明细
    @Select("SELECT * FROM warehouseStockInPlanDetail WHERE planSerialNo=#{planSerialNo} and materialCode=#{materialCode};")
    List<WarehouseStockInPlanDetail> getStockInPlanDetailByPlanSerialNoAndMaterialCode(@Param("planSerialNo") String planSerialNo, @Param("materialCode") String materialCode);

    // 增加收货记录
    @InsertProvider(type = PurchaseImportProvider.class,
            method = "addReceivingRecordByParams")
    int addReceivingRecordByParams (String receivingSerialNo, String planSerialNo, int entryType, Map<String, Object> params);

    // 获取最后一条收货记录
    @Select("select * from receivingRecord order by id desc limit 1;")
    ReceivingRecord getLastReceivingRecord();

    // 获取最后一条收货记录明细
    @Select("select * from receivingRecordDetail order by id desc limit 1;")
    ReceivingRecordDetail getLastReceivingRecordDetail();

    // 增加收货记录明细
    @InsertProvider(type = PurchaseImportProvider.class,
            method = "addReceivingRecordDetailByParams")
    int addReceivingRecordDetailByParams (Map<String, Object> params);

    // 更新收货记录
    @UpdateProvider(type = PurchaseImportProvider.class,
            method = "updateReceivingRecordByParams")
    int updateReceivingRecordByParams(Map<String, Object> params);

    // 根据收货记录流水号查询收货记录
    @Select("SELECT * FROM receivingRecord WHERE receivingSerialNo=#{receivingSerialNo};")
    List<ReceivingRecord> getReceivingRecordResultByreceivingSerialNo(String receivingSerialNo);

    // 根据入库计划流水号查询收货记录
    @Select("SELECT * FROM receivingRecord WHERE planSerialNo=#{planSerialNo};")
    List<ReceivingRecord> getReceivingRecordResultByPlanSerialNo(String planSerialNo);

    // 根据入库计划流水号查询收货记录明细
    @Select("SELECT * FROM receivingRecordDetail WHERE planSerialNo=#{planSerialNo};")
    List<ReceivingRecordDetail> getReceivingRecordDetailResultByPlanSerialNo(String planSerialNo);

    // 根据入库计划流水号和物料编号查询收货记录明细 唯一确定一个计划的一种物料的数量
    @Select("SELECT * FROM receivingRecordDetail WHERE planSerialNo=#{planSerialNo} and materialCode=#{materialCode};")
    List<ReceivingRecordDetail> getReceivingRecordDetailResultByPlanSerialNoAndMaterialCode (@Param("planSerialNo") String planSerialNo, @Param("materialCode") String materialCode);

    // 根据入库计划流水号和物料编号查询入库计划明细 唯一确定一个计划的一种物料的数量
    @Select("SELECT * FROM warehouseStockInPlanDetail WHERE planSerialNo=#{planSerialNo} and materialCode=#{materialCode};")
    List<WarehouseStockInPlanDetail> getStockInPlanDetailResultByPlanSerialNoAndMaterialCode(@Param("planSerialNo") String planSerialNo, @Param("materialCode") String materialCode);

    // 增加检验记录
    @InsertProvider(type = PurchaseImportProvider.class,
            method = "addQualityTestRecordByParams")
    int addQualityTestRecordByParams (String qualityTestSerialNo, String receivingSerialNo, Map<String, Object> params);

    // 获取最后一条收货记录
    @Select("select * from qualityTestRecord order by id desc limit 1;")
    QualityTestRecord getLastQualityTestRecord();

    // 增加检验记录明细
    @InsertProvider(type = PurchaseImportProvider.class,
            method = "addQualityTestRecordDetailByParams")
    int addQualityTestRecordDetailByParams(Map<String, Object> params);

    // 删除检验记录明细
    @DeleteProvider(type = PurchaseImportProvider.class,
            method = "deleteQualityTestRecordDetailByParams")
    int deleteQualityTestRecordDetailByParams(Map<String, Object> params);

    // 根据条件搜索相应的检验记录
    @SelectProvider(type = PurchaseImportProvider.class,
            method = "searchQualityTestRecordResultByParams")
    List<QualityTestRecord> searchQualityTestRecordResultByParams (Map<String, Object> params);

    // 根据与物料相关的搜索条件搜索相应的检验记录明细
    @SelectProvider(type = PurchaseImportProvider.class,
            method = "searchQualityTestRecordDetailResultByParams")
    List<QualityTestRecordDetail> searchQualityTestRecordDetailResultByParams (Map<String, Object> params);

    // 获取所有检验记录
    @Select("SELECT * FROM qualityTestRecord;")
    List<QualityTestRecord> getAllQualityTestRecordResult();

    // 根据页码获取检验记录
    @Select("SELECT * FROM qualityTestRecord limit #{offset},#{number};")
    List<QualityTestRecord> getQualityTestRecordResultByOffset(@Param("offset") int offset, @Param("number") int number);

    // 根据检验单号查询对应的检验记录明细
    @Select("SELECT * FROM qualityTestRecordDetail WHERE qualityTestSerialNo=#{qualityTestSerialNo};")
    List<QualityTestRecordDetail> getQualityTestRecordDetailResultByQualityTestSerialNo(String qualityTestSerialNo);

    // 更新检验记录
    @UpdateProvider(type = PurchaseImportProvider.class,
            method = "updateQualityTestRecordByParams")
    int updateQualityTestRecordByParams(Map<String, Object> params);

    // 根据参数查询相应的检验记录明细 （用于更新）
    @SelectProvider(type = PurchaseImportProvider.class,
            method = "getQualityTestRecordDetailByParams")
    List <QualityTestRecordDetail> getQualityTestRecordDetailByParams(Map<String, Object> params);

    // 更新检验记录明细
    @UpdateProvider(type = PurchaseImportProvider.class,
            method = "updateQualityTestRecordDetailByParams")
    int updateQualityTestRecordDetailByParams(Map<String, Object> params);

    // 新增入库记录
    @InsertProvider(type = PurchaseImportProvider.class,
            method = "addWarehouseStockInRecordByParams")
    int addWarehouseStockInRecordByParams(Map<String, Object> params);

    // 根据条件搜索相应的入库记录
    @SelectProvider(type = PurchaseImportProvider.class,
            method = "searchStockInRecordResultByParams")
    List<WarehouseStockInRecord> searchStockInRecordResultByParams (Map<String, Object> params);

    // 根据与物料相关的搜索条件搜索相应的入库记录明细
    @SelectProvider(type = PurchaseImportProvider.class,
            method = "searchStockInRecordDetailResultByParams")
    List<WarehouseStockInRecordDetail> searchStockInRecordDetailResultByParams (Map<String, Object> params);

    // 获取当前入库类型下所有入库记录
    @Select("SELECT * FROM warehouseStockInRecord WHERE entryType=#{entryType};")
    List<WarehouseStockInRecord> getAllWarehouseStockInRecordResultByEntryType(int entryType);

    // 根据页码获取入库记录
    @Select("SELECT * FROM warehouseStockInRecord WHERE entryType=#{entryType} limit #{offset},#{number};")
    List<WarehouseStockInRecord> getWarehouseStockInRecordResultByOffset(@Param("offset") int offset, @Param("number") int number , @Param("entryType") int entryType);

    // 新增入库记录明细
    @InsertProvider(type = PurchaseImportProvider.class,
            method = "addWarehouseStockInRecordDetailByParams")
    int addWarehouseStockInRecordDetailByParams(Map<String, Object> params);

    // 根据入库单号获取入库记录明细
    @Select("SELECT * FROM warehouseStockInRecordDetail WHERE entrySerialNo=#{entrySerialNo};")
    List <WarehouseStockInRecordDetail> getStockInRecordDetailByEntrySerialNo(String entrySerialNo);

    // 根据入库单号和物料编码获取入库记录明细
    @Select("SELECT * FROM shelfRecord WHERE entrySerialNo=#{entrySerialNo} and materialCode=#{materialCode};")
    List <WarehouseStockInRecordDetail> getStockInRecordDetailByEntrySerialNoAndMaterialCode(@Param("entrySerialNo") String entrySerialNo, @Param("materialCode") String materialCode);

    // 更新入库记录
    @UpdateProvider(type = PurchaseImportProvider.class,
            method = "updateWarehouseStockInRecordByParams")
    int updateWarehouseStockInRecordByParams(Map<String, Object> params);

    // 新增上架记录
    @InsertProvider(type = PurchaseImportProvider.class,
            method = "addShelfRecord")
    int addShelfRecordByParams(Map<String, Object> params);

    // 根据上架单号获取上架记录
    @Select("SELECT * FROM shelfRecord WHERE shelfSerialNo=#{shelfSerialNo};")
    List <ShelfRecord> getShelfRecordByshelfSerialNo(String shelfSerialNo);

    // 新增上架明细
    @InsertProvider(type = PurchaseImportProvider.class,
            method = "addShelfRecordDetailByParams")
    int addShelfRecordDetailByParams(Map<String, Object> params);

    // 删除上架明细
    @DeleteProvider(type = PurchaseImportProvider.class,
            method = "deleteShelfRecordDetailByParams")
    int deleteShelfRecordDetailByParams(Map<String, Object> params);

}
