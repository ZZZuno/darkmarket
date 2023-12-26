CREATE TABLE DARK_TBL(

        DARK_ID             VARCHAR2(15),

        DARK_NAME           VARCHAR2(30)            NOT NULL,

        DARK_EMAIL          VARCHAR2(50)            NOT NULL,

        DARK_PASSWORD       CHAR(60)               NOT NULL,        -- 비밀번호 암호화 처리.

        DARK_ZIPCODE        CHAR(5)                 NOT NULL,

        DARK_ADDR           VARCHAR2(100)            NOT NULL,

        DARK_DEADDR         VARCHAR2(100)            NOT NULL,

        DARK_PHONE          VARCHAR2(15)            NOT NULL,

        DARK_POINT          NUMBER DEFAULT 0        NOT NULL,

        DARK_LASTLOGIN      DATE DEFAULT SYSDATE    NOT NULL,

        DARK_DATESUB        DATE DEFAULT SYSDATE    NOT NULL,

        DARK_UPDATEDATE     DATE DEFAULT SYSDATE    NOT NULL

);
