
drop table if exists `warehouseStockInPlan`;
create table if not exists `warehouseStockInPlan` (
    `id` int not null AUTO_INCREMENT,
    `planSerialNo` varchar(20) not null,
    `entryType` tinyint(4) not null,
    `vouchSerialNo` varchar(20),
    `vouchType` tinyint(4),
    `deptId` int,
    `onwerId` int,
    `startDate` datetime,
    `arrivalDate` datetime,
    `deliveryId` int,
    `deliveryAddrId` int,
    `warehouseId` int,
    `receivingAddrId` int,
    `operUserId` int,
    `status` int,
    `note` varchar(100),
    primary key (`id`) using BTREE
)engine=InnoDB default charset=utf8;


drop table if exists `warehouseStockInPlanDetail`;
create table if not exists `warehouseStockInPlanDetail` (
    `id` int not null AUTO_INCREMENT,
    `planSerialNo` varchar(20) not null,
    `materialCode` varchar(20) not null,
    `unitId` int not null,
    `planQuantity` int not null,
    `arrivalQuantity` int not null,
    `price` int not null,
    `taxPrice` int not null,
    `status` int not null,
    `note` varchar(100),
    primary key (`id`) using BTREE
)engine=InnoDB default charset=utf8;


drop table if exists `receivingRecord`;
create table if not exists `receivingRecord` (
    `id` int not null AUTO_INCREMENT,
    `receivingSerialNo` varchar(20) not null,
    `planSerialNo` varchar(20) not null,
    `vouchSerialNo` varchar(20),
    `vouchType` tinyint(4),
    `entryType` tinyint(4) not null,
    `deliveryId` int,
    `deliveryAddrId` int,
    `receivingDate` datetime,
    `warehouseId` int,
    `receivingAddrId` int,
    `operUserId` int,
    `receivingStatus` int,
    `attachmentId` int,
    `note` varchar(100),
    primary key (`id`) using BTREE
)engine=InnoDB default charset=utf8;

drop table if exists `receivingRecordDetail`;
create table if not exists `receivingRecordDetail`(
    `id` int not null AUTO_INCREMENT,
    `receivingSerialNo` varchar(20) not null,
    `planSerialNo` varchar(20) not null,
    `materialCode` varchar(20) not null,
    `unitId` int not null,
    `receivingQuantity` int not null,
    `note` varchar(100),
    primary key (`id`) using BTREE
)engine=InnoDB default charset=utf8;


drop table if exists `qualityTestRecord`;
create table if not exists `qualityTestRecord`(
    `id` int not null AUTO_INCREMENT,
    `qualityTestSerialNo` varchar(20) not null,
    `receivingSerialNo` varchar(20) not null,
    `qualityTestDate` datetime,
    `qualityTestAddr` varchar(10),
    `deptId` int,
    `pickerId` int,
    `operUserId` int,
    `affirmantId` int,
    `status` int,
    `note` varchar(100),
    primary key (`id`) using BTREE
)engine=InnoDB default charset=utf8;


drop table if exists `qualityTestRecordDetail`;
create table if not exists `qualityTestRecordDetail` (
    `id` int not null AUTO_INCREMENT,
    `qualityTestSerialNo` varchar(20) not null,
    `receivingSerialNo` varchar(20) not null,
    `materialCode` varchar(20) not null,
    `unitId` int ,
    `qualityTestQuantity` int default,
    `qualityTestMethod`  varchar(10) ,
    `qualityTestStandard` varchar(10) ,
    `entryQuantity` int ,
    `returnQuantity` int ,
    `reason` varchar(100),
    `result` varchar(10) ,
    `note` varchar(100),
    primary key (`id`) using BTREE
)engine=InnoDB default charset=utf8;


drop table if exists `warehouseStockInRecord`;
create table if not exists `warehouseStockInRecord` (
    `id` int not null AUTO_INCREMENT,
    `entrySerialNo` varchar(20) not null,
    `entryType` tinyint(4) not null,
    `vouchSerialNo` varchar(20),
    `vouchType` tinyint(4),
    `warehouseId` int,
    `deptId` int,
    `operUserId` int,
    `deliveryId` int,
    `entryDate` datetime,
    `result` int not null,
    `note` varchar(100),
    primary key (`id`) using BTREE
)engine=InnoDB default charset=utf8;


drop table if exists `warehouseStockInRecordDetail`;
create table if not exists `warehouseStockInRecordDetail` (
    `id` int not null AUTO_INCREMENT,
    `entrySerialNo` varchar(20) not null,
    `materialCode` varchar(20) not null,
    `batchCode` varchar(20),
    `unitId` int not null,
    `entryQuantity` int not null,
    `price` int not null,
    `taxPrice` int not null,
    `note` varchar(100),
    primary key (`id`) using BTREE
)engine=InnoDB default charset=utf8;


drop table if exists `shelfRecord`;
create table if not exists `ShelfRecord` (
    `id` int not null AUTO_INCREMENT,
    `entrySerialNo` varchar(20) not null,
    `shelfSerialNo` varchar(20) not null,
    `operUserId` int not null,
    `shelfDate` datetime not null,
    `result` varchar(100) not null,
    `note` varchar(100),
    primary key (`id`) using BTREE
)engine=InnoDB default charset=utf8;


drop table if exists `shelfRecordDetail`;
create table if not exists `ShelfRecordDetail` (
    `id` int not null AUTO_INCREMENT,
    `shelfSerialNo` varchar(20) not null,
    `materialCode` varchar(20) not null,
    `batchCode` varchar(20),
    `unitId` int not null,
    `locationId` int not null,
    `shelfQuantity` int not null,
    `note` varchar(100),
    primary key (`id`) using BTREE
)engine=InnoDB default charset=utf8;

drop table if exists `partner`;
create table if not exists `partner` (
    `id` int not null AUTO_INCREMENT,
    `typeId` tinyint(1),
    `catId` int,
    `name` varchar(50),
    `title` varchar(50),
    `company` varchar(50),
    `position` varchar(50),
    `taxNumber` varchar(30),
    `addressId` int,
    `phone` varchar(20),
    `mobile` varchar(20),
    `email` varchar(30),
    `website` varchar(30),
    `language` varchar(20),
    `contactId` int,
    `isCustomer` tinyint(1),
    `isSupplier` tinyint(1),
    `isEmployee` tinyint(1),
    `note` varchar(100),
    primary key (`id`) using BTREE
)engine=InnoDB default charset=utf8;

drop table if exists `partnerCategory`;
create table if not exists `partnerCategory` (
    `id` int not null AUTO_INCREMENT,
    `name` varchar(30) not null,
    primary key (`id`) using BTREE
)engine=InnoDB default charset=utf8;

drop table if exists `addressRelation`;
create table if not exists `addressRelation` (
    `id` int not null AUTO_INCREMENT,
    `partnerId` int,
    `purpose` varchar(50),
    `addressId` int,
    primary key (`id`) using BTREE
)engine=InnoDB default charset=utf8;

drop table if exists `address`;
create table if not exists `address` (
    `id` int not null AUTO_INCREMENT,
    `country` varchar(20),
    `province` varchar(20),
    `city` varchar(20),
    `street` varchar(20),
    `streetSecond` varchar(20),
    `zipCode` varchar(20),
    `contactName` varchar(20),
    `phone` varchar(20),
    `mobile` varchar(20),
    `email` varchar(20),
    `note` varchar(20),
    primary key (`id`) using BTREE
)engine=InnoDB default charset=utf8;

