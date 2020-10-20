-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 21, 2020 at 12:38 AM
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
-- Table structure for table `homework_completed_table`
--

CREATE TABLE `homework_completed_table` (
  `id` int(11) NOT NULL,
  `grade` varchar(10) NOT NULL,
  `homework` text NOT NULL,
  `id_fullname` int(10) NOT NULL,
  `id_subject` int(10) NOT NULL,
  `key_homework` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `homework_completed_table`
--

INSERT INTO `homework_completed_table` (`id`, `grade`, `homework`, `id_fullname`, `id_subject`, `key_homework`) VALUES
(1, '10', 'http://192.168.100.3/MySchool/StudentUpload/751181032_1602857286.jpg', 2, 1, 2),
(2, '10', 'http://192.168.0.167/MySchool/StudentUpload/219590150_1602857319.jpg', 2, 4, 9),
(3, '10', 'http://192.168.0.167/MySchool/StudentUpload/2073326223_1602857502.jpg', 1, 1, 1),
(4, '10', 'http://192.168.0.167/MySchool/StudentUpload/548321195_1602857521.jpg', 1, 2, 7);

-- --------------------------------------------------------

--
-- Table structure for table `homework_forstudent_table`
--

CREATE TABLE `homework_forstudent_table` (
  `id` int(11) NOT NULL,
  `grade` varchar(5) NOT NULL,
  `homework` varchar(75) NOT NULL,
  `id_fullname` int(10) NOT NULL,
  `id_subject` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `homework_forstudent_table`
--

INSERT INTO `homework_forstudent_table` (`id`, `grade`, `homework`, `id_fullname`, `id_subject`) VALUES
(1, '10', 'http://192.168.100.3/MySchool/TeacherUpload/930416394_1602856938.jpg', 1, 1),
(2, '10', 'http://192.168.100.3/MySchool/TeacherUpload/930416394_1602856938.jpg', 2, 1),
(3, '10', 'http://192.168.100.3/MySchool/TeacherUpload/930416394_1602856938.jpg', 3, 1),
(4, '8', 'http://192.168.100.3/MySchool/TeacherUpload/1315611731_1602856974.jpg', 4, 2),
(5, '8', 'http://192.168.100.3/MySchool/TeacherUpload/1315611731_1602856974.jpg', 5, 2),
(6, '8', 'http://192.168.100.3/MySchool/TeacherUpload/1315611731_1602856974.jpg', 7, 2),
(7, '10', 'http://192.168.100.3/MySchool/TeacherUpload/616713205_1602857005.jpg', 1, 2),
(8, '10', 'http://192.168.100.3/MySchool/TeacherUpload/1069725921_1602857148.jpg', 1, 4),
(9, '10', 'http://192.168.100.3/MySchool/TeacherUpload/1069725921_1602857148.jpg', 2, 4),
(10, '10', 'http://192.168.100.3/MySchool/TeacherUpload/1069725921_1602857148.jpg', 3, 4);

-- --------------------------------------------------------

--
-- Table structure for table `marks_table`
--

CREATE TABLE `marks_table` (
  `id` int(11) NOT NULL,
  `mark` int(11) NOT NULL,
  `id_subject` int(11) NOT NULL,
  `id_student` int(11) NOT NULL,
  `key_homework` int(11) NOT NULL,
  `grade` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `marks_table`
--

INSERT INTO `marks_table` (`id`, `mark`, `id_subject`, `id_student`, `key_homework`, `grade`) VALUES
(1, 6, 1, 1, 2, '10'),
(2, 6, 1, 2, 1, '10'),
(3, 6, 4, 2, 1, '10'),
(4, 5, 4, 2, 9, '10');

-- --------------------------------------------------------

--
-- Table structure for table `parent_messege_table`
--

CREATE TABLE `parent_messege_table` (
  `id` int(11) NOT NULL,
  `text_message` varchar(300) NOT NULL,
  `id_name_teacher` int(10) NOT NULL,
  `id_name_parent` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `parent_messege_table`
--

INSERT INTO `parent_messege_table` (`id`, `text_message`, `id_name_teacher`, `id_name_parent`) VALUES
(1, 'Hello dear parents!', 2, 3),
(2, 'Hello dear parents!', 2, 4),
(3, 'Hello I see ......', 1, 1);

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

-- --------------------------------------------------------

--
-- Table structure for table `student_table`
--

CREATE TABLE `student_table` (
  `id` int(11) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `grade` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_table`
--

INSERT INTO `student_table` (`id`, `fullname`, `email`, `password`, `grade`) VALUES
(1, 'Stoqn Ivanov Stoqnov', 'stoqn1@abv.bg', '5f4dcc3b5aa765d61d8327deb882cf99', '10'),
(2, 'Iliqna Miteva Todorova', 'iliqna@abv.bg', '5f4dcc3b5aa765d61d8327deb882cf99', '10'),
(3, 'Martin Dimitrov Dimitrov', 'martin@abv.bg', '5f4dcc3b5aa765d61d8327deb882cf99', '10'),
(4, 'Sara Ivanova Dimova', 'sara@abv.bg', '5f4dcc3b5aa765d61d8327deb882cf99', '8'),
(5, 'Magi Stefanova Todorova', 'magi@abv.bg', '5f4dcc3b5aa765d61d8327deb882cf99', '8'),
(6, 'Radost Stoqnova Petrova', 'radost@abv.bg', '5f4dcc3b5aa765d61d8327deb882cf99', '7'),
(7, 'Mihaela Veleva Staneva', 'mihaela@abv.bg', '5f4dcc3b5aa765d61d8327deb882cf99', '8');

-- --------------------------------------------------------

--
-- Table structure for table `subject_table`
--

CREATE TABLE `subject_table` (
  `id` int(11) NOT NULL,
  `subject` varchar(75) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subject_table`
--

INSERT INTO `subject_table` (`id`, `subject`) VALUES
(1, 'Geography'),
(2, 'History'),
(3, 'Mathematics'),
(4, 'Music'),
(5, 'Biology'),
(6, 'Chemistry'),
(7, 'Physics');

-- --------------------------------------------------------

--
-- Table structure for table `teacher_messege_table`
--

CREATE TABLE `teacher_messege_table` (
  `id` int(11) NOT NULL,
  `id_name_parent` int(11) NOT NULL,
  `id_name_teacher` int(11) NOT NULL,
  `text_message` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teacher_messege_table`
--

INSERT INTO `teacher_messege_table` (`id`, `id_name_parent`, `id_name_teacher`, `text_message`) VALUES
(1, 3, 1, 'Hi ! I am so sorry:) !'),
(2, 3, 2, 'Hi ! Thank you very much !');

-- --------------------------------------------------------

--
-- Table structure for table `teacher_table`
--

CREATE TABLE `teacher_table` (
  `id` int(11) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `teacher_table`
--

INSERT INTO `teacher_table` (`id`, `fullname`, `email`, `password`) VALUES
(1, 'Ivan Ivanov Ivanov', 'ivan@abv.bg', '5f4dcc3b5aa765d61d8327deb882cf99'),
(2, 'Stefka Stefanova Stefanova', 'stefka@abv.bg', '5f4dcc3b5aa765d61d8327deb882cf99'),
(3, 'Angel Angelov Angelov', 'angel@abv.bg', '5f4dcc3b5aa765d61d8327deb882cf99'),
(4, 'Simeon Lazarov Ninov', 'simeon@abv.bg', '5f4dcc3b5aa765d61d8327deb882cf99');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `homework_completed_table`
--
ALTER TABLE `homework_completed_table`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_subject` (`id_subject`),
  ADD KEY `key_homework` (`key_homework`),
  ADD KEY `id_fullname` (`id_fullname`);

--
-- Indexes for table `homework_forstudent_table`
--
ALTER TABLE `homework_forstudent_table`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_subject` (`id_subject`),
  ADD KEY `id_fullname` (`id_fullname`);

--
-- Indexes for table `marks_table`
--
ALTER TABLE `marks_table`
  ADD PRIMARY KEY (`id`),
  ADD KEY `marks_table_ibfk_2` (`id_subject`),
  ADD KEY `key_homework` (`key_homework`),
  ADD KEY `id_student` (`id_student`);

--
-- Indexes for table `parent_messege_table`
--
ALTER TABLE `parent_messege_table`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_name_parent` (`id_name_parent`),
  ADD KEY `id_name_teacher` (`id_name_teacher`);

--
-- Indexes for table `parent_table`
--
ALTER TABLE `parent_table`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `childName` (`childName`);

--
-- Indexes for table `student_table`
--
ALTER TABLE `student_table`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `subject_table`
--
ALTER TABLE `subject_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `teacher_messege_table`
--
ALTER TABLE `teacher_messege_table`
  ADD PRIMARY KEY (`id`),
  ADD KEY `teacher_messege_table_ibfk_1` (`id_name_parent`),
  ADD KEY `id_name_teacher` (`id_name_teacher`);

--
-- Indexes for table `teacher_table`
--
ALTER TABLE `teacher_table`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `homework_completed_table`
--
ALTER TABLE `homework_completed_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `homework_forstudent_table`
--
ALTER TABLE `homework_forstudent_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `marks_table`
--
ALTER TABLE `marks_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `parent_messege_table`
--
ALTER TABLE `parent_messege_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `parent_table`
--
ALTER TABLE `parent_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `student_table`
--
ALTER TABLE `student_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `subject_table`
--
ALTER TABLE `subject_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `teacher_messege_table`
--
ALTER TABLE `teacher_messege_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `teacher_table`
--
ALTER TABLE `teacher_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `homework_completed_table`
--
ALTER TABLE `homework_completed_table`
  ADD CONSTRAINT `homework_completed_table_ibfk_2` FOREIGN KEY (`id_subject`) REFERENCES `subject_table` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `homework_completed_table_ibfk_3` FOREIGN KEY (`key_homework`) REFERENCES `homework_forstudent_table` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `homework_completed_table_ibfk_4` FOREIGN KEY (`id_fullname`) REFERENCES `student_table` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `homework_forstudent_table`
--
ALTER TABLE `homework_forstudent_table`
  ADD CONSTRAINT `homework_forstudent_table_ibfk_2` FOREIGN KEY (`id_subject`) REFERENCES `subject_table` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `homework_forstudent_table_ibfk_3` FOREIGN KEY (`id_fullname`) REFERENCES `student_table` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `marks_table`
--
ALTER TABLE `marks_table`
  ADD CONSTRAINT `marks_table_ibfk_2` FOREIGN KEY (`id_subject`) REFERENCES `subject_table` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `marks_table_ibfk_3` FOREIGN KEY (`key_homework`) REFERENCES `homework_completed_table` (`key_homework`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `marks_table_ibfk_4` FOREIGN KEY (`id_student`) REFERENCES `student_table` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `parent_messege_table`
--
ALTER TABLE `parent_messege_table`
  ADD CONSTRAINT `parent_messege_table_ibfk_2` FOREIGN KEY (`id_name_parent`) REFERENCES `parent_table` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `parent_messege_table_ibfk_3` FOREIGN KEY (`id_name_teacher`) REFERENCES `teacher_table` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `parent_table`
--
ALTER TABLE `parent_table`
  ADD CONSTRAINT `parent_table_ibfk_1` FOREIGN KEY (`childName`) REFERENCES `student_table` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `teacher_messege_table`
--
ALTER TABLE `teacher_messege_table`
  ADD CONSTRAINT `teacher_messege_table_ibfk_1` FOREIGN KEY (`id_name_parent`) REFERENCES `parent_table` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `teacher_messege_table_ibfk_2` FOREIGN KEY (`id_name_teacher`) REFERENCES `teacher_table` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
