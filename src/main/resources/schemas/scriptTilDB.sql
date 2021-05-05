DROP SCHEMA IF EXISTS coronaVaccinationer;
CREATE SCHEMA IF NOT EXISTS coronaVaccinationer;
USE coronaVaccinationer;

CREATE TABLE IF NOT EXISTS Person(
cpr varchar(11) NOT NULL,

personNavn varchar(255),

PRIMARY key (cpr)
);

CREATE TABLE IF NOT EXISTS Medarbejder(
medarbejderID int NOT NULL unique auto_increment,

navn varchar(255),
løn int,
jobTitle varchar(255),

primary key (medarbejderID)
);

create table if not exists Lokation(
postNR int not null,

afdelingsNavn varchar(255),
antalVaccineret int,

PRIMARY KEY(postNR)
);

create table if not exists Vaccine(
vaccineTypeID int not null,

vaccinepris int,
vaccineTypeNavn varchar(10),

Primary key(vaccineTypeID)
);

create table if not exists Certifikat(
medarbejderID int NOT null,
vaccineTypeID int NOT null,

certifikatDato date,

primary key (medarbejderID, vaccineTypeID),
foreign key (medarbejderID) references Medarbejder(medarbejderID),
foreign key (vaccineTypeID) references Vaccine(vaccineTypeID)
);

create table if not exists Vagt(
vagtID int not null unique auto_increment,
medarbejderID int not null,

startTidspunkt int,
slutTidspunkt int,
dato date,

primary key(vagtID, medarbejderID),
foreign key(medarbejderID) references Medarbejder(medarbejderID)
);

create table if not exists Vagtplan(
postNR int not null,
vagtID int not null,

primary key(postNR, vagtID),
foreign key(postNR) references Lokation(postNR),
foreign key(vagtID) references Vagt(vagtID)
);

create table if not exists Lager(
postNR int not null,
vaccineTypeID int not null,

vaccineAntal int,

Primary key(postNR, vaccineTypeID),
Foreign key(postNR) references Lokation(postNR),
foreign key(vaccineTypeID) references Vaccine(vaccineTypeID)
);

create table if not exists VaccinationsAftale(
aftaleID int not null unique auto_increment,
postNR int not null,
vaccineTypeID int not null, 
medarbejderID int not null,
cpr varchar(11) not null,

tidspunkt int,
dato date,

Primary key(aftaleID),
foreign key(postNR) references Lokation(postNR),
foreign key(vaccineTypeID) references Vaccine(vaccineTypeID),
foreign key(medarbejderID) references Medarbejder(medarbejderID),
foreign key(cpr) references Person(cpr)
);

insert into Lokation(postNR,afdelingsNavn,antalVaccineret)
values
(1570,'kbh',0),
(5000,'odense',0),
(8000,'aarhus',0),
(4900,'nakskov',0),
(6000,'kolding',0),
(3400,'hill',0);

insert into Medarbejder(navn,løn,jobTitle)
values
('John Snow', 20000, 'Sygeplejerske'),
('Henrik Skindhøj', 10000, 'Vikar'),
('Kasper Skov', 50000, 'Overlaege'),
('Hans Christian Leth-Nissen', 34000, 'Laege'),
('Johan Olsen', 35000, 'Laege'),
('Peter Madsen', 20000, 'Sygeplejerske'),
('Kirsten Hansen', 16000, 'Sygeplejerske'),
('Helle Thorning', 22000, 'Sygeplejerske'),
('Susanne Olsen', 30000, 'Sygeplejerske'),
('Gitte Møller', 10000, 'Sygeplejerske'),
('Magnus Dahlkvist', 12000, 'Sygeplejerske'),
('Bente Johannsen', 13000, 'Sygeplejerske'),
('Rasmus Andersen', 22000, 'Sygeplejerske');

insert into Vaccine(vaccineTypeID,vaccinepris,vaccineTypeNavn)
values
(0,352,'COVAXX'),
(1,556,'DIVOC'),
(2,877,'BLAST3000'),
(3,795,'ASPERA');

insert into Certifikat(medarbejderID, vaccineTypeID, certifikatDato)
values
(1,0,'2020-11-06'),
(1,1,'2020-12-28'),
(2,2,'2021-01-03'),
(3,0,'2021-01-28'),
(3,1,'2021-03-08'),
(3,2,'2020-11-13'),
(3,3,'2020-12-17'),
(4,1,'2021-03-18'),
(4,2,'2021-02-19'),
(4,3,'2021-04-10'),
(5,3,'2020-11-30'),
(5,0,'2020-12-29'),
(6,2,'2021-01-14'),
(7,3,'2021-04-10'),
(8,0,'2021-03-13'),
(8,1,'2021-02-11'),
(9,1,'2020-12-07'),
(9,2,'2020-12-11'),
(9,3,'2020-12-16'),
(10,3,'2021-01-14'),
(11,2,'2021-02-20'),
(12,2,'2021-05-04'),
(13,3,'2021-02-20'),
(13,0,'2021-02-28');

insert into Lager(postNR, vaccineTypeID, vaccineAntal)
values
(1570, 0, 1805),
(1570, 1, 2322),
(1570, 2, 0),
(1570, 3, 1223),
(5000, 0, 1565),
(5000, 1, 1322),
(5000, 2, 2020),
(5000, 3, 1736),
(8000, 0, 1414),
(8000, 1, 2760),
(8000, 2, 1320),
(8000, 3, 2750),
(4900, 0, 15000),
(4900, 1, 5),
(4900, 2, 0),
(4900, 3, 5),
(6000, 0, 1204),
(6000, 1, 1954),
(6000, 2, 914),
(6000, 3, 1433),
(3400, 0, 50),
(3400, 1, 712),
(3400, 2, 1402),
(3400, 3, 1202);

