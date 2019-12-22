CREATE SCHEMA `powerlifting` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
CREATE USER 'powerlifter'@'%' IDENTIFIED BY 'passwort';
GRANT ALL PRIVILEGES ON `powerlifting`.* TO 'powerlifter'@'%' IDENTIFIED BY 'passwort';
CREATE USER 'powerlifter'@'localhost' IDENTIFIED BY 'passwort';
GRANT ALL PRIVILEGES ON `powerlifting`.* TO 'powerlifter'@'localhost' IDENTIFIED BY 'passwort';
FLUSH privileges;

CREATE TABLE Benutzer
(
ID INT NOT NULL,
Vorname VARCHAR(128) NOT NULL,
Nachname VARCHAR(128) NOT NULL,
Koerpergewicht INT,
Koerpergroesse INT,
Lebensalter INT,
Kraftlevel VARCHAR(128) NOT NULL,
Geschlecht VARCHAR(128) NOT NULL,
Erfahrung VARCHAR(128) NOT NULL,
Ernaehrung VARCHAR(128) NOT NULL,
Schlafqualitaet VARCHAR(128) NOT NULL,
Stress VARCHAR(128) NOT NULL,
Doping VARCHAR(128) NOT NULL,
Regenerationsfaehigkeit VARCHAR(128) NOT NULL,
PRIMARY KEY(ID)
);

CREATE TABLE Uebung
(
ID INT NOT NULL,
Name VARCHAR(128) NOT NULL,
Uebungsart VARCHAR(128) NOT NULL,
Uebungskategorie VARCHAR(128) NOT NULL,
PRIMARY KEY(ID),
FOREIGN KEY(Belastungsfaktor_ID)
    REFERENCES Belastungsfaktor(ID)
    ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE Belastungsfaktor
(
ID INT NOT NULL,
Squat DECIMAL NOT NULL,
Benchpress DECIMAL NOT NULL,
Deadlift DECIMAL NOT NULL,
Triceps DECIMAL NOT NULL,
Chest DECIMAL NOT NULL,
Core DECIMAL NOT NULL,
Back DECIMAL NOT NULL,
Biceps DECIMAL NOT NULL,
Glutes DECIMAL NOT NULL,
Quads DECIMAL NOT NULL,
Hamstrings DECIMAL NOT NULL,
Shoulder DECIMAL NOT NULL,
PRIMARY KEY(ID)
);

CREATE TABLE 