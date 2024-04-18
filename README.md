# 문화인들의 밤

문화인들의 밤은 영화 커뮤니티 플랫폼을 제공하는 프로젝트로, 사용자들이 영화에 대해 토론하고 정보를 공유할 수 있는 공간입니다. 백엔드는 Java와 Spring Boot를 사용하였고, 프론트엔드는 HTML, CSS, JavaScript로 구현되었습니다.

## 시작하기

이 섹션에서는 프로젝트를 로컬 환경에서 실행하는 방법에 대해 설명합니다.

<hr>

### 프로그래밍 언어 및 개발 환경
- **Java**: JDK 17을 사용하여 백엔드 로직을 구현.
- **Spring Boot**: 모던 웹 애플리케이션 개발을 위한 프레임워크, 빠른 개발과 간편한 배포 제공.
- **JSP/Servlet**: 동적 웹 페이지 생성과 서버 사이드 로직 처리.
- **JavaScript/CSS/HTML**: 프론트엔드 인터페이스 구현.

### 데이터베이스
- **Oracle 11g**: 데이터 저장과 관리를 위한 관계형 데이터베이스 시스템.

### ORM 및 데이터 액세스
- **MyBatis**: SQL 매핑 프레임워크로 데이터베이스와 객체간의 매핑 처리.

### 협업 도구
- **Asana**: 프로젝트 관리 및 팀 작업의 일정 관리.
- **Slack**: 팀 커뮤니케이션을 위한 메시징 플랫폼.
- **Discord**: 실시간 음성 및 텍스트 기반 커뮤니케이션 도구.

### 버전 관리 및 코드 리포지토리
- **Git**: 소스 코드 버전 관리.
- **GitHub/GitLab**: 코드 호스팅 및 팀원 간의 코드 리뷰, 머지 요청 및 이슈 트래킹을 지원.

### 기타 도구 및 라이브러리
- **Apache Tomcat**: 웹 애플리케이션 서버로 서블릿과 JSP 실행 환경 제공.
- **Maven**: 프로젝트 의존성 관리 및 빌드 자동화 도구.

<hr>

### 설치

1. 프로젝트 클론:
   ```bash
   git clone https://github.com/yourusername/cultural-night.git
   ```
2. 필요한 Java 라이브러리 설치:

   ```
   mvn install
   ```

 ## 실행하기  

 1. Spring Boot 애플리케이션 실행:
   ```
   mvn spring-boot:run
   ```
 2. 브라우저에서 다음 URL로 접속:
   ```
   http://localhost:8090/acorn
   ```

 ## 구성

 ### 주요 설정 파일
application.properties 설정 예시:
```
server.port=8090
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=acorn
spring.datasource.password=root
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
spring.mvc.static-path-pattern=/resources/**
spring.profiles.active=dev
jwt.expiredMs=3600000
```

 ## 기능
문화인들의 밤은 다음과 같은 주요 기능을 제공합니다:

 ### 영화 정보 공유
- 사용자 간의 토론 및 의견 교환
- 영화 평점 및 리뷰 작성
- 영화 커뮤니티 게시판
- 관리자 관리 페이지
- 마이페이지
- 소모임(채팅)
 ## 마이그레이션 안내
2024년 4월 초, Spring Boot 3.x 버전으로 마이그레이션 예정입니다.

 ## SQL   
