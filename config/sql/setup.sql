CREATE SCHEMA `trainharder` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
CREATE USER 'trainharder'@'%' IDENTIFIED BY 'passwort';
GRANT ALL PRIVILEGES ON `trainharder`.* TO 'trainharder'@'%' IDENTIFIED BY 'passwort';
CREATE USER 'trainharder'@'localhost' IDENTIFIED BY 'passwort';
GRANT ALL PRIVILEGES ON `trainharder`.* TO 'trainharder'@'localhost' IDENTIFIED BY 'passwort';
FLUSH privileges;
