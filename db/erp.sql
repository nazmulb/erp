/*
SQLyog Community Edition- MySQL GUI v5.20
Host - 5.5.8 : Database - erp
*********************************************************************
Server version : 5.5.8
*/

SET NAMES utf8;

SET SQL_MODE='';

create database if not exists `erp`;

USE `erp`;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

/*Table structure for table `tbl_customer` */

DROP TABLE IF EXISTS `tbl_customer`;

CREATE TABLE `tbl_customer` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '0 - Inactive, 1 - Active',
  PRIMARY KEY (`cid`),
  KEY `status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_customer` */

/*Table structure for table `tbl_invoice` */

DROP TABLE IF EXISTS `tbl_invoice`;

CREATE TABLE `tbl_invoice` (
  `invoice_id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `invoice_date` date DEFAULT NULL,
  `reference_no` varchar(255) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `vat` double DEFAULT NULL,
  `grand_total` double DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0' COMMENT '0 - Not Deliver, 1 - Delivered',
  PRIMARY KEY (`invoice_id`),
  KEY `fk_tbl_invoice_tbl_customer1_idx` (`cid`),
  CONSTRAINT `fk_tbl_invoice_tbl_customer1` FOREIGN KEY (`cid`) REFERENCES `tbl_customer` (`cid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_invoice` */

/*Table structure for table `tbl_invoice_details` */

DROP TABLE IF EXISTS `tbl_invoice_details`;

CREATE TABLE `tbl_invoice_details` (
  `invoice_det_id` int(11) NOT NULL AUTO_INCREMENT,
  `invoice_id` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0' COMMENT '0 - Not Deliver, 1 - Delivered',
  PRIMARY KEY (`invoice_det_id`),
  KEY `fk_tbl_invoice_details_tbl_invoice1_idx` (`invoice_id`),
  KEY `fk_tbl_invoice_details_tbl_product1_idx` (`pid`),
  CONSTRAINT `fk_tbl_invoice_details_tbl_invoice1` FOREIGN KEY (`invoice_id`) REFERENCES `tbl_invoice` (`invoice_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_invoice_details_tbl_product1` FOREIGN KEY (`pid`) REFERENCES `tbl_product` (`pid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_invoice_details` */

/*Table structure for table `tbl_product` */

DROP TABLE IF EXISTS `tbl_product`;

CREATE TABLE `tbl_product` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `current_stock` double DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `product_type` tinyint(4) DEFAULT '1' COMMENT '1 - Raw Materials, 2 - Finish Goods',
  PRIMARY KEY (`pid`),
  KEY `product_type` (`product_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_product` */

/*Table structure for table `tbl_product_out` */

DROP TABLE IF EXISTS `tbl_product_out`;

CREATE TABLE `tbl_product_out` (
  `pout_id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `rec_id` int(11) DEFAULT NULL,
  `req_det_id` int(11) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `out_date` datetime DEFAULT NULL,
  PRIMARY KEY (`pout_id`),
  KEY `fk_tbl_product_out_tbl_product1_idx` (`pid`),
  KEY `fk_tbl_product_out_tbl_product_rec1_idx` (`rec_id`),
  KEY `fk_tbl_product_out_tbl_product_req_details1_idx` (`req_det_id`),
  CONSTRAINT `fk_tbl_product_out_tbl_product1` FOREIGN KEY (`pid`) REFERENCES `tbl_product` (`pid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_product_out_tbl_product_rec1` FOREIGN KEY (`rec_id`) REFERENCES `tbl_product_rec` (`rec_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_product_out_tbl_product_rec2` FOREIGN KEY (`rec_id`) REFERENCES `tbl_product_rec` (`rec_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_product_out_tbl_product_req_details1` FOREIGN KEY (`req_det_id`) REFERENCES `tbl_product_req_details` (`req_det_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_product_out` */

/*Table structure for table `tbl_product_purchase_req` */

DROP TABLE IF EXISTS `tbl_product_purchase_req`;

CREATE TABLE `tbl_product_purchase_req` (
  `pur_req_id` int(11) NOT NULL AUTO_INCREMENT,
  `pur_req_date` date DEFAULT NULL,
  `pur_req_by` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '0 - Not Receive, 1 - Received',
  PRIMARY KEY (`pur_req_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_product_purchase_req` */

/*Table structure for table `tbl_product_purchase_req_details` */

DROP TABLE IF EXISTS `tbl_product_purchase_req_details`;

CREATE TABLE `tbl_product_purchase_req_details` (
  `pur_req_det_id` int(11) NOT NULL AUTO_INCREMENT,
  `pur_req_id` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  PRIMARY KEY (`pur_req_det_id`),
  KEY `fk_tbl_product_req_details_tbl_product1_idx` (`pid`),
  KEY `fk_tbl_product_req_details_tbl_product_req1_idx` (`pur_req_id`),
  CONSTRAINT `fk_tbl_product_req_details_tbl_product1` FOREIGN KEY (`pid`) REFERENCES `tbl_product` (`pid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_product_req_details_tbl_product_req1` FOREIGN KEY (`pur_req_id`) REFERENCES `tbl_product_purchase_req` (`pur_req_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_product_purchase_req_details` */

/*Table structure for table `tbl_product_rec` */

DROP TABLE IF EXISTS `tbl_product_rec`;

CREATE TABLE `tbl_product_rec` (
  `rec_id` int(11) NOT NULL AUTO_INCREMENT,
  `pur_req_det_id` int(11) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `rec_date` datetime DEFAULT NULL,
  `qty_disburse` double DEFAULT NULL,
  PRIMARY KEY (`rec_id`),
  KEY `fk_tbl_product_rec_tbl_product_purchase_req_details1_idx` (`pur_req_det_id`),
  CONSTRAINT `fk_tbl_product_rec_tbl_product_purchase_req_details1` FOREIGN KEY (`pur_req_det_id`) REFERENCES `tbl_product_purchase_req_details` (`pur_req_det_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_product_rec` */

/*Table structure for table `tbl_product_req` */

DROP TABLE IF EXISTS `tbl_product_req`;

CREATE TABLE `tbl_product_req` (
  `req_id` int(11) NOT NULL AUTO_INCREMENT,
  `req_date` date DEFAULT NULL,
  `req_by` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '0 - Not Issue, 1 - Issued',
  `req_required_date` date DEFAULT NULL,
  PRIMARY KEY (`req_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_product_req` */

/*Table structure for table `tbl_product_req_details` */

DROP TABLE IF EXISTS `tbl_product_req_details`;

CREATE TABLE `tbl_product_req_details` (
  `req_det_id` int(11) NOT NULL AUTO_INCREMENT,
  `req_id` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  PRIMARY KEY (`req_det_id`),
  KEY `fk_tbl_product_req_details_tbl_product_req2_idx` (`req_id`),
  KEY `fk_tbl_product_req_details_tbl_product2_idx` (`pid`),
  CONSTRAINT `fk_tbl_product_req_details_tbl_product_req2` FOREIGN KEY (`req_id`) REFERENCES `tbl_product_req` (`req_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_product_req_details_tbl_product2` FOREIGN KEY (`pid`) REFERENCES `tbl_product` (`pid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_product_req_details` */

/*Table structure for table `tbl_user` */

DROP TABLE IF EXISTS `tbl_user`;

CREATE TABLE `tbl_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(100) NOT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '0 - Inactive, 1 - Active',
  PRIMARY KEY (`uid`),
  KEY `status` (`status`),
  KEY `uname` (`uname`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_user` */

insert  into `tbl_user`(`uid`,`uname`,`first_name`,`last_name`,`password`,`email`,`phone`,`image`,`status`) values (1,'admin','Nazmul','Basher','92668751','nazmul.basher@fieldnation.com','+8801712994064',NULL,1),(2,'alamin','Al','Amin','-1415201540','enalamin@gmail.com','+8801677073455',NULL,1);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