```
---- 사용자 (memberDB) 테이블
CREATE TABLE memberDB (
    userId VARCHAR2(50) PRIMARY KEY,
    userPw VARCHAR2(50) NOT NULL CHECK (LENGTHB(userPw) >= 4),
    userName VARCHAR2(30) NOT NULL CHECK (LENGTHB(userName) >= 2),
    userSSN1 VARCHAR2(10),
    userSSN2 VARCHAR2(10),
    userGender VARCHAR2(20) CHECK (userGender IN ('male', 'female')),
    nickname VARCHAR2(30) UNIQUE,
    userPhoneNum1 VARCHAR2(3),
    userPhoneNum2 VARCHAR2(4),
    userPhoneNum3 VARCHAR2(4),
    userEmailId VARCHAR2(50) NOT NULL,
    userEmailDomain VARCHAR2(50) NOT NULL,
    userSignDate VARCHAR2(10) DEFAULT TO_CHAR(SYSDATE, 'yyyy/MM/dd') NOT NULL,
    userType VARCHAR2(1) DEFAULT '1' CHECK (userType IN ('0', '1', '2', '3', '4')) -- 0 for admin, 1 for member, default is member
);

--디버그 게시판DB 생성
create table debugBoardDB (
 BOARDNUM Number(10) primary key,
 NICKNAME VARCHAR2(20) NOT NULL,
 PASSWORD Varchar2(20),
 TITLE VARCHAR2(200) NOT NULL,
 CATEGORY VARCHAR2(20) NOT NULL,
 CONTENT VARCHAR2(2000) NOT NULL,
 POSTDATE VARCHAR2(30) NOT NULL,
 EDITTEDDATE VARCHAR2(30),
 VIEWCOUNT NUMBER(20) DEFAULT 0 NOT NULL,
 RECOMMENDNUM   NUMBER(20) DEFAULT 0 NOT NULL,
 DISRECOMMENDNUM  NUMBER(20) DEFAULT 0 NOT NULL
);

---- 감독,저자 (producerDB) 테이블
CREATE TABLE producerDB (
   producerId NUMBER PRIMARY KEY,
   producerName VARCHAR(255) NOT NULL
);
-- 감독데이터 삽입
insert into producerdb values (1, 'test');
commit;

---- 작품 (컨텐츠)(contentDB) 테이블
CREATE TABLE contentDB (
    contId NUMBER PRIMARY KEY,
    contTitle VARCHAR2(255) NOT NULL,
    producerId NUMBER,
    description CLOB,
    nation VARCHAR2(20),
    releaseDate DATE,
    avgRate NUMBER(2, 1),
    contType VARCHAR2(20) NOT NULL,
    contImg VARCHAR2(500),
    CONSTRAINT contType_check CHECK (contType IN ('movie', 'book', 'tv_show', 'tv_drama')),
    CONSTRAINT contentDB_producerId_fk FOREIGN KEY (producerId) REFERENCES producerDB(producerId)
);
-- 컨텐츠 데이터 삽입
insert into contentdb values (1, '스즈메의 문단속', 1, '“이 근처에 폐허 없니? 문을 찾고 있어”
규슈의 한적한 마을에 살고 있는 소녀 ‘스즈메’는 문을 찾아 여행 중인 청년 ‘소타’를 만난다. 그의 뒤를 쫓아 산속 폐허에서 발견한 낡은 문. 스즈메가 무언가에 이끌리듯 문을 열자 마을에 재난의 위기가 닥쳐오고 가문 대대로 문 너머의 재난을 봉인하는 소타를 도와 간신히 문을 닫는다.
“닫아야만 하잖아요, 여기를!”
재난을 막았다는 안도감도 잠시, 수수께끼의 고양이 ‘다이진’이 나타나 소타를 의자로 바꿔 버리고 일본 각지의 폐허에 재난을 부르는 문이 열리기 시작하자 스즈메는 의자가 된 소타와 함께 재난을 막기 위한 여정에 나선다.
“꿈이 아니었어”
규슈, 시코쿠, 고베, 도쿄. 재난을 막기 위해 일본 전역을 돌며 필사적으로 문을 닫아가던 중 어릴 적 고향에 닿은 스즈메는 잊고 있던 진실과 마주하게 되는데…', '한국', sysdate, null, 'movie',
'https://an2-img.amz.wtchn.net/image/v2/T7qP_idp-A7AdHCV6-wZBA.jpg?jwt=ZXlKaGJHY2lPaUpJVXpJMU5pSjkuZXlKdmNIUnpJanBiSW1SZk5Ea3dlRGN3TUhFNE1DSmRMQ0p3SWpvaUwzWXlMM04wYjNKbEwybHRZV2RsTHpFMk56VTJOVE16TlRNNE9EVTVNVEEyTURVaWZRLmZxSThtNU1jQl9HSDFxQ0plZGlUYUxPa1R4WTVwSC1kZGhNWVhISy16anM');
commit;

---- 별점 (rateDB) 테이블
CREATE TABLE rateDB (
    userId VARCHAR2(20),
    contId NUMBER,
    score NUMBER(2, 0) DEFAULT 0, 
    CONSTRAINT rateDB_userId_fk FOREIGN KEY (userId) REFERENCES memberDB(userId),
    CONSTRAINT rateDB_contId_fk FOREIGN KEY (contId) REFERENCES contentDB(contId) ON DELETE CASCADE,
    CONSTRAINT rateDB_pk PRIMARY KEY(userId, contId)
);

---- 게시글 말머리 (PostCategories) 테이블
CREATE TABLE PostCategories (
    categoryId INT PRIMARY KEY,
    categoryName VARCHAR2(255) NOT NULL,
    description CLOB,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP
);
-- 게시글 말머리 데이터 삽입
INSERT INTO postcategories (CATEGORYID, CATEGORYNAME, DESCRIPTION, CREATEDAT) VALUES (1, '일반', '영화>자유>일반', TO_TIMESTAMP('24/02/05 18:12:26.221000000', 'RR/MM/DD HH24:MI:SS.FF'));
INSERT INTO postcategories (CATEGORYID, CATEGORYNAME, DESCRIPTION, CREATEDAT) VALUES (2, '신작', '영화>자유>신작', TO_TIMESTAMP('24/02/05 18:12:26.226000000', 'RR/MM/DD HH24:MI:SS.FF'));
INSERT INTO postcategories (CATEGORYID, CATEGORYNAME, DESCRIPTION, CREATEDAT) VALUES (3, '후기', '영화>자유>후기', TO_TIMESTAMP('24/02/05 18:12:26.228000000', 'RR/MM/DD HH24:MI:SS.FF'));
INSERT INTO postcategories (CATEGORYID, CATEGORYNAME, DESCRIPTION, CREATEDAT) VALUES (4, '추천', '영화>자유>추천', TO_TIMESTAMP('24/02/05 18:12:26.228000000', 'RR/MM/DD HH24:MI:SS.FF'));
INSERT INTO postcategories (CATEGORYID, CATEGORYNAME, DESCRIPTION, CREATEDAT) VALUES (5, '토론', '영화>자유>토론', TO_TIMESTAMP('24/02/05 18:12:26.229000000', 'RR/MM/DD HH24:MI:SS.FF'));
INSERT INTO postcategories (CATEGORYID, CATEGORYNAME, DESCRIPTION, CREATEDAT) VALUES (6, '해외', '영화>자유>해외', TO_TIMESTAMP('24/02/05 18:12:39.342000000', 'RR/MM/DD HH24:MI:SS.FF'));
commit;

---- 게시물 글 (postDB) 테이블
CREATE TABLE postDB (
    postId NUMBER(5, 0) PRIMARY KEY,
    postBoard VARCHAR(20) NOT NULL,
    userId VARCHAR2(20) ,
    contId NUMBER(5, 0),
    postTitle VARCHAR(50) NOT NULL,
    postDate DATE DEFAULT SYSDATE,
    postEditDate DATE,
    postText CLOB NOT NULL,
    nickname VARCHAR(20) NOT NULL,
    categoryId NUMBER(38,0),
    CONSTRAINT postDB_userId_fk FOREIGN KEY (userId) REFERENCES memberDB(userId),
    CONSTRAINT fk_post_categories FOREIGN KEY (categoryId) REFERENCES PostCategories (categoryId)
);

---- 게시물 조회수 (postInfoDB) 테이블
CREATE TABLE postInfoDB (
postId NUMBER(5, 0),
viewNum NUMBER(5, 0) DEFAULT 0,
CONSTRAINT postInfoDB_postId_fk FOREIGN KEY (postId) REFERENCES postDB(postId) ON DELETE CASCADE
);

---- 게시물 좋아요수 (likDB) 테이블
CREATE TABLE likeDB (
    postId NUMBER(5, 0),
    userId VARCHAR2(20),
    isLike char(1) DEFAULT 0 CHECK (isLike IN (0, 1)), -- 1이 공감버튼 누른 상태 ()
    CONSTRAINT likeDB_userId_fk FOREIGN KEY (userId) REFERENCES memberDB(userId),
    CONSTRAINT likeDB_postId_fk FOREIGN KEY (postId) REFERENCES postDB(postId) ON DELETE CASCADE,
    CONSTRAINT likeDB_pk PRIMARY KEY(userId, postId)
);

---- 댓글 (commentDB) 테이블
CREATE TABLE commentDB (
    comId NUMBER(5, 0) PRIMARY KEY,
    postId NUMBER(5, 0),
    userId VARCHAR2(20),
    comDate DATE DEFAULT SYSDATE,
    comText CLOB NOT NULL,
    aboveCom NUMBER(5, 0),
    nickname VARCHAR2(20),
    aboveComId NUMBER(5, 0),
    CONSTRAINT commentDB_postId_fk FOREIGN KEY (postId) REFERENCES postDB(postId) ON DELETE CASCADE,
    CONSTRAINT commentDB_userId_fk FOREIGN KEY (userId) REFERENCES memberDB(userId)
);

---- 게시글 임시저장 (postSaveDB) 테이블
CREATE TABLE postSaveDB (
    postSaveId NUMBER(5, 0) PRIMARY KEY,
    userId VARCHAR2(20),
    postSaveTitle VARCHAR(50) NOT NULL,
    postSaveText CLOB NOT NULL,
    postSaveDate DATE DEFAULT SYSDATE,
    CONSTRAINT postSaveDB_userId_fk FOREIGN KEY (userId) REFERENCES memberDB(userId)
);

---- 쪽지기능 (messages) 테이블
CREATE TABLE Messages (
    MessageID NUMBER PRIMARY KEY,
    SenderID VARCHAR2(20 BYTE) REFERENCES MemberDB(USERID),
    ReceiverID VARCHAR2(20 BYTE) REFERENCES MemberDB(USERID),
    MessageContent CLOB,
    SendDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ReadStatus VARCHAR2(1 BYTE) DEFAULT 'N' CHECK (ReadStatus IN ('Y', 'N'))
);

-- 신고 (reprotDB) 테이블
CREATE TABLE reportDB (
    postId NUMBER(5, 0),
    reason VARCHAR2(4000),
    userId VARCHAR2(20),
    reportDate VARCHAR2(10),
    CONSTRAINT reportDB_pk PRIMARY KEY (postId, userId),
    CONSTRAINT reportdb_postid_fk FOREIGN KEY (postId) REFERENCES postDB(postId) ON DELETE CASCADE,
    CONSTRAINT reportdb_userid_fk FOREIGN KEY (userId) REFERENCES memberDB(userId)
);



----------<시퀀스>
-- 감독,저자id 시퀀스 (   producerIdSeq.NEXTVAL   )
CREATE SEQUENCE producerIdSeq NOCACHE;

--컨텐츠id 시퀀스 (   contIdSeq.NEXTVAL   )
CREATE SEQUENCE contIdSeq NOCACHE;

--게시글 식별번호 id 시퀀스 (   postIdSeq.NEXTVAL   )
CREATE SEQUENCE postIdSeq NOCACHE;

--댓글 식별번호 id 시퀀스 (   comId.NEXTVAL   )
CREATE SEQUENCE comIdSeq NOCACHE;

--게시글 말머리 시퀀스 (   POSTCATEGORIESSEQ.NEXTVAL   )
CREATE SEQUENCE POSTCATEGORIESSEQ
START WITH 1
INCREMENT BY 1
NOMAXVALUE;

--쪽지id 시퀀스 (   MessageID_Seq.NEXTVAL   )
CREATE SEQUENCE MessageID_Seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

--디버그 게시판용 시퀸스
CREATE SEQUENCE debugBoard_seq
 INCREMENT BY 1
 START WITH 1
 MINVALUE 1
 MAXVALUE 99999
 NOCYCLE;

----------<트리거> => 개별로 실행해야 정상적으로 컴파일 됨.
--postdb insert시 postinfodb 같은 postid로 생성되게 하는 트리거
CREATE OR REPLACE TRIGGER trg_post_after_insert
AFTER INSERT ON postdb
FOR EACH ROW
BEGIN
  INSERT INTO postinfodb (POSTID, VIEWNUM)
  VALUES (:new.POSTID, 0);
END;

--PostCategories 테이블 => categoryId 자동 증가 및 updatedAt 자동 갱신 구현: categoryId 자동 증가
CREATE OR REPLACE TRIGGER trg_post_categories_id
BEFORE INSERT ON PostCategories
FOR EACH ROW
BEGIN
    SELECT POSTCATEGORIESSEQ.NEXTVAL
    INTO :new.categoryId
    FROM dual;
END;

-- PostCategories 테이블 => updated_at 자동 갱신 트리거
CREATE OR REPLACE TRIGGER trg_post_categories_updated_at
BEFORE UPDATE ON PostCategories
FOR EACH ROW
BEGIN
    :new.updatedAt := CURRENT_TIMESTAMP;
END;
```
