create table myUser
(
NAME VARCHAR2(20 BYTE),
ID NUMBER(*, 0),
ADDRESS VARCHAR2(20 BYTE),
AGE NUMBER(*, 0),
TOKEN VARCHAR2(100 BYTE),
AVATAR_URL VARCHAR2(200 BYTE),
PASSWORD NVARCHAR2(20) DEFAULT 123456
);
//�������У�ʹ�÷���Myuser_Id_Seq.Nextval
CREATE SEQUENCE Myuser_Id_Seq 
start with 1 
increment by 1;
