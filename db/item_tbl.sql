CREATE TABLE ITEM_TBL(
        ITEM_NUM             NUMBER  CONSTRAINT  PK_ITEM_NUM         PRIMARY KEY,
        CG_CODE            NUMBER            NULL,
        ITEM_NAME            VARCHAR2(50)            NOT NULL,
        ITEM_PRICE           NUMBER                  NOT NULL,
        ITEM_DISCOUNT        NUMBER                  NOT NULL,
        ITEM_PUBLISHER       VARCHAR2(50)            NOT NULL,
        ITEM_CONTENT         VARCHAR2(4000)  /* CLOB */                  NOT NULL,       -- 내용이 4000BYTE 초과여부판단?
        ITEM_UP_FOLDER       VARCHAR(50)             NOT NULL,
        ITEM_IMG             VARCHAR(500)             NOT NULL,  -- 날짜폴더경로가 포함하여 파일이름저장
        ITEM_AMOUNT          NUMBER                  NOT NULL,
        ITEM_BUY             CHAR(1)                 NOT NULL,
        ITEM_DATE            DATE DEFAULT SYSDATE    NOT NULL,
        ITEM_UPDATEDATE      DATE DEFAULT SYSDATE    NOT NULL,
        FOREIGN KEY(CG_CODE) REFERENCES CATEGORY_TBL(CG_CODE)
);

CREATE SEQUENCE SEQ_ITEM_TBL;