-- ----------------------------
-- 逐日数据库部分
-- ----------------------------
-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '登陆帐户',
  `password_` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `user_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '1' COMMENT '用户类型(1普通用户2管理员3系统用户)',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `name_pinyin` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名拼音',
  `sex_` int(1) NOT NULL DEFAULT 0 COMMENT '性别(0:未知;1:男;2:女)',
  `avatar_` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像',
  `phone_` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电话',
  `mobile_` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号码',
  `email_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证号码',
  `wei_xin` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '微信',
  `wei_bo` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '微博',
  `qq_` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'QQ',
  `birth_day` date NULL DEFAULT NULL COMMENT '出生日期',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门编号',
  `position_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '职位',
  `address_` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '详细地址',
  `staff_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工号',
  `att` bit(1) NULL DEFAULT NULL COMMENT '是否记录考勤 0：记录；1：不记录',
  `enroll_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '考勤记录的用户ID',
  `education_` int(11) NULL DEFAULT NULL COMMENT '学历ID',
  `entry_day` date NULL DEFAULT NULL COMMENT '入职日期',
  `graduateInstitutions` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '毕业院校',
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '专业',
  `cet` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '外语等级',
  `quit` bit(1) NULL DEFAULT NULL COMMENT '离职状态 0：在职；1：离职',
  `leave_day` date NULL DEFAULT NULL COMMENT '离职日期',
  `enable_` tinyint(1) NULL DEFAULT 1,
  `remark_` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE,
  UNIQUE INDEX `account`(`account_`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27353 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'i/sV2VpTPy7Y+ppesmkCmM==', '3', 'admin', 'adad', 1, '', '8088', '18761580890', '', '', '', '', '', '1900-01-01', 38, '', '江苏江阴市嘉祥', '', b'1', '', 0, '2015-12-23', '', '', '', b'0', NULL, 1, '', '2018-04-11 09:38:49', 1, '2018-06-21 09:05:28', 1);
INSERT INTO `sys_user` VALUES (26341, 'FAME PARTNERS', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '杨红霞111', '', 2, '', '15161057836', '12121212', '', '321024197808064225', '', '', '', '1978-08-06', 37, '', 'FAME PARTNERS 客户', '', b'1', '', 1, '2016-06-07', '', '', '', b'0', NULL, 1, '', '2018-05-16 13:21:39', 1, '2018-06-21 10:22:10', 1);
INSERT INTO `sys_user` VALUES (26342, 'jessie wang', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '王建亚', '', 2, '', '68822652-8026', '13701520687', 'jessie@supertexinter', '320219197308178022', '', '', '965337231', '1973-08-17', 39, '', '江苏省江阴市人民东路123号11-505', '', b'1', '', 4, '2012-07-02', '西北纺织工学院', '纺织工程', '四级', b'0', NULL, 1, '', '2018-05-16 13:21:39', 1, '2018-06-21 11:15:39', NULL);
INSERT INTO `sys_user` VALUES (26343, 'LilyYu', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '郁磊', '', 2, '', '68822652-8059', '13506197017', 'lily.yu@supertexinte', '320203197203110629', '', '', '', '1972-03-11', 39, '', '江苏省无锡市滨湖区上严巷45号502室', '', b'1', '', 0, '2015-09-01', '', '', '', b'0', NULL, 1, '', '2018-05-16 13:21:39', 1, '2018-06-21 12:56:37', NULL);
INSERT INTO `sys_user` VALUES (26352, '卞华珍', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '卞华珍', '', 2, '', '15152662620', '', '', '321024197401270625', '', '', '', '1974-01-27', 39, '', '靖江市西来镇永奠村西十圩南埭28号', '', b'1', '', 1, '2017-10-26', '', '', '', b'0', NULL, 1, '', '2018-05-16 13:21:39', 1, '2018-06-21 13:02:35', NULL);
INSERT INTO `sys_user` VALUES (26353, '卜华楠', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '卜华楠', NULL, 1, NULL, '', '', '', '', NULL, NULL, '', '2018-01-11', NULL, NULL, '', NULL, b'1', NULL, 3, '2018-01-11', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:39', NULL, '2018-05-24 14:55:56', NULL);
INSERT INTO `sys_user` VALUES (26354, '蔡成国', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '蔡成国', NULL, 1, NULL, '', '13771229168', '', '320219196809110275', NULL, NULL, '', '1968-09-11', NULL, NULL, '江苏省江阴市璜土镇石庄花港苑A区11幢203室', NULL, b'0', '115', 3, '2015-10-10', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:39', NULL, '2018-05-24 14:55:56', NULL);
INSERT INTO `sys_user` VALUES (26355, '蔡呈凤', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '蔡呈凤', NULL, 2, NULL, '', '15252610385', '', '321086197603201029', NULL, NULL, '', '1976-03-20', NULL, NULL, '靖江市景安苑17幢501室', NULL, b'1', NULL, 1, '2017-04-01', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:39', NULL, '2018-05-24 14:55:56', NULL);
INSERT INTO `sys_user` VALUES (26356, '蔡春雷', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '蔡春雷', NULL, 1, NULL, '', '', '', '', NULL, NULL, '', '2017-09-20', NULL, NULL, '', NULL, b'1', NULL, 5, '2017-09-20', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:39', NULL, '2018-05-24 14:55:56', NULL);
INSERT INTO `sys_user` VALUES (26357, '蔡国华', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '蔡国华', NULL, 1, NULL, '', '18914504630', '', '321024197005081451', NULL, NULL, '', '1970-05-08', NULL, NULL, '江苏省靖江市斜桥镇新华村南十圩埭27号', NULL, b'1', NULL, 0, '2014-11-12', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:39', NULL, '2018-05-24 14:55:56', NULL);
INSERT INTO `sys_user` VALUES (26358, '蔡红梅', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '蔡红梅', NULL, 2, NULL, '', '18014508483', '', '321282198112032427', NULL, NULL, '', '1981-12-03', NULL, NULL, '江苏省靖江市靖城镇中桥村前继盛圩58号', NULL, b'0', '447', 1, '2018-02-23', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:39', NULL, '2018-05-24 14:55:56', NULL);
INSERT INTO `sys_user` VALUES (26359, '蔡欢', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '蔡欢', NULL, 2, NULL, '', '18912362826', '', '220103198107081448', NULL, NULL, '', '1981-07-08', NULL, NULL, '长春市宽城区机车128栋1门1号', NULL, b'1', '296', 5, '2016-10-24', '北华大学', '工商管理', '六级', b'0', NULL, 1, NULL, '2018-05-16 13:21:39', NULL, '2018-05-24 14:55:56', NULL);
INSERT INTO `sys_user` VALUES (26360, '蔡金芳', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '蔡金芳', NULL, 2, NULL, '0523-84691828', '18952620458', '', '321024196812272268', NULL, NULL, '', '1968-12-27', NULL, NULL, '江苏省靖江市富阳小区11幢2单元301室', NULL, b'1', NULL, 0, '2011-09-01', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:39', NULL, '2018-05-24 14:55:56', NULL);
INSERT INTO `sys_user` VALUES (26361, '蔡金芬', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '蔡金芬', NULL, 2, NULL, '0523-84691828', '18952620458', '', '', NULL, NULL, '', NULL, NULL, NULL, '', NULL, b'1', NULL, 0, NULL, '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:39', NULL, '2018-05-24 14:55:56', NULL);
INSERT INTO `sys_user` VALUES (26362, '蔡金龙', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '蔡金龙', NULL, 1, NULL, '', '18014539513', '', '321086197202020413', NULL, NULL, '', '1972-02-02', NULL, NULL, '靖江市西来镇普福村新市七圩埭4-2号', NULL, b'1', NULL, 1, '2017-02-15', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:56', NULL);
INSERT INTO `sys_user` VALUES (26363, '蔡美华', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '蔡美华', NULL, 2, NULL, '68822652-8018', '15995339185', '', '330521196812267401', NULL, NULL, '1037979283', '1968-12-26', NULL, NULL, '江苏省江阴市新华一村27幢103室', NULL, b'0', '86', 3, '2009-11-01', '浙江省德清职高', '纺织', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:56', NULL);
INSERT INTO `sys_user` VALUES (26364, '蔡娜', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '蔡娜', NULL, 2, NULL, '', '', '', '', NULL, NULL, '', NULL, NULL, NULL, '', NULL, b'1', NULL, 5, NULL, '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:56', NULL);
INSERT INTO `sys_user` VALUES (26365, '蔡向荣', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '蔡向荣', NULL, 1, NULL, '', '', '', '', NULL, NULL, '', NULL, NULL, NULL, '', NULL, b'1', NULL, 1, NULL, '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:56', NULL);
INSERT INTO `sys_user` VALUES (26366, '蔡晓凤', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '蔡晓凤', NULL, 2, NULL, '', '18761580936', '', '321324199512181284', NULL, NULL, '', '1995-12-18', NULL, NULL, '江苏省泗洪县魏营镇涧北村十一组103号', NULL, b'0', '330', 4, '2017-02-08', '江阴职业技术学院', '服装设计', '三级', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:56', NULL);
INSERT INTO `sys_user` VALUES (26367, '蔡晓贤', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '蔡晓贤', NULL, 2, NULL, '', '15950131967', '', '320219198212010780', NULL, NULL, '911567002', '1982-12-01', NULL, NULL, '江苏省江阴市利港镇黄丹村七', NULL, b'1', NULL, 1, '2003-01-01', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:56', NULL);
INSERT INTO `sys_user` VALUES (26368, '蔡云', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '蔡云', '', 1, '', '', '', '', '', '', '', '', '2017-04-29', 37, '', '', '11212', b'1', '', 5, '2017-04-29', '', '', '', b'0', NULL, 1, '', '2018-05-16 13:21:40', 1, '2018-06-13 17:10:32', NULL);
INSERT INTO `sys_user` VALUES (26369, '蔡章根', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '蔡章根', NULL, 1, NULL, '', '', '', '', NULL, NULL, '', '2017-10-20', NULL, NULL, '', NULL, b'1', NULL, 5, '2017-10-20', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26370, '曹德美', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '曹德美', NULL, 2, NULL, '', '', '', '', NULL, NULL, '', '2018-04-09', NULL, NULL, '', NULL, b'1', NULL, 5, '2018-04-09', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26371, '曹广静', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '曹广静', NULL, 2, NULL, '68822652-8079', '13515194820', 'diana@supertexintern', '32032319841004202X', NULL, NULL, '414221371', '1984-10-04', NULL, NULL, '江苏省铜山县铜山镇阎山村9对190号', NULL, b'1', '26', 4, '2013-03-11', '徐州工程学院', '外贸英语', '六级', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26372, '曹巧娣', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '曹巧娣', NULL, 2, NULL, '68822652-8007', '18352585757', '', '320219197011196764', NULL, NULL, '', '1970-11-19', NULL, NULL, '江苏省江阴市锦隆一村17幢403室', NULL, b'1', '331', 1, '2017-02-10', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26374, '曹圣', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '曹圣', NULL, 1, NULL, '', '', '', '', NULL, NULL, '', '2017-08-23', NULL, NULL, '', NULL, b'1', NULL, 2, '2017-08-23', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26375, '曹霞', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '曹霞', NULL, 2, NULL, '68822652-8030', '13621539972', '', '320825197803205729', NULL, NULL, '', '1978-03-20', NULL, NULL, '江苏省江阴市澄江镇香山村孙家弄156-1号', NULL, b'0', '251', 2, '2016-04-01', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26376, '曹小余', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '曹小余', NULL, 1, NULL, '', '18952628085', '', '321024195305272218', NULL, NULL, '', '1953-05-27', NULL, NULL, '江苏省靖江市靖城镇宜和村裕盛圩41号', NULL, b'1', NULL, 0, '2012-10-28', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26377, '查春燕', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '查春燕', NULL, 2, NULL, '', '13771252491', '', '320281198902181024', NULL, NULL, '', '1989-02-18', NULL, NULL, '江苏省江阴市临港街道江南花园20幢203室', NULL, b'1', '386', 5, '2017-05-16', '南京工业大学浦江学院', '生物工程', '四级', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26378, '常龙军', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '常龙军', NULL, 1, NULL, '', '', '', '', NULL, NULL, '', '2017-04-01', NULL, NULL, '', NULL, b'1', NULL, 4, '2017-04-01', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26379, '常秋平', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '常秋平', NULL, 2, NULL, '', '13382571569', '', '321024197206124227', NULL, NULL, '', '1972-06-12', NULL, NULL, '江苏省靖江市东兴镇成德村隆盛庄52号', NULL, b'0', '392', 1, '2017-05-29', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26380, '常艳', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '常艳', NULL, 1, NULL, '18101528633', '18752681032', '', '321024197511122236', NULL, NULL, '814775747', '1975-11-12', NULL, NULL, '江苏省靖江市靖城镇越江村泰兴圩34-1号', NULL, b'0', '142', 4, '2012-07-02', '上海卢湾业余大学', '制版', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26381, '常永龙', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '常永龙', NULL, 1, NULL, '', '18752488978', '', '321024196408062218', NULL, NULL, '', '1964-08-06', NULL, NULL, '靖江市滨江新区富阳社区一区17幢一单元101室', NULL, b'1', NULL, 1, '2018-04-16', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26382, '常玉兰', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '常玉兰', NULL, 2, NULL, '68822652-8020', '18351162593', '', '321086196604152420', NULL, NULL, '', '1966-04-15', NULL, NULL, '江苏省靖江市江阴经济开发区靖江园区莲沁苑二区9幢102室', NULL, b'0', '119', 1, '2012-08-13', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26383, '陈爱红', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈爱红', NULL, 2, NULL, '', '18606104948', '', '320682197708211887', NULL, NULL, '', '1977-08-21', NULL, NULL, '靖江市季市镇陈塘村唐家埭76号', NULL, b'1', NULL, 1, '2016-02-18', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26384, '陈爱华', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈爱华', NULL, 2, NULL, '', '', '', '', NULL, NULL, '', NULL, NULL, NULL, '', NULL, b'1', NULL, 5, NULL, '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26385, '陈春红', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈春红', NULL, 2, NULL, '8014', '', '891895158@QQ.COM', '', NULL, NULL, '891895158', NULL, NULL, NULL, '', NULL, b'1', NULL, 5, NULL, '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26386, '陈翠红', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈翠红', NULL, 2, NULL, '', '15961070055', '', '321024197609192224', NULL, NULL, '', '1976-09-19', NULL, NULL, '江苏省靖江市靖城镇宜稼村后宏盛圩73号', NULL, b'1', NULL, 0, '2014-03-20', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26387, '陈东明', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈东明', NULL, 1, NULL, '', '13813574373', '', '', NULL, NULL, '', NULL, NULL, NULL, '', NULL, b'1', NULL, 5, NULL, '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26388, '陈恩', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈恩', NULL, 1, NULL, '68822652-8091', '13584159311', '', '32021919751002051X', NULL, NULL, '', '1975-10-02', NULL, NULL, '', NULL, b'0', '351', 5, '2016-01-15', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:57', NULL);
INSERT INTO `sys_user` VALUES (26389, '陈桂兰', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈桂兰', NULL, 2, NULL, '', '13852636781', '', '32102419690709242X', NULL, NULL, '', '1969-07-09', NULL, NULL, '江苏省靖江市江阴经济开发区靖江园区八圩村后义太庄19号', NULL, b'1', NULL, 0, '2015-09-14', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26390, '陈国平', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈国平', NULL, 1, NULL, '', '18752683820', '', '321024197008202212', NULL, NULL, '', '1970-08-20', NULL, NULL, '江苏省靖江市雅桥村业盛圩19号', NULL, b'1', NULL, 0, '2014-04-06', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26391, '陈汉松', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈汉松', NULL, 1, NULL, '18860995325', '15061033429', '', '321024196105243417', NULL, NULL, '2589696521', '1961-05-24', NULL, NULL, '江苏省靖江市马桥镇祖师村陈家观音堂埭26号', NULL, b'1', NULL, 2, '2012-02-27', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26392, '陈红', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈红', NULL, 2, NULL, '', '15250802732', '', '321282199209130044', NULL, NULL, '', '1992-09-13', NULL, NULL, '江苏省靖江市江阴经济开发区靖江园区六圩村奎盛圩16号', NULL, b'1', '260', 4, '2016-05-30', '江苏农林业技术学院', '环境检测', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26393, '陈红军', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈红军', NULL, 1, NULL, '', '', '', '', NULL, NULL, '', '1900-01-01', NULL, NULL, '', NULL, b'1', NULL, 5, '2016-01-14', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26394, '陈建兵', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈建兵', NULL, 1, NULL, '', '13852630104', '', '32102419690612221X', NULL, NULL, '', '1969-06-12', NULL, NULL, '江苏省靖江市富阳小区5幢2单元202', NULL, b'1', NULL, 0, '2012-10-15', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:40', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26395, '陈金叶', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈金叶', NULL, 1, NULL, '', '', '', '', NULL, NULL, '', '2018-01-18', NULL, NULL, '', NULL, b'1', NULL, 2, '2018-01-18', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26396, '陈金云', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈金云', NULL, 2, NULL, '13812173832', '13428618922', '', '321024196707284224', NULL, NULL, '', '1967-07-28', NULL, NULL, '江苏省靖江市新桥镇太和村大圩33号', NULL, b'0', '282', 1, '2016-08-08', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26397, '陈静霞', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈静霞', NULL, 2, NULL, '', '15861604049', '', '320281198711116529', NULL, NULL, '', '1987-11-11', NULL, NULL, '江苏省江阴市澄江镇皮弄村计家湾52号', NULL, b'0', '495', 5, '2018-05-08', '南京理工大学紫金学院', '计算机科学与技术', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26398, '陈娟', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈娟', NULL, 2, NULL, '', '18896815567', '', '320382199607104241', NULL, NULL, '', '1996-07-10', NULL, NULL, '江苏省邳州市邢楼镇黄楼村瓦房庄386号', NULL, b'0', '471', 5, '2018-03-16', '苏州大学应用技术学院', '服装设计与工程', '六级', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26399, '陈君', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈君', NULL, 1, NULL, '', '', '', '', NULL, NULL, '', '2018-03-15', NULL, NULL, '', NULL, b'1', NULL, 5, '2018-03-15', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26400, '陈蕾', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈蕾', NULL, 2, NULL, '', '15995351359', '', '320281199111196769', NULL, NULL, '', '1991-11-19', NULL, NULL, '江苏省江阴市长山渡江四村16幢305室', NULL, b'1', NULL, 5, '2018-03-16', '南京工程学院', '人力资源管理', '四级', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26401, '陈莉', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈莉', NULL, 2, NULL, '', '15989375922', '', '321283198605306429', NULL, NULL, '', '1986-05-30', NULL, NULL, '江苏省泰兴市七圩镇高圩村高东10号', NULL, b'0', '436', 4, '2017-12-01', '江苏广播电视大学', '文秘/信息管理', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26402, '陈立明', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈立明', NULL, 1, NULL, '', '18952629286', '', '321024197101192231', NULL, NULL, '2105367098', '1971-01-19', NULL, NULL, '江苏省靖江市靖城镇新普村孙大圩105-1号', NULL, b'1', NULL, 2, '2014-02-10', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26403, '陈丽', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈丽', NULL, 2, NULL, '', '15152658113', '', '321282198703041229', NULL, NULL, '', '1987-03-04', NULL, NULL, '靖江市斜桥镇红旗村白家殿埭90号', NULL, b'1', NULL, 1, '2016-02-18', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26404, '陈丽琴', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈丽琴', NULL, 2, NULL, '', '18921732090', '', '321086197704100622', NULL, NULL, '', '1977-04-10', NULL, NULL, '靖江市西来镇创新村保和埭39号', NULL, b'1', NULL, 1, '2017-02-06', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26405, '陈玲', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈玲', NULL, 2, NULL, '68822652-8011', '18352587836', 'lynne@supertexintern', '320281199006149047', NULL, NULL, '779258003', '1990-06-14', NULL, NULL, '江苏省江阴市澄江镇金童村陈彦桥12号', NULL, b'1', '38', 5, '2013-10-07', '常州大学', '英语', '六级', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26406, '陈铃', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈铃', NULL, 2, NULL, '', '15896025792', '', '321282198603211024', NULL, NULL, '', '1986-03-21', NULL, NULL, '靖江市季市镇长安村海安殿埭2号', NULL, b'1', NULL, 1, '2016-02-29', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26407, '陈鎏', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈鎏', NULL, 1, NULL, '', '15250825662', '', '321282198903291812', NULL, NULL, '', '1989-03-29', NULL, NULL, '江苏省靖江市靖城镇东郊村自家弄23号', NULL, b'1', '431', 5, '2017-10-27', '南京理工大学紫金学院', '工业工程', '四级', b'1', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26408, '陈吕洋', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈吕洋', NULL, 1, NULL, '18860995380', '', '', '32102419640801403X', NULL, NULL, '1242231523', '1964-08-01', NULL, NULL, '江苏省靖江市东兴镇东兴村里东兴圩44号', NULL, b'1', NULL, 2, '2014-02-24', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26409, '陈满英', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈满英', NULL, 2, NULL, '', '15152641624', '', '321024197207251826', NULL, NULL, '', '1972-07-25', NULL, NULL, '靖江市靖城镇光明村包家坎17号', NULL, b'1', NULL, 1, '2017-05-02', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:58', NULL);
INSERT INTO `sys_user` VALUES (26410, '陈曼', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈曼', NULL, 2, NULL, '', '18761583270', '', '320722199603241260', NULL, NULL, '', '1996-03-24', NULL, NULL, '江苏省东海县驼峰乡杨大庄村9-11号', NULL, b'0', '313', 4, '2017-01-04', '江阴职业技术学院', '时装设计', '三级', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26411, '陈妙菊', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈妙菊', NULL, 2, NULL, '15952639631', '', '', '330727197112100046', NULL, NULL, '', '1971-12-10', NULL, NULL, '江苏省靖江市富阳小区36幢201室', NULL, b'1', NULL, 0, '2012-08-03', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26412, '陈萍', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈萍', NULL, 2, NULL, '', '', '', '', NULL, NULL, '', '2018-04-09', NULL, NULL, '', NULL, b'1', NULL, 5, '2018-04-09', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26413, '陈师傅', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈师傅', NULL, 2, NULL, '', '13376009059', '', '', NULL, NULL, '', NULL, NULL, NULL, '', NULL, b'1', NULL, 0, NULL, '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26414, '陈松', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈松', NULL, 1, NULL, '18101528633', '15852612932', '', '321282198702024216', NULL, NULL, '258660116', '1987-02-02', NULL, NULL, '江苏省靖江市新桥镇滨江村尖角圩29号', NULL, b'0', '152', 2, '2015-04-16', '', '制版', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26415, '陈婷', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈婷', NULL, 2, NULL, '68822652-8057', '15061566258', 'vanessa@supertexinte', '320281199007046541', NULL, NULL, '2812548952', '1990-07-04', NULL, NULL, '江苏省江阴市澄江镇蒲桥村吴家村56号', NULL, b'0', '36', 5, '2013-07-22', '南京航天航空大学（自考）', '商务英语', '六级', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26416, '陈小娟', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈小娟', NULL, 2, NULL, '', '', '', '321282198611062241', NULL, NULL, '', '1986-11-06', NULL, NULL, '江苏省靖江市靖城镇井兴村顶兴圩30号', NULL, b'1', NULL, 0, '2015-10-12', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26417, '陈小艳', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈小艳', NULL, 2, NULL, '68822652-8078', '13915258555', '', '32082619821210242X', NULL, NULL, '', '1982-12-10', NULL, NULL, '江苏省江阴市西园一村20幢104室', NULL, b'0', '305', 4, '2016-11-07', '江阴职业技术学院', '现代毛纺技术', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26418, '陈晓霞', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈晓霞', NULL, 2, NULL, '', '', '', '', NULL, NULL, '', NULL, NULL, NULL, '', NULL, b'1', NULL, 5, NULL, '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26419, '陈新华', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈新华', NULL, 2, NULL, '68822652-8015', '15358025895', '', '320219197810097287', NULL, NULL, '', '1978-10-09', NULL, NULL, '江苏省江阴市花园一村9幢102室', NULL, b'0', '357', 5, '2017-03-08', '南京财经大学', '会计学', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26420, '陈鑫', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈鑫', NULL, 1, NULL, '68822652-8058', '15952630499', '', '321282199202160435', NULL, NULL, '895679464', '1992-02-16', NULL, NULL, '江苏省靖江市西来镇见龙村高祥甫埭10-6号', NULL, b'1', '169', 5, '2015-06-18', '苏州大学', '服装设计与工程', '四级', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26421, '陈桠楠', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈桠楠', NULL, 2, NULL, '68822652-8023', '18601592445', '', '320281199309156025', NULL, NULL, '', '1993-09-15', NULL, NULL, '江苏省江阴市云亭镇云新村卢家村70号', NULL, b'0', '396', 5, '2017-06-07', '无锡太湖学院', '英语', '专八', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26422, '陈亚', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈亚', NULL, 1, NULL, '18101528633', '15996050239', '', '32108619660622263X', NULL, NULL, '', '1966-06-22', NULL, NULL, '江苏省靖江市靖城镇十圩村南开胜圩31号', NULL, b'0', '135', 2, '2012-09-22', '', '制版', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26423, '陈燕', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈燕', NULL, 2, NULL, '', '13852859936', '', '321282197703062228', NULL, NULL, '', '1977-03-06', NULL, NULL, '江苏省靖江市斜桥镇黄普村黄普圩5-2号', NULL, b'1', NULL, 0, '2015-11-02', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26424, '陈一鸣', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈一鸣', NULL, 1, NULL, '68822652-8087', '18352622431', '', '321283199605210616', NULL, NULL, '', '1996-05-21', NULL, NULL, '江苏省泰兴市分界镇长生村北周四组34号', NULL, b'0', '160', 1, '2015-09-14', '东华大学服装学院', '高级时装设计进修班', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26425, '陈仪平', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈仪平', NULL, 1, NULL, '', '15961033281', '', '320324195403025515', NULL, NULL, '', '1954-03-02', NULL, NULL, '江苏省靖江市东环', NULL, b'1', NULL, 0, '2014-06-08', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26426, '陈怡', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈怡', NULL, 2, NULL, '', '13771608960', 'supertexcy@vip.sina.com', '', NULL, NULL, '804537050', NULL, NULL, NULL, '', NULL, b'1', NULL, 5, NULL, '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26427, '陈怡新', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈怡新', NULL, 1, NULL, '', '15061822190', '', '32022219800502343X', NULL, NULL, '', '1980-05-02', NULL, NULL, '江苏省无锡市惠山区金惠苑359号501室', NULL, b'0', '439', 3, '2017-12-12', '常州纺织工业学校', '服装工程与计算机应用', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26428, '陈银霞', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈银霞', NULL, 2, NULL, '', '18712353239', '', '321282198812212840', NULL, NULL, '', '1988-12-21', NULL, NULL, '靖江市靖城镇友仁村钱西埭11号', NULL, b'1', NULL, 1, '2016-11-02', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26429, '陈友兵', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈友兵', NULL, 1, NULL, '', '13564289232', '', '321283198708120475', NULL, NULL, '', '1987-08-12', NULL, NULL, '江苏省泰兴市珊瑚镇珊瑚新村十九组3号', NULL, b'1', NULL, 1, '2016-09-12', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26430, '陈友平', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈友平', NULL, 1, NULL, '68822652-8099', '18121743085', '', '321025197709260411', NULL, NULL, '', '1977-09-26', NULL, NULL, '江苏省泰兴市珊瑚镇珊瑚新村十九组5号', NULL, b'0', '238', 1, '2015-02-26', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:55:59', NULL);
INSERT INTO `sys_user` VALUES (26431, '陈宇豪', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈宇豪', NULL, 1, NULL, '68822652-8076', '18451395217', '', '320281199510066777', NULL, NULL, '', '1995-10-06', NULL, NULL, '江苏省江阴市长山石牌一村26幢503室', NULL, b'0', '362', 5, '2017-03-27', '盐城工学院', '国际经济与贸易', '四级', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26432, '陈玉芳', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈玉芳', NULL, 2, NULL, '', '', '', '320622196910315603', NULL, NULL, '', '1969-10-31', NULL, NULL, '靖江市西来镇龙华村振华埭17号', NULL, b'1', NULL, 1, '2016-03-03', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26433, '陈月霞', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈月霞', NULL, 2, NULL, '15261631690', '15158827936', '', '321085197712245243', NULL, NULL, '295810211', '1977-12-24', NULL, NULL, '江苏省靖江市东兴镇东兴村长圩49号', NULL, b'1', NULL, 1, '2015-04-13', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26434, '陈云芳', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈云芳', NULL, 2, NULL, '', '13952622721', '', '321282198204292429', NULL, NULL, '', '1982-04-29', NULL, NULL, '靖江市靖城镇火炬村27号', NULL, b'1', NULL, 1, '2016-02-15', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26435, '陈云霞', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈云霞', NULL, 2, NULL, '18921736619', '18921731070', '', '32108619761117362X', NULL, NULL, '', '1976-11-17', NULL, NULL, '江苏省靖江市马桥镇正西村朱家场14号', NULL, b'1', NULL, 0, '2015-04-23', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26436, '陈正红', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈正红', NULL, 1, NULL, '68822652-8018', '15850858215', '', '321024196511215016', NULL, NULL, '1004617348', '1965-11-21', NULL, NULL, '江苏省靖江市江阴经济开发区靖江园区莲沁苑二区17幢203', NULL, b'0', '77', 2, '2014-02-08', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26437, '陈志金', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '陈志金', NULL, 1, NULL, '68822652-8024', '13701434569', '', '321024196410224212', NULL, NULL, '', '1964-10-22', NULL, NULL, '江苏省靖江市新桥镇礼胜村礼士圩东5号', NULL, b'0', '85', 2, '2016-02-17', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26438, '成纪德', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '成纪德', NULL, 1, NULL, '68822652-8001', '15052892506', '2824603548@qq.com', '321024196704224031', NULL, NULL, '2824603548', '1967-04-22', NULL, NULL, '江苏省靖江市东兴镇兴旺圩36号', NULL, b'0', '84', 1, '2011-12-10', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:41', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26439, '仇旻', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '仇旻', NULL, 2, NULL, '68822652-8062', '13656168860', 'charming@supertexint', '320281199204058525', NULL, NULL, '794279735', '1992-04-05', NULL, NULL, '江苏省江阴市虹桥五村13幢104室', NULL, b'1', '202', 5, '2014-09-15', '江苏理工学院', '国际经济贸易', '六级', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26440, '仇盈晶', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '仇盈晶', NULL, 1, NULL, '', '', '', '', NULL, NULL, '', '2018-04-17', NULL, NULL, '', NULL, b'1', NULL, 5, '2018-04-17', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26441, '储星星', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '储星星', NULL, 2, NULL, '', '18860975592', '', '320621199410202028', NULL, NULL, '', '1994-10-20', NULL, NULL, '江苏省海安县城东镇新立村十一组4号', NULL, b'1', '323', 5, '2017-02-06', '南通大学杏林学院', '服装设计与工程', '四级', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26442, '崔霄', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '崔霄', NULL, 2, NULL, '', '18861755692', '', '321183199502201623', NULL, NULL, '', '1995-02-20', NULL, NULL, '江苏省句容市宝华镇崔家自然村39号', NULL, b'1', '20', 4, '2016-02-17', '江阴职业技术学院', '商务英语', '六级', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26443, '戴德琳', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '戴德琳', NULL, 2, NULL, '68822652-8076', '13812139817', 'linda@supertexintern', '321002198301275548', NULL, NULL, '80889211', '1983-01-27', NULL, NULL, '江苏省江阴望江花园怡江城152号302室', NULL, b'0', '23', 4, '2014-04-01', '扬州大学', '商务英语', '四级', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26444, '戴飞', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '戴飞', NULL, 1, NULL, '', '', '', '321283197902227212', NULL, NULL, '', '1979-02-22', NULL, NULL, '', NULL, b'1', '340', 2, '2017-02-17', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26445, '戴凤芳', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '戴凤芳', NULL, 2, NULL, '', '15161039145', '', '321024196809282246', NULL, NULL, '', '1968-09-28', NULL, NULL, '江苏省靖江市靖城镇井兴村汪家圩36号', NULL, b'1', NULL, 0, '2014-05-12', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26446, '戴凤芬', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '戴凤芬', NULL, 1, NULL, '', '', '', '', NULL, NULL, '', NULL, NULL, NULL, '', NULL, b'1', NULL, 5, NULL, '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26447, '戴华', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '戴华', NULL, 2, NULL, '', '15995355299', '', '320219198209096026', NULL, NULL, '', '1982-09-09', NULL, NULL, '江苏省江阴市徐霞客镇东宏村汤家村133号', NULL, b'1', '277', 4, '2016-07-20', '南通纺织职业技术学院', '纺织品检测/国际贸易', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26448, '戴剑民', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '戴剑民', NULL, 1, NULL, '', '', '', '321086197105172616', NULL, NULL, '632607350', '1971-05-17', NULL, NULL, '江苏省靖江市江阴经济开发区靖江园区莲沁苑五区12幢503', NULL, b'1', '82', 2, '2014-10-07', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26449, '戴丽君', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '戴丽君', '', 2, '', '18860995361', '18861024019', '', '321282198506075286', '', '', '2917954992', '1985-06-07', 39, '', '江苏省靖江市新桥镇新桥村新四圩西39号', '', b'1', '', 2, '2014-02-07', '', '', '', b'0', NULL, 1, '', '2018-05-16 13:21:42', 1, '2018-06-13 17:18:34', NULL);
INSERT INTO `sys_user` VALUES (26450, '戴美华', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '戴美华', NULL, 2, NULL, '', '', '', '332624196603240409', NULL, NULL, '', '1966-03-24', NULL, NULL, '靖江市靖城镇柏一村卢家场14号', NULL, b'1', NULL, 1, '2016-02-15', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:00', NULL);
INSERT INTO `sys_user` VALUES (26451, '戴咪', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '戴咪', NULL, 2, NULL, '68822652-8076', '18761801609', 'julie@supertexintern', '320281199302075767', NULL, NULL, '806022628', '1993-02-07', NULL, NULL, '江苏省江阴市南闸镇龙运村花家村24号', NULL, b'0', '49', 5, '2015-06-30', '南京信息工程大学', '国际经济与贸易', '六级', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);
INSERT INTO `sys_user` VALUES (26452, '戴雅雯', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '戴雅雯', NULL, 2, NULL, '68822652-8010', '13915308806', '', '320281199407287029', NULL, NULL, '', '1994-07-28', NULL, NULL, '江苏省江阴市澄西路七十二弄29号', NULL, b'0', '275', 5, '2016-07-11', '苏州大学应用技术学院', '国际经济与贸易', '六级', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);
INSERT INTO `sys_user` VALUES (26453, '戴烨', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '戴烨', NULL, 2, NULL, '68822651-8024', '13626238585', 'dora@supertexinterna', '320281198808122263', NULL, NULL, '724693953', '1988-08-12', NULL, NULL, '江苏省江阴市花园四村27幢407', NULL, b'1', '44', 5, '2014-08-04', '华中科技大学', '国际贸易', '四级', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);
INSERT INTO `sys_user` VALUES (26454, '戴一平', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '戴一平', NULL, 2, NULL, '', '15950128074', '', '320219198201011286', NULL, NULL, '965641889', '1982-01-01', NULL, NULL, '江苏省江阴市夏港镇夏东村戴家村12号', NULL, b'0', '106', 4, '2014-03-15', '', '商务英语', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);
INSERT INTO `sys_user` VALUES (26455, '戴玉香', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '戴玉香', NULL, 2, NULL, '15961535378', '', '', '320219196308270043', NULL, NULL, '2667958733', '1963-08-27', NULL, NULL, '江苏省江阴市璜土镇璜土村胡家头27号', NULL, b'1', NULL, 1, '2013-02-17', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);
INSERT INTO `sys_user` VALUES (26456, '单道芬', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '单道芬', NULL, 1, NULL, '', '', '', '', NULL, NULL, '', '2017-10-20', NULL, NULL, '', NULL, b'1', NULL, 5, '2017-10-20', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);
INSERT INTO `sys_user` VALUES (26457, '邓白银', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '邓白银', NULL, 1, NULL, '', '18882677267', '', '511602199505074310', NULL, NULL, '', '1995-05-07', NULL, NULL, '四川省广安市广安区枣山镇建民村2组16号', NULL, b'0', '465', 2, '2018-03-06', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);
INSERT INTO `sys_user` VALUES (26458, '邓琳琳', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '邓琳琳', NULL, 2, NULL, '68822652-8089', '13585065081', '', '320281199502241782', NULL, NULL, '', '1995-02-24', NULL, NULL, '江苏省江阴市青阳镇塘头桥村西尖圩', NULL, b'0', '394', 5, '2017-06-01', '南京工程学院', '商务管理', '六级', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);
INSERT INTO `sys_user` VALUES (26459, '邓兴法', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '邓兴法', NULL, 1, NULL, '', '', '', '', NULL, NULL, '', '2017-04-29', NULL, NULL, '', NULL, b'1', NULL, 5, '2017-04-29', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);
INSERT INTO `sys_user` VALUES (26460, '丁惠娣', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '丁惠娣', NULL, 2, NULL, '68822652-8061', '13961649390', '', '320219197308205801', NULL, NULL, '1350911981', '1973-08-20', NULL, NULL, '江苏省江阴市南闸镇蔡泾村丁家村43号', NULL, b'0', '209', 4, '2015-12-03', '江苏电大', '会计', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);
INSERT INTO `sys_user` VALUES (26461, '丁纪恒', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '', NULL, 1, NULL, '68822652-8098', '13182279018', '', '', NULL, NULL, '', NULL, NULL, NULL, '', NULL, b'1', '438', 0, NULL, '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);
INSERT INTO `sys_user` VALUES (26462, '丁继娟', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '丁继娟', NULL, 2, NULL, '', '18762366305', '', '321025197612042629', NULL, NULL, '', '1976-12-04', NULL, NULL, '靖江市马桥镇侯河村陆家埭26-1号', NULL, b'1', NULL, 1, '2016-10-17', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);
INSERT INTO `sys_user` VALUES (26463, '丁建忠', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '丁建忠', NULL, 1, NULL, '18101528012', '13815983311', '', '', NULL, NULL, '', NULL, NULL, NULL, '', NULL, b'1', NULL, 5, NULL, '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);
INSERT INTO `sys_user` VALUES (26464, '丁静华', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '丁静华', NULL, 2, NULL, '', '15861033059', '', '321024197012132026', NULL, NULL, '', '1970-12-13', NULL, NULL, '江苏省靖江市靖城镇鼎新村夏盛圩37号', NULL, b'1', NULL, 0, '2015-05-25', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);
INSERT INTO `sys_user` VALUES (26465, '丁坤', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '丁坤', NULL, 1, NULL, '68822652-8073', '13656154945', '', '321283199112263015', NULL, NULL, '1761013513', '1991-12-26', NULL, NULL, '江苏省泰兴市曲霞镇戴窑村戴窑一组16号', NULL, b'0', '13', 5, '2015-11-09', '苏州大学应用技术学院', '服装设计与工程', '六级', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);
INSERT INTO `sys_user` VALUES (26466, '丁黎玮', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '丁黎玮', NULL, 2, NULL, '', '', '', '', NULL, NULL, '', NULL, NULL, NULL, '', NULL, b'1', NULL, 5, NULL, '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);
INSERT INTO `sys_user` VALUES (26467, '丁林', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '丁林', NULL, 1, NULL, '', '13656157801', '', '321283197409152819', NULL, NULL, '', '1974-09-15', NULL, NULL, '江苏省泰兴市河失镇刘庄村丁立九组26号', NULL, b'1', '227', 1, '2010-06-01', '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);
INSERT INTO `sys_user` VALUES (26468, '丁小军', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '丁小军', NULL, 1, NULL, '68822652-8071', '18952631757', '', '', NULL, NULL, '', NULL, NULL, NULL, '', NULL, b'1', NULL, 5, NULL, '', '', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);
INSERT INTO `sys_user` VALUES (26469, '丁友军', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', '丁友军', NULL, 1, NULL, '68822652-8080', '13395160520', '', '32098219790228041X', NULL, NULL, '394680024', '1979-02-28', NULL, NULL, '江苏省大丰市大中镇健康东路81号', NULL, b'0', '177', 3, '2013-01-02', '盐城纺校', '机织工程', '', b'0', NULL, 1, NULL, '2018-05-16 13:21:42', NULL, '2018-05-24 14:56:01', NULL);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id_` int(10) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `unit_id` bigint(20) NULL DEFAULT NULL COMMENT '隶属单位',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '部门名称',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上级部门编号',
  `sort_no` int(3) NULL DEFAULT NULL COMMENT '排序号',
  `leaf_` int(1) NULL DEFAULT NULL COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `shift_id` bigint(10) NULL DEFAULT NULL COMMENT '默认班次',
  `enable_` tinyint(1) NULL DEFAULT NULL COMMENT '启用状态',
  `remark_` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `dept_type` int(1) NULL DEFAULT NULL COMMENT '集团:0;子公司:1;分公司:2;事业部:3;办事处:4;部门:5;营业部:6;门店:7;工厂:8;项目:9;',
  `virtual` int(1) NULL DEFAULT NULL COMMENT '0:虚拟组织；1：实体组织',
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '部门' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 1, '总公司', 0, 1, 0, 3, 1, 'qw', 1, '2016-06-28 18:04:06', 1, '2018-07-13 16:52:30', 0, 1);
INSERT INTO `sys_dept` VALUES (6, NULL, 'IT', 15, 0, NULL, 3, 1, '', 1, '2018-04-04 16:58:32', 1, '2018-05-10 16:14:12', 5, 1);
INSERT INTO `sys_dept` VALUES (15, NULL, '嘉祥贸易', 1, 0, NULL, 3, NULL, '', 1, '2018-04-10 10:26:43', 1, '2018-05-10 11:55:16', 1, 1);
INSERT INTO `sys_dept` VALUES (34, NULL, '逐日科技', 1, 0, NULL, 3, NULL, '', 1, '2018-04-16 11:03:00', 1, '2018-05-10 11:58:43', 1, 1);
INSERT INTO `sys_dept` VALUES (35, NULL, '上海办事处', 1, 0, NULL, 3, NULL, '', 1, '2018-04-16 12:05:53', 1, '2018-05-10 12:06:37', 4, 1);
INSERT INTO `sys_dept` VALUES (37, NULL, 'QA', 15, 0, NULL, 3, NULL, '', 1, '2018-05-07 09:50:47', 1, '2018-05-07 09:51:33', 5, 1);
INSERT INTO `sys_dept` VALUES (38, NULL, 'QC', 15, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:51:44', 1, '2018-05-07 09:51:44', 5, 1);
INSERT INTO `sys_dept` VALUES (39, NULL, '版房', 15, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:52:04', 1, '2018-05-07 09:52:04', 5, 1);
INSERT INTO `sys_dept` VALUES (40, NULL, '财务', 15, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:52:14', 1, '2018-05-07 09:52:14', 5, 1);
INSERT INTO `sys_dept` VALUES (41, NULL, '单证', 15, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:52:36', 1, '2018-05-07 09:52:36', 5, 1);
INSERT INTO `sys_dept` VALUES (42, NULL, '辅料', 15, 0, NULL, 3, NULL, '', 1, '2018-05-07 09:52:47', 1, '2018-05-24 17:11:45', 5, 1);
INSERT INTO `sys_dept` VALUES (43, NULL, '工艺', 15, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:53:01', 1, '2018-05-07 09:53:01', 5, 1);
INSERT INTO `sys_dept` VALUES (44, NULL, '行政', 15, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:53:12', 1, '2018-05-07 09:53:12', 5, 1);
INSERT INTO `sys_dept` VALUES (45, NULL, '核料', 15, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:53:44', 1, '2018-05-07 09:53:44', 5, 1);
INSERT INTO `sys_dept` VALUES (46, NULL, '技术', 15, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:53:53', 1, '2018-05-07 09:53:53', 5, 1);
INSERT INTO `sys_dept` VALUES (47, NULL, '精品组', 15, 0, NULL, 3, NULL, '', 1, '2018-05-07 09:54:11', 1, '2018-05-24 16:17:55', 5, 1);
INSERT INTO `sys_dept` VALUES (48, NULL, '理单', 15, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:54:25', 1, '2018-05-07 09:54:25', 5, 1);
INSERT INTO `sys_dept` VALUES (49, NULL, '面料', 15, 0, NULL, 3, NULL, '', 1, '2018-05-07 09:54:38', 1, '2018-05-07 09:54:59', 5, 1);
INSERT INTO `sys_dept` VALUES (50, NULL, '品控', 15, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:55:28', 1, '2018-05-07 09:55:28', 5, 1);
INSERT INTO `sys_dept` VALUES (51, NULL, '前期理单', 15, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:55:41', 1, '2018-05-07 09:55:41', 5, 1);
INSERT INTO `sys_dept` VALUES (52, NULL, '人事部', 15, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:55:59', 1, '2018-05-07 09:55:59', 5, 1);
INSERT INTO `sys_dept` VALUES (53, NULL, '设计部', 15, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:56:09', 1, '2018-05-07 09:56:09', 5, 1);
INSERT INTO `sys_dept` VALUES (54, NULL, '生产', 15, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:56:21', 1, '2018-05-07 09:56:21', 5, 1);
INSERT INTO `sys_dept` VALUES (55, NULL, '样衣', 15, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:56:31', 1, '2018-05-07 09:56:31', 5, 1);
INSERT INTO `sys_dept` VALUES (56, NULL, '业务', 15, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:56:47', 1, '2018-05-07 09:56:47', 5, 1);
INSERT INTO `sys_dept` VALUES (57, NULL, '总裁室', 15, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:57:17', 1, '2018-05-07 09:57:17', 5, 1);
INSERT INTO `sys_dept` VALUES (58, NULL, '总经理', 57, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:57:48', 1, '2018-05-07 09:57:48', 5, 1);
INSERT INTO `sys_dept` VALUES (59, NULL, '总经理助理', 57, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:58:05', 1, '2018-05-07 09:58:05', 5, 1);
INSERT INTO `sys_dept` VALUES (60, NULL, '业务一组', 56, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:58:35', 1, '2018-05-07 09:58:35', 5, 1);
INSERT INTO `sys_dept` VALUES (61, NULL, '业务二组', 56, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:58:49', 1, '2018-05-07 09:58:49', 5, 1);
INSERT INTO `sys_dept` VALUES (62, NULL, '业务三组', 56, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:59:02', 1, '2018-05-07 09:59:02', 5, 1);
INSERT INTO `sys_dept` VALUES (63, NULL, '业务四组', 56, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:59:18', 1, '2018-05-07 09:59:18', 5, 1);
INSERT INTO `sys_dept` VALUES (64, NULL, '业务五组', 56, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:59:35', 1, '2018-05-07 09:59:35', 5, 1);
INSERT INTO `sys_dept` VALUES (65, NULL, '业务六组', 56, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 09:59:54', 1, '2018-05-07 09:59:54', 5, 1);
INSERT INTO `sys_dept` VALUES (66, NULL, '业务七组', 56, 0, NULL, 3, NULL, NULL, 1, '2018-05-07 10:00:09', 1, '2018-05-07 10:00:09', 5, 1);
INSERT INTO `sys_dept` VALUES (67, NULL, '业务开发', 56, 0, NULL, 3, NULL, '', 1, '2018-05-07 10:00:23', 1, '2018-05-09 08:13:03', 5, 1);
INSERT INTO `sys_dept` VALUES (75, NULL, '客户部', 35, 0, NULL, 3, NULL, NULL, 1, '2018-05-15 17:27:35', 1, '2018-05-15 17:27:35', 5, 1);

-- -- ----------------------------
-- -- Records of warehouseStockInPlan
-- -- ----------------------------

INSERT INTO `warehouseStockInPlan` VALUES (1,'plan001',1,'purchase001',1,99901,9901,'2018-08-20 10:00:00',NULL,66601,666010,3331,33100,3301,0,NULL);
INSERT INTO `warehouseStockInPlan` VALUES (2,'plan002',1,'purchase002',1,99901,9901,'2018-08-21 10:00:00',NULL,66601,666010,3331,33100,3301,0,NULL);
INSERT INTO `warehouseStockInPlan` VALUES (3,'plan003',1,'purchase003',1,99902,9902,'2018-08-22 10:00:00',NULL,66601,666010,3331,33100,3301,0,NULL);
INSERT INTO `warehouseStockInPlan` VALUES (4,'plan004',1,'purchase004',1,99901,9901,'2018-08-23 10:00:00',NULL,66602,666020,3332,33200,3302,0,NULL);
INSERT INTO `warehouseStockInPlan` VALUES (5,'plan005',1,'purchase005',1,99901,9901,'2018-08-24 10:00:00',NULL,66601,666010,3332,33200,3302,0,NULL);
INSERT INTO `warehouseStockInPlan` VALUES (6,'plan006',1,'purchase006',1,99901,9901,'2018-08-25 10:00:00',NULL,66601,666010,3332,33200,3302,0,NULL);
INSERT INTO `warehouseStockInPlan` VALUES (7,'plan007',1,'purchase007',1,99902,9902,'2018-08-26 10:00:00',NULL,66601,666010,3332,33200,3302,0,NULL);
INSERT INTO `warehouseStockInPlan` VALUES (8,'plan008',1,'purchase008',1,99901,9901,'2018-08-27 10:00:00',NULL,66601,666010,3332,33200,3302,0,NULL);
INSERT INTO `warehouseStockInPlan` VALUES (9,'plan009',1,'purchase009',1,99902,9902,'2018-08-28 10:00:00',NULL,66601,666010,3333,33300,3302,0,NULL);
INSERT INTO `warehouseStockInPlan` VALUES (10,'plan010',1,'purchase010',1,99901,9901,'2018-08-29 10:00:00',NULL,66601,666010,3333,33300,3303,0,NULL);
INSERT INTO `warehouseStockInPlan` VALUES (11,'plan011',1,'purchase011',1,99901,9901,'2018-08-30 10:00:00',NULL,66601,666010,3333,33300,3303,0,NULL);
INSERT INTO `warehouseStockInPlan` VALUES (12,'plan012',1,'purchase012',1,99903,9903,'2018-08-31 10:00:00',NULL,66602,666020,3333,33300,3303,0,NULL);
INSERT INTO `warehouseStockInPlan` VALUES (13,'plan013',1,'purchase013',1,99902,9902,'2018-09-01 10:00:00',NULL,66602,666020,3333,33300,3303,0,NULL);
INSERT INTO `warehouseStockInPlan` VALUES (14,'plan014',1,'purchase014',1,99903,9903,'2018-09-02 10:00:00',NULL,66602,666020,3333,33300,3303,0,NULL);

-- -- ----------------------------
-- -- Records of warehouseStockInPlanDetail
-- -- ----------------------------
-- INSERT INTO `warehouseStockInPlanDetail` VALUES (1,'plan001','m00010',1,200,0,20,25,0,NULL);
-- INSERT INTO `warehouseStockInPlanDetail` VALUES (2,'plan002','m00020',1,300,0,25,28,0,NULL);
-- INSERT INTO `warehouseStockInPlanDetail` VALUES (3,'plan003','m00030',1,200,0,30,33,0,NULL);
-- INSERT INTO `warehouseStockInPlanDetail` VALUES (4,'plan004','m00040',1,300,0,32,35,0,NULL);
-- INSERT INTO `warehouseStockInPlanDetail` VALUES (5,'plan005','m00050',1,200,0,33,37,0,NULL);
-- INSERT INTO `warehouseStockInPlanDetail` VALUES (6,'plan006','m00060',1,300,0,40,43,0,NULL);
-- INSERT INTO `warehouseStockInPlanDetail` VALUES (7,'plan007','m00070',1,200,0,45,48,0,NULL);
-- INSERT INTO `warehouseStockInPlanDetail` VALUES (8,'plan008','m00080',1,300,0,22,26,0,NULL);
-- INSERT INTO `warehouseStockInPlanDetail` VALUES (9,'plan009','m00090',1,200,0,21,27,0,NULL);
-- INSERT INTO `warehouseStockInPlanDetail` VALUES (10,'plan010','m00100',1,300,0,67,70,0,NULL);
-- INSERT INTO `warehouseStockInPlanDetail` VALUES (11,'plan010','m00101',1,300,0,50,55,0,NULL);
-- INSERT INTO `warehouseStockInPlanDetail` VALUES (12,'plan010','m00102',1,200,0,60,65,0,NULL);

INSERT INTO `warehouseStockInPlanDetail` VALUES (1,'plan001','11101',1,20,0,500000,520000,0,NULL);
INSERT INTO `warehouseStockInPlanDetail` VALUES (2,'plan001','11102',1,30,0,200000,210000,0,NULL);
INSERT INTO `warehouseStockInPlanDetail` VALUES (3,'plan002','21101',2,100,0,10000,10500,0,NULL);
INSERT INTO `warehouseStockInPlanDetail` VALUES (4,'plan002','21102',2,150,0,10000,10500,0,NULL);
INSERT INTO `warehouseStockInPlanDetail` VALUES (5,'plan003','31101',3,200,0,8000,8200,0,NULL);
INSERT INTO `warehouseStockInPlanDetail` VALUES (6,'plan003','31102',3,100,0,6000,6150,0,NULL);
INSERT INTO `warehouseStockInPlanDetail` VALUES (7,'plan004','31101',4,200,0,8100,8500,0,NULL);
INSERT INTO `warehouseStockInPlanDetail` VALUES (8,'plan004','31102',4,100,0,6100,6400,0,NULL);
INSERT INTO `warehouseStockInPlanDetail` VALUES (9,'plan005','31102',4,100,0,6100,6400,0,NULL);