-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 05, 2020 at 01:34 PM
-- Server version: 5.7.28-0ubuntu0.19.04.2
-- PHP Version: 7.2.24-0ubuntu0.19.04.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `retail_store_management_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `order_name` varchar(255) NOT NULL,
  `order_mobile` varchar(255) NOT NULL,
  `order_date` varchar(255) NOT NULL,
  `order_total` varchar(255) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `order_name`, `order_mobile`, `order_date`, `order_total`) VALUES
(1, 'Kaushal Kishore', '8376986802', '17/01/2020 14:39:03', '340'),
(2, 'Amit Kumar', '8978654543', '17/01/2020 14:39:03', '110'),
(3, 'Sumit Kumar', '7876564543', '17/01/2020 14:39:03', '3330'),
(4, 'Anil Singh', '9878654354', '17/01/2020 14:39:03', '1290'),
(5, 'Sunil Singh', '9871343234', '17/01/2020 14:39:03', '1387'),
(6, 'Suresh Kumar', '8978765456', '17/01/2020 14:39:03', '450'),
(26, 'Ramesh Kumar', '8976545676', '17/01/2020 14:39:03', '5900'),
(27, 'Ajit Kumar', '9878965456', '17/01/2020 14:40:59', '3200'),
(28, 'Sujit KUmar', '7865429876', '17/01/2020 14:42:13', '250'),
(29, 'Ajay', '8987678765', '17/01/2020 15:09:20', '220'),
(30, 'Rahul', '7867654565', '17/01/2020 16:50:31', '100'),
(31, 'Uday', '9876787654', '17/01/2020 20:12:09', '240'),
(32, 'Amit', '9876543212', '05/06/2020 13:20:14', '230');

-- --------------------------------------------------------

--
-- Table structure for table `order_item`
--

CREATE TABLE `order_item` (
  `oi_id` int(11) NOT NULL,
  `oi_order_id` varchar(255) NOT NULL,
  `oi_product_id` varchar(255) NOT NULL,
  `oi_quantity` varchar(255) NOT NULL,
  `oi_cost` varchar(255) NOT NULL,
  `oi_total` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_item`
--

INSERT INTO `order_item` (`oi_id`, `oi_order_id`, `oi_product_id`, `oi_quantity`, `oi_cost`, `oi_total`) VALUES
(1, '12', '8', '3', '20', '60'),
(2, '13', '2', '1', '10', '10'),
(3, '13', '10', '3', '40', '120'),
(4, '14', '2', '1', '10', '10'),
(5, '15', '2', '1', '10', '10'),
(6, '15', '10', '3', '40', '120'),
(7, '15', '8', '3', '20', '60'),
(8, '16', '2', '1', '10', '10'),
(9, '17', '8', '1', '20', '20'),
(11, '19', '8', '1', '20', '20'),
(13, '21', '9', '1', '30', '30'),
(14, '21', '10', '1', '40', '40'),
(15, '21', '9', '1', '30', '30'),
(16, '21', '8', '3', '20', '60'),
(17, '21', '8', '3', '20', '60'),
(18, '21', '8', '3', '20', '60'),
(20, '21', '8', '3', '20', '60'),
(21, '21', '8', '3', '20', '60'),
(22, '22', '8', '1', '20', '20'),
(23, '22', '10', '1', '40', '40'),
(24, '23', '8', '1', '20', '20'),
(25, '24', '8', '1', '20', '20'),
(26, '25', '8', '1', '20', '20'),
(27, '25', '10', '1', '40', '40'),
(28, '26', '2', '3', '10', '30'),
(29, '26', '10', '2', '40', '80'),
(30, '27', '8', '4', '20', '80'),
(31, '27', '10', '4', '40', '160'),
(32, '28', '2', '3', '10', '30'),
(33, '28', '10', '3', '40', '120'),
(34, '29', '2', '1', '10', '10'),
(35, '29', '9', '3', '30', '90'),
(36, '29', '8', '2', '20', '40'),
(37, '29', '10', '2', '40', '80'),
(38, '30', '2', '1', '10', '10'),
(39, '30', '9', '1', '30', '30'),
(40, '30', '8', '1', '20', '20'),
(41, '30', '10', '1', '40', '40'),
(42, '31', '2', '2', '10', '20'),
(43, '31', '8', '1', '20', '20'),
(44, '31', '10', '5', '40', '200'),
(45, '32', '1', '1', '10', '10'),
(46, '32', '4', '1', '40', '40'),
(47, '32', '6', '1', '150', '150'),
(48, '32', '3', '1', '30', '30');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_cost` varchar(255) NOT NULL,
  `product_company` varchar(255) NOT NULL,
  `product_type` varchar(255) NOT NULL,
  `prduct_description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_id`, `product_name`, `product_cost`, `product_company`, `product_type`, `prduct_description`) VALUES
(1, 'Parle G', '10', 'Parle', 'Biscuits', 'Parle G'),
(2, 'Vim Bar', '20', 'Vim', 'Soaps', 'Vim Bar'),
(3, 'Haldiram Bhunjia', '30', 'Haldiram', 'Food Item', 'Haldiram Bhunjia'),
(4, 'Fortune Oil', '40', 'Fortune', 'Oil', 'Fortune Oil'),
(5, 'Lays Chips', '10', 'Lays', 'Chips', 'Lays Chips'),
(6, 'Face Cream', '150', 'Lotus', 'Cream', 'Lotus'),
(7, 'Mustard Oil', '120', 'Organic', 'Oil', 'Mustard Oil'),
(8, 'Toor Pulse', '150', 'Organic', 'Pulse', 'Pulse'),
(9, 'Basmati Rice', '200', 'India Gate', 'Rice', 'Basmati Rice'),
(10, 'Brown Bread', '50', 'Family', 'Bread', 'Brown Bread');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`oi_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `order_item`
--
ALTER TABLE `order_item`
  MODIFY `oi_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
