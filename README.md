# 말하는 감자(Saying potato) - 대학생 전용 대학 주변 상권 내 할인 애플리케이션
Spring Boot, Spring Data JPA, QueryDSL을 활용한 RESTful API 서버

## 🖥️ 프로젝트 소개
말하는 감자(Saying potato)는 대학생 전용 대학 주변 상권 내 할인 애플리케이션입니다.    
COVID-19 사태와 인플레이션으로 많은 어려움을 겪고 있는 업주 분들과
대면 수업 전환으로 고정적으로 높은 외식비를 지출하고 있는 대학생 분들의 부담을 경감시켜드리고자 시작된 프로젝트입니다.   
그 중에서도 현재 리포지터리는 Spring Boot, Spring Data JPA, QueryDSL을 활용한 Backend 관련 repository입니다.   

<img width="1687" alt="1" src="https://user-images.githubusercontent.com/77658361/232472869-38595327-eaf5-4190-9708-1dfb7f87287f.png">
<img width="1688" alt="2" src="https://user-images.githubusercontent.com/77658361/232472887-0e827728-341d-4cc3-b40b-7295f9012251.png">
<img width="1688" alt="3" src="https://user-images.githubusercontent.com/77658361/232472896-a9bafcd4-a916-4b92-be20-5683a7e52bb6.png">
<img width="1686" alt="4" src="https://user-images.githubusercontent.com/77658361/232472903-deb2909b-2d83-4274-8ec6-b1ea1e18471a.png">

<br>

## 🕰️ 개발 기간
* 23.3.16일 - 현재
<img width="1685" alt="7" src="https://user-images.githubusercontent.com/77658361/232473032-06af219f-4b78-477b-8ad0-4f80d47c9c1d.png">

### 🧑‍🤝‍🧑 맴버구성
- @xylogan - 회원 인증 및 로그인 등과 같은 회원과 관련된 비즈니스 로직 개발, CI/CD 구축, Docker 및 Kubernetes 기반의 Cloud Native 배포 
- @jinhoon227 - 가게 정보, 요일 별 할인 페이지, 리뷰 등 메인 비즈니스 로직 개발, Docker 및 Kubernetes 기반의 Cloud Native 배포

### ⚙️ 개발 환경
- **Java Platform** : `OpenJDK 11`
- **IDE** : `IntelliJ IDEA`
- **Framework** : `Springboot(2.7.9)`
- **Database** : `MySQL Community (8.0)`
- **ORM** : `Spring Data JPA`
- **Deployment** : `Container-Optimized OS-based GCE(Google Compute Engine)`
- **Container** : `Docker(20.10.12)`
<img width="1685" alt="6" src="https://user-images.githubusercontent.com/77658361/232473152-2e0c8c1b-5d27-4b2b-8bcb-48f9c3ee0600.png">

## 📌 주요 기능
#### 로그인 및 회원가입
- Spring Security와 JWT를 활용한 인증 인가
- PASS 본인 인증
- 학생 인증을 위한 교내 Web mail 혹은 학생증 인증
- 회원 가입 시 동시성 및 중복 이슈 해결

#### 요일별 할인 페이지
- 요일별 할인 가게 리스트업 기능
- GPS 기반 현재 위치와의 거리 + 할인율 순 정렬 

#### 가게 상세 페이지
- 메뉴 정보 제공
- 리뷰 정보 제공
- 가게 자리 배치도 제공

#### 주문 페이지
- 장바구니 기능
- 물품 선택 후 주문 시 해당 가게 업주 분에게 Push 알람 기능

#### 업주 페이지
- 가게 상태 및 정보 관리 기능
- 데이터 기반 판매 및 매출 통계 분석

#### 관리자 페이지
- 학생증 인증 처리
- 고객 문의 사항 관리
- 회원 관리
