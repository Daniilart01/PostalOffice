CREATE TABLE USERS(username varchar(24) NOT NULL, password varchar(16) NOT NULL ,role varchar(5) DEFAULT 'user', CONSTRAINT user_pk PRIMARY KEY(username));

INSERT INTO USERS(username, password, role) VALUES ('danil', 'danil', 'admin');

INSERT INTO USERS(username, password) VALUES ('oleg', 'oleg');
INSERT INTO USERS(username, password) VALUES ('yarik', 'yarik');
INSERT INTO USERS(username, password) VALUES ('sasha', 'sasha');

CREATE TABLE DEPARTMENT(id number(10), city varchar(50) NOT NULL, country varchar(50) NOT NULL, phone_number varchar(20) NOT NULL,
CONSTRAINT department_pk PRIMARY KEY(id), CONSTRAINT unique_constraint UNIQUE(phone_number));

INSERT INTO DEPARTMENT(id, city, country, phone_number) VALUES (1,'Kharkiv','Ukraine','0995463721');
INSERT INTO DEPARTMENT(id, city, country, phone_number) VALUES (2,'Kharkiv','Ukraine','0503641990');
INSERT INTO DEPARTMENT(id, city, country, phone_number) VALUES (3,'Kiev','Ukraine','0675436211');
INSERT INTO DEPARTMENT(id, city, country, phone_number) VALUES (4,'Kiev','Ukraine','0974657888');
INSERT INTO DEPARTMENT(id, city, country, phone_number) VALUES (5,'Lviv','Ukraine','0964321221');

CREATE TABLE PACKAGE(id number(10), sender varchar(24) NOT NULL, receiver varchar(24) NOT NULL, department_id_sender number(10) NOT NULL, department_id_receiver number(10) NOT NULL, 
status varchar(20) NOT NULL, date_departure DATE NOT NULL, date_receiving DATE NOT NULL, 
CONSTRAINT package_pk PRIMARY KEY(id), 
CONSTRAINT package_fk1  FOREIGN KEY(sender) REFERENCES USERS(username) ON DELETE CASCADE,
CONSTRAINT package_fk2  FOREIGN KEY(receiver) REFERENCES USERS(username)ON DELETE CASCADE,
CONSTRAINT package_fk3  FOREIGN KEY(department_id_sender) REFERENCES DEPARTMENT(id) ON DELETE CASCADE,
CONSTRAINT package_fk4  FOREIGN KEY(department_id_receiver) REFERENCES DEPARTMENT(id) ON DELETE CASCADE);

INSERT INTO PACKAGE(id,sender,receiver,department_id_sender,department_id_receiver,status,date_departure,date_receiving) 
VALUES(3542162312, 'sasha','yarik',1,2,'Shipping', TO_DATE('1-06-2022','DD-MM-YYYY'),TO_DATE('30-06-2022','DD-MM-YYYY'));

INSERT INTO PACKAGE(id,sender,receiver,department_id_sender,department_id_receiver,status,date_departure,date_receiving) 
VALUES(5373967519, 'yarik','oleg',1,4,'Shipping', TO_DATE('3-06-2022','DD-MM-YYYY'),TO_DATE('1-07-2022','DD-MM-YYYY'));

INSERT INTO PACKAGE(id,sender,receiver,department_id_sender,department_id_receiver,status,date_departure,date_receiving) 
VALUES(8673524311, 'sasha','oleg',2,5,'Delivered', TO_DATE('24-05-2022','DD-MM-YYYY'),TO_DATE('20-06-2022','DD-MM-YYYY'));

INSERT INTO PACKAGE(id,sender,receiver,department_id_sender,department_id_receiver,status,date_departure,date_receiving) 
VALUES(5674928371, 'yarik','oleg',3,4,'Accepted', TO_DATE('12-06-2022','DD-MM-YYYY'),TO_DATE('07-07-2022','DD-MM-YYYY'));

INSERT INTO PACKAGE(id,sender,receiver,department_id_sender,department_id_receiver,status,date_departure,date_receiving) 
VALUES(9786654121, 'oleg','sasha',5,1,'Delivered', TO_DATE('22-05-2022','DD-MM-YYYY'),TO_DATE('18-06-2022','DD-MM-YYYY'));

INSERT INTO PACKAGE(id,sender,receiver,department_id_sender,department_id_receiver,status,date_departure,date_receiving) 
VALUES(7768901223, 'yarik','sasha',4,2,'Received', TO_DATE('15-05-2022','DD-MM-YYYY'),TO_DATE('10-06-2022','DD-MM-YYYY'));

CREATE TABLE CREATEDPACK(id number(10), sender varchar(24) NOT NULL, receiver varchar(24) NOT NULL, department_id_sender number(10) NOT NULL, department_id_receiver number(10) NOT NULL,
date_creating DATE NOT NULL, 
CONSTRAINT CREATEDPACK_fk1  FOREIGN KEY(sender) REFERENCES USERS(username) ON DELETE CASCADE,
CONSTRAINT CREATEDPACK_fk2  FOREIGN KEY(receiver) REFERENCES USERS(username)ON DELETE CASCADE,
CONSTRAINT CREATEDPACK_fk3  FOREIGN KEY(department_id_sender) REFERENCES DEPARTMENT(id) ON DELETE CASCADE,
CONSTRAINT CREATEDPACK_fk4  FOREIGN KEY(department_id_receiver) REFERENCES DEPARTMENT(id) ON DELETE CASCADE);

INSERT INTO CREATEDPACK(id,sender,receiver,department_id_sender,department_id_receiver,date_creating) 
VALUES(9869462432, 'oleg','yarik',5,4,TO_DATE('12-06-2022','DD-MM-YYYY'));

INSERT INTO CREATEDPACK(id,sender,receiver,department_id_sender,department_id_receiver,date_creating) 
VALUES(1243241521, 'sasha','oleg',4,5, TO_DATE('12-06-2022','DD-MM-YYYY'));

