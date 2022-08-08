# 레스토랑 예약 프로그램
:globe_with_meridians: http://sushicaptain.com
- Server: <img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=spring&logoColor=white"/>
  <img src="https://img.shields.io/badge/MariaDB-003545?style=flat-square&logo=mariadb&logoColor=white"/>
  <img src="https://img.shields.io/badge/Amazon_EC2-FF9900?style=flat-square&logo=amazonaws&logoColor=white"/>
- Client: <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=html5&logoColor=white"/>
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=css&logoColor=white"/>
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=white"/>
  <img src="https://img.shields.io/badge/Bootstrap-7952B3?style=flat-square&logo=bootstrap&logoColor=white"/>
  <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=flat-square&logo=thymeleaf&logoColor=white"/>


## Entity&DTO
![](/readmeImg/erd.png)
### 사용자
- [MemberDTO](/src/main/java/com/example/sushi/dto/user/MemberDTO.java):
[Member](/src/main/java/com/example/sushi/entity/user/Member.java),
[MemberRole](/src/main/java/com/example/sushi/entity/user/MemberRole.java)
- [ReservationDTO](/src/main/java/com/example/sushi/dto/user/ReservationDTO.java):
[Reservation](/src/main/java/com/example/sushi/entity/user/Reservation.java),
[ReseveTime](/src/main/java/com/example/sushi/entity/user/ReserveTime.java)
    
### 관리자
- [InformationDTO](/src/main/java/com/example/sushi/dto/admin/InformationDTO.java):
[Information](/src/main/java/com/example/sushi/entity/admin/Information.java)
- [MenuDTO](/src/main/java/com/example/sushi/dto/admin/MenuDTO.java):
[Menu](/src/main/java/com/example/sushi/entity/admin/Menu.java),
[MenuType](/src/main/java/com/example/sushi/entity/admin/MenuType.java)

## Service
### [MemberService](/src/main/java/com/example/sushi/service/MemberServiceImpl.java)
- 회원 등록, 회원 수정, 회원 삭제
- 전체 회원 정보 조회, 개별 회원 정보 조회
### [ReservationService](/src/main/java/com/example/sushi/service/ReservationServiceImpl.java)
- 예약 등록, 예약 수정, 예약 삭제
- 전체 예약 조회, 개별 예약 조회, 회원 예약 조회
- 예약 가능 날짜 조회, 예약 가능 시간 조회
### [AdminService](/src/main/java/com/example/sushi/service/AdminServiceImpl.java)
- 레스토랑 정보 조회, 레스토랑 정보 수정
- 메뉴 등록, 메뉴 수정, 메뉴 삭제
- 전체 메뉴 조회, 개별 메뉴 조회
- 메뉴 종류 등록, 메뉴 종류 수정, 메뉴 종류 삭제, 메뉴 종류 조회 (개발 예정)

## View
### 사용자 화면
![](/readmeImg/user.png)
- [메인화면](/src/main/resources/templates/sushi/main.html):
[레스토랑 배너](/src/main/resources/templates/sushi/fragment/hero.html),
[레스토랑 메뉴](/src/main/resources/templates/sushi/fragment/menu.html),
[레스토랑 정보](/src/main/resources/templates/sushi/fragment/contact.html)
- [예약 등록 화면](/src/main/resources/templates/sushi/register.html):
[레스토랑 배너](/src/main/resources/templates/sushi/fragment/hero.html),
[예약 등록](/src/main/resources/templates/sushi/fragment/book.html)
- [예약 조회 화면](/src/main/resources/templates/sushi/register.html):
[레스토랑 배너](/src/main/resources/templates/sushi/fragment/hero.html),
[예약 조회](/src/main/resources/templates/sushi/fragment/special.html)

### 관리자 화면
![](/readmeImg/admin.png)
- [예약 내역 조회 화면](/src/main/resources/templates/admin/reservation.html),
[예약별 상세 내역 조회 화면](/src/main/resources/templates/admin/read.html)
- [회원 내역 조회 화면](/src/main/resources/templates/admin/member.html),
[회원별 예약 내역 조회 화면](/src/main/resources/templates/admin/list.html)
- [메뉴 변경 화면](/src/main/resources/templates/admin/menu.html),
[메뉴 종류 변경 화면(개발 예정)](/src/main/resources/templates/admin/menutype.html)
- [관리자 정보 변경 화면](/src/main/resources/templates/admin/information.html)

## etc
### 로그인
- annotation: [LoginCheckAspect](/src/main/java/com/example/sushi/annotation/LoginCheckAspect.java)
- config: [SecurityConfig](/src/main/java/com/example/sushi/config/SecurityConfig.java)
- service: [KakaoLoginService](/src/main/java/com/example/sushi/service/KakaoLoginService.java)
### 로그 처리
- [logback-spring.xml](/src/main/resources/logback-spring.xml)
### 에러 처리
- controller: [CustomErrorController](/src/main/java/com/example/sushi/controller/CustomErrorController.java)
- [error.html](/src/main/resources/templates/error.html)    
