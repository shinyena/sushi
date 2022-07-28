package com.example.sushi.service;

import com.example.sushi.dto.user.ReservationDTO;
import com.example.sushi.entity.user.Member;
import com.example.sushi.entity.user.Reservation;
import com.example.sushi.entity.user.ReserveTime;
import com.example.sushi.entity.user.ReserveTimeID;
import com.example.sushi.repository.user.MemberRepository;
import com.example.sushi.repository.user.ReservationRepository;
import com.example.sushi.repository.user.ReserveTimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final ReservationRepository repository;
    private final MemberRepository memberRepository;
    private final ReserveTimeRepository timeRepository;

    @Override
    public void register(ReservationDTO reservationDTO) {
        Optional<ReserveTime> result = timeRepository.findById(new ReserveTimeID(reservationDTO.getRdate(), reservationDTO.getRtime()));

        if (!result.isPresent()) {
            Map<String, Object> entityMap = dtoToEntity(reservationDTO);

            Optional<Member> byId = memberRepository.findById(reservationDTO.getEmail());
            if (!byId.isPresent()) {
                Member member = (Member) entityMap.get("member");
                memberRepository.save(member);
            }


            ReserveTime reserveTime = (ReserveTime) entityMap.get("reserveTime");
            timeRepository.save(reserveTime);

            Reservation reservation = (Reservation) entityMap.get("reservation");
            repository.save(reservation);
        } else {
            log.error("이미 예약된 날짜, 시간 입니다.");
            log.error(result);
        }

    }

    @Override
    public void modify(ReservationDTO reservationDTO) {
        Optional<Reservation> result = repository.findById(reservationDTO.getRid());

        if (result.isPresent()) {
            Reservation reservation = result.get();
            reservation.changeName(reservationDTO.getName());
            reservation.changePhone(reservationDTO.getPhone());
            reservation.changeCount(reservationDTO.getCount());
            reservation.changeMessage(reservationDTO.getMessage());
            repository.save(reservation);

            Optional<ReserveTime> checkTime = timeRepository.findById(new ReserveTimeID(
                    reservationDTO.getRdate(), reservationDTO.getRtime()));
            if (!checkTime.isPresent()) {
                timeRepository.deleteById(new ReserveTimeID(
                        reservation.getReserveTime().getRdate(),
                        reservation.getReserveTime().getRtime()));

                ReserveTime reserveTime = ReserveTime.builder()
                        .rdate(reservationDTO.getRdate())
                        .rtime(reservationDTO.getRtime())
                        .build();
                timeRepository.save(reserveTime);

                reservation.changeTime(reserveTime);
                repository.save(reservation);
            }
            else {
                log.error("이미 예약된 날짜, 시간 입니다.");
                log.error(checkTime);
            }
        }

    }

    @Override
    public void remove(Long rid) {
        ReserveTime reserveTime = repository.getReserveTimeByRid(rid);
        timeRepository.deleteById(new ReserveTimeID(reserveTime.getRdate(), reserveTime.getRtime()));
        repository.deleteById(rid);
    }

    @Override
    public List<ReservationDTO> getAll() {
        List<Reservation> reservationList = repository.findAllOrderByReserveTime();
        List<ReservationDTO> dtoList = new ArrayList<>();
        reservationList.forEach( reservation -> {
            ReservationDTO dto  = entityToDTO(reservation);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public List<ReservationDTO> getList(String email) {
        List<Reservation> reservationList = repository.findByEmail(email);
        List<ReservationDTO> dtoList = new ArrayList<>();
        reservationList.forEach( reservation -> {
            ReservationDTO dto  = entityToDTO(reservation);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public ReservationDTO getOne(Long rid) {
        Optional<Reservation> result = repository.findById(rid);
        if (result.isPresent()) {
            Reservation reservation = result.get();
            return entityToDTO(reservation);
        }
        return null;
    }

    @Override
    public List<ReservationDTO> getTime(LocalDate date) {
        List<ReservationDTO> dtoList = new ArrayList<>();
        List<Reservation> reservationList = repository.findByDate(date);
        reservationList.forEach( reservation -> {
            ReservationDTO reservationDTO = entityToDTO(reservation);
            dtoList.add(reservationDTO);
        });
        return dtoList;
    }

    @Override
    public List<LocalDate> getDate() {
        List<LocalDate> dateList = timeRepository.getFullReservedDate();
        return dateList;
    }
}
