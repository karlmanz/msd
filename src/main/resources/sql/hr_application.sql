-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 22, 2022 at 09:37 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hr_application`
--

-- --------------------------------------------------------

--
-- Table structure for table `attempts`
--

CREATE TABLE `attempts` (
  `id` int(11) NOT NULL,
  `attempts` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `docs_filetopic`
--

CREATE TABLE `docs_filetopic` (
  `doc_id` bigint(20) NOT NULL,
  `filetopic_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `document_main`
--

CREATE TABLE `document_main` (
  `DM_ID` int(11) NOT NULL,
  `DM_REF_NUM_ID` int(11) DEFAULT NULL,
  `DM_IS_STAFF` tinyint(1) NOT NULL,
  `DM_STAFF_ID` varchar(30) NOT NULL,
  `DM_RECIPIENT_NAME` varchar(300) NOT NULL,
  `DM_ADDRESS` varchar(500) NOT NULL,
  `DM_TITLE` varchar(500) NOT NULL,
  `DM_CONTENT` blob DEFAULT NULL,
  `DM_DOC_DATE` date DEFAULT NULL,
  `DM_CONTENT_SIGN` varchar(500) DEFAULT NULL,
  `DM_CONTENT_CC` varchar(500) DEFAULT NULL,
  `DM_IS_CONFIDENTIAL` tinyint(1) NOT NULL,
  `DM_STATUS` varchar(10) DEFAULT NULL,
  `DM_ENTER_BY` varchar(255) DEFAULT NULL,
  `DM_ENTER_DATE` datetime DEFAULT current_timestamp(),
  `DM_UPDATE_BY` varchar(10) DEFAULT NULL,
  `DM_UPDATE_DATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `document_review`
--

CREATE TABLE `document_review` (
  `DR_ID` int(11) NOT NULL,
  `DR_SUBMISSION_DATE` datetime DEFAULT NULL,
  `DR_REF_CODE_ID` varchar(30) DEFAULT NULL,
  `DR_DOCUMENT_ID` int(11) DEFAULT NULL,
  `DR_COMMENT_DATE` datetime DEFAULT NULL,
  `DR_COMMENT` varchar(4000) DEFAULT NULL,
  `DR_SUPERVISOR_ID` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `file_reference_number`
--

CREATE TABLE `file_reference_number` (
  `FRN_ID` int(11) NOT NULL,
  `FRN_REF_CODE_ID` varchar(30) DEFAULT NULL,
  `FRN_TOPIC_REF_ID` int(11) NOT NULL,
  `FRN_CLASS` varchar(10) DEFAULT NULL,
  `FRN_HURIS_DEPT` varchar(30) DEFAULT NULL,
  `FRN_ORG_ID` int(11) DEFAULT NULL,
  `FRN_DEPARTMENT_NO` int(11) DEFAULT NULL,
  `FRN_DATE_START` date DEFAULT NULL,
  `FRN_DATE_END` date DEFAULT NULL,
  `FRN_ENTER_BY` varchar(10) DEFAULT NULL,
  `FRN_ENTER_DATE` datetime DEFAULT NULL,
  `FRN_UPDATE_BY` varchar(10) DEFAULT NULL,
  `FRN_UPDATE_DATE` datetime DEFAULT NULL,
  `frn_subtopic_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `file_subtopic`
--

CREATE TABLE `file_subtopic` (
  `FS_ID` int(11) NOT NULL,
  `FS_TOPIC_REF_ID` int(11) NOT NULL,
  `FS_SUB_NO_1` int(11) DEFAULT NULL,
  `FS_SUB_TOPIC_1` varchar(500) DEFAULT NULL,
  `FS_SUB_NO_2` int(11) DEFAULT NULL,
  `FS_SUB_TOPIC_2` varchar(500) DEFAULT NULL,
  `FS_SUB_NO_3` int(11) DEFAULT NULL,
  `FS_SUB_TOPIC_3` varchar(500) DEFAULT NULL,
  `FS_DATE_START` date DEFAULT NULL,
  `FS_DATE_END` date DEFAULT NULL,
  `FS_ENTER_BY` varchar(10) DEFAULT NULL,
  `FS_ENTER_DATE` datetime DEFAULT NULL,
  `FS_UPDATE_BY` varchar(10) DEFAULT NULL,
  `FS_UPDATE_DATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `FILE_TOPIC`
--

CREATE TABLE `FILE_TOPIC` (
  `FT_ID` int(11) NOT NULL,
  `FT_TOPIC_REF_ID` int(11) NOT NULL,
  `FT_TITLE` varchar(500) NOT NULL,
  `FT_CLASS` varchar(10) NOT NULL,
  `FT_VOLUME_NO` int(11) NOT NULL,
  `FT_DATE_START` date DEFAULT NULL,
  `FT_DATE_END` date DEFAULT NULL,
  `FT_ENTER_BY` varchar(10) DEFAULT NULL,
  `FT_ENTER_DATE` datetime DEFAULT NULL,
  `FT_UPDATE_BY` varchar(10) DEFAULT NULL,
  `FT_UPDATE_DATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `ORGANIZATION_MAIN`
--

CREATE TABLE `ORGANIZATION_MAIN` (
  `OM_ID` int(11) NOT NULL,
  `OM_ORG_ACRONYM` varchar(30) DEFAULT NULL,
  `OM_ORG_TITLE` varchar(300) DEFAULT NULL,
  `OM_ORG_STATUS` varchar(30) DEFAULT NULL,
  `OM_ENTER_BY` varchar(10) DEFAULT NULL,
  `OM_ENTER_DATE` datetime DEFAULT NULL,
  `OM_UPDATE_BY` varchar(10) DEFAULT NULL,
  `OM_UPDATE_DATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `ROLE_MAIN`
--

CREATE TABLE `ROLE_MAIN` (
  `RM_ROLE_ID` bigint(20) NOT NULL,
  `RM_ROLE_NAME` varchar(30) NOT NULL,
  `RM_ROLE_DESCRIPTION` varchar(500) DEFAULT NULL,
  `RM_DATE_START` date DEFAULT NULL,
  `RM_DATE_END` date DEFAULT NULL,
  `RM_ENTER_BY` varchar(10) DEFAULT NULL,
  `RM_ENTER_DATE` datetime DEFAULT NULL,
  `RM_UPDATE_BY` varchar(10) DEFAULT NULL,
  `RM_UPDATE_DATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `TEMPLATE_MAIN`
--

CREATE TABLE `TEMPLATE_MAIN` (
  `TM_ID` int(11) NOT NULL,
  `TM_REF_CODE_ID` varchar(30) NOT NULL,
  `TM_TITLE` varchar(500) NOT NULL,
  `TM_CONTENT` blob NOT NULL,
  `TM_CONTENT_SIGN` varchar(4000) DEFAULT NULL,
  `TM_CONTENT_CC` varchar(4000) DEFAULT NULL,
  `TM_DATE_START` date DEFAULT NULL,
  `TM_DATE_END` date DEFAULT NULL,
  `TM_ENTER_BY` varchar(10) DEFAULT NULL,
  `TM_ENTER_DATE` datetime DEFAULT NULL,
  `TM_UPDATE_BY` varchar(10) DEFAULT NULL,
  `TM_UPDATE_DATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UR_STAFF_USERNAME` varchar(100) NOT NULL,
  `UR_STAFF_ID` varchar(30) NOT NULL,
  `UR_ACCOUNT_NON_LOCKED` bit(1) DEFAULT NULL,
  `UR_STAFF_NAME` varchar(255) DEFAULT NULL,
  `UR_PASSWORD` varchar(255) DEFAULT NULL,
  `UR_EMAIL` varchar(255) DEFAULT NULL,
  `UR_HURIS_DEPT` varchar(30) DEFAULT NULL,
  `UR_DATE_START` datetime DEFAULT NULL,
  `UR_DATE_END` datetime DEFAULT NULL,
  `UR_ENTER_BY` varchar(255) DEFAULT NULL,
  `UR_ENTER_DATE` datetime DEFAULT NULL,
  `UR_UPDATE_BY` varchar(255) DEFAULT NULL,
  `UR_UPDATE_DATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` varchar(100) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Stand-in structure for view `view_notification`
-- (See below for the actual view)
--
CREATE TABLE `view_notification` (
`notify_from` varchar(255)
,`notify_to` varchar(255)
,`notify_doc_id` int(11)
,`notify_title` varchar(500)
,`notify_status` varchar(10)
,`notify_action` varchar(10)
,`notify_date` datetime
);

-- --------------------------------------------------------

--
-- Structure for view `view_notification`
--
DROP TABLE IF EXISTS `view_notification`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_notification`  AS SELECT ifnull(`document_main`.`DM_UPDATE_BY`,`document_main`.`DM_ENTER_BY`) AS `notify_from`, `document_review`.`DR_SUPERVISOR_ID` AS `notify_to`, `document_main`.`DM_ID` AS `notify_doc_id`, `document_main`.`DM_TITLE` AS `notify_title`, `document_main`.`DM_STATUS` AS `notify_status`, `document_main`.`DM_STATUS` AS `notify_action`, ifnull(`document_main`.`DM_UPDATE_DATE`,`document_main`.`DM_ENTER_DATE`) AS `notify_date` FROM (`document_main` join `document_review`) WHERE `document_main`.`DM_ID` = `document_review`.`DR_DOCUMENT_ID` AND `document_main`.`DM_STATUS` = 'Submitted' ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attempts`
--
ALTER TABLE `attempts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `docs_filetopic`
--
ALTER TABLE `docs_filetopic`
  ADD PRIMARY KEY (`doc_id`,`filetopic_id`);

--
-- Indexes for table `document_main`
--
ALTER TABLE `document_main`
  ADD PRIMARY KEY (`DM_ID`),
  ADD KEY `FKpwrkti0ionwtc2fhkeb6q150j` (`DM_REF_NUM_ID`);

--
-- Indexes for table `document_review`
--
ALTER TABLE `document_review`
  ADD PRIMARY KEY (`DR_ID`),
  ADD KEY `FK_DR_DM` (`DR_DOCUMENT_ID`) USING BTREE;

--
-- Indexes for table `file_reference_number`
--
ALTER TABLE `file_reference_number`
  ADD PRIMARY KEY (`FRN_ID`),
  ADD KEY `FKmtt01awiv02ols3sjf4dmems1` (`FRN_TOPIC_REF_ID`),
  ADD KEY `FKbbuchgjgu5yplqrxw64avjy1b` (`FRN_ORG_ID`);

--
-- Indexes for table `file_subtopic`
--
ALTER TABLE `file_subtopic`
  ADD PRIMARY KEY (`FS_ID`),
  ADD KEY `FKm476fo9395oj2bny3kshsi1in` (`FS_TOPIC_REF_ID`);

--
-- Indexes for table `FILE_TOPIC`
--
ALTER TABLE `FILE_TOPIC`
  ADD PRIMARY KEY (`FT_ID`);

--
-- Indexes for table `ORGANIZATION_MAIN`
--
ALTER TABLE `ORGANIZATION_MAIN`
  ADD PRIMARY KEY (`OM_ID`);

--
-- Indexes for table `ROLE_MAIN`
--
ALTER TABLE `ROLE_MAIN`
  ADD PRIMARY KEY (`RM_ROLE_ID`);

--
-- Indexes for table `TEMPLATE_MAIN`
--
ALTER TABLE `TEMPLATE_MAIN`
  ADD PRIMARY KEY (`TM_ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UR_STAFF_USERNAME`);

--
-- Indexes for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKdth3rwgk05t91ovapcelmiokl` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attempts`
--
ALTER TABLE `attempts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `document_main`
--
ALTER TABLE `document_main`
  MODIFY `DM_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `document_review`
--
ALTER TABLE `document_review`
  MODIFY `DR_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `file_reference_number`
--
ALTER TABLE `file_reference_number`
  MODIFY `FRN_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `file_subtopic`
--
ALTER TABLE `file_subtopic`
  MODIFY `FS_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `FILE_TOPIC`
--
ALTER TABLE `FILE_TOPIC`
  MODIFY `FT_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ORGANIZATION_MAIN`
--
ALTER TABLE `ORGANIZATION_MAIN`
  MODIFY `OM_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ROLE_MAIN`
--
ALTER TABLE `ROLE_MAIN`
  MODIFY `RM_ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `TEMPLATE_MAIN`
--
ALTER TABLE `TEMPLATE_MAIN`
  MODIFY `TM_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `document_main`
--
ALTER TABLE `document_main`
  ADD CONSTRAINT `FKpwrkti0ionwtc2fhkeb6q150j` FOREIGN KEY (`DM_REF_NUM_ID`) REFERENCES `file_reference_number` (`FRN_ID`);

--
-- Constraints for table `document_review`
--
ALTER TABLE `document_review`
  ADD CONSTRAINT `FK_DR_DM` FOREIGN KEY (`DR_DOCUMENT_ID`) REFERENCES `DOCUMENT_MAIN` (`DM_ID`),
  ADD CONSTRAINT `FKr2jjau3ye6oj4ddcvg9s70lsj` FOREIGN KEY (`DR_DOCUMENT_ID`) REFERENCES `document_main` (`DM_ID`);

--
-- Constraints for table `file_reference_number`
--
ALTER TABLE `file_reference_number`
  ADD CONSTRAINT `FKbbuchgjgu5yplqrxw64avjy1b` FOREIGN KEY (`FRN_ORG_ID`) REFERENCES `organization_main` (`OM_ID`),
  ADD CONSTRAINT `FKmtt01awiv02ols3sjf4dmems1` FOREIGN KEY (`FRN_TOPIC_REF_ID`) REFERENCES `file_topic` (`FT_ID`);

--
-- Constraints for table `file_subtopic`
--
ALTER TABLE `file_subtopic`
  ADD CONSTRAINT `FKm476fo9395oj2bny3kshsi1in` FOREIGN KEY (`FS_TOPIC_REF_ID`) REFERENCES `file_topic` (`FT_ID`);

--
-- Constraints for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`UR_STAFF_USERNAME`),
  ADD CONSTRAINT `FKdth3rwgk05t91ovapcelmiokl` FOREIGN KEY (`role_id`) REFERENCES `role_main` (`RM_ROLE_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
