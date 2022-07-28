//package com.example.sushi.repository.admin;
//
//import com.example.sushi.entity.admin.Menu;
//import com.example.sushi.entity.admin.MenuType;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest
//class MenuRepositoryTest {
//    @Autowired
//    MenuRepository repository;
//
//    @Test
//    void insertMenu() {
//        Menu menu1 = Menu.builder()
//                .menuName("카이센동")
//                .menuType(MenuType.builder().typeId(3L).build())
//                .menuPrice(25000)
//                .menuComment("다양한 생선회가 올라간 일본식 회덮밥")
//                .build();
//        repository.save(menu1);
//
//        Menu menu2 = Menu.builder()
//                .menuName("우나기동")
//                .menuType(MenuType.builder().typeId(3L).build())
//                .menuPrice(22000)
//                .menuComment("원기회복 보양식 장어덮밥")
//                .build();
//        repository.save(menu2);
//
//        Menu menu3 = Menu.builder()
//                .menuName("이꾸라동")
//                .menuType(MenuType.builder().typeId(3L).build())
//                .menuPrice(30000)
//                .menuComment("연어알 듬뿍 올라간 연어 덮밥")
//                .build();
//        repository.save(menu3);
//
//        Menu menu4 = Menu.builder()
//                .menuName("사케동")
//                .menuType(MenuType.builder().typeId(3L).build())
//                .menuPrice(25000)
//                .menuComment("감칠맛나게 숙성시킨 연어가 올라간 덮밥")
//                .build();
//        repository.save(menu4);
//
//        Menu menu5 = Menu.builder()
//                .menuName("우니동")
//                .menuType(MenuType.builder().typeId(3L).build())
//                .menuPrice(45000)
//                .menuComment("시즌별로 가장 맛이 좋은 내장성게가 듬뿍 올라간 덮밥")
//                .build();
//        repository.save(menu5);
//
//        Menu menu6 = Menu.builder()
//                .menuName("특선초밥(14p)")
//                .menuType(MenuType.builder().typeId(1L).build())
//                .menuPrice(37000)
//                .menuComment("특별한 제철재료로 준비해드립니다.")
//                .build();
//        repository.save(menu6);
//
//        Menu menu7 = Menu.builder()
//                .menuName("모둠초밥(12p)")
//                .menuType(MenuType.builder().typeId(1L).build())
//                .menuPrice(22000)
//                .menuComment("숙성회 연어 참치 찐새우 한치 소라 문어 생새우")
//                .build();
//        repository.save(menu7);
//
//        Menu menu8 = Menu.builder()
//                .menuName("후토마끼(10p)")
//                .menuType(MenuType.builder().typeId(1L).build())
//                .menuPrice(27000)
//                .menuComment("참치, 연어, 새우, 계란 등 각종 재료가 듬뿍 들어간 대왕 김 초밥")
//                .build();
//        repository.save(menu8);
//
//        Menu menu9 = Menu.builder()
//                .menuName("후토마끼(5p)")
//                .menuType(MenuType.builder().typeId(1L).build())
//                .menuPrice(14000)
//                .menuComment("참치, 연어, 새우, 계란 등 각종 재료가 듬뿍 들어간 대왕 김 초밥")
//                .build();
//        repository.save(menu9);
//
//        Menu menu10 = Menu.builder()
//                .menuName("마제소바")
//                .menuType(MenuType.builder().typeId(2L).build())
//                .menuPrice(12000)
//                .menuComment("특제 소스에 비벼먹는 소바")
//                .build();
//        repository.save(menu10);
//
//        Menu menu11 = Menu.builder()
//                .menuName("차슈 온 소바")
//                .menuType(MenuType.builder().typeId(2L).build())
//                .menuPrice(8000)
//                .menuComment("차슈를 얹은 따뜻한 국물 소바")
//                .build();
//        repository.save(menu11);
//
//
//    }
//
//
//
//}