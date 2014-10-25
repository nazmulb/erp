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

/*Table structure for table `tbl_product` */

DROP TABLE IF EXISTS `tbl_product`;

CREATE TABLE `tbl_product` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'xzxz',
  `name` varchar(255) NOT NULL,
  `current_stock` double DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_product` */

insert  into `tbl_product`(`pid`,`name`,`current_stock`,`rate`,`unit`) values (1,'Pen',90,12,'pcs'),(2,'Pencil',48,5,'pcs');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_product_out` */

insert  into `tbl_product_out`(`pout_id`,`pid`,`rec_id`,`req_det_id`,`qty`,`rate`,`out_date`) values (1,1,1,1,10,12,'2014-10-21 12:25:16'),(2,2,2,2,2,5,'2014-10-21 12:25:40');

/*Table structure for table `tbl_product_purchase_req` */

DROP TABLE IF EXISTS `tbl_product_purchase_req`;

CREATE TABLE `tbl_product_purchase_req` (
  `pur_req_id` int(11) NOT NULL AUTO_INCREMENT,
  `pur_req_date` date DEFAULT NULL,
  `pur_req_by` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`pur_req_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_product_purchase_req` */

insert  into `tbl_product_purchase_req`(`pur_req_id`,`pur_req_date`,`pur_req_by`,`status`) values (1,'2014-10-21',1,1);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_product_purchase_req_details` */

insert  into `tbl_product_purchase_req_details`(`pur_req_det_id`,`pur_req_id`,`pid`,`qty`) values (1,1,1,100),(2,1,2,50);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_product_rec` */

insert  into `tbl_product_rec`(`rec_id`,`pur_req_det_id`,`qty`,`rate`,`rec_date`,`qty_disburse`) values (1,1,100,12,'2014-10-21 12:23:20',10),(2,2,50,5,'2014-10-21 12:23:48',2);

/*Table structure for table `tbl_product_req` */

DROP TABLE IF EXISTS `tbl_product_req`;

CREATE TABLE `tbl_product_req` (
  `req_id` int(11) NOT NULL AUTO_INCREMENT,
  `req_date` date DEFAULT NULL,
  `req_by` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `req_required_date` date DEFAULT NULL,
  PRIMARY KEY (`req_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_product_req` */

insert  into `tbl_product_req`(`req_id`,`req_date`,`req_by`,`status`,`req_required_date`) values (1,'2014-10-21',1,1,'2014-10-22');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_product_req_details` */

insert  into `tbl_product_req_details`(`req_det_id`,`req_id`,`pid`,`qty`) values (1,1,1,10),(2,1,2,2);

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
  `status` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_user` */

insert  into `tbl_user`(`uid`,`uname`,`first_name`,`last_name`,`password`,`email`,`phone`,`image`,`status`) values (1,'admin','Nazmul','Basher','92668751','nazmul.basher@fieldnation.com','+8801712994064',NULL,1),(2,'alamin','Al','Amin','-1415201540','enalamin@gmail.com','+8801677073455',NULL,1);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
