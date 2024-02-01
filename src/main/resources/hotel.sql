DROP TABLE IF EXISTS `rooms`;
CREATE TABLE `rooms` (
  `roomId` int(11) unsigned NOT NULL,
  `status` int(1) NOT NULL,
  `guestName` varchar(50),
  `bookingId` int(11),
  PRIMARY KEY (`roomId`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `roomBookings`;
CREATE TABLE `roomBookings` (
    `bookingId` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `roomId` int(11) unsigned NOT NULL,
    `guestName` varchar(50) NOT NULL,
    `startTime` timestamp NOT NULL,
    `endTime` timestamp NOT NULL,
    PRIMARY KEY (`bookingId`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userName` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `accountType` int(1) NOT NULL,
  `phoneNumber` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB;



DROP TABLE IF EXISTS `parcels`;
CREATE TABLE `parcels` (
  `parcelId` varchar(50) NOT NULL,
  `receiverName` varchar(50) NOT NULL,
  `roomId` int(11) unsigned NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  `deliveredTime` timestamp NOT NULL ,
  `pickupTime` timestamp,
  PRIMARY KEY (`parcelId`)
) ENGINE=InnoDB;



