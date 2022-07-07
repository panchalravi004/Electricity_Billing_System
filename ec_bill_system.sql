-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 07, 2022 at 09:49 AM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ec_bill_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `meterno` int(10) NOT NULL,
  `fname` varchar(10) NOT NULL,
  `lname` varchar(10) NOT NULL,
  `district` varchar(25) NOT NULL,
  `city` varchar(25) NOT NULL,
  `mobile` int(10) NOT NULL,
  `pincode` int(6) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`meterno`, `fname`, `lname`, `district`, `city`, `mobile`, `pincode`, `id`) VALUES
(101, 'ravi', 'panchal', 'sabarkantha', 'himmatnagar', 123456, 383001, 1),
(102, 'jack', 'panchal', 'sabarkantha', 'himmatnagar', 654321, 383001, 2),
(104, 'Priyank', 'Sukhadiya', 'sabarkantha', 'himmatnagar', 654321, 383001, 6),
(105, 'Meet', 'panchal', 'sabarkantha', 'himmatnagar', 654321, 383001, 7),
(106, 'Parth', 'panchal', 'sabarkantha', 'himmatnagar', 789456, 383001, 8);

-- --------------------------------------------------------

--
-- Table structure for table `paid_data`
--

CREATE TABLE `paid_data` (
  `meterno` int(10) NOT NULL,
  `year` varchar(15) NOT NULL,
  `month` varchar(15) NOT NULL,
  `amt` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `paid_data`
--

INSERT INTO `paid_data` (`meterno`, `year`, `month`, `amt`) VALUES
(101, '2021', 'January', 1678),
(101, '2021', 'February', 1744),
(101, '2021', 'March', 1580),
(106, '2021', 'January', 2016);

-- --------------------------------------------------------

--
-- Table structure for table `unit_data`
--

CREATE TABLE `unit_data` (
  `meterno` int(11) NOT NULL,
  `year` varchar(10) NOT NULL,
  `month` varchar(15) NOT NULL,
  `unit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `unit_data`
--

INSERT INTO `unit_data` (`meterno`, `year`, `month`, `unit`) VALUES
(105, '2021', 'January', 100),
(101, '2021', 'January', 154),
(104, '2021', 'January', 160),
(102, '2021', 'January', 108),
(101, '2021', 'February', 160),
(106, '2021', 'January', 185),
(101, '2021', 'March', 145);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
