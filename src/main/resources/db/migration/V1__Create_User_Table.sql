create table myUser
(
NAME VARCHAR2(20 BYTE),
ID NUMBER(*, 0),
ADDRESS VARCHAR2(20 BYTE),
AGE NUMBER(*, 0),
TOKEN VARCHAR2(100 BYTE),
HEAD_URL VARCHAR2(200 BYTE)
);
//创建序列，使用方法Myuser_Id_Seq.Nextval
CREATE SEQUENCE Myuser_Id_Seq 
start with 1 
increment by 1;
