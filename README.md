<h1>레스토랑 예약 프로그램</h1>
https://sushicaptain.com
<br>

<h2>Entity&DTO</h2>
    <h3>사용자</h3>
    <ul>
        <li>
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/java/com/example/sushi/dto/user/MemberDTO.java">MemberDTO</a>:
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/java/com/example/sushi/entity/user/Member.java">Member</a>,
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/java/com/example/sushi/entity/user/MemberRole.java">MemberRole</a>
        </li>
        <li>
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/java/com/example/sushi/dto/user/ReservationDTO.java">ReservationDTO</a>:
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/java/com/example/sushi/entity/user/Reservation.java">Reservation</a>,
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/java/com/example/sushi/entity/user/ReserveTime.java">ReserveTime</a>
        </li>
    </ul>
    <h3>관리자</h3>
    <ul>
        <li>
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/java/com/example/sushi/dto/admin/InformationDTO.java">InformationDTO</a>:
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/java/com/example/sushi/entity/admin/Information.java">Information</a>
        </li>
        <li>
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/java/com/example/sushi/dto/admin/MenuDTO.java">MenuDTO</a>:
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/java/com/example/sushi/entity/admin/Menu.java">Menu</a>,
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/java/com/example/sushi/entity/admin/MenuType.java">MenuType</a>
        </li>
    </ul>
<br>

<h2>Service</h2>
    <h3><a href="https://github.com/shinyena/sushi/blob/master/src/main/java/com/example/sushi/service/MemberServiceImpl.java">MemberService</a></h3>
    <ul>
        <li>회원 등록, 회원 수정, 회원 삭제</li>
        <li>전체 회원 정보 조회, 개별 회원 정보 조회</li>
    </ul>
    <h3><a href="https://github.com/shinyena/sushi/blob/master/src/main/java/com/example/sushi/service/ReservationServiceImpl.java">ReservationService</a></h3>
    <ul>
        <li>예약 등록, 예약 수정, 예약 삭제</li>
        <li>전체 예약 조회, 개별 예약 조회, 회원 예약 조회</li>
        <li>예약 가능 날짜 조회, 예약 가능 시간 조회</li>
    </ul>
    <h3><a href="https://github.com/shinyena/sushi/blob/master/src/main/java/com/example/sushi/service/AdminServiceImpl.java">AdminService</a></h3>
    <ul>
        <li>레스토랑 정보 조회, 레스토랑 정보 수정</li>
        <li>메뉴 등록, 메뉴 수정, 메뉴 삭제</li>
        <li>전체 메뉴 조회, 개별 메뉴 조회</li>
        <li>메뉴 종류 등록, 메뉴 종류 수정, 메뉴 종류 삭제, 메뉴 종류 조회 (개발 예정)</li>
    </ul>
<br>

<h2>View</h2>
    <h3>사용자 화면</h3>
    <ul>
        <li>
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/resources/templates/sushi/main.html">메인 화면</a>:
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/resources/templates/sushi/fragment/hero.html">레스토랑 배너</a>,
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/resources/templates/sushi/fragment/menu.html">레스토랑 메뉴</a>,
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/resources/templates/sushi/fragment/contact.html">레스토랑 정보</a>
        </li>
        <li>
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/resources/templates/sushi/register.html">예약 등록 화면</a>:
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/resources/templates/sushi/fragment/hero.html">레스토랑 배너</a>,
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/resources/templates/sushi/fragment/book.html">예약 등록</a>
        </li>
        <li>
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/resources/templates/sushi/list.html">예약 조회 화면</a>:
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/resources/templates/sushi/fragment/hero.html">레스토랑 배너</a>,
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/resources/templates/sushi/fragment/special.html">예약 조회</a>
        </li>
    </ul>
    <h3>관리자 화면</h3>
    <ul>
        <li>
            <a href="member">회원 내역 조회 화면</a>
            <a href="">회원별 예약 내역 조회 화면</a>
        </li>
        <li>
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/resources/templates/admin/list.html">예약 내역 조회 화면</a>,
            <a href="https://github.com/shinyena/sushi/blob/master/src/main/resources/templates/admin/read.html">예약별 상세 내역 조회 화면</a>
        </li>
        <li><a href="https://github.com/shinyena/sushi/blob/master/src/main/resources/templates/admin/menu.html">메뉴 변경 화면</a></li>
        <li><a href="menutype">메뉴 종류 변경 화면</a></li>
        <li><a href="/src/main/resources/templates/admin/information.html">관리자 정보 변경 화면</a></li>
    </ul>
<br>    

<h2>etc</h2>
    <h3>로그인</h3>
    <ul>
        <li>annotation: <a href="https://github.com/shinyena/sushi/blob/master/src/main/java/com/example/sushi/annotation/LoginCheckAspect.java">LoginCheckAspect</a></li>
        <li>config: <a href="https://github.com/shinyena/sushi/blob/master/src/main/java/com/example/sushi/config/SecurityConfig.java">SecurityConfig</a></li>
        <li>service: <a href="https://github.com/shinyena/sushi/blob/master/src/main/java/com/example/sushi/service/KakaoLoginService.java">KakaoLoginService</a></li>
    </ul>
    <h3>로그 처리</h3>
    <ul>
        <li><a href="https://github.com/shinyena/sushi/blob/master/src/main/resources/logback-spring.xml">logback-spring.xml</a></li>
    </ul>
    <h3>에러 처리</h3>
    <ul>
        <li>controller: <a href="https://github.com/shinyena/sushi/blob/master/src/main/java/com/example/sushi/controller/CustomErrorController.java">CustomErrorController</a></li>
        <li><a href="https://github.com/shinyena/sushi/blob/master/src/main/resources/templates/error.html">error.html</a></li>
    </ul>
<br>
        
