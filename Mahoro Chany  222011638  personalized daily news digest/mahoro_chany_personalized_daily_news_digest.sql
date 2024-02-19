-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 19, 2024 at 08:07 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mahoro_chany_personalized daily news digest`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `CreateSubqueryViewProcedure`()
BEGIN
    SET @subquery = '(SELECT UserID, COUNT(*) AS ArticleCount FROM NewsArticle GROUP BY UserID) AS UserArticleCount';
    SET @sql = CONCAT('CREATE VIEW UserArticleCountView AS SELECT * FROM ', @subquery);
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteFromTablesProcedure`(
    IN p_TableName1 VARCHAR(255),
    IN p_ConditionColumn1 VARCHAR(255),
    IN p_ConditionValue1 INT,
    IN p_TableName2 VARCHAR(255),
    IN p_ConditionColumn2 VARCHAR(255),
    IN p_ConditionValue2 INT
)
BEGIN
    SET @sql1 = CONCAT('DELETE FROM ', p_TableName1, ' WHERE ', p_ConditionColumn1, ' = ?');
    PREPARE stmt1 FROM @sql1;
    SET @condition_value1 = p_ConditionValue1;
    EXECUTE stmt1 USING @condition_value1;
    DEALLOCATE PREPARE stmt1;

    SET @sql2 = CONCAT('DELETE FROM ', p_TableName2, ' WHERE ', p_ConditionColumn2, ' = ?');
    PREPARE stmt2 FROM @sql2;
    SET @condition_value2 = p_ConditionValue2;
    EXECUTE stmt2 USING @condition_value2;
    DEALLOCATE PREPARE stmt2;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DisplayAllTableDataProcedure`()
BEGIN
    SELECT * FROM UserProfile;
    SELECT * FROM NewsArticle;
    SELECT * FROM DigestConfiguration;
    SELECT * FROM ReadingHistory;
    SELECT * FROM NotificationLog;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertIntoUserProfileProcedure`(
    IN p_UserID INT,
    IN p_Username VARCHAR(255),
    IN p_Email VARCHAR(255),
    IN p_Preferences VARCHAR(255),
    IN p_ReadingHistory VARCHAR(255),
    IN p_SubscriptionStatus VARCHAR(255)
)
BEGIN
    INSERT INTO UserProfile (UserID, Username, Email, Preferences, ReadingHistory, SubscriptionStatus) 
    VALUES (p_UserID, p_Username, p_Email, p_Preferences, p_ReadingHistory, p_SubscriptionStatus);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateTableDataProcedure`(
    IN p_TableName VARCHAR(255),
    IN p_ColumnName VARCHAR(255),
    IN p_NewValue VARCHAR(255),
    IN p_ConditionColumn VARCHAR(255),
    IN p_ConditionValue INT
)
BEGIN
    SET @sql = CONCAT('UPDATE ', p_TableName, ' SET ', p_ColumnName, ' = ? WHERE ', p_ConditionColumn, ' = ?');
    PREPARE stmt FROM @sql;
    SET @new_value = p_NewValue;
    SET @condition_value = p_ConditionValue;
    EXECUTE stmt USING @new_value, @condition_value;
    DEALLOCATE PREPARE stmt;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `fname` varchar(255) NOT NULL,
  `lname` varchar(255) NOT NULL,
  `idNumber` varchar(255) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `gender` enum('Male','Female') DEFAULT NULL,
  `martialStatus` varchar(50) DEFAULT NULL,
  `DoB` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`idNumber`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`fname`, `lname`, `idNumber`, `phone`, `gender`, `martialStatus`, `DoB`, `email`, `password`) VALUES
('mucyo', 'alex', '1', '0789675432', 'Male', 'single', '2001-01-01', 'alex@gmail.com', '1234'),
('MUHIRE', 'ERIC', '2', '0784567345', 'Male', 'SINGLE', '2000-01-12', 'ericmuhi@gmail.com', '4321');

-- --------------------------------------------------------

--
-- Stand-in structure for view `alldigestconfigurationdata`
--
CREATE TABLE IF NOT EXISTS `alldigestconfigurationdata` (
`DigestID` int(11)
,`UserID` int(11)
,`TimeOfDelivery` time
,`PreferredContentTypes` varchar(255)
,`LanguagePreferences` varchar(255)
);
-- --------------------------------------------------------

--
-- Stand-in structure for view `allnewsarticledata`
--
CREATE TABLE IF NOT EXISTS `allnewsarticledata` (
`ArticleID` int(11)
,`Title` varchar(255)
,`Author` varchar(255)
,`PublicationDate` date
,`Content` text
,`Tags` varchar(255)
,`Source` varchar(255)
);
-- --------------------------------------------------------

--
-- Stand-in structure for view `allnotificationlogdata`
--
CREATE TABLE IF NOT EXISTS `allnotificationlogdata` (
`LogID` int(11)
,`UserID` int(11)
,`NotificationType` varchar(255)
,`Timestamp` datetime
,`Status` varchar(255)
);
-- --------------------------------------------------------

--
-- Stand-in structure for view `allreadinghistorydata`
--
CREATE TABLE IF NOT EXISTS `allreadinghistorydata` (
`HistoryID` int(11)
,`UserID` int(11)
,`ArticleID` int(11)
,`Timestamp` datetime
,`ReadingDuration` int(11)
);
-- --------------------------------------------------------

--
-- Stand-in structure for view `alluserprofiledata`
--
CREATE TABLE IF NOT EXISTS `alluserprofiledata` (
`UserID` int(11)
,`Username` varchar(255)
,`Email` varchar(255)
,`Preferences` varchar(255)
,`ReadingHistory` varchar(255)
,`SubscriptionStatus` varchar(255)
);
-- --------------------------------------------------------

--
-- Table structure for table `digestconfiguration`
--

CREATE TABLE IF NOT EXISTS `digestconfiguration` (
  `DigestID` int(11) NOT NULL,
  `UserID` int(11) DEFAULT NULL,
  `TimeOfDelivery` time DEFAULT NULL,
  `PreferredContentTypes` varchar(255) DEFAULT NULL,
  `LanguagePreferences` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DigestID`),
  KEY `UserID` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `digestconfiguration`
