-- phpMyAdmin SQL Dump
-- version 4.2.8
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 02, 2014 at 11:26 PM
-- Server version: 5.6.20
-- PHP Version: 5.4.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `stec`
--
CREATE DATABASE IF NOT EXISTS `stec` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `stec`;

-- --------------------------------------------------------

--
-- Table structure for table `exchange`
--

DROP TABLE IF EXISTS `exchange`;
CREATE TABLE IF NOT EXISTS `exchange` (
`id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `type` enum('public','private','restricted','') NOT NULL,
  `createdat` datetime NOT NULL COMMENT 'NOW() when created',
  `createdby` int(11) NOT NULL COMMENT 'id of the user who created it',
  `description` varchar(5000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tokens`
--

DROP TABLE IF EXISTS `tokens`;
CREATE TABLE IF NOT EXISTS `tokens` (
`id` int(11) NOT NULL,
  `token_value` varchar(4000) NOT NULL,
  `createdat` int(11) NOT NULL COMMENT 'NOW() when created',
  `description` varchar(4000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tokens_to_connections`
--

DROP TABLE IF EXISTS `tokens_to_connections`;
CREATE TABLE IF NOT EXISTS `tokens_to_connections` (
`id` int(11) NOT NULL COMMENT 'Just PK',
  `token_id` int(11) NOT NULL COMMENT 'link to the token',
  `connection_id` int(11) NOT NULL COMMENT 'link to the connection'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
`id` int(11) NOT NULL COMMENT 'just PK',
  `name` varchar(100) DEFAULT NULL COMMENT 'user''s name',
  `surename` varchar(100) DEFAULT NULL COMMENT 'user''s surename',
  `email` varchar(100) NOT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `about` varchar(3000) DEFAULT NULL COMMENT 'some info that user can write about him',
  `createdat` datetime NOT NULL COMMENT 'NOW() when insert',
  `company` varchar(200) CHARACTER SET utf32 DEFAULT NULL COMMENT 'if any - company name',
  `company_address` varchar(400) DEFAULT NULL COMMENT 'if any'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `users_emails`
--

DROP TABLE IF EXISTS `users_emails`;
CREATE TABLE IF NOT EXISTS `users_emails` (
  `id` int(11) NOT NULL COMMENT 'autoincrement integer id',
  `email` varchar(200) NOT NULL,
  `user_id` int(11) NOT NULL COMMENT 'link to user',
  `createdat` datetime NOT NULL COMMENT 'NOW() when created',
  `description` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `users_keys`
--

DROP TABLE IF EXISTS `users_keys`;
CREATE TABLE IF NOT EXISTS `users_keys` (
`id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `key_value` varchar(200) NOT NULL COMMENT 'generated key',
  `key_type` set('register','none') NOT NULL COMMENT 'type of the key - for what it will be used:  register - at link when registering the user',
  `createdat` datetime NOT NULL COMMENT 'NOW() when created'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_to_exchange`
--

DROP TABLE IF EXISTS `user_to_exchange`;
CREATE TABLE IF NOT EXISTS `user_to_exchange` (
  `user_id` int(11) NOT NULL COMMENT 'link to user id',
  `exchange_id` int(11) NOT NULL COMMENT 'link to exchange id',
  `own_type` set('owner','publish','subscription','none') NOT NULL COMMENT 'owner - full rights add users to exchange, publish - right to push threats, subscription - right to get data from it, none - no right',
  `updatedat` int(11) NOT NULL COMMENT 'updated or created time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `exchange`
--
ALTER TABLE `exchange`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tokens`
--
ALTER TABLE `tokens`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tokens_to_connections`
--
ALTER TABLE `tokens_to_connections`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `users_emails`
--
ALTER TABLE `users_emails`
 ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users_keys`
--
ALTER TABLE `users_keys`
 ADD PRIMARY KEY (`id`), ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user_to_exchange`
--
ALTER TABLE `user_to_exchange`
 ADD KEY `user_id` (`user_id`), ADD KEY `exchange_id` (`exchange_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `exchange`
--
ALTER TABLE `exchange`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tokens`
--
ALTER TABLE `tokens`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tokens_to_connections`
--
ALTER TABLE `tokens_to_connections`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Just PK';
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'just PK';
--
-- AUTO_INCREMENT for table `users_keys`
--
ALTER TABLE `users_keys`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `users_emails`
--
ALTER TABLE `users_emails`
ADD CONSTRAINT `users_emails_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `users_keys`
--
ALTER TABLE `users_keys`
ADD CONSTRAINT `users_keys_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `user_to_exchange`
--
ALTER TABLE `user_to_exchange`
ADD CONSTRAINT `user_to_exchange_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
ADD CONSTRAINT `user_to_exchange_ibfk_2` FOREIGN KEY (`exchange_id`) REFERENCES `exchange` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
