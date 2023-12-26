CREATE TABLE PAYMENT (
    PAY_CODE            NUMBER          PRIMARY KEY, -- 일련번호
    ORD_CODE            NUMBER          NOT NULL,    -- 주문번호  
    DARK_ID             VARCHAR2(50)    NOT NULL,    -- 회원ID
    PAY_METHOD          VARCHAR2(50)    NOT NULL,    -- 결제방식
    PAY_DATE            DATE                NULL,    -- 결제일
    PAY_TOT_PRICE       NUMBER          NOT NULL,    -- 결제금액
    PAY_NOBANK_PRICE    NUMBER              NULL,    -- 무통장입금금액
    PAY_NOBANK_USER     VARCHAR2(50)        NULL,    -- 무통장입금자명
    PAY_NOBANK          VARCHAR2(50)        NULL,    -- 입금은행
    PAY_MEMO            VARCHAR2(100)       NULL,     -- 메모
    PAY_BANKACCOUNT     VARCHAR2(50)        NULL
);