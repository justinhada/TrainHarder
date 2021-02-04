CREATE SCHEMA `trainharder` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
CREATE USER 'powerlifter'@'%' IDENTIFIED BY 'passwort';
GRANT ALL PRIVILEGES ON `trainharder`.* TO 'powerlifter'@'%' IDENTIFIED BY 'passwort';
CREATE USER 'powerlifter'@'localhost' IDENTIFIED BY 'passwort';
GRANT ALL PRIVILEGES ON `trainharder`.* TO 'powerlifter'@'localhost' IDENTIFIED BY 'passwort';
FLUSH privileges;

CREATE SCHEMA `trainharderTest` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
GRANT ALL PRIVILEGES ON `trainharderTest`.* TO 'powerlifter'@'%' IDENTIFIED BY 'passwort';
CREATE USER 'powerlifter'@'localhost' IDENTIFIED BY 'passwort';
GRANT ALL PRIVILEGES ON `trainharderTest`.* TO 'powerlifter'@'localhost' IDENTIFIED BY 'passwort';
FLUSH privileges;