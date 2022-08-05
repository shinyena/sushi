//package com.example.sushi.repository.user;
//
//import com.example.sushi.entity.user.Member;
//import com.example.sushi.entity.user.MemberRole;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class MemberRepositoryTest {
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    void insertMember() {
//        Member member = Member.builder()
//                .email("yena5790@naver.com")
//                .memberRole(MemberRole.USER)
//                .build();
//        memberRepository.save(member);
//    }
//
//}