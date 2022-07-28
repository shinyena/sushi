//package com.example.sushi.repository.admin;
//
//import com.example.sushi.entity.admin.Information;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class InformationRepositoryTest {
//    @Autowired
//    InformationRepository repository;
//
//    @Test
//    void insertInformation() {
//        Information information = Information.builder()
//                .adminId("sushicaptain")
//                .password("1234")
//                .location("인천 중구 햇내로안길 48-8")
//                .open("11:30 AM - 14:00 PM, 17:00 PM - 21:00 PM")
//                .close("첫째주 토요일, 매주 일요일")
//                .instagram("sushi_captain")
//                .phone("032-223-1117")
//                .build();
//
//        repository.save(information);
//    }
//
//}