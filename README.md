<h1>레스토랑 예약 프로그램</h1>
<a href="sushicaptain.com">sushicaptain.com</a>

<h2>Entity&DTO</h2>
<h3>사용자</h3>
<ul>
    <li>
        <a href="">MemberDTO</a>:
        <a href="">Member</a>
        <a href="">MemberRole</a>
    </li>
    <li>
        <a href="">ReservationDTO</a>:
        <a href="">Reservation</a>
        <a href="">ReserveTime</a>
    </li>
</ul>
<h3>관리자</h3>
<ul>
    <li>
        <a href="">InformationDTO</a>:
        <a href="">Information</a>
    </li>
    <li>
        <a href="">MenuDTO</a>:
        <a href="">Menu</a>
        <a href="">MenuType</a>
    </li>
</ul>

<h2>Service</h2>
<h3><a href="">MemberService</a></h3>
<ul>
    <li>회원 등록, 회원 수정, 회원 삭제</li>
    <li>전체 회원 정보 조회, 개별 회원 정보 조회</li>
</ul>
<h3><a href="">ReservationService</a></h3>
<ul>
    <li>예약 등록, 예약 수정, 예약 삭제</li>
    <li>전체 예약 조회, 개별 예약 조회, 회원 예약 조회</li>
    <li>예약 가능 날짜 조회, 예약 가능 시간 조회</li>
</ul>
<h3><a href="">AdminService</a></h3>
<ul>
    <li>레스토랑 정보 조회, 레스토랑 정보 수정</li>
    <li>메뉴 등록, 메뉴 수정, 메뉴 삭제</li>
    <li>전체 메뉴 조회, 개별 메뉴 조회</li>
    <li>메뉴 종류 등록, 메뉴 종류 수정, 메뉴 종류 삭제, 메뉴 종류 조회 (개발 예정)</li>
</ul>

<h2>View</h2>
<h3>사용자 화면</h3>
<ul>
    <li>
        <a href="">메인 화면</a>:
        <a href="">레스토랑 배너</a>
        <a href="">레스토랑 메뉴</a>
        <a href="">레스토랑 정보</a>
    </li>
    <li>
        <a href="">예약 등록 화면</a>:
        <a href="">레스토랑 배너</a>
        <a href="">예약 등록</a>
    </li>
    <li>
        <a href="">예약 조회 화면</a>:
        <a href="">레스토랑 배너</a>
        <a href="">예약 조회</a>
    </li>
</ul>


<h3>관리자 화면</h3>
<ul>
    <li><a href="">회원 내역 조회 화면</a></li>
    <li><a href="">예약 내역 조회 화면</a></li>
    <li><a href="">메뉴 변경 화면</a></li>
    <li><a href="">메뉴 종류 변경 화면</a></li>
    <li><a href="">관리자 정보 변경 화면</a></li>
</ul>

<h2>etc</h2>
    <h3>로그인</h3>
    <ul>
        <li>annotation: <a href="">LoginCheckAspect</a></li>
        <li>config: <a href="">SecurityConfig</a></li>
        <li>service: <a href="">KakaoLoginService</a></li>
    </ul>
    <h3>로그 처리</h3>
    <ul>
        <li><a href="">logback-spring.xml</a></li>
    </ul>
    <h3>에러 처리</h3>
    <ul>
        <li>controller: <a href="">CustomErrorController</a></li>
        <li><a href="">error.html</a></li>
    </ul>
        
