package com.example.sushi.service;

import com.example.sushi.dto.user.ReservationDTO;
import com.example.sushi.entity.user.Member;
import com.example.sushi.entity.user.Reservation;
import com.example.sushi.entity.user.ReserveTime;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {
    void register(ReservationDTO reservationDTO);
    void modify(ReservationDTO reservationDTO);
    void remove(Long rid);

    List<ReservationDTO> getAll();
    List<ReservationDTO> getList(String email);
    ReservationDTO getOne(Long rid);
    List<ReservationDTO> getTime(LocalDate date);
    List<LocalDate> getDate();

    default Map<String, Object> dtoToEntity(ReservationDTO dto) {
        Map<String, Object> entityMap = new HashMap<>();

        Member member = Member.builder()
                .email(dto.getEmail())
                .build();
        entityMap.put("member", member);

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
                .email(reservation.getMember().getEmail())
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
