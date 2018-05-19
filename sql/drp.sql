-- MySQL dump 10.13  Distrib 5.7.21, for macos10.13 (x86_64)
--
-- Host: localhost    Database: drp
-- ------------------------------------------------------
-- Server version	5.7.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin_user`
--

DROP TABLE IF EXISTS `admin_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_user` (
  `id` varchar(50) NOT NULL,
  `account` varchar(45) DEFAULT '' COMMENT '登录账号',
  `password` varchar(80) DEFAULT '' COMMENT '登录密码',
  `shop_id` int(45) DEFAULT NULL COMMENT '门店id',
  `name` varchar(200) DEFAULT NULL COMMENT '名字',
  `mobile` varchar(200) DEFAULT NULL COMMENT '电话号码',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `status` varchar(45) DEFAULT '' COMMENT '状态：是否可用',
  `create_by` varchar(45) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` varchar(45) NOT NULL,
  `update_time` datetime NOT NULL,
  `salt` varchar(512) DEFAULT NULL COMMENT '盐值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_user`
--

LOCK TABLES `admin_user` WRITE;
/*!40000 ALTER TABLE `admin_user` DISABLE KEYS */;
INSERT INTO `admin_user` VALUES ('1','admin','b2e81fcd59a92638e9a013065574de2d',1,'管理员','123456789',1,'0','1','2018-01-01 00:00:00','1','2018-01-01 00:00:00','fd7e8d21972eb2f668234a7ecc37d84f');
/*!40000 ALTER TABLE `admin_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(45) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) DEFAULT NULL COMMENT '门店Id',
  `name` varchar(45) DEFAULT '' COMMENT '名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '描述',
  `status` int(1) DEFAULT NULL COMMENT '状态：0，1',
  `create_by` varchar(45) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(45) DEFAULT '',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `shop_id` int(11) DEFAULT NULL COMMENT '门店Id',
  `name` varchar(45) DEFAULT '' COMMENT '商品名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '描述',
  `cate_id` varchar(45) DEFAULT '' COMMENT '售卖类目id',
  `sale_price` decimal(20,2) DEFAULT NULL COMMENT '售卖价格',
  `original_price` decimal(20,2) DEFAULT NULL COMMENT '成本价格',
  `delete_tag` char(1) DEFAULT '' COMMENT '删除标记',
  `status` varchar(45) DEFAULT '' COMMENT '上下架状态 WAIT/ON/OFF',
  `create_by` varchar(45) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(45) DEFAULT '',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `goods_stock`
--

DROP TABLE IF EXISTS `goods_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods_stock` (
  `id` varchar(45) NOT NULL,
  `shop_id` int(11) NOT NULL COMMENT '门店Id',
  `goods_id` varchar(50) NOT NULL DEFAULT '' COMMENT '商品Id',
  `original_stock` int(11) NOT NULL COMMENT '原始库存',
  `in_quantity` int(11) NOT NULL COMMENT '进货',
  `out_quentity` int(11) NOT NULL COMMENT '出货',
  `current_stock` int(11) NOT NULL COMMENT '当前库存',
  `status` int(1) NOT NULL COMMENT '状态',
  `create_by` varchar(45) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` varchar(45) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `goods_stock_log`
--

DROP TABLE IF EXISTS `goods_stock_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods_stock_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) DEFAULT NULL COMMENT '门店Id',
  `goods_id` varchar(50) DEFAULT NULL COMMENT '商品Id',
  `previous_stock` int(11) DEFAULT NULL COMMENT '原始库存',
  `quantity` int(11) DEFAULT NULL COMMENT '进货',
  `current_stock` int(11) DEFAULT NULL COMMENT '当前库存',
  `type` varchar(45) DEFAULT '' COMMENT '类型：in 进货| out 出库',
  `create_by` varchar(45) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(45) DEFAULT '',
  `update_time` datetime DEFAULT NULL,
  `number` varchar(45) DEFAULT NULL COMMENT '订单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名字',
  `url` varchar(100) DEFAULT NULL COMMENT 'url地址',
  `level` int(11) DEFAULT NULL COMMENT '序号',
  `parent` int(11) DEFAULT NULL COMMENT '上级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'系统管理','#',1,NULL),(2,'门店管理','/shop/index.do',2,1),(3,'岗位管理','/role/index.do',3,1),(4,'员工管理','/adminUser/index.do',4,1),(5,'操作日志','/operationLog/index.do',5,1),(6,'图书管理','#',6,NULL),(7,'图书分类管理','/category/index.do',7,6),(8,'图书基本信息管理','/goods/index.do',8,6),(9,'图书采购管理','#',9,NULL),(10,'图书采购订单管理','/order/index.do',10,9),(11,'图书采购退货管理','/order/return.do',11,9),(12,'图书销售管理','#',12,NULL),(13,'图书销售管理','/order/sale.do',13,12),(14,'图书退货管理','/order/ret.do',14,12),(15,'图书库存管理','#',15,NULL),(16,'图书入库管理','/order/storage.do',16,15),(17,'图书出库管理','/order/out.do',17,15),(18,'财务管理','#',18,NULL),(19,'图书进销存对账','/goodsStock/index.do',19,18);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation_log`
--

DROP TABLE IF EXISTS `operation_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_user_id` varchar(45) DEFAULT NULL COMMENT '用户Id',
  `shop_id` int(45) DEFAULT NULL COMMENT '门店Id',
  `desc_code` varchar(512) DEFAULT NULL COMMENT '描述',
  `create_by` varchar(45) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id` varchar(45) NOT NULL,
  `shop_id` int(11) DEFAULT NULL,
  `user_id` varchar(45) DEFAULT NULL COMMENT '采购时间',
  `number` varchar(45) DEFAULT NULL COMMENT '流水号',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `status` int(5) DEFAULT NULL COMMENT '状态',
  `out_time` datetime DEFAULT NULL COMMENT '出库时间',
  `in_time` datetime DEFAULT NULL COMMENT '入库时间',
  `create_by` varchar(45) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(45) DEFAULT '',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order_goods`
--

DROP TABLE IF EXISTS `order_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_goods` (
  `id` varchar(45) NOT NULL,
  `order_id` varchar(45) NOT NULL DEFAULT '' COMMENT '订单Id',
  `goods_id` varchar(45) NOT NULL DEFAULT '' COMMENT '商品Id',
  `num` int(11) NOT NULL COMMENT '原始库存',
  `create_by` varchar(45) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` varchar(45) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `relation`
--

DROP TABLE IF EXISTS `relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relation`
--

LOCK TABLES `relation` WRITE;
/*!40000 ALTER TABLE `relation` DISABLE KEYS */;
INSERT INTO `relation` VALUES (1,1,1);
/*!40000 ALTER TABLE `relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '名字',
  `status` varchar(45) DEFAULT '' COMMENT '状态：是否可用',
  `create_by` varchar(45) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` varchar(45) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='岗位表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin','0','1','2018-01-01 00:00:00','1','2018-01-01 00:00:00');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop` (
  `shop_num` int(11) NOT NULL,
  `name` varchar(45) DEFAULT '' COMMENT '门店名称',
  `phone` varchar(45) DEFAULT '' COMMENT '电话',
  `area` varchar(45) DEFAULT NULL COMMENT '地区',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `remark` varchar(200) DEFAULT NULL COMMENT '描述',
  `status` varchar(45) DEFAULT '' COMMENT '状态：是否可用',
  `create_by` varchar(45) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` varchar(45) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`shop_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-01 00:00:00
