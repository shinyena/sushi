package com.example.sushi.service;

import com.example.sushi.dto.user.ReservationDTO;
import com.example.sushi.entity.user.Member;
import com.example.sushi.entity.user.Reservation;
import com.example.sushi.entity.user.ReserveTime;
import com.example.sushi.repository.user.MemberRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ReservationService {
    Long register(ReservationDTO reservationDTO);   // 예약 등록
    Long modify(ReservationDTO reservationDTO);     // 예약 수정
    void remove(Long rid);                          // 예약 삭제

    List<ReservationDTO> getAll();                  // 전체 예약 조회
    List<ReservationDTO> getList(Long mid);         // 한 사람의 전체 예약 조회
    ReservationDTO getOne(Long rid);                // 개별 예약 조회
    List<ReservationDTO> getTime(LocalDate date);   // 예약 가능 시간 조회
    List<LocalDate> getDate();                      // 예약 가능 날짜 조회

    default Map<String, Object> dtoToEntity(ReservationDTO dto, Member member) {
        Map<String, Object> entityMap = new HashMap<>();

        ReserveTime reserveTime = ReserveTime.builder()
                .rdate(dto.getRdate())
                .rtime(dto.getRtime())
                .build();
        entityMap.put("reserveTime", reserveTime);

        Reservation reservation = Reservation.builder()
                .rid(dto.getRid())
                .member(member)
                .name(dto.getName())
                .phone(dto.getPhone())
                .count(dto.getCount())
                .reserveTime(reserveTime)
                .message(dto.getMessage())
                .build();
        entityMap.put("reservation", reservation);

        return entityMap;
    }

    default ReservationDTO entityToDTO(Reservation reservation) {
        ReservationDTO dto = ReservationDTO.builder()
                .rid(reservation.getRid())
                .mid(reservation.getMember().getMid())
                .name(reservation.getName())
                .phone(reservation.getPhone())
                .count(reservation.getCount())
                .rdate(reservation.getReserveTime().getRdate())
                .rtime(reservation.getReserveTime().getRtime())
                .message(reservation.getMessage())
                .build();
        return dto;
    }
}
