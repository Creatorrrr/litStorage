
DROP TABLE board CASCADE CONSTRAINTS;
DROP TABLE changehistory CASCADE CONSTRAINTS;
DROP TABLE discussioncontent CASCADE CONSTRAINTS;
DROP TABLE discussionplace CASCADE CONSTRAINTS;
DROP TABLE episode CASCADE CONSTRAINTS;
DROP TABLE inviterequest CASCADE CONSTRAINTS;
DROP TABLE literature CASCADE CONSTRAINTS;
DROP TABLE litstorage CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE memberlitstorage CASCADE CONSTRAINTS;
DROP TABLE post CASCADE CONSTRAINTS;

CREATE TABLE board (
id VARCHAR2(20),
name VARCHAR2(30)
);

CREATE TABLE changehistory (
id VARCHAR2(30),
editor VARCHAR2(30),
content VARCHAR2(2000),
changetime DATE,
message VARCHAR2(200),
episodeid VARCHAR2(30)
);

CREATE TABLE discussioncontent (
id VARCHAR2(30),
writer VARCHAR2(30),
content VARCHAR2(300),
discussionplaceid VARCHAR2(30)
);

CREATE TABLE discussionplace (
id VARCHAR2(30),
name VARCHAR2(50),
writer VARCHAR2(30),
litstorageid VARCHAR2(30)
);

CREATE TABLE episode (
id VARCHAR2(30),
name VARCHAR2(50),
content VARCHAR2(2000),
writer VARCHAR2(30),
bound CHAR(10),
literature VARCHAR2(30)
);

CREATE TABLE inviterequest (
sender VARCHAR2(30),
receiver VARCHAR2(30),
sendtime DATE,
form VARCHAR2(5),
message VARCHAR2(300)
);

CREATE TABLE literature (
id VARCHAR2(30),
name VARCHAR2(50),
genre VARCHAR2(30),
imagepath VARCHAR2(200),
introduce VARCHAR2(500),
writer VARCHAR2(30),
hits NUMBER,
litstorageid VARCHAR2(30)
);

CREATE TABLE litstorage (
id VARCHAR2(30),
introduce VARCHAR2(300),
name VARCHAR2(50),
writer VARCHAR2(30)
);

CREATE TABLE member(
id VARCHAR2(30),
name VARCHAR2(30),
password VARCHAR2(30),
email VARCHAR2(50),
isout VARCHAR2(5)
);

CREATE TABLE memberlitstorage(
memberid VARCHAR2(30),
litstorageid VARCHAR2(30),
jointime DATE
);

CREATE TABLE post(
id VARCHAR2(30),
name VARCHAR2(30),
content VARCHAR2(2000),
hashtag VARCHAR2(100),
writer VARCHAR2(30),
boardid VARCHAR2(30)
);


drop sequence BOARD_SEQ;
drop sequence CHANGE_HISTORY_SEQ;
drop sequence DISCUSSION_CONTENT_SEQ;
drop sequence DISCUSSION_PLACE_SEQ;
drop sequence EPISODE_SEQ;
drop sequence LITERATURE_SEQ;
drop sequence POST_SEQ;
drop sequence LITSTORAGE_SEQ;

create sequence BOARD_SEQ START WITH 1 INCREMENT BY 1;
create sequence CHANGE_HISTORY_SEQ START WITH 1 INCREMENT BY 1;
create sequence DISCUSSION_CONTENT_SEQ START WITH 1 INCREMENT BY 1;
create sequence DISCUSSION_PLACE_SEQ START WITH 1 INCREMENT BY 1;
create sequence EPISODE_SEQ START WITH 1 INCREMENT BY 1;
create sequence LITERATURE_SEQ START WITH 1 INCREMENT BY 1;
create sequence POST_SEQ START WITH 1 INCREMENT BY 1;
create sequence LITSTORAGE_SEQ START WITH 1 INCREMENT BY 1;
alter table inviterequest add(litstorageId varchar2(50));