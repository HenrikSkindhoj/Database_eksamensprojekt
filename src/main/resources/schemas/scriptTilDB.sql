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

insert into Lokation(postNR,afdelingsNavn)
values
(1570,'kbh'),
(5000,'odense'),
(8000,'aarhus'),
(4900,'nakskov'),
(6000,'kolding'),
(3400,'hill');

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
(2,3,'2021-01-10'),
(3,0,'2021-01-28'),
(3,1,'2021-03-08'),
(3,2,'2020-11-13'),
(3,3,'2020-12-17'),
(4,1,'2021-03-18'),
(4,2,'2021-02-19'),
(4,3,'2021-04-10'),
(5,3,'2020-11-30'),
(5,0,'2020-12-29'),
(5,1,'2020-12-15'),
(6,2,'2021-01-14'),
(7,3,'2021-04-10'),
(7,2,'2021-04-27'),
(8,0,'2021-03-13'),
(8,1,'2021-02-11'),
(9,1,'2020-12-07'),
(9,2,'2020-12-11'),
(9,3,'2020-12-16'),
(10,0,'2021-01-14'),
(11,1,'2021-02-20'),
(12,2,'2021-05-04'),
(13,3,'2021-02-07'),
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
(2, 800, 1600, '2021-05-01'),
(1, 740, 1540, '2021-04-12'),
(1, 740, 1540, '2021-04-12'),
(1, 800, 1600, '2021-06-12'),
(2, 840, 1700, '2021-07-01'),
(1, 800, 1600, '2021-06-12'),

(11, 810, 1500, '2021-04-13'),
(13, 720, 1530, '2021-04-12'),
(11, 850, 1500, '2021-05-04'),
(11, 800, 1500, '2021-06-13'),
(13, 900, 1300, '2021-06-12'),
(11, 800, 1500, '2021-07-04'),

(8, 800, 1730, '2021-05-25'),
(7, 900, 1600, '2021-04-30'),
(8, 800, 1600, '2021-07-25'),
(7, 900, 1730, '2021-06-30'),

(3, 700, 1500, '2021-04-12'),
(4, 700, 1500, '2021-04-12'),
(3, 700, 1500, '2021-06-12'),
(4, 700, 1500, '2021-06-12'),

(9, 900, 1700, '2021-04-12'),
(9, 800, 1600, '2021-04-17'),
(9, 900, 1700, '2021-06-12'),
(9, 800, 1600, '2021-06-17'),

(6, 900, 1500, '2021-04-15'),
(5, 900, 1600, '2021-05-03'),
(6, 900, 1700, '2021-05-12'),
(6, 900, 1500, '2021-06-15'),
(5, 900, 1600, '2021-07-03'),
(6, 900, 1700, '2021-07-12');


insert into Vagtplan(postNR, vagtID)
values
(1570, 1),
(1570, 2),
(1570, 3),
(1570, 4),
(1570, 5),
(1570, 6),
(3400, 7),
(3400, 8),
(3400, 9),
(3400, 10),
(3400, 11),
(3400, 12),
(4900, 13),
(4900, 14),
(4900, 15),
(4900, 16),
(5000, 17),
(5000, 18),
(5000, 19),
(5000, 20),
(6000, 21),
(6000, 22),
(6000, 23),
(6000, 24),
(8000, 25),
(8000, 26),
(8000, 27),
(8000, 28),
(8000, 29),
(8000, 30);

create view IF NOT EXISTS månedligRapport
as
select vaccinationsAftale.cpr, vaccinationsAftale.vaccineTypeID, vaccinationsAftale.dato, Vaccine.vaccinePris 
from vaccinationsAftale
inner join Vaccine
where Vaccine.vaccineTypeID = vaccinationsAftale.vaccineTypeID and (vaccinationsAftale.dato between '2021-05-01' AND '2021-05-31');

create view IF NOT EXISTS vagtView
as
select Vagt.vagtID, Vagt.medarbejderID, Vagt.startTidspunkt, Vagt.slutTidspunkt, Vagt.dato, Medarbejder.navn, Medarbejder.løn, Medarbejder.jobTitle, VagtPlan.postNR
from Vagt 
inner join Medarbejder
on Vagt.MedarbejderID = Medarbejder.MedarbejderID
inner join VagtPlan
on VagtPlan.vagtID = vagt.vagtID;

create view IF NOT EXISTS VaccinationsAftalePlan
as
select VaccinationsAftale.postNR, VaccinationsAftale.vaccineTypeID, VaccinationsAftale.medarbejderID, VaccinationsAftale.cpr, person.personNavn, VaccinationsAftale.dato, VaccinationsAftale.tidspunkt, medarbejder.navn
from VaccinationsAftale
inner join person
on VaccinationsAftale.cpr = person.cpr
inner join medarbejder
on medarbejder.medarbejderID = VaccinationsAftale.medarbejderID
ORDER BY postNR;

create view IF NOT EXISTS vagtplanview
as
select Vagt.vagtID, Vagt.medarbejderID, Vagt.startTidspunkt, Vagt.slutTidspunkt, Vagt.dato, Vagtplan.PostNR
from Vagt
inner join Vagtplan
where Vagt.vagtID = Vagtplan.vagtID; 

create view IF NOT EXISTS DagsplanView
as
select lokation.postNR, vagtplanview.medarbejderID, vagtplanview.dato, certifikat.vaccineTypeID
from vagtplanview
inner join lokation
on vagtplanview.postNR = lokation.postNR and dato = '2021-04-12'
inner join certifikat
on vagtplanview.medarbejderID = certifikat.medarbejderID;
select * from DagsplanView;

create view IF NOT EXISTS certifikatView 
as
select certifikat.medarbejderID, certifikat.vaccineTypeID, certifikat.certifikatDato, medarbejder.navn
from certifikat
inner join medarbejder
where certifikat.medarbejderID = medarbejder.medarbejderID;

create view IF NOT EXISTS vaccineAntalView
as
select vaccinetypeid,SUM(vaccineAntal)
from Lager
Group by vaccineTypeID;


DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `VaccinationPerLokation`(postNR int, dato date) 
RETURNS int
BEGIN
declare antal int;
select COUNT(*) into antal from vaccinationsAftale
where postNR = vaccinationsAftale.postNR and dato = vaccinationsAftale.dato;
RETURN antal;
END$$


CREATE DEFINER=`root`@`localhost` PROCEDURE `daglig_rapport`(dato date)
BEGIN
drop table if exists dagligrapport;
create table dagligrapport
as
select VaccinationsAftale.postNR, VaccinationsAftale.vaccineTypeID, VaccinationsAftale.medarbejderID, VaccinationsAftale.cpr, person.personNavn, VaccinationsAftale.dato, VaccinationsAftale.tidspunkt, medarbejder.navn 
from VaccinationsAftale
natural join person
join medarbejder
where VaccinationsAftale.dato = dato and VaccinationsAftale.cpr = person.cpr and medarbejder.medarbejderID = VaccinationsAftale.medarbejderID;

END$$ 

 
CREATE Trigger `Opdater_aftale`
after delete on vaccinationsAftale
for each row
Begin
update Lager
set vaccineantal = vaccineantal-1
where OLD.vaccineTypeID = lager.vaccineTypeID and OLD.postNR = lager.postNR;
END
$$
DELIMITER ;




