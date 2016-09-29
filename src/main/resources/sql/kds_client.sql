CREATE DATABASE IF NOT EXISTS `kds_client` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `kds_client`;

CREATE TABLE IF NOT EXISTS `request` (
  `request_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '요청전문 순번',
  `request_uuid` varchar(50) NOT NULL DEFAULT '0' COMMENT '요청전문식별번호(NMI_REQ_HEADER의 NMI_REQ_UUID)',
  `content` varchar(2000) NOT NULL DEFAULT '0' COMMENT '요청전문(JSON)',
  `reg_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '전문요청시간',
  PRIMARY KEY (`request_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='요청전문';

CREATE TABLE IF NOT EXISTS `response` (
  `response_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '응답전문 순번',
  `response_uuid` varchar(50) NOT NULL COMMENT '응답전문식별번호(NMI_RES_HEADER의 NMI_RES_UUID)',
  `request_uuid` varchar(50) NOT NULL COMMENT '요청전문식별번호',
  `content` varchar(2000) NOT NULL COMMENT '응답전문(JSON)',
  `reg_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '응답시간',
  PRIMARY KEY (`response_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='응답전문';