-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 17, 2020 at 12:05 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `myschooldb`
--

-- --------------------------------------------------------

--
-- Table structure for table `parent_table`
--

CREATE TABLE `parent_table` (
  `id` int(11) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `childName` int(10) NOT NULL,
  `gradeChild` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parent_table`
--

INSERT INTO `parent_table` (`id`, `fullname`, `email`, `password`, `childName`, `gradeChild`) VALUES
(1, 'Stoqn Petkov Petkov', 'stoqn@abv.bg', '5f4dcc3b5aa765d61d8327deb882cf99', 2, '10'),
(2, 'Gergana Borisova Savova', 'gergana@abv.bg', '5f4dcc3b5aa765d61d8327deb882cf99', 3, '10'),
(3, 'Nina Ivanova Boteva', 'nina@abv.bg', '5f4dcc3b5aa765d61d8327deb882cf99', 4, '8'),
(4, 'Ivanka Todorova Todorova', 'ivanka@abv.bg', '5f4dcc3b5aa765d61d8327deb882cf99', 5, '8'),
(6, 'Darina Angelova Angelova', 'darina@abv.bg', '5f4dcc3b5aa765d61d8327deb882cf99', 7, '8');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `parent_table`
--
ALTER TABLE `parent_table`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `childName` (`childName`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `parent_table`
--
ALTER TABLE `parent_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `parent_table`
--
ALTER TABLE `parent_table`
  ADD CONSTRAINT `parent_table_ibfk_1` FOREIGN KEY (`childName`) REFERENCES `student_table` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