--

INSERT INTO `digestconfiguration` (`DigestID`, `UserID`, `TimeOfDelivery`, `PreferredContentTypes`, `LanguagePreferences`) VALUES
(1, 1, '12:00:00', 'Technology, Science', 'English'),
(2, 2, '09:00:00', 'Science, Health', 'English'),
(3, 3, '10:00:00', 'Politics, Economics', 'English'),
(4, 4, '11:00:00', 'Art, Literature', 'English');

--
-- Triggers `digestconfiguration`
--
DROP TRIGGER IF EXISTS `AfterUpdateDigestConfiguration`;
DELIMITER //
CREATE TRIGGER `AfterUpdateDigestConfiguration` AFTER UPDATE ON `digestconfiguration`
 FOR EACH ROW BEGIN
    INSERT INTO AuditTrail (TableName, Action, Timestamp)
    VALUES ('DigestConfiguration', 'Update', NOW());
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `insertdigestconfigurationdigestid`
--
CREATE TABLE IF NOT EXISTS `insertdigestconfigurationdigestid` (
`DigestID` int(11)
);
-- --------------------------------------------------------

--
-- Stand-in structure for view `insertnewsarticlearticleid`
--
CREATE TABLE IF NOT EXISTS `insertnewsarticlearticleid` (
`ArticleID` int(11)
);
-- --------------------------------------------------------

--
-- Stand-in structure for view `insertnotificationloglogid`
--
CREATE TABLE IF NOT EXISTS `insertnotificationloglogid` (
`LogID` int(11)
);
-- --------------------------------------------------------

--
-- Stand-in structure for view `insertreadinghistoryhistoryid`
--
CREATE TABLE IF NOT EXISTS `insertreadinghistoryhistoryid` (
`HistoryID` int(11)
);
-- --------------------------------------------------------

--
-- Stand-in structure for view `insertuserprofileuserid`
--
CREATE TABLE IF NOT EXISTS `insertuserprofileuserid` (
`UserID` int(11)
);
-- --------------------------------------------------------

--
-- Table structure for table `newsarticle`
--

