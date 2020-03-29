### Database Demo

本測試使用的 Schema與Structure
```sql

# Dump of table book
# ------------------------------------------------------------

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `bookid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bookid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;

INSERT INTO `book` (`bookid`, `name`, `author`)
VALUES
	(1,'天龍八部','金庸'),
	(2,'鹿鼎記','金庸'),
	(3,'多拉Ａ夢','藤子不二雄');

/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table members
# ------------------------------------------------------------

DROP TABLE IF EXISTS `members`;

CREATE TABLE `members` (
  `member_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL DEFAULT '',
  `last_name` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`member_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;

INSERT INTO `members` (`member_id`, `first_name`, `last_name`, `email`)
VALUES
	(1,'Adam','OuYang','adam@example.com'),
	(2,'Brown','Chen','brown@example.com'),
	(3,'Cayla','Liao','cayla@example.com');

/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

# Dump of table department
# ------------------------------------------------------------

DROP TABLE IF EXISTS `department`;
-- Create syntax for TABLE 'department'
CREATE TABLE `department` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `department_name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


# Dump of table employee
# ------------------------------------------------------------

DROP TABLE IF EXISTS `employee`;
-- Create syntax for TABLE 'employee'
CREATE TABLE `employee` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `lastName` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `gender` int(2) DEFAULT NULL,
  `dId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
```