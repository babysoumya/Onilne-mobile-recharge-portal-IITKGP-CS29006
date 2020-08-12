-- phpMyAdmin SQL Dump
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 24, 2020 at 09:00 PM


SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `OMR`
--

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `Username` varchar(50) NOT NULL DEFAULT '',
  `Password` varchar(50) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Phone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`Username`, `Password`) VALUES
('admin', 'root');

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE IF NOT EXISTS `registration` (
  `Username` varchar(50) NOT NULL DEFAULT '',
  `Password` varchar(50) DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `emailId` varchar(50) DEFAULT NULL,
  `Phone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `plan`
--

CREATE TABLE IF NOT EXISTS `plan` (
  `Id number` int(11) NOT NULL AUTO_INCREMENT,
  `Operator` varchar(50) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Amount` varchar(50) DEFAULT NULL,
  `Data` varchar(50) DEFAULT NULL,
  `Validity` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id number`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plan`
--

INSERT INTO `login` (`Operator`, `Type`, `Amount`, `Data`, `Validity`) VALUES
('Vodafone', 'Prepaid', '299', '2GB/day', '28 days');

-- --------------------------------------------------------

--
-- Table structure for table `offer`
--

CREATE TABLE IF NOT EXISTS `offer` (
  `Id number` int(11) NOT NULL AUTO_INCREMENT,
  `Discount` int(11) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
 
  PRIMARY KEY (`Id number`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `offer`
--

INSERT INTO `offer` (`Discount`, `Description`) VALUES
('10', 'Offer 1');

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE IF NOT EXISTS `notification` (
  `Id number` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) DEFAULT NULL,
  `Date` Date NOT NULL DEFAULT current_timestamp(),
  `Time` Time NOT NULL DEFAULT current_timestamp(),
  `Subject` varchar(50) DEFAULT NULL,
  `Description` varchar(100) DEFAULT NULL, 
  `View Status` varchar(10) DEFAULT 'False',
  PRIMARY KEY (`Id number`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE IF NOT EXISTS `feedback` (
  `Id number` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) DEFAULT NULL,
  `Feedback` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id number`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE IF NOT EXISTS `bills` (
  `Id number` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) DEFAULT NULL,
  `Phone` varchar(50) DEFAULT NULL,
  `Plan Id` int(11) DEFAULT NULL,
  `Amount` int(11) DEFAULT NULL,
  `Date` varchar(50) NOT NULL DEFAULT current_timestamp(),
  `Deadline` Date DEFAULT NULL,
  PRIMARY KEY (`Id number`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `amount`
--

CREATE TABLE IF NOT EXISTS `amount` (
  `Username` varchar(50) NOT NULL DEFAULT '',
  `Amount` int(11) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `amount`
--

INSERT INTO `amount` (`Username`, `Amount`, `Type`) VALUES
('admin', 'root', '1000000');

-- --------------------------------------------------------

--
-- Table structure for table `trans`
--

CREATE TABLE IF NOT EXISTS `trans` (
  `Id number` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) DEFAULT NULL,
  `Amount` int(11) DEFAULT NULL,
  `Date` Date NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`Id number`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `Id number` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) DEFAULT NULL,
  `Bill Id` varchar(50) DEFAULT NULL,
  `Phone` varchar(50) DEFAULT NULL,
  `Plan Id` varchar(50) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Date` Date NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`Id number`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
