drop table customer if exists;
CREATE TABLE CUSTOMER (CUSTOMER_ID int not null auto_increment, CUSTOMER_NAME VARCHAR2(50) );
drop table TRANSACTION if exists;
CREATE TABLE TRANSACTION (TRANSACTION_ID int,CUSTOMER_ID int ,TRANSACTION_DATE DATE,AMOUNT int);
INSERT INTO CUSTOMER(CUSTOMER_ID,CUSTOMER_NAME) values (1001,'Customer1');
INSERT INTO CUSTOMER(CUSTOMER_ID,CUSTOMER_NAME) values (1002,'Customer2');
INSERT INTO CUSTOMER(CUSTOMER_ID,CUSTOMER_NAME) values (1003,'Customer3');
INSERT INTO CUSTOMER(CUSTOMER_ID,CUSTOMER_NAME) values (1004,'Customer4');
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10001,1001,'2022-09-12',120);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10002,1001,'2022-08-01',85);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10003,1001,'2022-07-04',160);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10004,1001,'2022-06-01',90);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10005,1001,'2022-05-04',120);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10006,1001,'2022-04-05',165);

INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10007,1002,'2022-09-05',113);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10008,1002,'2022-08-27',80);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10009,1002,'2022-07-04',102);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10010,1002,'2022-06-01',210);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10011,1002,'2022-05-27',130);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10012,1002,'2022-04-15',88);

INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10013,1003,'2022-09-05',102);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10014,1003,'2022-08-27',84);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10015,1003,'2022-07-04',200);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10016,1003,'2022-06-01',103);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10017,1003,'2022-05-27',500);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10018,1003,'2022-04-20',585);

INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10019,1004,'2022-09-14',102);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10020,1004,'2022-08-14',84);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10021,1004,'2022-07-14',200);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10022,1004,'2022-06-14',103);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10023,1004,'2022-05-14',500);
INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (10024,1004,'2022-04-14',585);
COMMIT;