insert into Vagt(medarbejderID, startTidspunkt, slutTidspunkt, dato)
values
(1, 1000, 1700, '2021-05-10'),
(2, 1000, 1500, '2021-05-10'),
(3, 800, 1700, '2021-05-10'),
(4, 1000, 1700, '2021-05-10'),
(5, 800, 1500, '2021-05-10'),
(6, 1100, 1800, '2021-05-10'),
(7, 700, 1400, '2021-05-10'),
(8, 1200, 1900, '2021-05-10'),
(9, 1000, 1700, '2021-05-10'),
(10, 800, 1500, '2021-05-10'),
(11, 800, 1500, '2021-05-10'),
(12, 1000, 1700, '2021-05-10'),
(13, 1100, 1800, '2021-05-10'),
(1, 1000, 1700, '2021-05-11'),
(2, 1000, 1500, '2021-05-11'),
(3, 800, 1700, '2021-05-11'),
(4, 1000, 1700, '2021-05-11'),
(5, 800, 1500, '2021-05-11'),
(6, 1100, 1800, '2021-05-11'),
(7, 700, 1400, '2021-05-11'),
(8, 1200, 1900, '2021-05-11'),
(9, 1000, 1700, '2021-05-11'),
(10, 800, 1500, '2021-05-11'),
(11, 800, 1500, '2021-05-11'),
(12, 1000, 1700, '2021-05-11'),
(13, 1100, 1800, '2021-05-11'),
(1, 1000, 1700, '2021-05-12'),
(2, 1000, 1500, '2021-05-12'),
(3, 800, 1700, '2021-05-12'),
(4, 1000, 1700, '2021-05-12'),
(5, 800, 1500, '2021-05-12'),
(6, 1100, 1800, '2021-05-12'),
(7, 700, 1400, '2021-05-12'),
(8, 1200, 1900, '2021-05-12'),
(9, 1000, 1700, '2021-05-12'),
(10, 800, 1500, '2021-05-12'),
(11, 800, 1500, '2021-05-12'),
(12, 1000, 1700, '2021-05-12'),
(13, 1100, 1800, '2021-05-12'),
(1, 1000, 1700, '2021-05-13'),
(2, 1000, 1500, '2021-05-13'),
(3, 800, 1700, '2021-05-13'),
(4, 1000, 1700, '2021-05-13'),
(5, 800, 1500, '2021-05-13'),
(6, 1100, 1800, '2021-05-13'),
(7, 700, 1400, '2021-05-13'),
(8, 1200, 1900, '2021-05-13'),
(9, 1000, 1700, '2021-05-13'),
(10, 800, 1500, '2021-05-13'),
(11, 800, 1500, '2021-05-13'),
(12, 1000, 1700, '2021-05-13'),
(13, 1100, 1800, '2021-05-13'),
(1, 1000, 1700, '2021-05-14'),
(2, 1000, 1500, '2021-05-14'),
(3, 800, 1700, '2021-05-14'),
(4, 1000, 1700, '2021-05-14'),
(5, 800, 1500, '2021-05-14'),
(6, 1100, 1800, '2021-05-14'),
(7, 700, 1400, '2021-05-14'),
(8, 1200, 1900, '2021-05-14'),
(9, 1000, 1700, '2021-05-14'),
(10, 800, 1500, '2021-05-14'),
(11, 800, 1500, '2021-05-14'),
(12, 1000, 1700, '2021-05-14'),
(13, 1100, 1800, '2021-05-14'),
(1, 1000, 1700, '2021-05-15'),
(2, 1000, 1500, '2021-05-15'),
(3, 800, 1700, '2021-05-15'),
(4, 1000, 1700, '2021-05-15'),
(5, 800, 1500, '2021-05-15'),
(6, 1100, 1800, '2021-05-15'),
(7, 700, 1400, '2021-05-15'),
(8, 1200, 1900, '2021-05-15'),
(9, 1000, 1700, '2021-05-15'),
(10, 800, 1500, '2021-05-15'),
(11, 800, 1500, '2021-05-15'),
(12, 1000, 1700, '2021-05-15'),
(13, 1100, 1800, '2021-05-15');

insert into Vagtplan(postNR, vagtID)
values
(1570, 1),
(1570, 2),
(5000, 3),
(5000, 4),
(8000, 5),
(8000, 6),
(4900, 7),
(4900, 8),
(6000, 9),
(6000, 10),
(3400, 11),
(3400, 12),
(3400, 13),
(1570, 14),
(1570, 15),
(5000, 16),
(5000, 17),
(8000, 18),
(8000, 19),
(4900, 20),
(4900, 21),
(6000, 22),
(6000, 23),
(3400, 24),
(3400, 25),
(3400, 26),
(1570, 27),
(1570, 28),
(5000, 29),
(5000, 30),
(8000, 31),
(8000, 32),
(4900, 33),
(4900, 34),
(6000, 35),
(6000, 36),
(3400, 37),
(3400, 38),
(3400, 39),
(1570, 40),
(1570, 41),
(5000, 42),
(5000, 43),
(8000, 44),
(8000, 45),
(4900, 46),
(4900, 47),
(6000, 48),
(6000, 49),
(3400, 50),
(3400, 51),
(3400, 52),
(1570, 53),
(1570, 54),
(5000, 55),
(5000, 56),
(8000, 57),
(8000, 58),
(4900, 59),
(4900, 60),
(6000, 61),
(6000, 62),
(3400, 63),
(3400, 64),
(3400, 65),
(1570, 66),
(1570, 67),
(5000, 68),
(5000, 69),
(8000, 70),
(8000, 71),
(4900, 72),
(4900, 73),
(6000, 74),
(6000, 75),
(3400, 76),
(3400, 77),
(3400, 78);
