/*
SQLyog Ultimate v8.32 
MySQL - 5.7.18-log : Database - eysh
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`eysh` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `eysh`;

/*Table structure for table `cms_access_record` */

DROP TABLE IF EXISTS `cms_access_record`;

CREATE TABLE `cms_access_record` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `siteId` varchar(32) DEFAULT NULL COMMENT '站点标识',
  `vid` varchar(32) DEFAULT NULL COMMENT '用户唯一标识',
  `vip` varchar(100) DEFAULT NULL COMMENT '用户ip',
  `domain_` varchar(100) DEFAULT NULL COMMENT '域名',
  `path` varchar(500) DEFAULT NULL COMMENT '访问的路径',
  `title` varchar(500) DEFAULT NULL COMMENT '页面标题',
  `artId` varchar(32) DEFAULT NULL COMMENT '文章id',
  `os` varchar(50) DEFAULT NULL COMMENT '客户端系统',
  `ostype` tinyint(4) DEFAULT NULL COMMENT '客户端系统类型',
  `browser` varchar(50) DEFAULT NULL COMMENT '客户端浏览器',
  `referrer` varchar(500) DEFAULT NULL COMMENT '来源',
  `atime` bigint(80) DEFAULT NULL COMMENT '访问时间',
  `duration` bigint(80) DEFAULT NULL COMMENT '该页面停留时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_access_record` */

insert  into `cms_access_record`(`id`,`siteId`,`vid`,`vip`,`domain_`,`path`,`title`,`artId`,`os`,`ostype`,`browser`,`referrer`,`atime`,`duration`) values ('02701ae0f9534fb69c970dee64465a39','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/about','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521970579549,1760229),('02f71207f47645baaa79a535960f6b08','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact?title=8341&author=123&content=','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1523544504274,268426),('066a3477b63c4576805049b6a4f113b8','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact?title=%E4%BD%A0%E5%A5%BD%EF%BC%8C%E6%88%91%E7%9A%84%E4%B8%96%E7%95%8C&author=%E5%91%A8%E5%A4%A7%E8%AF%97%E4%BA%BA&content=%E6%9D%A5%EF%BC%8C%E4%BD%A0%E6%9D%A5%E4%BA%86%E3%80%82%0D%0A%E8%B5%B0%EF%BC%8C%E4%BD%A0%E5%88%AB%E8%B5%B0%E3%80%82%0D%0A%E4%BA%B2%E7%88%B1%E7%9A%84%EF%BC%81%0D%0Adarling%EF%BC%81','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1523544760047,0),('06875bb0dd0c4383943d1ec1827dbb2b','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/about','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521966415275,569),('078cddb44729415bba228bae7e2f00bc','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521966416961,0),('079663c1058744dc86a5247dd9306e0f','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/gallery',1521947404197,0),('085cfb3c6df84e2a952b4347edce6290','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact?title=%E4%BD%A0%E5%A5%BD%EF%BC%8C%E6%88%91%E7%9A%84%E4%B8%96%E7%95%8C&author=%E5%91%A8%E5%A4%A7%E8%AF%97%E4%BA%BA&content=%E6%9D%A5%EF%BC%8C%E4%BD%A0%E6%9D%A5%E4%BA%86%E3%80%82%0D%0A%E8%B5%B0%EF%BC%8C%E4%BD%A0%E5%88%AB%E8%B5%B0%E3%80%82%0D%0A%E4%BA%B2%E7%88%B1%E7%9A%84%EF%BC%81%0D%0Adarling%EF%BC%81','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1523544760787,0),('08c802e777394fc4996c90cb56d09ea3','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1521946661834,0),('096ce94340a9423e9ef2f2bce3b99eae','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/front/login?returnUrl=',1521948018550,0),('09caef197c9e428eb9c9bc252b81e8ad','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1521980879366,0),('0a649b66aa5a4a40b830c8c922d93b8e','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521966256314,0),('0b1b97b89c814344a6da0503571b9666','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/contact',1521970366150,743),('0b436c80889942ed83bfde2be8f4c7e8','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/contact',1521966414816,459),('0bdd9e35e6964f5e81cdd3ce168d8a87','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539408261,0),('0c71427d5d0c42ca9e7adfb71e7b0fc5','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521903106238,0),('0d80703835664060b21b66652a934951','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539646911,0),('0dca05a841fd4d30aae15f1929afc673','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/about','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1524016943956,3566),('0e5f068f2e014d74962a8c2863d19a2a','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/gallery',1521947629936,0),('0f201d994df4400f846f46ad6e1e72b4','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/front/login?returnUrl=contact',1521947757701,0),('0f9d85aab2bb49b2a09370a7ee1ee3a6','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523540807623,0),('0fbf7eafb8914a85b64e9e81d354639b','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Firefox','',1522116516520,0),('1013b7aadf804923a74ad3fa4afb7b5f','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact?title=%E4%BD%A0%E5%A5%BD%EF%BC%8C%E6%88%91%E7%9A%84%E4%B8%96%E7%95%8C&author=%E5%91%A8%E5%A4%A7%E8%AF%97%E4%BA%BA&content=%E6%9D%A5%EF%BC%8C%E4%BD%A0%E6%9D%A5%E4%BA%86%E3%80%82%0D%0A%E8%B5%B0%EF%BC%8C%E4%BD%A0%E5%88%AB%E8%B5%B0%E3%80%82%0D%0A%E4%BA%B2%E7%88%B1%E7%9A%84%EF%BC%81%0D%0Adarling%EF%BC%81','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1523543779024,0),('10943a9915d04973b9c106649177c4c4','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1523629148865,0),('118c4dde95964a17a1ceac93388d7ace','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Microsoft Edge','',1523537553636,1347),('11fc3b670b0f4e1fb13442f3dec262f1','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/welcome?returnUrl=gallery',1523889295525,0),('12d2eb56da1a48f6af622f6c925a346e','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/about','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521970363482,503),('141e31e1286947c28b0be5d93b4302c0','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523540354413,0),('14a6cb283d4f42cdac51adbc0d3374fc','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/front/login?returnUrl=contact',1521947802616,0),('150db06d3c4e47d4864b59c063359d39','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Microsoft Edge','http://localhost:8080/',1523539175738,0),('17068c32d9e44d7d9a8a87ec7ed62719','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521966895324,1483),('174607853e344a4c907448ff4221a14b','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521902983366,0),('18291b6e839c435086fd67986d167a5d','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521941907016,0),('18bf0b9a102a4a16ac50cc31a6217bcd','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539647965,0),('19d0dced8d1f46ed8e5b897dd7ede3c1','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521980901204,0),('1a178c2ab41841f291cad53df5f932b1','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/about','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521966662921,1756),('1af3681d579b4fd996df528ecb611835','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Microsoft Edge','',1523537531753,0),('1b5fe3334bda4cc9bd63c106724461e0','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1521903838912,0),('1bb2b298a7e84f209c7e0d0a6892d670','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','',1524017347334,0),('1bb72373b6094ee991adcb385728c18f','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact?title=123&author=123&content=','Eysh','','Windows 10',0,'Firefox','http://127.0.0.1:8080/contact?title=123&author=123&content=',1523544181518,198378),('1d1f1c6a7ee3447a81061b8918f2b724','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Microsoft Edge','http://localhost:8080/',1523537583950,0),('1d64a67619c14d46ac6d4fdcd1a69c0d','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/welcome?returnUrl=',1524016928828,0),('1deda50b1e224614bc2e618b9f1084d5','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521967057609,56706),('1e46a70d86f741aa8b04c32cb7dfacf9','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Microsoft Edge','http://localhost:8080/front/login?returnUrl=gallery',1523537601838,0),('1f00a4f128fc41999162a5137d07158e','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523543356938,3224),('1f1b1f0bfa35488088c97d3f5ae1609e','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539351139,0),('1fa20bc7660f4d64aaf1e47704ab1769','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact?title=123&author=123&content=','Eysh','','Windows 10',0,'Firefox','http://127.0.0.1:8080/contact?title=123&author=123&content=',1523544666552,0),('2007304e13c34ab9bc9db3f07060e43d','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521903082799,0),('20f29d48b1e84db3abedc8b2c4173ff0','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521966660863,1380),('21a332daa82141e0bdc412b44b48dc54','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1521901308564,0),('21a7215f38f448c4a451baf9ae916c4e','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','',1521955959358,0),('22477473eeb64bd0992a37e31815ece5','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/about','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521966416524,244339),('2350044465234cb99b40729fbf8267ed','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1521980894906,1176),('24a4b83a68c147bf9e2688b9ca73ca8c','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Firefox','http://127.0.0.1:8080/',1523544148234,8910),('2637a0bd65a74e51a75476ae1d1c5c9d','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact?title=%E4%BD%A0%E5%A5%BD%EF%BC%8C%E6%88%91%E7%9A%84%E4%B8%96%E7%95%8C&author=%E5%91%A8%E5%A4%A7%E8%AF%97%E4%BA%BA&content=%E6%9D%A5%EF%BC%8C%E4%BD%A0%E6%9D%A5%E4%BA%86%E3%80%82%0D%0A%E8%B5%B0%EF%BC%8C%E4%BD%A0%E5%88%AB%E8%B5%B0%E3%80%82%0D%0A%E4%BA%B2%E7%88%B1%E7%9A%84%EF%BC%81%0D%0Adarling%EF%BC%81','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1523543360162,0),('26acc57cc6964c899b1ae7f2efdf29f4','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539407754,0),('273bf80a81b54c74a40615e4cc816550','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Microsoft Edge','',1523538924645,251984),('276012d232c9419aab934eb68816936f','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Firefox','http://127.0.0.1:8080/',1522116580780,0),('27f8b8c07a904442a8e0c40a31843786','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539450122,0),('28668236a2744965be894e85c3a65a64','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1523539220078,0),('28d1a99c9509478f8b7b765a4e8f2ce3','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1521901915197,0),('29427bac771e488c9432051c249db246','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','',1521941812987,94029),('294614d19d5d4bfa935be6c47085645f','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/contact',1521967114315,3240615),('294cb5d5874f44d29d9fc83348b4d054','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521966415844,680),('2980511fba894b3a922469ee305f6d9d','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1523541561604,1795334),('2a8e9e95ec344807a438f7bb15723892','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539405107,0),('2ac0820f346544838a350b18dcc377ae','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Microsoft Edge','http://localhost:8080/',1523539173681,0),('2b0858c793144ed19e8e6daff3a42b36','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/welcome?returnUrl=',1524016903654,2459),('2c2031457d70447dab899c96665bff2e','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/about','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521970355581,1491),('2c4b8506c74c49138f033d844e971f38','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1522125994067,0),('2cc70e53b3b54962b7061cfc2513ae2e','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/front/login?returnUrl=',1521947023623,0),('2e10bab54754482aafa18eb1c4960b89','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1522125992895,387),('2e1275d523834824bd66b46ebb0c2511','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523540067105,0),('2f149ee41bd24bab9154833bdea5710a','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Firefox','',1522116535300,376),('30325447136f4cd09cd3454bf0b655b9','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523540352163,0),('317b37f8786f4e06b11cc6571a9b2735','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact?title=%E4%BD%A0%E5%A5%BD%EF%BC%8C%E6%88%91%E7%9A%84%E4%B8%96%E7%95%8C&author=%E5%91%A8%E5%A4%A7%E8%AF%97%E4%BA%BA&content=%E6%9D%A5%EF%BC%8C%E4%BD%A0%E6%9D%A5%E4%BA%86%E3%80%82%0D%0A%E8%B5%B0%EF%BC%8C%E4%BD%A0%E5%88%AB%E8%B5%B0%E3%80%82%0D%0A%E4%BA%B2%E7%88%B1%E7%9A%84%EF%BC%81%0D%0Adarling%EF%BC%81','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1523543307543,0),('32ee8c4446b14f08b04420d71ac04f83','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/gallery',1521947133804,0),('33d356940a194761ba8d40ebfdfe8f0d','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1521906218040,0),('342b85061c7b4ad0b9bed443edb56d80','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/front/login?returnUrl=contact',1523545009128,0),('34813064224746a698b4da6316852ca9','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/about','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521966791477,586),('37aa799ee12a4befbd3965530cc3c161','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1521901330273,0),('37e63cc569c8463689a295a881049502','','f7d3f3f7e5b3bc369ef948c8978e747e','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','',1521972423258,0),('389b082d99c54bc181c76427c2b00e43','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/front/login?returnUrl=contact',1521981187945,4753264),('38d9850f6ce34d66ae703d31b7ebc73c','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1521946098650,0),('38df9df0e96e412895300d9364fd73c4','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523540292245,0),('394ea47fad1846309b9bad1649026d29','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/about',1524016947522,0),('3987d412912940be8b9667e62afaa5b7','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','',1521955937309,0),('399b756311954ea8a148364f1e164eea','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','',1524017314863,0),('3aa8240290f4410d9c4cd99a08d80644','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1521980889128,5778),('3af653d1b6b5429ba8d3b03297d7927b','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','',1524017315114,0),('3c772bb8b3254c6bbc4aeb4af27a039a','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1522125987554,4665),('3db3fbf64f404b5e9525f6464cccbe82','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/about',1524016921104,3014),('3ed55f9803e9402bb446db436ee82611','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1521901375876,539321),('404490136ef34391a88fc698f4c86299','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/about','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/gallery',1521947131653,544),('41738709398c44fbb6cd42db66201300','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/platform/login/noPermission',1521965014376,0),('41a2c11da75e4636a0b16c7513f0b4df','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/platform/login/noPermission',1521984236269,0),('4255a8ea83c04bd0a33dda1f57a3827b','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/about','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/contact',1521966794372,100952),('45c3ba161e404518b3bf25831b327930','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/about','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/gallery',1524016909183,11921),('4689d9367f1a4a3d9c73e45acdc12784','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1521906218218,0),('47f5fbfa41254d6294ffa811201b40f3','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact?title=123&author=123&content=','Eysh','','Windows 10',0,'Firefox','http://127.0.0.1:8080/contact?title=123&author=123&content=',1523544379896,4378),('4817cd179f264dd5a2f11dc9c0f43a66','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1523433099339,0),('48a02f7b399541ab89aee84d7b3687e2','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1523540052771,1434466),('48c76ea08369483593d1a240073512c4','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact?title=%E4%BD%A0%E5%A5%BD%EF%BC%8C%E6%88%91%E7%9A%84%E4%B8%96%E7%95%8C&author=%E5%91%A8%E5%A4%A7%E8%AF%97%E4%BA%BA&content=%E6%9D%A5%EF%BC%8C%E4%BD%A0%E6%9D%A5%E4%BA%86%E3%80%82%0D%0A%E8%B5%B0%EF%BC%8C%E4%BD%A0%E5%88%AB%E8%B5%B0%E3%80%82%0D%0A%E4%BA%B2%E7%88%B1%E7%9A%84%EF%BC%81%0D%0Adarling%EF%BC%81','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1523543809283,949267),('48fba093e7f8423ba3f52c2759bd95ff','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1521980849602,0),('49c6a2d2947e446487bea385e072377d','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact?title=%E5%9B%9E%E5%88%B0%E6%B3%95%E5%9B%BD&author=3%C2%B713&content=123',1523544774306,0),('4accbc795c624560adf9213a67cd46d7','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact?title=123&author=123&content=','Eysh','','Windows 10',0,'Firefox','http://127.0.0.1:8080/contact?title=123&author=123&content=',1523544445136,9840),('4c8e2b9020cf43af8f9982d4fe0e6006','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Firefox','',1524017367370,0),('4d34c2c7b9cb4fdaa1c0b5b1b57ebfd6','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','',1521941785986,0),('4ea9382136bf4c75a28379b0f63c5222','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/register?returnUrl=/contact',1521966252317,3997),('4f0ddf23d7ea4be78721bf06f09f4e7e','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact?title=%E5%8E%BB%EF%BC%8C%E4%BD%A0%E7%9A%84%E6%97%85%E8%A1%8C&author=%E5%91%A8%E5%A4%A7%E8%AF%97%E4%BA%BA&content=%E4%BD%A0%E5%A5%BD%EF%BC%8C%E6%88%91%E6%9D%A5%E4%BA%86','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact?title=%E4%BD%A0%E5%A5%BD%EF%BC%8C%E6%88%91%E7%9A%84%E4%B8%96%E7%95%8C&author=%E5%91%A8%E5%A4%A7%E8%AF%97%E4%BA%BA&content=%E6%9D%A5%EF%BC%8C%E4%BD%A0%E6%9D%A5%E4%BA%86%E3%80%82%0D%0A%E8%B5%B0%EF%BC%8C%E4%BD%A0%E5%88%AB%E8%B5%B0%E3%80%82%0D%0A%E4%BA%B2%E7%88%B1%E7%9A%84%EF%BC%81%0D%0Adarling%EF%BC%81',1523544727889,0),('4fe9e01d011b424cb4c4bfdac3eec172','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539355293,0),('54632dd47a8b4e58ad8817f9219a894d','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact?title=123&author=123&content=','Eysh','','Windows 10',0,'Firefox','http://127.0.0.1:8080/contact?title=123&author=123&content=',1523544648088,18464),('54cb69187aa04e2dbd58c5749b2818a3','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1522116341379,0),('56d1b0697bb4446880265e783613b0ff','','f7d3f3f7e5b3bc369ef948c8978e747e','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','',1521972383695,0),('57e993d25533459f85dcb9291b2a0605','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/gallery',1523889336194,0),('591bac9b465f4f40899b8f1ff3d0e6f4','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523542366817,0),('5b28287ff5c64176b6180ffaf12d0658','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/about',1524016908718,465),('5c3bb6deeb6341be9cb75cb321fe4ec4','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Firefox','http://127.0.0.1:8080/',1522116536200,346),('5c7ac0e6f7fd40079b786ecfe9b163f0','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521972314134,0),('5da42b18df7f49f29c7f2dd1d05452a1','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539406507,0),('5e228431493246f78b044593d1c74161','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539647619,0),('5f1f6cf66a7a4773a78f5cca6ace1058','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1521906217406,0),('6052bf231ddb475681cf53658b07e940','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521901364262,10885),('6358d01477054bc187bac0e49562c579','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Microsoft Edge','',1523538332100,0),('63bef0a8192143bfa3efec03249fdce2','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523541487237,0),('64b3991ebc344bf4b44dd991f11ec072','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1521980727089,0),('64bf780674e8410884fe855f439fed60','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','',1524017313627,0),('653c2e0b429f45959b1e2655f7e3fd80','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521947762228,0),('667948f0bfa145be8458e70ee9de7bd2','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521970576866,1491),('68b292a2023f448b82e29475175be974','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539349933,0),('694224c606174eedb7dfcdbb8de22b18','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/front/login?returnUrl=',1521980958680,47514),('6a1ca412ff524bf586e405a55ccd84ff','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523542359069,0),('6a467f4ac6254589b803a24da0de0d0c','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521966988502,0),('6b1563961f0e4421967d1283a5aa0ff8','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521904538234,0),('6b2906c472a8405e9bdf10321c8f533f','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1523626237727,0),('6ca81857c40e4c809631152585625d99','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539581220,0),('6e5541fa2e674c3eaa6c6ff102949ba6','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539350548,0),('6f1dd900a3d44e4a919d4f375573d8bb','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/welcome?returnUrl=gallery',1523889256285,0),('6f20bc70fbef4998a134328b0e2ec8f7','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1522125992219,676),('70e805ccfac94b8e9d90b5e621571cf3','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521970362055,790),('7285eb371eac43a28aab96a9a959326b','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','',1523539218353,129705),('730046948c1542248f048eaab5a38e55','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521903740603,0),('7323cebb9da64b5884b94ea22c9605d7','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1523888841744,0),('73cb2ff5895f485084e8c4dddbf7c290','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521985941209,0),('759cc556889d43a8ad9fbb33352ef25c','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact?title=123&author=123&content=','Eysh','','Windows 10',0,'Firefox','http://127.0.0.1:8080/contact?title=123&author=123&content=',1523544454976,193112),('76c776aa62754936b620857f53bbc5c5','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521902989005,0),('76f22e72aa934d36ad5f2ba9e5a926af','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521966668499,1413),('76ff11b783334d17a3a251fdfb2ca285','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/front/login?returnUrl=about',1521948005559,0),('78f34ea0c29c4f8c8763b994ca944527','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523540298165,0),('791be0e45d5345d4a45ec094cff182de','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521970354930,651),('79979dd2c1374fe4813ae54ef404c6be','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1524016942497,1459),('7a736e979df74973871dda5480ac0e21','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/about','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521947810866,0),('7c38667639b244319d57868a1ba2a8c6','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521903142906,0),('7d1d381cb7f64087996b9c73fa5a6918','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521966793502,870),('7f27b74836124d298ce9bd806d91e4bc','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539353072,0),('7f84e703922042a7b583d2779aa07fb2','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1522125993944,123),('8164988662bd4368866cbc3e3ab518de','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521966958535,0),('827544c6521243889606469a6283ca44','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1521906217716,0),('828d334065524cf280a73ad051c43528','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1524016939605,0),('83b7054fb9844e0f87fc308163c5aeca','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523888363677,0),('83b89a3a13a34862904b7823316e65e2','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539637743,0),('879db7d5d1ed412b9fecb3c8cf8e517a','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact?title=%E5%9B%9E%E5%88%B0%E6%B3%95%E5%9B%BD&author=3%C2%B713&content=123','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact?title=8341&author=123&content=',1523544515735,0),('88243185d87842eaa0c18a36a1b06087','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539408111,0),('88580ee6fc3a41f4bc7e6ae75d7d8ab4','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1521947129295,2358),('8885e3ea8e0d41bfab951bd2241d1224','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521901321330,8943),('891e02a4ee4e4a1e8df8e0cf06869a28','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1522116275340,0),('894ec1d5c685480eb51c0ce20ca9f748','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1522118181483,0),('8af3ba9447084acd97c377210f6c14c4','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Microsoft Edge','http://localhost:8080/front/login',1523537581087,2863),('8b0966ced87b4b7c9d5f392ac4ef41eb','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1521946455734,0),('8b2c650ad925411695796dc587c3714e','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Microsoft Edge','http://localhost:8080/',1523539176629,0),('8bf7481186d9492cbbdb7fa8317a353d','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','',1521986785109,0),('8cdbc3c7b81847eeaa90669f195100e3','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539349240,0),('8d27047e648a44f0979a7ce8ef6892da','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Firefox','http://127.0.0.1:8080/',1522116535676,322),('8de46aed082f42268f96e6c52c126148','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539353564,0),('908cb4c7bed14a5fb64c6732d2335acf','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521980900518,686),('914a243a5a2f49be8e420765290d1451','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1522116470521,103885),('91e8dc8624bf4262870d9862f30f45e6','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Microsoft Edge','http://localhost:8080/',1523539174716,0),('924972dd5ae34f109d83ddddc58d33a9','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1523544462219,5671),('941cce132f2c42afaca9bb696aa20c2c','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/front/login?returnUrl=gallery',1521947315137,0),('951e16e5090c493f9b4dc8c5915a8ba2','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523544467890,292897),('952b434ca9bf4828a4ca2169aaebf2c0','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/register?returnUrl=gallery',1521966550767,0),('9777ca816ee242fda6c619ab7b59d2f0','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521966790210,1267),('9a2b4b3101d94f37be1a44a28604b3c7','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact?title=123&author=123&content=','Eysh','','Windows 10',0,'Firefox','http://127.0.0.1:8080/contact?title=123&author=123&content=',1523544400026,35056),('9a9a982b106e411db5126f1c65487d22','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539352486,0),('9c57d8017a99454e98f7cb5a740bebc3','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521903091270,0),('9cfb8d85ff6c4a3fa67cabfb6db90010','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1522125913318,74236),('9d219e5a0c274a1ebb7c6a57519c4877','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/about','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521967043682,925),('9e26cf9c839a4746b3bdb6911ed942eb','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact?title=%E4%BD%A0%E5%A5%BD%EF%BC%8C%E6%88%91%E7%9A%84%E4%B8%96%E7%95%8C&author=%E5%91%A8%E5%A4%A7%E8%AF%97%E4%BA%BA&content=%E6%9D%A5%EF%BC%8C%E4%BD%A0%E6%9D%A5%E4%BA%86%E3%80%82%0D%0A%E8%B5%B0%EF%BC%8C%E4%BD%A0%E5%88%AB%E8%B5%B0%E3%80%82%0D%0A%E4%BA%B2%E7%88%B1%E7%9A%84%EF%BC%81%0D%0Adarling%EF%BC%81','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1523544760638,0),('9ee7d18525cb4949aca1acc1ea9e6d14','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523540159688,0),('9f6e8508607c46689c2ab0ed92de4fbd','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539351645,0),('a0eb6b0f6f7a4ca4bc8bc5bbe16917dd','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521966664677,1372),('a14b7c03f6034f139f3364b577e069ef','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521966071511,4389),('a3db38bb3c0f439aaf75aff3c9fee40e','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/front/login',1524016938279,1326),('a45322e593864a90a4f1e9eada0dc3aa','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1521901375526,350),('a6056af7168d4893a1520b1f48a2f107','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539355933,0),('a662fe85605d4df681a0a6b40ed86c32','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1524016906113,1551),('a7770a17ebb44737a844940e0d532733','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/about','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521970578357,699),('a7c3e51a2c8e412aa38ca056e1bbad8e','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/about','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521970366893,1106),('a86b5db9473f4d1da628fd0c56865ea8','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1523543767512,8421),('a9a92555fd4546849bb8048faa9cc61f','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','',1524017761062,0),('aa6eafc15ba648378cb5bbe82a17008e','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/gallery',1521981151565,0),('ac23f2e07ab54441b7fc52ccf0328927','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Microsoft Edge','http://localhost:8080/gallery',1523537587576,0),('ac8d98894db1455ca6345646b8e5a0c5','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521966666986,1513),('ae3d6b1bcafe408f92e584aea6fba627','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/front/login?returnUrl=gallery',1521981135245,16320),('ae523d12f153473aa1ebf6917b5ac396','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1523888860289,54651),('af4ff7f7aba542718ef764b17e28d20f','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539354775,0),('af9ff22d1ae041aea591537a9371750e','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521903738777,0),('afba9b24558c43669f1389c7a25efaf2','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523540073280,0),('b0d277a79332439c9d43039ede855135','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1522116401645,0),('b165859c526b44ce8b36db7f94a2cc00','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1521980891732,0),('b16f59e69e244df699e7188f8abbb8f6','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521966792063,661),('b1de387a663945e0afb7b17700a440ec','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1524016887503,0),('b296947fbb7042d29edb6ab1e81d97be','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1521948008776,0),('b2ca12db241944188d04ed995a653bd8','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Microsoft Edge','http://localhost:8080/',1523539171488,0),('b30a15d13d484d4b9a5b69bcfd226f56','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523544463025,0),('b322c2a4076c438e8ee1db7d968f602f','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','',1524017753618,0),('b3e74b5b024c4b93b1a02508eb2ad107','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521966795065,0),('b4befaacfba742d2b57674037e941051','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521903087135,0),('b68ee5f9ceb64d3c9656c57b19cc3d44','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact?title=111&author=111&content=','Eysh','','Windows 10',0,'Firefox','http://127.0.0.1:8080/contact?title=123&author=123&content=',1523544384274,5010),('b7516549930646e89c4707779daff645','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/gallery',1521981055319,0),('b75bda282d7345479e4618ac3ddd4b6e','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1523539348058,0),('b79ef9c681ae4b48b805fe9d4e0c75ff','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521966075900,935),('b81a187bd4004dca984bfaa83607ffc2','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/about','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/gallery',1524016907664,1054),('b86226b776f54fa19e3313c65dfb7375','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1523544369778,0),('b8ff038ecc43400f9e2b14bf4174e5ad','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','',1524017324107,0),('bab14c58964d487cb96d07ca52edd676','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523541792028,0),('bb7ebd4eb5ad4a95812c59bbfa71302e','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/contact',1521970359181,666),('bbc569ca7ab144de926accd26a497fbb','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/about','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521966896807,656),('be06faba1aa344e08d1eeedbfa33aca6','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact?title=8341&author=123&content=','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1523544474480,0),('be3485b71ca7400094e583336ddc18e5','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521966886763,0),('be43fa8773f348a187fb22d229a89a59','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521970357913,1268),('be81d1a4363044b68a382fd7ec57d33e','','f7d3f3f7e5b3bc369ef948c8978e747e','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','',1521972382364,0),('bf58e66fffca4dedac78a5144d7f1e00','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521947809295,1571),('c077828a5e634b87b2d95f376043aa4b','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/gallery',1521901320122,5279231),('c13f043d0db74580a4d9399da62c9c11','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539647818,0),('c15a806761ea43a79ef5155bcc889927','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','',1521941908431,0),('c162cd191b0c46a39e3cd1db48bcd371','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521947631471,0),('c2314d7d78334551b8bb1900a3a82ae4','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact?title=%E5%8E%BB%EF%BC%8C%E4%BD%A0%E7%9A%84%E6%97%85%E8%A1%8C&author=%E5%91%A8%E5%A4%A7%E8%AF%97%E4%BA%BA&content=%E4%BD%A0%E5%A5%BD%EF%BC%8C%E6%88%91%E6%9D%A5%E4%BA%86','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact?title=%E4%BD%A0%E5%A5%BD%EF%BC%8C%E6%88%91%E7%9A%84%E4%B8%96%E7%95%8C&author=%E5%91%A8%E5%A4%A7%E8%AF%97%E4%BA%BA&content=%E6%9D%A5%EF%BC%8C%E4%BD%A0%E6%9D%A5%E4%BA%86%E3%80%82%0D%0A%E8%B5%B0%EF%BC%8C%E4%BD%A0%E5%88%AB%E8%B5%B0%E3%80%82%0D%0A%E4%BA%B2%E7%88%B1%E7%9A%84%EF%BC%81%0D%0Adarling%EF%BC%81',1523543826793,0),('c451530a58844e3dae0aba9f8a82e3d5','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1522125993429,163),('c4a753fd9eb44a1dad0329c7c40da95e','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Microsoft Edge','http://localhost:8080/front/login?returnUrl=gallery',1523537613732,0),('c53cd44a04ec46c4acff8aa6d51ec759','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/welcome?returnUrl=',1524016934581,0),('c5b00f6226b24ef7b748b97149fcda8b','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Microsoft Edge','',1523537975266,0),('c6425b987d384742b28b385039b2d887','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact?title=%E5%8E%BB%EF%BC%8C%E4%BD%A0%E7%9A%84%E6%97%85%E8%A1%8C&author=%E5%91%A8%E5%A4%A7%E8%AF%97%E4%BA%BA&content=%E4%BD%A0%E5%A5%BD%EF%BC%8C%E6%88%91%E6%9D%A5%E4%BA%86','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact?title=%E4%BD%A0%E5%A5%BD%EF%BC%8C%E6%88%91%E7%9A%84%E4%B8%96%E7%95%8C&author=%E5%91%A8%E5%A4%A7%E8%AF%97%E4%BA%BA&content=%E6%9D%A5%EF%BC%8C%E4%BD%A0%E6%9D%A5%E4%BA%86%E3%80%82%0D%0A%E8%B5%B0%EF%BC%8C%E4%BD%A0%E5%88%AB%E8%B5%B0%E3%80%82%0D%0A%E4%BA%B2%E7%88%B1%E7%9A%84%EF%BC%81%0D%0Adarling%EF%BC%81',1523544758550,0),('c8deac0e226b4e0895dd3756e5723a0e','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Firefox','',1523543912170,236064),('c8fe9b0aa2e1406d8c65f1da78466a0b','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523540158897,0),('c9bd7703e7ff44bea3a369603e668275','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/gallery',1521947319475,0),('ca10a14b615b43de9901664cb3332ce4','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact?title=123&author=123&content=','Eysh','','Windows 10',0,'Firefox','http://127.0.0.1:8080/contact?title=111&author=111&content=',1523544389284,10742),('cb25586071464b6c95eaad369fc73cf8','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521970367999,208867),('cc19ef8271e24cefb3ad49301722ecbd','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1521981006194,0),('cd08e10dd4a142f5ac8d946684468b30','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521970360419,621),('cd44301c944d42f28a97bd99a9af210d','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521970357072,841),('cd4614e72beb4ce3aa7dad3b62305fe5','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/welcome?returnUrl=gallery',1523889219495,0),('ce91615023084aeda23d77b1172459ba','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521966898302,0),('cea4e8c73a3e45e88e879ca50d8c1351','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539708876,0),('ced46daf3455493d878110588339b132','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521970363985,1027),('ced6c73048d24726bcf6e8091550cb08','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521966507586,0),('cfd17a18efb64221916ec3afeb56af0e','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523541563493,0),('d04fc3f5dd574333bb630a4902df2f6f','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521947716394,0),('d14fd8ef9b0f44b19b20869ad16b9cf1','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523541694332,0),('d156487b78ff42d5a147f666bb61b2ef','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523543775933,33350),('d17da592d2794e4d91bccfbee620c74d','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521972339778,0),('d244ca0118fa45bc9c924406bd51a090','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1522125993747,197),('d385842ec593463ca686cad719b7e471','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1521947132775,0),('d39529ba77ca4d65a61c8e0409546423','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/gallery',1521980896082,0),('d50916c238da44fca3701e30051ad60a','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/welcome?returnUrl=gallery',1523889312491,0),('d75280485ab945969e4f097a0c1c33c6','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521981152434,0),('d7d82e8200b1431e85ca3156cf74af5b','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1522125993592,155),('d87631bea35c47569b2965f27023acc6','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521967034017,9665),('d8b392a774664b1499d1787f8d6ae47f','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/front/login?returnUrl=gallery',1521947399024,0),('d8eab71a48db43f9873b0d80da3f795e','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/about','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521966671120,119090),('d8f44170967c49ada1f5c551b32a3db0','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/welcome?returnUrl=gallery',1523889231588,0),('d910dd7cf755426e87b07894e6adffac','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/about',1521947132197,578),('d9ba9d5c60b44fcea6d872128817396f','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521966897463,136554),('db3989803eed47b383b89e253e3e8565','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Firefox','http://127.0.0.1:8080/',1522116536724,44056),('dbd52bdb1a724ef4a155dab65c6fc640','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1523432704004,0),('dc253a9868d946ccbca6924107c439c4','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521967044607,13002),('dc7f3327a0be406db202646a3415605c','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Firefox','',1524017373738,0),('dc94464cfd1b4f47ba642a37da7021a3','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1522116218559,0),('dc968745a9e84400aa6ec4f35c2351cf','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521966076835,0),('dd037c714b7d401296698e780daebb7e','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521966792724,778),('dd133fd4ea524b35ba2bf12df493cf19','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Microsoft Edge','http://localhost:8080/',1523538964228,0),('dd68a82b6be54f6c964b455ccfa4a43b','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Microsoft Edge','http://localhost:8080/',1523537554983,0),('dd69a6ac28b24b10a166b53adc9e345b','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/about','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521970361040,1015),('de10dd77ebec4872ac46787be90ab7c7','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/about','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521970359847,572),('de11c141aa794c6dae53526a35a3d159','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/front/login?returnUrl=gallery',1521947571590,58346),('e09026fe62f340329f1f40c1a5cc2756','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/welcome?returnUrl=gallery',1523889306591,0),('e09bee48e3c34d278b73bc6d22427477','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/register?returnUrl=gallery',1521966343518,69096),('e0fc8bcdd5d240759c62458a90b3e84d','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521966412614,2202),('e12d7e64edcf4cdda0a9f3eed91bd645','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523541589778,0),('e42ea7dee1674eb9833c1ee1f7a44246','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523888914940,0),('e45a445f13b74779ad69b0f37b7689e9','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521970365012,1138),('e4a7ff7de889425cb1e11c91372b2a22','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539354394,0),('e4bb4e7e9c7a4956a47723859487c9cb','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539645115,0),('e567c4b64e6b47e296ff8af5577ae42b','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Firefox','http://127.0.0.1:8080/',1522116536546,178),('e66ed7c910da492099c1946824365073','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact?title=123&author=123&content=','Eysh','','Windows 10',0,'Firefox','http://127.0.0.1:8080/contact',1523544157144,24374),('e67167cadc124164b868d0a66d4d6469','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521970362845,637),('e6b1919753af4840956afa3b422d7ee6','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/about','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521966666049,937),('e7a105e88b17419c8090b809f27e4ba9','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521906599353,0),('e987e508a20247ddaed6bc1bc28c6253','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521970579056,493),('e9dd80a2fce94c0f83e19facbed46190','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact?title=%E5%9B%9E%E5%88%B0%E6%B3%95%E5%9B%BD&author=3%C2%B713&content=123','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact?title=8341&author=123&content=',1523544772700,1606),('ea4efa5daab44cdf9d0d8d5c8effdbdb','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/front/login?returnUrl=',1521947126164,3131),('eaf4bcd9d76d4c8d95b8f155063459e8','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact?title=123&author=123&content=','Eysh','','Windows 10',0,'Firefox','http://127.0.0.1:8080/contact?title=123&author=123&content=',1523544435082,10054),('edac0fd8da434b68947f8d19f50077e6','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521966662243,678),('ee0e666d5c2840c2a94fa9d21bd6ec1a','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539354005,0),('eebde7d9c1f848219323a5e050097eab','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1522116574406,1607077),('ef3ce1446974443d8a7ce329dcb787d3','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1522125906972,6346),('f085101a81534defa0f26d876b1c748c','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1521901375147,379),('f122fbe44ac54f629897144d6ff2dbc7','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1524016885451,0),('f170e800a4504c8e8f31950c63b1f9ee','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/about','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/',1521980899979,539),('f2967dc9e2494b1bb5daf17904783f6b','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/gallery','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/welcome?returnUrl=gallery',1523889319582,16612),('f29e880db4cb42b09491cea03d25c6d9','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/contact',1521903657644,0),('f42555dcb8064f31915a1d105ede3a52','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1521901305731,0),('f453e9909f5b4d3c8323e5b22755257a','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539407328,0),('f459bd1910e04778a1590e5136b672c5','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1522125993282,147),('f55b6e11ef7d424a952509fa5c01bd70','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539407939,0),('f56b490dd7f849dd890dca10688f473b','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','',1523539352076,0),('f58ede2a5bfa445dbd3bc7f8c5e8b707','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/contact',1521966669912,1208),('f62e82545c444619979b6a07473872fe','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/gallery',1524016924118,0),('f8cd9ec08afd4526a75621671a30f4c9','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/',1523542447729,859814),('f98ab452f78d4347a07d28fc452e937d','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/about',1521966672889,0),('faaff295126d415a8860947720320490','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/contact','Eysh','','Windows 10',0,'Chrome','http://localhost:8080/front/login?returnUrl=contact',1521947712560,0),('fcc4a1e2b89e4652825dfc7c8715004e','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1522116439273,0),('fda14cbe140a483094c3c5dce15c24fb','','1756b27efd13994e7be5001d7ed5ce4e','0:0:0:0:0:0:0:1','localhost','http://localhost:8080/','Eysh','','Windows 10',0,'Chrome','',1521941610776,0),('ff916976336744ac84c242d61eb74c39','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/contact','Eysh','','Windows 10',0,'Chrome','http://127.0.0.1:8080/gallery',1521967045284,0),('ffe79cfa3ec24413bae3754ae3a43559','','57298fb938492c024dde3e931587546c','127.0.0.1','127.0.0.1','http://127.0.0.1:8080/','Eysh','','Windows 10',0,'Firefox','http://127.0.0.1:8080/',1522116535998,202);

/*Table structure for table `cms_acq_log` */

DROP TABLE IF EXISTS `cms_acq_log`;

CREATE TABLE `cms_acq_log` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `url` varchar(255) DEFAULT NULL COMMENT '采集url',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_acq_log` */

/*Table structure for table `cms_acquisition` */

DROP TABLE IF EXISTS `cms_acquisition`;

CREATE TABLE `cms_acquisition` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `site_id` varchar(32) DEFAULT NULL COMMENT '站点id',
  `channel_id` varchar(32) DEFAULT NULL COMMENT '栏目id',
  `model_id` varchar(32) DEFAULT NULL COMMENT '模型id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `acq_name` varchar(150) DEFAULT NULL COMMENT '采集名称',
  `start_time` varchar(32) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(32) DEFAULT NULL COMMENT '结束时间',
  `status` int(32) DEFAULT NULL COMMENT '采集状态',
  `curr_num` int(32) DEFAULT NULL COMMENT 'curr_num',
  `curr_item` int(32) DEFAULT NULL COMMENT 'curr_item',
  `total_item` int(32) DEFAULT NULL COMMENT 'total_item',
  `pause_time` int(32) DEFAULT NULL COMMENT '暂停时间：单位(毫秒)',
  `page_encoding` varchar(10) DEFAULT NULL COMMENT '页面编码',
  `plan_list` varchar(255) DEFAULT NULL COMMENT '采集地址',
  `dynamic_addr` varchar(255) DEFAULT NULL COMMENT '动态地址',
  `dynamic_start` int(32) DEFAULT NULL COMMENT '动态地址页码开始',
  `dynamic_end` int(32) DEFAULT NULL COMMENT '动态地址页码结束',
  `linkset_start` varchar(255) DEFAULT NULL COMMENT '内容地址集开始HTML',
  `linkset_end` varchar(255) DEFAULT NULL COMMENT '内容地址集结束HTML',
  `link_start` varchar(255) DEFAULT NULL COMMENT '内容地址开始HTML',
  `link_end` varchar(255) DEFAULT NULL COMMENT '内容地址结束HTML',
  `title_start` varchar(255) DEFAULT NULL COMMENT '标题开始HTML',
  `title_end` varchar(255) DEFAULT NULL COMMENT '标题结束HTML',
  `description_start` varchar(255) DEFAULT NULL COMMENT '描述开始HTML',
  `description_end` varchar(255) DEFAULT NULL COMMENT '描述结束HTML',
  `content_start` varchar(255) DEFAULT NULL COMMENT '内容开始HTML',
  `content_end` varchar(255) DEFAULT NULL COMMENT '内容结束HTML',
  `content_prefix` varchar(255) DEFAULT NULL COMMENT '内容地址补全url',
  `img_prefix` varchar(255) DEFAULT NULL COMMENT '图片地址补全url',
  `view_start` varchar(255) DEFAULT NULL COMMENT '浏览量开始HTML',
  `view_end` varchar(255) DEFAULT NULL COMMENT '浏览量结束HTML',
  `view_id_start` varchar(255) DEFAULT NULL COMMENT '浏览量访问地址开始HTML',
  `view_id_end` varchar(255) DEFAULT NULL COMMENT '浏览量访问地址结束HTML',
  `view_link` varchar(255) DEFAULT NULL COMMENT 'view_link',
  `releasetime_start` varchar(255) DEFAULT NULL COMMENT '发布时间开始HTML',
  `releasetime_end` varchar(255) DEFAULT NULL COMMENT '发布时间结束HTML',
  `author_start` varchar(255) DEFAULT NULL COMMENT '作者开始HTML',
  `author_end` varchar(255) DEFAULT NULL COMMENT '作者结束HTML',
  `origin_start` varchar(255) DEFAULT NULL COMMENT '来源开始HTML',
  `origin_end` varchar(255) DEFAULT NULL COMMENT '来源结束HTML',
  `releasetime_format` varchar(32) DEFAULT NULL COMMENT '发布时间格式',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_acquisition` */

/*Table structure for table `cms_channel` */

DROP TABLE IF EXISTS `cms_channel`;

CREATE TABLE `cms_channel` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `site_id` varchar(32) DEFAULT NULL COMMENT '站点id',
  `model_id` varchar(32) DEFAULT NULL COMMENT '模型id',
  `name` varchar(100) DEFAULT NULL COMMENT '栏目名称',
  `path` varchar(100) DEFAULT NULL COMMENT '栏目路径',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `title` varchar(100) DEFAULT NULL COMMENT 'meta标题',
  `keywords` varchar(100) DEFAULT NULL COMMENT 'meta关键字',
  `description` varchar(255) DEFAULT NULL COMMENT 'meta描述',
  `tpl_channel` varchar(150) DEFAULT NULL COMMENT '栏目模板',
  `is_display` varchar(2) DEFAULT NULL COMMENT '是否在前台显示',
  `is_static` int(32) DEFAULT NULL COMMENT '发布状态',
  `blank` varchar(2) DEFAULT NULL COMMENT '是否新窗口打开',
  `linkurl` varchar(100) DEFAULT NULL COMMENT '外部链接',
  `flowid` varchar(32) DEFAULT NULL COMMENT '工作流id',
  `checked` varchar(2) DEFAULT NULL COMMENT '是否选中',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `hasChildren` tinyint(1) DEFAULT NULL COMMENT '有子节点',
  `pagesize` varchar(20) DEFAULT NULL COMMENT '每页记录数',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_channel` */

/*Table structure for table `cms_channel_model` */

DROP TABLE IF EXISTS `cms_channel_model`;

CREATE TABLE `cms_channel_model` (
  `site_id` varchar(32) DEFAULT NULL COMMENT '站点id',
  `channel_id` varchar(32) DEFAULT NULL COMMENT '栏目id',
  `model_id` varchar(32) DEFAULT NULL COMMENT '模型id',
  `tpl_content` varchar(255) DEFAULT NULL COMMENT '模型内容',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_channel_model` */

/*Table structure for table `cms_channel_role` */

DROP TABLE IF EXISTS `cms_channel_role`;

CREATE TABLE `cms_channel_role` (
  `channel_id` varchar(32) NOT NULL COMMENT '栏目id',
  `site_id` varchar(32) NOT NULL COMMENT '站点id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `has_add` varchar(2) DEFAULT NULL COMMENT '新增 Y/N',
  `has_update` varchar(2) DEFAULT NULL COMMENT '修改 Y/N',
  `has_del` varchar(2) DEFAULT NULL COMMENT '删除 Y/N',
  `has_move` varchar(2) DEFAULT NULL COMMENT '移动 Y/N',
  `has_check` varchar(2) DEFAULT NULL COMMENT '审核 Y/N',
  `has_static` varchar(2) DEFAULT NULL COMMENT '发布 Y/N',
  `has_push` varchar(2) DEFAULT NULL COMMENT '复制 Y/N',
  `has_share` varchar(2) DEFAULT NULL COMMENT '引用 Y/N',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`channel_id`,`site_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_channel_role` */

/*Table structure for table `cms_class_link` */

DROP TABLE IF EXISTS `cms_class_link`;

CREATE TABLE `cms_class_link` (
  `classId` varchar(32) DEFAULT NULL,
  `linkId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_class_link` */

/*Table structure for table `cms_content` */

DROP TABLE IF EXISTS `cms_content`;

CREATE TABLE `cms_content` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `site_id` varchar(32) DEFAULT NULL COMMENT '站点id',
  `topic_id` varchar(32) DEFAULT NULL COMMENT '专题id',
  `channel_id` varchar(32) DEFAULT NULL COMMENT '栏目id',
  `model_id` varchar(32) DEFAULT NULL COMMENT '模型id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `unit_id` varchar(32) DEFAULT NULL COMMENT '单位id',
  `status` int(32) DEFAULT NULL COMMENT '审核状态 -1：回收站',
  `has_title_img` varchar(255) DEFAULT NULL COMMENT '标题图片',
  `sort_time` varchar(50) DEFAULT NULL COMMENT '排序时间',
  `title` varchar(80) DEFAULT NULL COMMENT '标题',
  `link_` varchar(150) DEFAULT NULL COMMENT '外部链接',
  `short_title` varchar(60) DEFAULT NULL COMMENT '短标题',
  `title_color` varchar(50) DEFAULT NULL COMMENT '短标题颜色',
  `is_bold` varchar(2) DEFAULT NULL COMMENT '短标题是否加粗',
  `author` varchar(50) DEFAULT NULL COMMENT '发布者',
  `origin` varchar(50) DEFAULT NULL COMMENT '来源',
  `origin_url` varchar(150) DEFAULT NULL COMMENT '来源URL',
  `description` varchar(255) DEFAULT NULL COMMENT '摘要',
  `media_path` varchar(255) DEFAULT NULL COMMENT '多媒体',
  `media_type` varchar(255) DEFAULT NULL COMMENT '播放器',
  `top_level` varchar(255) DEFAULT NULL COMMENT '固顶级别',
  `add_time` varchar(255) DEFAULT NULL COMMENT '新增时间',
  `pub_time` varchar(255) DEFAULT NULL COMMENT '发布时间',
  `title_img` varchar(255) DEFAULT NULL COMMENT '标题图片',
  `title_img_s` varchar(255) DEFAULT NULL COMMENT 'title_img_s',
  `tpl_content` varchar(255) DEFAULT NULL COMMENT '指定模板',
  `is_static` int(32) DEFAULT NULL COMMENT '发布状态',
  `is_index` int(32) DEFAULT NULL COMMENT 'is_index',
  `department` varchar(255) DEFAULT NULL COMMENT '招聘部门',
  `area` varchar(255) DEFAULT NULL COMMENT '招聘地区',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_content` */

/*Table structure for table `cms_content_channel` */

DROP TABLE IF EXISTS `cms_content_channel`;

CREATE TABLE `cms_content_channel` (
  `content_id` varchar(32) DEFAULT NULL COMMENT '内容id',
  `channel_id` varchar(32) DEFAULT NULL COMMENT '栏目id',
  `site_id` varchar(32) DEFAULT NULL COMMENT '站点id',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_content_channel` */

/*Table structure for table `cms_content_check` */

DROP TABLE IF EXISTS `cms_content_check`;

CREATE TABLE `cms_content_check` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `content_id` varchar(32) DEFAULT NULL COMMENT '内容id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `flowid` varchar(32) DEFAULT NULL COMMENT '工作流id',
  `check_step` bigint(128) DEFAULT NULL COMMENT '审核步骤',
  `check_opinion` varchar(32) DEFAULT NULL COMMENT '审核描述',
  `is_checked` varchar(32) DEFAULT NULL COMMENT '是否通过',
  `reviewer` varchar(32) DEFAULT NULL COMMENT '审核人',
  `check_date` varchar(50) DEFAULT NULL COMMENT '审核日期',
  `status` tinyint(8) DEFAULT NULL COMMENT '审核状态1：通过',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_content_check` */

/*Table structure for table `cms_content_estemp` */

DROP TABLE IF EXISTS `cms_content_estemp`;

CREATE TABLE `cms_content_estemp` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `contentId` varchar(32) DEFAULT NULL COMMENT '文章ID',
  `action` varchar(32) DEFAULT NULL COMMENT '操作类型',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_content_estemp` */

/*Table structure for table `cms_content_file` */

DROP TABLE IF EXISTS `cms_content_file`;

CREATE TABLE `cms_content_file` (
  `content_id` varchar(32) DEFAULT NULL COMMENT '内容id',
  `priority` bigint(128) DEFAULT NULL COMMENT '排序',
  `file_path` varchar(501) DEFAULT NULL COMMENT '文件路径',
  `file_name` varchar(100) DEFAULT NULL COMMENT '生成的文件名称',
  `filename` varchar(100) DEFAULT NULL COMMENT '原文件名',
  `download_count` bigint(128) DEFAULT NULL COMMENT 'download_count',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_content_file` */

/*Table structure for table `cms_content_picture` */

DROP TABLE IF EXISTS `cms_content_picture`;

CREATE TABLE `cms_content_picture` (
  `content_id` varchar(32) DEFAULT NULL COMMENT '内容id',
  `priority` bigint(128) DEFAULT NULL COMMENT '排序',
  `img` varchar(150) DEFAULT NULL COMMENT '图片',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_content_picture` */

/*Table structure for table `cms_content_source` */

DROP TABLE IF EXISTS `cms_content_source`;

CREATE TABLE `cms_content_source` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `source` varchar(100) DEFAULT NULL COMMENT '来源名称',
  `ext1` varchar(128) DEFAULT NULL,
  `ext2` varchar(128) DEFAULT NULL,
  `ext3` varchar(128) DEFAULT NULL,
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_content_source` */

/*Table structure for table `cms_content_tag` */

DROP TABLE IF EXISTS `cms_content_tag`;

CREATE TABLE `cms_content_tag` (
  `content_id` varchar(32) DEFAULT NULL COMMENT '内容id',
  `tag_id` varchar(32) DEFAULT NULL COMMENT 'tag标签id',
  `priority` tinyint(8) DEFAULT NULL COMMENT '顺序',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_content_tag` */

/*Table structure for table `cms_content_txt` */

DROP TABLE IF EXISTS `cms_content_txt`;

CREATE TABLE `cms_content_txt` (
  `content_id` varchar(32) DEFAULT NULL COMMENT '内容id',
  `txt` text COMMENT '内容',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_content_txt` */

/*Table structure for table `cms_link` */

DROP TABLE IF EXISTS `cms_link`;

CREATE TABLE `cms_link` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(120) DEFAULT NULL COMMENT '链接名称',
  `type` varchar(20) DEFAULT NULL COMMENT '链接类型',
  `picurl` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `target` varchar(20) DEFAULT NULL COMMENT '打开方式',
  `classId` varchar(32) DEFAULT NULL,
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_link` */

/*Table structure for table `cms_link_class` */

DROP TABLE IF EXISTS `cms_link_class`;

CREATE TABLE `cms_link_class` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `key_word` varchar(120) DEFAULT NULL COMMENT '分类KEY',
  `name` varchar(120) DEFAULT NULL COMMENT '分类名称',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_link_class` */

/*Table structure for table `cms_log` */

DROP TABLE IF EXISTS `cms_log`;

CREATE TABLE `cms_log` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `site_id` varchar(32) DEFAULT NULL COMMENT '站点id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `category` bigint(128) DEFAULT NULL COMMENT 'category',
  `log_time` varchar(32) DEFAULT NULL COMMENT '操作时间',
  `ip` varchar(32) DEFAULT NULL COMMENT '用户ip',
  `url` varchar(120) DEFAULT NULL COMMENT '操作url',
  `title` varchar(225) DEFAULT NULL COMMENT '内容标题',
  `content` varchar(120) DEFAULT NULL COMMENT '修改内容',
  `content_id` varchar(32) DEFAULT NULL COMMENT '内容id',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_log` */

insert  into `cms_log`(`id`,`site_id`,`user_id`,`category`,`log_time`,`ip`,`url`,`title`,`content`,`content_id`,`opBy`,`opAt`,`delFlag`) values ('803d16edbbcd46b99fd292798b2b8420','6bfdf86056394ed1bc23c50b33df190b','1a38a92345bd44ed98648b9162b2d67a',1,'2018-03-24 22:25:41','0:0:0:0:0:0:0:1','/platform/cms/site/editDo','修改站点','site_id:6bfdf86056394ed1bc23c50b33df190b;site_name:Eysh','0','1a38a92345bd44ed98648b9162b2d67a',1521901541,0),('e9ab86e7902644ae9ec65a60d84bbd89','6bfdf86056394ed1bc23c50b33df190b','1a38a92345bd44ed98648b9162b2d67a',1,'2018-02-01 13:56:39','127.0.0.1','/platform/cms/site/addDo','添加站点','site_id:6bfdf86056394ed1bc23c50b33df190b;site_name:Eysh','0','1a38a92345bd44ed98648b9162b2d67a',1517464599,0);

/*Table structure for table `cms_model` */

DROP TABLE IF EXISTS `cms_model`;

CREATE TABLE `cms_model` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `model_name` varchar(80) DEFAULT NULL COMMENT '模型名称',
  `tpl_channel_perfix` varchar(20) DEFAULT NULL COMMENT '栏目模板前缀',
  `tpl_content_perfix` varchar(20) DEFAULT NULL COMMENT '内容模板前缀',
  `title_img_width` bigint(40) DEFAULT NULL COMMENT '栏目标题图宽',
  `title_img_height` bigint(40) DEFAULT NULL COMMENT '栏目标题图高',
  `content_img_width` bigint(40) DEFAULT NULL COMMENT '栏目内容图宽',
  `content_img_height` bigint(40) DEFAULT NULL COMMENT '栏目内容图高',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_model` */

/*Table structure for table `cms_navigation` */

DROP TABLE IF EXISTS `cms_navigation`;

CREATE TABLE `cms_navigation` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `navigationName` varchar(120) DEFAULT NULL COMMENT '频道名称',
  `navigationUrl` varchar(120) DEFAULT NULL COMMENT '链接地址',
  `location` int(32) DEFAULT '1' COMMENT '所在位置',
  `createAt` int(32) DEFAULT NULL COMMENT '创建时间',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否发布',
  `type` int(32) DEFAULT NULL COMMENT '频道类型',
  `belongType` int(32) DEFAULT NULL COMMENT '频道所属客户端',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_navigation` */

/*Table structure for table `cms_site` */

DROP TABLE IF EXISTS `cms_site`;

CREATE TABLE `cms_site` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `site_name` varchar(255) DEFAULT NULL COMMENT '网站名称',
  `site_sname` varchar(50) DEFAULT NULL COMMENT '网站简称',
  `site_domain` varchar(100) DEFAULT NULL COMMENT '域名',
  `site_path` varchar(100) DEFAULT NULL COMMENT '路径',
  `site_css` varchar(100) DEFAULT NULL COMMENT '样式路径',
  `is_static` int(32) DEFAULT NULL COMMENT '发布状态',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_site` */

insert  into `cms_site`(`id`,`site_name`,`site_sname`,`site_domain`,`site_path`,`site_css`,`is_static`,`location`,`opBy`,`opAt`,`delFlag`) values ('6bfdf86056394ed1bc23c50b33df190b','Eysh','Eysh','www.xyy277.cn','eysh','default',NULL,1,'1a38a92345bd44ed98648b9162b2d67a',1521901540,0);

/*Table structure for table `cms_site_role` */

DROP TABLE IF EXISTS `cms_site_role`;

CREATE TABLE `cms_site_role` (
  `siteId` varchar(32) DEFAULT NULL,
  `roleId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_site_role` */

/*Table structure for table `cms_tag` */

DROP TABLE IF EXISTS `cms_tag`;

CREATE TABLE `cms_tag` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '标签名称',
  `counter` bigint(120) DEFAULT NULL COMMENT '顺序',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_tag` */

/*Table structure for table `cms_topic` */

DROP TABLE IF EXISTS `cms_topic`;

CREATE TABLE `cms_topic` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `site_id` varchar(32) DEFAULT NULL COMMENT '站点id',
  `channelIds` varchar(255) DEFAULT NULL COMMENT '关联栏目ids',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `shortName` varchar(80) DEFAULT NULL COMMENT '简称',
  `keywords` varchar(80) DEFAULT NULL COMMENT '关键字',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `recommend` varchar(2) DEFAULT NULL COMMENT '是否推荐',
  `priority` int(32) DEFAULT NULL COMMENT '排序顺序',
  `tplContent` varchar(100) DEFAULT NULL COMMENT '模版',
  `title_img` varchar(100) DEFAULT NULL COMMENT '标题图',
  `content_img` varchar(100) DEFAULT NULL COMMENT '内容图',
  `title_img_width` bigint(40) DEFAULT NULL COMMENT '标题图宽',
  `title_img_height` bigint(40) DEFAULT NULL COMMENT '标题图高',
  `content_img_width` bigint(40) DEFAULT NULL COMMENT '内容图宽',
  `content_img_height` bigint(40) DEFAULT NULL COMMENT '内容图高',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_topic` */

/*Table structure for table `cms_workflow` */

DROP TABLE IF EXISTS `cms_workflow`;

CREATE TABLE `cms_workflow` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(30) DEFAULT NULL COMMENT '工作流名称',
  `note` varchar(50) DEFAULT NULL COMMENT '工作流描述',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_workflow` */

/*Table structure for table `cms_workflow_info` */

DROP TABLE IF EXISTS `cms_workflow_info`;

CREATE TABLE `cms_workflow_info` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `flowid` varchar(32) DEFAULT NULL COMMENT '工作流id',
  `step` int(32) DEFAULT NULL COMMENT '步骤',
  `roleid` varchar(32) DEFAULT NULL COMMENT '角色id',
  `type` tinyint(8) DEFAULT NULL COMMENT '类型',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_workflow_info` */

/*Table structure for table `gk_contract` */

DROP TABLE IF EXISTS `gk_contract`;

CREATE TABLE `gk_contract` (
  `id` varchar(32) NOT NULL COMMENT '合同表ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `reservation_id` varchar(32) DEFAULT NULL COMMENT '预约单号',
  `consumer_name` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `contract_number` varchar(32) DEFAULT NULL COMMENT '合同编号',
  `contract_name` varchar(255) DEFAULT NULL COMMENT '合同名称',
  `contract_amount` decimal(32,2) DEFAULT NULL COMMENT '合同金额',
  `signed_date` int(32) DEFAULT NULL COMMENT '签订日期',
  `contract_man` varchar(32) DEFAULT NULL COMMENT '联系人',
  `contract_phone` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `detection_style` varchar(32) DEFAULT NULL COMMENT '检测类型',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `gk_contract` */

/*Table structure for table `gk_contract_attachment` */

DROP TABLE IF EXISTS `gk_contract_attachment`;

CREATE TABLE `gk_contract_attachment` (
  `id` varchar(32) NOT NULL COMMENT '合同附件ID',
  `contract_id` varchar(32) DEFAULT NULL COMMENT '合同ID',
  `attachment_path` varchar(500) DEFAULT NULL COMMENT '附件路径',
  `path` varchar(512) DEFAULT NULL COMMENT '文件存放路径',
  `name` varchar(32) DEFAULT NULL COMMENT '附件名称',
  `sort_field` int(32) DEFAULT NULL COMMENT '排序字段',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `gk_contract_attachment` */

/*Table structure for table `gk_contract_report` */

DROP TABLE IF EXISTS `gk_contract_report`;

CREATE TABLE `gk_contract_report` (
  `id` varchar(32) NOT NULL COMMENT '合同报告ID',
  `contract_id` varchar(32) DEFAULT NULL COMMENT '合同ID',
  `report_number` varchar(32) DEFAULT NULL COMMENT '报告编号',
  `generated_time` int(32) DEFAULT NULL COMMENT '生成时间',
  `inspector` varchar(32) DEFAULT NULL COMMENT '检测人',
  `reporturl` varchar(150) DEFAULT NULL COMMENT '报告地址',
  `token` varchar(64) DEFAULT NULL COMMENT '验证身份',
  `unit` varchar(32) DEFAULT NULL COMMENT '委托单位',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `gk_contract_report` */

/*Table structure for table `gk_detect_order` */

DROP TABLE IF EXISTS `gk_detect_order`;

CREATE TABLE `gk_detect_order` (
  `id` varchar(32) NOT NULL COMMENT '订单ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `reservation_id` varchar(32) DEFAULT NULL COMMENT '预约单号',
  `detection_style` varchar(32) DEFAULT NULL COMMENT '检测类型',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `contract_phone` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `organization` varchar(255) DEFAULT NULL COMMENT '单位名称',
  `detect_sample` varchar(32) DEFAULT NULL COMMENT '检测样品',
  `detect_item` varchar(2000) DEFAULT NULL COMMENT '检测项目',
  `status` int(32) DEFAULT NULL COMMENT '状态',
  `regist_time` int(32) DEFAULT NULL COMMENT '登记时间',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `gk_detect_order` */

/*Table structure for table `gk_detect_sampling` */

DROP TABLE IF EXISTS `gk_detect_sampling`;

CREATE TABLE `gk_detect_sampling` (
  `id` varchar(32) NOT NULL,
  `contract_id` varchar(32) DEFAULT NULL COMMENT '合同单号',
  `report_id` varchar(32) DEFAULT NULL COMMENT '报告编号',
  `sample_name` varchar(255) DEFAULT NULL COMMENT '样品名称',
  `sample_spec` varchar(1000) DEFAULT NULL COMMENT '样品规格',
  `sample_amount` int(32) DEFAULT NULL COMMENT '样品数量',
  `sample_status` varchar(32) DEFAULT NULL COMMENT '样品状态',
  `production_date` int(32) DEFAULT NULL COMMENT '生产日期',
  `get_sample_date` int(32) DEFAULT NULL COMMENT '到样日期',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `gk_detect_sampling` */

/*Table structure for table `gk_phone_list` */

DROP TABLE IF EXISTS `gk_phone_list`;

CREATE TABLE `gk_phone_list` (
  `id` varchar(32) NOT NULL COMMENT '联系人ID',
  `type` varchar(32) DEFAULT NULL COMMENT '检测类型',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `gk_phone_list` */

/*Table structure for table `gk_phone_warning` */

DROP TABLE IF EXISTS `gk_phone_warning`;

CREATE TABLE `gk_phone_warning` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL COMMENT '预警名',
  `key_word` varchar(32) DEFAULT NULL COMMENT '预警键',
  `prevalue` int(32) DEFAULT NULL COMMENT '预设值',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `gk_phone_warning` */

/*Table structure for table `gk_suggestions` */

DROP TABLE IF EXISTS `gk_suggestions`;

CREATE TABLE `gk_suggestions` (
  `id` varchar(32) NOT NULL,
  `linkman` varchar(32) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `detail` varchar(2000) DEFAULT NULL COMMENT '反馈内容',
  `submit_time` int(32) DEFAULT NULL COMMENT '提交时间',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `gk_suggestions` */

/*Table structure for table `sc_account_cash` */

DROP TABLE IF EXISTS `sc_account_cash`;

CREATE TABLE `sc_account_cash` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `account_id` varchar(32) DEFAULT NULL COMMENT '帐号ID',
  `money` bigint(128) DEFAULT '0' COMMENT '账户余额',
  `freeze_money` bigint(128) DEFAULT '0' COMMENT '冻结金额',
  `bank_card` varchar(50) DEFAULT NULL COMMENT '银行卡号',
  `cardholder` varchar(100) DEFAULT NULL COMMENT '持卡人或者企业信息',
  `bank_name` varchar(400) DEFAULT NULL COMMENT '银行名称',
  `bank_information` varchar(400) DEFAULT NULL COMMENT '开户行信息',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_account_cash` */

/*Table structure for table `sc_account_cashin` */

DROP TABLE IF EXISTS `sc_account_cashin`;

CREATE TABLE `sc_account_cashin` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `account_id` varchar(32) DEFAULT NULL COMMENT '帐号ID',
  `money` bigint(128) DEFAULT NULL COMMENT '充值金额',
  `type` varchar(32) DEFAULT NULL COMMENT '充值方式',
  `accname` varchar(100) DEFAULT NULL COMMENT '账户名',
  `accyh` varchar(100) DEFAULT NULL COMMENT '开户行',
  `acc` varchar(100) DEFAULT NULL COMMENT '帐户号',
  `trade_no` varchar(50) DEFAULT NULL COMMENT '交易流水号',
  `apply_at` varchar(32) DEFAULT NULL COMMENT '申请时间',
  `pay_status` tinyint(4) DEFAULT NULL COMMENT '支付状态',
  `pay_at` varchar(32) DEFAULT NULL COMMENT '支付时间',
  `pay_by` varchar(32) DEFAULT NULL COMMENT '支付处理人',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '处理状态',
  `check_at` varchar(32) DEFAULT NULL COMMENT '处理时间',
  `check_by` varchar(32) DEFAULT NULL COMMENT '处理人',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_account_cashin` */

/*Table structure for table `sc_account_cashout` */

DROP TABLE IF EXISTS `sc_account_cashout`;

CREATE TABLE `sc_account_cashout` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `account_id` varchar(32) DEFAULT NULL COMMENT '帐号ID',
  `money` bigint(128) DEFAULT NULL COMMENT '提现金额',
  `type` varchar(1) DEFAULT NULL COMMENT '提现类型',
  `accname` varchar(200) DEFAULT NULL COMMENT '账户名',
  `accyh` varchar(200) DEFAULT NULL COMMENT '开户行',
  `acc` varchar(100) DEFAULT NULL COMMENT '帐户号',
  `apply_at` varchar(32) DEFAULT NULL COMMENT '申请时间',
  `pay_status` tinyint(4) DEFAULT NULL COMMENT '支付状态',
  `pay_at` varchar(32) DEFAULT NULL COMMENT '支付时间',
  `pay_by` varchar(32) DEFAULT NULL COMMENT '支付处理人',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `check_at` varchar(32) DEFAULT NULL COMMENT '审核时间',
  `check_by` varchar(32) DEFAULT NULL COMMENT '审核人',
  `check_note` varchar(32) DEFAULT NULL COMMENT '审核描述',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_account_cashout` */

/*Table structure for table `sc_account_cashout_picture` */

DROP TABLE IF EXISTS `sc_account_cashout_picture`;

CREATE TABLE `sc_account_cashout_picture` (
  `account_cashout_id` varchar(32) DEFAULT NULL COMMENT '提现记录单id',
  `priority` bigint(128) DEFAULT NULL COMMENT '排序',
  `img` varchar(150) DEFAULT NULL COMMENT '图片',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_account_cashout_picture` */

/*Table structure for table `sc_account_link` */

DROP TABLE IF EXISTS `sc_account_link`;

CREATE TABLE `sc_account_link` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `account_id` varchar(32) DEFAULT NULL COMMENT '账户id',
  `type` tinyint(4) DEFAULT NULL COMMENT '用户类型',
  `table_id` varchar(32) DEFAULT NULL COMMENT '关联表id',
  `basic` tinyint(4) DEFAULT NULL COMMENT '是否主帐号',
  `loginTheme` varchar(100) DEFAULT NULL COMMENT '皮肤样式',
  `loginSidebar` tinyint(1) DEFAULT NULL,
  `loginBoxed` tinyint(1) DEFAULT NULL,
  `loginScroll` tinyint(1) DEFAULT NULL,
  `loginPjax` tinyint(1) DEFAULT NULL,
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_account_link` */

insert  into `sc_account_link`(`id`,`account_id`,`type`,`table_id`,`basic`,`loginTheme`,`loginSidebar`,`loginBoxed`,`loginScroll`,`loginPjax`,`opBy`,`opAt`,`delFlag`) values ('a9a6a6d08a1144a7b2ed124cf3fe60c8','9c82a8e06bc34311ab237b07c2e722c3',NULL,'',NULL,NULL,0,0,0,0,'',1517452972,0);

/*Table structure for table `sc_account_loginlog` */

DROP TABLE IF EXISTS `sc_account_loginlog`;

CREATE TABLE `sc_account_loginlog` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `account_id` varchar(32) DEFAULT NULL COMMENT '帐号ID',
  `login_at` int(32) DEFAULT NULL COMMENT '登录时间',
  `login_ip` varchar(50) DEFAULT NULL COMMENT '登录IP',
  `client_browser` varchar(255) DEFAULT NULL COMMENT '浏览器版本',
  `client_type` varchar(255) DEFAULT NULL COMMENT '客户端类型',
  `client_name` varchar(255) DEFAULT NULL COMMENT '客户端名称',
  `login_type` varchar(20) DEFAULT NULL COMMENT '登录类型',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_account_loginlog` */

/*Table structure for table `sc_account_user` */

DROP TABLE IF EXISTS `sc_account_user`;

CREATE TABLE `sc_account_user` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `loginname` varchar(100) DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(128) DEFAULT NULL COMMENT '昵称',
  `head` varchar(256) DEFAULT NULL COMMENT '头像',
  `password` varchar(128) DEFAULT NULL COMMENT '加密密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '加密盐',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '县区',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `login_num` bigint(128) DEFAULT NULL COMMENT '登录次数',
  `last_ip` varchar(50) DEFAULT NULL COMMENT '最后登录IP',
  `last_time` varchar(32) DEFAULT NULL COMMENT '最后登录时间',
  `duties` varchar(32) DEFAULT NULL COMMENT '职务',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_account_user` */

insert  into `sc_account_user`(`id`,`loginname`,`nickname`,`head`,`password`,`salt`,`mobile`,`email`,`disabled`,`province`,`city`,`area`,`address`,`login_num`,`last_ip`,`last_time`,`duties`,`opBy`,`opAt`,`delFlag`) values ('9c82a8e06bc34311ab237b07c2e722c3','superadmin','超级管理员',NULL,'MabVv00AE4Rrpd38NVRpnu3OdV7Jt1HAAzcIICjZr6c=','Be6+E8iN2zjjVbhAk8sn8A==',NULL,'chenquanlai@qwang.com.cn',0,NULL,NULL,NULL,NULL,0,'127.0.0.1',NULL,NULL,'',1517452972,0),('b21b9a5ef7ff4b3dbdeb27fb3d7974dc','18235226938','18235226938',NULL,'6/+LmQ9F8SdCnQ6ryDQQuQSnlcKSx7stszOp6zOSivA=','01FVG0G/epP0D2fwtbLuiQ==',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',1521536773,0);

/*Table structure for table `sc_account_wx_link` */

DROP TABLE IF EXISTS `sc_account_wx_link`;

CREATE TABLE `sc_account_wx_link` (
  `open_id` varchar(128) NOT NULL COMMENT '微信openid',
  `account_id` varchar(32) NOT NULL COMMENT '用户id',
  `bind_date` varchar(32) NOT NULL COMMENT '绑定时间',
  `status` tinyint(4) NOT NULL COMMENT '绑定状态0:已绑定,1:未绑定',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`open_id`,`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_account_wx_link` */

/*Table structure for table `sc_design_resource` */

DROP TABLE IF EXISTS `sc_design_resource`;

CREATE TABLE `sc_design_resource` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `service_item_id` varchar(32) DEFAULT NULL COMMENT '服务项目id',
  `type` varchar(10) DEFAULT NULL COMMENT '设计资源类型',
  `des` varchar(2000) DEFAULT NULL COMMENT '资源描述',
  `website` varchar(128) DEFAULT NULL COMMENT '在线设计服务网址',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_design_resource` */

/*Table structure for table `sc_experiment_resource` */

DROP TABLE IF EXISTS `sc_experiment_resource`;

CREATE TABLE `sc_experiment_resource` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `service_item_id` varchar(32) DEFAULT NULL COMMENT '服务项目id',
  `des` varchar(2000) DEFAULT NULL COMMENT '检测实验设备及能力描述',
  `status` tinyint(4) DEFAULT NULL COMMENT '检测实验设备当前状态',
  `website` varchar(128) DEFAULT NULL COMMENT '在线检测服务网址',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_experiment_resource` */

/*Table structure for table `sc_expert_chat` */

DROP TABLE IF EXISTS `sc_expert_chat`;

CREATE TABLE `sc_expert_chat` (
  `order_id` varchar(32) DEFAULT NULL COMMENT '对应咨询单ID',
  `expert_id` varchar(32) DEFAULT NULL COMMENT '专家ID',
  `account_id` varchar(32) DEFAULT NULL COMMENT '专家帐号ID',
  `advisory_id` varchar(32) DEFAULT NULL COMMENT '咨询帐号ID',
  `advisory_note` text COMMENT '咨询内容',
  `advisory_time` bigint(128) DEFAULT NULL COMMENT '咨询时间',
  `reply_note` text COMMENT '回复内容',
  `reply_time` bigint(128) DEFAULT NULL COMMENT '回复时间',
  `media_id` varchar(128) DEFAULT NULL COMMENT '上传mediaId',
  `advisory_url` varchar(256) DEFAULT NULL COMMENT '咨询media保存路径Url',
  `reply_url` varchar(256) DEFAULT NULL COMMENT '回复media保存路径Url',
  `media_type` varchar(32) DEFAULT NULL COMMENT '上传media类型',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_expert_chat` */

/*Table structure for table `sc_expert_content` */

DROP TABLE IF EXISTS `sc_expert_content`;

CREATE TABLE `sc_expert_content` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `expert_id` varchar(32) DEFAULT NULL COMMENT '专家ID',
  `title` varchar(80) DEFAULT NULL COMMENT '标题',
  `short_title` varchar(60) DEFAULT NULL COMMENT '短标题',
  `description` varchar(255) DEFAULT NULL COMMENT '内容摘要',
  `author` varchar(50) DEFAULT NULL COMMENT '发布者',
  `picurl` varchar(255) DEFAULT NULL COMMENT '标题图',
  `add_time` varchar(255) DEFAULT NULL COMMENT '新增时间',
  `check_status` int(32) DEFAULT NULL COMMENT '审核状态',
  `check_by` varchar(32) DEFAULT NULL COMMENT '审核人',
  `check_at` varchar(32) DEFAULT NULL COMMENT '审核时间',
  `is_static` int(32) DEFAULT NULL COMMENT '发布状态',
  `pub_time` varchar(255) DEFAULT NULL COMMENT '发布时间',
  `content` text COMMENT '文章正文内容',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_expert_content` */

/*Table structure for table `sc_expert_content_check` */

DROP TABLE IF EXISTS `sc_expert_content_check`;

CREATE TABLE `sc_expert_content_check` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `content_id` varchar(32) DEFAULT NULL COMMENT '专家文章id',
  `check_by` varchar(32) DEFAULT NULL COMMENT '审核人',
  `check_at` varchar(32) DEFAULT NULL COMMENT '审核时间',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `check_note` varchar(256) DEFAULT NULL COMMENT '审核意见',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_expert_content_check` */

/*Table structure for table `sc_expert_content_estemp` */

DROP TABLE IF EXISTS `sc_expert_content_estemp`;

CREATE TABLE `sc_expert_content_estemp` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `contentId` varchar(32) DEFAULT NULL COMMENT '专家文章ID',
  `action` varchar(32) DEFAULT NULL COMMENT '操作类型',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_expert_content_estemp` */

/*Table structure for table `sc_expert_info` */

DROP TABLE IF EXISTS `sc_expert_info`;

CREATE TABLE `sc_expert_info` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `expertin` text COMMENT '擅长领域',
  `note` text COMMENT '个人介绍',
  `total_order` bigint(128) DEFAULT NULL COMMENT '总咨询次数',
  `total_num` bigint(128) DEFAULT NULL COMMENT '总评价次数',
  `total_point` bigint(128) DEFAULT NULL COMMENT '总评分',
  `avg_point` bigint(128) DEFAULT NULL COMMENT '平均分',
  `org_name` varchar(128) DEFAULT NULL COMMENT '任职单位',
  `org_code` varchar(32) DEFAULT NULL COMMENT '组织结构代码',
  `tel` varchar(50) DEFAULT NULL COMMENT '电话',
  `titles` varchar(200) DEFAULT NULL COMMENT '职称',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `industry_code` varchar(32) DEFAULT NULL COMMENT '行业代码',
  `industry_code1` varchar(32) DEFAULT NULL COMMENT '行业代码二级',
  `education` varchar(158) DEFAULT NULL COMMENT '学历',
  `province` varchar(158) DEFAULT NULL COMMENT '省',
  `city` varchar(158) DEFAULT NULL COMMENT '市',
  `county` varchar(32) DEFAULT NULL COMMENT '区/县',
  `address` varchar(158) DEFAULT NULL COMMENT '详细地址',
  `remark` text COMMENT '备注',
  `head` varchar(4000) DEFAULT NULL COMMENT '头像',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `apply_time` bigint(128) DEFAULT NULL COMMENT '申请时间',
  `check_reason` varchar(255) DEFAULT NULL COMMENT '申请驳回理由',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_expert_info` */

/*Table structure for table `sc_expert_info_estemp` */

DROP TABLE IF EXISTS `sc_expert_info_estemp`;

CREATE TABLE `sc_expert_info_estemp` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `expertId` varchar(32) DEFAULT NULL COMMENT '专家ID',
  `action` varchar(32) DEFAULT NULL COMMENT '操作类型',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_expert_info_estemp` */

/*Table structure for table `sc_expert_item` */

DROP TABLE IF EXISTS `sc_expert_item`;

CREATE TABLE `sc_expert_item` (
  `expert_id` varchar(32) DEFAULT NULL COMMENT '专家ID',
  `advisory_type` varchar(32) DEFAULT NULL COMMENT '咨询方式',
  `advisory_item` varchar(32) DEFAULT NULL COMMENT '咨询项目',
  `advisory_money` bigint(128) DEFAULT NULL COMMENT '咨询金额',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_expert_item` */

/*Table structure for table `sc_expert_order` */

DROP TABLE IF EXISTS `sc_expert_order`;

CREATE TABLE `sc_expert_order` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `expert_id` varchar(32) DEFAULT NULL COMMENT '专家ID',
  `org_name` varchar(32) DEFAULT NULL COMMENT '咨询方名称',
  `org_id` varchar(32) DEFAULT NULL COMMENT '咨询方id',
  `user_tel_no` varchar(32) DEFAULT NULL COMMENT '用户短信提醒号码',
  `question_item` varchar(32) DEFAULT NULL COMMENT '问题分类',
  `answer_type` varchar(32) DEFAULT NULL COMMENT '咨询方式',
  `question_title` varchar(128) DEFAULT NULL COMMENT '问题标题',
  `status` int(32) DEFAULT NULL COMMENT '咨询状态',
  `appraise_status` int(32) DEFAULT NULL COMMENT '评价状态',
  `start_time` bigint(128) DEFAULT NULL COMMENT '咨询发起时间',
  `last_time` bigint(128) DEFAULT NULL COMMENT '最近咨询时间',
  `end_time` bigint(128) DEFAULT NULL COMMENT '完成时间',
  `pay_money` bigint(128) DEFAULT NULL COMMENT '付款金额',
  `area_name` varchar(32) DEFAULT NULL COMMENT '所在地区',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_expert_order` */

/*Table structure for table `sc_expert_order_appraise` */

DROP TABLE IF EXISTS `sc_expert_order_appraise`;

CREATE TABLE `sc_expert_order_appraise` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `order_id` varchar(32) DEFAULT NULL COMMENT '订单ID',
  `expert_id` varchar(32) DEFAULT NULL COMMENT '专家id',
  `account_id` varchar(32) DEFAULT NULL COMMENT '咨询人帐号id',
  `note` varchar(255) DEFAULT NULL COMMENT '评价内容',
  `appScore` bigint(44) DEFAULT NULL COMMENT '项目评分',
  `feedAt` varchar(32) DEFAULT NULL COMMENT '评价时间',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `check_by` varchar(32) DEFAULT NULL COMMENT '审核人',
  `check_at` varchar(32) DEFAULT NULL COMMENT '审核时间',
  `check_note` varchar(256) DEFAULT NULL COMMENT '审核意见',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_expert_order_appraise` */

/*Table structure for table `sc_expert_order_payment` */

DROP TABLE IF EXISTS `sc_expert_order_payment`;

CREATE TABLE `sc_expert_order_payment` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `expert_id` varchar(32) DEFAULT NULL COMMENT '专家ID',
  `order_id` varchar(32) DEFAULT NULL COMMENT '订单ID',
  `pay_type` tinyint(4) DEFAULT NULL COMMENT '支付方式',
  `pay_money` bigint(128) DEFAULT NULL COMMENT '实付金额',
  `pay_no` varchar(32) DEFAULT NULL COMMENT '支付帐号',
  `trade_no` varchar(100) DEFAULT NULL COMMENT '交易流水号',
  `pay_at` varchar(32) DEFAULT NULL COMMENT '支付时间',
  `finish_at` varchar(32) DEFAULT NULL COMMENT '完成时间',
  `pay_status` tinyint(4) DEFAULT NULL COMMENT '支付状态',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_expert_order_payment` */

/*Table structure for table `sc_flat_content_estemp` */

DROP TABLE IF EXISTS `sc_flat_content_estemp`;

CREATE TABLE `sc_flat_content_estemp` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `contentId` varchar(32) DEFAULT NULL COMMENT '文章ID',
  `action` varchar(32) DEFAULT NULL COMMENT '操作类型',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_flat_content_estemp` */

/*Table structure for table `sc_flat_info` */

DROP TABLE IF EXISTS `sc_flat_info`;

CREATE TABLE `sc_flat_info` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `unitId` varchar(32) DEFAULT NULL COMMENT '所属单位',
  `flat_id` varchar(100) DEFAULT NULL COMMENT '平台编码',
  `flat_type` varchar(32) DEFAULT NULL COMMENT '平台类型',
  `flat_name` varchar(256) DEFAULT NULL COMMENT '服务平台名称',
  `org_property` varchar(32) DEFAULT NULL COMMENT '主体单位性质',
  `org_code` varchar(32) DEFAULT NULL COMMENT '组织机构代码',
  `org_name` varchar(256) DEFAULT NULL COMMENT '主体机构名称',
  `legal_person` varchar(256) DEFAULT NULL COMMENT '主体法定代表人',
  `area_county` varchar(6) DEFAULT NULL COMMENT '行政区划代码',
  `linkman` varchar(20) DEFAULT NULL COMMENT '联系人',
  `tel` varchar(50) DEFAULT NULL COMMENT '电话',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `email` varchar(128) DEFAULT NULL COMMENT '电子邮箱',
  `website` varchar(128) DEFAULT NULL COMMENT '网址',
  `address` varchar(512) DEFAULT NULL COMMENT '通讯地址',
  `postcode` varchar(6) DEFAULT NULL COMMENT '邮政编码',
  `main_remark` varchar(4000) DEFAULT NULL COMMENT '平台介绍',
  `url` varchar(128) DEFAULT NULL COMMENT '资源来源',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '县区',
  `build_start` varchar(20) DEFAULT NULL COMMENT '建设周期起',
  `build_end` varchar(20) DEFAULT NULL COMMENT '建设周期止',
  `space_has` tinyint(4) DEFAULT NULL COMMENT '有无办公场地',
  `space_note` varchar(512) DEFAULT NULL COMMENT '办公场地说明',
  `equipment_has` tinyint(4) DEFAULT NULL COMMENT '有无设备采购',
  `equipment_note` varchar(512) DEFAULT NULL COMMENT '设备采购说明',
  `informatization_has` tinyint(4) DEFAULT NULL COMMENT '有无信息化',
  `informatization_note` varchar(512) DEFAULT NULL COMMENT '信息化说明',
  `attachment` text COMMENT '附件',
  `status` tinyint(4) DEFAULT NULL COMMENT '项目状态',
  `total_money` bigint(128) DEFAULT NULL COMMENT '总投资',
  `self_money` bigint(128) DEFAULT NULL COMMENT '自筹资金',
  `gov_money` bigint(128) DEFAULT NULL COMMENT '政策补助资金',
  `send_unitid` varchar(32) DEFAULT NULL COMMENT '申请人单位',
  `send_by` varchar(32) DEFAULT NULL COMMENT '申请人',
  `send_at` varchar(32) DEFAULT NULL COMMENT '申请时间',
  `check_unitid` varchar(32) DEFAULT NULL COMMENT '审核人单位',
  `check_by` varchar(32) DEFAULT NULL COMMENT '审核人',
  `check_at` bigint(128) DEFAULT NULL COMMENT '审核时间',
  `check_status` bigint(128) DEFAULT NULL COMMENT '审核状态',
  `check_note` varchar(256) DEFAULT NULL COMMENT '审核意见',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_flat_info` */

/*Table structure for table `sc_flat_info_change` */

DROP TABLE IF EXISTS `sc_flat_info_change`;

CREATE TABLE `sc_flat_info_change` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `flat_id` varchar(100) DEFAULT NULL COMMENT '平台编码',
  `old_obj` text COMMENT '变更前Object',
  `new_obj` text COMMENT '变更后Object',
  `change_colum` varchar(256) DEFAULT NULL COMMENT '变更项目字段',
  `change_name` varchar(256) DEFAULT NULL COMMENT '变更项目名称',
  `createAt` bigint(128) DEFAULT NULL COMMENT '变更日期',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_flat_info_change` */

/*Table structure for table `sc_flat_info_changelog` */

DROP TABLE IF EXISTS `sc_flat_info_changelog`;

CREATE TABLE `sc_flat_info_changelog` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `flat_id` varchar(100) DEFAULT NULL COMMENT '平台编码',
  `createAt` bigint(128) DEFAULT NULL COMMENT '变更申请时间',
  `send_unitid` varchar(32) DEFAULT NULL COMMENT '申请人单位',
  `send_by` varchar(32) DEFAULT NULL COMMENT '申请人',
  `send_at` varchar(32) DEFAULT NULL COMMENT '申请时间',
  `check_unitid` varchar(32) DEFAULT NULL COMMENT '审核人单位',
  `check_by` varchar(32) DEFAULT NULL COMMENT '审核人',
  `check_at` varchar(32) DEFAULT NULL COMMENT '审核时间',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `check_note` varchar(255) DEFAULT NULL COMMENT '审核意见',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_flat_info_changelog` */

/*Table structure for table `sc_flat_info_progress` */

DROP TABLE IF EXISTS `sc_flat_info_progress`;

CREATE TABLE `sc_flat_info_progress` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `flat_id` varchar(100) DEFAULT NULL COMMENT '平台编码',
  `finish_num` varchar(32) DEFAULT NULL COMMENT '总完成进度',
  `finish_money` bigint(128) DEFAULT NULL COMMENT '完成投资额',
  `space_num` varchar(32) DEFAULT NULL COMMENT '办公场地完成面积',
  `space_money` bigint(128) DEFAULT NULL COMMENT '办公场地投资',
  `software_num` varchar(32) DEFAULT NULL COMMENT '软件子系统完成套数',
  `software_money` bigint(128) DEFAULT NULL COMMENT '软件子系统对应金额',
  `equipment_num` varchar(32) DEFAULT NULL COMMENT '设备采购套数',
  `equipment_money` bigint(128) DEFAULT NULL COMMENT '设备采购金额',
  `other_money` bigint(128) DEFAULT NULL COMMENT '其他金额',
  `unsolved` varchar(32) DEFAULT NULL COMMENT '待解决问题',
  `attachment` text COMMENT '附件',
  `sendDate` varchar(32) DEFAULT NULL COMMENT '报送日期',
  `send_time` datetime DEFAULT NULL COMMENT '报送时间',
  `sendTel` varchar(32) DEFAULT NULL COMMENT '报送人电话',
  `send_person` varchar(32) DEFAULT NULL COMMENT '报送人',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `type` tinyint(4) DEFAULT NULL COMMENT '分类',
  `money_warn_status` tinyint(4) DEFAULT '0' COMMENT '资金预警',
  `unsolv_warn_status` tinyint(4) DEFAULT '0' COMMENT '问题预警',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_flat_info_progress` */

/*Table structure for table `sc_flat_info_progresslog` */

DROP TABLE IF EXISTS `sc_flat_info_progresslog`;

CREATE TABLE `sc_flat_info_progresslog` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `flat_id` varchar(100) DEFAULT NULL COMMENT '平台编码',
  `progress_id` varchar(32) DEFAULT NULL COMMENT '进度表ID',
  `send_unitid` varchar(32) DEFAULT NULL COMMENT '申请人单位',
  `send_by` varchar(32) DEFAULT NULL COMMENT '申请人',
  `send_at` varchar(32) DEFAULT NULL COMMENT '申请时间',
  `check_unitid` varchar(32) DEFAULT NULL COMMENT '审核人单位',
  `check_by` varchar(32) DEFAULT NULL COMMENT '审核人',
  `check_at` varchar(32) DEFAULT NULL COMMENT '审核时间',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `check_note` varchar(255) DEFAULT NULL COMMENT '审核意见',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_flat_info_progresslog` */

/*Table structure for table `sc_notice` */

DROP TABLE IF EXISTS `sc_notice`;

CREATE TABLE `sc_notice` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `title` varchar(80) DEFAULT NULL COMMENT '标题',
  `short_title` varchar(60) DEFAULT NULL COMMENT '短标题',
  `description` varchar(255) DEFAULT NULL COMMENT '内容摘要',
  `author` varchar(50) DEFAULT NULL COMMENT '发布者',
  `picurl` varchar(255) DEFAULT NULL COMMENT '标题图',
  `add_time` varchar(255) DEFAULT NULL COMMENT '添加时间',
  `pub_time` varchar(255) DEFAULT NULL COMMENT '发布时间',
  `content` text COMMENT '正文内容',
  `type` tinyint(4) DEFAULT NULL COMMENT '通知类型',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_notice` */

/*Table structure for table `sc_org_in_flat` */

DROP TABLE IF EXISTS `sc_org_in_flat`;

CREATE TABLE `sc_org_in_flat` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `unitId` varchar(32) DEFAULT NULL COMMENT '所属单位',
  `flat_id` varchar(100) DEFAULT NULL COMMENT '平台编码',
  `flat_name` varchar(256) DEFAULT NULL COMMENT '平台名称',
  `org_id` varchar(100) DEFAULT NULL COMMENT '机构编码',
  `org_name` varchar(100) DEFAULT NULL COMMENT '机构名称',
  `flag` varchar(1) DEFAULT NULL COMMENT '是否是窗口平台的当前有效的服务机构',
  `begin_date` date DEFAULT NULL COMMENT '开始时间',
  `end_date` date DEFAULT NULL COMMENT '结束时间',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_org_in_flat` */

/*Table structure for table `sc_org_info` */

DROP TABLE IF EXISTS `sc_org_info`;

CREATE TABLE `sc_org_info` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `account_id` varchar(32) DEFAULT NULL COMMENT '账户id',
  `org_name` varchar(256) DEFAULT NULL COMMENT '服务对象名称',
  `org_property` varchar(32) DEFAULT NULL COMMENT '单位性质',
  `org_code` varchar(20) DEFAULT NULL COMMENT '组织机构代码或工商注册号',
  `legal_person` varchar(256) DEFAULT NULL COMMENT '法定代表人',
  `register_type` varchar(32) DEFAULT NULL COMMENT '企业注册类型一级',
  `register_type1` varchar(32) DEFAULT NULL COMMENT '企业注册类型二级',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area_county` varchar(32) DEFAULT NULL COMMENT '行政区划代码',
  `linkman` varchar(20) DEFAULT NULL COMMENT '联系人',
  `tel` varchar(50) DEFAULT NULL COMMENT '电话',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `email` varchar(128) DEFAULT NULL COMMENT '电子邮箱',
  `website` varchar(128) DEFAULT NULL COMMENT '网址',
  `address` varchar(512) DEFAULT NULL COMMENT '通讯地址',
  `postcode` varchar(6) DEFAULT NULL COMMENT '邮政编码',
  `industry_code` varchar(32) DEFAULT NULL COMMENT '行业代码',
  `industry_code1` varchar(32) DEFAULT NULL COMMENT '行业代码二级',
  `main_remark` varchar(4000) DEFAULT NULL COMMENT '主要产品或者主营业务',
  `share_type` varchar(5) DEFAULT NULL COMMENT '控股情况',
  `opened_time` varchar(7) DEFAULT NULL COMMENT '开业时间',
  `photo` varchar(128) DEFAULT NULL COMMENT '企业LOGO图片',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `signed` tinyint(4) DEFAULT NULL COMMENT '是否签约',
  `checked` tinyint(4) DEFAULT NULL COMMENT '是否认证',
  `total` bigint(80) DEFAULT NULL COMMENT '总评价次数',
  `point` bigint(80) DEFAULT NULL COMMENT '总评价分数',
  `point_avg` varchar(32) DEFAULT NULL COMMENT '平均评价分数',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_org_info` */

/*Table structure for table `sc_org_info_check` */

DROP TABLE IF EXISTS `sc_org_info_check`;

CREATE TABLE `sc_org_info_check` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `org_id` varchar(32) DEFAULT NULL COMMENT '企业信息id',
  `check_by` varchar(32) DEFAULT NULL COMMENT '审核人',
  `check_at` varchar(32) DEFAULT NULL COMMENT '审核时间',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `check_note` varchar(256) DEFAULT NULL COMMENT '审核意见',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_org_info_check` */

/*Table structure for table `sc_org_notic_config` */

DROP TABLE IF EXISTS `sc_org_notic_config`;

CREATE TABLE `sc_org_notic_config` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `is_wx_type` tinyint(4) DEFAULT NULL COMMENT '微信通知',
  `is_msg_type` tinyint(4) DEFAULT NULL COMMENT '短信通知',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `unitId` varchar(32) DEFAULT NULL COMMENT '所属单位',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_org_notic_config` */

/*Table structure for table `sc_org_report` */

DROP TABLE IF EXISTS `sc_org_report`;

CREATE TABLE `sc_org_report` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `org_id` varchar(100) DEFAULT NULL COMMENT '服务对象编码',
  `report_date` varchar(4) DEFAULT NULL COMMENT '年度',
  `income` bigint(128) DEFAULT NULL COMMENT '年营业收入',
  `contribution_margin` bigint(128) DEFAULT NULL COMMENT '年利润总额',
  `employees_num` bigint(128) DEFAULT NULL COMMENT '从业人数',
  `asset` bigint(128) DEFAULT NULL COMMENT '资产总额',
  `top_money` bigint(128) DEFAULT NULL COMMENT '上限',
  `end_time` bigint(128) DEFAULT NULL COMMENT '结束时间',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_org_report` */

/*Table structure for table `sc_org_requirement` */

DROP TABLE IF EXISTS `sc_org_requirement`;

CREATE TABLE `sc_org_requirement` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `type` varchar(32) DEFAULT NULL COMMENT '需求类型',
  `type1` varchar(32) DEFAULT NULL COMMENT '需求类型',
  `type2` varchar(32) DEFAULT NULL COMMENT '需求类型',
  `front_type` varchar(32) DEFAULT NULL COMMENT '需求前台类型',
  `org_id` varchar(32) DEFAULT NULL COMMENT '企业ID',
  `startup_id` varchar(32) DEFAULT NULL COMMENT '创业者ID',
  `account_id` varchar(32) DEFAULT NULL COMMENT '发布需求的用户',
  `area_county` varchar(32) DEFAULT NULL COMMENT '需求发布者的行政区划',
  `title` varchar(128) DEFAULT NULL COMMENT '需求标题',
  `des` text COMMENT '需求描述',
  `bid_start` bigint(80) DEFAULT NULL COMMENT '竞标开始时间',
  `bid_end` bigint(80) DEFAULT NULL COMMENT '竞标截止时间',
  `price` bigint(128) DEFAULT NULL COMMENT '预算价格',
  `linkman` varchar(20) DEFAULT NULL COMMENT '联系人',
  `tel` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `expect_service_mode` varchar(1) DEFAULT NULL COMMENT '期望服务模式',
  `expect_service_method` varchar(512) DEFAULT NULL COMMENT '期望服务方式',
  `remark` text COMMENT '备注',
  `status` tinyint(4) DEFAULT NULL COMMENT '需求响应状态',
  `localization` tinyint(4) DEFAULT NULL COMMENT '是否要求本地化服务',
  `srv_org_type` varchar(3) DEFAULT NULL COMMENT '服务提供组织要求',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `pub_time` varchar(32) DEFAULT NULL COMMENT '发布时间',
  `url` varchar(128) DEFAULT NULL COMMENT '资源来源',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_org_requirement` */

/*Table structure for table `sc_org_requirement_check` */

DROP TABLE IF EXISTS `sc_org_requirement_check`;

CREATE TABLE `sc_org_requirement_check` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `requirement_id` varchar(32) DEFAULT NULL COMMENT '需求id',
  `check_by` varchar(32) DEFAULT NULL COMMENT '审核人',
  `check_at` varchar(32) DEFAULT NULL COMMENT '审核时间',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `check_note` varchar(256) DEFAULT NULL COMMENT '审核意见',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_org_requirement_check` */

/*Table structure for table `sc_org_requirement_class` */

DROP TABLE IF EXISTS `sc_org_requirement_class`;

CREATE TABLE `sc_org_requirement_class` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `path` varchar(100) DEFAULT NULL COMMENT '树路径',
  `code` varchar(20) DEFAULT NULL COMMENT '编码',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `hasChildren` tinyint(1) DEFAULT NULL COMMENT '有子节点',
  `url` varchar(128) DEFAULT NULL COMMENT '分类链接',
  `nav_img` varchar(128) DEFAULT NULL COMMENT '导航图片',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_org_requirement_class` */

/*Table structure for table `sc_org_requirement_dict` */

DROP TABLE IF EXISTS `sc_org_requirement_dict`;

CREATE TABLE `sc_org_requirement_dict` (
  `classId` varchar(32) DEFAULT NULL,
  `dictId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_org_requirement_dict` */

/*Table structure for table `sc_org_requirement_estemp` */

DROP TABLE IF EXISTS `sc_org_requirement_estemp`;

CREATE TABLE `sc_org_requirement_estemp` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `requirementId` varchar(32) DEFAULT NULL COMMENT '需求ID',
  `action` varchar(32) DEFAULT NULL COMMENT '操作类型',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_org_requirement_estemp` */

/*Table structure for table `sc_org_requirement_resp` */

DROP TABLE IF EXISTS `sc_org_requirement_resp`;

CREATE TABLE `sc_org_requirement_resp` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `org_id` varchar(32) DEFAULT NULL COMMENT '报名机构id',
  `requirement_id` varchar(32) DEFAULT NULL COMMENT '需求id',
  `title` varchar(128) DEFAULT NULL COMMENT '响应单标题',
  `type` varchar(32) DEFAULT NULL COMMENT '响应方式',
  `offer` bigint(128) DEFAULT NULL COMMENT '出价金额',
  `linkman` varchar(20) DEFAULT NULL COMMENT '服务联系人',
  `tel` varchar(50) DEFAULT NULL COMMENT '服务联系电话',
  `ser_remark` varchar(2000) DEFAULT NULL COMMENT '服务方备注',
  `is_accept` tinyint(4) DEFAULT NULL COMMENT '服务机构是否接受',
  `is_bid` tinyint(4) DEFAULT NULL COMMENT '是否中标',
  `org_remark` varchar(2000) DEFAULT NULL COMMENT '需求方备注',
  `is_order` tinyint(4) DEFAULT NULL COMMENT '是否生成订单',
  `order_no` varchar(32) DEFAULT NULL COMMENT '订单号',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_org_requirement_resp` */

/*Table structure for table `sc_originality_data` */

DROP TABLE IF EXISTS `sc_originality_data`;

CREATE TABLE `sc_originality_data` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `originality_id` varchar(32) DEFAULT NULL COMMENT '创意ID',
  `name` varchar(128) DEFAULT NULL COMMENT '资料名称',
  `content` varchar(4000) DEFAULT NULL COMMENT '资料内容',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_originality_data` */

/*Table structure for table `sc_originality_info` */

DROP TABLE IF EXISTS `sc_originality_info`;

CREATE TABLE `sc_originality_info` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `startup_id` varchar(32) DEFAULT NULL COMMENT '创业者ID',
  `name` varchar(128) DEFAULT NULL COMMENT '创意名称',
  `type` varchar(32) DEFAULT NULL COMMENT '创意类型一级分类',
  `type1` varchar(32) DEFAULT NULL COMMENT '创意类型二级分类',
  `des` varchar(4000) DEFAULT NULL COMMENT '创意描述',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_originality_info` */

/*Table structure for table `sc_process_resource` */

DROP TABLE IF EXISTS `sc_process_resource`;

CREATE TABLE `sc_process_resource` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `des` varchar(2000) DEFAULT NULL COMMENT '加工设备及能力描述',
  `status` tinyint(4) DEFAULT NULL COMMENT '加工资源当前状态',
  `website` varchar(128) DEFAULT NULL COMMENT '在线加工服务网址',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `service_item_id` varchar(32) DEFAULT NULL COMMENT '服务项目id',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_process_resource` */

/*Table structure for table `sc_service_activity` */

DROP TABLE IF EXISTS `sc_service_activity`;

CREATE TABLE `sc_service_activity` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `service_id` varchar(32) DEFAULT NULL COMMENT '对应服务机构ID',
  `title` varchar(128) DEFAULT NULL COMMENT '活动主题',
  `activity_name` varchar(128) DEFAULT NULL COMMENT '活动名称',
  `activity_type` varchar(32) DEFAULT NULL COMMENT '活动类别',
  `content` text COMMENT '活动内容',
  `activity_address` varchar(512) DEFAULT NULL COMMENT '活动地点',
  `start_time` varchar(32) DEFAULT NULL COMMENT '活动开始时间',
  `end_time` varchar(32) DEFAULT NULL COMMENT '活动结束时间',
  `signup_start_time` varchar(32) DEFAULT NULL COMMENT '报名开始时间',
  `signup_end_time` varchar(32) DEFAULT NULL COMMENT '报名截止时间',
  `send_at` varchar(32) DEFAULT NULL COMMENT '活动申请时间',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area_county` varchar(32) DEFAULT NULL COMMENT '区',
  `create_org_id` varchar(100) DEFAULT NULL COMMENT '主办单位编码',
  `create_org_code` varchar(15) DEFAULT NULL COMMENT '主办单位组织机构代码',
  `create_org_type` char(1) DEFAULT NULL COMMENT '主办单位类型',
  `create_org_name` varchar(128) DEFAULT NULL COMMENT '主办单位名称',
  `co_org_name` varchar(512) DEFAULT NULL COMMENT '协办单位名称',
  `form_item` varchar(4000) DEFAULT NULL COMMENT '报名填写项目',
  `summary` varchar(4000) DEFAULT NULL COMMENT '活动总结',
  `top_img` varchar(256) DEFAULT NULL COMMENT '顶部大图',
  `share_img` varchar(256) DEFAULT NULL COMMENT '分享小图',
  `check_unitid` varchar(32) DEFAULT NULL COMMENT '审核人单位',
  `check_by` varchar(32) DEFAULT NULL COMMENT '审核人',
  `check_at` bigint(128) DEFAULT NULL COMMENT '审核时间',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `status` tinyint(4) DEFAULT NULL COMMENT '活动状态',
  `check_note` varchar(256) DEFAULT NULL COMMENT '审核意见',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `url` varchar(128) DEFAULT NULL COMMENT '资源来源',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_activity` */

/*Table structure for table `sc_service_activity_check` */

DROP TABLE IF EXISTS `sc_service_activity_check`;

CREATE TABLE `sc_service_activity_check` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `activity_id` varchar(32) DEFAULT NULL COMMENT '服务活动id',
  `check_by` varchar(32) DEFAULT NULL COMMENT '审核人',
  `check_at` varchar(32) DEFAULT NULL COMMENT '审核时间',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `check_note` varchar(256) DEFAULT NULL COMMENT '审核意见',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_activity_check` */

/*Table structure for table `sc_service_activity_class` */

DROP TABLE IF EXISTS `sc_service_activity_class`;

CREATE TABLE `sc_service_activity_class` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `code` varchar(20) DEFAULT NULL COMMENT '编码',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `url` varchar(128) DEFAULT NULL COMMENT '分类链接',
  `nav_img` varchar(128) DEFAULT NULL COMMENT '导航图片',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_activity_class` */

/*Table structure for table `sc_service_activity_dict` */

DROP TABLE IF EXISTS `sc_service_activity_dict`;

CREATE TABLE `sc_service_activity_dict` (
  `classId` varchar(32) DEFAULT NULL,
  `dictId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_activity_dict` */

/*Table structure for table `sc_service_activity_estemp` */

DROP TABLE IF EXISTS `sc_service_activity_estemp`;

CREATE TABLE `sc_service_activity_estemp` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `activityId` varchar(32) DEFAULT NULL COMMENT '服务活动ID',
  `action` varchar(32) DEFAULT NULL COMMENT '操作类型',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_activity_estemp` */

/*Table structure for table `sc_service_activity_signup` */

DROP TABLE IF EXISTS `sc_service_activity_signup`;

CREATE TABLE `sc_service_activity_signup` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `account_id` varchar(32) DEFAULT NULL COMMENT '报名人用户帐号ID',
  `activity_id` varchar(32) DEFAULT NULL COMMENT '活动id',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `unit_name` varchar(128) DEFAULT NULL COMMENT '单位名称',
  `unit_post` varchar(128) DEFAULT NULL COMMENT '职务',
  `person_num` bigint(128) DEFAULT NULL COMMENT '参与人数',
  `sign_src` tinyint(4) DEFAULT NULL COMMENT '报名来源',
  `sign_at` varchar(32) DEFAULT NULL COMMENT '报名时间',
  `sign_status` tinyint(4) DEFAULT NULL COMMENT '签到状态',
  `sign_in_type` tinyint(4) DEFAULT NULL COMMENT '签到方式',
  `sign_in_at` varchar(32) DEFAULT NULL COMMENT '签到时间',
  `check_at` varchar(32) DEFAULT NULL COMMENT '审核时间',
  `check_note` varchar(255) DEFAULT NULL COMMENT '审核意见',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_activity_signup` */

/*Table structure for table `sc_service_case` */

DROP TABLE IF EXISTS `sc_service_case`;

CREATE TABLE `sc_service_case` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `unitId` varchar(32) DEFAULT NULL COMMENT '所属单位',
  `service_item_id` varchar(100) DEFAULT NULL COMMENT '服务项目编码',
  `service_name` varchar(512) DEFAULT NULL COMMENT '服务名称',
  `title` varchar(512) DEFAULT NULL COMMENT '成功案例标题',
  `content` varchar(4000) DEFAULT NULL COMMENT '成功案例描述',
  `url` varchar(128) DEFAULT NULL COMMENT '资源来源',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_case` */

/*Table structure for table `sc_service_class` */

DROP TABLE IF EXISTS `sc_service_class`;

CREATE TABLE `sc_service_class` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `path` varchar(100) DEFAULT NULL COMMENT '树路径',
  `code` varchar(20) DEFAULT NULL COMMENT '编码',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `hasChildren` tinyint(1) DEFAULT NULL COMMENT '有子节点',
  `url` varchar(128) DEFAULT NULL COMMENT '分类链接',
  `nav_img` varchar(128) DEFAULT NULL COMMENT '导航图片',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SERVICE_CLASS_PATH` (`path`),
  UNIQUE KEY `INDEX_SERVICE_CLASS_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_class` */

/*Table structure for table `sc_service_class_dict` */

DROP TABLE IF EXISTS `sc_service_class_dict`;

CREATE TABLE `sc_service_class_dict` (
  `classId` varchar(32) DEFAULT NULL,
  `dictId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_class_dict` */

/*Table structure for table `sc_service_credit` */

DROP TABLE IF EXISTS `sc_service_credit`;

CREATE TABLE `sc_service_credit` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `service_order_no` int(32) DEFAULT NULL COMMENT '服务过程顺序号',
  `order_id` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `service_type` varchar(1) DEFAULT NULL COMMENT '服务类型',
  `service_class` varchar(32) DEFAULT NULL COMMENT '服务分类',
  `service_class2` varchar(32) DEFAULT NULL COMMENT '服务分类三级',
  `service_class1` varchar(32) DEFAULT NULL COMMENT '服务分类二级',
  `service_org_id` varchar(100) DEFAULT NULL COMMENT '平台或服务机构编码',
  `service_org_name` varchar(256) DEFAULT NULL COMMENT '平台或服务机构名称',
  `area_county` varchar(32) DEFAULT NULL COMMENT '平台或服务机构地区编码',
  `service_item_id` varchar(100) DEFAULT NULL COMMENT '服务项目编码',
  `service_name` varchar(512) DEFAULT NULL COMMENT '服务名称',
  `service_desc` varchar(4000) DEFAULT NULL COMMENT '服务描述',
  `served_org_name` varchar(256) DEFAULT NULL COMMENT '服务对象企业名称',
  `served_account_id` varchar(32) DEFAULT NULL COMMENT '服务对象账户id',
  `served_org_linkman` varchar(100) DEFAULT NULL COMMENT '服务对象联系人',
  `served_org_tel` varchar(50) DEFAULT NULL COMMENT '服务对象联系电话',
  `server_method` varchar(256) DEFAULT NULL COMMENT '主要服务方式',
  `server_date_apply` datetime DEFAULT NULL COMMENT '服务申请时间',
  `server_date_accept` datetime DEFAULT NULL COMMENT '服务受理时间',
  `server_date_end` datetime DEFAULT NULL COMMENT '服务结束时间',
  `server_result` varchar(4000) DEFAULT NULL COMMENT '服务成果',
  `server_income` bigint(128) DEFAULT NULL COMMENT '服务收入',
  `allowance` bigint(128) DEFAULT NULL COMMENT '财政拨款',
  `served_org_evaluation` char(1) DEFAULT NULL COMMENT '服务评价',
  `served_org_remark` varchar(4000) DEFAULT NULL COMMENT '服务意见',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_credit` */

/*Table structure for table `sc_service_item` */

DROP TABLE IF EXISTS `sc_service_item`;

CREATE TABLE `sc_service_item` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `account_id` varchar(32) DEFAULT NULL COMMENT '帐号ID',
  `service_source` varchar(1) DEFAULT NULL COMMENT '服务来源',
  `owner_id` varchar(100) DEFAULT NULL COMMENT '平台编码或者机构编码',
  `owner_name` varchar(100) DEFAULT NULL COMMENT '平台或者机构名称',
  `service_name` varchar(512) DEFAULT NULL COMMENT '服务名称',
  `register_date` varchar(32) DEFAULT NULL COMMENT '注册时间',
  `used_num` bigint(128) DEFAULT '0' COMMENT '服务次数',
  `service_class2` varchar(32) DEFAULT NULL COMMENT '服务分类三级',
  `service_class1` varchar(32) DEFAULT NULL COMMENT '服务分类二级',
  `service_class` varchar(32) DEFAULT NULL COMMENT '服务分类一级',
  `before_service_class` varchar(32) DEFAULT NULL COMMENT '前台服务分类',
  `linkman` varchar(20) DEFAULT NULL COMMENT '服务联系人',
  `tel` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(128) DEFAULT NULL COMMENT '电子邮箱',
  `service_object` varchar(50) DEFAULT NULL COMMENT '服务对象',
  `service_method` varchar(256) DEFAULT NULL COMMENT '服务方式',
  `service_procedure` varchar(4000) DEFAULT NULL COMMENT '服务和服务流程描述',
  `charge_method` varchar(2000) DEFAULT NULL COMMENT '收费模式',
  `url` varchar(128) DEFAULT NULL COMMENT '资源来源',
  `money` bigint(128) DEFAULT NULL COMMENT '收费金额',
  `prepaid_money` bigint(128) DEFAULT NULL COMMENT '服务预付金额设置',
  `pay_money` bigint(128) DEFAULT NULL COMMENT '应付金额',
  `use_coupon` varchar(1) DEFAULT NULL COMMENT '是否可用电子券',
  `checkStatus` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `createAt` bigint(128) DEFAULT NULL COMMENT '操作时间',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `qecode` mediumblob COMMENT '产品二维码',
  `sales` bigint(120) DEFAULT '0' COMMENT '销量',
  `total` bigint(80) DEFAULT '0' COMMENT '总评价次数',
  `praise_total` bigint(80) DEFAULT '0' COMMENT '好评次数',
  `type` varchar(1) DEFAULT '0' COMMENT '产品类型',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_item` */

/*Table structure for table `sc_service_item_check` */

DROP TABLE IF EXISTS `sc_service_item_check`;

CREATE TABLE `sc_service_item_check` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `item_id` varchar(32) DEFAULT NULL COMMENT '服务产品id',
  `check_by` varchar(32) DEFAULT NULL COMMENT '审核人',
  `check_at` varchar(32) DEFAULT NULL COMMENT '审核时间',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `check_note` varchar(256) DEFAULT NULL COMMENT '审核意见',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_item_check` */

/*Table structure for table `sc_service_item_estemp` */

DROP TABLE IF EXISTS `sc_service_item_estemp`;

CREATE TABLE `sc_service_item_estemp` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `itemId` varchar(32) DEFAULT NULL COMMENT '服务产品ID',
  `action` varchar(32) DEFAULT NULL COMMENT '操作类型',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_item_estemp` */

/*Table structure for table `sc_service_item_picture` */

DROP TABLE IF EXISTS `sc_service_item_picture`;

CREATE TABLE `sc_service_item_picture` (
  `service_item_id` varchar(32) DEFAULT NULL COMMENT '服务项目id',
  `priority` bigint(128) DEFAULT NULL COMMENT '排序',
  `img` varchar(150) DEFAULT NULL COMMENT '图片',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_item_picture` */

/*Table structure for table `sc_service_item_test_pro` */

DROP TABLE IF EXISTS `sc_service_item_test_pro`;

CREATE TABLE `sc_service_item_test_pro` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `item_id` varchar(32) DEFAULT NULL COMMENT '服务产品id',
  `test_class` varchar(100) DEFAULT NULL COMMENT '检测分类',
  `test_pro` varchar(500) DEFAULT NULL COMMENT '检测项目',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_item_test_pro` */

/*Table structure for table `sc_service_menu` */

DROP TABLE IF EXISTS `sc_service_menu`;

CREATE TABLE `sc_service_menu` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `path` varchar(100) DEFAULT NULL COMMENT '树路径',
  `name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `aliasName` varchar(100) DEFAULT NULL COMMENT '菜单别名',
  `system` varchar(10) DEFAULT NULL COMMENT '所属系统',
  `type` varchar(10) DEFAULT NULL COMMENT '资源类型',
  `href` varchar(255) DEFAULT NULL COMMENT '菜单链接',
  `target` varchar(50) DEFAULT NULL COMMENT '打开方式',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `isShow` tinyint(1) DEFAULT NULL COMMENT '是否显示',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `note` varchar(255) DEFAULT NULL COMMENT '菜单介绍',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `hasChildren` tinyint(1) DEFAULT NULL COMMENT '有子节点',
  `mtype` tinyint(4) DEFAULT NULL COMMENT '菜单类型',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SC_SERVICE_MENU_PATH` (`path`),
  UNIQUE KEY `INDEX_SC_SERVICE_MENU_PREM` (`permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_menu` */

/*Table structure for table `sc_service_order` */

DROP TABLE IF EXISTS `sc_service_order`;

CREATE TABLE `sc_service_order` (
  `id` varchar(32) NOT NULL COMMENT '订单号',
  `service_id` varchar(32) DEFAULT NULL COMMENT '服务机构id',
  `item_id` varchar(32) DEFAULT NULL COMMENT '服务项目id',
  `type` tinyint(4) DEFAULT NULL COMMENT '订单类型',
  `account_id` varchar(32) DEFAULT NULL COMMENT '服务对象帐号ID',
  `order_status` int(32) DEFAULT '0' COMMENT '订单状态',
  `coupon_money` bigint(128) DEFAULT NULL COMMENT '电子券抵扣金额',
  `use_coupon` varchar(1) DEFAULT NULL COMMENT '是否使用电子券',
  `money` bigint(128) DEFAULT NULL COMMENT '订单金额',
  `pay_money` bigint(128) DEFAULT NULL COMMENT '应付金额',
  `pay_type` tinyint(4) DEFAULT NULL COMMENT '支付方式',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  `order_linkman` varchar(50) DEFAULT NULL COMMENT '联系人',
  `order_tel` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `pay_at` varchar(32) DEFAULT NULL COMMENT '付款时间',
  `order_at` varchar(32) DEFAULT NULL COMMENT '下单时间',
  `appraise_state` tinyint(4) DEFAULT '0' COMMENT '评论状态',
  `complaints_state` tinyint(4) DEFAULT '0' COMMENT '服务机构投诉状态',
  `org_complaints_state` tinyint(4) DEFAULT '0' COMMENT '企业投诉状态',
  `coupon_state` tinyint(4) DEFAULT NULL COMMENT '领券申请状态',
  `couponpay_state` tinyint(4) DEFAULT NULL COMMENT '申请兑换状态',
  `over_at` varchar(32) DEFAULT NULL COMMENT '服务完成时间',
  `orderSrc` tinyint(4) DEFAULT NULL COMMENT '订单来源',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_order` */

/*Table structure for table `sc_service_order_appraise` */

DROP TABLE IF EXISTS `sc_service_order_appraise`;

CREATE TABLE `sc_service_order_appraise` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `order_id` varchar(32) DEFAULT NULL COMMENT '订单ID',
  `item_id` varchar(32) DEFAULT NULL COMMENT '服务项目id',
  `account_id` varchar(32) DEFAULT NULL COMMENT '服务对象id',
  `note` varchar(255) DEFAULT NULL COMMENT '评价内容',
  `appScore` bigint(44) DEFAULT NULL COMMENT '项目评分',
  `feedAt` varchar(32) DEFAULT NULL COMMENT '评价时间',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `check_by` varchar(32) DEFAULT NULL COMMENT '审核人',
  `check_at` varchar(32) DEFAULT NULL COMMENT '审核时间',
  `check_note` varchar(256) DEFAULT NULL COMMENT '审核意见',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_order_appraise` */

/*Table structure for table `sc_service_order_complain` */

DROP TABLE IF EXISTS `sc_service_order_complain`;

CREATE TABLE `sc_service_order_complain` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `service_id` varchar(32) DEFAULT NULL COMMENT '服务机构id',
  `item_id` varchar(32) DEFAULT NULL COMMENT '服务项目ID',
  `account_id` varchar(32) DEFAULT NULL COMMENT '服务对象帐号ID',
  `order_id` varchar(32) DEFAULT NULL COMMENT '订单ID',
  `send_by` varchar(32) DEFAULT NULL COMMENT '申请人',
  `send_at` bigint(128) DEFAULT NULL COMMENT '申请时间',
  `type` varchar(32) DEFAULT NULL COMMENT '投诉类型',
  `complain_title` varchar(255) DEFAULT NULL COMMENT '投诉标题',
  `complain_note` varchar(255) DEFAULT NULL COMMENT '投诉内容',
  `processing_by` varchar(32) DEFAULT NULL COMMENT '处理人',
  `processing_at` bigint(128) DEFAULT NULL COMMENT '处理时间',
  `processing_status` tinyint(4) DEFAULT '0' COMMENT '处理状态',
  `processing_note` varchar(255) DEFAULT NULL COMMENT '处理内容',
  `send_code` varchar(1) DEFAULT '0' COMMENT '发起方',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_order_complain` */

/*Table structure for table `sc_service_order_complain_pic` */

DROP TABLE IF EXISTS `sc_service_order_complain_pic`;

CREATE TABLE `sc_service_order_complain_pic` (
  `complainId` varchar(32) DEFAULT NULL COMMENT '投诉ID',
  `priority` bigint(128) DEFAULT NULL COMMENT '排序',
  `img` varchar(150) DEFAULT NULL COMMENT '图片',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_order_complain_pic` */

/*Table structure for table `sc_service_order_log` */

DROP TABLE IF EXISTS `sc_service_order_log`;

CREATE TABLE `sc_service_order_log` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `orderId` varchar(32) DEFAULT NULL COMMENT '订单ID',
  `accountId` varchar(32) DEFAULT NULL COMMENT '帐号ID',
  `note` text COMMENT '日志内容',
  `behavior` int(32) DEFAULT '0' COMMENT '日志行为',
  `opByName` varchar(32) DEFAULT NULL COMMENT '日志操作人',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_order_log` */

/*Table structure for table `sc_service_order_payment` */

DROP TABLE IF EXISTS `sc_service_order_payment`;

CREATE TABLE `sc_service_order_payment` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `account_id` varchar(32) DEFAULT NULL COMMENT '帐号ID',
  `item_id` varchar(32) DEFAULT NULL COMMENT '服务项目ID',
  `order_id` varchar(32) DEFAULT NULL COMMENT '订单ID',
  `pay_type` tinyint(4) DEFAULT NULL COMMENT '支付方式',
  `pay_money` bigint(128) DEFAULT NULL COMMENT '实付金额',
  `pay_no` varchar(32) DEFAULT NULL COMMENT '支付帐号',
  `trade_no` varchar(100) DEFAULT NULL COMMENT '交易流水号',
  `pay_at` varchar(32) DEFAULT NULL COMMENT '支付时间',
  `finish_at` varchar(32) DEFAULT NULL COMMENT '完成时间',
  `is_coupon` varchar(1) DEFAULT NULL COMMENT '是否使用电子券',
  `coupon_money` bigint(128) DEFAULT NULL COMMENT '电子券抵扣金额',
  `pay_status` tinyint(4) DEFAULT NULL COMMENT '支付状态',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_order_payment` */

/*Table structure for table `sc_service_order_paypicture` */

DROP TABLE IF EXISTS `sc_service_order_paypicture`;

CREATE TABLE `sc_service_order_paypicture` (
  `orderId` varchar(32) DEFAULT NULL COMMENT '订单ID',
  `priority` bigint(128) DEFAULT NULL COMMENT '排序',
  `img` varchar(150) DEFAULT NULL COMMENT '图片',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_order_paypicture` */

/*Table structure for table `sc_service_org_estemp` */

DROP TABLE IF EXISTS `sc_service_org_estemp`;

CREATE TABLE `sc_service_org_estemp` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `serviceOrgId` varchar(32) DEFAULT NULL COMMENT '服务机构Id',
  `action` varchar(32) DEFAULT NULL COMMENT '操作类型',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_org_estemp` */

/*Table structure for table `sc_service_org_info` */

DROP TABLE IF EXISTS `sc_service_org_info`;

CREATE TABLE `sc_service_org_info` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `account_id` varchar(32) DEFAULT NULL COMMENT '帐号ID',
  `org_property` varchar(32) DEFAULT NULL COMMENT '单位性质',
  `org_code` varchar(15) DEFAULT NULL COMMENT '组织机构代码',
  `org_name` varchar(256) DEFAULT NULL COMMENT '机构名称',
  `legal_person` varchar(256) DEFAULT NULL COMMENT '法定代表人',
  `register_type` varchar(32) DEFAULT NULL COMMENT '企业注册类型',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area_county` varchar(32) DEFAULT NULL COMMENT '县区',
  `linkman` varchar(20) DEFAULT NULL COMMENT '联系人',
  `tel` varchar(50) DEFAULT NULL COMMENT '电话',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `email` varchar(128) DEFAULT NULL COMMENT '电子邮箱',
  `website` varchar(128) DEFAULT NULL COMMENT '网址',
  `address` varchar(512) DEFAULT NULL COMMENT '通讯地址',
  `postcode` varchar(6) DEFAULT NULL COMMENT '邮政编码',
  `industry_code` varchar(32) DEFAULT NULL COMMENT '行业代码',
  `industry_code1` varchar(32) DEFAULT NULL COMMENT '行业代码二级',
  `main_remark` varchar(4000) DEFAULT NULL COMMENT '主要服务内容和特色',
  `share_type` varchar(32) DEFAULT NULL COMMENT '控股情况',
  `opened_time` varchar(7) DEFAULT NULL COMMENT '开业时间',
  `register_asset` bigint(128) DEFAULT NULL COMMENT '注册资本',
  `qualification` varchar(4000) DEFAULT NULL COMMENT '专业资质情况',
  `remark` varchar(4000) DEFAULT NULL COMMENT '主要服务设备及条件',
  `personnel` varchar(4000) DEFAULT NULL COMMENT '人员素质构成情况',
  `photo` varchar(128) DEFAULT NULL COMMENT '服务机构LOGO图片',
  `org_grade` varchar(32) DEFAULT NULL COMMENT '认定情况',
  `url` varchar(128) DEFAULT NULL COMMENT '资源来源',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `signed` tinyint(4) DEFAULT NULL COMMENT '是否签约',
  `reg_at` varchar(32) DEFAULT NULL COMMENT '服务注册时间',
  `check_at` varchar(32) DEFAULT NULL COMMENT '服务开通时间',
  `total` bigint(80) DEFAULT NULL COMMENT '总评价次数',
  `point` bigint(80) DEFAULT NULL COMMENT '总评价分数',
  `money` bigint(128) DEFAULT '0' COMMENT '账户余额',
  `freeze_money` bigint(128) DEFAULT '0' COMMENT '冻结金额',
  `service_level` tinyint(8) DEFAULT NULL COMMENT '服务等级',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_org_info` */

/*Table structure for table `sc_service_org_info_check` */

DROP TABLE IF EXISTS `sc_service_org_info_check`;

CREATE TABLE `sc_service_org_info_check` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `org_id` varchar(32) DEFAULT NULL COMMENT '服务机构id',
  `check_by` varchar(32) DEFAULT NULL COMMENT '审核人',
  `check_at` varchar(32) DEFAULT NULL COMMENT '审核时间',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `check_note` varchar(256) DEFAULT NULL COMMENT '审核意见',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_org_info_check` */

/*Table structure for table `sc_service_org_report` */

DROP TABLE IF EXISTS `sc_service_org_report`;

CREATE TABLE `sc_service_org_report` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `org_id` varchar(100) DEFAULT NULL COMMENT '机构编码',
  `report_date` varchar(4) DEFAULT NULL COMMENT '年度',
  `income` bigint(128) DEFAULT NULL COMMENT '年营业收入',
  `contribution_margin` bigint(128) DEFAULT NULL COMMENT '年利润总额',
  `employees_num` bigint(128) DEFAULT NULL COMMENT '从业人数',
  `asset` bigint(128) DEFAULT NULL COMMENT '资产总额',
  `top_money` bigint(128) DEFAULT NULL COMMENT '金额上限',
  `end_time` bigint(128) DEFAULT NULL COMMENT '截止时间',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_org_report` */

/*Table structure for table `sc_service_role` */

DROP TABLE IF EXISTS `sc_service_role`;

CREATE TABLE `sc_service_role` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(255) DEFAULT NULL COMMENT '唯一标识',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `defaultValue` tinyint(1) DEFAULT NULL COMMENT '是否默认',
  `note` varchar(255) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '角色类型',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_STORE_ROLE_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_role` */

/*Table structure for table `sc_service_role_menu` */

DROP TABLE IF EXISTS `sc_service_role_menu`;

CREATE TABLE `sc_service_role_menu` (
  `roleId` varchar(32) DEFAULT NULL,
  `menuId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_role_menu` */

/*Table structure for table `sc_service_user_role` */

DROP TABLE IF EXISTS `sc_service_user_role`;

CREATE TABLE `sc_service_user_role` (
  `linkId` varchar(32) DEFAULT NULL,
  `roleId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_service_user_role` */

/*Table structure for table `sc_startup_ability` */

DROP TABLE IF EXISTS `sc_startup_ability`;

CREATE TABLE `sc_startup_ability` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `startup_id` varchar(32) DEFAULT NULL COMMENT '创业者ID',
  `education` varchar(32) DEFAULT NULL COMMENT '学历',
  `title` varchar(128) DEFAULT NULL COMMENT '职称',
  `major` varchar(64) DEFAULT NULL COMMENT '创业者专业',
  `filed` varchar(100) DEFAULT NULL COMMENT '创业者擅长领域',
  `experience` varchar(2000) DEFAULT NULL COMMENT '创业经历',
  `achievement` varchar(2000) DEFAULT NULL COMMENT '个人业绩',
  `certification` varchar(512) DEFAULT NULL COMMENT '个人资质或证书',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_startup_ability` */

/*Table structure for table `sc_startup_info` */

DROP TABLE IF EXISTS `sc_startup_info`;

CREATE TABLE `sc_startup_info` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `account_id` varchar(32) DEFAULT NULL COMMENT '账户id',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `head` varchar(256) DEFAULT NULL COMMENT '头像',
  `linkman` varchar(50) DEFAULT NULL COMMENT '联系人',
  `tel` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `address` varchar(512) DEFAULT NULL COMMENT '联系地址',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '区/县',
  `remark` varchar(4000) DEFAULT NULL COMMENT '备注',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_startup_info` */

/*Table structure for table `sc_startup_info_check` */

DROP TABLE IF EXISTS `sc_startup_info_check`;

CREATE TABLE `sc_startup_info_check` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `startup_id` varchar(32) DEFAULT NULL COMMENT '创业者信息id',
  `check_by` varchar(32) DEFAULT NULL COMMENT '审核人',
  `check_at` varchar(32) DEFAULT NULL COMMENT '审核时间',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `check_note` varchar(256) DEFAULT NULL COMMENT '审核意见',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_startup_info_check` */

/*Table structure for table `sc_startup_site` */

DROP TABLE IF EXISTS `sc_startup_site`;

CREATE TABLE `sc_startup_site` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `service_item_id` varchar(32) DEFAULT NULL COMMENT '服务项目id',
  `type` varchar(32) DEFAULT NULL COMMENT '场地类型',
  `des` varchar(2000) DEFAULT NULL COMMENT '场地情况描述',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '县区',
  `address` varchar(512) DEFAULT NULL COMMENT '详细地址',
  `site_type` varchar(10) DEFAULT NULL COMMENT '场地租售类别',
  `position` varchar(512) DEFAULT NULL COMMENT '场地电子地图地址',
  `pic` varchar(512) DEFAULT NULL COMMENT '场地内部空间3D',
  `linkman` varchar(20) DEFAULT NULL COMMENT '服务联系人',
  `tel` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc_startup_site` */

/*Table structure for table `sms_mould_info` */

DROP TABLE IF EXISTS `sms_mould_info`;

CREATE TABLE `sms_mould_info` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `code` varchar(50) DEFAULT NULL COMMENT '模板code',
  `ali_code` varchar(50) DEFAULT NULL COMMENT '阿里大于模板code',
  `name` varchar(30) DEFAULT NULL COMMENT '模板名称',
  `type` varchar(32) DEFAULT NULL COMMENT '模板类型',
  `content` varchar(500) DEFAULT NULL COMMENT '模板内容',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sms_mould_info` */

insert  into `sms_mould_info`(`id`,`code`,`ali_code`,`name`,`type`,`content`,`opBy`,`opAt`,`delFlag`) values ('dd66f4d426dc4fcc9b356f081231f9c3','SMS_001','SMS_127167995','注册手机验证',NULL,'验证码为：${code}，您正在注册成为eysh会员，感谢您的支持！','1a38a92345bd44ed98648b9162b2d67a',1521525244,0);

/*Table structure for table `sms_send_log_` */

DROP TABLE IF EXISTS `sms_send_log_`;

CREATE TABLE `sms_send_log_` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `send_person` varchar(50) DEFAULT NULL COMMENT '发送方',
  `receive_person` varchar(50) DEFAULT NULL COMMENT '接收方',
  `mould_code` varchar(50) DEFAULT NULL COMMENT '模板code',
  `send_msg` varchar(500) DEFAULT NULL COMMENT '发送内容',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sms_send_log_` */

/*Table structure for table `sms_send_log_201802` */

DROP TABLE IF EXISTS `sms_send_log_201802`;

CREATE TABLE `sms_send_log_201802` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `send_person` varchar(50) DEFAULT NULL COMMENT '发送方',
  `receive_person` varchar(50) DEFAULT NULL COMMENT '接收方',
  `mould_code` varchar(50) DEFAULT NULL COMMENT '模板code',
  `send_msg` varchar(500) DEFAULT NULL COMMENT '发送内容',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sms_send_log_201802` */

/*Table structure for table `sms_send_log_201803` */

DROP TABLE IF EXISTS `sms_send_log_201803`;

CREATE TABLE `sms_send_log_201803` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `send_person` varchar(50) DEFAULT NULL COMMENT '发送方',
  `receive_person` varchar(50) DEFAULT NULL COMMENT '接收方',
  `mould_code` varchar(50) DEFAULT NULL COMMENT '模板code',
  `send_msg` varchar(500) DEFAULT NULL COMMENT '发送内容',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sms_send_log_201803` */

insert  into `sms_send_log_201803`(`id`,`send_person`,`receive_person`,`mould_code`,`send_msg`,`opBy`,`opAt`,`delFlag`) values ('004d11901bc3412a92c74210ba76d436','','18888889999','SMS_001','验证码为：811426，您正在注册成为eysh会员，感谢您的支持！','',1521535901,0),('07caf8fcc315415a895f0c2e7ee5347e','','18888888881','SMS_001','验证码为：883980，您正在注册成为eysh会员，感谢您的支持！','',1521540027,0),('0d5d184c3e624e719a3e5be3ab395eae','','18949464266','SMS_001','验证码为：635517，您正在注册成为eysh会员，感谢您的支持！','',1521525646,0),('10f5be3722b64b9899502f9b787426f2','','18888888888','SMS_001','验证码为：361055，您正在注册成为eysh会员，感谢您的支持！','',1521534828,0),('15e40f2b87ad4024a02d76ca29960c9b','','18999999999','SMS_001','验证码为：405280，您正在注册成为eysh会员，感谢您的支持！','',1521536650,0),('1f34526a34a74165ba4cdbf746373f09','','18949464266','SMS_001','验证码为：640283，您正在注册成为eysh会员，感谢您的支持！','',1521534299,0),('237ba313c05a4c4abdc3eedc7c625c46','','18954123124','SMS_001','验证码为：358004，您正在注册成为eysh会员，感谢您的支持！','',1521540313,0),('3fefa260b0044086b822e9cc06fd2ac6','','18949461231','SMS_001','验证码为：104991，您正在注册成为eysh会员，感谢您的支持！','',1521540169,0),('4251cc83523d4ee5964952e89ea294aa','','18949464266','SMS_001','验证码为：161526，您正在注册成为eysh会员，感谢您的支持！','',1521533832,0),('48d00a115ba34050895db89409af69c9','','18949494949','SMS_001','验证码为：840949，您正在注册成为eysh会员，感谢您的支持！','',1521537857,0),('533150d09d7045029d4ab0a742bb9000','','18949464266','SMS_001','验证码为：128140，您正在注册成为eysh会员，感谢您的支持！','',1521525869,0),('59dcfe0a93154af98a7c12da27d509d3','','18949234234','SMS_001','验证码为：123868，您正在注册成为eysh会员，感谢您的支持！','',1521537973,0),('5ad36ae8d0b4477abae5acb515d6ed94','','18888888888','SMS_001','验证码为：860350，您正在注册成为eysh会员，感谢您的支持！','',1521539942,0),('5e36a5be15a342a299619b9a65315ed3','','18888888999','SMS_001','验证码为：659022，您正在注册成为eysh会员，感谢您的支持！','',1521536171,0),('6a2f5410849b4a6990e695e146eff409','','18888888888','SMS_001','验证码为：866949，您正在注册成为eysh会员，感谢您的支持！','',1521966097,0),('6f41ffdfc0c7469abd71d9f717400ab7','','18949464266','SMS_001','验证码为：385270，您正在注册成为eysh会员，感谢您的支持！','',1521527033,0),('703cf13cbe164f708e2044dde940f4cf','','15000000000','SMS_001','验证码为：822216，您正在注册成为eysh会员，感谢您的支持！','',1521966321,0),('75da6afdd4154eb59227a1be8048a94f','','18949464266','SMS_001','验证码为：377677，您正在注册成为eysh会员，感谢您的支持！','',1521533930,0),('853edfe9140c431ca7ec23149e1a561d','','18949464266','SMS_001','验证码为：142432，您正在注册成为eysh会员，感谢您的支持！','',1521533245,0),('85e4b23059bd4caaaec462b68dcccbf0','','18999999999','SMS_001','验证码为：975536，您正在注册成为eysh会员，感谢您的支持！','',1521966235,0),('8baa9bf57d184cd7be657d623d03f753','','18949464266','SMS_001','验证码为：944191，您正在注册成为eysh会员，感谢您的支持！','',1521554466,0),('90b8165d4437403dbf1278d5c508864f','','18998988989','SMS_001','验证码为：988627，您正在注册成为eysh会员，感谢您的支持！','',1521537566,0),('92b2636d5d36419facdbb608635cfc90','','18856345251','SMS_001','验证码为：104719，您正在注册成为eysh会员，感谢您的支持！','',1521540375,0),('9a6b54c83ef74040a4d748c2708871ff','','18949464266','SMS_001','验证码为：374974，您正在注册成为eysh会员，感谢您的支持！','',1521525283,0),('9ceb3c5939ef4c2681c25ad5e646e27f','','15111111111','SMS_001','验证码为：946526，您正在注册成为eysh会员，感谢您的支持！','',1521966526,0),('9ffb36baa4c04d4b8ee694489595da6f','','18888888888','SMS_001','验证码为：435742，您正在注册成为eysh会员，感谢您的支持！','',1521537686,0),('ac1b0317a9684040a1ed165b69dd6bfe','','18888444444','SMS_001','验证码为：522180，您正在注册成为eysh会员，感谢您的支持！','',1521535859,0),('b74100fc5b8d4a8ea514520cce5ca067','','18888888888','SMS_001','验证码为：533254，您正在注册成为eysh会员，感谢您的支持！','',1521536112,0),('bf302030052b400aac2be6608007e5e3','','18949464266','SMS_001','验证码为：978168，您正在注册成为eysh会员，感谢您的支持！','',1521525791,0),('d8ea3ce92af14eb6a3735762c481805c','','18888888888','SMS_001','验证码为：826493，您正在注册成为eysh会员，感谢您的支持！','',1521535921,0);

/*Table structure for table `sms_send_log_201804` */

DROP TABLE IF EXISTS `sms_send_log_201804`;

CREATE TABLE `sms_send_log_201804` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `send_person` varchar(50) DEFAULT NULL COMMENT '发送方',
  `receive_person` varchar(50) DEFAULT NULL COMMENT '接收方',
  `mould_code` varchar(50) DEFAULT NULL COMMENT '模板code',
  `send_msg` varchar(500) DEFAULT NULL COMMENT '发送内容',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sms_send_log_201804` */

insert  into `sms_send_log_201804`(`id`,`send_person`,`receive_person`,`mould_code`,`send_msg`,`opBy`,`opAt`,`delFlag`) values ('299d5ca1a70e4eb8a0bdc88093a35481','','18949464266','SMS_001','验证码为：751984，您正在注册成为eysh会员，感谢您的支持！','',1524022804,0),('46cc9bd5d3eb483d8d2c25e76883f593','','18949464266','SMS_001','验证码为：151279，您正在注册成为eysh会员，感谢您的支持！','',1524022967,0),('9769e61d46074ec198b77ca31863373d','','18949464266','SMS_001','验证码为：074057，您正在注册成为eysh会员，感谢您的支持！','',1524022917,0),('b7b75872241f47139371a405182cbba8','','18949464266','SMS_001','验证码为：900903，您正在注册成为eysh会员，感谢您的支持！','',1524023046,0);

/*Table structure for table `sms_tele_info` */

DROP TABLE IF EXISTS `sms_tele_info`;

CREATE TABLE `sms_tele_info` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `phone_no` varchar(20) DEFAULT NULL COMMENT '虚拟号码',
  `end_time` bigint(128) DEFAULT NULL COMMENT '绑定截止时间',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sms_tele_info` */

/*Table structure for table `sms_tele_log_` */

DROP TABLE IF EXISTS `sms_tele_log_`;

CREATE TABLE `sms_tele_log_` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `appid` varchar(32) DEFAULT NULL COMMENT 'appid',
  `fm` varchar(20) DEFAULT NULL COMMENT '来源号码',
  `tm` varchar(20) DEFAULT NULL COMMENT '转移至号码',
  `bind_time` bigint(40) DEFAULT NULL COMMENT '绑定时间',
  `do_type` varchar(10) DEFAULT NULL COMMENT '操作类型',
  `vm` varchar(20) DEFAULT NULL COMMENT '虚拟号码',
  `scene` int(20) DEFAULT NULL COMMENT '场景',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sms_tele_log_` */

/*Table structure for table `sms_tele_log_201802` */

DROP TABLE IF EXISTS `sms_tele_log_201802`;

CREATE TABLE `sms_tele_log_201802` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `appid` varchar(32) DEFAULT NULL COMMENT 'appid',
  `fm` varchar(20) DEFAULT NULL COMMENT '来源号码',
  `tm` varchar(20) DEFAULT NULL COMMENT '转移至号码',
  `bind_time` bigint(40) DEFAULT NULL COMMENT '绑定时间',
  `do_type` varchar(10) DEFAULT NULL COMMENT '操作类型',
  `vm` varchar(20) DEFAULT NULL COMMENT '虚拟号码',
  `scene` int(20) DEFAULT NULL COMMENT '场景',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sms_tele_log_201802` */

/*Table structure for table `sms_tele_log_201803` */

DROP TABLE IF EXISTS `sms_tele_log_201803`;

CREATE TABLE `sms_tele_log_201803` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `appid` varchar(32) DEFAULT NULL COMMENT 'appid',
  `fm` varchar(20) DEFAULT NULL COMMENT '来源号码',
  `tm` varchar(20) DEFAULT NULL COMMENT '转移至号码',
  `bind_time` bigint(40) DEFAULT NULL COMMENT '绑定时间',
  `do_type` varchar(10) DEFAULT NULL COMMENT '操作类型',
  `vm` varchar(20) DEFAULT NULL COMMENT '虚拟号码',
  `scene` int(20) DEFAULT NULL COMMENT '场景',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sms_tele_log_201803` */

/*Table structure for table `sms_tele_log_201804` */

DROP TABLE IF EXISTS `sms_tele_log_201804`;

CREATE TABLE `sms_tele_log_201804` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `appid` varchar(32) DEFAULT NULL COMMENT 'appid',
  `fm` varchar(20) DEFAULT NULL COMMENT '来源号码',
  `tm` varchar(20) DEFAULT NULL COMMENT '转移至号码',
  `bind_time` bigint(40) DEFAULT NULL COMMENT '绑定时间',
  `do_type` varchar(10) DEFAULT NULL COMMENT '操作类型',
  `vm` varchar(20) DEFAULT NULL COMMENT '虚拟号码',
  `scene` int(20) DEFAULT NULL COMMENT '场景',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sms_tele_log_201804` */

/*Table structure for table `sys_alipay` */

DROP TABLE IF EXISTS `sys_alipay`;

CREATE TABLE `sys_alipay` (
  `id` varchar(32) NOT NULL,
  `appid` varchar(32) DEFAULT NULL COMMENT 'appid',
  `suid` varchar(32) DEFAULT NULL COMMENT '商户UID',
  `tradeNo` varchar(32) DEFAULT NULL COMMENT '支付宝交易号',
  `outTradeno` varchar(32) DEFAULT NULL COMMENT '订单号',
  `totalAmount` varchar(16) DEFAULT NULL COMMENT '交易金额',
  `recieveAmount` varchar(16) DEFAULT NULL COMMENT '收到金额',
  `tradestatus` varchar(16) DEFAULT NULL COMMENT '交易状态',
  `subject` varchar(32) DEFAULT NULL COMMENT '购买商品',
  `body` varchar(256) DEFAULT NULL COMMENT '购买商品详情',
  `returnurl` varchar(256) DEFAULT NULL COMMENT '成功返回',
  `notifyurl` varchar(256) DEFAULT NULL COMMENT '回调参数',
  `cj_date` varchar(16) DEFAULT NULL COMMENT '创建时间',
  `fukuan_date` varchar(16) DEFAULT NULL COMMENT '付款时间',
  `fanhui_date` varchar(16) DEFAULT NULL COMMENT '返回时间',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_alipay` */

/*Table structure for table `sys_api` */

DROP TABLE IF EXISTS `sys_api`;

CREATE TABLE `sys_api` (
  `id` varchar(32) NOT NULL,
  `appName` varchar(20) DEFAULT NULL COMMENT 'appName',
  `appId` varchar(255) DEFAULT NULL COMMENT 'appId',
  `appKey` varchar(255) DEFAULT NULL COMMENT 'appKey',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_api` */

/*Table structure for table `sys_app_unit` */

DROP TABLE IF EXISTS `sys_app_unit`;

CREATE TABLE `sys_app_unit` (
  `unitId` varchar(32) NOT NULL COMMENT '单位ID',
  `appId` varchar(32) NOT NULL COMMENT '应用appId',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`unitId`,`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_app_unit` */

/*Table structure for table `sys_area` */

DROP TABLE IF EXISTS `sys_area`;

CREATE TABLE `sys_area` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `path` varchar(100) DEFAULT NULL COMMENT '树路径',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `code` varchar(20) DEFAULT NULL COMMENT '编码',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `hasChildren` tinyint(1) DEFAULT NULL COMMENT '有子节点',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SYS_AREA_PATH` (`path`),
  UNIQUE KEY `INDEX_SYS_AREA_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_area` */

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
  `configKey` varchar(100) NOT NULL,
  `configValue` varchar(100) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`configKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_config` */

insert  into `sys_config`(`configKey`,`configValue`,`note`,`opBy`,`opAt`,`delFlag`) values ('APP_DOMAIN','127.0.0.1','系统域名','',1517452970,0),('APP_NAME','Eysh','系统名称','',1517458583,0),('APP_PHONE','18949464266','EYSH唯一phone','1a38a92345bd44ed98648b9162b2d67a',1521941758,0),('APP_SHORT_NAME','Eysh','系统短名称','',1517453866,0),('APP_UPLOAD_PATH','/upload','单机部署时文件上传路径','',1517452970,0);

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `path` varchar(100) DEFAULT NULL COMMENT '树路径',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `code` varchar(255) DEFAULT NULL COMMENT '编码',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `hasChildren` tinyint(1) DEFAULT NULL COMMENT '有子节点',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SYS_DICT_PATH` (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_dict` */

/*Table structure for table `sys_file` */

DROP TABLE IF EXISTS `sys_file`;

CREATE TABLE `sys_file` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(256) DEFAULT NULL COMMENT '文件名',
  `fdfs_file_id` varchar(512) DEFAULT NULL COMMENT '远程文件服务器',
  `path` varchar(512) DEFAULT NULL COMMENT '文件存放路径',
  `url` varchar(512) DEFAULT NULL COMMENT '文件访问路径',
  `type` varchar(128) DEFAULT NULL COMMENT '文件类型',
  `fileSize` bigint(128) DEFAULT NULL COMMENT '文件大小',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_file` */

/*Table structure for table `sys_log_` */

DROP TABLE IF EXISTS `sys_log_`;

CREATE TABLE `sys_log_` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `ip` varchar(255) DEFAULT NULL COMMENT 'IP地址',
  `module` varchar(255) DEFAULT NULL COMMENT '请求模块',
  `action` varchar(255) DEFAULT NULL COMMENT '请求方法',
  `description` varchar(255) DEFAULT NULL COMMENT '日志描述',
  `type` varchar(10) DEFAULT NULL COMMENT '日志类型(默认:system)',
  `methodMeta` text COMMENT '方法',
  `parameters` text COMMENT '参数',
  `methodReturn` text COMMENT '返回值',
  `actionTime` varchar(20) DEFAULT NULL COMMENT '请求时间',
  `operationTime` int(32) DEFAULT NULL COMMENT '执行时间',
  `success` tinyint(1) DEFAULT NULL COMMENT '操作状态(0:失败 1:成功)',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `SYS_LOG__INDEX` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_log_` */

/*Table structure for table `sys_log_201802` */

DROP TABLE IF EXISTS `sys_log_201802`;

CREATE TABLE `sys_log_201802` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `ip` varchar(255) DEFAULT NULL COMMENT 'IP地址',
  `module` varchar(255) DEFAULT NULL COMMENT '请求模块',
  `action` varchar(255) DEFAULT NULL COMMENT '请求方法',
  `description` varchar(255) DEFAULT NULL COMMENT '日志描述',
  `type` varchar(10) DEFAULT NULL COMMENT '日志类型(默认:system)',
  `methodMeta` text COMMENT '方法',
  `parameters` text COMMENT '参数',
  `methodReturn` text COMMENT '返回值',
  `actionTime` varchar(20) DEFAULT NULL COMMENT '请求时间',
  `operationTime` int(32) DEFAULT NULL COMMENT '执行时间',
  `success` tinyint(1) DEFAULT NULL COMMENT '操作状态(0:失败 1:成功)',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `SYS_LOG_201802_INDEX` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

/*Data for the table `sys_log_201802` */

insert  into `sys_log_201802`(`id`,`username`,`ip`,`module`,`action`,`description`,`type`,`methodMeta`,`parameters`,`methodReturn`,`actionTime`,`operationTime`,`success`,`opBy`,`opAt`,`delFlag`) values (1,'超级管理员','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'be74468e1345473cb1314610536281e5',1517453019,NULL),(2,'超级管理员','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysConfController','editDo','修改系统参数','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysConfController.editDo(com.aebiz.app.sys.modules.models.Sys_config)','[{\"configKey\":\"APP_SHORT_NAME\",\"configValue\":\"Eysh后台管理系统\",\"note\":\"系统短名称\"}]',NULL,'2018-02-01 10:57:37',26,1,'be74468e1345473cb1314610536281e5',1517453857,0),(3,'超级管理员','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysConfController','editDo','修改系统参数','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysConfController.editDo(com.aebiz.app.sys.modules.models.Sys_config)','[{\"configKey\":\"APP_SHORT_NAME\",\"configValue\":\"Eysh\",\"note\":\"系统短名称\"}]',NULL,'2018-02-01 10:57:46',8,1,'be74468e1345473cb1314610536281e5',1517453866,0),(4,'超级管理员','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysConfController','editDo','修改系统参数','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysConfController.editDo(com.aebiz.app.sys.modules.models.Sys_config)','[{\"configKey\":\"APP_NAME\",\"configValue\":\"Eysh后台管理系统\",\"note\":\"系统名称\"}]',NULL,'2018-02-01 10:57:51',8,1,'be74468e1345473cb1314610536281e5',1517453871,0),(5,'超级管理员','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'be74468e1345473cb1314610536281e5',1517454360,NULL),(6,'超级管理员','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'be74468e1345473cb1314610536281e5',1517454374,NULL),(7,'超级管理员','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'be74468e1345473cb1314610536281e5',1517455298,NULL),(8,'超级管理员','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysUserController','ssoAddDo','新建用户','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysUserController.ssoAddDo(com.aebiz.app.sys.modules.models.Sys_user,javax.servlet.http.HttpServletRequest)','[{\"loginname\":\"admin277\",\"password\":\"277\",\"username\":\"周家俊\",\"isOnline\":false,\"disabled\":false,\"email\":\"postmaster@xyy277.cn\",\"mobile\":\"18949464266\",\"duties\":\"\",\"loginSidebar\":false,\"loginBoxed\":false,\"loginScroll\":false,\"loginPjax\":false,\"unitid\":\"d094dfd0dd33402198ac8aac6deca01e\"},{\"unitid\":[\"d094dfd0dd33402198ac8aac6deca01e\"],\"type\":[\"01\"],\"loginname\":[\"admin277\"],\"password\":[\"277\"],\"username\":[\"周家俊\"],\"mobile\":[\"18949464266\"],\"duties\":[\"\"],\"email\":[\"postmaster@xyy277.cn\"]}]',NULL,'2018-02-01 11:48:53',9,1,'be74468e1345473cb1314610536281e5',1517456933,0),(9,'超级管理员','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController','pushUser','添加用户到角色','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController.pushUser(java.lang.String,java.lang.String,javax.servlet.http.HttpServletRequest)','[\"1a38a92345bd44ed98648b9162b2d67a\",\"8dbcb08a74fb4f458ba43474f9b3f8ac\",{\"menuIds\":[\"1a38a92345bd44ed98648b9162b2d67a\"],\"roleid\":[\"8dbcb08a74fb4f458ba43474f9b3f8ac\"]}]',NULL,'2018-02-01 11:49:12',5,1,'be74468e1345473cb1314610536281e5',1517456952,0),(10,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1517456967,NULL),(11,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysConfController','editDo','修改系统参数','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysConfController.editDo(com.aebiz.app.sys.modules.models.Sys_config)','[{\"configKey\":\"APP_NAME\",\"configValue\":\"Eysh\",\"note\":\"系统名称\"}]',NULL,'2018-02-01 12:16:23',11,1,'1a38a92345bd44ed98648b9162b2d67a',1517458583,0),(12,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController','addDo','Sys_role','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController.addDo(com.aebiz.app.sys.modules.models.Sys_role,javax.servlet.http.HttpServletRequest)','[{\"name\":\"CMS管理员\",\"code\":\"cms\",\"disabled\":true,\"unitid\":\"d094dfd0dd33402198ac8aac6deca01e\",\"note\":\"CMS管理\"},{\"menuIds\":[\"2f848a43ec394c4b96cc7241d2b06381,5490ca2f28ad421b80145eaf96abdc7c,ae17ba0fcce64b46a780ea329e052f3b,4b133273aba543878e0f132f69e47457,3e6d3fb5bc234feebba82a15bdfa2ef9,d81c017d173648e2aa8ccd8ac79230d2,be0363c47ee047cab3c6b47629c51a79,4381af82a2a2446495fba3eb146d7f61,d3dc1eb066de481587131ab048b3f6e2,48ed3df76ded491ebadd737527160744,b487e8b82b0e4d3f887df02f89d37507,32a6986d1b1f45a48c7789ec2112169c,e02c779c7e50419d8cc086250bc38264,6d5f86d6d04f4d8298334993d68a2337,99abc5f7fbb34f24bc9c03e5fbbe3054,a59bfc8ca6844149bd532c7d0d494d9f,a7b6b1d4e3a54b698ec84eec3ed52808,4c40c6049e914dfc93f897f3231ccc73,f587bcfe0962434b8e1a7f721531ba65,c8809a8735864b0ba52591a58f2b1375,8a387fb5ff3948b094068e6f75d7387a,482c744503d548ad826e784e00c04098,7d938aa5abd84415ad2d648a29e11b8b,e7806116788746fb8381184f0e8938ac,d2feef5baba2426f97643bd9a9e586fb,6f27d3cecf78457e98387023faf12a72,43efc7afc83249c3b4875fc4860f3aca,88e8ec8b29624f15a5fb5130696568f3,1e5687528e264274ace41a50ed0e64e2,8fe0340021da48c999d0f8292dbb29b2,dfc098f0925046339d4906ee013ef2fb,59e62054e822406c88e7db4af7ed8079,6f67fff3e7944a8599ca847b4521f5c1,8cd1a796fca24dd691c0add6a00a941b,d3b283908e2c4507be159ba2c776a9f9,060e4aa715d844168c0d1639962a1c8e,8d7fd658c1e34a30b21aeb4296714cc3,1c7b1c927b464af58f3cc0b2ad8b69b0,093a17c9d2944793a4e5e364299353fa,c84e77edf76e4b1891943e4bf8f8b516,fde039f39db840ca89987119198db229,99127e7a2a8748e8b3bae275e238cf5e,dbff508e055442a3bf87dafeee794f76,51ba669f420b425e9d00538be47ab7a2,1ab1f849dbd3484a85ec5609b9d8e61a,5829d16c03db45baac75c5e987be1506,7359b29c6fe24171863b02f49a266eae,a3501a5974f34625aacddb61a10f426e,8f7f347ae9eb40edbca08e31f7998f9d,f6bdd58aefbc43f1b79e2886aacc7028,fbd34327c5674ec3aca2cfc089089ff4,942d0029927640008a79322d26d09c4d,0f0285227e654659998be03d197cc48c,32cd2fe478644290a112bafe6a92965c,d470271109754506a5ffba4ff3f19ba1,4cc19c89acf349349b8065f06d703113,485c764b94c447b19ee4ac25e624cc5c,4f6b3451e57d4f26a9ca4c0904370ef2,b81188925c684908b7c258235865be3a,5642e30d6f1042f7ba8d4ac928514c47,df2a1743988640ab8a78377e18b785c2,7e0bb38e446e4f58a2925c359e2ce3f4,707a5a0e26244a44bdce0148b618de88,c2f2cedc581b48429cb01df2ec0f9f49,b44cde53b2014f9fb2dd45d98219ed61,a05e37395c97484b9dac1edbad912c32,a0a0db6e4ae04c4ea142c51970d011a9,5f1afd602a154b1bb43c840ab2f8ee27,0d863c2ea36744539f1caa50d6ea698f,43c4bc1f77564b3680be836b18b9acc4,c372d1b975c84e77a463f277bfb3180b,921a064041fb44db86cc18ca04c6166b,25e9c554b2b94c0c906d36e1337512a1,8dea6d33e18a434f8b44d884cf68331c,aed9a1f8730e4bcd962127f64fd7c4f2,ea89da74b98947a3bc6f5d9e2c27a316,1c2d18b718d248168049f07cdf363cf2,2b40aede791a4802b2decf0339252fe4\"],\"userIds\":[\"\"],\"unitName\":[\"系统管理\"],\"unitid\":[\"d094dfd0dd33402198ac8aac6deca01e\"],\"name\":[\"CMS管理员\"],\"code\":[\"cms\"],\"note\":[\"CMS管理\"],\"disabled\":[\"on\"]}]',NULL,'2018-02-01 13:52:28',336,1,'1a38a92345bd44ed98648b9162b2d67a',1517464349,0),(13,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController','enable','系统角色','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController.enable(java.lang.String,javax.servlet.http.HttpServletRequest)','[\"791cece1cbc047a2af788950a3c9e93e\",{}]',NULL,'2018-02-01 13:52:37',31,1,'1a38a92345bd44ed98648b9162b2d67a',1517464357,0),(14,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController','editDo','Sys_role','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController.editDo(com.aebiz.app.sys.modules.models.Sys_role,javax.servlet.http.HttpServletRequest)','[{\"id\":\"791cece1cbc047a2af788950a3c9e93e\",\"name\":\"CMS管理员\",\"code\":\"cms\",\"disabled\":false,\"unitid\":\"root\",\"note\":\"CMS管理\"},{\"id\":[\"791cece1cbc047a2af788950a3c9e93e\"],\"unitid\":[\"root\"],\"oldCode\":[\"cms\"],\"name\":[\"CMS管理员\"],\"code\":[\"cms\"],\"note\":[\"CMS管理\"]}]',NULL,'2018-02-01 13:53:49',104,1,'1a38a92345bd44ed98648b9162b2d67a',1517464429,0),(15,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.cms.CmsSiteController','addDo','Cms_site','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.cms.CmsSiteController.addDo(com.aebiz.app.cms.modules.models.Cms_site,javax.servlet.http.HttpServletRequest)','[{\"site_name\":\"Eysh\",\"site_sname\":\"traveler home\",\"site_domain\":\"www.xyy277.cn\",\"site_path\":\"eysh\",\"site_css\":\"default\",\"hasChildren\":false},{\"site_name\":[\"Eysh\"],\"site_sname\":[\"traveler home\"],\"site_domain\":[\"www.xyy277.cn\"],\"site_path\":[\"eysh\"],\"site_css\":[\"default\"]}]',NULL,'2018-02-01 13:56:39',238,1,'1a38a92345bd44ed98648b9162b2d67a',1517464599,0),(16,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1517464621,NULL),(17,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysUserController','ssoAddDo','新建用户','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysUserController.ssoAddDo(com.aebiz.app.sys.modules.models.Sys_user,javax.servlet.http.HttpServletRequest)','[{\"loginname\":\"adminxyy\",\"password\":\"277\",\"username\":\"xyy\",\"isOnline\":false,\"disabled\":false,\"email\":\"102589412@qq.com\",\"mobile\":\"18815793208\",\"duties\":\"\",\"loginSidebar\":false,\"loginBoxed\":false,\"loginScroll\":false,\"loginPjax\":false,\"unitid\":\"ac380368d900410caa5194a751d8f2fb\"},{\"unitid\":[\"ac380368d900410caa5194a751d8f2fb\"],\"type\":[\"01\"],\"loginname\":[\"adminxyy\"],\"password\":[\"277\"],\"username\":[\"xyy\"],\"mobile\":[\"18815793208\"],\"duties\":[\"\"],\"email\":[\"102589412@qq.com\"]}]',NULL,'2018-02-01 14:03:20',33,1,'1a38a92345bd44ed98648b9162b2d67a',1517465000,0),(18,'xyy','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'7272a53bc6fe443cbd0070f600fd9783',1517465014,NULL),(19,'xyy','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','logout','用户退出','LOGIN','public void com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController.logout(javax.servlet.http.HttpServletResponse,javax.servlet.http.HttpSession)','[]','null','2018-02-01 14:03:40',80,1,'',1517465020,0),(20,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1517465024,NULL),(21,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController','pushUser','添加用户到角色','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController.pushUser(java.lang.String,java.lang.String,javax.servlet.http.HttpServletRequest)','[\"7272a53bc6fe443cbd0070f600fd9783\",\"791cece1cbc047a2af788950a3c9e93e\",{\"menuIds\":[\"7272a53bc6fe443cbd0070f600fd9783\"],\"roleid\":[\"791cece1cbc047a2af788950a3c9e93e\"]}]',NULL,'2018-02-01 14:05:04',5,1,'1a38a92345bd44ed98648b9162b2d67a',1517465104,0),(22,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController','addDo','Sys_role','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController.addDo(com.aebiz.app.sys.modules.models.Sys_role,javax.servlet.http.HttpServletRequest)','[{\"name\":\"eysh后台管理员\",\"code\":\"eysh\",\"disabled\":false,\"unitid\":\"ac380368d900410caa5194a751d8f2fb\",\"note\":\"负责处理后台数据\"},{\"menuIds\":[\"\"],\"userIds\":[\"\"],\"unitName\":[\"eysh\"],\"unitid\":[\"ac380368d900410caa5194a751d8f2fb\"],\"name\":[\"eysh后台管理员\"],\"code\":[\"eysh\"],\"note\":[\"负责处理后台数据\"]}]',NULL,'2018-02-01 14:06:24',0,1,'1a38a92345bd44ed98648b9162b2d67a',1517465184,0),(23,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController','addDo','Sys_role','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController.addDo(com.aebiz.app.sys.modules.models.Sys_role,javax.servlet.http.HttpServletRequest)','[{\"name\":\"WXadmin\",\"code\":\"eysh\",\"disabled\":false,\"unitid\":\"ac380368d900410caa5194a751d8f2fb\",\"note\":\"负责处理微信后台数据\"},{\"menuIds\":[\"d71bf5725af6410a8970b34808169dfb,2896e9306e1347cfad778d471cb06a50,7ab1f391b9584a5fadfd06b6baeae4c7,36d8249922824876bdabc07c892a9cfc,140faae251f54b84979fabf62187d829,981b0671f38c4835a211ef648652b659,4ccb479b45834793a4eccf4739f0617a,1991770395df48569263225cdd741f1c,0631806d9edf4a0bbc675f260862bab4,ba796ecacfbb41f3a4685213e5b18380,76201449f9d348e08107869ca4ffb869,9ecbd017bbf549ff89ac08849d348010,6b529321690f4527bfbe80d638f9c1f3,adedb5da86184c269907c7ea24b6f73e,eb78f704d16047739749e6878ec8bdc4,fb490e890d41454da7c9d0cbf5041560,0ca6569701b840af934b5d6d38ae4097,dddf5fc6be4540f4bcb79b6f9bf8a71e,ee64d857d7144e0faaf4c6bf529d7704,d47013e699954826b13d3903f856c821,8b1d29398c8e480e893a50e2741e2e72,402459c22b7d46eab2229f47c479310f,3476ac041c284881878d9b8df4dab8b4,a7d1c610a3eb4da6b3ff246aa88eb765,c21897fc27254d4c92d9455c216abefb,5e4d15dde3c9481d8d581a739275b40d,de613ed29dae428b875cea4d93936973,4aa27cbb9f0b442dbc7634abc71875aa,d625d80589144d51a13c5ee0c091d0dc,3db17fd76f364a37be24bd72efe9dcde,f0712ecdb5e5484bb06813a64a1fb881,7a94ccd1913a4e459f51406432ea58ea,1f378d2531c04947ab1b43d0778e1eae,febf1ca1528c49daab59f8cbf698488b,d9abc363306f4d5fbef97a8f7a6cf4de,4cc2c6a4d9104bae876e7632f025be3d,d19f2346a5084b5e95308abc7654608b,a7c3c57335144e43be4e041202419d78,ef7db3ea2b0d4fac99302cb6c95b18ca,8102dca1971f41e583bc2af4229ea55a,50a4b244620a4ec5acedbb580543a79e,7cf34691816746e390e0191bf3a54c03,017d13cdc23d48118ac82940ac3ef105,0c33da3e9212406ab8ffe4d8f1674baf,9adbc1e74d7e4b589d8ccf90a70144d6,ded16b4a63cf42bf86b5626620abbe0a\"],\"userIds\":[\"\"],\"unitName\":[\"eysh\"],\"unitid\":[\"ac380368d900410caa5194a751d8f2fb\"],\"name\":[\"WXadmin\"],\"code\":[\"eysh\"],\"note\":[\"负责处理微信后台数据\"]}]',NULL,'2018-02-01 14:07:15',166,1,'1a38a92345bd44ed98648b9162b2d67a',1517465235,0),(24,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController','editDo','Sys_role','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController.editDo(com.aebiz.app.sys.modules.models.Sys_role,javax.servlet.http.HttpServletRequest)','[{\"id\":\"2ed3dd76c3ad4f98886d725bd8a15d6d\",\"name\":\"WXadmin\",\"code\":\"wx\",\"disabled\":false,\"unitid\":\"ac380368d900410caa5194a751d8f2fb\",\"note\":\"负责处理微信后台数据\"},{\"id\":[\"2ed3dd76c3ad4f98886d725bd8a15d6d\"],\"unitid\":[\"ac380368d900410caa5194a751d8f2fb\"],\"oldCode\":[\"eysh\"],\"name\":[\"WXadmin\"],\"code\":[\"wx\"],\"note\":[\"负责处理微信后台数据\"]}]',NULL,'2018-02-01 14:07:28',5,1,'1a38a92345bd44ed98648b9162b2d67a',1517465248,0),(25,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController','pushUser','添加用户到角色','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController.pushUser(java.lang.String,java.lang.String,javax.servlet.http.HttpServletRequest)','[\"7272a53bc6fe443cbd0070f600fd9783\",\"2ed3dd76c3ad4f98886d725bd8a15d6d\",{\"menuIds\":[\"7272a53bc6fe443cbd0070f600fd9783\"],\"roleid\":[\"2ed3dd76c3ad4f98886d725bd8a15d6d\"]}]',NULL,'2018-02-01 14:07:41',4,1,'1a38a92345bd44ed98648b9162b2d67a',1517465261,0),(26,'xyy','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'7272a53bc6fe443cbd0070f600fd9783',1517465294,NULL),(27,'xyy','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'7272a53bc6fe443cbd0070f600fd9783',1517465304,NULL),(28,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1517465323,NULL),(29,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysUserController','ssoAddDo','新建用户','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysUserController.ssoAddDo(com.aebiz.app.sys.modules.models.Sys_user,javax.servlet.http.HttpServletRequest)','[{\"loginname\":\"cmsadmin\",\"password\":\"1\",\"username\":\"David\",\"isOnline\":false,\"disabled\":false,\"email\":\"18888888888@qq.com\",\"mobile\":\"18888888888\",\"duties\":\"\",\"loginSidebar\":false,\"loginBoxed\":false,\"loginScroll\":false,\"loginPjax\":false,\"unitid\":\"ac380368d900410caa5194a751d8f2fb\"},{\"unitid\":[\"ac380368d900410caa5194a751d8f2fb\"],\"type\":[\"01\"],\"loginname\":[\"cmsadmin\"],\"password\":[\"1\"],\"username\":[\"David\"],\"mobile\":[\"18888888888\"],\"duties\":[\"\"],\"email\":[\"18888888888@qq.com\"]}]',NULL,'2018-02-01 14:11:14',16,1,'1a38a92345bd44ed98648b9162b2d67a',1517465474,0),(30,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController','pushUser','添加用户到角色','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController.pushUser(java.lang.String,java.lang.String,javax.servlet.http.HttpServletRequest)','[\"b323736ba474410d8045d04d476524e6\",\"791cece1cbc047a2af788950a3c9e93e\",{\"menuIds\":[\"b323736ba474410d8045d04d476524e6\"],\"roleid\":[\"791cece1cbc047a2af788950a3c9e93e\"]}]',NULL,'2018-02-01 14:11:58',5,1,'1a38a92345bd44ed98648b9162b2d67a',1517465518,0),(31,'David','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'b323736ba474410d8045d04d476524e6',1517465540,NULL),(32,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1517465556,NULL),(33,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController','delUser','从角色中删除用户','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController.delUser(java.lang.String,java.lang.String,javax.servlet.http.HttpServletRequest)','[\"7272a53bc6fe443cbd0070f600fd9783\",\"791cece1cbc047a2af788950a3c9e93e\",{\"menuIds\":[\"7272a53bc6fe443cbd0070f600fd9783\"],\"roleid\":[\"791cece1cbc047a2af788950a3c9e93e\"]}]',NULL,'2018-02-01 14:14:04',4,1,'1a38a92345bd44ed98648b9162b2d67a',1517465644,0),(34,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController','editDo','Sys_role','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController.editDo(com.aebiz.app.sys.modules.models.Sys_role,javax.servlet.http.HttpServletRequest)','[{\"id\":\"791cece1cbc047a2af788950a3c9e93e\",\"name\":\"CMS管理员\",\"code\":\"cms\",\"disabled\":false,\"unitid\":\"ac380368d900410caa5194a751d8f2fb\",\"note\":\"CMS管理\"},{\"id\":[\"791cece1cbc047a2af788950a3c9e93e\"],\"unitid\":[\"ac380368d900410caa5194a751d8f2fb\"],\"oldCode\":[\"cms\"],\"name\":[\"CMS管理员\"],\"code\":[\"cms\"],\"note\":[\"CMS管理\"]}]',NULL,'2018-02-01 14:15:05',4,1,'1a38a92345bd44ed98648b9162b2d67a',1517465705,0),(35,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysUserController','editDo','修改用户','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysUserController.editDo(com.aebiz.app.sys.modules.models.Sys_user,java.lang.String,java.lang.String,javax.servlet.http.HttpServletRequest)','[{\"id\":\"7272a53bc6fe443cbd0070f600fd9783\",\"loginname\":\"adminwx\",\"username\":\"xyy\",\"isOnline\":false,\"disabled\":false,\"email\":\"102589412@qq.com\",\"mobile\":\"18815793208\",\"duties\":\"\",\"loginSidebar\":false,\"loginBoxed\":false,\"loginScroll\":false,\"loginPjax\":false,\"unitid\":\"ac380368d900410caa5194a751d8f2fb\"},\"adminxyy\",\"18815793208\",{\"id\":[\"7272a53bc6fe443cbd0070f600fd9783\"],\"unitid\":[\"ac380368d900410caa5194a751d8f2fb\"],\"oldLoginname\":[\"adminxyy\"],\"oldMobile\":[\"18815793208\"],\"loginname\":[\"adminwx\"],\"username\":[\"xyy\"],\"mobile\":[\"18815793208\"],\"duties\":[\"\"],\"email\":[\"102589412@qq.com\"]}]',NULL,'2018-02-01 14:27:51',6,1,'1a38a92345bd44ed98648b9162b2d67a',1517466471,0),(36,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysUserController','editDo','修改用户','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysUserController.editDo(com.aebiz.app.sys.modules.models.Sys_user,java.lang.String,java.lang.String,javax.servlet.http.HttpServletRequest)','[{\"id\":\"b323736ba474410d8045d04d476524e6\",\"loginname\":\"admincms\",\"username\":\"David\",\"isOnline\":false,\"disabled\":false,\"email\":\"18888888888@qq.com\",\"mobile\":\"18888888888\",\"duties\":\"\",\"loginSidebar\":false,\"loginBoxed\":false,\"loginScroll\":false,\"loginPjax\":false,\"unitid\":\"ac380368d900410caa5194a751d8f2fb\"},\"cmsadmin\",\"18888888888\",{\"id\":[\"b323736ba474410d8045d04d476524e6\"],\"unitid\":[\"ac380368d900410caa5194a751d8f2fb\"],\"oldLoginname\":[\"cmsadmin\"],\"oldMobile\":[\"18888888888\"],\"loginname\":[\"admincms\"],\"username\":[\"David\"],\"mobile\":[\"18888888888\"],\"duties\":[\"\"],\"email\":[\"18888888888@qq.com\"]}]',NULL,'2018-02-01 14:28:01',4,1,'1a38a92345bd44ed98648b9162b2d67a',1517466481,0),(37,'xyy','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'7272a53bc6fe443cbd0070f600fd9783',1517466501,NULL),(38,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1517466524,NULL);

/*Table structure for table `sys_log_201803` */

DROP TABLE IF EXISTS `sys_log_201803`;

CREATE TABLE `sys_log_201803` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `ip` varchar(255) DEFAULT NULL COMMENT 'IP地址',
  `module` varchar(255) DEFAULT NULL COMMENT '请求模块',
  `action` varchar(255) DEFAULT NULL COMMENT '请求方法',
  `description` varchar(255) DEFAULT NULL COMMENT '日志描述',
  `type` varchar(10) DEFAULT NULL COMMENT '日志类型(默认:system)',
  `methodMeta` text COMMENT '方法',
  `parameters` text COMMENT '参数',
  `methodReturn` text COMMENT '返回值',
  `actionTime` varchar(20) DEFAULT NULL COMMENT '请求时间',
  `operationTime` int(32) DEFAULT NULL COMMENT '执行时间',
  `success` tinyint(1) DEFAULT NULL COMMENT '操作状态(0:失败 1:成功)',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `SYS_LOG_201803_INDEX` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8;

/*Data for the table `sys_log_201803` */

insert  into `sys_log_201803`(`id`,`username`,`ip`,`module`,`action`,`description`,`type`,`methodMeta`,`parameters`,`methodReturn`,`actionTime`,`operationTime`,`success`,`opBy`,`opAt`,`delFlag`) values (1,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521256912,NULL),(2,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','logout','用户退出','LOGIN','public void com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController.logout(javax.servlet.http.HttpServletResponse,javax.servlet.http.HttpSession)','[]','null','2018-03-17 11:52:28',86,1,'',1521258748,0),(3,'xyy','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'7272a53bc6fe443cbd0070f600fd9783',1521258757,NULL),(4,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521258787,NULL),(5,'xyy','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'7272a53bc6fe443cbd0070f600fd9783',1521258796,NULL),(6,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521357831,NULL),(7,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521433650,NULL),(8,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController','addDo','Sys_menu','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController.addDo(com.aebiz.app.sys.modules.models.Sys_menu,java.lang.String,java.util.List,java.util.List,java.util.List,javax.servlet.http.HttpServletRequest)','[{\"parentId\":\"\",\"name\":\"行者之家\",\"aliasName\":\"eysh\",\"system\":\"platform\",\"type\":\"menu\",\"href\":\"\",\"target\":\"\",\"icon\":\"\",\"isShow\":false,\"disabled\":false,\"permission\":\"eysh\",\"hasChildren\":false},null,null,null,null,{\"system\":[\"platform\"],\"type\":[\"menu\"],\"parentId\":[\"\"],\"setRoleVal\":[\"1\"],\"name\":[\"行者之家\"],\"aliasName\":[\"eysh\"],\"permission\":[\"eysh\"],\"href\":[\"\"],\"target\":[\"\"],\"icon\":[\"\"]}]',NULL,'2018-03-19 12:29:49',29,1,'1a38a92345bd44ed98648b9162b2d67a',1521433789,0),(9,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521433831,NULL),(10,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController','addDo','Sys_menu','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController.addDo(com.aebiz.app.sys.modules.models.Sys_menu,java.lang.String,java.util.List,java.util.List,java.util.List,javax.servlet.http.HttpServletRequest)','[{\"parentId\":\"0ddc69960e4e4912adad5cf245e288d9\",\"name\":\"行者管理\",\"aliasName\":\"tourist\",\"system\":\"platform\",\"type\":\"menu\",\"href\":\"/platform/tourist/user\",\"target\":\"\",\"icon\":\"\",\"isShow\":false,\"disabled\":false,\"permission\":\"eysh.tour.user\",\"hasChildren\":false},\"0\",[\"添加\",\"修改\",\"删除\"],[\"eysh.tour.user.add\",\"eysh.tour.user.edit\",\"eysh.tour.user.delete\"],[\"0\",\"0\",\"0\"],{\"system\":[\"platform\"],\"type\":[\"menu\"],\"parentId\":[\"0ddc69960e4e4912adad5cf245e288d9\"],\"setRoleVal\":[\"0\"],\"name\":[\"行者管理\"],\"aliasName\":[\"tourist\"],\"permission\":[\"eysh.tour.user\"],\"href\":[\"/platform/tourist/user\"],\"target\":[\"\"],\"icon\":[\"\"],\"listChildMenu.name\":[\"添加\",\"修改\",\"删除\"],\"listChildMenu.permission\":[\"eysh.tour.user.add\",\"eysh.tour.user.edit\",\"eysh.tour.user.delete\"],\"listChildMenu.disabled\":[\"0\",\"0\",\"0\"]}]',NULL,'2018-03-19 12:35:28',42,1,'1a38a92345bd44ed98648b9162b2d67a',1521434128,0),(11,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521434210,NULL),(12,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521434294,NULL),(13,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController','delete','Sys_menu','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController.delete(java.lang.String,javax.servlet.http.HttpServletRequest)','[\"2d11ae09fda9447d902ea39bdbeddd5a\",{}]',NULL,'2018-03-19 12:39:28',14,1,'1a38a92345bd44ed98648b9162b2d67a',1521434368,0),(14,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController','delete','Sys_menu','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController.delete(java.lang.String,javax.servlet.http.HttpServletRequest)','[\"0b66eb91037249af948c642a4805301f\",{}]',NULL,'2018-03-19 12:39:28',12,1,'1a38a92345bd44ed98648b9162b2d67a',1521434368,0),(15,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController','delete','Sys_menu','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController.delete(java.lang.String,javax.servlet.http.HttpServletRequest)','[\"1a0c25c3aea147bb93d698f7cfc0a9eb\",{}]',NULL,'2018-03-19 12:39:29',18,1,'1a38a92345bd44ed98648b9162b2d67a',1521434369,0),(16,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController','editDo','Sys_menu','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController.editDo(com.aebiz.app.sys.modules.models.Sys_menu,javax.servlet.http.HttpServletRequest)','[{\"id\":\"bbc135b5efef4e8484576126e5e4f9c4\",\"parentId\":\"0ddc69960e4e4912adad5cf245e288d9\",\"name\":\"行者会员\",\"aliasName\":\"tourist\",\"href\":\"\",\"target\":\"\",\"icon\":\"\",\"isShow\":true,\"disabled\":false,\"permission\":\"eysh.tour\",\"hasChildren\":false},{\"id\":[\"bbc135b5efef4e8484576126e5e4f9c4\"],\"isShow\":[\"true\"],\"hasChildren\":[\"false\"],\"oldPermission\":[\"eysh.tour.user\"],\"parentId\":[\"0ddc69960e4e4912adad5cf245e288d9\"],\"name\":[\"行者会员\"],\"aliasName\":[\"tourist\"],\"permission\":[\"eysh.tour\"],\"href\":[\"\"],\"target\":[\"\"],\"icon\":[\"\"]}]',NULL,'2018-03-19 12:40:09',10,1,'1a38a92345bd44ed98648b9162b2d67a',1521434409,0),(17,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController','addDo','Sys_menu','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController.addDo(com.aebiz.app.sys.modules.models.Sys_menu,java.lang.String,java.util.List,java.util.List,java.util.List,javax.servlet.http.HttpServletRequest)','[{\"parentId\":\"bbc135b5efef4e8484576126e5e4f9c4\",\"name\":\"会员管理\",\"aliasName\":\"user\",\"system\":\"platform\",\"type\":\"menu\",\"href\":\"/platform/tourist/user\",\"target\":\"\",\"icon\":\"\",\"isShow\":false,\"disabled\":false,\"permission\":\"eysh.tour.user\",\"hasChildren\":false},\"0\",[\"添加\",\"修改\",\"删除\"],[\"eysh.tour.user.add\",\"eysh.tour.user.edit\",\"eysh.tour.user.delete\"],[\"0\",\"0\",\"0\"],{\"system\":[\"platform\"],\"type\":[\"menu\"],\"parentId\":[\"bbc135b5efef4e8484576126e5e4f9c4\"],\"setRoleVal\":[\"0\"],\"name\":[\"会员管理\"],\"aliasName\":[\"user\"],\"permission\":[\"eysh.tour.user\"],\"href\":[\"/platform/tourist/user\"],\"target\":[\"\"],\"icon\":[\"\"],\"listChildMenu.name\":[\"添加\",\"修改\",\"删除\"],\"listChildMenu.permission\":[\"eysh.tour.user.add\",\"eysh.tour.user.edit\",\"eysh.tour.user.delete\"],\"listChildMenu.disabled\":[\"0\",\"0\",\"0\"]}]',NULL,'2018-03-19 12:40:53',92,1,'1a38a92345bd44ed98648b9162b2d67a',1521434453,0),(18,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521434465,NULL),(19,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521468706,NULL),(20,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.tourist.TourUserController','addDo','Tour_user','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.tourist.TourUserController.addDo(com.aebiz.app.tourist.modules.models.Tour_user,javax.servlet.http.HttpServletRequest)','[{\"loginname\":\"1\",\"nickname\":\"1\",\"password\":\"1\",\"salt\":\"1\",\"mobile\":\"1\",\"email\":\"1\",\"qq\":\"1\",\"name\":\"1\",\"id_num\":\"1\",\"disabled\":true,\"province\":\"1\",\"city\":\"1\",\"area\":\"1\",\"address\":\"1\",\"login_num\":1,\"last_ip\":\"1\",\"last_time\":\"1\"},{\"loginname\":[\"1\"],\"nickname\":[\"1\"],\"password\":[\"1\"],\"salt\":[\"1\"],\"mobile\":[\"1\"],\"email\":[\"1\"],\"qq\":[\"1\"],\"name\":[\"1\"],\"id_num\":[\"1\"],\"disabled\":[\"1\"],\"province\":[\"1\"],\"city\":[\"1\"],\"area\":[\"1\"],\"address\":[\"1\"],\"login_num\":[\"1\"],\"last_ip\":[\"1\"],\"last_time\":[\"1\"]}]',NULL,'2018-03-19 22:39:12',161,1,'1a38a92345bd44ed98648b9162b2d67a',1521470352,0),(21,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521523555,NULL),(22,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sms.SmsMouldInfoController','addDo','Sms_mould_info','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sms.SmsMouldInfoController.addDo(com.aebiz.app.sms.modules.models.Sms_mould_info,javax.servlet.http.HttpServletRequest)','[{\"code\":\"1\",\"ali_code\":\"SMS_127167995\",\"name\":\"注册手机验证\",\"content\":\"本次验证码为：${code}\"},{\"code\":[\"1\"],\"ali_code\":[\"SMS_127167995\"],\"name\":[\"注册手机验证\"],\"content\":[\"本次验证码为：${code}\"]}]',NULL,'2018-03-20 13:37:10',69,1,'1a38a92345bd44ed98648b9162b2d67a',1521524230,0),(23,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521524254,NULL),(24,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController','delete','Sys_menu','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController.delete(java.lang.String,javax.servlet.http.HttpServletRequest)','[\"6571aa5e11af4963a22436ca7dba3ff0\",{}]',NULL,'2018-03-20 13:37:52',23,1,'1a38a92345bd44ed98648b9162b2d67a',1521524272,0),(25,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521524283,NULL),(26,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521524387,NULL),(27,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sms.SmsMouldInfoController','editDo','Sms_mould_info','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sms.SmsMouldInfoController.editDo(com.aebiz.app.sms.modules.models.Sms_mould_info,javax.servlet.http.HttpServletRequest)','[{\"id\":\"dd66f4d426dc4fcc9b356f081231f9c3\",\"code\":\"1\",\"ali_code\":\"SMS_127167995\",\"name\":\"注册手机验证\",\"content\":\"验证码为：${code}，您正在注册成为eysh会员，感谢您的支持！\"},{\"id\":[\"dd66f4d426dc4fcc9b356f081231f9c3\"],\"code\":[\"1\"],\"ali_code\":[\"SMS_127167995\"],\"name\":[\"注册手机验证\"],\"content\":[\"验证码为：${code}，您正在注册成为eysh会员，感谢您的支持！\"]}]',NULL,'2018-03-20 13:49:03',50,1,'1a38a92345bd44ed98648b9162b2d67a',1521524943,0),(28,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sms.SmsMouldInfoController','editDo','Sms_mould_info','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sms.SmsMouldInfoController.editDo(com.aebiz.app.sms.modules.models.Sms_mould_info,javax.servlet.http.HttpServletRequest)','[{\"id\":\"dd66f4d426dc4fcc9b356f081231f9c3\",\"code\":\"SMS_001\",\"ali_code\":\"SMS_127167995\",\"name\":\"注册手机验证\",\"content\":\"验证码为：${code}，您正在注册成为eysh会员，感谢您的支持！\"},{\"id\":[\"dd66f4d426dc4fcc9b356f081231f9c3\"],\"code\":[\"SMS_001\"],\"ali_code\":[\"SMS_127167995\"],\"name\":[\"注册手机验证\"],\"content\":[\"验证码为：${code}，您正在注册成为eysh会员，感谢您的支持！\"]}]',NULL,'2018-03-20 13:54:04',4,1,'1a38a92345bd44ed98648b9162b2d67a',1521525244,0),(29,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521532420,NULL),(30,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','regiserDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.regiserDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277\"],\"phone\":[\"18888888999\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"907507646\"]}]',NULL,'2018-03-20 16:56:28',99,1,'',1521536188,0),(31,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','regiserDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.regiserDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277\"],\"phone\":[\"18888888999\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"907507646\"]}]',NULL,'2018-03-20 17:00:22',59,1,'',1521536422,0),(32,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','regiserDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.regiserDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277\"],\"phone\":[\"18888888999\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"907507646\"]}]',NULL,'2018-03-20 17:00:30',56,1,'',1521536430,0),(33,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','regiserDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.regiserDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277\"],\"phone\":[\"18888888999\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"907507646\"]}]',NULL,'2018-03-20 17:00:36',80,1,'',1521536437,0),(34,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','regiserDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.regiserDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277\"],\"phone\":[\"18888888999\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"907507646\"]}]',NULL,'2018-03-20 17:01:27',61,1,'',1521536487,0),(35,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','regiserDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.regiserDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277\"],\"phone\":[\"18888888999\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"907507646\"]}]',NULL,'2018-03-20 17:02:08',81,1,'',1521536528,0),(36,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277\"],\"phone\":[\"18999999999\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"907507646\"]}]',NULL,'2018-03-20 17:04:27',26932,1,'',1521536694,0),(37,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277\"],\"phone\":[\"18999999999\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"907507646\"]}]',NULL,'2018-03-20 17:05:32',26863,1,'',1521536759,0),(38,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277\"],\"phone\":[\"18999999999\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"907507646\"]}]',NULL,'2018-03-20 17:10:19',101975,1,'',1521537121,0),(39,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277\"],\"phone\":[\"18999999999\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"907507646\"]}]',NULL,'2018-03-20 17:13:43',17007,1,'',1521537240,0),(40,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277\"],\"phone\":[\"18999999999\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"907507646\"]}]',NULL,'2018-03-20 17:15:43',2663,1,'',1521537345,0),(41,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277\"],\"phone\":[\"18999999999\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"907507646\"]}]',NULL,'2018-03-20 17:15:54',2733,1,'',1521537357,0),(42,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277\"],\"phone\":[\"18998988989\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"89123123\"]}]',NULL,'2018-03-20 17:19:40',2536,1,'',1521537582,0),(43,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin2777\"],\"phone\":[\"18888888888\"],\"password\":[\"xyy277\"],\"name\":[\"刘备\"],\"qq\":[\"11111111111\"]}]',NULL,'2018-03-20 17:21:39',10879,1,'',1521537710,0),(44,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin2771\"],\"phone\":[\"18949494949\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"1231231231\"]}]',NULL,'2018-03-20 17:24:30',4084,1,'',1521537874,0),(45,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin2771\"],\"phone\":[\"18949494949\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"1231231231\"]}]',NULL,'2018-03-20 17:24:36',79348,1,'',1521537956,0),(46,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277\"],\"phone\":[\"18949234234\"],\"password\":[\"xyy277\"],\"name\":[\"按照\"],\"qq\":[\"12315123\"]}]',NULL,'2018-03-20 17:26:24',1799,1,'',1521537986,0),(47,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277\"],\"phone\":[\"18949234234\"],\"password\":[\"xyy277\"],\"name\":[\"按照\"],\"qq\":[\"12315123\"]}]',NULL,'2018-03-20 17:26:29',80156,1,'',1521538069,0),(48,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277\"],\"phone\":[\"18888888999\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"907507646\"]}]',NULL,'2018-03-20 17:48:09',1996,1,'',1521539291,0),(49,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277\"],\"phone\":[\"18888888999\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"907507646\"]}]',NULL,'2018-03-20 17:48:52',3283,1,'',1521539335,0),(50,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277\"],\"phone\":[\"18888888999\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"907507646\"]}]',NULL,'2018-03-20 17:49:03',2,1,'',1521539343,0),(51,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277777\"],\"phone\":[\"18888888888\"],\"password\":[\"xyy277\"],\"name\":[\"张家界\"],\"qq\":[\"1231234124\"]}]',NULL,'2018-03-20 17:59:15',3,1,'',1521539955,0),(52,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277777\"],\"phone\":[\"18888888888\"],\"password\":[\"xyy277\"],\"name\":[\"张家界\"],\"qq\":[\"1231234124\"]}]',NULL,'2018-03-20 17:59:18',4,1,'',1521539958,0),(53,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277777\"],\"phone\":[\"18888888888\"],\"password\":[\"xyy277\"],\"name\":[\"张家界\"],\"qq\":[\"1231234124\"]}]',NULL,'2018-03-20 17:59:22',4,1,'',1521539962,0),(54,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277777\"],\"phone\":[\"18888888888\"],\"password\":[\"xyy277\"],\"name\":[\"张家界\"],\"qq\":[\"1231234124\"]}]',NULL,'2018-03-20 17:59:23',4,1,'',1521539963,0),(55,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277123123\"],\"phone\":[\"18888888881\"],\"password\":[\"xyy277\"],\"name\":[\"爱的\"],\"qq\":[\"123154125\"]}]',NULL,'2018-03-20 18:00:37',19,1,'',1521540037,0),(56,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277123123\"],\"phone\":[\"18888888881\"],\"password\":[\"xyy277\"],\"name\":[\"爱的\"],\"qq\":[\"123154125\"]}]',NULL,'2018-03-20 18:00:38',2,1,'',1521540038,0),(57,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin27712314\"],\"phone\":[\"18949461231\"],\"password\":[\"xyy277\"],\"name\":[\"发噶啥\"],\"qq\":[\"12341512\"]}]',NULL,'2018-03-20 18:04:12',16,1,'',1521540252,0),(58,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin2771253123\"],\"phone\":[\"18954123124\"],\"password\":[\"xyy277\"],\"name\":[\"嘎嘎的\"],\"qq\":[\"253124124\"]}]',NULL,'2018-03-20 18:05:23',15,1,'',1521540323,0),(59,'','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"admin277512415\"],\"phone\":[\"18856345251\"],\"password\":[\"xyy277\"],\"name\":[\"嘎斯坦福\"],\"qq\":[\"1241235125\"]}]',NULL,'2018-03-20 18:06:27',11,1,'',1521540387,0),(60,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521540430,NULL),(61,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521547788,NULL),(62,'','127.0.0.1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"zjj1995\"],\"phone\":[\"18949464266\"],\"password\":[\"zjj1995\"],\"name\":[\"周总\"],\"qq\":[\"907507646\"]}]',NULL,'2018-03-20 22:01:23',41,1,'',1521554483,0),(63,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521775221,NULL),(64,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521787924,NULL),(65,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521788201,NULL),(66,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521794163,NULL),(67,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController','editDo','Sys_menu','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController.editDo(com.aebiz.app.sys.modules.models.Sys_menu,javax.servlet.http.HttpServletRequest)','[{\"id\":\"bbc135b5efef4e8484576126e5e4f9c4\",\"parentId\":\"0ddc69960e4e4912adad5cf245e288d9\",\"name\":\"eysh管理\",\"aliasName\":\"tourist\",\"href\":\"\",\"target\":\"\",\"icon\":\"\",\"isShow\":true,\"disabled\":false,\"permission\":\"eysh.tour\",\"hasChildren\":true},{\"id\":[\"bbc135b5efef4e8484576126e5e4f9c4\"],\"isShow\":[\"true\"],\"hasChildren\":[\"true\"],\"oldPermission\":[\"eysh.tour\"],\"parentId\":[\"0ddc69960e4e4912adad5cf245e288d9\"],\"name\":[\"eysh管理\"],\"aliasName\":[\"tourist\"],\"permission\":[\"eysh.tour\"],\"href\":[\"\"],\"target\":[\"\"],\"icon\":[\"\"]}]',NULL,'2018-03-23 16:37:55',13,1,'1a38a92345bd44ed98648b9162b2d67a',1521794276,0),(68,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController','addDo','Sys_menu','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController.addDo(com.aebiz.app.sys.modules.models.Sys_menu,java.lang.String,java.util.List,java.util.List,java.util.List,javax.servlet.http.HttpServletRequest)','[{\"parentId\":\"bbc135b5efef4e8484576126e5e4f9c4\",\"name\":\"游记管理\",\"aliasName\":\"journey\",\"system\":\"platform\",\"type\":\"menu\",\"href\":\"/platform/tourist/journey\",\"target\":\"\",\"icon\":\"\",\"isShow\":false,\"disabled\":false,\"permission\":\"eysh.tour.journey\",\"hasChildren\":false},\"0\",[\"添加\",\"修改\",\"删除\"],[\"eysh.tour.journey.add\",\"eysh.tour.journey.edit\",\"eysh.tour.journey.delete\"],[\"0\",\"0\",\"0\"],{\"system\":[\"platform\"],\"type\":[\"menu\"],\"parentId\":[\"bbc135b5efef4e8484576126e5e4f9c4\"],\"setRoleVal\":[\"0\"],\"name\":[\"游记管理\"],\"aliasName\":[\"journey\"],\"permission\":[\"eysh.tour.journey\"],\"href\":[\"/platform/tourist/journey\"],\"target\":[\"\"],\"icon\":[\"\"],\"listChildMenu.name\":[\"添加\",\"修改\",\"删除\"],\"listChildMenu.permission\":[\"eysh.tour.journey.add\",\"eysh.tour.journey.edit\",\"eysh.tour.journey.delete\"],\"listChildMenu.disabled\":[\"0\",\"0\",\"0\"]}]',NULL,'2018-03-23 16:38:42',91,1,'1a38a92345bd44ed98648b9162b2d67a',1521794322,0),(69,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521794338,NULL),(70,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.tourist.TourJourneyController','addDo','Tour_journey','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.tourist.TourJourneyController.addDo(com.aebiz.app.tourist.modules.models.Tour_journey,javax.servlet.http.HttpServletRequest)','[{\"title\":\"墨尔本自驾游\",\"status\":0,\"author\":\"david\",\"readnum\":1,\"up_number\":0,\"comment_no\":0},{\"title\":[\"墨尔本自驾游\"],\"status\":[\"0\"],\"author\":[\"david\"],\"readnum\":[\"1\"],\"up_number\":[\"0\"],\"comment_no\":[\"0\"]}]',NULL,'2018-03-23 16:41:27',63,1,'1a38a92345bd44ed98648b9162b2d67a',1521794487,0),(71,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521819762,NULL),(72,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521848489,NULL),(73,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521848531,NULL),(74,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521848554,NULL),(75,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521896380,NULL),(76,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController','addDo','Sys_menu','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController.addDo(com.aebiz.app.sys.modules.models.Sys_menu,java.lang.String,java.util.List,java.util.List,java.util.List,javax.servlet.http.HttpServletRequest)','[{\"parentId\":\"bbc135b5efef4e8484576126e5e4f9c4\",\"name\":\"精彩短句\",\"aliasName\":\"verse\",\"system\":\"platform\",\"type\":\"menu\",\"href\":\"/platform/tourist/verse\",\"target\":\"\",\"icon\":\"\",\"isShow\":false,\"disabled\":false,\"permission\":\"eysh.tour.verse\",\"hasChildren\":false},\"0\",[\"添加\",\"修改\",\"删除\"],[\"eysh.tour.verse.add\",\"eysh.tour.verse.edit\",\"eysh.tour.verse.delete\"],[\"0\",\"0\",\"0\"],{\"system\":[\"platform\"],\"type\":[\"menu\"],\"parentId\":[\"bbc135b5efef4e8484576126e5e4f9c4\"],\"setRoleVal\":[\"0\"],\"name\":[\"精彩短句\"],\"aliasName\":[\"verse\"],\"permission\":[\"eysh.tour.verse\"],\"href\":[\"/platform/tourist/verse\"],\"target\":[\"\"],\"icon\":[\"\"],\"listChildMenu.name\":[\"添加\",\"修改\",\"删除\"],\"listChildMenu.permission\":[\"eysh.tour.verse.add\",\"eysh.tour.verse.edit\",\"eysh.tour.verse.delete\"],\"listChildMenu.disabled\":[\"0\",\"0\",\"0\"]}]',NULL,'2018-03-24 21:00:28',57,1,'1a38a92345bd44ed98648b9162b2d67a',1521896429,0),(77,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521896435,NULL),(78,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521896456,NULL),(79,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521901041,NULL),(80,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.cms.CmsSiteController','editDo','Cms_site','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.cms.CmsSiteController.editDo(com.aebiz.app.cms.modules.models.Cms_site,javax.servlet.http.HttpServletRequest)','[{\"id\":\"6bfdf86056394ed1bc23c50b33df190b\",\"site_name\":\"Eysh\",\"site_sname\":\"Eysh\",\"site_domain\":\"www.xyy277.cn\",\"site_path\":\"eysh\",\"site_css\":\"default\",\"hasChildren\":false},{\"id\":[\"6bfdf86056394ed1bc23c50b33df190b\"],\"site_name\":[\"Eysh\"],\"site_sname\":[\"Eysh\"],\"site_domain\":[\"www.xyy277.cn\"],\"site_path\":[\"eysh\"],\"site_css\":[\"default\"]}]',NULL,'2018-03-24 22:25:40',455,1,'1a38a92345bd44ed98648b9162b2d67a',1521901541,0),(81,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521902452,NULL),(82,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521903284,NULL),(83,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521903929,NULL),(84,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521906586,NULL),(85,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521941657,NULL),(86,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysConfController','addDo','新增系统参数','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysConfController.addDo(com.aebiz.app.sys.modules.models.Sys_config)','[{\"configKey\":\"APP_PHONE\",\"configValue\":\"18949464266\",\"note\":\"EYSH唯一phone\"}]',NULL,'2018-03-25 09:35:58',28,1,'1a38a92345bd44ed98648b9162b2d67a',1521941758,0),(87,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521946323,NULL),(88,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController','addDo','Sys_menu','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController.addDo(com.aebiz.app.sys.modules.models.Sys_menu,java.lang.String,java.util.List,java.util.List,java.util.List,javax.servlet.http.HttpServletRequest)','[{\"parentId\":\"bbc135b5efef4e8484576126e5e4f9c4\",\"name\":\"官方联系\",\"aliasName\":\"contract\",\"system\":\"platform\",\"type\":\"menu\",\"href\":\"/platform/tourist/linkman\",\"target\":\"\",\"icon\":\"\",\"isShow\":false,\"disabled\":false,\"permission\":\"eysh.tour.linkman\",\"hasChildren\":false},\"0\",[\"添加\",\"修改\",\"删除\"],[\"eysh.tour.linkman.add\",\"eysh.tour.linkman.edit\",\"eysh.tour.linkman.delete\"],[\"0\",\"0\",\"0\"],{\"system\":[\"platform\"],\"type\":[\"menu\"],\"parentId\":[\"bbc135b5efef4e8484576126e5e4f9c4\"],\"setRoleVal\":[\"0\"],\"name\":[\"官方联系\"],\"aliasName\":[\"contract\"],\"permission\":[\"eysh.tour.linkman\"],\"href\":[\"/platform/tourist/linkman\"],\"target\":[\"\"],\"icon\":[\"\"],\"listChildMenu.name\":[\"添加\",\"修改\",\"删除\"],\"listChildMenu.permission\":[\"eysh.tour.linkman.add\",\"eysh.tour.linkman.edit\",\"eysh.tour.linkman.delete\"],\"listChildMenu.disabled\":[\"0\",\"0\",\"0\"]}]',NULL,'2018-03-25 10:52:42',52,1,'1a38a92345bd44ed98648b9162b2d67a',1521946362,0),(89,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521946378,NULL),(90,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521955939,NULL),(91,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521964599,NULL),(92,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysUserController','ssoAddDo','新建用户','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysUserController.ssoAddDo(com.aebiz.app.sys.modules.models.Sys_user,javax.servlet.http.HttpServletRequest)','[{\"loginname\":\"eysh\",\"password\":\"1\",\"username\":\"admin\",\"isOnline\":false,\"disabled\":false,\"email\":\"\",\"mobile\":\"18949464266\",\"duties\":\"\",\"loginSidebar\":false,\"loginBoxed\":false,\"loginScroll\":false,\"loginPjax\":false,\"unitid\":\"ac380368d900410caa5194a751d8f2fb\"},{\"unitid\":[\"ac380368d900410caa5194a751d8f2fb\"],\"type\":[\"01\"],\"loginname\":[\"eysh\"],\"password\":[\"1\"],\"username\":[\"admin\"],\"mobile\":[\"18949464266\"],\"duties\":[\"\"],\"email\":[\"\"]}]',NULL,'2018-03-25 16:00:01',3,1,'1a38a92345bd44ed98648b9162b2d67a',1521964801,0),(93,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysUserController','ssoAddDo','新建用户','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysUserController.ssoAddDo(com.aebiz.app.sys.modules.models.Sys_user,javax.servlet.http.HttpServletRequest)','[{\"loginname\":\"eysh\",\"password\":\"1\",\"username\":\"admin\",\"isOnline\":false,\"disabled\":false,\"email\":\"\",\"mobile\":\"18133687482\",\"duties\":\"\",\"loginSidebar\":false,\"loginBoxed\":false,\"loginScroll\":false,\"loginPjax\":false,\"unitid\":\"ac380368d900410caa5194a751d8f2fb\"},{\"unitid\":[\"ac380368d900410caa5194a751d8f2fb\"],\"type\":[\"01\"],\"loginname\":[\"eysh\"],\"password\":[\"1\"],\"username\":[\"admin\"],\"mobile\":[\"18133687482\"],\"duties\":[\"\"],\"email\":[\"\"]}]',NULL,'2018-03-25 16:00:32',46,1,'1a38a92345bd44ed98648b9162b2d67a',1521964832,0),(94,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysUserController','enable','启用用户','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysUserController.enable(java.lang.String,javax.servlet.http.HttpServletRequest)','[\"b263771718bd43d6bcfc34f2b538e5b2\",{}]',NULL,'2018-03-25 16:01:52',4,1,'1a38a92345bd44ed98648b9162b2d67a',1521964912,0),(95,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController','addDo','Sys_role','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController.addDo(com.aebiz.app.sys.modules.models.Sys_role,javax.servlet.http.HttpServletRequest)','[{\"name\":\"adminEysh\",\"code\":\"eysh\",\"disabled\":false,\"unitid\":\"ac380368d900410caa5194a751d8f2fb\",\"note\":\"行者之家管理员\"},{\"menuIds\":[\"0ddc69960e4e4912adad5cf245e288d9,bbc135b5efef4e8484576126e5e4f9c4,5950f4bd46054157b910a3d45ffadf09,0a6e029ddaa94c7fae7b0cab1f64f8da,04be9536b3fc4797b09877d2ac864f6d,01574e284bd7493cb2dd65240603ef0a,5b7414e49e4d4e1d80463bf8714bfa41,75b5a8bf7c324760be57e4b3ffc0b015,b561ae6ebb1a4e57a1ab6d2dc30d8da2,192cc19988df4872b38882cae9967c6a,5e84be56a3274b24b625624e89408627,40ccdaad894d4ed48a1c0082f8a19d00,6da598ce6bba48069b39b0db06a0837c,ee37dbb88f00473e8881106c7da1a46e,bf5667954ffc42ccbad0944e8e562928,b0193970d4c6486ea6e5e51cdd8fe581,713b83e55ecb4b67a65492bba530f170,51097a633ea246aca36a0bed9a9ea59a\"],\"userIds\":[\"\"],\"unitName\":[\"eysh\"],\"unitid\":[\"ac380368d900410caa5194a751d8f2fb\"],\"name\":[\"adminEysh\"],\"code\":[\"eysh\"],\"note\":[\"行者之家管理员\"]}]',NULL,'2018-03-25 16:02:34',66,1,'1a38a92345bd44ed98648b9162b2d67a',1521964954,0),(96,'admin','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'b263771718bd43d6bcfc34f2b538e5b2',1521965009,NULL),(97,'admin','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','logout','用户退出','LOGIN','public void com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController.logout(javax.servlet.http.HttpServletResponse,javax.servlet.http.HttpSession)','[]','null','2018-03-25 16:03:36',15,1,'',1521965016,0),(98,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521965023,NULL),(99,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController','pushUser','添加用户到角色','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysRoleController.pushUser(java.lang.String,java.lang.String,javax.servlet.http.HttpServletRequest)','[\"b263771718bd43d6bcfc34f2b538e5b2\",\"528d6a61affd4d9ab346d10e7653da81\",{\"menuIds\":[\"b263771718bd43d6bcfc34f2b538e5b2\"],\"roleid\":[\"528d6a61affd4d9ab346d10e7653da81\"]}]',NULL,'2018-03-25 16:03:58',4,1,'1a38a92345bd44ed98648b9162b2d67a',1521965039,0),(100,'admin','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'b263771718bd43d6bcfc34f2b538e5b2',1521965080,NULL),(101,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521965093,NULL),(102,'admin','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'b263771718bd43d6bcfc34f2b538e5b2',1521965168,NULL),(103,'','127.0.0.1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"xyy277\"],\"phone\":[\"18888888888\"],\"password\":[\"xyy277\"],\"name\":[\"大牛\"],\"qq\":[\"123456\"]}]',NULL,'2018-03-25 16:22:06',12,1,'',1521966126,0),(104,'','127.0.0.1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"xyy2777\"],\"phone\":[\"18999999999\"],\"password\":[\"xyy277\"],\"name\":[\"大牛\"],\"qq\":[\"123456\"]}]',NULL,'2018-03-25 16:24:10',3,1,'',1521966250,0),(105,'','127.0.0.1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"周123\"],\"phone\":[\"15000000000\"],\"password\":[\"xyy277\"],\"name\":[\"大牛\"],\"qq\":[\"123456\"]}]',NULL,'2018-03-25 16:25:32',12,1,'',1521966332,0),(106,'','127.0.0.1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"周1234\"],\"phone\":[\"15111111111\"],\"password\":[\"xyy277\"],\"name\":[\"大牛\"],\"qq\":[\"123456\"]}]',NULL,'2018-03-25 16:29:00',15,1,'91835205d4a24c8a824bd48be2f6e1c9',1521966540,0),(107,'admin','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','logout','用户退出','LOGIN','public void com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController.logout(javax.servlet.http.HttpServletResponse,javax.servlet.http.HttpSession)','[]','null','2018-03-25 17:09:15',42,1,'',1521968955,0),(108,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521968963,NULL),(109,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController','editDo','Sys_menu','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController.editDo(com.aebiz.app.sys.modules.models.Sys_menu,javax.servlet.http.HttpServletRequest)','[{\"id\":\"5e84be56a3274b24b625624e89408627\",\"parentId\":\"bbc135b5efef4e8484576126e5e4f9c4\",\"name\":\"精彩短篇\",\"aliasName\":\"verse\",\"href\":\"/platform/tourist/verse\",\"target\":\"\",\"icon\":\"\",\"isShow\":true,\"disabled\":false,\"permission\":\"eysh.tour.verse\",\"hasChildren\":false},{\"id\":[\"5e84be56a3274b24b625624e89408627\"],\"isShow\":[\"true\"],\"hasChildren\":[\"false\"],\"oldPermission\":[\"eysh.tour.verse\"],\"parentId\":[\"bbc135b5efef4e8484576126e5e4f9c4\"],\"name\":[\"精彩短篇\"],\"aliasName\":[\"verse\"],\"permission\":[\"eysh.tour.verse\"],\"href\":[\"/platform/tourist/verse\"],\"target\":[\"\"],\"icon\":[\"\"]}]',NULL,'2018-03-25 17:52:47',7,1,'1a38a92345bd44ed98648b9162b2d67a',1521971567,0),(110,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521971573,NULL),(111,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController','editDo','Sys_menu','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController.editDo(com.aebiz.app.sys.modules.models.Sys_menu,javax.servlet.http.HttpServletRequest)','[{\"id\":\"bf5667954ffc42ccbad0944e8e562928\",\"parentId\":\"bbc135b5efef4e8484576126e5e4f9c4\",\"name\":\"参数管理\",\"aliasName\":\"contract\",\"href\":\"/platform/tourist/linkman\",\"target\":\"\",\"icon\":\"\",\"isShow\":true,\"disabled\":false,\"permission\":\"eysh.tour.linkman\",\"hasChildren\":false},{\"id\":[\"bf5667954ffc42ccbad0944e8e562928\"],\"isShow\":[\"true\"],\"hasChildren\":[\"false\"],\"oldPermission\":[\"eysh.tour.linkman\"],\"parentId\":[\"bbc135b5efef4e8484576126e5e4f9c4\"],\"name\":[\"参数管理\"],\"aliasName\":[\"contract\"],\"permission\":[\"eysh.tour.linkman\"],\"href\":[\"/platform/tourist/linkman\"],\"target\":[\"\"],\"icon\":[\"\"]}]',NULL,'2018-03-25 17:53:47',4,1,'1a38a92345bd44ed98648b9162b2d67a',1521971627,0),(112,'超级管理员','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'be74468e1345473cb1314610536281e5',1521972996,NULL),(113,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521973431,NULL),(114,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521979487,NULL),(115,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController','editDo','Sys_menu','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController.editDo(com.aebiz.app.sys.modules.models.Sys_menu,javax.servlet.http.HttpServletRequest)','[{\"id\":\"bf5667954ffc42ccbad0944e8e562928\",\"parentId\":\"bbc135b5efef4e8484576126e5e4f9c4\",\"name\":\"参数管理\",\"aliasName\":\"linkman\",\"href\":\"/platform/tourist/linkman\",\"target\":\"\",\"icon\":\"\",\"isShow\":true,\"disabled\":false,\"permission\":\"eysh.tour.linkman\",\"hasChildren\":false},{\"id\":[\"bf5667954ffc42ccbad0944e8e562928\"],\"isShow\":[\"true\"],\"hasChildren\":[\"false\"],\"oldPermission\":[\"eysh.tour.linkman\"],\"parentId\":[\"bbc135b5efef4e8484576126e5e4f9c4\"],\"name\":[\"参数管理\"],\"aliasName\":[\"linkman\"],\"permission\":[\"eysh.tour.linkman\"],\"href\":[\"/platform/tourist/linkman\"],\"target\":[\"\"],\"icon\":[\"\"]}]',NULL,'2018-03-25 20:11:00',6,1,'1a38a92345bd44ed98648b9162b2d67a',1521979860,0),(116,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521979865,NULL),(117,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521984243,NULL),(118,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1522116456,NULL),(119,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.tourist.TourLinkmanController','addDo','Tour_linkman','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.tourist.TourLinkmanController.addDo(com.aebiz.app.tourist.modules.models.Tour_linkman,javax.servlet.http.HttpServletRequest)','[{\"phone\":\"1\",\"email\":\"11\",\"qq\":\"1\"},{\"phone\":[\"1\"],\"email\":[\"11\"],\"qq\":[\"1\"]}]',NULL,'2018-03-27 10:37:12',34,1,'1a38a92345bd44ed98648b9162b2d67a',1522118232,0),(120,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1522125903,NULL);

/*Table structure for table `sys_log_201804` */

DROP TABLE IF EXISTS `sys_log_201804`;

CREATE TABLE `sys_log_201804` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `ip` varchar(255) DEFAULT NULL COMMENT 'IP地址',
  `module` varchar(255) DEFAULT NULL COMMENT '请求模块',
  `action` varchar(255) DEFAULT NULL COMMENT '请求方法',
  `description` varchar(255) DEFAULT NULL COMMENT '日志描述',
  `type` varchar(10) DEFAULT NULL COMMENT '日志类型(默认:system)',
  `methodMeta` text COMMENT '方法',
  `parameters` text COMMENT '参数',
  `methodReturn` text COMMENT '返回值',
  `actionTime` varchar(20) DEFAULT NULL COMMENT '请求时间',
  `operationTime` int(32) DEFAULT NULL COMMENT '执行时间',
  `success` tinyint(1) DEFAULT NULL COMMENT '操作状态(0:失败 1:成功)',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `SYS_LOG_201804_INDEX` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `sys_log_201804` */

insert  into `sys_log_201804`(`id`,`username`,`ip`,`module`,`action`,`description`,`type`,`methodMeta`,`parameters`,`methodReturn`,`actionTime`,`operationTime`,`success`,`opBy`,`opAt`,`delFlag`) values (1,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1523433137,NULL),(2,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController','editDo','Sys_menu','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysMenuController.editDo(com.aebiz.app.sys.modules.models.Sys_menu,javax.servlet.http.HttpServletRequest)','[{\"id\":\"bf5667954ffc42ccbad0944e8e562928\",\"parentId\":\"bbc135b5efef4e8484576126e5e4f9c4\",\"name\":\"参数管理\",\"aliasName\":\"linkman\",\"href\":\"/platform/tourist/linkman\",\"target\":\"\",\"icon\":\"\",\"isShow\":true,\"disabled\":false,\"permission\":\"eysh.tour.linkman\",\"hasChildren\":false},{\"id\":[\"bf5667954ffc42ccbad0944e8e562928\"],\"isShow\":[\"true\"],\"hasChildren\":[\"false\"],\"oldPermission\":[\"eysh.tour.linkman\"],\"parentId\":[\"bbc135b5efef4e8484576126e5e4f9c4\"],\"name\":[\"参数管理\"],\"aliasName\":[\"linkman\"],\"permission\":[\"eysh.tour.linkman\"],\"href\":[\"/platform/tourist/linkman\"],\"target\":[\"\"],\"icon\":[\"\"]}]',NULL,'2018-04-11 15:54:27',10,1,'1a38a92345bd44ed98648b9162b2d67a',1523433267,0),(3,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.tourist.TourUserController','delete','Tour_user','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.tourist.TourUserController.delete(java.lang.String,java.lang.String[],javax.servlet.http.HttpServletRequest)','[null,[\"91835205d4a24c8a824bd48be2f6e1c9,f9a2a29c9ec343119a80d2fc2330f645\"],{\"ids\":[\"91835205d4a24c8a824bd48be2f6e1c9,f9a2a29c9ec343119a80d2fc2330f645\"]}]',NULL,'2018-04-11 16:04:23',2,1,'1a38a92345bd44ed98648b9162b2d67a',1523433863,0),(4,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1523539601,NULL),(5,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.tourist.TourJourneyController','delete','Tour_journey','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.tourist.TourJourneyController.delete(java.lang.String,java.lang.String[],javax.servlet.http.HttpServletRequest)','[null,[\"e842ecd3e2404b6d9de6b20626737919\"],{\"ids\":[\"e842ecd3e2404b6d9de6b20626737919\"]}]',NULL,'2018-04-12 22:38:19',6,1,'1a38a92345bd44ed98648b9162b2d67a',1523543899,0),(6,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1523628066,NULL),(7,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysTaskController','addDo','新增定时任务','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysTaskController.addDo(com.aebiz.app.sys.modules.models.Sys_task,javax.servlet.http.HttpServletRequest)','[{\"id\":\"\",\"name\":\"每日定时自动审查文章\",\"type\":\"CUSTOM\",\"jobClass\":\"com.aebiz.app.web.commons.quartz.job.TourJourneyJob\",\"note\":\"每天凌晨处理业务\",\"cron\":\"0 0 23 * * ？\",\"data\":\"\"},{\"id\":[\"\"],\"name\":[\"每日定时自动审查文章\"],\"jobClass\":[\"com.aebiz.app.web.commons.quartz.job.TourJourneyJob\"],\"data\":[\"\"],\"note\":[\"每天凌晨处理业务\"],\"cron\":[\"0 0 23 * * ？\"]}]',NULL,'2018-04-13 22:03:08',16,1,'1a38a92345bd44ed98648b9162b2d67a',1523628188,0),(8,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysTaskController','addDo','新增定时任务','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysTaskController.addDo(com.aebiz.app.sys.modules.models.Sys_task,javax.servlet.http.HttpServletRequest)','[{\"id\":\"\",\"name\":\"每日定时自动审查文章\",\"type\":\"CUSTOM\",\"jobClass\":\"com.aebiz.app.web.commons.quartz.job.TourJourneyJob\",\"note\":\"每天凌晨处理业务\",\"cron\":\"0 0 23 * * ？\",\"data\":\"1\"},{\"id\":[\"\"],\"name\":[\"每日定时自动审查文章\"],\"jobClass\":[\"com.aebiz.app.web.commons.quartz.job.TourJourneyJob\"],\"data\":[\"1\"],\"note\":[\"每天凌晨处理业务\"],\"cron\":[\"0 0 23 * * ？\"]}]',NULL,'2018-04-13 22:03:23',6,1,'1a38a92345bd44ed98648b9162b2d67a',1523628203,0),(9,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysTaskController','addDo','新增定时任务','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysTaskController.addDo(com.aebiz.app.sys.modules.models.Sys_task,javax.servlet.http.HttpServletRequest)','[{\"id\":\"\",\"name\":\"每日定时自动审查文章\",\"type\":\"CUSTOM\",\"jobClass\":\"com.aebiz.app.web.commons.quartz.job.TourJourneyJob\",\"note\":\"每天凌晨处理业务\",\"cron\":\"0 0 23 * * ?\",\"data\":\"\"},{\"id\":[\"\"],\"name\":[\"每日定时自动审查文章\"],\"jobClass\":[\"com.aebiz.app.web.commons.quartz.job.TourJourneyJob\"],\"data\":[\"\"],\"note\":[\"每天凌晨处理业务\"],\"cron\":[\"0 0 23 * * ?\"]}]',NULL,'2018-04-13 22:03:37',29,1,'1a38a92345bd44ed98648b9162b2d67a',1523628217,0),(10,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysTaskController','delete','删除任务','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysTaskController.delete(java.lang.String,java.lang.String[])','[\"ebffe50e2d444e428f3228cd5912e4fb\",null]',NULL,'2018-04-13 22:03:58',38,1,'1a38a92345bd44ed98648b9162b2d67a',1523628238,0),(11,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysTaskController','delete','删除任务','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysTaskController.delete(java.lang.String,java.lang.String[])','[\"a569075cfada41aaae6cb787ccccc5e6\",null]',NULL,'2018-04-13 22:04:04',8,1,'1a38a92345bd44ed98648b9162b2d67a',1523628244,0),(12,'周家俊','127.0.0.1','com.aebiz.app.web.modules.controllers.platform.sys.SysTaskController','editDo','编辑定时任务','SYSTEM','public java.lang.Object com.aebiz.app.web.modules.controllers.platform.sys.SysTaskController.editDo(com.aebiz.app.sys.modules.models.Sys_task,javax.servlet.http.HttpServletRequest)','[{\"id\":\"c819429a822a4047bae0ec37fffdc2a2\",\"name\":\"每日定时自动审查文章并筛选每日最佳精彩短篇到首页\",\"type\":\"CUSTOM\",\"jobClass\":\"com.aebiz.app.web.commons.quartz.job.TourJourneyJob\",\"note\":\"每天凌晨处理业务\",\"cron\":\"0 0 23 * * ?\",\"data\":\"\"},{\"id\":[\"c819429a822a4047bae0ec37fffdc2a2\"],\"name\":[\"每日定时自动审查文章并筛选每日最佳精彩短篇到首页\"],\"jobClass\":[\"com.aebiz.app.web.commons.quartz.job.TourJourneyJob\"],\"data\":[\"\"],\"note\":[\"每天凌晨处理业务\"],\"cron\":[\"0 0 23 * * ?\"]}]',NULL,'2018-04-13 22:04:34',104,1,'1a38a92345bd44ed98648b9162b2d67a',1523628274,0),(13,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1524016987,NULL),(14,'周家俊','0:0:0:0:0:0:0:1','com.aebiz.app.web.modules.controllers.platform.sys.SysLoginController','doLogin','登录系统','LOGIN',NULL,NULL,NULL,NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1524017298,NULL),(15,'','127.0.0.1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"周家俊\"],\"phone\":[\"18949464266\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"123456\"]}]',NULL,'2018-04-18 11:40:17',5,1,'',1524022817,0),(16,'','127.0.0.1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"周家俊\"],\"phone\":[\"18949464266\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"123456\"]}]',NULL,'2018-04-18 11:42:14',2,1,'',1524022934,0),(17,'','127.0.0.1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"周家俊\"],\"phone\":[\"18949464266\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"123456\"]}]',NULL,'2018-04-18 11:42:58',2,1,'',1524022978,0),(18,'','127.0.0.1','com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController','registerDo','','SYSTEM','public com.aebiz.baseframework.base.Result com.aebiz.app.web.modules.controllers.front.front.FrontRegisterController.registerDo(javax.servlet.http.HttpServletRequest)','[{\"loginname\":[\"周家俊\"],\"phone\":[\"18949464266\"],\"password\":[\"xyy277\"],\"name\":[\"周家俊\"],\"qq\":[\"123456\"]}]',NULL,'2018-04-18 11:44:22',2,1,'',1524023062,0);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `path` varchar(100) DEFAULT NULL COMMENT '树路径',
  `name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `aliasName` varchar(100) DEFAULT NULL COMMENT '菜单别名',
  `system` varchar(10) DEFAULT NULL COMMENT '所属系统',
  `type` varchar(10) DEFAULT NULL COMMENT '资源类型',
  `href` varchar(255) DEFAULT NULL COMMENT '菜单链接',
  `target` varchar(50) DEFAULT NULL COMMENT '打开方式',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `isShow` tinyint(1) DEFAULT NULL COMMENT '是否显示',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `note` varchar(255) DEFAULT NULL COMMENT '菜单介绍',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `hasChildren` tinyint(1) DEFAULT NULL COMMENT '有子节点',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SYS_MENU_PATH` (`path`),
  UNIQUE KEY `INDEX_SYS_MENU_PREM` (`permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`parentId`,`path`,`name`,`aliasName`,`system`,`type`,`href`,`target`,`icon`,`isShow`,`disabled`,`permission`,`note`,`location`,`hasChildren`,`opBy`,`opAt`,`delFlag`) values ('0120dfd1852e4bb1a905216b7db579a5','9723f56569324c22bc01d431ca89354c','0001000100030005','分配用户','SetUser','platform','data',NULL,NULL,NULL,0,0,'sys.manager.role.user',NULL,0,0,'',1517452970,0),('01574e284bd7493cb2dd65240603ef0a','5950f4bd46054157b910a3d45ffadf09','0004000100010003','删除',NULL,'platform','data',NULL,NULL,NULL,0,0,'eysh.tour.user.delete',NULL,70,0,'1a38a92345bd44ed98648b9162b2d67a',1521434453,0),('017d13cdc23d48118ac82940ac3ef105','50a4b244620a4ec5acedbb580543a79e','0003000400010002','删除编号',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.tpl.id.delete',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517384039,0),('04be9536b3fc4797b09877d2ac864f6d','5950f4bd46054157b910a3d45ffadf09','0004000100010002','修改',NULL,'platform','data',NULL,NULL,NULL,0,0,'eysh.tour.user.edit',NULL,69,0,'1a38a92345bd44ed98648b9162b2d67a',1521434453,0),('060e4aa715d844168c0d1639962a1c8e','8cd1a796fca24dd691c0add6a00a941b','0002000300010002','修改',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.link.class.edit',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759644,0),('0631806d9edf4a0bbc675f260862bab4','1991770395df48569263225cdd741f1c','0003000200020001','群发消息',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.msg.mass.pushNews',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517382435,0),('093a17c9d2944793a4e5e364299353fa','1c7b1c927b464af58f3cc0b2ad8b69b0','0002000300020001','添加',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.link.link.add',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759695,0),('0a6e029ddaa94c7fae7b0cab1f64f8da','5950f4bd46054157b910a3d45ffadf09','0004000100010001','添加',NULL,'platform','data',NULL,NULL,NULL,0,0,'eysh.tour.user.add',NULL,68,0,'1a38a92345bd44ed98648b9162b2d67a',1521434453,0),('0c33da3e9212406ab8ffe4d8f1674baf','8102dca1971f41e583bc2af4229ea55a','000300040002','模板列表','List','platform','menu','/platform/wx/tpl/list','data-pjax','',1,0,'wx.tpl.list',NULL,17,0,'bbb623218bc641659073a034b9cfb1c7',1517384316,0),('0ca6569701b840af934b5d6d38ae4097','9ecbd017bbf549ff89ac08849d348010','000300030002','图文内容','News','platform','menu','/platform/wx/reply/news','data-pjax','',1,0,'wx.reply.news',NULL,9,0,'bbb623218bc641659073a034b9cfb1c7',1517382854,0),('0d863c2ea36744539f1caa50d6ea698f','5f1afd602a154b1bb43c840ab2f8ee27','000200060001','首页静态化','staticindex','platform','menu','/platform/cms/staticpage/index','','',1,0,'cms.staticpage.index',NULL,39,0,'bbb623218bc641659073a034b9cfb1c7',1517191572,0),('0ddc69960e4e4912adad5cf245e288d9','','0004','行者之家','eysh','platform','menu','','','',1,0,'eysh',NULL,0,1,'1a38a92345bd44ed98648b9162b2d67a',1521433789,0),('0f0285227e654659998be03d197cc48c','942d0029927640008a79322d26d09c4d','0002000500020001','发布',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.content.dohtmls',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517190957,0),('11547c7f93b44dcd92d31f6fd2fcec9c','cb28f0158cd240aebf11598eb490c951','0001000200010003','修改',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.sms.mould.info.edit',NULL,0,0,'',1517452970,0),('140faae251f54b84979fabf62187d829','d71bf5725af6410a8970b34808169dfb','00030002','消息管理','Message','platform','menu','','','ti-pencil-alt',1,0,'wx.msg',NULL,4,1,'bbb623218bc641659073a034b9cfb1c7',1517381968,0),('192cc19988df4872b38882cae9967c6a','5b7414e49e4d4e1d80463bf8714bfa41','0004000100020003','删除',NULL,'platform','data',NULL,NULL,NULL,0,0,'eysh.tour.journey.delete',NULL,74,0,'1a38a92345bd44ed98648b9162b2d67a',1521794322,0),('1991770395df48569263225cdd741f1c','140faae251f54b84979fabf62187d829','000300020002','群发消息','Mass','platform','menu','/platform/wx/msg/mass','data-pjax','',1,0,'wx.msg.mass',NULL,6,0,'bbb623218bc641659073a034b9cfb1c7',1517382464,0),('1ab1f849dbd3484a85ec5609b9d8e61a','dbff508e055442a3bf87dafeee794f76','0002000400010002','修改',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.workflow.edit',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759742,0),('1b584cd4075c4d9eac6972c233437479','2cfdc33046d140a18b318f7870a0f73d','000100010008','自定义路由','Route','platform','menu','/platform/sys/route','data-pjax',NULL,1,0,'sys.manager.route',NULL,52,0,'',1517452970,0),('1c2d18b718d248168049f07cdf363cf2','ea89da74b98947a3bc6f5d9e2c27a316','0002000600040001','删除页面',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.staticpage.topic.del',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517191765,0),('1c7b1c927b464af58f3cc0b2ad8b69b0','6f67fff3e7944a8599ca847b4521f5c1','000200030002','链接管理','Link','platform','menu','/platform/cms/link/link','data-pjax','',1,0,'cms.link.link',NULL,30,0,'bbb623218bc641659073a034b9cfb1c7',1516759695,0),('1c8e4461a938442ba371b46cd5b577f9','2cfdc33046d140a18b318f7870a0f73d','000100010010','数据字典','Dict','platform','menu','/platform/sys/dict','data-pjax',NULL,1,0,'sys.manager.dict',NULL,54,0,'',1517452970,0),('1e5687528e264274ace41a50ed0e64e2','6f27d3cecf78457e98387023faf12a72','0002000200040003','删除文件',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.res.delname',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759531,0),('1f378d2531c04947ab1b43d0778e1eae','3db17fd76f364a37be24bd72efe9dcde','0003000500010003','删除帐号',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.conf.account.delete',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517383568,0),('25e9c554b2b94c0c906d36e1337512a1','921a064041fb44db86cc18ca04c6166b','0002000600020001','生成静态HTML',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.staticpage.channel.ge',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517191639,0),('27670b2b44644af4bbc7b2580c6dd303','f586812f2e3d4704a718cf7c9e76eaf4','0001000100130001','导出',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.sys.subscribe.exp',NULL,0,0,'',1517452970,0),('2896e9306e1347cfad778d471cb06a50','d71bf5725af6410a8970b34808169dfb','00030001','微信会员','Member','platform','menu','','','fa fa-user',1,0,'wx.user',NULL,2,1,'bbb623218bc641659073a034b9cfb1c7',1517381944,0),('28d45b5eb9dc4b50936470a21843891d','2cfdc33046d140a18b318f7870a0f73d','000100010001','单位管理','Unit','platform','menu','/platform/sys/unit','data-pjax',NULL,1,0,'sys.manager.unit',NULL,45,0,'',1517452970,0),('2b40aede791a4802b2decf0339252fe4','ea89da74b98947a3bc6f5d9e2c27a316','0002000600040002','生成页面',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.staticpage.topic.ge',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517191765,0),('2cfdc33046d140a18b318f7870a0f73d','81267f0d8d84492eaa07adedf14686d6','00010001','系统管理','Manager','platform','menu','','','ti-settings',1,0,'sys.manager','系统管理',44,1,'',1517452970,0),('2f848a43ec394c4b96cc7241d2b06381','','0002','CMS','cms','platform','menu','','','',1,0,'cms',NULL,19,1,'bbb623218bc641659073a034b9cfb1c7',1516758233,0),('2f906103f48340419a8bab96899b7d82','c48afe87e7db4cf4a9926fa52a99ba3b','000100020002','发送记录','fsjl','platform','menu','/platform/sms/send/log','data-pjax',NULL,1,0,'platform.sms.send.log',NULL,59,0,'',1517452970,0),('32a6986d1b1f45a48c7789ec2112169c','d3dc1eb066de481587131ab048b3f6e2','0002000200010003','添加子栏目',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.channel.add',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516758870,0),('32cd2fe478644290a112bafe6a92965c','942d0029927640008a79322d26d09c4d','0002000500020002','删除',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.content.delete',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517190957,0),('3476ac041c284881878d9b8df4dab8b4','8b1d29398c8e480e893a50e2741e2e72','0003000300030002','修改绑定',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.reply.follow.edit',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517382951,0),('35bf6b0aadcd42f0861e00dcaeb8b057','657d53d3665049d781d340db0a3e4bc7','0001000100070001','添加任务','Add','platform','data',NULL,NULL,NULL,0,0,'sys.manager.task.add',NULL,0,0,'',1517452970,0),('36d8249922824876bdabc07c892a9cfc','7ab1f391b9584a5fadfd06b6baeae4c7','0003000100010001','同步会员信息',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.user.list.sync',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517382290,0),('3b49cc0fcdc84b9ca55226aa43560ec5','28d45b5eb9dc4b50936470a21843891d','0001000100010001','添加单位','Add','platform','data',NULL,NULL,NULL,0,0,'sys.manager.unit.add',NULL,0,0,'',1517452970,0),('3c605075d1e34cf5a095fe87a1deb4bf','cb28f0158cd240aebf11598eb490c951','0001000200010001','新增',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.sms.mould.info.add',NULL,0,0,'',1517452970,0),('3db17fd76f364a37be24bd72efe9dcde','d625d80589144d51a13c5ee0c091d0dc','000300050001','帐号配置','Account','platform','menu','/platform/wx/conf/account','data-pjax','',1,0,'wx.conf.account',NULL,13,0,'bbb623218bc641659073a034b9cfb1c7',1517383572,0),('3e6d3fb5bc234feebba82a15bdfa2ef9','ae17ba0fcce64b46a780ea329e052f3b','0002000100010002','编辑站点',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.site.settings.edit',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516758515,0),('402459c22b7d46eab2229f47c479310f','8b1d29398c8e480e893a50e2741e2e72','0003000300030001','添加绑定',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.reply.follow.add',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517382940,0),('40ccdaad894d4ed48a1c0082f8a19d00','5e84be56a3274b24b625624e89408627','0004000100030001','添加',NULL,'platform','data',NULL,NULL,NULL,0,0,'eysh.tour.verse.add',NULL,76,0,'1a38a92345bd44ed98648b9162b2d67a',1521896429,0),('4345db75bbd74c8bb8dfedbfd42119f7','28d45b5eb9dc4b50936470a21843891d','0001000100010003','删除单位','Delete','platform','data',NULL,NULL,NULL,0,0,'sys.manager.unit.delete',NULL,0,0,'',1517452970,0),('4381af82a2a2446495fba3eb146d7f61','2f848a43ec394c4b96cc7241d2b06381','00020002','网站管理','site','platform','menu','','','ti-pencil-alt',1,0,'site',NULL,23,1,'bbb623218bc641659073a034b9cfb1c7',1516758309,0),('43c4bc1f77564b3680be836b18b9acc4','0d863c2ea36744539f1caa50d6ea698f','0002000600010001','删除首页',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.staticpage.index.del',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517191572,0),('43efc7afc83249c3b4875fc4860f3aca','6f27d3cecf78457e98387023faf12a72','0002000200040001','修改文件',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.res.savefile',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759479,0),('45533f4a901941be966cd8ee56db8623','2cfdc33046d140a18b318f7870a0f73d','000100010004','菜单管理','Menu','platform','menu','/platform/sys/menu','data-pjax',NULL,1,0,'sys.manager.menu',NULL,48,0,'',1517452970,0),('47746c7b0b4c4157838a2f42796187d2','d7de61d2d6f049ad87e1491565a3e39d','0001000100060001','清除日志','Delete','platform','data',NULL,NULL,NULL,0,0,'sys.manager.log.delete',NULL,0,0,'',1517452970,0),('478c5451273443c38c8bc7a4f3b05856','657d53d3665049d781d340db0a3e4bc7','0001000100070002','修改任务','Edit','platform','data',NULL,NULL,NULL,0,0,'sys.manager.task.edit',NULL,0,0,'',1517452970,0),('482c744503d548ad826e784e00c04098','f587bcfe0962434b8e1a7f721531ba65','0002000200030003','上传文件',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.tpl.uploadOneSave',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759284,0),('485c764b94c447b19ee4ac25e624cc5c','942d0029927640008a79322d26d09c4d','0002000500020005','提交',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.content.dosubmit',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517191155,0),('48a231b9d0454de0ad41b0538dd71269','f586812f2e3d4704a718cf7c9e76eaf4','0001000100130003','删除',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.sys.subscribe.delete',NULL,0,0,'',1517452970,0),('48ed3df76ded491ebadd737527160744','d3dc1eb066de481587131ab048b3f6e2','0002000200010001','修改栏目',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.channel.edit',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516758774,0),('497f384e6bf647c1b25d3db934b07f93','9723f56569324c22bc01d431ca89354c','0001000100030006','分配CMS权限','SetCmsMenu','platform','data',NULL,NULL,NULL,0,0,'sys.manager.role.cmsmenu',NULL,0,0,'',1517452970,0),('4aa27cbb9f0b442dbc7634abc71875aa','c21897fc27254d4c92d9455c216abefb','0003000300040003','添加绑定',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.reply.keyword.add',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517383071,0),('4b133273aba543878e0f132f69e47457','ae17ba0fcce64b46a780ea329e052f3b','0002000100010001','删除站点',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.site.settings.delete',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516758515,0),('4b4109f0952e46be8ac5a78c6bde4ddb','2cfdc33046d140a18b318f7870a0f73d','000100012','接口权限设置','ApiApp','platform','menu','/platform/sys/api/unit','data-pjax',NULL,1,0,'platform.sys.api.unit',NULL,0,0,'',1517452970,0),('4c40c6049e914dfc93f897f3231ccc73','99abc5f7fbb34f24bc9c03e5fbbe3054','0002000200020003','删除',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.model.delete',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759024,0),('4cc19c89acf349349b8065f06d703113','942d0029927640008a79322d26d09c4d','0002000500020004','修改',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.content.edit',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517191115,0),('4cc2c6a4d9104bae876e7632f025be3d','febf1ca1528c49daab59f8cbf698488b','0003000500020002','修改菜单',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.conf.menu.edit',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517383923,0),('4ccb479b45834793a4eccf4739f0617a','981b0671f38c4835a211ef648652b659','0003000200010001','回复消息',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.msg.user.reply',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517382347,0),('4dee58d89f724023a8816764b3da7dcb','8818e755cec24a6ea737d46d4025b2da','0001000100110002','修改地区','Edit','platform','data',NULL,NULL,NULL,0,0,'sys.config.delivery.area.edit',NULL,0,0,'',1517452970,0),('4f2d05978bb34e0bb284bed84b165b84','9723f56569324c22bc01d431ca89354c','0001000100030001','添加角色','Add','platform','data',NULL,NULL,NULL,0,0,'sys.manager.role.add',NULL,0,0,'',1517452970,0),('4f6b3451e57d4f26a9ca4c0904370ef2','942d0029927640008a79322d26d09c4d','0002000500020006','审核',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.content.savecheck',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517191185,0),('4fa293ab334e44e3b630e2bf20c1977c','9723f56569324c22bc01d431ca89354c','0001000100030004','分配菜单','SetMenu','platform','data',NULL,NULL,NULL,0,0,'sys.manager.role.menu',NULL,0,0,'',1517452970,0),('50a4b244620a4ec5acedbb580543a79e','8102dca1971f41e583bc2af4229ea55a','000300040001','模板编号','Id','platform','menu','/platform/wx/tpl/id','data-pjax','',1,0,'wx.tpl.id',NULL,16,0,'bbb623218bc641659073a034b9cfb1c7',1517384271,0),('51097a633ea246aca36a0bed9a9ea59a','bf5667954ffc42ccbad0944e8e562928','0004000100040003','删除',NULL,'platform','data',NULL,NULL,NULL,0,0,'eysh.tour.linkman.delete',NULL,82,0,'1a38a92345bd44ed98648b9162b2d67a',1521946362,0),('51ba669f420b425e9d00538be47ab7a2','dbff508e055442a3bf87dafeee794f76','0002000400010001','添加',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.workflow.add',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759742,0),('51c36f99a1a34e7286b5ca8aeff136ad','f586812f2e3d4704a718cf7c9e76eaf4','0001000100130002','修改',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.sys.subscribe.edit',NULL,0,0,'',1517452970,0),('5490ca2f28ad421b80145eaf96abdc7c','2f848a43ec394c4b96cc7241d2b06381','00020001','站点管理','Site','platform','menu','','','ti-world',1,0,'cms.site',NULL,20,1,'bbb623218bc641659073a034b9cfb1c7',1516758263,0),('55dbc91b769f4af9af659e34ce40e26e','45533f4a901941be966cd8ee56db8623','0001000100040002','修改菜单','Edit','platform','data',NULL,NULL,NULL,0,0,'sys.manager.menu.edit',NULL,0,0,'',1517452970,0),('5642e30d6f1042f7ba8d4ac928514c47','942d0029927640008a79322d26d09c4d','0002000500020008','移动',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.content.moveSave',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517191222,0),('56585a9a5d3f4f5faf6fc9b399090e2a','2cfdc33046d140a18b318f7870a0f73d','000100010002','用户管理','User','platform','menu','/platform/sys/user','data-pjax',NULL,1,0,'sys.manager.user',NULL,46,0,'',1517452970,0),('5829d16c03db45baac75c5e987be1506','dbff508e055442a3bf87dafeee794f76','0002000400010003','删除',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.workflow.delete',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759742,0),('582ca649ea1e4b2eaa008f5f96978195','cb28f0158cd240aebf11598eb490c951','0001000200010002','删除',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.sms.mould.info.delete',NULL,0,0,'',1517452970,0),('586f37b1481f4794a03e0c3575dc1582','eca69ff7058940f386a3903a67e67bf7','000100030002','虚拟号码使用日志','fsjl','platform','menu','/platform/sms/tele/log','data-pjax',NULL,1,0,'platform.sms.tele.log',NULL,62,0,'',1517452970,0),('5950f4bd46054157b910a3d45ffadf09','bbc135b5efef4e8484576126e5e4f9c4','000400010001','会员管理','user','platform','menu','/platform/tourist/user','','',1,0,'eysh.tour.user',NULL,67,0,'1a38a92345bd44ed98648b9162b2d67a',1521434453,0),('59e62054e822406c88e7db4af7ed8079','6f27d3cecf78457e98387023faf12a72','0002000200040006','上传文件',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.res.uploadOneSave',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759574,0),('5b7414e49e4d4e1d80463bf8714bfa41','bbc135b5efef4e8484576126e5e4f9c4','000400010002','游记管理','journey','platform','menu','/platform/tourist/journey','','',1,0,'eysh.tour.journey',NULL,71,0,'1a38a92345bd44ed98648b9162b2d67a',1521794322,0),('5bd04c4a329746439a5ea1bc829c0013','28d45b5eb9dc4b50936470a21843891d','0001000100010002','修改单位','Edit','platform','data',NULL,NULL,NULL,0,0,'sys.manager.unit.edit',NULL,0,0,'',1517452970,0),('5e4d15dde3c9481d8d581a739275b40d','c21897fc27254d4c92d9455c216abefb','0003000300040001','删除绑定',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.reply.keyword.delete',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517383038,0),('5e84be56a3274b24b625624e89408627','bbc135b5efef4e8484576126e5e4f9c4','000400010003','精彩短篇','verse','platform','menu','/platform/tourist/verse','','',1,0,'eysh.tour.verse',NULL,75,0,'1a38a92345bd44ed98648b9162b2d67a',1521971567,0),('5f1afd602a154b1bb43c840ab2f8ee27','2f848a43ec394c4b96cc7241d2b06381','00020006','网页生成','staticpage','platform','menu','','','',1,0,'cms.staticpage',NULL,38,1,'bbb623218bc641659073a034b9cfb1c7',1516758412,0),('657d53d3665049d781d340db0a3e4bc7','2cfdc33046d140a18b318f7870a0f73d','000100010007','定时任务','Task','platform','menu','/platform/sys/task','data-pjax',NULL,1,0,'sys.manager.task',NULL,51,0,'',1517452970,0),('6b529321690f4527bfbe80d638f9c1f3','9ecbd017bbf549ff89ac08849d348010','000300030001','文本内容','Txt','platform','menu','/platform/wx/reply/txt','data-pjax','',1,0,'wx.reply.txt',NULL,8,0,'bbb623218bc641659073a034b9cfb1c7',1517382610,0),('6d36a15e774b4173a0dd41ed86006d8b','1c8e4461a938442ba371b46cd5b577f9','0001000100100003','删除字典','Delete','platform','data',NULL,NULL,NULL,0,0,'sys.manager.dict.delete',NULL,0,0,'',1517452970,0),('6d5f86d6d04f4d8298334993d68a2337','d3dc1eb066de481587131ab048b3f6e2','0002000200010005','删除栏目',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.channel.delete',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516758904,0),('6da598ce6bba48069b39b0db06a0837c','5e84be56a3274b24b625624e89408627','0004000100030002','修改',NULL,'platform','data',NULL,NULL,NULL,0,0,'eysh.tour.verse.edit',NULL,77,0,'1a38a92345bd44ed98648b9162b2d67a',1521896429,0),('6f27d3cecf78457e98387023faf12a72','4381af82a2a2446495fba3eb146d7f61','000200020004','资源管理','res','platform','menu','/platform/cms/res','data_pjax','',1,0,'cms.res',NULL,27,0,'bbb623218bc641659073a034b9cfb1c7',1516759575,0),('6f67fff3e7944a8599ca847b4521f5c1','2f848a43ec394c4b96cc7241d2b06381','00020003','友情链接','AD','platform','menu','','','ti-link',1,0,'cms.link',NULL,28,1,'bbb623218bc641659073a034b9cfb1c7',1516758340,0),('707a5a0e26244a44bdce0148b618de88','df2a1743988640ab8a78377e18b785c2','0002000500030002','删除',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.content.garbage.delete',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517191314,0),('713b83e55ecb4b67a65492bba530f170','bf5667954ffc42ccbad0944e8e562928','0004000100040002','修改',NULL,'platform','data',NULL,NULL,NULL,0,0,'eysh.tour.linkman.edit',NULL,81,0,'1a38a92345bd44ed98648b9162b2d67a',1521946362,0),('7359b29c6fe24171863b02f49a266eae','2f848a43ec394c4b96cc7241d2b06381','00020005','内容管理','content','platform','menu','','data-pjax','',1,0,'content',NULL,33,1,'bbb623218bc641659073a034b9cfb1c7',1516758392,0),('75b5a8bf7c324760be57e4b3ffc0b015','5b7414e49e4d4e1d80463bf8714bfa41','0004000100020001','添加',NULL,'platform','data',NULL,NULL,NULL,0,0,'eysh.tour.journey.add',NULL,72,0,'1a38a92345bd44ed98648b9162b2d67a',1521794322,0),('76201449f9d348e08107869ca4ffb869','1991770395df48569263225cdd741f1c','0003000200020003','添加图文',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.msg.mass.addNews',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517382463,0),('7a94ccd1913a4e459f51406432ea58ea','3db17fd76f364a37be24bd72efe9dcde','0003000500010002','修改帐号',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.conf.account.edit',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517383557,0),('7ab1f391b9584a5fadfd06b6baeae4c7','2896e9306e1347cfad778d471cb06a50','000300010001','会员列表','List','platform','menu','/platform/wx/user/index','data-pjax','',1,0,'wx.user.list',NULL,3,0,'bbb623218bc641659073a034b9cfb1c7',1517382290,0),('7c58f800dd9c429fbcc22713465db866','56585a9a5d3f4f5faf6fc9b399090e2a','0001000100020001','添加用户','Add','platform','data',NULL,NULL,NULL,0,0,'sys.manager.user.add',NULL,0,0,'',1517452970,0),('7cf34691816746e390e0191bf3a54c03','50a4b244620a4ec5acedbb580543a79e','0003000400010001','添加编号',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.tpl.id.add',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517384028,0),('7d938aa5abd84415ad2d648a29e11b8b','f587bcfe0962434b8e1a7f721531ba65','0002000200030004','删除文件',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.tpl.delname',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759310,0),('7e0bb38e446e4f58a2925c359e2ce3f4','df2a1743988640ab8a78377e18b785c2','0002000500030001','转为草稿',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.content.garbage.draft',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517191314,0),('8102dca1971f41e583bc2af4229ea55a','d71bf5725af6410a8970b34808169dfb','00030004','模板消息','Template','platform','menu','','','ti-notepad',1,0,'wx.tpl',NULL,15,1,'bbb623218bc641659073a034b9cfb1c7',1517382016,0),('81267f0d8d84492eaa07adedf14686d6','','0001','系统','System','platform','menu','','','',1,0,'sys','系统',43,1,'',1517452970,0),('8144c56f687a45ffa43fe6aa33af7026','d58a4bf922ec42729d822770107668c1','0001000100050003','删除参数','Delete','platform','data',NULL,NULL,NULL,0,0,'sys.manager.conf.delete',NULL,0,0,'',1517452970,0),('8818e755cec24a6ea737d46d4025b2da','2cfdc33046d140a18b318f7870a0f73d','000100010011','地区设置','area','platform','menu','/platform/sys/config/area','data-pjax',NULL,1,0,'sys.config.delivery.area',NULL,55,0,'',1517452970,0),('88e8ec8b29624f15a5fb5130696568f3','6f27d3cecf78457e98387023faf12a72','0002000200040002','批量删除文件',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.res.delnames',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759479,0),('8a387fb5ff3948b094068e6f75d7387a','f587bcfe0962434b8e1a7f721531ba65','0002000200030002','批量删除文件',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.tpl.delnames',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759154,0),('8b1d29398c8e480e893a50e2741e2e72','9ecbd017bbf549ff89ac08849d348010','000300030003','关注自动回复','Follow','platform','menu','/platform/wx/reply/conf/follow','data-pjax','',1,0,'wx.reply.follow',NULL,10,0,'bbb623218bc641659073a034b9cfb1c7',1517382964,0),('8cd1a796fca24dd691c0add6a00a941b','6f67fff3e7944a8599ca847b4521f5c1','000200030001','链接分类','Class','platform','menu','/platform/cms/link/class','data-pjax','',1,0,'cms.link.class',NULL,29,0,'bbb623218bc641659073a034b9cfb1c7',1516759644,0),('8d7fd658c1e34a30b21aeb4296714cc3','8cd1a796fca24dd691c0add6a00a941b','0002000300010003','删除',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.link.class.delete',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759644,0),('8dcd9f27c9414f44b80b7864907e7057','1b584cd4075c4d9eac6972c233437479','0001000100080003','删除路由','Delete','platform','data',NULL,NULL,NULL,0,0,'sys.manager.route.delete',NULL,0,0,'',1517452970,0),('8dea6d33e18a434f8b44d884cf68331c','5f1afd602a154b1bb43c840ab2f8ee27','000200060003','内容页静态化','staticcontent','platform','menu','/platform/cms/staticpage/content','','',1,0,'cms.staticpage.content',NULL,41,0,'bbb623218bc641659073a034b9cfb1c7',1517191706,0),('8f7f347ae9eb40edbca08e31f7998f9d','a3501a5974f34625aacddb61a10f426e','0002000500010001','添加',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.topic.add',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759802,0),('8fe0340021da48c999d0f8292dbb29b2','6f27d3cecf78457e98387023faf12a72','0002000200040004','重命名',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.res.rename',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759555,0),('9184ef1c590b471fa071b7b7c2d5a934','1b584cd4075c4d9eac6972c233437479','0001000100080002','修改路由','Edit','platform','data',NULL,NULL,NULL,0,0,'sys.manager.route.edit',NULL,0,0,'',1517452970,0),('921a064041fb44db86cc18ca04c6166b','5f1afd602a154b1bb43c840ab2f8ee27','000200060002','栏目页静态化','staticchannel','platform','menu','/platform/cms/staticpage/channel','','',1,0,'cms.staticpage.channel',NULL,40,0,'bbb623218bc641659073a034b9cfb1c7',1517191639,0),('93852750a1f541e4bb775938d78cd7e9','9723f56569324c22bc01d431ca89354c','0001000100030003','删除角色','Delete','platform','data',NULL,NULL,NULL,0,0,'sys.manager.role.delete',NULL,0,0,'',1517452970,0),('942d0029927640008a79322d26d09c4d','7359b29c6fe24171863b02f49a266eae','000200050002','文章管理','content','platform','menu','/platform/cms/content','data-pjax','',1,0,'platform.cms.content',NULL,35,0,'bbb623218bc641659073a034b9cfb1c7',1517191223,0),('9686ae8ae8e74b73913fa0bf5968445d','9723f56569324c22bc01d431ca89354c','0001000100030002','修改角色','Edit','platform','data',NULL,NULL,NULL,0,0,'sys.manager.role.edit',NULL,0,0,'',1517452970,0),('9723f56569324c22bc01d431ca89354c','2cfdc33046d140a18b318f7870a0f73d','000100010003','角色管理','Role','platform','menu','/platform/sys/role','data-pjax',NULL,1,0,'sys.manager.role',NULL,47,0,'',1517452970,0),('981b0671f38c4835a211ef648652b659','140faae251f54b84979fabf62187d829','000300020001','会员消息','Msg','platform','menu','/platform/wx/msg/user','data-pjax','',1,0,'wx.msg.user',NULL,5,0,'bbb623218bc641659073a034b9cfb1c7',1517382347,0),('99127e7a2a8748e8b3bae275e238cf5e','2f848a43ec394c4b96cc7241d2b06381','00020004','工作流设置','cmsworkflow','platform','menu','','','',1,0,'cms.workflow',NULL,31,1,'bbb623218bc641659073a034b9cfb1c7',1516758364,0),('99abc5f7fbb34f24bc9c03e5fbbe3054','4381af82a2a2446495fba3eb146d7f61','000200020002','模型管理','model','platform','menu','/platform/cms/model','data-pjax','',1,0,'platform.cms.model',NULL,25,0,'bbb623218bc641659073a034b9cfb1c7',1516759025,0),('9abf17f0b2064b60987c5e063ce9e562','56585a9a5d3f4f5faf6fc9b399090e2a','0001000100020003','删除用户','Delete','platform','data',NULL,NULL,NULL,0,0,'sys.manager.user.delete',NULL,0,0,'',1517452970,0),('9adbc1e74d7e4b589d8ccf90a70144d6','0c33da3e9212406ab8ffe4d8f1674baf','0003000400020001','获取列表',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.tpl.list.get',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517384316,0),('9eab50fcf2694b7da6d734ea747ccd80','eca69ff7058940f386a3903a67e67bf7','000100030001','虚拟号码配置','xnhmpz','platform','menu','/platform/sms/tele/info','data-pjax',NULL,1,0,'platform.sms.tele.info',NULL,61,0,'',1517452970,0),('9ecbd017bbf549ff89ac08849d348010','d71bf5725af6410a8970b34808169dfb','00030003','自动回复','AutoReply','platform','menu','','','ti-back-left',1,0,'wx.reply',NULL,7,1,'bbb623218bc641659073a034b9cfb1c7',1517381997,0),('a05e37395c97484b9dac1edbad912c32','c2f2cedc581b48429cb01df2ec0f9f49','0002000500040002','修改',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.acquisition.edit',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517191483,0),('a0a0db6e4ae04c4ea142c51970d011a9','c2f2cedc581b48429cb01df2ec0f9f49','0002000500040003','删除',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.acquisition.delete',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517191483,0),('a26bf4b0f0134e8e8095c52dbf5d4580','56585a9a5d3f4f5faf6fc9b399090e2a','0001000100020002','修改用户','Edit','platform','data',NULL,NULL,NULL,0,0,'sys.manager.user.edit',NULL,0,0,'',1517452970,0),('a3501a5974f34625aacddb61a10f426e','7359b29c6fe24171863b02f49a266eae','000200050001','专题管理','topic','platform','menu','/platform/cms/topic','data-pjax','',1,0,'platform.cms.topic',NULL,34,0,'bbb623218bc641659073a034b9cfb1c7',1516759802,0),('a4674262bb734a40bceae6609cbc976f','aab9af88820e4cd48a62f417ce503b67','0001000100090002','修改应用','Edit','platform','data',NULL,NULL,NULL,0,0,'sys.manager.api.edit',NULL,0,0,'',1517452970,0),('a59bfc8ca6844149bd532c7d0d494d9f','99abc5f7fbb34f24bc9c03e5fbbe3054','0002000200020001','排序',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.model.sort',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516758985,0),('a7b6b1d4e3a54b698ec84eec3ed52808','99abc5f7fbb34f24bc9c03e5fbbe3054','0002000200020002','添加',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.model.add',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516758985,0),('a7c3c57335144e43be4e041202419d78','febf1ca1528c49daab59f8cbf698488b','0003000500020004','保存排序',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.conf.menu.sort',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517383952,0),('a7d1c610a3eb4da6b3ff246aa88eb765','8b1d29398c8e480e893a50e2741e2e72','0003000300030003','删除绑定',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.reply.follow.delete',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517382963,0),('a806900609dc4a50baf5da208812cf2b','657d53d3665049d781d340db0a3e4bc7','0001000100070003','删除任务','Delete','platform','data',NULL,NULL,NULL,0,0,'sys.manager.task.delete',NULL,0,0,'',1517452970,0),('a861fcc6874b467a8c0a6213319db1e3','d58a4bf922ec42729d822770107668c1','0001000100050002','修改参数','Edit','platform','data',NULL,NULL,NULL,0,0,'sys.manager.conf.edit',NULL,0,0,'',1517452970,0),('aab9af88820e4cd48a62f417ce503b67','2cfdc33046d140a18b318f7870a0f73d','000100010009','应用管理','App','platform','menu','/platform/sys/api','data-pjax',NULL,1,0,'sys.manager.api',NULL,53,0,'',1517452970,0),('adedb5da86184c269907c7ea24b6f73e','6b529321690f4527bfbe80d638f9c1f3','0003000300010001','删除文本',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.reply.txt.delete',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517382591,0),('ae17ba0fcce64b46a780ea329e052f3b','5490ca2f28ad421b80145eaf96abdc7c','000200010001','网站配置','Settings','platform','menu','/platform/cms/site','data-pjax','',1,0,'cms.site.settings',NULL,21,0,'bbb623218bc641659073a034b9cfb1c7',1516758604,0),('aed9a1f8730e4bcd962127f64fd7c4f2','8dea6d33e18a434f8b44d884cf68331c','0002000600030001','生成静态页面',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.staticpage.content.ge',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517191706,0),('b0193970d4c6486ea6e5e51cdd8fe581','bf5667954ffc42ccbad0944e8e562928','0004000100040001','添加',NULL,'platform','data',NULL,NULL,NULL,0,0,'eysh.tour.linkman.add',NULL,80,0,'1a38a92345bd44ed98648b9162b2d67a',1521946362,0),('b28c05b7d6f64ae087b6d658f4d0a471','aab9af88820e4cd48a62f417ce503b67','0001000100090003','删除应用','Delete','platform','data',NULL,NULL,NULL,0,0,'sys.manager.api.delete',NULL,0,0,'',1517452970,0),('b2a84baec72c46779f3f7743abeede51','1c8e4461a938442ba371b46cd5b577f9','0001000100100001','添加字典','Add','platform','data',NULL,NULL,NULL,0,0,'sys.manager.dict.add',NULL,0,0,'',1517452970,0),('b346f3d4712d48fbaaa970d3f02dd0ec','8818e755cec24a6ea737d46d4025b2da','0001000100110001','添加地区','Add','platform','data',NULL,NULL,NULL,0,0,'sys.config.delivery.area.add',NULL,0,0,'',1517452970,0),('b44cde53b2014f9fb2dd45d98219ed61','c2f2cedc581b48429cb01df2ec0f9f49','0002000500040001','添加',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.acquisition.add',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517191482,0),('b487e8b82b0e4d3f887df02f89d37507','d3dc1eb066de481587131ab048b3f6e2','0002000200010002','栏目排序',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.channel.sort',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516758774,0),('b561ae6ebb1a4e57a1ab6d2dc30d8da2','5b7414e49e4d4e1d80463bf8714bfa41','0004000100020002','修改',NULL,'platform','data',NULL,NULL,NULL,0,0,'eysh.tour.journey.edit',NULL,73,0,'1a38a92345bd44ed98648b9162b2d67a',1521794322,0),('b81188925c684908b7c258235865be3a','942d0029927640008a79322d26d09c4d','0002000500020007','批量审核',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.content.savechecks',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517191193,0),('ba796ecacfbb41f3a4685213e5b18380','1991770395df48569263225cdd741f1c','0003000200020002','删除图文',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.msg.mass.delNews',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517382435,0),('bbc135b5efef4e8484576126e5e4f9c4','0ddc69960e4e4912adad5cf245e288d9','00040001','eysh管理','tourist','platform','menu','','','',1,0,'eysh.tour',NULL,66,1,'1a38a92345bd44ed98648b9162b2d67a',1521794276,0),('bc25d55cb1cc41409e8428fe15a91b61','1c8e4461a938442ba371b46cd5b577f9','0001000100100002','修改字典','Edit','platform','data',NULL,NULL,NULL,0,0,'sys.manager.dict.edit',NULL,0,0,'',1517452970,0),('be0363c47ee047cab3c6b47629c51a79','5490ca2f28ad421b80145eaf96abdc7c','000200010002','访问统计','record','platform','menu','/platform/cms/record/index','','',1,0,'cms.site.record',NULL,22,0,'bbb623218bc641659073a034b9cfb1c7',1516758643,0),('bf5667954ffc42ccbad0944e8e562928','bbc135b5efef4e8484576126e5e4f9c4','000400010004','参数管理','linkman','platform','menu','/platform/tourist/linkman','','',1,0,'eysh.tour.linkman',NULL,79,0,'1a38a92345bd44ed98648b9162b2d67a',1523433267,0),('c0916d2a1513492c9ad2a19f3b042ebe','d58a4bf922ec42729d822770107668c1','0001000100050001','添加参数','Add','platform','data',NULL,NULL,NULL,0,0,'sys.manager.conf.add',NULL,0,0,'',1517452970,0),('c21897fc27254d4c92d9455c216abefb','9ecbd017bbf549ff89ac08849d348010','000300030004','关键词回复','Keyword','platform','menu','/platform/wx/reply/conf/keyword','data-pjax','',1,0,'wx.reply.keyword',NULL,11,0,'bbb623218bc641659073a034b9cfb1c7',1517383072,0),('c2f2cedc581b48429cb01df2ec0f9f49','7359b29c6fe24171863b02f49a266eae','000200050004','数据采集','acquisition','platform','menu','/platform/cms/acquisition','','',1,0,'platform.cms.acquisition',NULL,37,0,'bbb623218bc641659073a034b9cfb1c7',1517191482,0),('c372d1b975c84e77a463f277bfb3180b','0d863c2ea36744539f1caa50d6ea698f','0002000600010002','生成首页',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.staticpage.index.ge',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517191572,0),('c48afe87e7db4cf4a9926fa52a99ba3b','2cfdc33046d140a18b318f7870a0f73d','00010002','短信管理','dxgl','platform','menu',NULL,'data-pjax',NULL,1,0,'sms.manager',NULL,57,1,'',1517452970,0),('c84e77edf76e4b1891943e4bf8f8b516','1c7b1c927b464af58f3cc0b2ad8b69b0','0002000300020002','修改',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.link.link.edit',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759695,0),('c8809a8735864b0ba52591a58f2b1375','f587bcfe0962434b8e1a7f721531ba65','0002000200030001','修改文件',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.tpl.savefile',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759154,0),('c9b2a5b8aefe4148a5b7b66a4aeda820','4b4109f0952e46be8ac5a78c6bde4ddb','0001000120003','删除','ApiAppDelete','platform','data',NULL,'data-pjax',NULL,0,0,'platform.sys.api.unit.delete',NULL,0,0,'',1517452970,0),('cb1c1a8f1a0d43c1b9c24c5321448296','45533f4a901941be966cd8ee56db8623','0001000100040003','删除菜单','Delete','platform','data',NULL,NULL,NULL,0,0,'sys.manager.menu.delete',NULL,0,0,'',1517452970,0),('cb28f0158cd240aebf11598eb490c951','c48afe87e7db4cf4a9926fa52a99ba3b','000100020001','短信模板管理','dxmbgl','platform','menu','/platform/sms/mould/info','data-pjax',NULL,1,0,'platform.sms.mould.info',NULL,58,0,'',1517452970,0),('cf068f43ff5344dd8fcf5e4facdfdd1e','4b4109f0952e46be8ac5a78c6bde4ddb','0001000120002','修改','ApiAppEdit','platform','data',NULL,'data-pjax',NULL,0,0,'platform.sys.api.unit.edit',NULL,0,0,'',1517452970,0),('cfa1038c0a26465e8d90ddd23645ae75','9eab50fcf2694b7da6d734ea747ccd80','0001000300010001','新增',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.sms.tele.info.add',NULL,0,0,'',1517452970,0),('d19f2346a5084b5e95308abc7654608b','febf1ca1528c49daab59f8cbf698488b','0003000500020003','删除菜单',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.conf.menu.delete',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517383941,0),('d2feef5baba2426f97643bd9a9e586fb','f587bcfe0962434b8e1a7f721531ba65','0002000200030006','新建目录',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.tpl.addDir',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759337,0),('d3b283908e2c4507be159ba2c776a9f9','8cd1a796fca24dd691c0add6a00a941b','0002000300010001','添加',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.link.class.add',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759644,0),('d3dc1eb066de481587131ab048b3f6e2','4381af82a2a2446495fba3eb146d7f61','000200020001','栏目管理','Channel','platform','menu','/platform/cms/channel','data-pjax','',1,0,'platform.cms.channel',NULL,24,0,'bbb623218bc641659073a034b9cfb1c7',1516758908,0),('d47013e699954826b13d3903f856c821','0ca6569701b840af934b5d6d38ae4097','0003000300020003','删除图文',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.reply.news.delete',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517382853,0),('d470271109754506a5ffba4ff3f19ba1','942d0029927640008a79322d26d09c4d','0002000500020003','新增',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.content.add',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517191089,0),('d58a4bf922ec42729d822770107668c1','2cfdc33046d140a18b318f7870a0f73d','000100010005','系统参数','Param','platform','menu','/platform/sys/conf','data-pjax',NULL,1,0,'sys.manager.conf',NULL,49,0,'',1517452970,0),('d625d80589144d51a13c5ee0c091d0dc','d71bf5725af6410a8970b34808169dfb','00030005','微信配置','Config','platform','menu','','','fa fa-weixin',1,0,'wx.conf',NULL,12,1,'bbb623218bc641659073a034b9cfb1c7',1517382119,0),('d71bf5725af6410a8970b34808169dfb','','0003','微信','wx','platform','menu','','','',1,0,'wx',NULL,1,1,'bbb623218bc641659073a034b9cfb1c7',1517381912,0),('d7de61d2d6f049ad87e1491565a3e39d','2cfdc33046d140a18b318f7870a0f73d','000100010006','日志管理','Log','platform','menu','/platform/sys/log','data-pjax',NULL,1,0,'sys.manager.log',NULL,50,0,'',1517452970,0),('d81c017d173648e2aa8ccd8ac79230d2','ae17ba0fcce64b46a780ea329e052f3b','0002000100010003','新增站点',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.site.settings.add',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516758603,0),('d9abc363306f4d5fbef97a8f7a6cf4de','febf1ca1528c49daab59f8cbf698488b','0003000500020001','添加菜单',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.conf.menu.add',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517383913,0),('dbff508e055442a3bf87dafeee794f76','99127e7a2a8748e8b3bae275e238cf5e','000200040001','工作流管理','workflow','platform','menu','/platform/cms/workflow','data-pjax','',1,0,'platform.cms.workflow',NULL,32,0,'bbb623218bc641659073a034b9cfb1c7',1516759742,0),('dddf5fc6be4540f4bcb79b6f9bf8a71e','0ca6569701b840af934b5d6d38ae4097','0003000300020001','添加图文',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.reply.news.add',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517382829,0),('de613ed29dae428b875cea4d93936973','c21897fc27254d4c92d9455c216abefb','0003000300040002','修改绑定',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.reply.keyword.edit',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517383038,0),('ded16b4a63cf42bf86b5626620abbe0a','8102dca1971f41e583bc2af4229ea55a','000300040003','发送记录','log','platform','menu','/platform/wx/tpl/log','data-pjax','',1,0,'wx.tpl.log',NULL,18,0,'bbb623218bc641659073a034b9cfb1c7',1517384492,0),('df2a1743988640ab8a78377e18b785c2','7359b29c6fe24171863b02f49a266eae','000200050003','回收站','garbage','platform','menu','/platform/cms/content/garbage','data-pjax','',1,0,'platform.cms.content.garbage',NULL,36,0,'bbb623218bc641659073a034b9cfb1c7',1517191327,0),('dfc098f0925046339d4906ee013ef2fb','6f27d3cecf78457e98387023faf12a72','0002000200040005','新建目录',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.res.addDir',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759563,0),('e02c779c7e50419d8cc086250bc38264','d3dc1eb066de481587131ab048b3f6e2','0002000200010004','刷新栏目',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.channel.refresh',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516758888,0),('e074f270c7184bccb9229472d4950fe7','9eab50fcf2694b7da6d734ea747ccd80','0001000300010003','修改',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.sms.tele.info.edit',NULL,0,0,'',1517452970,0),('e0f585e923f34ce48bb735b0895ea43d','4b4109f0952e46be8ac5a78c6bde4ddb','0001000120001','添加','ApiAppAdd','platform','data',NULL,'data-pjax',NULL,0,0,'platform.sys.api.unit.add',NULL,0,0,'',1517452970,0),('e7806116788746fb8381184f0e8938ac','f587bcfe0962434b8e1a7f721531ba65','0002000200030005','重命名',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.tpl.rename',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759324,0),('e9458f7718fc4c468a5b7b66df3def75','45533f4a901941be966cd8ee56db8623','0001000100040001','添加菜单','Add','platform','data',NULL,NULL,NULL,0,0,'sys.manager.menu.add',NULL,0,0,'',1517452970,0),('ea89da74b98947a3bc6f5d9e2c27a316','5f1afd602a154b1bb43c840ab2f8ee27','000200060004','专题页生成','statictopic','platform','menu','/platform/cms/staticpage/topic','','',1,0,'cms.staticpage.topic',NULL,42,0,'bbb623218bc641659073a034b9cfb1c7',1517191765,0),('eb78f704d16047739749e6878ec8bdc4','6b529321690f4527bfbe80d638f9c1f3','0003000300010002','修改文本',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.reply.txt.edit',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517382591,0),('ec0bcc3a3d5e4854a03289ebe6b5733d','1b584cd4075c4d9eac6972c233437479','0001000100080001','添加路由','Add','platform','data',NULL,NULL,NULL,0,0,'sys.manager.route.add',NULL,0,0,'',1517452970,0),('ec8bca425e0845fe8b39b76161b086f0','9eab50fcf2694b7da6d734ea747ccd80','0001000300010002','删除',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.sms.tele.info.delete',NULL,0,0,'',1517452970,0),('eca69ff7058940f386a3903a67e67bf7','2cfdc33046d140a18b318f7870a0f73d','00010003','虚拟号码管理','xnhmgl','platform','menu',NULL,'data-pjax',NULL,1,0,'tele',NULL,60,1,'',1517452970,0),('ee37dbb88f00473e8881106c7da1a46e','5e84be56a3274b24b625624e89408627','0004000100030003','删除',NULL,'platform','data',NULL,NULL,NULL,0,0,'eysh.tour.verse.delete',NULL,78,0,'1a38a92345bd44ed98648b9162b2d67a',1521896429,0),('ee64d857d7144e0faaf4c6bf529d7704','0ca6569701b840af934b5d6d38ae4097','0003000300020002','修改图文',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.reply.news.edit',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517382841,0),('eea54c6fb72b487e8b9a9f5756382d25','aab9af88820e4cd48a62f417ce503b67','0001000100090001','添加应用','Add','platform','data',NULL,NULL,NULL,0,0,'sys.manager.api.add',NULL,0,0,'',1517452970,0),('ef7db3ea2b0d4fac99302cb6c95b18ca','febf1ca1528c49daab59f8cbf698488b','0003000500020005','推送到微信',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.conf.menu.push',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517383963,0),('f0712ecdb5e5484bb06813a64a1fb881','3db17fd76f364a37be24bd72efe9dcde','0003000500010001','添加帐号',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.conf.account.add',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517383540,0),('f4e471a70fc24eb4ba2c927dd283648b','8818e755cec24a6ea737d46d4025b2da','0001000100110003','删除地区','Delete','platform','data',NULL,NULL,NULL,0,0,'sys.config.delivery.area.delete',NULL,0,0,'',1517452970,0),('f586812f2e3d4704a718cf7c9e76eaf4','2cfdc33046d140a18b318f7870a0f73d','000100010013','客户订阅管理','khdygl','platform','menu','/platform/sys/subscribe','data-pjax',NULL,1,0,'platform.sys.subscribe',NULL,56,0,'',1517452970,0),('f587bcfe0962434b8e1a7f721531ba65','4381af82a2a2446495fba3eb146d7f61','000200020003','模版管理','tpl','platform','menu','/platform/cms/tpl','data-pjax','',1,0,'cms.tpl',NULL,26,0,'bbb623218bc641659073a034b9cfb1c7',1516759338,0),('f6bdd58aefbc43f1b79e2886aacc7028','a3501a5974f34625aacddb61a10f426e','0002000500010002','修改',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.topic.edit',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759802,0),('fb490e890d41454da7c9d0cbf5041560','6b529321690f4527bfbe80d638f9c1f3','0003000300010003','添加文本',NULL,'platform','data',NULL,NULL,NULL,0,0,'wx.reply.txt.add',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1517382609,0),('fbd34327c5674ec3aca2cfc089089ff4','a3501a5974f34625aacddb61a10f426e','0002000500010003','删除',NULL,'platform','data',NULL,NULL,NULL,0,0,'platform.cms.topic.delete',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759802,0),('fde039f39db840ca89987119198db229','1c7b1c927b464af58f3cc0b2ad8b69b0','0002000300020003','删除',NULL,'platform','data',NULL,NULL,NULL,0,0,'cms.link.link.delete',NULL,0,0,'bbb623218bc641659073a034b9cfb1c7',1516759695,0),('febf1ca1528c49daab59f8cbf698488b','d625d80589144d51a13c5ee0c091d0dc','000300050002','菜单配置','Menu','platform','menu','/platform/wx/conf/menu','data-pjax','',1,0,'wx.conf.menu',NULL,14,0,'bbb623218bc641659073a034b9cfb1c7',1517383964,0);

/*Table structure for table `sys_qq` */

DROP TABLE IF EXISTS `sys_qq`;

CREATE TABLE `sys_qq` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '客服名称',
  `qq` varchar(20) DEFAULT NULL COMMENT '客服qq号',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_qq` */

/*Table structure for table `sys_qrtz_blob_triggers` */

DROP TABLE IF EXISTS `sys_qrtz_blob_triggers`;

CREATE TABLE `sys_qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `sys_qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `sys_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_qrtz_blob_triggers` */

/*Table structure for table `sys_qrtz_calendars` */

DROP TABLE IF EXISTS `sys_qrtz_calendars`;

CREATE TABLE `sys_qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_qrtz_calendars` */

/*Table structure for table `sys_qrtz_cron_triggers` */

DROP TABLE IF EXISTS `sys_qrtz_cron_triggers`;

CREATE TABLE `sys_qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `sys_qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `sys_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_qrtz_cron_triggers` */

insert  into `sys_qrtz_cron_triggers`(`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`CRON_EXPRESSION`,`TIME_ZONE_ID`) values ('clusterScheduler','a13572c1e7bb49fca3c5f27c4ba63b54','a13572c1e7bb49fca3c5f27c4ba63b54','0 59 23 L * ?','Asia/Shanghai'),('clusterScheduler','c819429a822a4047bae0ec37fffdc2a2','c819429a822a4047bae0ec37fffdc2a2','0 0 23 * * ?','Asia/Shanghai');

/*Table structure for table `sys_qrtz_fired_triggers` */

DROP TABLE IF EXISTS `sys_qrtz_fired_triggers`;

CREATE TABLE `sys_qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_SYS_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_SYS_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_SYS_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_SYS_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_SYS_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_SYS_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_qrtz_fired_triggers` */

/*Table structure for table `sys_qrtz_job_details` */

DROP TABLE IF EXISTS `sys_qrtz_job_details`;

CREATE TABLE `sys_qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_SYS_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_SYS_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_qrtz_job_details` */

insert  into `sys_qrtz_job_details`(`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`,`DESCRIPTION`,`JOB_CLASS_NAME`,`IS_DURABLE`,`IS_NONCONCURRENT`,`IS_UPDATE_DATA`,`REQUESTS_RECOVERY`,`JOB_DATA`) values ('clusterScheduler','a13572c1e7bb49fca3c5f27c4ba63b54','a13572c1e7bb49fca3c5f27c4ba63b54',NULL,'com.aebiz.app.web.commons.quartz.job.SLogMonthTableCreateJob','0','0','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xp\0sr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0\0x\0'),('clusterScheduler','c819429a822a4047bae0ec37fffdc2a2','c819429a822a4047bae0ec37fffdc2a2',NULL,'com.aebiz.app.web.commons.quartz.job.TourJourneyJob','0','0','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xp\0sr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0\0x\0');

/*Table structure for table `sys_qrtz_locks` */

DROP TABLE IF EXISTS `sys_qrtz_locks`;

CREATE TABLE `sys_qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_qrtz_locks` */

insert  into `sys_qrtz_locks`(`SCHED_NAME`,`LOCK_NAME`) values ('clusterScheduler','STATE_ACCESS'),('clusterScheduler','TRIGGER_ACCESS');

/*Table structure for table `sys_qrtz_paused_trigger_grps` */

DROP TABLE IF EXISTS `sys_qrtz_paused_trigger_grps`;

CREATE TABLE `sys_qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_qrtz_paused_trigger_grps` */

/*Table structure for table `sys_qrtz_scheduler_state` */

DROP TABLE IF EXISTS `sys_qrtz_scheduler_state`;

CREATE TABLE `sys_qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_qrtz_scheduler_state` */

insert  into `sys_qrtz_scheduler_state`(`SCHED_NAME`,`INSTANCE_NAME`,`LAST_CHECKIN_TIME`,`CHECKIN_INTERVAL`) values ('clusterScheduler','zjj1524016872122',1524024690099,20000);

/*Table structure for table `sys_qrtz_simple_triggers` */

DROP TABLE IF EXISTS `sys_qrtz_simple_triggers`;

CREATE TABLE `sys_qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `sys_qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `sys_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_qrtz_simple_triggers` */

/*Table structure for table `sys_qrtz_simprop_triggers` */

DROP TABLE IF EXISTS `sys_qrtz_simprop_triggers`;

CREATE TABLE `sys_qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `sys_qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `sys_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_qrtz_simprop_triggers` */

/*Table structure for table `sys_qrtz_triggers` */

DROP TABLE IF EXISTS `sys_qrtz_triggers`;

CREATE TABLE `sys_qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_SYS_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_SYS_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_SYS_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_SYS_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_SYS_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_SYS_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_SYS_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_SYS_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_SYS_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_SYS_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_SYS_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_SYS_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `sys_qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `sys_qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_qrtz_triggers` */

insert  into `sys_qrtz_triggers`(`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`JOB_NAME`,`JOB_GROUP`,`DESCRIPTION`,`NEXT_FIRE_TIME`,`PREV_FIRE_TIME`,`PRIORITY`,`TRIGGER_STATE`,`TRIGGER_TYPE`,`START_TIME`,`END_TIME`,`CALENDAR_NAME`,`MISFIRE_INSTR`,`JOB_DATA`) values ('clusterScheduler','a13572c1e7bb49fca3c5f27c4ba63b54','a13572c1e7bb49fca3c5f27c4ba63b54','a13572c1e7bb49fca3c5f27c4ba63b54','a13572c1e7bb49fca3c5f27c4ba63b54',NULL,1525103940000,-1,5,'WAITING','CRON',1524016881000,0,NULL,0,''),('clusterScheduler','c819429a822a4047bae0ec37fffdc2a2','c819429a822a4047bae0ec37fffdc2a2','c819429a822a4047bae0ec37fffdc2a2','c819429a822a4047bae0ec37fffdc2a2',NULL,1524063600000,-1,5,'WAITING','CRON',1524016881000,0,NULL,0,'');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `aliasName` varchar(50) DEFAULT NULL,
  `disabled` tinyint(1) DEFAULT NULL,
  `unitid` varchar(32) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SYS_ROLE_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`code`,`aliasName`,`disabled`,`unitid`,`note`,`opBy`,`opAt`,`delFlag`) values ('2ed3dd76c3ad4f98886d725bd8a15d6d','WXadmin','wx',NULL,0,'ac380368d900410caa5194a751d8f2fb','负责处理微信后台数据','1a38a92345bd44ed98648b9162b2d67a',1517465248,0),('528d6a61affd4d9ab346d10e7653da81','adminEysh','eysh',NULL,0,'ac380368d900410caa5194a751d8f2fb','行者之家管理员','1a38a92345bd44ed98648b9162b2d67a',1521964954,0),('791cece1cbc047a2af788950a3c9e93e','CMS管理员','cms',NULL,0,'ac380368d900410caa5194a751d8f2fb','CMS管理','1a38a92345bd44ed98648b9162b2d67a',1517465705,0),('8dbcb08a74fb4f458ba43474f9b3f8ac','系统管理员','sysadmin','Sysadmin',0,'d094dfd0dd33402198ac8aac6deca01e','System Admin','',1517452970,0),('dee8221b51bf442f9114c0e667f9fc12','公共角色','public','Public',0,'d094dfd0dd33402198ac8aac6deca01e','All user has role','',1517452970,0);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `roleId` varchar(32) DEFAULT NULL,
  `menuId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`roleId`,`menuId`) values ('a937e3aa5e074a0b86fca09157ca9319','4b133273aba543878e0f132f69e47457'),('a937e3aa5e074a0b86fca09157ca9319','3e6d3fb5bc234feebba82a15bdfa2ef9'),('a937e3aa5e074a0b86fca09157ca9319','d81c017d173648e2aa8ccd8ac79230d2'),('a937e3aa5e074a0b86fca09157ca9319','be0363c47ee047cab3c6b47629c51a79'),('a937e3aa5e074a0b86fca09157ca9319','48ed3df76ded491ebadd737527160744'),('a937e3aa5e074a0b86fca09157ca9319','b487e8b82b0e4d3f887df02f89d37507'),('a937e3aa5e074a0b86fca09157ca9319','32a6986d1b1f45a48c7789ec2112169c'),('a937e3aa5e074a0b86fca09157ca9319','e02c779c7e50419d8cc086250bc38264'),('a937e3aa5e074a0b86fca09157ca9319','6d5f86d6d04f4d8298334993d68a2337'),('a937e3aa5e074a0b86fca09157ca9319','a59bfc8ca6844149bd532c7d0d494d9f'),('a937e3aa5e074a0b86fca09157ca9319','a7b6b1d4e3a54b698ec84eec3ed52808'),('a937e3aa5e074a0b86fca09157ca9319','4c40c6049e914dfc93f897f3231ccc73'),('a937e3aa5e074a0b86fca09157ca9319','c8809a8735864b0ba52591a58f2b1375'),('a937e3aa5e074a0b86fca09157ca9319','8a387fb5ff3948b094068e6f75d7387a'),('a937e3aa5e074a0b86fca09157ca9319','482c744503d548ad826e784e00c04098'),('a937e3aa5e074a0b86fca09157ca9319','7d938aa5abd84415ad2d648a29e11b8b'),('a937e3aa5e074a0b86fca09157ca9319','e7806116788746fb8381184f0e8938ac'),('a937e3aa5e074a0b86fca09157ca9319','d2feef5baba2426f97643bd9a9e586fb'),('a937e3aa5e074a0b86fca09157ca9319','43efc7afc83249c3b4875fc4860f3aca'),('a937e3aa5e074a0b86fca09157ca9319','88e8ec8b29624f15a5fb5130696568f3'),('a937e3aa5e074a0b86fca09157ca9319','1e5687528e264274ace41a50ed0e64e2'),('a937e3aa5e074a0b86fca09157ca9319','8fe0340021da48c999d0f8292dbb29b2'),('a937e3aa5e074a0b86fca09157ca9319','dfc098f0925046339d4906ee013ef2fb'),('a937e3aa5e074a0b86fca09157ca9319','59e62054e822406c88e7db4af7ed8079'),('a937e3aa5e074a0b86fca09157ca9319','d3b283908e2c4507be159ba2c776a9f9'),('a937e3aa5e074a0b86fca09157ca9319','060e4aa715d844168c0d1639962a1c8e'),('a937e3aa5e074a0b86fca09157ca9319','8d7fd658c1e34a30b21aeb4296714cc3'),('a937e3aa5e074a0b86fca09157ca9319','093a17c9d2944793a4e5e364299353fa'),('a937e3aa5e074a0b86fca09157ca9319','c84e77edf76e4b1891943e4bf8f8b516'),('a937e3aa5e074a0b86fca09157ca9319','fde039f39db840ca89987119198db229'),('a937e3aa5e074a0b86fca09157ca9319','51ba669f420b425e9d00538be47ab7a2'),('a937e3aa5e074a0b86fca09157ca9319','1ab1f849dbd3484a85ec5609b9d8e61a'),('a937e3aa5e074a0b86fca09157ca9319','5829d16c03db45baac75c5e987be1506'),('a937e3aa5e074a0b86fca09157ca9319','8f7f347ae9eb40edbca08e31f7998f9d'),('a937e3aa5e074a0b86fca09157ca9319','f6bdd58aefbc43f1b79e2886aacc7028'),('a937e3aa5e074a0b86fca09157ca9319','fbd34327c5674ec3aca2cfc089089ff4'),('a937e3aa5e074a0b86fca09157ca9319','0f0285227e654659998be03d197cc48c'),('a937e3aa5e074a0b86fca09157ca9319','32cd2fe478644290a112bafe6a92965c'),('a937e3aa5e074a0b86fca09157ca9319','d470271109754506a5ffba4ff3f19ba1'),('a937e3aa5e074a0b86fca09157ca9319','4cc19c89acf349349b8065f06d703113'),('a937e3aa5e074a0b86fca09157ca9319','485c764b94c447b19ee4ac25e624cc5c'),('a937e3aa5e074a0b86fca09157ca9319','4f6b3451e57d4f26a9ca4c0904370ef2'),('a937e3aa5e074a0b86fca09157ca9319','b81188925c684908b7c258235865be3a'),('a937e3aa5e074a0b86fca09157ca9319','5642e30d6f1042f7ba8d4ac928514c47'),('a937e3aa5e074a0b86fca09157ca9319','7e0bb38e446e4f58a2925c359e2ce3f4'),('a937e3aa5e074a0b86fca09157ca9319','707a5a0e26244a44bdce0148b618de88'),('a937e3aa5e074a0b86fca09157ca9319','b44cde53b2014f9fb2dd45d98219ed61'),('a937e3aa5e074a0b86fca09157ca9319','a05e37395c97484b9dac1edbad912c32'),('a937e3aa5e074a0b86fca09157ca9319','a0a0db6e4ae04c4ea142c51970d011a9'),('a937e3aa5e074a0b86fca09157ca9319','43c4bc1f77564b3680be836b18b9acc4'),('a937e3aa5e074a0b86fca09157ca9319','c372d1b975c84e77a463f277bfb3180b'),('a937e3aa5e074a0b86fca09157ca9319','25e9c554b2b94c0c906d36e1337512a1'),('a937e3aa5e074a0b86fca09157ca9319','aed9a1f8730e4bcd962127f64fd7c4f2'),('a937e3aa5e074a0b86fca09157ca9319','1c2d18b718d248168049f07cdf363cf2'),('a937e3aa5e074a0b86fca09157ca9319','2b40aede791a4802b2decf0339252fe4'),('a937e3aa5e074a0b86fca09157ca9319','b2d10c80a9c846ebbb3ffaf3e5931993'),('a937e3aa5e074a0b86fca09157ca9319','e41e5e9d04d24bf3a0ab507b055af9bf'),('a937e3aa5e074a0b86fca09157ca9319','50e77272c1c64b398faa70a700b87e97'),('a937e3aa5e074a0b86fca09157ca9319','ce44447cc9774de3b431b6c9e29ab334'),('a937e3aa5e074a0b86fca09157ca9319','ffc415c0733045289f717ad255511f00'),('a937e3aa5e074a0b86fca09157ca9319','e81b7c9378c045d689e003f86b3f5db5'),('a937e3aa5e074a0b86fca09157ca9319','c8a5b0a139c646f7a5b3dc7de4147a75'),('a937e3aa5e074a0b86fca09157ca9319','05798b43d97f4da797bbd3c7880bb43c'),('a937e3aa5e074a0b86fca09157ca9319','68b0b141410c4addbb39e9497424ac41'),('a937e3aa5e074a0b86fca09157ca9319','2e3fe1a78cb8434e89356a6f64da8eda'),('a937e3aa5e074a0b86fca09157ca9319','ee11182e257d42bb975c9438097fee95'),('a937e3aa5e074a0b86fca09157ca9319','1687ad49a63242e9b26d12e17fff5495'),('a937e3aa5e074a0b86fca09157ca9319','482ee3c8cf4848c8801bef5da21f5cc4'),('a937e3aa5e074a0b86fca09157ca9319','f34627997fd94149964168b0378a693a'),('a937e3aa5e074a0b86fca09157ca9319','b17398a68c91442abba7c0283e737280'),('a937e3aa5e074a0b86fca09157ca9319','d7c8a51bc60948febd2e76098bc99071'),('a937e3aa5e074a0b86fca09157ca9319','a6cb7cba70b54ad0b8a936d5a606fb1c'),('a937e3aa5e074a0b86fca09157ca9319','7ecbcd7dc1144e9bbb721d82a41da8ec'),('a937e3aa5e074a0b86fca09157ca9319','a0d29261715e4ad898f8e12ced27b9d6'),('a937e3aa5e074a0b86fca09157ca9319','e3bdb0a040114e98a6ef492e00f60f36'),('a937e3aa5e074a0b86fca09157ca9319','715b2bcd30c84a5b99b5ec2db68e2166'),('a937e3aa5e074a0b86fca09157ca9319','aee3cc6a93df452c8e9997b9a210d152'),('a937e3aa5e074a0b86fca09157ca9319','a4889f17a8d64a3fb0d4029d0fbe2e1d'),('a937e3aa5e074a0b86fca09157ca9319','ece677432ac745bcaf6eb0b371797ea0'),('a937e3aa5e074a0b86fca09157ca9319','61e6901878154ea8be8ebac947378bf8'),('a937e3aa5e074a0b86fca09157ca9319','12af0f9774d14f9f81ea42182876f297'),('a937e3aa5e074a0b86fca09157ca9319','849978ba9ce249a78a7d3d624b79fe3f'),('a937e3aa5e074a0b86fca09157ca9319','7fd2b5cea8a84d25aa56727bbb564b1a'),('a937e3aa5e074a0b86fca09157ca9319','7b81597909104f1e92491bb9acde1dee'),('a937e3aa5e074a0b86fca09157ca9319','e0c4c5d6616145da8013912dd8c512a6'),('a937e3aa5e074a0b86fca09157ca9319','df9e663355514da6bb34a2f073923e2e'),('a937e3aa5e074a0b86fca09157ca9319','ba3b12420492464bbe896665953056e8'),('a937e3aa5e074a0b86fca09157ca9319','5762fd8b0afa4d299f6906c6f8c68e3c'),('a937e3aa5e074a0b86fca09157ca9319','1127d5bd87814fe7b6c46e08082200b9'),('a937e3aa5e074a0b86fca09157ca9319','20e1f016fc4a4f95901fab2be6e62791'),('a937e3aa5e074a0b86fca09157ca9319','bcc2404c232345f3996065c46a0bede9'),('a937e3aa5e074a0b86fca09157ca9319','411ca5cea8414a9c8c329156a7594b14'),('a937e3aa5e074a0b86fca09157ca9319','2930af06a1af4bde8883253fdb25a1b0'),('a937e3aa5e074a0b86fca09157ca9319','6fbfa841433b4cd68b0c8d2cb9ec7b19'),('a937e3aa5e074a0b86fca09157ca9319','855266f9ba4c43818b41ceab43b851a8'),('a937e3aa5e074a0b86fca09157ca9319','e119160f758c404a8c2aff55453bf9fb'),('a937e3aa5e074a0b86fca09157ca9319','de6bcd3d3a1a4187a3d883efa3c86943'),('a937e3aa5e074a0b86fca09157ca9319','32996d89188e484894cc5cd3aeb48331'),('a937e3aa5e074a0b86fca09157ca9319','0e5a1cc1851b4fc5adab2e4df8fd9179'),('a937e3aa5e074a0b86fca09157ca9319','bc9f6369b16148508d977fc63c951a82'),('a937e3aa5e074a0b86fca09157ca9319','abf4b2990e9f4b18a636409872171509'),('a937e3aa5e074a0b86fca09157ca9319','bc3931a2a58b470f9f6f19ec5394dad5'),('a937e3aa5e074a0b86fca09157ca9319','1e31303fa9ea4b2d809e34b3fcec8a64'),('a937e3aa5e074a0b86fca09157ca9319','d71bf5725af6410a8970b34808169dfb'),('a937e3aa5e074a0b86fca09157ca9319','2896e9306e1347cfad778d471cb06a50'),('a937e3aa5e074a0b86fca09157ca9319','7ab1f391b9584a5fadfd06b6baeae4c7'),('a937e3aa5e074a0b86fca09157ca9319','36d8249922824876bdabc07c892a9cfc'),('a937e3aa5e074a0b86fca09157ca9319','140faae251f54b84979fabf62187d829'),('a937e3aa5e074a0b86fca09157ca9319','981b0671f38c4835a211ef648652b659'),('a937e3aa5e074a0b86fca09157ca9319','4ccb479b45834793a4eccf4739f0617a'),('a937e3aa5e074a0b86fca09157ca9319','1991770395df48569263225cdd741f1c'),('a937e3aa5e074a0b86fca09157ca9319','0631806d9edf4a0bbc675f260862bab4'),('a937e3aa5e074a0b86fca09157ca9319','ba796ecacfbb41f3a4685213e5b18380'),('a937e3aa5e074a0b86fca09157ca9319','76201449f9d348e08107869ca4ffb869'),('a937e3aa5e074a0b86fca09157ca9319','9ecbd017bbf549ff89ac08849d348010'),('a937e3aa5e074a0b86fca09157ca9319','6b529321690f4527bfbe80d638f9c1f3'),('a937e3aa5e074a0b86fca09157ca9319','adedb5da86184c269907c7ea24b6f73e'),('a937e3aa5e074a0b86fca09157ca9319','eb78f704d16047739749e6878ec8bdc4'),('a937e3aa5e074a0b86fca09157ca9319','fb490e890d41454da7c9d0cbf5041560'),('a937e3aa5e074a0b86fca09157ca9319','0ca6569701b840af934b5d6d38ae4097'),('a937e3aa5e074a0b86fca09157ca9319','dddf5fc6be4540f4bcb79b6f9bf8a71e'),('a937e3aa5e074a0b86fca09157ca9319','ee64d857d7144e0faaf4c6bf529d7704'),('a937e3aa5e074a0b86fca09157ca9319','d47013e699954826b13d3903f856c821'),('a937e3aa5e074a0b86fca09157ca9319','8b1d29398c8e480e893a50e2741e2e72'),('a937e3aa5e074a0b86fca09157ca9319','402459c22b7d46eab2229f47c479310f'),('a937e3aa5e074a0b86fca09157ca9319','3476ac041c284881878d9b8df4dab8b4'),('a937e3aa5e074a0b86fca09157ca9319','a7d1c610a3eb4da6b3ff246aa88eb765'),('a937e3aa5e074a0b86fca09157ca9319','c21897fc27254d4c92d9455c216abefb'),('a937e3aa5e074a0b86fca09157ca9319','5e4d15dde3c9481d8d581a739275b40d'),('a937e3aa5e074a0b86fca09157ca9319','de613ed29dae428b875cea4d93936973'),('a937e3aa5e074a0b86fca09157ca9319','4aa27cbb9f0b442dbc7634abc71875aa'),('a937e3aa5e074a0b86fca09157ca9319','d625d80589144d51a13c5ee0c091d0dc'),('a937e3aa5e074a0b86fca09157ca9319','3db17fd76f364a37be24bd72efe9dcde'),('a937e3aa5e074a0b86fca09157ca9319','f0712ecdb5e5484bb06813a64a1fb881'),('a937e3aa5e074a0b86fca09157ca9319','7a94ccd1913a4e459f51406432ea58ea'),('a937e3aa5e074a0b86fca09157ca9319','1f378d2531c04947ab1b43d0778e1eae'),('a937e3aa5e074a0b86fca09157ca9319','febf1ca1528c49daab59f8cbf698488b'),('a937e3aa5e074a0b86fca09157ca9319','d9abc363306f4d5fbef97a8f7a6cf4de'),('a937e3aa5e074a0b86fca09157ca9319','4cc2c6a4d9104bae876e7632f025be3d'),('a937e3aa5e074a0b86fca09157ca9319','d19f2346a5084b5e95308abc7654608b'),('a937e3aa5e074a0b86fca09157ca9319','a7c3c57335144e43be4e041202419d78'),('a937e3aa5e074a0b86fca09157ca9319','ef7db3ea2b0d4fac99302cb6c95b18ca'),('a937e3aa5e074a0b86fca09157ca9319','8102dca1971f41e583bc2af4229ea55a'),('a937e3aa5e074a0b86fca09157ca9319','50a4b244620a4ec5acedbb580543a79e'),('a937e3aa5e074a0b86fca09157ca9319','7cf34691816746e390e0191bf3a54c03'),('a937e3aa5e074a0b86fca09157ca9319','017d13cdc23d48118ac82940ac3ef105'),('a937e3aa5e074a0b86fca09157ca9319','0c33da3e9212406ab8ffe4d8f1674baf'),('a937e3aa5e074a0b86fca09157ca9319','9adbc1e74d7e4b589d8ccf90a70144d6'),('a937e3aa5e074a0b86fca09157ca9319','ded16b4a63cf42bf86b5626620abbe0a'),('a937e3aa5e074a0b86fca09157ca9319','ae17ba0fcce64b46a780ea329e052f3b'),('a937e3aa5e074a0b86fca09157ca9319','5490ca2f28ad421b80145eaf96abdc7c'),('a937e3aa5e074a0b86fca09157ca9319','2f848a43ec394c4b96cc7241d2b06381'),('a937e3aa5e074a0b86fca09157ca9319','d3dc1eb066de481587131ab048b3f6e2'),('a937e3aa5e074a0b86fca09157ca9319','4381af82a2a2446495fba3eb146d7f61'),('a937e3aa5e074a0b86fca09157ca9319','99abc5f7fbb34f24bc9c03e5fbbe3054'),('a937e3aa5e074a0b86fca09157ca9319','f587bcfe0962434b8e1a7f721531ba65'),('a937e3aa5e074a0b86fca09157ca9319','6f27d3cecf78457e98387023faf12a72'),('a937e3aa5e074a0b86fca09157ca9319','8cd1a796fca24dd691c0add6a00a941b'),('a937e3aa5e074a0b86fca09157ca9319','6f67fff3e7944a8599ca847b4521f5c1'),('a937e3aa5e074a0b86fca09157ca9319','1c7b1c927b464af58f3cc0b2ad8b69b0'),('a937e3aa5e074a0b86fca09157ca9319','dbff508e055442a3bf87dafeee794f76'),('a937e3aa5e074a0b86fca09157ca9319','99127e7a2a8748e8b3bae275e238cf5e'),('a937e3aa5e074a0b86fca09157ca9319','a3501a5974f34625aacddb61a10f426e'),('a937e3aa5e074a0b86fca09157ca9319','7359b29c6fe24171863b02f49a266eae'),('a937e3aa5e074a0b86fca09157ca9319','942d0029927640008a79322d26d09c4d'),('a937e3aa5e074a0b86fca09157ca9319','df2a1743988640ab8a78377e18b785c2'),('a937e3aa5e074a0b86fca09157ca9319','c2f2cedc581b48429cb01df2ec0f9f49'),('a937e3aa5e074a0b86fca09157ca9319','0d863c2ea36744539f1caa50d6ea698f'),('a937e3aa5e074a0b86fca09157ca9319','5f1afd602a154b1bb43c840ab2f8ee27'),('a937e3aa5e074a0b86fca09157ca9319','921a064041fb44db86cc18ca04c6166b'),('a937e3aa5e074a0b86fca09157ca9319','8dea6d33e18a434f8b44d884cf68331c'),('a937e3aa5e074a0b86fca09157ca9319','ea89da74b98947a3bc6f5d9e2c27a316'),('a937e3aa5e074a0b86fca09157ca9319','748db7f9a6824db9885533960ba20976'),('a937e3aa5e074a0b86fca09157ca9319','fe2464d65b95457c914bbd881322442c'),('a937e3aa5e074a0b86fca09157ca9319','92a62ac43231494abc0b2bba55e8f253'),('a937e3aa5e074a0b86fca09157ca9319','245f29d15f3645f18e9c9ee4001a8861'),('a937e3aa5e074a0b86fca09157ca9319','80480cc0f9054515aa818fe5d485d34d'),('a937e3aa5e074a0b86fca09157ca9319','5f9d78463d184ca791e42c2bb5883045'),('a937e3aa5e074a0b86fca09157ca9319','60bf32d0d88e4fe3b9282667c07e2ccc'),('a937e3aa5e074a0b86fca09157ca9319','64b8913e93ed44a998a42aaeb2e68a82'),('a937e3aa5e074a0b86fca09157ca9319','7655660af40e4d13981b7db25abfbd79'),('a937e3aa5e074a0b86fca09157ca9319','44009964689a43dcb4f57ce55a0df476'),('a937e3aa5e074a0b86fca09157ca9319','140e6d10c25643d7b3ea777d0f3a6f1c'),('a937e3aa5e074a0b86fca09157ca9319','584b310404e14061a7e79110fd1f2cc5'),('a937e3aa5e074a0b86fca09157ca9319','eec382736e0646d79392f43f61c17ffc'),('a937e3aa5e074a0b86fca09157ca9319','429c104c6f6e4c04a4d0a68672203015'),('a937e3aa5e074a0b86fca09157ca9319','2b2b459d96f14aa0a6196cd0c8bb591c'),('a937e3aa5e074a0b86fca09157ca9319','53791e80fae744f795fd94066a76b166'),('a937e3aa5e074a0b86fca09157ca9319','3d592eb4e61e4709a1b2d5fab58463a3'),('a937e3aa5e074a0b86fca09157ca9319','7df81e6ade0e4d1fbe5e817a55c81637'),('a937e3aa5e074a0b86fca09157ca9319','f7b957896ec3429ab1e89a4dfc0c5ad4'),('a937e3aa5e074a0b86fca09157ca9319','4b133273aba543878e0f132f69e47457'),('a937e3aa5e074a0b86fca09157ca9319','3e6d3fb5bc234feebba82a15bdfa2ef9'),('a937e3aa5e074a0b86fca09157ca9319','d81c017d173648e2aa8ccd8ac79230d2'),('a937e3aa5e074a0b86fca09157ca9319','be0363c47ee047cab3c6b47629c51a79'),('a937e3aa5e074a0b86fca09157ca9319','48ed3df76ded491ebadd737527160744'),('a937e3aa5e074a0b86fca09157ca9319','b487e8b82b0e4d3f887df02f89d37507'),('a937e3aa5e074a0b86fca09157ca9319','32a6986d1b1f45a48c7789ec2112169c'),('a937e3aa5e074a0b86fca09157ca9319','e02c779c7e50419d8cc086250bc38264'),('a937e3aa5e074a0b86fca09157ca9319','6d5f86d6d04f4d8298334993d68a2337'),('a937e3aa5e074a0b86fca09157ca9319','a59bfc8ca6844149bd532c7d0d494d9f'),('a937e3aa5e074a0b86fca09157ca9319','a7b6b1d4e3a54b698ec84eec3ed52808'),('a937e3aa5e074a0b86fca09157ca9319','4c40c6049e914dfc93f897f3231ccc73'),('a937e3aa5e074a0b86fca09157ca9319','c8809a8735864b0ba52591a58f2b1375'),('a937e3aa5e074a0b86fca09157ca9319','8a387fb5ff3948b094068e6f75d7387a'),('a937e3aa5e074a0b86fca09157ca9319','482c744503d548ad826e784e00c04098'),('a937e3aa5e074a0b86fca09157ca9319','7d938aa5abd84415ad2d648a29e11b8b'),('a937e3aa5e074a0b86fca09157ca9319','e7806116788746fb8381184f0e8938ac'),('a937e3aa5e074a0b86fca09157ca9319','d2feef5baba2426f97643bd9a9e586fb'),('a937e3aa5e074a0b86fca09157ca9319','43efc7afc83249c3b4875fc4860f3aca'),('a937e3aa5e074a0b86fca09157ca9319','88e8ec8b29624f15a5fb5130696568f3'),('a937e3aa5e074a0b86fca09157ca9319','1e5687528e264274ace41a50ed0e64e2'),('a937e3aa5e074a0b86fca09157ca9319','8fe0340021da48c999d0f8292dbb29b2'),('a937e3aa5e074a0b86fca09157ca9319','dfc098f0925046339d4906ee013ef2fb'),('a937e3aa5e074a0b86fca09157ca9319','59e62054e822406c88e7db4af7ed8079'),('a937e3aa5e074a0b86fca09157ca9319','d3b283908e2c4507be159ba2c776a9f9'),('a937e3aa5e074a0b86fca09157ca9319','060e4aa715d844168c0d1639962a1c8e'),('a937e3aa5e074a0b86fca09157ca9319','8d7fd658c1e34a30b21aeb4296714cc3'),('a937e3aa5e074a0b86fca09157ca9319','093a17c9d2944793a4e5e364299353fa'),('a937e3aa5e074a0b86fca09157ca9319','c84e77edf76e4b1891943e4bf8f8b516'),('a937e3aa5e074a0b86fca09157ca9319','fde039f39db840ca89987119198db229'),('a937e3aa5e074a0b86fca09157ca9319','51ba669f420b425e9d00538be47ab7a2'),('a937e3aa5e074a0b86fca09157ca9319','1ab1f849dbd3484a85ec5609b9d8e61a'),('a937e3aa5e074a0b86fca09157ca9319','5829d16c03db45baac75c5e987be1506'),('a937e3aa5e074a0b86fca09157ca9319','8f7f347ae9eb40edbca08e31f7998f9d'),('a937e3aa5e074a0b86fca09157ca9319','f6bdd58aefbc43f1b79e2886aacc7028'),('a937e3aa5e074a0b86fca09157ca9319','fbd34327c5674ec3aca2cfc089089ff4'),('a937e3aa5e074a0b86fca09157ca9319','0f0285227e654659998be03d197cc48c'),('a937e3aa5e074a0b86fca09157ca9319','32cd2fe478644290a112bafe6a92965c'),('a937e3aa5e074a0b86fca09157ca9319','d470271109754506a5ffba4ff3f19ba1'),('a937e3aa5e074a0b86fca09157ca9319','4cc19c89acf349349b8065f06d703113'),('a937e3aa5e074a0b86fca09157ca9319','485c764b94c447b19ee4ac25e624cc5c'),('a937e3aa5e074a0b86fca09157ca9319','4f6b3451e57d4f26a9ca4c0904370ef2'),('a937e3aa5e074a0b86fca09157ca9319','b81188925c684908b7c258235865be3a'),('a937e3aa5e074a0b86fca09157ca9319','5642e30d6f1042f7ba8d4ac928514c47'),('a937e3aa5e074a0b86fca09157ca9319','7e0bb38e446e4f58a2925c359e2ce3f4'),('a937e3aa5e074a0b86fca09157ca9319','707a5a0e26244a44bdce0148b618de88'),('a937e3aa5e074a0b86fca09157ca9319','b44cde53b2014f9fb2dd45d98219ed61'),('a937e3aa5e074a0b86fca09157ca9319','a05e37395c97484b9dac1edbad912c32'),('a937e3aa5e074a0b86fca09157ca9319','a0a0db6e4ae04c4ea142c51970d011a9'),('a937e3aa5e074a0b86fca09157ca9319','43c4bc1f77564b3680be836b18b9acc4'),('a937e3aa5e074a0b86fca09157ca9319','c372d1b975c84e77a463f277bfb3180b'),('a937e3aa5e074a0b86fca09157ca9319','25e9c554b2b94c0c906d36e1337512a1'),('a937e3aa5e074a0b86fca09157ca9319','aed9a1f8730e4bcd962127f64fd7c4f2'),('a937e3aa5e074a0b86fca09157ca9319','1c2d18b718d248168049f07cdf363cf2'),('a937e3aa5e074a0b86fca09157ca9319','2b40aede791a4802b2decf0339252fe4'),('a937e3aa5e074a0b86fca09157ca9319','b2d10c80a9c846ebbb3ffaf3e5931993'),('a937e3aa5e074a0b86fca09157ca9319','e41e5e9d04d24bf3a0ab507b055af9bf'),('a937e3aa5e074a0b86fca09157ca9319','50e77272c1c64b398faa70a700b87e97'),('a937e3aa5e074a0b86fca09157ca9319','ce44447cc9774de3b431b6c9e29ab334'),('a937e3aa5e074a0b86fca09157ca9319','ffc415c0733045289f717ad255511f00'),('a937e3aa5e074a0b86fca09157ca9319','e81b7c9378c045d689e003f86b3f5db5'),('a937e3aa5e074a0b86fca09157ca9319','c8a5b0a139c646f7a5b3dc7de4147a75'),('a937e3aa5e074a0b86fca09157ca9319','05798b43d97f4da797bbd3c7880bb43c'),('a937e3aa5e074a0b86fca09157ca9319','68b0b141410c4addbb39e9497424ac41'),('a937e3aa5e074a0b86fca09157ca9319','2e3fe1a78cb8434e89356a6f64da8eda'),('a937e3aa5e074a0b86fca09157ca9319','ee11182e257d42bb975c9438097fee95'),('a937e3aa5e074a0b86fca09157ca9319','1687ad49a63242e9b26d12e17fff5495'),('a937e3aa5e074a0b86fca09157ca9319','482ee3c8cf4848c8801bef5da21f5cc4'),('a937e3aa5e074a0b86fca09157ca9319','f34627997fd94149964168b0378a693a'),('a937e3aa5e074a0b86fca09157ca9319','b17398a68c91442abba7c0283e737280'),('a937e3aa5e074a0b86fca09157ca9319','d7c8a51bc60948febd2e76098bc99071'),('a937e3aa5e074a0b86fca09157ca9319','a6cb7cba70b54ad0b8a936d5a606fb1c'),('a937e3aa5e074a0b86fca09157ca9319','7ecbcd7dc1144e9bbb721d82a41da8ec'),('a937e3aa5e074a0b86fca09157ca9319','a0d29261715e4ad898f8e12ced27b9d6'),('a937e3aa5e074a0b86fca09157ca9319','e3bdb0a040114e98a6ef492e00f60f36'),('a937e3aa5e074a0b86fca09157ca9319','715b2bcd30c84a5b99b5ec2db68e2166'),('a937e3aa5e074a0b86fca09157ca9319','aee3cc6a93df452c8e9997b9a210d152'),('a937e3aa5e074a0b86fca09157ca9319','a4889f17a8d64a3fb0d4029d0fbe2e1d'),('a937e3aa5e074a0b86fca09157ca9319','ece677432ac745bcaf6eb0b371797ea0'),('a937e3aa5e074a0b86fca09157ca9319','61e6901878154ea8be8ebac947378bf8'),('a937e3aa5e074a0b86fca09157ca9319','12af0f9774d14f9f81ea42182876f297'),('a937e3aa5e074a0b86fca09157ca9319','849978ba9ce249a78a7d3d624b79fe3f'),('a937e3aa5e074a0b86fca09157ca9319','7fd2b5cea8a84d25aa56727bbb564b1a'),('a937e3aa5e074a0b86fca09157ca9319','7b81597909104f1e92491bb9acde1dee'),('a937e3aa5e074a0b86fca09157ca9319','e0c4c5d6616145da8013912dd8c512a6'),('a937e3aa5e074a0b86fca09157ca9319','df9e663355514da6bb34a2f073923e2e'),('a937e3aa5e074a0b86fca09157ca9319','ba3b12420492464bbe896665953056e8'),('a937e3aa5e074a0b86fca09157ca9319','5762fd8b0afa4d299f6906c6f8c68e3c'),('a937e3aa5e074a0b86fca09157ca9319','1127d5bd87814fe7b6c46e08082200b9'),('a937e3aa5e074a0b86fca09157ca9319','20e1f016fc4a4f95901fab2be6e62791'),('a937e3aa5e074a0b86fca09157ca9319','bcc2404c232345f3996065c46a0bede9'),('a937e3aa5e074a0b86fca09157ca9319','411ca5cea8414a9c8c329156a7594b14'),('a937e3aa5e074a0b86fca09157ca9319','2930af06a1af4bde8883253fdb25a1b0'),('a937e3aa5e074a0b86fca09157ca9319','6fbfa841433b4cd68b0c8d2cb9ec7b19'),('a937e3aa5e074a0b86fca09157ca9319','855266f9ba4c43818b41ceab43b851a8'),('a937e3aa5e074a0b86fca09157ca9319','e119160f758c404a8c2aff55453bf9fb'),('a937e3aa5e074a0b86fca09157ca9319','de6bcd3d3a1a4187a3d883efa3c86943'),('a937e3aa5e074a0b86fca09157ca9319','32996d89188e484894cc5cd3aeb48331'),('a937e3aa5e074a0b86fca09157ca9319','0e5a1cc1851b4fc5adab2e4df8fd9179'),('a937e3aa5e074a0b86fca09157ca9319','bc9f6369b16148508d977fc63c951a82'),('a937e3aa5e074a0b86fca09157ca9319','abf4b2990e9f4b18a636409872171509'),('a937e3aa5e074a0b86fca09157ca9319','bc3931a2a58b470f9f6f19ec5394dad5'),('a937e3aa5e074a0b86fca09157ca9319','1e31303fa9ea4b2d809e34b3fcec8a64'),('a937e3aa5e074a0b86fca09157ca9319','d71bf5725af6410a8970b34808169dfb'),('a937e3aa5e074a0b86fca09157ca9319','2896e9306e1347cfad778d471cb06a50'),('a937e3aa5e074a0b86fca09157ca9319','7ab1f391b9584a5fadfd06b6baeae4c7'),('a937e3aa5e074a0b86fca09157ca9319','36d8249922824876bdabc07c892a9cfc'),('a937e3aa5e074a0b86fca09157ca9319','140faae251f54b84979fabf62187d829'),('a937e3aa5e074a0b86fca09157ca9319','981b0671f38c4835a211ef648652b659'),('a937e3aa5e074a0b86fca09157ca9319','4ccb479b45834793a4eccf4739f0617a'),('a937e3aa5e074a0b86fca09157ca9319','1991770395df48569263225cdd741f1c'),('a937e3aa5e074a0b86fca09157ca9319','0631806d9edf4a0bbc675f260862bab4'),('a937e3aa5e074a0b86fca09157ca9319','ba796ecacfbb41f3a4685213e5b18380'),('a937e3aa5e074a0b86fca09157ca9319','76201449f9d348e08107869ca4ffb869'),('a937e3aa5e074a0b86fca09157ca9319','9ecbd017bbf549ff89ac08849d348010'),('a937e3aa5e074a0b86fca09157ca9319','6b529321690f4527bfbe80d638f9c1f3'),('a937e3aa5e074a0b86fca09157ca9319','adedb5da86184c269907c7ea24b6f73e'),('a937e3aa5e074a0b86fca09157ca9319','eb78f704d16047739749e6878ec8bdc4'),('a937e3aa5e074a0b86fca09157ca9319','fb490e890d41454da7c9d0cbf5041560'),('a937e3aa5e074a0b86fca09157ca9319','0ca6569701b840af934b5d6d38ae4097'),('a937e3aa5e074a0b86fca09157ca9319','dddf5fc6be4540f4bcb79b6f9bf8a71e'),('a937e3aa5e074a0b86fca09157ca9319','ee64d857d7144e0faaf4c6bf529d7704'),('a937e3aa5e074a0b86fca09157ca9319','d47013e699954826b13d3903f856c821'),('a937e3aa5e074a0b86fca09157ca9319','8b1d29398c8e480e893a50e2741e2e72'),('a937e3aa5e074a0b86fca09157ca9319','402459c22b7d46eab2229f47c479310f'),('a937e3aa5e074a0b86fca09157ca9319','3476ac041c284881878d9b8df4dab8b4'),('a937e3aa5e074a0b86fca09157ca9319','a7d1c610a3eb4da6b3ff246aa88eb765'),('a937e3aa5e074a0b86fca09157ca9319','c21897fc27254d4c92d9455c216abefb'),('a937e3aa5e074a0b86fca09157ca9319','5e4d15dde3c9481d8d581a739275b40d'),('a937e3aa5e074a0b86fca09157ca9319','de613ed29dae428b875cea4d93936973'),('a937e3aa5e074a0b86fca09157ca9319','4aa27cbb9f0b442dbc7634abc71875aa'),('a937e3aa5e074a0b86fca09157ca9319','d625d80589144d51a13c5ee0c091d0dc'),('a937e3aa5e074a0b86fca09157ca9319','3db17fd76f364a37be24bd72efe9dcde'),('a937e3aa5e074a0b86fca09157ca9319','f0712ecdb5e5484bb06813a64a1fb881'),('a937e3aa5e074a0b86fca09157ca9319','7a94ccd1913a4e459f51406432ea58ea'),('a937e3aa5e074a0b86fca09157ca9319','1f378d2531c04947ab1b43d0778e1eae'),('a937e3aa5e074a0b86fca09157ca9319','febf1ca1528c49daab59f8cbf698488b'),('a937e3aa5e074a0b86fca09157ca9319','d9abc363306f4d5fbef97a8f7a6cf4de'),('a937e3aa5e074a0b86fca09157ca9319','4cc2c6a4d9104bae876e7632f025be3d'),('a937e3aa5e074a0b86fca09157ca9319','d19f2346a5084b5e95308abc7654608b'),('a937e3aa5e074a0b86fca09157ca9319','a7c3c57335144e43be4e041202419d78'),('a937e3aa5e074a0b86fca09157ca9319','ef7db3ea2b0d4fac99302cb6c95b18ca'),('a937e3aa5e074a0b86fca09157ca9319','8102dca1971f41e583bc2af4229ea55a'),('a937e3aa5e074a0b86fca09157ca9319','50a4b244620a4ec5acedbb580543a79e'),('a937e3aa5e074a0b86fca09157ca9319','7cf34691816746e390e0191bf3a54c03'),('a937e3aa5e074a0b86fca09157ca9319','017d13cdc23d48118ac82940ac3ef105'),('a937e3aa5e074a0b86fca09157ca9319','0c33da3e9212406ab8ffe4d8f1674baf'),('a937e3aa5e074a0b86fca09157ca9319','9adbc1e74d7e4b589d8ccf90a70144d6'),('a937e3aa5e074a0b86fca09157ca9319','ded16b4a63cf42bf86b5626620abbe0a'),('a937e3aa5e074a0b86fca09157ca9319','ae17ba0fcce64b46a780ea329e052f3b'),('a937e3aa5e074a0b86fca09157ca9319','5490ca2f28ad421b80145eaf96abdc7c'),('a937e3aa5e074a0b86fca09157ca9319','2f848a43ec394c4b96cc7241d2b06381'),('a937e3aa5e074a0b86fca09157ca9319','d3dc1eb066de481587131ab048b3f6e2'),('a937e3aa5e074a0b86fca09157ca9319','4381af82a2a2446495fba3eb146d7f61'),('a937e3aa5e074a0b86fca09157ca9319','99abc5f7fbb34f24bc9c03e5fbbe3054'),('a937e3aa5e074a0b86fca09157ca9319','f587bcfe0962434b8e1a7f721531ba65'),('a937e3aa5e074a0b86fca09157ca9319','6f27d3cecf78457e98387023faf12a72'),('a937e3aa5e074a0b86fca09157ca9319','8cd1a796fca24dd691c0add6a00a941b'),('a937e3aa5e074a0b86fca09157ca9319','6f67fff3e7944a8599ca847b4521f5c1'),('a937e3aa5e074a0b86fca09157ca9319','1c7b1c927b464af58f3cc0b2ad8b69b0'),('a937e3aa5e074a0b86fca09157ca9319','dbff508e055442a3bf87dafeee794f76'),('a937e3aa5e074a0b86fca09157ca9319','99127e7a2a8748e8b3bae275e238cf5e'),('a937e3aa5e074a0b86fca09157ca9319','a3501a5974f34625aacddb61a10f426e'),('a937e3aa5e074a0b86fca09157ca9319','7359b29c6fe24171863b02f49a266eae'),('a937e3aa5e074a0b86fca09157ca9319','942d0029927640008a79322d26d09c4d'),('a937e3aa5e074a0b86fca09157ca9319','df2a1743988640ab8a78377e18b785c2'),('a937e3aa5e074a0b86fca09157ca9319','c2f2cedc581b48429cb01df2ec0f9f49'),('a937e3aa5e074a0b86fca09157ca9319','0d863c2ea36744539f1caa50d6ea698f'),('a937e3aa5e074a0b86fca09157ca9319','5f1afd602a154b1bb43c840ab2f8ee27'),('a937e3aa5e074a0b86fca09157ca9319','921a064041fb44db86cc18ca04c6166b'),('a937e3aa5e074a0b86fca09157ca9319','8dea6d33e18a434f8b44d884cf68331c'),('a937e3aa5e074a0b86fca09157ca9319','ea89da74b98947a3bc6f5d9e2c27a316'),('a937e3aa5e074a0b86fca09157ca9319','748db7f9a6824db9885533960ba20976'),('a937e3aa5e074a0b86fca09157ca9319','fe2464d65b95457c914bbd881322442c'),('a937e3aa5e074a0b86fca09157ca9319','92a62ac43231494abc0b2bba55e8f253'),('a937e3aa5e074a0b86fca09157ca9319','245f29d15f3645f18e9c9ee4001a8861'),('a937e3aa5e074a0b86fca09157ca9319','80480cc0f9054515aa818fe5d485d34d'),('a937e3aa5e074a0b86fca09157ca9319','5f9d78463d184ca791e42c2bb5883045'),('a937e3aa5e074a0b86fca09157ca9319','60bf32d0d88e4fe3b9282667c07e2ccc'),('a937e3aa5e074a0b86fca09157ca9319','64b8913e93ed44a998a42aaeb2e68a82'),('a937e3aa5e074a0b86fca09157ca9319','7655660af40e4d13981b7db25abfbd79'),('a937e3aa5e074a0b86fca09157ca9319','44009964689a43dcb4f57ce55a0df476'),('a937e3aa5e074a0b86fca09157ca9319','140e6d10c25643d7b3ea777d0f3a6f1c'),('a937e3aa5e074a0b86fca09157ca9319','584b310404e14061a7e79110fd1f2cc5'),('a937e3aa5e074a0b86fca09157ca9319','eec382736e0646d79392f43f61c17ffc'),('a937e3aa5e074a0b86fca09157ca9319','429c104c6f6e4c04a4d0a68672203015'),('a937e3aa5e074a0b86fca09157ca9319','2b2b459d96f14aa0a6196cd0c8bb591c'),('a937e3aa5e074a0b86fca09157ca9319','53791e80fae744f795fd94066a76b166'),('a937e3aa5e074a0b86fca09157ca9319','3d592eb4e61e4709a1b2d5fab58463a3'),('a937e3aa5e074a0b86fca09157ca9319','7df81e6ade0e4d1fbe5e817a55c81637'),('a937e3aa5e074a0b86fca09157ca9319','f7b957896ec3429ab1e89a4dfc0c5ad4'),('a937e3aa5e074a0b86fca09157ca9319','4b133273aba543878e0f132f69e47457'),('a937e3aa5e074a0b86fca09157ca9319','3e6d3fb5bc234feebba82a15bdfa2ef9'),('a937e3aa5e074a0b86fca09157ca9319','d81c017d173648e2aa8ccd8ac79230d2'),('a937e3aa5e074a0b86fca09157ca9319','be0363c47ee047cab3c6b47629c51a79'),('a937e3aa5e074a0b86fca09157ca9319','48ed3df76ded491ebadd737527160744'),('a937e3aa5e074a0b86fca09157ca9319','b487e8b82b0e4d3f887df02f89d37507'),('a937e3aa5e074a0b86fca09157ca9319','32a6986d1b1f45a48c7789ec2112169c'),('a937e3aa5e074a0b86fca09157ca9319','e02c779c7e50419d8cc086250bc38264'),('a937e3aa5e074a0b86fca09157ca9319','6d5f86d6d04f4d8298334993d68a2337'),('a937e3aa5e074a0b86fca09157ca9319','a59bfc8ca6844149bd532c7d0d494d9f'),('a937e3aa5e074a0b86fca09157ca9319','a7b6b1d4e3a54b698ec84eec3ed52808'),('a937e3aa5e074a0b86fca09157ca9319','4c40c6049e914dfc93f897f3231ccc73'),('a937e3aa5e074a0b86fca09157ca9319','c8809a8735864b0ba52591a58f2b1375'),('a937e3aa5e074a0b86fca09157ca9319','8a387fb5ff3948b094068e6f75d7387a'),('a937e3aa5e074a0b86fca09157ca9319','482c744503d548ad826e784e00c04098'),('a937e3aa5e074a0b86fca09157ca9319','7d938aa5abd84415ad2d648a29e11b8b'),('a937e3aa5e074a0b86fca09157ca9319','e7806116788746fb8381184f0e8938ac'),('a937e3aa5e074a0b86fca09157ca9319','d2feef5baba2426f97643bd9a9e586fb'),('a937e3aa5e074a0b86fca09157ca9319','43efc7afc83249c3b4875fc4860f3aca'),('a937e3aa5e074a0b86fca09157ca9319','88e8ec8b29624f15a5fb5130696568f3'),('a937e3aa5e074a0b86fca09157ca9319','1e5687528e264274ace41a50ed0e64e2'),('a937e3aa5e074a0b86fca09157ca9319','8fe0340021da48c999d0f8292dbb29b2'),('a937e3aa5e074a0b86fca09157ca9319','dfc098f0925046339d4906ee013ef2fb'),('a937e3aa5e074a0b86fca09157ca9319','59e62054e822406c88e7db4af7ed8079'),('a937e3aa5e074a0b86fca09157ca9319','d3b283908e2c4507be159ba2c776a9f9'),('a937e3aa5e074a0b86fca09157ca9319','060e4aa715d844168c0d1639962a1c8e'),('a937e3aa5e074a0b86fca09157ca9319','8d7fd658c1e34a30b21aeb4296714cc3'),('a937e3aa5e074a0b86fca09157ca9319','093a17c9d2944793a4e5e364299353fa'),('a937e3aa5e074a0b86fca09157ca9319','c84e77edf76e4b1891943e4bf8f8b516'),('a937e3aa5e074a0b86fca09157ca9319','fde039f39db840ca89987119198db229'),('a937e3aa5e074a0b86fca09157ca9319','51ba669f420b425e9d00538be47ab7a2'),('a937e3aa5e074a0b86fca09157ca9319','1ab1f849dbd3484a85ec5609b9d8e61a'),('a937e3aa5e074a0b86fca09157ca9319','5829d16c03db45baac75c5e987be1506'),('a937e3aa5e074a0b86fca09157ca9319','8f7f347ae9eb40edbca08e31f7998f9d'),('a937e3aa5e074a0b86fca09157ca9319','f6bdd58aefbc43f1b79e2886aacc7028'),('a937e3aa5e074a0b86fca09157ca9319','fbd34327c5674ec3aca2cfc089089ff4'),('a937e3aa5e074a0b86fca09157ca9319','0f0285227e654659998be03d197cc48c'),('a937e3aa5e074a0b86fca09157ca9319','32cd2fe478644290a112bafe6a92965c'),('a937e3aa5e074a0b86fca09157ca9319','d470271109754506a5ffba4ff3f19ba1'),('a937e3aa5e074a0b86fca09157ca9319','4cc19c89acf349349b8065f06d703113'),('a937e3aa5e074a0b86fca09157ca9319','485c764b94c447b19ee4ac25e624cc5c'),('a937e3aa5e074a0b86fca09157ca9319','4f6b3451e57d4f26a9ca4c0904370ef2'),('a937e3aa5e074a0b86fca09157ca9319','b81188925c684908b7c258235865be3a'),('a937e3aa5e074a0b86fca09157ca9319','5642e30d6f1042f7ba8d4ac928514c47'),('a937e3aa5e074a0b86fca09157ca9319','7e0bb38e446e4f58a2925c359e2ce3f4'),('a937e3aa5e074a0b86fca09157ca9319','707a5a0e26244a44bdce0148b618de88'),('a937e3aa5e074a0b86fca09157ca9319','b44cde53b2014f9fb2dd45d98219ed61'),('a937e3aa5e074a0b86fca09157ca9319','a05e37395c97484b9dac1edbad912c32'),('a937e3aa5e074a0b86fca09157ca9319','a0a0db6e4ae04c4ea142c51970d011a9'),('a937e3aa5e074a0b86fca09157ca9319','43c4bc1f77564b3680be836b18b9acc4'),('a937e3aa5e074a0b86fca09157ca9319','c372d1b975c84e77a463f277bfb3180b'),('a937e3aa5e074a0b86fca09157ca9319','25e9c554b2b94c0c906d36e1337512a1'),('a937e3aa5e074a0b86fca09157ca9319','aed9a1f8730e4bcd962127f64fd7c4f2'),('a937e3aa5e074a0b86fca09157ca9319','1c2d18b718d248168049f07cdf363cf2'),('a937e3aa5e074a0b86fca09157ca9319','2b40aede791a4802b2decf0339252fe4'),('a937e3aa5e074a0b86fca09157ca9319','b2d10c80a9c846ebbb3ffaf3e5931993'),('a937e3aa5e074a0b86fca09157ca9319','e41e5e9d04d24bf3a0ab507b055af9bf'),('a937e3aa5e074a0b86fca09157ca9319','50e77272c1c64b398faa70a700b87e97'),('a937e3aa5e074a0b86fca09157ca9319','ce44447cc9774de3b431b6c9e29ab334'),('a937e3aa5e074a0b86fca09157ca9319','ffc415c0733045289f717ad255511f00'),('a937e3aa5e074a0b86fca09157ca9319','e81b7c9378c045d689e003f86b3f5db5'),('a937e3aa5e074a0b86fca09157ca9319','c8a5b0a139c646f7a5b3dc7de4147a75'),('a937e3aa5e074a0b86fca09157ca9319','05798b43d97f4da797bbd3c7880bb43c'),('a937e3aa5e074a0b86fca09157ca9319','68b0b141410c4addbb39e9497424ac41'),('a937e3aa5e074a0b86fca09157ca9319','2e3fe1a78cb8434e89356a6f64da8eda'),('a937e3aa5e074a0b86fca09157ca9319','ee11182e257d42bb975c9438097fee95'),('a937e3aa5e074a0b86fca09157ca9319','1687ad49a63242e9b26d12e17fff5495'),('a937e3aa5e074a0b86fca09157ca9319','482ee3c8cf4848c8801bef5da21f5cc4'),('a937e3aa5e074a0b86fca09157ca9319','f34627997fd94149964168b0378a693a'),('a937e3aa5e074a0b86fca09157ca9319','b17398a68c91442abba7c0283e737280'),('a937e3aa5e074a0b86fca09157ca9319','d7c8a51bc60948febd2e76098bc99071'),('a937e3aa5e074a0b86fca09157ca9319','a6cb7cba70b54ad0b8a936d5a606fb1c'),('a937e3aa5e074a0b86fca09157ca9319','7ecbcd7dc1144e9bbb721d82a41da8ec'),('a937e3aa5e074a0b86fca09157ca9319','a0d29261715e4ad898f8e12ced27b9d6'),('a937e3aa5e074a0b86fca09157ca9319','e3bdb0a040114e98a6ef492e00f60f36'),('a937e3aa5e074a0b86fca09157ca9319','715b2bcd30c84a5b99b5ec2db68e2166'),('a937e3aa5e074a0b86fca09157ca9319','aee3cc6a93df452c8e9997b9a210d152'),('a937e3aa5e074a0b86fca09157ca9319','a4889f17a8d64a3fb0d4029d0fbe2e1d'),('a937e3aa5e074a0b86fca09157ca9319','ece677432ac745bcaf6eb0b371797ea0'),('a937e3aa5e074a0b86fca09157ca9319','61e6901878154ea8be8ebac947378bf8'),('a937e3aa5e074a0b86fca09157ca9319','12af0f9774d14f9f81ea42182876f297'),('a937e3aa5e074a0b86fca09157ca9319','849978ba9ce249a78a7d3d624b79fe3f'),('a937e3aa5e074a0b86fca09157ca9319','7fd2b5cea8a84d25aa56727bbb564b1a'),('a937e3aa5e074a0b86fca09157ca9319','7b81597909104f1e92491bb9acde1dee'),('a937e3aa5e074a0b86fca09157ca9319','e0c4c5d6616145da8013912dd8c512a6'),('a937e3aa5e074a0b86fca09157ca9319','df9e663355514da6bb34a2f073923e2e'),('a937e3aa5e074a0b86fca09157ca9319','ba3b12420492464bbe896665953056e8'),('a937e3aa5e074a0b86fca09157ca9319','5762fd8b0afa4d299f6906c6f8c68e3c'),('a937e3aa5e074a0b86fca09157ca9319','1127d5bd87814fe7b6c46e08082200b9'),('a937e3aa5e074a0b86fca09157ca9319','20e1f016fc4a4f95901fab2be6e62791'),('a937e3aa5e074a0b86fca09157ca9319','bcc2404c232345f3996065c46a0bede9'),('a937e3aa5e074a0b86fca09157ca9319','411ca5cea8414a9c8c329156a7594b14'),('a937e3aa5e074a0b86fca09157ca9319','2930af06a1af4bde8883253fdb25a1b0'),('a937e3aa5e074a0b86fca09157ca9319','6fbfa841433b4cd68b0c8d2cb9ec7b19'),('a937e3aa5e074a0b86fca09157ca9319','855266f9ba4c43818b41ceab43b851a8'),('a937e3aa5e074a0b86fca09157ca9319','e119160f758c404a8c2aff55453bf9fb'),('a937e3aa5e074a0b86fca09157ca9319','de6bcd3d3a1a4187a3d883efa3c86943'),('a937e3aa5e074a0b86fca09157ca9319','32996d89188e484894cc5cd3aeb48331'),('a937e3aa5e074a0b86fca09157ca9319','0e5a1cc1851b4fc5adab2e4df8fd9179'),('a937e3aa5e074a0b86fca09157ca9319','bc9f6369b16148508d977fc63c951a82'),('a937e3aa5e074a0b86fca09157ca9319','abf4b2990e9f4b18a636409872171509'),('a937e3aa5e074a0b86fca09157ca9319','bc3931a2a58b470f9f6f19ec5394dad5'),('a937e3aa5e074a0b86fca09157ca9319','1e31303fa9ea4b2d809e34b3fcec8a64'),('a937e3aa5e074a0b86fca09157ca9319','d71bf5725af6410a8970b34808169dfb'),('a937e3aa5e074a0b86fca09157ca9319','2896e9306e1347cfad778d471cb06a50'),('a937e3aa5e074a0b86fca09157ca9319','7ab1f391b9584a5fadfd06b6baeae4c7'),('a937e3aa5e074a0b86fca09157ca9319','36d8249922824876bdabc07c892a9cfc'),('a937e3aa5e074a0b86fca09157ca9319','140faae251f54b84979fabf62187d829'),('a937e3aa5e074a0b86fca09157ca9319','981b0671f38c4835a211ef648652b659'),('a937e3aa5e074a0b86fca09157ca9319','4ccb479b45834793a4eccf4739f0617a'),('a937e3aa5e074a0b86fca09157ca9319','1991770395df48569263225cdd741f1c'),('a937e3aa5e074a0b86fca09157ca9319','0631806d9edf4a0bbc675f260862bab4'),('a937e3aa5e074a0b86fca09157ca9319','ba796ecacfbb41f3a4685213e5b18380'),('a937e3aa5e074a0b86fca09157ca9319','76201449f9d348e08107869ca4ffb869'),('a937e3aa5e074a0b86fca09157ca9319','9ecbd017bbf549ff89ac08849d348010'),('a937e3aa5e074a0b86fca09157ca9319','6b529321690f4527bfbe80d638f9c1f3'),('a937e3aa5e074a0b86fca09157ca9319','adedb5da86184c269907c7ea24b6f73e'),('a937e3aa5e074a0b86fca09157ca9319','eb78f704d16047739749e6878ec8bdc4'),('a937e3aa5e074a0b86fca09157ca9319','fb490e890d41454da7c9d0cbf5041560'),('a937e3aa5e074a0b86fca09157ca9319','0ca6569701b840af934b5d6d38ae4097'),('a937e3aa5e074a0b86fca09157ca9319','dddf5fc6be4540f4bcb79b6f9bf8a71e'),('a937e3aa5e074a0b86fca09157ca9319','ee64d857d7144e0faaf4c6bf529d7704'),('a937e3aa5e074a0b86fca09157ca9319','d47013e699954826b13d3903f856c821'),('a937e3aa5e074a0b86fca09157ca9319','8b1d29398c8e480e893a50e2741e2e72'),('a937e3aa5e074a0b86fca09157ca9319','402459c22b7d46eab2229f47c479310f'),('a937e3aa5e074a0b86fca09157ca9319','3476ac041c284881878d9b8df4dab8b4'),('a937e3aa5e074a0b86fca09157ca9319','a7d1c610a3eb4da6b3ff246aa88eb765'),('a937e3aa5e074a0b86fca09157ca9319','c21897fc27254d4c92d9455c216abefb'),('a937e3aa5e074a0b86fca09157ca9319','5e4d15dde3c9481d8d581a739275b40d'),('a937e3aa5e074a0b86fca09157ca9319','de613ed29dae428b875cea4d93936973'),('a937e3aa5e074a0b86fca09157ca9319','4aa27cbb9f0b442dbc7634abc71875aa'),('a937e3aa5e074a0b86fca09157ca9319','d625d80589144d51a13c5ee0c091d0dc'),('a937e3aa5e074a0b86fca09157ca9319','3db17fd76f364a37be24bd72efe9dcde'),('a937e3aa5e074a0b86fca09157ca9319','f0712ecdb5e5484bb06813a64a1fb881'),('a937e3aa5e074a0b86fca09157ca9319','7a94ccd1913a4e459f51406432ea58ea'),('a937e3aa5e074a0b86fca09157ca9319','1f378d2531c04947ab1b43d0778e1eae'),('a937e3aa5e074a0b86fca09157ca9319','febf1ca1528c49daab59f8cbf698488b'),('a937e3aa5e074a0b86fca09157ca9319','d9abc363306f4d5fbef97a8f7a6cf4de'),('a937e3aa5e074a0b86fca09157ca9319','4cc2c6a4d9104bae876e7632f025be3d'),('a937e3aa5e074a0b86fca09157ca9319','d19f2346a5084b5e95308abc7654608b'),('a937e3aa5e074a0b86fca09157ca9319','a7c3c57335144e43be4e041202419d78'),('a937e3aa5e074a0b86fca09157ca9319','ef7db3ea2b0d4fac99302cb6c95b18ca'),('a937e3aa5e074a0b86fca09157ca9319','8102dca1971f41e583bc2af4229ea55a'),('a937e3aa5e074a0b86fca09157ca9319','50a4b244620a4ec5acedbb580543a79e'),('a937e3aa5e074a0b86fca09157ca9319','7cf34691816746e390e0191bf3a54c03'),('a937e3aa5e074a0b86fca09157ca9319','017d13cdc23d48118ac82940ac3ef105'),('a937e3aa5e074a0b86fca09157ca9319','0c33da3e9212406ab8ffe4d8f1674baf'),('a937e3aa5e074a0b86fca09157ca9319','9adbc1e74d7e4b589d8ccf90a70144d6'),('a937e3aa5e074a0b86fca09157ca9319','ded16b4a63cf42bf86b5626620abbe0a'),('a937e3aa5e074a0b86fca09157ca9319','ae17ba0fcce64b46a780ea329e052f3b'),('a937e3aa5e074a0b86fca09157ca9319','5490ca2f28ad421b80145eaf96abdc7c'),('a937e3aa5e074a0b86fca09157ca9319','2f848a43ec394c4b96cc7241d2b06381'),('a937e3aa5e074a0b86fca09157ca9319','d3dc1eb066de481587131ab048b3f6e2'),('a937e3aa5e074a0b86fca09157ca9319','4381af82a2a2446495fba3eb146d7f61'),('a937e3aa5e074a0b86fca09157ca9319','99abc5f7fbb34f24bc9c03e5fbbe3054'),('a937e3aa5e074a0b86fca09157ca9319','f587bcfe0962434b8e1a7f721531ba65'),('a937e3aa5e074a0b86fca09157ca9319','6f27d3cecf78457e98387023faf12a72'),('a937e3aa5e074a0b86fca09157ca9319','8cd1a796fca24dd691c0add6a00a941b'),('a937e3aa5e074a0b86fca09157ca9319','6f67fff3e7944a8599ca847b4521f5c1'),('a937e3aa5e074a0b86fca09157ca9319','1c7b1c927b464af58f3cc0b2ad8b69b0'),('a937e3aa5e074a0b86fca09157ca9319','dbff508e055442a3bf87dafeee794f76'),('a937e3aa5e074a0b86fca09157ca9319','99127e7a2a8748e8b3bae275e238cf5e'),('a937e3aa5e074a0b86fca09157ca9319','a3501a5974f34625aacddb61a10f426e'),('a937e3aa5e074a0b86fca09157ca9319','7359b29c6fe24171863b02f49a266eae'),('a937e3aa5e074a0b86fca09157ca9319','942d0029927640008a79322d26d09c4d'),('a937e3aa5e074a0b86fca09157ca9319','df2a1743988640ab8a78377e18b785c2'),('a937e3aa5e074a0b86fca09157ca9319','c2f2cedc581b48429cb01df2ec0f9f49'),('a937e3aa5e074a0b86fca09157ca9319','0d863c2ea36744539f1caa50d6ea698f'),('a937e3aa5e074a0b86fca09157ca9319','5f1afd602a154b1bb43c840ab2f8ee27'),('a937e3aa5e074a0b86fca09157ca9319','921a064041fb44db86cc18ca04c6166b'),('a937e3aa5e074a0b86fca09157ca9319','8dea6d33e18a434f8b44d884cf68331c'),('a937e3aa5e074a0b86fca09157ca9319','ea89da74b98947a3bc6f5d9e2c27a316'),('a937e3aa5e074a0b86fca09157ca9319','748db7f9a6824db9885533960ba20976'),('a937e3aa5e074a0b86fca09157ca9319','fe2464d65b95457c914bbd881322442c'),('a937e3aa5e074a0b86fca09157ca9319','92a62ac43231494abc0b2bba55e8f253'),('a937e3aa5e074a0b86fca09157ca9319','245f29d15f3645f18e9c9ee4001a8861'),('a937e3aa5e074a0b86fca09157ca9319','80480cc0f9054515aa818fe5d485d34d'),('a937e3aa5e074a0b86fca09157ca9319','5f9d78463d184ca791e42c2bb5883045'),('a937e3aa5e074a0b86fca09157ca9319','60bf32d0d88e4fe3b9282667c07e2ccc'),('a937e3aa5e074a0b86fca09157ca9319','64b8913e93ed44a998a42aaeb2e68a82'),('a937e3aa5e074a0b86fca09157ca9319','7655660af40e4d13981b7db25abfbd79'),('a937e3aa5e074a0b86fca09157ca9319','44009964689a43dcb4f57ce55a0df476'),('a937e3aa5e074a0b86fca09157ca9319','140e6d10c25643d7b3ea777d0f3a6f1c'),('a937e3aa5e074a0b86fca09157ca9319','584b310404e14061a7e79110fd1f2cc5'),('a937e3aa5e074a0b86fca09157ca9319','eec382736e0646d79392f43f61c17ffc'),('a937e3aa5e074a0b86fca09157ca9319','429c104c6f6e4c04a4d0a68672203015'),('a937e3aa5e074a0b86fca09157ca9319','2b2b459d96f14aa0a6196cd0c8bb591c'),('a937e3aa5e074a0b86fca09157ca9319','53791e80fae744f795fd94066a76b166'),('a937e3aa5e074a0b86fca09157ca9319','3d592eb4e61e4709a1b2d5fab58463a3'),('a937e3aa5e074a0b86fca09157ca9319','7df81e6ade0e4d1fbe5e817a55c81637'),('a937e3aa5e074a0b86fca09157ca9319','f7b957896ec3429ab1e89a4dfc0c5ad4'),('791cece1cbc047a2af788950a3c9e93e','2f848a43ec394c4b96cc7241d2b06381'),('791cece1cbc047a2af788950a3c9e93e','5490ca2f28ad421b80145eaf96abdc7c'),('791cece1cbc047a2af788950a3c9e93e','ae17ba0fcce64b46a780ea329e052f3b'),('791cece1cbc047a2af788950a3c9e93e','4b133273aba543878e0f132f69e47457'),('791cece1cbc047a2af788950a3c9e93e','3e6d3fb5bc234feebba82a15bdfa2ef9'),('791cece1cbc047a2af788950a3c9e93e','d81c017d173648e2aa8ccd8ac79230d2'),('791cece1cbc047a2af788950a3c9e93e','be0363c47ee047cab3c6b47629c51a79'),('791cece1cbc047a2af788950a3c9e93e','4381af82a2a2446495fba3eb146d7f61'),('791cece1cbc047a2af788950a3c9e93e','d3dc1eb066de481587131ab048b3f6e2'),('791cece1cbc047a2af788950a3c9e93e','48ed3df76ded491ebadd737527160744'),('791cece1cbc047a2af788950a3c9e93e','b487e8b82b0e4d3f887df02f89d37507'),('791cece1cbc047a2af788950a3c9e93e','32a6986d1b1f45a48c7789ec2112169c'),('791cece1cbc047a2af788950a3c9e93e','e02c779c7e50419d8cc086250bc38264'),('791cece1cbc047a2af788950a3c9e93e','6d5f86d6d04f4d8298334993d68a2337'),('791cece1cbc047a2af788950a3c9e93e','99abc5f7fbb34f24bc9c03e5fbbe3054'),('791cece1cbc047a2af788950a3c9e93e','a59bfc8ca6844149bd532c7d0d494d9f'),('791cece1cbc047a2af788950a3c9e93e','a7b6b1d4e3a54b698ec84eec3ed52808'),('791cece1cbc047a2af788950a3c9e93e','4c40c6049e914dfc93f897f3231ccc73'),('791cece1cbc047a2af788950a3c9e93e','f587bcfe0962434b8e1a7f721531ba65'),('791cece1cbc047a2af788950a3c9e93e','c8809a8735864b0ba52591a58f2b1375'),('791cece1cbc047a2af788950a3c9e93e','8a387fb5ff3948b094068e6f75d7387a'),('791cece1cbc047a2af788950a3c9e93e','482c744503d548ad826e784e00c04098'),('791cece1cbc047a2af788950a3c9e93e','7d938aa5abd84415ad2d648a29e11b8b'),('791cece1cbc047a2af788950a3c9e93e','e7806116788746fb8381184f0e8938ac'),('791cece1cbc047a2af788950a3c9e93e','d2feef5baba2426f97643bd9a9e586fb'),('791cece1cbc047a2af788950a3c9e93e','6f27d3cecf78457e98387023faf12a72'),('791cece1cbc047a2af788950a3c9e93e','43efc7afc83249c3b4875fc4860f3aca'),('791cece1cbc047a2af788950a3c9e93e','88e8ec8b29624f15a5fb5130696568f3'),('791cece1cbc047a2af788950a3c9e93e','1e5687528e264274ace41a50ed0e64e2'),('791cece1cbc047a2af788950a3c9e93e','8fe0340021da48c999d0f8292dbb29b2'),('791cece1cbc047a2af788950a3c9e93e','dfc098f0925046339d4906ee013ef2fb'),('791cece1cbc047a2af788950a3c9e93e','59e62054e822406c88e7db4af7ed8079'),('791cece1cbc047a2af788950a3c9e93e','6f67fff3e7944a8599ca847b4521f5c1'),('791cece1cbc047a2af788950a3c9e93e','8cd1a796fca24dd691c0add6a00a941b'),('791cece1cbc047a2af788950a3c9e93e','d3b283908e2c4507be159ba2c776a9f9'),('791cece1cbc047a2af788950a3c9e93e','060e4aa715d844168c0d1639962a1c8e'),('791cece1cbc047a2af788950a3c9e93e','8d7fd658c1e34a30b21aeb4296714cc3'),('791cece1cbc047a2af788950a3c9e93e','1c7b1c927b464af58f3cc0b2ad8b69b0'),('791cece1cbc047a2af788950a3c9e93e','093a17c9d2944793a4e5e364299353fa'),('791cece1cbc047a2af788950a3c9e93e','c84e77edf76e4b1891943e4bf8f8b516'),('791cece1cbc047a2af788950a3c9e93e','fde039f39db840ca89987119198db229'),('791cece1cbc047a2af788950a3c9e93e','99127e7a2a8748e8b3bae275e238cf5e'),('791cece1cbc047a2af788950a3c9e93e','dbff508e055442a3bf87dafeee794f76'),('791cece1cbc047a2af788950a3c9e93e','51ba669f420b425e9d00538be47ab7a2'),('791cece1cbc047a2af788950a3c9e93e','1ab1f849dbd3484a85ec5609b9d8e61a'),('791cece1cbc047a2af788950a3c9e93e','5829d16c03db45baac75c5e987be1506'),('791cece1cbc047a2af788950a3c9e93e','7359b29c6fe24171863b02f49a266eae'),('791cece1cbc047a2af788950a3c9e93e','a3501a5974f34625aacddb61a10f426e'),('791cece1cbc047a2af788950a3c9e93e','8f7f347ae9eb40edbca08e31f7998f9d'),('791cece1cbc047a2af788950a3c9e93e','f6bdd58aefbc43f1b79e2886aacc7028'),('791cece1cbc047a2af788950a3c9e93e','fbd34327c5674ec3aca2cfc089089ff4'),('791cece1cbc047a2af788950a3c9e93e','942d0029927640008a79322d26d09c4d'),('791cece1cbc047a2af788950a3c9e93e','0f0285227e654659998be03d197cc48c'),('791cece1cbc047a2af788950a3c9e93e','32cd2fe478644290a112bafe6a92965c'),('791cece1cbc047a2af788950a3c9e93e','d470271109754506a5ffba4ff3f19ba1'),('791cece1cbc047a2af788950a3c9e93e','4cc19c89acf349349b8065f06d703113'),('791cece1cbc047a2af788950a3c9e93e','485c764b94c447b19ee4ac25e624cc5c'),('791cece1cbc047a2af788950a3c9e93e','4f6b3451e57d4f26a9ca4c0904370ef2'),('791cece1cbc047a2af788950a3c9e93e','b81188925c684908b7c258235865be3a'),('791cece1cbc047a2af788950a3c9e93e','5642e30d6f1042f7ba8d4ac928514c47'),('791cece1cbc047a2af788950a3c9e93e','df2a1743988640ab8a78377e18b785c2'),('791cece1cbc047a2af788950a3c9e93e','7e0bb38e446e4f58a2925c359e2ce3f4'),('791cece1cbc047a2af788950a3c9e93e','707a5a0e26244a44bdce0148b618de88'),('791cece1cbc047a2af788950a3c9e93e','c2f2cedc581b48429cb01df2ec0f9f49'),('791cece1cbc047a2af788950a3c9e93e','b44cde53b2014f9fb2dd45d98219ed61'),('791cece1cbc047a2af788950a3c9e93e','a05e37395c97484b9dac1edbad912c32'),('791cece1cbc047a2af788950a3c9e93e','a0a0db6e4ae04c4ea142c51970d011a9'),('791cece1cbc047a2af788950a3c9e93e','5f1afd602a154b1bb43c840ab2f8ee27'),('791cece1cbc047a2af788950a3c9e93e','0d863c2ea36744539f1caa50d6ea698f'),('791cece1cbc047a2af788950a3c9e93e','43c4bc1f77564b3680be836b18b9acc4'),('791cece1cbc047a2af788950a3c9e93e','c372d1b975c84e77a463f277bfb3180b'),('791cece1cbc047a2af788950a3c9e93e','921a064041fb44db86cc18ca04c6166b'),('791cece1cbc047a2af788950a3c9e93e','25e9c554b2b94c0c906d36e1337512a1'),('791cece1cbc047a2af788950a3c9e93e','8dea6d33e18a434f8b44d884cf68331c'),('791cece1cbc047a2af788950a3c9e93e','aed9a1f8730e4bcd962127f64fd7c4f2'),('791cece1cbc047a2af788950a3c9e93e','ea89da74b98947a3bc6f5d9e2c27a316'),('791cece1cbc047a2af788950a3c9e93e','1c2d18b718d248168049f07cdf363cf2'),('791cece1cbc047a2af788950a3c9e93e','2b40aede791a4802b2decf0339252fe4'),('8dbcb08a74fb4f458ba43474f9b3f8ac','0a6e029ddaa94c7fae7b0cab1f64f8da'),('8dbcb08a74fb4f458ba43474f9b3f8ac','04be9536b3fc4797b09877d2ac864f6d'),('8dbcb08a74fb4f458ba43474f9b3f8ac','01574e284bd7493cb2dd65240603ef0a'),('8dbcb08a74fb4f458ba43474f9b3f8ac','75b5a8bf7c324760be57e4b3ffc0b015'),('8dbcb08a74fb4f458ba43474f9b3f8ac','b561ae6ebb1a4e57a1ab6d2dc30d8da2'),('8dbcb08a74fb4f458ba43474f9b3f8ac','192cc19988df4872b38882cae9967c6a'),('8dbcb08a74fb4f458ba43474f9b3f8ac','40ccdaad894d4ed48a1c0082f8a19d00'),('8dbcb08a74fb4f458ba43474f9b3f8ac','6da598ce6bba48069b39b0db06a0837c'),('8dbcb08a74fb4f458ba43474f9b3f8ac','ee37dbb88f00473e8881106c7da1a46e'),('8dbcb08a74fb4f458ba43474f9b3f8ac','36d8249922824876bdabc07c892a9cfc'),('8dbcb08a74fb4f458ba43474f9b3f8ac','4ccb479b45834793a4eccf4739f0617a'),('8dbcb08a74fb4f458ba43474f9b3f8ac','0631806d9edf4a0bbc675f260862bab4'),('8dbcb08a74fb4f458ba43474f9b3f8ac','ba796ecacfbb41f3a4685213e5b18380'),('8dbcb08a74fb4f458ba43474f9b3f8ac','76201449f9d348e08107869ca4ffb869'),('8dbcb08a74fb4f458ba43474f9b3f8ac','adedb5da86184c269907c7ea24b6f73e'),('8dbcb08a74fb4f458ba43474f9b3f8ac','eb78f704d16047739749e6878ec8bdc4'),('8dbcb08a74fb4f458ba43474f9b3f8ac','fb490e890d41454da7c9d0cbf5041560'),('8dbcb08a74fb4f458ba43474f9b3f8ac','dddf5fc6be4540f4bcb79b6f9bf8a71e'),('8dbcb08a74fb4f458ba43474f9b3f8ac','ee64d857d7144e0faaf4c6bf529d7704'),('8dbcb08a74fb4f458ba43474f9b3f8ac','d47013e699954826b13d3903f856c821'),('8dbcb08a74fb4f458ba43474f9b3f8ac','402459c22b7d46eab2229f47c479310f'),('8dbcb08a74fb4f458ba43474f9b3f8ac','3476ac041c284881878d9b8df4dab8b4'),('8dbcb08a74fb4f458ba43474f9b3f8ac','a7d1c610a3eb4da6b3ff246aa88eb765'),('8dbcb08a74fb4f458ba43474f9b3f8ac','5e4d15dde3c9481d8d581a739275b40d'),('8dbcb08a74fb4f458ba43474f9b3f8ac','de613ed29dae428b875cea4d93936973'),('8dbcb08a74fb4f458ba43474f9b3f8ac','4aa27cbb9f0b442dbc7634abc71875aa'),('8dbcb08a74fb4f458ba43474f9b3f8ac','f0712ecdb5e5484bb06813a64a1fb881'),('8dbcb08a74fb4f458ba43474f9b3f8ac','7a94ccd1913a4e459f51406432ea58ea'),('8dbcb08a74fb4f458ba43474f9b3f8ac','1f378d2531c04947ab1b43d0778e1eae'),('8dbcb08a74fb4f458ba43474f9b3f8ac','d9abc363306f4d5fbef97a8f7a6cf4de'),('8dbcb08a74fb4f458ba43474f9b3f8ac','4cc2c6a4d9104bae876e7632f025be3d'),('8dbcb08a74fb4f458ba43474f9b3f8ac','d19f2346a5084b5e95308abc7654608b'),('8dbcb08a74fb4f458ba43474f9b3f8ac','a7c3c57335144e43be4e041202419d78'),('8dbcb08a74fb4f458ba43474f9b3f8ac','ef7db3ea2b0d4fac99302cb6c95b18ca'),('8dbcb08a74fb4f458ba43474f9b3f8ac','7cf34691816746e390e0191bf3a54c03'),('8dbcb08a74fb4f458ba43474f9b3f8ac','017d13cdc23d48118ac82940ac3ef105'),('8dbcb08a74fb4f458ba43474f9b3f8ac','9adbc1e74d7e4b589d8ccf90a70144d6'),('8dbcb08a74fb4f458ba43474f9b3f8ac','ded16b4a63cf42bf86b5626620abbe0a'),('8dbcb08a74fb4f458ba43474f9b3f8ac','4b133273aba543878e0f132f69e47457'),('8dbcb08a74fb4f458ba43474f9b3f8ac','3e6d3fb5bc234feebba82a15bdfa2ef9'),('8dbcb08a74fb4f458ba43474f9b3f8ac','d81c017d173648e2aa8ccd8ac79230d2'),('8dbcb08a74fb4f458ba43474f9b3f8ac','be0363c47ee047cab3c6b47629c51a79'),('8dbcb08a74fb4f458ba43474f9b3f8ac','48ed3df76ded491ebadd737527160744'),('8dbcb08a74fb4f458ba43474f9b3f8ac','b487e8b82b0e4d3f887df02f89d37507'),('8dbcb08a74fb4f458ba43474f9b3f8ac','32a6986d1b1f45a48c7789ec2112169c'),('8dbcb08a74fb4f458ba43474f9b3f8ac','e02c779c7e50419d8cc086250bc38264'),('8dbcb08a74fb4f458ba43474f9b3f8ac','6d5f86d6d04f4d8298334993d68a2337'),('8dbcb08a74fb4f458ba43474f9b3f8ac','a59bfc8ca6844149bd532c7d0d494d9f'),('8dbcb08a74fb4f458ba43474f9b3f8ac','a7b6b1d4e3a54b698ec84eec3ed52808'),('8dbcb08a74fb4f458ba43474f9b3f8ac','4c40c6049e914dfc93f897f3231ccc73'),('8dbcb08a74fb4f458ba43474f9b3f8ac','c8809a8735864b0ba52591a58f2b1375'),('8dbcb08a74fb4f458ba43474f9b3f8ac','8a387fb5ff3948b094068e6f75d7387a'),('8dbcb08a74fb4f458ba43474f9b3f8ac','482c744503d548ad826e784e00c04098'),('8dbcb08a74fb4f458ba43474f9b3f8ac','7d938aa5abd84415ad2d648a29e11b8b'),('8dbcb08a74fb4f458ba43474f9b3f8ac','e7806116788746fb8381184f0e8938ac'),('8dbcb08a74fb4f458ba43474f9b3f8ac','d2feef5baba2426f97643bd9a9e586fb'),('8dbcb08a74fb4f458ba43474f9b3f8ac','43efc7afc83249c3b4875fc4860f3aca'),('8dbcb08a74fb4f458ba43474f9b3f8ac','88e8ec8b29624f15a5fb5130696568f3'),('8dbcb08a74fb4f458ba43474f9b3f8ac','1e5687528e264274ace41a50ed0e64e2'),('8dbcb08a74fb4f458ba43474f9b3f8ac','8fe0340021da48c999d0f8292dbb29b2'),('8dbcb08a74fb4f458ba43474f9b3f8ac','dfc098f0925046339d4906ee013ef2fb'),('8dbcb08a74fb4f458ba43474f9b3f8ac','59e62054e822406c88e7db4af7ed8079'),('8dbcb08a74fb4f458ba43474f9b3f8ac','d3b283908e2c4507be159ba2c776a9f9'),('8dbcb08a74fb4f458ba43474f9b3f8ac','060e4aa715d844168c0d1639962a1c8e'),('8dbcb08a74fb4f458ba43474f9b3f8ac','8d7fd658c1e34a30b21aeb4296714cc3'),('8dbcb08a74fb4f458ba43474f9b3f8ac','093a17c9d2944793a4e5e364299353fa'),('8dbcb08a74fb4f458ba43474f9b3f8ac','c84e77edf76e4b1891943e4bf8f8b516'),('8dbcb08a74fb4f458ba43474f9b3f8ac','fde039f39db840ca89987119198db229'),('8dbcb08a74fb4f458ba43474f9b3f8ac','51ba669f420b425e9d00538be47ab7a2'),('8dbcb08a74fb4f458ba43474f9b3f8ac','1ab1f849dbd3484a85ec5609b9d8e61a'),('8dbcb08a74fb4f458ba43474f9b3f8ac','5829d16c03db45baac75c5e987be1506'),('8dbcb08a74fb4f458ba43474f9b3f8ac','8f7f347ae9eb40edbca08e31f7998f9d'),('8dbcb08a74fb4f458ba43474f9b3f8ac','f6bdd58aefbc43f1b79e2886aacc7028'),('8dbcb08a74fb4f458ba43474f9b3f8ac','fbd34327c5674ec3aca2cfc089089ff4'),('8dbcb08a74fb4f458ba43474f9b3f8ac','0f0285227e654659998be03d197cc48c'),('8dbcb08a74fb4f458ba43474f9b3f8ac','32cd2fe478644290a112bafe6a92965c'),('8dbcb08a74fb4f458ba43474f9b3f8ac','d470271109754506a5ffba4ff3f19ba1'),('8dbcb08a74fb4f458ba43474f9b3f8ac','4cc19c89acf349349b8065f06d703113'),('8dbcb08a74fb4f458ba43474f9b3f8ac','485c764b94c447b19ee4ac25e624cc5c'),('8dbcb08a74fb4f458ba43474f9b3f8ac','4f6b3451e57d4f26a9ca4c0904370ef2'),('8dbcb08a74fb4f458ba43474f9b3f8ac','b81188925c684908b7c258235865be3a'),('8dbcb08a74fb4f458ba43474f9b3f8ac','5642e30d6f1042f7ba8d4ac928514c47'),('8dbcb08a74fb4f458ba43474f9b3f8ac','7e0bb38e446e4f58a2925c359e2ce3f4'),('8dbcb08a74fb4f458ba43474f9b3f8ac','707a5a0e26244a44bdce0148b618de88'),('8dbcb08a74fb4f458ba43474f9b3f8ac','b44cde53b2014f9fb2dd45d98219ed61'),('8dbcb08a74fb4f458ba43474f9b3f8ac','a05e37395c97484b9dac1edbad912c32'),('8dbcb08a74fb4f458ba43474f9b3f8ac','a0a0db6e4ae04c4ea142c51970d011a9'),('8dbcb08a74fb4f458ba43474f9b3f8ac','43c4bc1f77564b3680be836b18b9acc4'),('8dbcb08a74fb4f458ba43474f9b3f8ac','c372d1b975c84e77a463f277bfb3180b'),('8dbcb08a74fb4f458ba43474f9b3f8ac','25e9c554b2b94c0c906d36e1337512a1'),('8dbcb08a74fb4f458ba43474f9b3f8ac','aed9a1f8730e4bcd962127f64fd7c4f2'),('8dbcb08a74fb4f458ba43474f9b3f8ac','1c2d18b718d248168049f07cdf363cf2'),('8dbcb08a74fb4f458ba43474f9b3f8ac','2b40aede791a4802b2decf0339252fe4'),('8dbcb08a74fb4f458ba43474f9b3f8ac','e0f585e923f34ce48bb735b0895ea43d'),('8dbcb08a74fb4f458ba43474f9b3f8ac','cf068f43ff5344dd8fcf5e4facdfdd1e'),('8dbcb08a74fb4f458ba43474f9b3f8ac','c9b2a5b8aefe4148a5b7b66a4aeda820'),('8dbcb08a74fb4f458ba43474f9b3f8ac','7c58f800dd9c429fbcc22713465db866'),('8dbcb08a74fb4f458ba43474f9b3f8ac','a26bf4b0f0134e8e8095c52dbf5d4580'),('8dbcb08a74fb4f458ba43474f9b3f8ac','9abf17f0b2064b60987c5e063ce9e562'),('8dbcb08a74fb4f458ba43474f9b3f8ac','4f2d05978bb34e0bb284bed84b165b84'),('8dbcb08a74fb4f458ba43474f9b3f8ac','9686ae8ae8e74b73913fa0bf5968445d'),('8dbcb08a74fb4f458ba43474f9b3f8ac','93852750a1f541e4bb775938d78cd7e9'),('8dbcb08a74fb4f458ba43474f9b3f8ac','4fa293ab334e44e3b630e2bf20c1977c'),('8dbcb08a74fb4f458ba43474f9b3f8ac','0120dfd1852e4bb1a905216b7db579a5'),('8dbcb08a74fb4f458ba43474f9b3f8ac','497f384e6bf647c1b25d3db934b07f93'),('8dbcb08a74fb4f458ba43474f9b3f8ac','e9458f7718fc4c468a5b7b66df3def75'),('8dbcb08a74fb4f458ba43474f9b3f8ac','55dbc91b769f4af9af659e34ce40e26e'),('8dbcb08a74fb4f458ba43474f9b3f8ac','cb1c1a8f1a0d43c1b9c24c5321448296'),('8dbcb08a74fb4f458ba43474f9b3f8ac','c0916d2a1513492c9ad2a19f3b042ebe'),('8dbcb08a74fb4f458ba43474f9b3f8ac','a861fcc6874b467a8c0a6213319db1e3'),('8dbcb08a74fb4f458ba43474f9b3f8ac','8144c56f687a45ffa43fe6aa33af7026'),('8dbcb08a74fb4f458ba43474f9b3f8ac','47746c7b0b4c4157838a2f42796187d2'),('8dbcb08a74fb4f458ba43474f9b3f8ac','35bf6b0aadcd42f0861e00dcaeb8b057'),('8dbcb08a74fb4f458ba43474f9b3f8ac','478c5451273443c38c8bc7a4f3b05856'),('8dbcb08a74fb4f458ba43474f9b3f8ac','a806900609dc4a50baf5da208812cf2b'),('8dbcb08a74fb4f458ba43474f9b3f8ac','ec0bcc3a3d5e4854a03289ebe6b5733d'),('8dbcb08a74fb4f458ba43474f9b3f8ac','9184ef1c590b471fa071b7b7c2d5a934'),('8dbcb08a74fb4f458ba43474f9b3f8ac','8dcd9f27c9414f44b80b7864907e7057'),('8dbcb08a74fb4f458ba43474f9b3f8ac','b2a84baec72c46779f3f7743abeede51'),('8dbcb08a74fb4f458ba43474f9b3f8ac','bc25d55cb1cc41409e8428fe15a91b61'),('8dbcb08a74fb4f458ba43474f9b3f8ac','6d36a15e774b4173a0dd41ed86006d8b'),('8dbcb08a74fb4f458ba43474f9b3f8ac','3c605075d1e34cf5a095fe87a1deb4bf'),('8dbcb08a74fb4f458ba43474f9b3f8ac','582ca649ea1e4b2eaa008f5f96978195'),('8dbcb08a74fb4f458ba43474f9b3f8ac','11547c7f93b44dcd92d31f6fd2fcec9c'),('8dbcb08a74fb4f458ba43474f9b3f8ac','2f906103f48340419a8bab96899b7d82'),('8dbcb08a74fb4f458ba43474f9b3f8ac','0ddc69960e4e4912adad5cf245e288d9'),('8dbcb08a74fb4f458ba43474f9b3f8ac','bbc135b5efef4e8484576126e5e4f9c4'),('8dbcb08a74fb4f458ba43474f9b3f8ac','5950f4bd46054157b910a3d45ffadf09'),('8dbcb08a74fb4f458ba43474f9b3f8ac','5b7414e49e4d4e1d80463bf8714bfa41'),('8dbcb08a74fb4f458ba43474f9b3f8ac','5e84be56a3274b24b625624e89408627'),('8dbcb08a74fb4f458ba43474f9b3f8ac','bf5667954ffc42ccbad0944e8e562928'),('8dbcb08a74fb4f458ba43474f9b3f8ac','b0193970d4c6486ea6e5e51cdd8fe581'),('8dbcb08a74fb4f458ba43474f9b3f8ac','713b83e55ecb4b67a65492bba530f170'),('8dbcb08a74fb4f458ba43474f9b3f8ac','51097a633ea246aca36a0bed9a9ea59a'),('8dbcb08a74fb4f458ba43474f9b3f8ac','7ab1f391b9584a5fadfd06b6baeae4c7'),('8dbcb08a74fb4f458ba43474f9b3f8ac','2896e9306e1347cfad778d471cb06a50'),('8dbcb08a74fb4f458ba43474f9b3f8ac','d71bf5725af6410a8970b34808169dfb'),('8dbcb08a74fb4f458ba43474f9b3f8ac','981b0671f38c4835a211ef648652b659'),('8dbcb08a74fb4f458ba43474f9b3f8ac','140faae251f54b84979fabf62187d829'),('8dbcb08a74fb4f458ba43474f9b3f8ac','1991770395df48569263225cdd741f1c'),('8dbcb08a74fb4f458ba43474f9b3f8ac','6b529321690f4527bfbe80d638f9c1f3'),('8dbcb08a74fb4f458ba43474f9b3f8ac','9ecbd017bbf549ff89ac08849d348010'),('8dbcb08a74fb4f458ba43474f9b3f8ac','0ca6569701b840af934b5d6d38ae4097'),('8dbcb08a74fb4f458ba43474f9b3f8ac','8b1d29398c8e480e893a50e2741e2e72'),('8dbcb08a74fb4f458ba43474f9b3f8ac','c21897fc27254d4c92d9455c216abefb'),('8dbcb08a74fb4f458ba43474f9b3f8ac','3db17fd76f364a37be24bd72efe9dcde'),('8dbcb08a74fb4f458ba43474f9b3f8ac','d625d80589144d51a13c5ee0c091d0dc'),('8dbcb08a74fb4f458ba43474f9b3f8ac','febf1ca1528c49daab59f8cbf698488b'),('8dbcb08a74fb4f458ba43474f9b3f8ac','50a4b244620a4ec5acedbb580543a79e'),('8dbcb08a74fb4f458ba43474f9b3f8ac','8102dca1971f41e583bc2af4229ea55a'),('8dbcb08a74fb4f458ba43474f9b3f8ac','0c33da3e9212406ab8ffe4d8f1674baf'),('8dbcb08a74fb4f458ba43474f9b3f8ac','ae17ba0fcce64b46a780ea329e052f3b'),('8dbcb08a74fb4f458ba43474f9b3f8ac','5490ca2f28ad421b80145eaf96abdc7c'),('8dbcb08a74fb4f458ba43474f9b3f8ac','2f848a43ec394c4b96cc7241d2b06381'),('8dbcb08a74fb4f458ba43474f9b3f8ac','d3dc1eb066de481587131ab048b3f6e2'),('8dbcb08a74fb4f458ba43474f9b3f8ac','4381af82a2a2446495fba3eb146d7f61'),('8dbcb08a74fb4f458ba43474f9b3f8ac','99abc5f7fbb34f24bc9c03e5fbbe3054'),('8dbcb08a74fb4f458ba43474f9b3f8ac','f587bcfe0962434b8e1a7f721531ba65'),('8dbcb08a74fb4f458ba43474f9b3f8ac','6f27d3cecf78457e98387023faf12a72'),('8dbcb08a74fb4f458ba43474f9b3f8ac','8cd1a796fca24dd691c0add6a00a941b'),('8dbcb08a74fb4f458ba43474f9b3f8ac','6f67fff3e7944a8599ca847b4521f5c1'),('8dbcb08a74fb4f458ba43474f9b3f8ac','1c7b1c927b464af58f3cc0b2ad8b69b0'),('8dbcb08a74fb4f458ba43474f9b3f8ac','dbff508e055442a3bf87dafeee794f76'),('8dbcb08a74fb4f458ba43474f9b3f8ac','99127e7a2a8748e8b3bae275e238cf5e'),('8dbcb08a74fb4f458ba43474f9b3f8ac','a3501a5974f34625aacddb61a10f426e'),('8dbcb08a74fb4f458ba43474f9b3f8ac','7359b29c6fe24171863b02f49a266eae'),('8dbcb08a74fb4f458ba43474f9b3f8ac','942d0029927640008a79322d26d09c4d'),('8dbcb08a74fb4f458ba43474f9b3f8ac','df2a1743988640ab8a78377e18b785c2'),('8dbcb08a74fb4f458ba43474f9b3f8ac','c2f2cedc581b48429cb01df2ec0f9f49'),('8dbcb08a74fb4f458ba43474f9b3f8ac','0d863c2ea36744539f1caa50d6ea698f'),('8dbcb08a74fb4f458ba43474f9b3f8ac','5f1afd602a154b1bb43c840ab2f8ee27'),('8dbcb08a74fb4f458ba43474f9b3f8ac','921a064041fb44db86cc18ca04c6166b'),('8dbcb08a74fb4f458ba43474f9b3f8ac','8dea6d33e18a434f8b44d884cf68331c'),('8dbcb08a74fb4f458ba43474f9b3f8ac','ea89da74b98947a3bc6f5d9e2c27a316'),('8dbcb08a74fb4f458ba43474f9b3f8ac','4b4109f0952e46be8ac5a78c6bde4ddb'),('8dbcb08a74fb4f458ba43474f9b3f8ac','2cfdc33046d140a18b318f7870a0f73d'),('8dbcb08a74fb4f458ba43474f9b3f8ac','81267f0d8d84492eaa07adedf14686d6'),('8dbcb08a74fb4f458ba43474f9b3f8ac','56585a9a5d3f4f5faf6fc9b399090e2a'),('8dbcb08a74fb4f458ba43474f9b3f8ac','9723f56569324c22bc01d431ca89354c'),('8dbcb08a74fb4f458ba43474f9b3f8ac','45533f4a901941be966cd8ee56db8623'),('8dbcb08a74fb4f458ba43474f9b3f8ac','d58a4bf922ec42729d822770107668c1'),('8dbcb08a74fb4f458ba43474f9b3f8ac','d7de61d2d6f049ad87e1491565a3e39d'),('8dbcb08a74fb4f458ba43474f9b3f8ac','657d53d3665049d781d340db0a3e4bc7'),('8dbcb08a74fb4f458ba43474f9b3f8ac','1b584cd4075c4d9eac6972c233437479'),('8dbcb08a74fb4f458ba43474f9b3f8ac','1c8e4461a938442ba371b46cd5b577f9'),('8dbcb08a74fb4f458ba43474f9b3f8ac','cb28f0158cd240aebf11598eb490c951'),('8dbcb08a74fb4f458ba43474f9b3f8ac','c48afe87e7db4cf4a9926fa52a99ba3b'),('2ed3dd76c3ad4f98886d725bd8a15d6d','36d8249922824876bdabc07c892a9cfc'),('2ed3dd76c3ad4f98886d725bd8a15d6d','4ccb479b45834793a4eccf4739f0617a'),('2ed3dd76c3ad4f98886d725bd8a15d6d','0631806d9edf4a0bbc675f260862bab4'),('2ed3dd76c3ad4f98886d725bd8a15d6d','ba796ecacfbb41f3a4685213e5b18380'),('2ed3dd76c3ad4f98886d725bd8a15d6d','76201449f9d348e08107869ca4ffb869'),('2ed3dd76c3ad4f98886d725bd8a15d6d','adedb5da86184c269907c7ea24b6f73e'),('2ed3dd76c3ad4f98886d725bd8a15d6d','eb78f704d16047739749e6878ec8bdc4'),('2ed3dd76c3ad4f98886d725bd8a15d6d','fb490e890d41454da7c9d0cbf5041560'),('2ed3dd76c3ad4f98886d725bd8a15d6d','dddf5fc6be4540f4bcb79b6f9bf8a71e'),('2ed3dd76c3ad4f98886d725bd8a15d6d','ee64d857d7144e0faaf4c6bf529d7704'),('2ed3dd76c3ad4f98886d725bd8a15d6d','d47013e699954826b13d3903f856c821'),('2ed3dd76c3ad4f98886d725bd8a15d6d','402459c22b7d46eab2229f47c479310f'),('2ed3dd76c3ad4f98886d725bd8a15d6d','3476ac041c284881878d9b8df4dab8b4'),('2ed3dd76c3ad4f98886d725bd8a15d6d','a7d1c610a3eb4da6b3ff246aa88eb765'),('2ed3dd76c3ad4f98886d725bd8a15d6d','5e4d15dde3c9481d8d581a739275b40d'),('2ed3dd76c3ad4f98886d725bd8a15d6d','de613ed29dae428b875cea4d93936973'),('2ed3dd76c3ad4f98886d725bd8a15d6d','4aa27cbb9f0b442dbc7634abc71875aa'),('2ed3dd76c3ad4f98886d725bd8a15d6d','f0712ecdb5e5484bb06813a64a1fb881'),('2ed3dd76c3ad4f98886d725bd8a15d6d','7a94ccd1913a4e459f51406432ea58ea'),('2ed3dd76c3ad4f98886d725bd8a15d6d','1f378d2531c04947ab1b43d0778e1eae'),('2ed3dd76c3ad4f98886d725bd8a15d6d','d9abc363306f4d5fbef97a8f7a6cf4de'),('2ed3dd76c3ad4f98886d725bd8a15d6d','4cc2c6a4d9104bae876e7632f025be3d'),('2ed3dd76c3ad4f98886d725bd8a15d6d','d19f2346a5084b5e95308abc7654608b'),('2ed3dd76c3ad4f98886d725bd8a15d6d','a7c3c57335144e43be4e041202419d78'),('2ed3dd76c3ad4f98886d725bd8a15d6d','ef7db3ea2b0d4fac99302cb6c95b18ca'),('2ed3dd76c3ad4f98886d725bd8a15d6d','7cf34691816746e390e0191bf3a54c03'),('2ed3dd76c3ad4f98886d725bd8a15d6d','017d13cdc23d48118ac82940ac3ef105'),('2ed3dd76c3ad4f98886d725bd8a15d6d','9adbc1e74d7e4b589d8ccf90a70144d6'),('2ed3dd76c3ad4f98886d725bd8a15d6d','ded16b4a63cf42bf86b5626620abbe0a'),('2ed3dd76c3ad4f98886d725bd8a15d6d','be0363c47ee047cab3c6b47629c51a79'),('2ed3dd76c3ad4f98886d725bd8a15d6d','7ab1f391b9584a5fadfd06b6baeae4c7'),('2ed3dd76c3ad4f98886d725bd8a15d6d','2896e9306e1347cfad778d471cb06a50'),('2ed3dd76c3ad4f98886d725bd8a15d6d','d71bf5725af6410a8970b34808169dfb'),('2ed3dd76c3ad4f98886d725bd8a15d6d','981b0671f38c4835a211ef648652b659'),('2ed3dd76c3ad4f98886d725bd8a15d6d','140faae251f54b84979fabf62187d829'),('2ed3dd76c3ad4f98886d725bd8a15d6d','1991770395df48569263225cdd741f1c'),('2ed3dd76c3ad4f98886d725bd8a15d6d','6b529321690f4527bfbe80d638f9c1f3'),('2ed3dd76c3ad4f98886d725bd8a15d6d','9ecbd017bbf549ff89ac08849d348010'),('2ed3dd76c3ad4f98886d725bd8a15d6d','0ca6569701b840af934b5d6d38ae4097'),('2ed3dd76c3ad4f98886d725bd8a15d6d','8b1d29398c8e480e893a50e2741e2e72'),('2ed3dd76c3ad4f98886d725bd8a15d6d','c21897fc27254d4c92d9455c216abefb'),('2ed3dd76c3ad4f98886d725bd8a15d6d','3db17fd76f364a37be24bd72efe9dcde'),('2ed3dd76c3ad4f98886d725bd8a15d6d','d625d80589144d51a13c5ee0c091d0dc'),('2ed3dd76c3ad4f98886d725bd8a15d6d','febf1ca1528c49daab59f8cbf698488b'),('2ed3dd76c3ad4f98886d725bd8a15d6d','50a4b244620a4ec5acedbb580543a79e'),('2ed3dd76c3ad4f98886d725bd8a15d6d','8102dca1971f41e583bc2af4229ea55a'),('2ed3dd76c3ad4f98886d725bd8a15d6d','0c33da3e9212406ab8ffe4d8f1674baf'),('2ed3dd76c3ad4f98886d725bd8a15d6d','5490ca2f28ad421b80145eaf96abdc7c'),('2ed3dd76c3ad4f98886d725bd8a15d6d','2f848a43ec394c4b96cc7241d2b06381'),('528d6a61affd4d9ab346d10e7653da81','0a6e029ddaa94c7fae7b0cab1f64f8da'),('528d6a61affd4d9ab346d10e7653da81','04be9536b3fc4797b09877d2ac864f6d'),('528d6a61affd4d9ab346d10e7653da81','01574e284bd7493cb2dd65240603ef0a'),('528d6a61affd4d9ab346d10e7653da81','75b5a8bf7c324760be57e4b3ffc0b015'),('528d6a61affd4d9ab346d10e7653da81','b561ae6ebb1a4e57a1ab6d2dc30d8da2'),('528d6a61affd4d9ab346d10e7653da81','192cc19988df4872b38882cae9967c6a'),('528d6a61affd4d9ab346d10e7653da81','40ccdaad894d4ed48a1c0082f8a19d00'),('528d6a61affd4d9ab346d10e7653da81','6da598ce6bba48069b39b0db06a0837c'),('528d6a61affd4d9ab346d10e7653da81','ee37dbb88f00473e8881106c7da1a46e'),('528d6a61affd4d9ab346d10e7653da81','b0193970d4c6486ea6e5e51cdd8fe581'),('528d6a61affd4d9ab346d10e7653da81','713b83e55ecb4b67a65492bba530f170'),('528d6a61affd4d9ab346d10e7653da81','51097a633ea246aca36a0bed9a9ea59a'),('528d6a61affd4d9ab346d10e7653da81','be0363c47ee047cab3c6b47629c51a79'),('528d6a61affd4d9ab346d10e7653da81','5950f4bd46054157b910a3d45ffadf09'),('528d6a61affd4d9ab346d10e7653da81','bbc135b5efef4e8484576126e5e4f9c4'),('528d6a61affd4d9ab346d10e7653da81','0ddc69960e4e4912adad5cf245e288d9'),('528d6a61affd4d9ab346d10e7653da81','5b7414e49e4d4e1d80463bf8714bfa41'),('528d6a61affd4d9ab346d10e7653da81','5e84be56a3274b24b625624e89408627'),('528d6a61affd4d9ab346d10e7653da81','bf5667954ffc42ccbad0944e8e562928'),('528d6a61affd4d9ab346d10e7653da81','5490ca2f28ad421b80145eaf96abdc7c'),('528d6a61affd4d9ab346d10e7653da81','2f848a43ec394c4b96cc7241d2b06381'),('dee8221b51bf442f9114c0e667f9fc12','be0363c47ee047cab3c6b47629c51a79'),('dee8221b51bf442f9114c0e667f9fc12','5490ca2f28ad421b80145eaf96abdc7c'),('dee8221b51bf442f9114c0e667f9fc12','2f848a43ec394c4b96cc7241d2b06381');

/*Table structure for table `sys_route` */

DROP TABLE IF EXISTS `sys_route`;

CREATE TABLE `sys_route` (
  `id` varchar(32) NOT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '原始路径',
  `toUrl` varchar(255) DEFAULT NULL COMMENT '跳转路径',
  `type` varchar(10) DEFAULT NULL COMMENT '转发类型',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_route` */

insert  into `sys_route`(`id`,`url`,`toUrl`,`type`,`disabled`,`opBy`,`opAt`,`delFlag`) values ('651dec6e22f74b21a6c1ea081597a350','/sysadmin','/platform/login','hide',0,'',1517452972,0);

/*Table structure for table `sys_subscribe` */

DROP TABLE IF EXISTS `sys_subscribe`;

CREATE TABLE `sys_subscribe` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `org_name` varchar(32) DEFAULT NULL COMMENT '企业名称',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_subscribe` */

/*Table structure for table `sys_task` */

DROP TABLE IF EXISTS `sys_task`;

CREATE TABLE `sys_task` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '任务名',
  `type` varchar(10) DEFAULT NULL COMMENT '任务类别 SYSTEM:不可禁用或删除 CUSTOM:',
  `jobClass` varchar(255) DEFAULT NULL COMMENT '执行类',
  `note` varchar(255) DEFAULT NULL COMMENT '任务说明',
  `cron` varchar(50) DEFAULT NULL COMMENT '定时规则',
  `data` text COMMENT '执行参数',
  `exeAt` int(32) DEFAULT NULL COMMENT '执行时间',
  `nextAt` int(32) DEFAULT NULL COMMENT '下次执行时间',
  `exeResult` text COMMENT '执行结果',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_task` */

insert  into `sys_task`(`id`,`name`,`type`,`jobClass`,`note`,`cron`,`data`,`exeAt`,`nextAt`,`exeResult`,`status`,`opBy`,`opAt`,`delFlag`) values ('a13572c1e7bb49fca3c5f27c4ba63b54','日志表检查','SYSTEM','com.aebiz.app.web.commons.quartz.job.SLogMonthTableCreateJob','每个月最后一天执行','0 59 23 L * ?','{}',NULL,NULL,NULL,0,'',1517452971,0),('c1db994676c246349e2633a4b90d6bf0','电子券配置更新','SYSTEM','com.aebiz.app.web.commons.quartz.job.ScCouponConfigUpdateJob','每天0点执行','0 0 0 * * ?','{}',NULL,NULL,NULL,0,'',1517452971,0),('c819429a822a4047bae0ec37fffdc2a2','每日定时自动审查文章并筛选每日最佳精彩短篇到首页','CUSTOM','com.aebiz.app.web.commons.quartz.job.TourJourneyJob','每天凌晨处理业务','0 0 23 * * ?','',NULL,NULL,'暂未执行',0,'1a38a92345bd44ed98648b9162b2d67a',1523628274,0);

/*Table structure for table `sys_unit` */

DROP TABLE IF EXISTS `sys_unit`;

CREATE TABLE `sys_unit` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `path` varchar(100) DEFAULT NULL COMMENT '树路径',
  `name` varchar(100) DEFAULT NULL COMMENT '单位名称',
  `aliasName` varchar(100) DEFAULT NULL COMMENT '单位别名',
  `unitcode` varchar(32) DEFAULT NULL COMMENT '机构编码',
  `note` varchar(255) DEFAULT NULL COMMENT '单位介绍',
  `address` varchar(100) DEFAULT NULL COMMENT '单位地址',
  `telephone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(100) DEFAULT NULL COMMENT '单位邮箱',
  `website` varchar(100) DEFAULT NULL COMMENT '单位网站',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `hasChildren` tinyint(1) DEFAULT NULL COMMENT '有子节点',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SYS_UNIT_PATH` (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_unit` */

insert  into `sys_unit`(`id`,`parentId`,`path`,`name`,`aliasName`,`unitcode`,`note`,`address`,`telephone`,`email`,`website`,`location`,`hasChildren`,`opBy`,`opAt`,`delFlag`) values ('ac380368d900410caa5194a751d8f2fb','','0002','eysh',NULL,'2018277','eysh网站管理负责人','安徽省合肥市蜀山区黄山路','18949464266',NULL,NULL,2,0,'1a38a92345bd44ed98648b9162b2d67a',1517464891,0),('d094dfd0dd33402198ac8aac6deca01e','','0001','系统管理','System','','','银河-太阳系-地球','','wizzer@qq.com','http://www.qwang.com.cn',1,0,'',1517452970,0);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `loginname` varchar(120) DEFAULT NULL COMMENT '登录名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '密码加盐',
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `isOnline` tinyint(1) DEFAULT NULL COMMENT '是否在线',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `email` varchar(255) DEFAULT NULL,
  `loginAt` int(32) DEFAULT NULL COMMENT '登陆时间',
  `loginIp` varchar(255) DEFAULT NULL COMMENT '登陆IP',
  `loginCount` int(32) DEFAULT NULL COMMENT '登陆次数',
  `customMenu` varchar(255) DEFAULT NULL COMMENT '常用菜单',
  `loginTheme` varchar(100) DEFAULT NULL COMMENT '皮肤样式',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机号',
  `duties` varchar(32) DEFAULT NULL COMMENT '职务',
  `loginSidebar` tinyint(1) DEFAULT NULL,
  `loginBoxed` tinyint(1) DEFAULT NULL,
  `loginScroll` tinyint(1) DEFAULT NULL,
  `loginPjax` tinyint(1) DEFAULT NULL,
  `unitid` varchar(32) DEFAULT NULL,
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SYS_USER_LOGINNAMAE` (`loginname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`loginname`,`password`,`salt`,`username`,`isOnline`,`disabled`,`email`,`loginAt`,`loginIp`,`loginCount`,`customMenu`,`loginTheme`,`mobile`,`duties`,`loginSidebar`,`loginBoxed`,`loginScroll`,`loginPjax`,`unitid`,`opBy`,`opAt`,`delFlag`) values ('1a38a92345bd44ed98648b9162b2d67a','admin277','jQUn0YZZTGmMeLOZpaUb1SsQmA95dwnasq7CBQKVUXQ=','ssDdMX99xZ87wxifIgnj9Q==','周家俊',1,0,'postmaster@xyy277.cn',1524017298,NULL,59,'5950f4bd46054157b910a3d45ffadf09,be0363c47ee047cab3c6b47629c51a79,9723f56569324c22bc01d431ca89354c,45533f4a901941be966cd8ee56db8623,657d53d3665049d781d340db0a3e4bc7',NULL,'18949464266','',0,0,0,1,'d094dfd0dd33402198ac8aac6deca01e','be74468e1345473cb1314610536281e5',1517456933,0),('7272a53bc6fe443cbd0070f600fd9783','adminwx','rlxg+feafG24R6QBV/zbim0+0InONxF0qLRq2ghS8/g=','BU1VH5kGZCg/6a4jNlNAVw==','xyy',1,0,'102589412@qq.com',1521258796,NULL,6,NULL,NULL,'18815793208','',0,0,0,0,'ac380368d900410caa5194a751d8f2fb','',1517466471,0),('b263771718bd43d6bcfc34f2b538e5b2','eysh','HJRjtV5OeO101S9AIyWEeUYR5okMpbv+1ArJE4PozaU=','53EjSnLnT67evX8WJXPCrg==','admin',0,0,'',1521965168,NULL,3,NULL,NULL,'18133687482','',0,0,0,1,'ac380368d900410caa5194a751d8f2fb','1a38a92345bd44ed98648b9162b2d67a',1521964832,0),('b323736ba474410d8045d04d476524e6','admincms','n64aTHNv70Ru8Y+r6lDb1GmiYn6xg1wqmNz3ultYlpg=','sgdE0hAsUbvTH5x9sCH4Wg==','David',0,0,'18888888888@qq.com',1517465540,NULL,1,NULL,NULL,'18888888888','',0,0,0,0,'ac380368d900410caa5194a751d8f2fb','',1517466481,0),('be74468e1345473cb1314610536281e5','superadmin','z0mI2aAhBsrIsrfi2o8BFJykKfr/7Ng9kw5X1kY7L0Y=','dStpHs/qlnxon4YA8m4kLA==','超级管理员',1,0,'chenquanlai@qwang.com.cn',1521972996,'127.0.0.1',5,NULL,'palette.css',NULL,NULL,0,0,0,1,'d094dfd0dd33402198ac8aac6deca01e','',1517452970,0);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `roleId` varchar(32) DEFAULT NULL,
  `userId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`roleId`,`userId`) values ('8dbcb08a74fb4f458ba43474f9b3f8ac','be74468e1345473cb1314610536281e5'),('8dbcb08a74fb4f458ba43474f9b3f8ac','1a38a92345bd44ed98648b9162b2d67a'),('2ed3dd76c3ad4f98886d725bd8a15d6d','7272a53bc6fe443cbd0070f600fd9783'),('791cece1cbc047a2af788950a3c9e93e','b323736ba474410d8045d04d476524e6'),('528d6a61affd4d9ab346d10e7653da81','b263771718bd43d6bcfc34f2b538e5b2');

/*Table structure for table `sys_user_unit` */

DROP TABLE IF EXISTS `sys_user_unit`;

CREATE TABLE `sys_user_unit` (
  `userId` varchar(32) DEFAULT NULL,
  `unitId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_unit` */

insert  into `sys_user_unit`(`userId`,`unitId`) values ('be74468e1345473cb1314610536281e5','d094dfd0dd33402198ac8aac6deca01e');

/*Table structure for table `tour_journey` */

DROP TABLE IF EXISTS `tour_journey`;

CREATE TABLE `tour_journey` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `status` int(32) DEFAULT NULL COMMENT '审核状态 -1：回收站',
  `author` varchar(50) DEFAULT NULL COMMENT '发布者',
  `readnum` int(32) DEFAULT NULL COMMENT '点击量',
  `up_number` int(32) DEFAULT NULL COMMENT '点赞数',
  `comment_no` int(32) DEFAULT NULL COMMENT '评论数',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tour_journey` */

insert  into `tour_journey`(`id`,`title`,`status`,`author`,`readnum`,`up_number`,`comment_no`,`opBy`,`opAt`,`delFlag`) values ('118921f146dc40cb901d30e14978b442','去，你的旅行',0,'周大诗人',0,0,0,'',1523543832,0),('bf801f4b06f748b094f3c2b1ab37cdde','你好，我的世界',0,'周家俊',0,0,0,'c8ed186eb3554ec0be9f627f6d619275',1523545025,0);

/*Table structure for table `tour_journey_check` */

DROP TABLE IF EXISTS `tour_journey_check`;

CREATE TABLE `tour_journey_check` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `journey_id` varchar(32) DEFAULT NULL COMMENT '游记id',
  `check_time` bigint(128) DEFAULT NULL COMMENT '审核时间',
  `check_status` int(32) DEFAULT NULL COMMENT '审核状态',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tour_journey_check` */

/*Table structure for table `tour_journey_comment` */

DROP TABLE IF EXISTS `tour_journey_comment`;

CREATE TABLE `tour_journey_comment` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `journey_id` varchar(32) DEFAULT NULL COMMENT '游记id',
  `comment` varchar(200) DEFAULT NULL COMMENT '评论内容',
  `status` int(32) DEFAULT NULL COMMENT '审核状态',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tour_journey_comment` */

/*Table structure for table `tour_journey_picture` */

DROP TABLE IF EXISTS `tour_journey_picture`;

CREATE TABLE `tour_journey_picture` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `journey_id` varchar(32) DEFAULT NULL COMMENT '游记id',
  `path` varchar(256) DEFAULT NULL COMMENT '图片存放路径',
  `is_title` varchar(1) DEFAULT NULL COMMENT '是否标题图',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tour_journey_picture` */

/*Table structure for table `tour_journey_txt` */

DROP TABLE IF EXISTS `tour_journey_txt`;

CREATE TABLE `tour_journey_txt` (
  `journey_id` varchar(32) DEFAULT NULL COMMENT '游记id',
  `content` text COMMENT '游记内容',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tour_journey_txt` */

insert  into `tour_journey_txt`(`journey_id`,`content`,`opBy`,`opAt`,`delFlag`) values ('118921f146dc40cb901d30e14978b442','你好，我来了','',1523543832,0),('bf801f4b06f748b094f3c2b1ab37cdde','你来就离开，再见康桥','c8ed186eb3554ec0be9f627f6d619275',1523545025,0);

/*Table structure for table `tour_linkman` */

DROP TABLE IF EXISTS `tour_linkman`;

CREATE TABLE `tour_linkman` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系号码',
  `email` varchar(100) DEFAULT NULL COMMENT '联系邮箱',
  `qq` varchar(20) DEFAULT NULL COMMENT '联系QQ',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tour_linkman` */

insert  into `tour_linkman`(`id`,`phone`,`email`,`qq`,`opBy`,`opAt`,`delFlag`) values ('e960cd4575e34dbbb99836e53f33f246','1','11','1','1a38a92345bd44ed98648b9162b2d67a',1522118232,0);

/*Table structure for table `tour_user` */

DROP TABLE IF EXISTS `tour_user`;

CREATE TABLE `tour_user` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `loginname` varchar(100) DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(128) DEFAULT NULL COMMENT '昵称',
  `password` varchar(128) DEFAULT NULL COMMENT '加密密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '加密盐',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ',
  `name` varchar(10) DEFAULT NULL COMMENT '真实姓名',
  `id_num` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '县区',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `login_num` bigint(128) DEFAULT NULL COMMENT '登录次数',
  `last_ip` varchar(50) DEFAULT NULL COMMENT '最后登录IP',
  `last_time` varchar(32) DEFAULT NULL COMMENT '最后登录时间',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `score` int(32) DEFAULT NULL COMMENT '游戏得分',
  `rank` int(32) DEFAULT NULL COMMENT '游戏排名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tour_user` */

insert  into `tour_user`(`id`,`loginname`,`nickname`,`password`,`salt`,`mobile`,`email`,`qq`,`name`,`id_num`,`disabled`,`province`,`city`,`area`,`address`,`login_num`,`last_ip`,`last_time`,`opBy`,`opAt`,`delFlag`,`score`,`rank`) values ('4214c59377cd435a9faf3c2b19f7598f','xyy277','小大','hM8QpikPsWIb0LViVUAn6Ke+fAbIho+oxfYCqJGawP0=','X49FwaCXHbQwa5B3kcQ5cg==','18888888888',NULL,'123456','大牛',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',1521966126,0,NULL,NULL),('91835205d4a24c8a824bd48be2f6e1c9','周1234','小大','qBSkKvszS4SuNPJ25Lf4rwm9JMmF6/d6U17YFpsmNRM=','PpNjCgIbm9y0/m+gFbJ/ig==','15111111111',NULL,'123456','大牛',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',1521966540,0,NULL,NULL),('c8ed186eb3554ec0be9f627f6d619275','admin277','小周','NCo/y0oo0y+fxQkENgZmuQLZWCtf0kVKJTWE7CKYwfk=','X8mZtZz7oBlN8ao1ovblxg==','18999999999',NULL,'907507646','周家俊',NULL,0,NULL,NULL,NULL,NULL,NULL,'0:0:0:0:0:0:0:1','2018-04-18 10:02:13','',1521537230,0,NULL,NULL),('ed16d7a8706a41f39b682b235bf1a92b','zjj1995','小周','vGVDp30i/GpyBu10IG2XplVmxelLCfI9kKo7DiBaBUo=','DH6S8eZ4lk2rF6lh/fbqgw==','18949464266',NULL,'907507646','周总',NULL,0,NULL,NULL,NULL,NULL,NULL,'0:0:0:0:0:0:0:1','2018-04-12 20:53:21','',1521554483,0,NULL,NULL),('f9a2a29c9ec343119a80d2fc2330f645','周123','小大','38/Pr4isS/d1rENLbWb1aEBVRBkrTGWqcVP2/fViZVs=','aaH6PKCGFlfqJSigi2Yq/g==','15000000000',NULL,'123456','大牛',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',1521966332,0,NULL,NULL);

/*Table structure for table `tour_user_concern` */

DROP TABLE IF EXISTS `tour_user_concern`;

CREATE TABLE `tour_user_concern` (
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `concern_id` varchar(32) DEFAULT NULL COMMENT '关注id',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tour_user_concern` */

/*Table structure for table `tour_verse` */

DROP TABLE IF EXISTS `tour_verse`;

CREATE TABLE `tour_verse` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `title` varchar(16) DEFAULT NULL COMMENT '标题',
  `content` varchar(128) DEFAULT NULL COMMENT '内容',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tour_verse` */

/*Table structure for table `wx_config` */

DROP TABLE IF EXISTS `wx_config`;

CREATE TABLE `wx_config` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `appname` varchar(120) DEFAULT NULL COMMENT '公众号名称',
  `ghid` varchar(100) DEFAULT NULL COMMENT '原始ID',
  `appid` varchar(50) DEFAULT NULL COMMENT 'Appid',
  `appsecret` varchar(50) DEFAULT NULL COMMENT 'Appsecret',
  `encodingAESKey` varchar(100) DEFAULT NULL COMMENT 'EncodingAESKey',
  `token` varchar(100) DEFAULT NULL COMMENT 'Token',
  `access_token` varchar(255) DEFAULT NULL COMMENT 'access_token',
  `access_token_expires` int(32) DEFAULT NULL COMMENT 'access_token_expires',
  `access_token_lastat` varchar(50) DEFAULT NULL COMMENT 'access_token_lastat',
  `is_main` varchar(1) DEFAULT NULL COMMENT '是否为主账号',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_config` */

/*Table structure for table `wx_mass` */

DROP TABLE IF EXISTS `wx_mass`;

CREATE TABLE `wx_mass` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '群发名称',
  `type` varchar(20) DEFAULT NULL COMMENT '群发类型',
  `media_id` varchar(255) DEFAULT NULL COMMENT '媒体文件ID',
  `scope` varchar(20) DEFAULT NULL COMMENT 'Scope',
  `content` text COMMENT 'Content',
  `status` int(32) DEFAULT NULL COMMENT '发送状态',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_mass` */

/*Table structure for table `wx_mass_news` */

DROP TABLE IF EXISTS `wx_mass_news`;

CREATE TABLE `wx_mass_news` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `thumb_media_id` varchar(255) DEFAULT NULL COMMENT '缩略图',
  `author` varchar(120) DEFAULT NULL COMMENT '作者',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content_source_url` varchar(255) DEFAULT NULL COMMENT '原地址',
  `content` text COMMENT '图文内容',
  `digest` text COMMENT '摘要',
  `show_cover_pic` int(32) DEFAULT NULL COMMENT '显示封面',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_mass_news` */

/*Table structure for table `wx_mass_send` */

DROP TABLE IF EXISTS `wx_mass_send`;

CREATE TABLE `wx_mass_send` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `massId` varchar(32) DEFAULT NULL COMMENT '群发ID',
  `receivers` text COMMENT 'Openid列表',
  `status` int(32) DEFAULT NULL COMMENT '发送状态',
  `msgId` varchar(32) DEFAULT NULL COMMENT 'msgId',
  `errCode` varchar(32) DEFAULT NULL COMMENT 'errCode',
  `errMsg` varchar(255) DEFAULT NULL COMMENT 'errMsg',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_mass_send` */

/*Table structure for table `wx_menu` */

DROP TABLE IF EXISTS `wx_menu`;

CREATE TABLE `wx_menu` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父ID',
  `path` varchar(100) DEFAULT NULL COMMENT '树路径',
  `menuName` varchar(20) DEFAULT NULL COMMENT '菜单名称',
  `menuType` varchar(20) DEFAULT NULL COMMENT '菜单类型',
  `menuKey` varchar(20) DEFAULT NULL COMMENT '关键词',
  `url` varchar(255) DEFAULT NULL COMMENT '网址',
  `appid` varchar(255) DEFAULT NULL COMMENT '小程序appid',
  `pagepath` varchar(255) DEFAULT NULL COMMENT '小程序入口页',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `hasChildren` tinyint(1) DEFAULT NULL COMMENT '有子节点',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_menu` */

/*Table structure for table `wx_msg` */

DROP TABLE IF EXISTS `wx_msg`;

CREATE TABLE `wx_msg` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `openid` varchar(50) DEFAULT NULL COMMENT 'openid',
  `nickname` varchar(255) DEFAULT NULL COMMENT '微信昵称',
  `type` varchar(20) DEFAULT NULL COMMENT '信息类型',
  `content` text COMMENT '信息内容',
  `replyId` varchar(32) DEFAULT NULL COMMENT '回复ID',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_msg` */

/*Table structure for table `wx_msg_reply` */

DROP TABLE IF EXISTS `wx_msg_reply`;

CREATE TABLE `wx_msg_reply` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `msgid` varchar(32) DEFAULT NULL COMMENT 'msgid',
  `openid` varchar(50) DEFAULT NULL COMMENT 'openid',
  `type` varchar(20) DEFAULT NULL COMMENT '信息类型',
  `content` text COMMENT '信息内容',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_msg_reply` */

/*Table structure for table `wx_reply` */

DROP TABLE IF EXISTS `wx_reply`;

CREATE TABLE `wx_reply` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `type` varchar(20) DEFAULT NULL COMMENT '回复类型',
  `msgType` varchar(20) DEFAULT NULL COMMENT '消息类型',
  `keyword` varchar(50) DEFAULT NULL COMMENT '关键词',
  `content` text COMMENT '回复内容',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_reply` */

/*Table structure for table `wx_reply_news` */

DROP TABLE IF EXISTS `wx_reply_news`;

CREATE TABLE `wx_reply_news` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `description` varchar(255) DEFAULT NULL COMMENT '摘要',
  `picUrl` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `url` varchar(255) DEFAULT NULL COMMENT '文章路径',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_reply_news` */

/*Table structure for table `wx_reply_txt` */

DROP TABLE IF EXISTS `wx_reply_txt`;

CREATE TABLE `wx_reply_txt` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_reply_txt` */

/*Table structure for table `wx_sc_news` */

DROP TABLE IF EXISTS `wx_sc_news`;

CREATE TABLE `wx_sc_news` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `author` varchar(120) DEFAULT NULL COMMENT '作者',
  `content_source_url` varchar(255) DEFAULT NULL COMMENT '原地址',
  `thumb_media` varchar(255) DEFAULT NULL COMMENT '缩略图',
  `content` text COMMENT '图文内容',
  `digest` text COMMENT '简述',
  `show_cover_pic` varchar(500) DEFAULT NULL COMMENT '显示封面',
  `status` int(32) DEFAULT NULL COMMENT '状态',
  `appid` varchar(32) DEFAULT NULL COMMENT 'appid',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_sc_news` */

/*Table structure for table `wx_tpl_id` */

DROP TABLE IF EXISTS `wx_tpl_id`;

CREATE TABLE `wx_tpl_id` (
  `id` varchar(32) NOT NULL COMMENT '模板编号',
  `template_id` varchar(255) DEFAULT NULL COMMENT '模板ID',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_tpl_id` */

/*Table structure for table `wx_tpl_list` */

DROP TABLE IF EXISTS `wx_tpl_list`;

CREATE TABLE `wx_tpl_list` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `template_id` varchar(100) DEFAULT NULL COMMENT '模板ID',
  `title` varchar(255) DEFAULT NULL COMMENT '模板标题',
  `primary_industry` varchar(100) DEFAULT NULL COMMENT '主营行业',
  `deputy_industry` varchar(100) DEFAULT NULL COMMENT '副营行业',
  `content` varchar(300) DEFAULT NULL COMMENT '模板内容',
  `example` varchar(300) DEFAULT NULL COMMENT '模板示例',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_WX_TPL_LIST` (`template_id`,`wxid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_tpl_list` */

/*Table structure for table `wx_tpl_log` */

DROP TABLE IF EXISTS `wx_tpl_log`;

CREATE TABLE `wx_tpl_log` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `openid` varchar(50) DEFAULT NULL COMMENT 'openid',
  `nickname` varchar(255) DEFAULT NULL COMMENT '微信昵称',
  `content` text COMMENT '发送内容',
  `status` int(32) DEFAULT NULL COMMENT '发送状态',
  `result` text COMMENT '发送结果',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_tpl_log` */

/*Table structure for table `wx_user` */

DROP TABLE IF EXISTS `wx_user`;

CREATE TABLE `wx_user` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `openid` varchar(50) DEFAULT NULL COMMENT 'openid',
  `unionid` varchar(50) DEFAULT NULL COMMENT 'unionid',
  `nickname` varchar(255) DEFAULT NULL COMMENT '微信昵称',
  `subscribe` tinyint(1) DEFAULT NULL COMMENT '是否关注',
  `subscribeAt` int(32) DEFAULT NULL COMMENT '关注时间',
  `sex` int(32) DEFAULT NULL COMMENT '性别',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `headimgurl` varchar(255) DEFAULT NULL COMMENT '头像',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_WX_USER_OPENID` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
