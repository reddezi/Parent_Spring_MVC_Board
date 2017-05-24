-- ȸ�����̺�
CREATE TABLE member (
    email varchar2(60) PRIMARY KEY,
    passwd varchar2(20) NOT NULL,
    name varchar2(20) NOT NULL,
    mobile varchar2(20)
);

-- ������ �������� �α����� ���� ��Ű�� (�տ��� cmd�� ���� ����־���) 

-- ������ ���ݺ��� �Խ����� ���� �߰� �� ��Ű�� 

-- �Խ��� ����
CREATE TABLE board (
    boardcd varchar2(20),
    boardnm varchar2(40) NOT NULL,
    CONSTRAINT PK_BOARD PRIMARY KEY(boardcd)
);
 
-- �Խñ�
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
 
-- �Խñ� ��ȣ ������
CREATE SEQUENCE SEQ_ARTICLE
INCREMENT BY 1
START WITH 1;
 
--�Խ��� ������ �Է�
INSERT INTO board VALUES ('free','�����Խ���');
INSERT INTO board VALUES ('qna','QnA�Խ���');
INSERT INTO board VALUES ('data','�ڷ��');

insert into article values(seq_article.nextval, 'free', 'test1', 'it''s test1', 'email1', 0, sysdate)
insert into article values(seq_article.nextval, 'free', 'test2', 'it''s \*test2', 'email2', 0, sysdate)
insert into article values(seq_article.nextval, 'free', 'test2', 'it''s \$test3', 'email3', 0, sysdate)
 
commit