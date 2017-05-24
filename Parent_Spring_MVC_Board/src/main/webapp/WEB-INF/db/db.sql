-- 회원테이블
CREATE TABLE member (
    email varchar2(60) PRIMARY KEY,
    passwd varchar2(20) NOT NULL,
    name varchar2(20) NOT NULL,
    mobile varchar2(20)
);

-- ↑↑↑↑↑ 이전까지 로그인을 위한 스키마 (앞에서 cmd를 통해 집어넣었음) 

-- ↓↓↓↓↓ 지금부터 게시판을 위해 추가 한 스키마 

-- 게시판 종류
CREATE TABLE board (
    boardcd varchar2(20),
    boardnm varchar2(40) NOT NULL,
    CONSTRAINT PK_BOARD PRIMARY KEY(boardcd)
);
 
-- 게시글
CREATE TABLE article (
    articleno NUMBER,
    boardcd varchar2(20),
    title varchar2(200) NOT NULL,
    content CLOB,
    email varchar2(60),
    hit NUMBER,
    regdate DATE,
    CONSTRAINT PK_ARTICLE PRIMARY KEY(articleno),
    CONSTRAINT FK_ARTICLE FOREIGN KEY(boardcd) REFERENCES board(boardcd)
);
 
-- 게시글 번호 생성기
CREATE SEQUENCE SEQ_ARTICLE
INCREMENT BY 1
START WITH 1;
 
--게시판 데이터 입력
INSERT INTO board VALUES ('free','자유게시판');
INSERT INTO board VALUES ('qna','QnA게시판');
INSERT INTO board VALUES ('data','자료실');

insert into article values(seq_article.nextval, 'free', 'test1', 'it''s test1', 'email1', 0, sysdate)
insert into article values(seq_article.nextval, 'free', 'test2', 'it''s \*test2', 'email2', 0, sysdate)
insert into article values(seq_article.nextval, 'free', 'test2', 'it''s \$test3', 'email3', 0, sysdate)
 
commit