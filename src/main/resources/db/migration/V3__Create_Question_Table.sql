create table user_question
(
ID NUMBER(*, 0),
TITLE VARCHAR2(20 BYTE),
DESCRIPTION VARCHAR2(500 BYTE),
GMT_CREATE NUMBER(*, 0),
GMT_MODIFIED NUMBER(*, 0),
CREATOR NUMBER(*, 0),
VIEW_COUNT NUMBER(*, 0),
COMMENT_COUNT NUMBER(*, 0),
LIKE_COUNT NUMBER(*, 0),
TAG VARCHAR2(100 BYTE)
);