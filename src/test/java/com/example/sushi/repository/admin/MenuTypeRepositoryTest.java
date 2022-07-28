//package com.example.sushi.repository.admin;
//
//import com.example.sushi.entity.admin.MenuType;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class MenuTypeRepositoryTest {
//    @Autowired
//    MenuTypeRepository repository;
//
//    @Test
//    void insertMenuType() {
//        MenuType menu1 = MenuType.builder().type("초밥").build();
//        repository.save(menu1);
//
//        MenuType menu2 = MenuType.builder().type("면류").build();
//        repository.save(menu2);
//
//        MenuType menu3 = MenuType.builder().type("덮밥류").build();
//        repository.save(menu3);
//    }
//
//}