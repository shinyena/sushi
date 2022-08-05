package com.example.sushi.service;

import com.example.sushi.dto.user.MemberDTO;
import com.example.sushi.dto.user.ReservationDTO;
import com.example.sushi.entity.user.Member;
import com.example.sushi.entity.user.Reservation;
import org.springframework.aop.target.LazyInitTargetSource;

import java.util.List;
import java.util.Map;

public interface MemberService {
    Long register(Long mid);                    // 회원 등록
    Long modify(ReservationDTO reservationDTO); // 회원 정보 수정
    void remove(Long mid) ;                     // 회원 삭제
    List<MemberDTO> getAll();                   // 전체 회원 조회
    MemberDTO getOne(Long mid);                 // 개별 회원 조회

    default MemberDTO entityToDTO(Member member) {
        MemberDTO dto = MemberDTO.builder()
                .mid(member.getMid())
                .name(member.getName())
                .phone(member.getPhone())
                .memberRole(member.getMemberRole())
                .build();
        return dto;
    }
}
