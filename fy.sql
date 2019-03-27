/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.61 : Database - fydb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fydb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `fydb`;

/*Table structure for table `tb_admin` */

DROP TABLE IF EXISTS `tb_admin`;

CREATE TABLE `tb_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `openID` char(32) DEFAULT NULL COMMENT '管理员openID',
  `username` varchar(15) NOT NULL COMMENT '登录名',
  `password` varchar(15) NOT NULL COMMENT '登陆密码',
  `realname` varchar(10) DEFAULT NULL COMMENT '管理员真实姓名',
  `flag` tinyint(1) DEFAULT '0' COMMENT '是否激活',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='管理员表';

/*Data for the table `tb_admin` */

insert  into `tb_admin`(`id`,`openID`,`username`,`password`,`realname`,`flag`) values (1,'test','admin','123456','小王',1),(2,NULL,'admin2','123456','老王',0);

/*Table structure for table `tb_admin_model` */

DROP TABLE IF EXISTS `tb_admin_model`;

CREATE TABLE `tb_admin_model` (
  `adminID` int(11) NOT NULL,
  `modelID` int(11) NOT NULL,
  KEY `adminID` (`adminID`),
  KEY `modelID` (`modelID`),
  CONSTRAINT `tb_admin_model_ibfk_1` FOREIGN KEY (`adminID`) REFERENCES `tb_admin` (`id`),
  CONSTRAINT `tb_admin_model_ibfk_2` FOREIGN KEY (`modelID`) REFERENCES `tb_model` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员与模块关联表';

/*Data for the table `tb_admin_model` */

insert  into `tb_admin_model`(`adminID`,`modelID`) values (1,1);

/*Table structure for table `tb_choose` */

DROP TABLE IF EXISTS `tb_choose`;

CREATE TABLE `tb_choose` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stuOpenID` char(32) NOT NULL COMMENT '学员编号',
  `teaOpenID` char(32) NOT NULL COMMENT '导师编号',
  `reason` varchar(500) DEFAULT NULL COMMENT '选择理由',
  `flag` tinyint(2) DEFAULT '0' COMMENT '0等待老师选择 1面试 2被选 3未选',
  `star` tinyint(1) DEFAULT NULL COMMENT '导师对学员的打分',
  `remark` varchar(500) DEFAULT NULL COMMENT '导师对学生的评语',
  `overTime` date DEFAULT NULL COMMENT '私塾到期时间',
  `overFlag` tinyint(1) DEFAULT '0' COMMENT '私塾是否到期',
  PRIMARY KEY (`id`),
  KEY `stuOpenID` (`stuOpenID`),
  CONSTRAINT `tb_choose_ibfk_1` FOREIGN KEY (`stuOpenID`) REFERENCES `tb_student` (`stuOpenID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='双向选择表';

/*Data for the table `tb_choose` */

insert  into `tb_choose`(`id`,`stuOpenID`,`teaOpenID`,`reason`,`flag`,`star`,`remark`,`overTime`,`overFlag`) values (4,'ohYXK1aTYFUk_rm8KRx2yFbGc_4Q','ohYXK1fe9OPUxu5xw0rLDPWHoN_A','我爱你',2,NULL,NULL,'2019-02-12',1),(5,'ohYXK1c3XAQYTW7aTFU3cM59WqPc','test','aaaaaa',0,NULL,NULL,NULL,0);

/*Table structure for table `tb_guide` */

DROP TABLE IF EXISTS `tb_guide`;

CREATE TABLE `tb_guide` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `img` varchar(255) DEFAULT NULL COMMENT '头部图片',
  `flag` tinyint(1) DEFAULT NULL COMMENT '0学员 1导师',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='会员/导师信息填写页';

/*Data for the table `tb_guide` */

insert  into `tb_guide`(`id`,`title`,`content`,`img`,`flag`) values (3,'会员登记','亲爱的【赋羽青年】会员：\r\n\r\n       感谢你选择【赋羽青年】为你的青春助力！\r\n\r\n       完成所有资料的填写需要大致半小时的时间，请你挑选一个整段的时间认真填写，谢谢！\r\n\r\n       我们将会依据你所填写的资料为你推荐导师及活动，你心仪的导师也将依据本资料进行学徒遴选。\r\n\r\n       如果你的信息发生更改，请及时重新填写。',NULL,0);

/*Table structure for table `tb_model` */

DROP TABLE IF EXISTS `tb_model`;

CREATE TABLE `tb_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '模块编号',
  `name` varchar(15) NOT NULL COMMENT '模块名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='模块管理表';

/*Data for the table `tb_model` */

insert  into `tb_model`(`id`,`name`) values (1,'二维码'),(2,'订单');

/*Table structure for table `tb_order` */

DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
  `outTradeNo` char(32) DEFAULT NULL COMMENT '订单号',
  `transactionId` char(28) DEFAULT NULL COMMENT '微信订单号',
  `openID` char(32) DEFAULT NULL COMMENT 'openID',
  `totalFee` int(11) DEFAULT NULL COMMENT '付款金额(单位：分)',
  `createTime` datetime DEFAULT NULL COMMENT '付款时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

/*Data for the table `tb_order` */

insert  into `tb_order`(`outTradeNo`,`transactionId`,`openID`,`totalFee`,`createTime`) values ('132','132','132',1000,'2019-03-01 15:42:18'),('7897987987897987987987','56456456456','456456456456',1000,'2019-03-01 00:00:00'),('213213','21321321','3213213',111,'2019-03-01 00:00:00');

/*Table structure for table `tb_qr_code` */

DROP TABLE IF EXISTS `tb_qr_code`;

CREATE TABLE `tb_qr_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '二维码编号',
  `title` varchar(30) DEFAULT NULL COMMENT '二维码标题',
  `remark` varchar(100) DEFAULT NULL COMMENT '二维码备注',
  `qrCode` varchar(255) DEFAULT NULL COMMENT '二维码',
  `expires` date DEFAULT NULL COMMENT '二维码到期时间',
  `openID` char(32) DEFAULT NULL COMMENT '所属导师',
  `flag` tinyint(1) DEFAULT NULL COMMENT '0会员群 1导师会员群 2导师面试群',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='二维码表';

/*Data for the table `tb_qr_code` */

insert  into `tb_qr_code`(`id`,`title`,`remark`,`qrCode`,`expires`,`openID`,`flag`) values (1,'赋羽青年会员群','进群失败？请联系15696669464','qrCode/1c39554929104a74b37eaa0004993cfe.jpg','2019-02-19','test',0),(2,'传一个二维码','这是测试的','qrCode/1548336573048.png','2019-01-30',NULL,1),(3,'巴巴爸爸啵','巴巴爸爸啵','qrCode/3a034c3c2c7e4e04aef12858184ebf6d.sql','2019-03-08','ohYXK1aTYFUk_rm8KRx2yFbGc_4Q',0);

/*Table structure for table `tb_rich_text` */

DROP TABLE IF EXISTS `tb_rich_text`;

CREATE TABLE `tb_rich_text` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '富文本编号',
  `content` text COMMENT '富文本内容',
  `flag` tinyint(1) DEFAULT NULL COMMENT '富文本分类 0学员页面 1导师页面 2导师',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='富文本表';

/*Data for the table `tb_rich_text` */

insert  into `tb_rich_text`(`id`,`content`,`flag`) values (1,'<ul>                         <li style=\"display: flex;justify-content: center;align-items: center; padding: 10px;font-size: 14px;\">                             <div style=\"flex: 1;\">                                 <img src=\"../../../../static/img/免费.png\" alt=\"\">                             </div>                             <div style=\"flex: 5;line-height: 1.5;color: #999999;\">                                 <h6 style=\"font-size: 16px; color: #000; font-weight: 500;\">本微客主页免费收听</h6>                                 <p>                                     <span style=\"color: #f1e2cc;\">本微客主页所有课程免费收听</span>                                     ，优质好课不断更新，支持有限期内无限回听(转播课和加密课除外)                                     </p>                             </div>                         </li>                         <li style=\"display: flex;justify-content: center;align-items: center; padding: 10px;font-size: 14px;\">                             <div style=\"flex: 1;\">                                 <img src=\"../../../../static/img/vip.png\" alt=\"\">                             </div>                             <div style=\"flex: 5;line-height: 1.5;color: #999999;\">                                 <h6 style=\"font-size: 16px; color: #000; font-weight: 500;\">尊贵标识</h6>                                 <p>                                     VIP会员在微课主页尽显尊贵身份，课程内容无限畅享                                 </p>                             </div>                         </li>                     </ul>',0),(2,'<div style=\"padding: 15px; font-size: 14px;\">                                 <p>1. 私塾学习：进入优质导师圈，向心仪的导师投递简历，获得两个月的私塾学习机会</p>                                 <p>2. 线上大咖课：会员能免费参与资深行业大咖线上职场课程。</p>                                 <p>3. 终身会员：如完成学习任务需求，可申请次年免年费续会员。（相当于一次付费，终身会员资格）</p>                                 <p>【温馨提示】付费成为会员后请填写赋羽青年会员登记表</p>                             </div>',1),(3,'<ul>                     <li>                         <div class=\"vip-icon\">                             <img src=\"../../../../static/icon/fyPay/images/icon/免费.png\" srcset=\"../../../../static/icon/fyPay/images/icon/免费@2x.png 2x, ../../../../static/icon/fyPay/images/icon/免费@3x.png 3x\" alt=\"\">                         </div>                         <div class=\"vip-content\">                             <h6>本微客主页免费收听</h6>                             <p>                                 <span class=\"im\">本微客主页所有课程免费收听</span>                                 ，优质好课不断更新，支持有限期内无限回听(转播课和加密课除外)                                 </p>                         </div>                     </li>                     <li>                         <div class=\"vip-icon\">                             <img src=\"../../../../static/icon/fyPay/images/icon/vip.png\" srcset=\"../../../../static/icon/fyPay/images/icon/vip@2x.png 2x,../../../../static/icon/fyPay/images/icon/vip@3x.png 3x\" alt=\"\">                         </div>                         <div class=\"vip-content\">                             <h6>尊贵标识</h6>                             <p>                                 VIP会员在微课主页尽显尊贵身份，课程内容无限畅享                             </p>                         </div>                     </li>                 </ul>',2),(4,'<ul>                     <li>                         <div class=\"vip-icon\">                             <img src=\"../../../../static/icon/fyPay/images/icon/免费.png\" srcset=\"../../../../static/icon/fyPay/images/icon/免费@2x.png 2x, ../../../../static/icon/fyPay/images/icon/免费@3x.png 3x\" alt=\"\">                         </div>                         <div class=\"vip-content\">                             <h6>本微客主页免费收听</h6>                             <p>                                 <span class=\"im\">本微客主页所有课程免费收听</span>                                 ，优质好课不断更新，支持有限期内无限回听(转播课和加密课除外)                                 </p>                         </div>                     </li>                     <li>                         <div class=\"vip-icon\">                             <img src=\"../../../../static/icon/fyPay/images/icon/vip.png\" srcset=\"../../../../static/icon/fyPay/images/icon/vip@2x.png 2x,../../../../static/icon/fyPay/images/icon/vip@3x.png 3x\" alt=\"\">                         </div>                         <div class=\"vip-content\">                             <h6>尊贵标识</h6>                             <p>                                 VIP会员在微课主页尽显尊贵身份，课程内容无限畅享                             </p>                         </div>                     </li>                 </ul>',2),(5,'<ul>                     <li>                         <div class=\"vip-icon\">                             <img src=\"../../../../static/icon/fyPay/images/icon/免费.png\" srcset=\"../../../../static/icon/fyPay/images/icon/免费@2x.png 2x, ../../../../static/icon/fyPay/images/icon/免费@3x.png 3x\" alt=\"\">                         </div>                         <div class=\"vip-content\">                             <h6>本微客主页免费收听</h6>                             <p>                                 <span class=\"im\">本微客主页所有课程免费收听</span>                                 ，优质好课不断更新，支持有限期内无限回听(转播课和加密课除外)                                 </p>                         </div>                     </li>                     <li>                         <div class=\"vip-icon\">                             <img src=\"../../../../static/icon/fyPay/images/icon/vip.png\" srcset=\"../../../../static/icon/fyPay/images/icon/vip@2x.png 2x,../../../../static/icon/fyPay/images/icon/vip@3x.png 3x\" alt=\"\">                         </div>                         <div class=\"vip-content\">                             <h6>尊贵标识</h6>                             <p>                                 VIP会员在微课主页尽显尊贵身份，课程内容无限畅享                             </p>                         </div>                     </li>                 </ul>',2),(6,'<ul>                         <li style=\"display: flex;justify-content: center;align-items: center; padding: 10px;font-size: 14px;\">                             <div style=\"flex: 1;\">                                 <img src=\"../../../../static/img/免费.png\" alt=\"\">                             </div>                             <div style=\"flex: 5;line-height: 1.5;color: #999999;\">                                 <h6 style=\"font-size: 16px; color: #000; font-weight: 500;\">本微客主页免费收听</h6>                                 <p>                                     <span style=\"color: #f1e2cc;\">本微客主页所有课程免费收听</span>                                     ，优质好课不断更新，支持有限期内无限回听(转播课和加密课除外)                                     </p>                             </div>                         </li>                         <li style=\"display: flex;justify-content: center;align-items: center; padding: 10px;font-size: 14px;\">                             <div style=\"flex: 1;\">                                 <img src=\"../../../../static/img/vip.png\" alt=\"\">                             </div>                             <div style=\"flex: 5;line-height: 1.5;color: #999999;\">                                 <h6 style=\"font-size: 16px; color: #000; font-weight: 500;\">尊贵标识</h6>                                 <p>                                     VIP会员在微课主页尽显尊贵身份，课程内容无限畅享                                 </p>                             </div>                         </li>                     </ul>',0),(7,'<div style=\"padding: 15px; font-size: 14px;\">                                 <p>1. 私塾学习：进入优质导师圈，向心仪的导师投递简历，获得两个月的私塾学习机会</p>                                 <p>2. 线上大咖课：会员能免费参与资深行业大咖线上职场课程。</p>                                 <p>3. 终身会员：如完成学习任务需求，可申请次年免年费续会员。（相当于一次付费，终身会员资格）</p>                                 <p>【温馨提示】付费成为会员后请填写赋羽青年会员登记表</p>                             </div>',0);

/*Table structure for table `tb_stu_ticket` */

DROP TABLE IF EXISTS `tb_stu_ticket`;

CREATE TABLE `tb_stu_ticket` (
  `stuOpenID` char(32) NOT NULL COMMENT '学员编号',
  `sum` int(11) NOT NULL DEFAULT '1' COMMENT '可使用优惠券总量',
  `usingNub` int(11) NOT NULL DEFAULT '0' COMMENT '正在使用中的优惠券数量',
  `usedNub` int(11) NOT NULL DEFAULT '0' COMMENT '已经使用的优惠券',
  KEY `stuOpenID` (`stuOpenID`),
  CONSTRAINT `tb_stu_ticket_ibfk_1` FOREIGN KEY (`stuOpenID`) REFERENCES `tb_student` (`stuOpenID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='赋羽券';

/*Data for the table `tb_stu_ticket` */

insert  into `tb_stu_ticket`(`stuOpenID`,`sum`,`usingNub`,`usedNub`) values ('123',0,0,1),('13719664496',1,0,0),('null',1,0,0),('ohYXK1XbW8VjFBKm9EpI390eEi74',1,0,0),('ohYXK1aTYFUk_rm8KRx2yFbGc_4Q',0,0,1),('123456',1,0,0),('ohYXK1c3XAQYTW7aTFU3cM59WqPc',0,1,0),('12345678',1,0,0),('ohYXK1fYMrA-lHFSVvlsfn4SOo4w',1,0,0),('654321',1,0,0),('123654',1,0,0),('147258',1,0,0),('987777777',1,0,0);

/*Table structure for table `tb_student` */

DROP TABLE IF EXISTS `tb_student`;

CREATE TABLE `tb_student` (
  `stuOpenID` char(32) NOT NULL COMMENT '学员编号(微信)',
  `pic` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `stuName` varchar(25) DEFAULT NULL COMMENT '学员真实姓名',
  `phone` char(11) DEFAULT NULL COMMENT '电话号码',
  `waChat` varchar(30) DEFAULT NULL COMMENT '微信号',
  `stuCity` varchar(20) DEFAULT NULL COMMENT '用户所在城市',
  `stuOrg` varchar(30) DEFAULT NULL COMMENT '所属学校全称或所在机构/公司',
  `stuDept` varchar(30) DEFAULT NULL COMMENT '或所在部门',
  `stuSex` tinyint(1) DEFAULT NULL COMMENT '学员性别 0男 1女',
  `identity` varchar(30) DEFAULT NULL COMMENT '身份',
  `stuTag` varchar(200) DEFAULT NULL COMMENT '正向标签',
  `stuTag2` varchar(200) DEFAULT NULL COMMENT '负面标签',
  `stuKill` varchar(200) DEFAULT NULL COMMENT '个人技能',
  `stuExperience` varchar(600) DEFAULT NULL COMMENT '工作/学习经历',
  `giveAuto` varchar(600) DEFAULT NULL COMMENT '10年后的自己',
  `expectTeacher` varchar(300) DEFAULT NULL COMMENT '理想中的人生导师',
  `giveTeacher` varchar(200) DEFAULT NULL COMMENT '对你未来的人生导师说一句话',
  `harvestList` varchar(50) DEFAULT NULL COMMENT '收获集合',
  `know` varchar(50) DEFAULT NULL COMMENT '了解赋羽',
  `stuFile` varchar(255) DEFAULT NULL COMMENT '文件',
  `birthday` date DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`stuOpenID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学员表';

/*Data for the table `tb_student` */

insert  into `tb_student`(`stuOpenID`,`pic`,`stuName`,`phone`,`waChat`,`stuCity`,`stuOrg`,`stuDept`,`stuSex`,`identity`,`stuTag`,`stuTag2`,`stuKill`,`stuExperience`,`giveAuto`,`expectTeacher`,`giveTeacher`,`harvestList`,`know`,`stuFile`,`birthday`) values ('123','123','aaaa','13812345678','123456','吴川','广现',NULL,0,'学生','啊啊啊啊','111111','222222','111111','222222','333333','4444444','职场通识,人生方向,','朋友介绍,',NULL,'2018-12-11'),('123456',NULL,'1111','13812345678','123456','aaaa','','adadadad',0,'','aaaaaa','aaaa','aaa','aaaa','aaaa','','','职场通识,人生方向,aaaa','朋友介绍,线上咨询,aaaaa',NULL,NULL),('12345678',NULL,'aaa','13812345678','aaaa','ssss','aaa','aaaa',0,'dddd','1111','2222','3333','4444','5555','6666','aaaa','职场通识,实习工作机会,aaaa','朋友介绍,cccc',NULL,'2018-12-28'),('123654',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('13719664496',NULL,'zz',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('147258',NULL,'早上好','13671477734','66666666666','','','广现',0,'','','','','','',NULL,'','职场通识,人生方向,','',NULL,NULL),('654321',NULL,'test','13812345678','123456','湛江','广现','大二',0,'学生','积极向上','懒','哈哈哈','','','','','','',NULL,'2019-01-01'),('987777777','987777777',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('null',NULL,'zz','13812345678','aaaaaaaaa','','','',0,'','','','','','','','','人生方向,实习证明,增长见识,','线上咨询,线下活动,',NULL,NULL),('ohYXK1aTYFUk_rm8KRx2yFbGc_4Q','http://thirdwx.qlogo.cn/mmopen/vi_32/tlOfRrsCfFqFs2EAQtTLVWRIqVEvHXWwHxXjRwuHBcpjAR7FH2O4sPCMQVBE6lMB4YhJz0ViaQ7EicYW7rz6CHSA/132',' ','13719664496','123','惠州','广现','',0,'学生','阳光，帅气','太优秀','玩游戏','hhhh','hhhh','hhhh','hhhhh','职场通识,工作技能,认知自己,','朋友介绍,','student/d9ea489618b44d2ea581f2fd501f61b6.jpg','1998-02-13'),('ohYXK1c3XAQYTW7aTFU3cM59WqPc','http://thirdwx.qlogo.cn/mmopen/vi_32/GiciaicKlFp8XvLux7sb5RfXRkLCBIszMfDZeYZAZHDKSfCoO6uT6MzTuwUDzYu8icBNmEN9PaDe1FECwibkovqemicw/132',' ','13671477734','13812345678','湛江','广现','专才',0,'学生','积极向上','懒','NB','','',NULL,'','职场通识,人生方向,人生导师,','',NULL,'2019-01-28'),('ohYXK1fYMrA-lHFSVvlsfn4SOo4w','http://thirdwx.qlogo.cn/mmopen/vi_32/8iaHl3wKG4aKKMeA5T3BgAFCsGpkAqpKvZDw8LOCn9ib8TrFBaJ9WpnAJUy6l8Pnmqh5XPhKgicYg0ELQUB4pytaQ/132',' ','15118874688','Jiangxinlong805','广州','飞利浦照明中国投资有限公司','总经办',0,'职场人士','呵呵呵呵呵呵啊呵呵哒呵呵呵呵呵呵，横说竖说','','','','',NULL,'','','',NULL,'1995-11-10'),('ohYXK1XbW8VjFBKm9EpI390eEi74','http://thirdwx.qlogo.cn/mmopen/vi_32/Q6x99VxceL2RDVagg7G9mnIYdDKM7dCicGYUSaL8qReK3GkcKPQ7oYS8aksIyEL3vQhhCbfibq4IBxxNTKkFzTqA/132','哈哈哈','13812345678','123456','广州','广现','小学',0,'学生','呃呃呃呃','心态容易爆炸','什么都不会','小学','糟老头','哈哈哈','你好','职场通识,人生方向,扩展人脉,','朋友介绍,线上咨询,荔枝微课,',NULL,'2019-01-21'),('ttttttttttttttttttt',NULL,'zz',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('undefined',NULL,'哈哈哈','13812345678','','','',NULL,0,'','','','','','','','','','',NULL,NULL),('zzzzzzzzzzzzzzzz',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tb_teacher` */

DROP TABLE IF EXISTS `tb_teacher`;

CREATE TABLE `tb_teacher` (
  `teaOpenID` char(32) NOT NULL COMMENT '导师编号(微信)',
  `teaName` varchar(25) DEFAULT NULL COMMENT '导师真实姓名',
  `phone` char(11) DEFAULT NULL COMMENT '电话号码',
  `waChat` varchar(30) DEFAULT NULL COMMENT '微信号',
  `teaCity` varchar(20) DEFAULT NULL COMMENT '用户所在城市',
  `teaOrg` varchar(30) DEFAULT NULL COMMENT '所属学校全称或所在机构/公司',
  `identity` varchar(30) DEFAULT NULL COMMENT '身份',
  `introduce` varchar(300) DEFAULT NULL COMMENT '个人简介',
  `profession` varchar(100) DEFAULT NULL COMMENT '所属行业',
  `claim` varchar(500) DEFAULT NULL COMMENT '对学员要求',
  `value` varchar(500) DEFAULT NULL COMMENT '私塾价值',
  `other` varchar(500) DEFAULT NULL COMMENT '其他补充说明',
  `pic` varchar(300) DEFAULT NULL COMMENT '文件',
  PRIMARY KEY (`teaOpenID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='导师表';

/*Data for the table `tb_teacher` */

insert  into `tb_teacher`(`teaOpenID`,`teaName`,`phone`,`waChat`,`teaCity`,`teaOrg`,`identity`,`introduce`,`profession`,`claim`,`value`,`other`,`pic`) values ('ohYXK1aTYFUk_rm8KRx2yFbGc_4Q','王逸','13749664496','hhhh','广州','','','','','','','','teacher/6f993458f8974eefbb42b4b9409cd255.jpg,'),('ohYXK1fe9OPUxu5xw0rLDPWHoN_A','老板娘',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,''),('teacher123','aaa','13671477734','147852dada','aaaa','dada','dddd','sssss','wwww','eeeee','qqqqq','eeeee','teacher/bf3c0dcf535744e0bf8ee13e1e06ec0c.png,teacher/6e3df2a470d14fda92c74debb56ea606.png'),('test','test',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tb_teacher_card` */

DROP TABLE IF EXISTS `tb_teacher_card`;

CREATE TABLE `tb_teacher_card` (
  `teaOpenID` char(32) NOT NULL COMMENT '导师编号',
  `pic` varchar(255) DEFAULT NULL COMMENT '导师海报',
  `richTextID` int(11) DEFAULT NULL COMMENT '导师富文本编号',
  `flag` tinyint(1) DEFAULT '1' COMMENT '是否展示',
  KEY `teaOpenID` (`teaOpenID`),
  CONSTRAINT `tb_teacher_card_ibfk_1` FOREIGN KEY (`teaOpenID`) REFERENCES `tb_teacher` (`teaOpenID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='导师海报列表';

/*Data for the table `tb_teacher_card` */

insert  into `tb_teacher_card`(`teaOpenID`,`pic`,`richTextID`,`flag`) values ('test','test',3,NULL);

/*Table structure for table `tb_user_flag` */

DROP TABLE IF EXISTS `tb_user_flag`;

CREATE TABLE `tb_user_flag` (
  `openID` char(32) NOT NULL,
  `vipFlag` tinyint(1) DEFAULT '0' COMMENT '是否成为会员',
  `subFlag` tinyint(1) DEFAULT '0' COMMENT '资料是否提交',
  `dataFlag` tinyint(1) DEFAULT '0' COMMENT '资料是否完整',
  KEY `openID` (`openID`),
  CONSTRAINT `tb_user_flag_ibfk_1` FOREIGN KEY (`openID`) REFERENCES `tb_student` (`stuOpenID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员判断表';

/*Data for the table `tb_user_flag` */

insert  into `tb_user_flag`(`openID`,`vipFlag`,`subFlag`,`dataFlag`) values ('123',1,1,0),('undefined',0,0,0),('13719664496',1,0,0),('null',1,0,0),('ohYXK1XbW8VjFBKm9EpI390eEi74',1,1,1),('ohYXK1aTYFUk_rm8KRx2yFbGc_4Q',1,1,0),('123456',1,0,0),('ohYXK1c3XAQYTW7aTFU3cM59WqPc',1,0,0),('12345678',1,1,1),('ohYXK1fYMrA-lHFSVvlsfn4SOo4w',1,0,0),('654321',1,1,0),('123654',1,0,0),('147258',1,0,0),('987777777',1,0,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
