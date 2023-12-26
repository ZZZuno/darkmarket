CREATE TABLE CATEGORY_TBL(
        CG_CODE            NUMBER    PRIMARY KEY,    -- 카테고리 코드
        CG_PARENT_CODE         NUMBER    NULL,           -- 상위카테고리 코드
        CG_NAME            VARCHAR2(50)    NOT NULL,
        FOREIGN KEY(CG_PARENT_CODE) REFERENCES CATEGORY_TBL(CG_CODE)
);


-- 1차 카테고리 : TOP(1) PANTS(2) SHIRTS(3) OUTER(4) SHOES(5) BAG(6) ACC(7)
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (1,NULL,'닭가슴살');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (2,NULL,'즉석 간편식');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (3,NULL,'도시락,볶음밥');    
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (4,NULL,'소고기');        
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (5,NULL,'돼지,오리고기');    
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (6,NULL,'닭안심살');    
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (7,NULL,'음료,프로틴');    

-- 1차카테고리 TOP : 1
-- 2차 카테고리 : 긴팔티 니트 맨투맨/후드티 프린팅티 나시 반팔티/7부티
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (8,1,'스테이크,소시지');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
VALUES (9,1,'스팀,소프트,슬라이스');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
VALUES (10,1,'소스닭가슴살');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
VALUES (11,1,'볼,큐브');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
VALUES (12,1,'냉장,실온보관');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
VALUES (13,1,'생닭가슴살');

-- 1차카테고리 PANTS : 2
-- 2차카테고리 : 밴딩팬츠 청바지 슬랙스 면바지 반바지
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (14,2,'브리또');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (15,2,'핫도그');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (16,2,'즉석밥,곤약밥');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (17,2,'곤약면,파스타');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (18,2,'치킨');
   
-- 1차카테고리 SHIRTS : 3
-- 2차카테고리 : 헨리넥/차이나 베이직 체크/패턴 청남방 스트라이프

INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (19,3,'다이어트 도시락');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (20,3,'간편 도시락');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (21,3,'주먹밥,김밥');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (22,3,'덮밥,컵밥');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (23,3,'볶음밥');
   
   
-- 1차카테고리 OUTER : 4
-- 2차카테고리 : 패딩 코트 수트/블레이져 자켓 블루종/MA-1 가디건/조끼 후드/집업

INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (24,4,'설도,홍두깨살');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (25,4,'스테이크,볼');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (26,4,'국거리,조리용');

   
-- 1차카테고리 SHOES : 5
-- 2차카테고리 : 스니커즈 로퍼/구두 키높이신발/깔창 슬리퍼/쪼리/샌들
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (27,5,'돼지고기');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (28,5,'오리고기');

   
-- 1차카테고리 BAG : 6
-- 2차카테고리 : 백팩 토트/숄더백 크로스백 클러치
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (29,6,'소프트');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (30,6,'생 안심');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (31,6,'구이,탕수육');

-- 1차카테고리 ACC : 7
-- 2차카테고리 : 양말/넥타이 모자 머플러/장갑 아이웨어 벨트/시계 기타
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (32,7,'제로음료');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (33,7,'프로틴쉐이크');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (34,7,'보충제,부스터');
INSERT INTO category_tbl (CG_CODE,CG_PARENT_CODE,CG_NAME)
    VALUES (35,7,'프로틴음료');