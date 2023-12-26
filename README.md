# Spring-Project-DarkMarket
스프링 + JSP 파싱_운동식단쇼핑몰


## 프로젝트 소개
쇼핑몰 관리자 페이지를 포함한 운동인을 위한 여러 식단을 판매하는 쇼핑몰을 만들었습니다. (master 브랜치)
<br>

## 개발 기간
* 23.11.17 ~ 23.12.27

### 개발 환경
- `Java 8`
- `JDK 1.8.0`
- **IDE** : STS 4.7
- **Framework** : Spring(MVC)
- **Database** : Oracle DB(11gXE)
- **ORM** : Mybatis

## 주요 기능
#### 로그인
- DB값 검증
- ID, PW 찾기
- 로그인 시 세션(Session) 생성
- 인터셉터 기능 구현(로그인 세션이 소멸된 상태로 접근 시 로그인 페이지로 이동)

#### 회원가입
- ID 중복체크
- DAUM 주소 API 연동
- 이메일 인증 구현(Gmail 활용)

#### 마이페이지
- DAUM 주소 API 연동
- 회원정보 변경(인증필요)
- 회원탈퇴(인증필요)
- 인터셉터 기능 구현

#### 장바구니, 주문하기
- 장바구니 수량 변경 및 삭제
- 주문시 카카오페이 API 연동
- 주문완료 후 나의 주문상태 확인기능
- 인터셉터 기능 구현

#### 카테고리
- 1차 카테고리, 2차 카테고리(하위카테고리)
- 1차 카테고리에 마우스 over 시 2차 카테고리가 나타나도록 구현
- 카테고리로 상품을 분류함

#### 상품목록
- 메인페이지에 대표상품 출력
- 카테고리별로 상품 출력
- 상품 선택, 장바구니에 추가기능

#### 상품 상세 페이지
- 상품정보 열람, 구매하기, 장바구니 담기
- 리뷰 열람, 등록하기(Session id 사용)
- 리뷰 수정 삭제(Session id 사용)

#### 공지사항(게시판)
- 공지사항 게시판 구현


-----
### 관리자기능

#### 로그인
- 관리자 로그인(Session 생성하여 관리)
- 인터셉터 기능 구현
  
#### 상품관리
- 대표상품 지정기능(메인페이지 출력)
- 상품 등록 기능(CKEditor 연동, 이미지 업로드)
- 상품 수정,삭제 기능
- 상품 목록(체크상품 한번에 수정기능)

#### 주문관리
- 주문 목록 기능(주문상태, 결제상태 열람기능)
- 주문 상세정보 열람기능
- 주문정보에서 상품 개별삭제 기능

#### 회원관리
- 회원 정보목록
- 회원 삭제 기능

#### 공지사항 관리
- 공지사항 게시판 글 작성
- 공지사항 게시판 글 수정, 삭제
  
