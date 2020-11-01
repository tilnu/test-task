CREATE TABLE T_DOCTOR (ID BIGINT IDENTITY NOT NULL PRIMARY KEY,
                       NAME VARCHAR(255) NOT NULL, 
                       SURNAME VARCHAR(255) NOT NULL,
                       PATRONYMIC VARCHAR(255) NOT NULL,
                       SPECIALISATION VARCHAR(255) NOT NULL);
CREATE TABLE T_PATIENT (ID BIGINT IDENTITY NOT NULL PRIMARY KEY,
                       NAME VARCHAR(255) NOT NULL, 
                       SURNAME VARCHAR(255) NOT NULL,
                       PATRONYMIC VARCHAR(255) NOT NULL,
                       PHONE VARCHAR(255) NOT NULL);
CREATE TABLE T_PRESCRIPTION (ID BIGINT IDENTITY NOT NULL PRIMARY KEY,
                       DESCRIPTION VARCHAR(255) NOT NULL, 
                       DOCTOR_ID BIGINT NOT NULL,
                       PATIENT_ID BIGINT NOT NULL,
                       ISSUE_DATE DATE NOT NULL,
                       VALID_DATE DATE NOT NULL,
                       PRIORITY INTEGER NOT NULL,
                       CONSTRAINT DOC_REF FOREIGN KEY(DOCTOR_ID) REFERENCES T_DOCTOR(ID),
                       CONSTRAINT PAT_REF FOREIGN KEY(PATIENT_ID) REFERENCES T_PATIENT(ID));
INSERT INTO T_DOCTOR VALUES(1,'Джабраил','Кадавров','Мортович','Патологоанатом'),
                           (2,'Богдан','Хлебнов','Александрович','Стоматолог'),
                           (3,'Михаил','Мясников','Михаилович','Хирург'),
                           (4,'Тимур','Соколов','Маратович','Окулист'),
                           (5,'Констатин','Константинопольский','Констатинович','Стоматолог-ортодонт');
INSERT INTO T_PATIENT VALUES(1,'Иван','Иванов','Иванович','+79999999999'),
                            (2,'Сергей','Сергеев','Сергеевич','9324567'),
                            (3,'Алексей','Кабаллов','Сидорович','88005553535'),
                            (4,'Пётр','Петров','Петрович','89674523167'),
                            (5,'Имярек','Имяреков','Имярекович','112');
INSERT INTO T_PRESCRIPTION VALUES(1,'Полоскания полости рта',2,3,'2020-11-02','2020-11-08',0),
                                 (2,'Промывания глаз',4,2,'2020-10-26','2020-11-15',0),
                                 (3,'Обезболивающие',5,4,'2020-10-26','2020-11-08',1),
                                 (4,'Подготовка к трансплантации',3,4,'2020-10-26','2020-11-22',1),
                                 (5,'Врач сказал в морг - значит в морг!',1,5,'2012-12-12','2020-12-12',2);