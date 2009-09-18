/*
SQLyog Community Edition- MySQL GUI v7.13 
MySQL - 5.0.67-community-nt : Database - cas
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE IF NOT EXISTS cas;

USE `cas`;

/*Table structure for table `registeredserviceimpl` */

DROP TABLE IF EXISTS `registeredserviceimpl`;

CREATE TABLE `registeredserviceimpl` (
  `id` bigint(20) NOT NULL auto_increment,
  `allowedAttributes` tinyblob,
  `allowedToProxy` bit(1) NOT NULL,
  `anonymousAccess` bit(1) NOT NULL,
  `description` varchar(255) default NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(255) default NULL,
  `serviceId` varchar(255) default NULL,
  `ssoEnabled` bit(1) NOT NULL,
  `theme` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `serviceticket` */

DROP TABLE IF EXISTS `serviceticket`;

CREATE TABLE `serviceticket` (
  `ID` varchar(255) NOT NULL,
  `NUMBER_OF_TIMES_USED` int(11) default NULL,
  `CREATION_TIME` bigint(20) default NULL,
  `EXPIRATION_POLICY` blob NOT NULL,
  `LAST_TIME_USED` bigint(20) default NULL,
  `PREVIOUS_LAST_TIME_USED` bigint(20) default NULL,
  `FROM_NEW_LOGIN` int(11) NOT NULL,
  `TICKET_ALREADY_GRANTED` bit(1) NOT NULL,
  `SERVICE` blob NOT NULL,
  `ticketGrantingTicket_ID` varchar(255) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK7645ADE132A2C0E5` (`ticketGrantingTicket_ID`),
  CONSTRAINT `FK7645ADE132A2C0E5` FOREIGN KEY (`ticketGrantingTicket_ID`) REFERENCES `ticketgrantingticket` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `ticketgrantingticket` */

DROP TABLE IF EXISTS `ticketgrantingticket`;

CREATE TABLE `ticketgrantingticket` (
  `ID` varchar(255) NOT NULL,
  `NUMBER_OF_TIMES_USED` int(11) default NULL,
  `CREATION_TIME` bigint(20) default NULL,
  `EXPIRATION_POLICY` blob NOT NULL,
  `LAST_TIME_USED` bigint(20) default NULL,
  `PREVIOUS_LAST_TIME_USED` bigint(20) default NULL,
  `AUTHENTICATION` blob NOT NULL,
  `EXPIRED` bit(1) NOT NULL,
  `SERVICES_GRANTED_ACCESS_TO` blob NOT NULL,
  `ticketGrantingTicket_ID` varchar(255) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FKB4C4CDDE32A2C0E5` (`ticketGrantingTicket_ID`),
  CONSTRAINT `FKB4C4CDDE32A2C0E5` FOREIGN KEY (`ticketGrantingTicket_ID`) REFERENCES `ticketgrantingticket` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