CREATE TABLE IF NOT EXISTS `newsarticle` (
  `ArticleID` int(11) NOT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `Author` varchar(255) DEFAULT NULL,
  `PublicationDate` date DEFAULT NULL,
  `Content` text,
  `Tags` varchar(255) DEFAULT NULL,
  `Source` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ArticleID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `newsarticle`
--

INSERT INTO `newsarticle` (`ArticleID`, `Title`, `Author`, `PublicationDate`, `Content`, `Tags`, `Source`) VALUES
(1, 'New Title', 'Jane Smith', '2023-12-15', 'Lorem ipsum dolor sit amet...', 'AI, Technology', 'TechNews.com'),
(2, 'The Impact of Climate Change', 'John Adams', '2024-01-05', 'Lorem ipsum dolor sit amet...', 'Climate Change, Environment', 'EnvironmentalNews.org'),
(3, 'Understanding Quantum Computing', 'Emily Johnson', '2024-01-10', 'Lorem ipsum dolor sit amet...', 'Quantum Computing, Technology', 'TechInsight.com'),
(4, 'Economic Trends in Asia', 'Michael Brown', '2024-01-15', 'Lorem ipsum dolor sit amet...', 'Economics, Asia', 'EconomicTimes.com');

-- --------------------------------------------------------

--
-- Table structure for table `notificationlog`
--

CREATE TABLE IF NOT EXISTS `notificationlog` (
  `LogID` int(11) NOT NULL,
  `UserID` int(11) DEFAULT NULL,
  `NotificationType` varchar(255) DEFAULT NULL,
  `Timestamp` datetime DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`LogID`),
  KEY `UserID` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notificationlog`
--

INSERT INTO `notificationlog` (`LogID`, `UserID`, `NotificationType`, `Timestamp`, `Status`) VALUES
(1, 1, 'New Article', '2023-12-20 09:00:00', 'Read'),
(2, 2, 'New Article', '2024-01-06 10:00:00', 'Unread'),
(3, 3, 'New Article', '2024-01-27 18:45:00', 'Unread'),
(4, 4, 'New Article', '2024-01-13 12:30:00', 'Unread');

-- --------------------------------------------------------

--
-- Table structure for table `readinghistory`
--

CREATE TABLE IF NOT EXISTS `readinghistory` (
  `HistoryID` int(11) NOT NULL,
  `UserID` int(11) DEFAULT NULL,
  `ArticleID` int(11) DEFAULT NULL,
  `Timestamp` datetime DEFAULT NULL,
  `ReadingDuration` int(11) DEFAULT NULL,
  PRIMARY KEY (`HistoryID`),
  KEY `UserID` (`UserID`),
  KEY `ArticleID` (`ArticleID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `readinghistory`
--

INSERT INTO `readinghistory` (`HistoryID`, `UserID`, `ArticleID`, `Timestamp`, `ReadingDuration`) VALUES
(1, 1, 1, '2023-12-18 10:30:00', 20),
(2, 2, 2, '2024-01-08 11:45:00', 20),
(3, 3, 3, '2024-01-15 13:20:00', 25),
(4, 4, 4, '2024-01-22 15:10:00', 18);

-- --------------------------------------------------------

--
-- Stand-in structure for view `totalreadingdurationperuser`
--
CREATE TABLE IF NOT EXISTS `totalreadingdurationperuser` (
`UserID` int(11)
,`TotalDuration` decimal(32,0)
);
-- --------------------------------------------------------

--
-- Table structure for table `userprofile`
--

CREATE TABLE IF NOT EXISTS `userprofile` (
  `UserID` int(11) NOT NULL,
  `Username` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Preferences` varchar(255) DEFAULT NULL,
  `ReadingHistory` varchar(255) DEFAULT NULL,
  `SubscriptionStatus` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userprofile`
--

INSERT INTO `userprofile` (`UserID`, `Username`, `Email`, `Preferences`, `ReadingHistory`, `SubscriptionStatus`) VALUES
(1, 'johnDoe', 'newemail@gmail.com', 'Technology, Science', '1,3,5', 'Active'),
(2, 'AliceSmith', 'alice.smith@gmail.com', 'Science, Health', '2,4,6', 'Active'),
(3, 'BobJohnson', 'bob.johnson@gmail.com', 'Politics, Economics', '3,5,7', 'Active'),
(4, 'EmilyDavis', 'emily.davis@gmail.com', 'Art, Literature', '4,6,8', 'Inactive');

-- --------------------------------------------------------

--
-- Structure for view `alldigestconfigurationdata`
--
DROP TABLE IF EXISTS `alldigestconfigurationdata`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `alldigestconfigurationdata` AS select `digestconfiguration`.`DigestID` AS `DigestID`,`digestconfiguration`.`UserID` AS `UserID`,`digestconfiguration`.`TimeOfDelivery` AS `TimeOfDelivery`,`digestconfiguration`.`PreferredContentTypes` AS `PreferredContentTypes`,`digestconfiguration`.`LanguagePreferences` AS `LanguagePreferences` from `digestconfiguration`;

-- --------------------------------------------------------

--
-- Structure for view `allnewsarticledata`
--
DROP TABLE IF EXISTS `allnewsarticledata`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `allnewsarticledata` AS select `newsarticle`.`ArticleID` AS `ArticleID`,`newsarticle`.`Title` AS `Title`,`newsarticle`.`Author` AS `Author`,`newsarticle`.`PublicationDate` AS `PublicationDate`,`newsarticle`.`Content` AS `Content`,`newsarticle`.`Tags` AS `Tags`,`newsarticle`.`Source` AS `Source` from `newsarticle`;

-- --------------------------------------------------------

--
-- Structure for view `allnotificationlogdata`
--
DROP TABLE IF EXISTS `allnotificationlogdata`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `allnotificationlogdata` AS select `notificationlog`.`LogID` AS `LogID`,`notificationlog`.`UserID` AS `UserID`,`notificationlog`.`NotificationType` AS `NotificationType`,`notificationlog`.`Timestamp` AS `Timestamp`,`notificationlog`.`Status` AS `Status` from `notificationlog`;

-- --------------------------------------------------------

--
-- Structure for view `allreadinghistorydata`
--
DROP TABLE IF EXISTS `allreadinghistorydata`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `allreadinghistorydata` AS select `readinghistory`.`HistoryID` AS `HistoryID`,`readinghistory`.`UserID` AS `UserID`,`readinghistory`.`ArticleID` AS `ArticleID`,`readinghistory`.`Timestamp` AS `Timestamp`,`readinghistory`.`ReadingDuration` AS `ReadingDuration` from `readinghistory`;

-- --------------------------------------------------------

--
-- Structure for view `alluserprofiledata`
--
DROP TABLE IF EXISTS `alluserprofiledata`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `alluserprofiledata` AS select `userprofile`.`UserID` AS `UserID`,`userprofile`.`Username` AS `Username`,`userprofile`.`Email` AS `Email`,`userprofile`.`Preferences` AS `Preferences`,`userprofile`.`ReadingHistory` AS `ReadingHistory`,`userprofile`.`SubscriptionStatus` AS `SubscriptionStatus` from `userprofile`;

-- --------------------------------------------------------

--
-- Structure for view `insertdigestconfigurationdigestid`
--
DROP TABLE IF EXISTS `insertdigestconfigurationdigestid`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `insertdigestconfigurationdigestid` AS select `digestconfiguration`.`DigestID` AS `DigestID` from `digestconfiguration`;

-- --------------------------------------------------------

--
-- Structure for view `insertnewsarticlearticleid`
--
DROP TABLE IF EXISTS `insertnewsarticlearticleid`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `insertnewsarticlearticleid` AS select `newsarticle`.`ArticleID` AS `ArticleID` from `newsarticle`;

-- --------------------------------------------------------

--
-- Structure for view `insertnotificationloglogid`
--
DROP TABLE IF EXISTS `insertnotificationloglogid`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `insertnotificationloglogid` AS select `notificationlog`.`LogID` AS `LogID` from `notificationlog`;

-- --------------------------------------------------------

--
-- Structure for view `insertreadinghistoryhistoryid`
--
DROP TABLE IF EXISTS `insertreadinghistoryhistoryid`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `insertreadinghistoryhistoryid` AS select `readinghistory`.`HistoryID` AS `HistoryID` from `readinghistory`;

-- --------------------------------------------------------

--
-- Structure for view `insertuserprofileuserid`
--
DROP TABLE IF EXISTS `insertuserprofileuserid`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `insertuserprofileuserid` AS select `userprofile`.`UserID` AS `UserID` from `userprofile`;

-- --------------------------------------------------------

--
-- Structure for view `totalreadingdurationperuser`
--
DROP TABLE IF EXISTS `totalreadingdurationperuser`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `totalreadingdurationperuser` AS select `readinghistory`.`UserID` AS `UserID`,sum(`readinghistory`.`ReadingDuration`) AS `TotalDuration` from `readinghistory` group by `readinghistory`.`UserID`;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `digestconfiguration`
--
ALTER TABLE `digestconfiguration`
  ADD CONSTRAINT `digestconfiguration_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `userprofile` (`UserID`);

--
-- Constraints for table `notificationlog`
--
ALTER TABLE `notificationlog`
  ADD CONSTRAINT `notificationlog_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `userprofile` (`UserID`);

--
-- Constraints for table `readinghistory`
--
ALTER TABLE `readinghistory`
  ADD CONSTRAINT `readinghistory_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `userprofile` (`UserID`),
  ADD CONSTRAINT `readinghistory_ibfk_2` FOREIGN KEY (`ArticleID`) REFERENCES `newsarticle` (`ArticleID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
