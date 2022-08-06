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
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReserveTimeRepository timeRepository;
    private final MemberRepository memberRepository;

    @Override
    public void register(ReservationDTO reservationDTO) {
        Optional<Member> memberRepositoryById = memberRepository.findById(reservationDTO.getMid());
        Optional<ReserveTime> timeRepositoryById = timeRepository.findById(new ReserveTimeID(reservationDTO.getRdate(), reservationDTO.getRtime()));

        if (memberRepositoryById.isPresent()) {
            Member member = memberRepositoryById.get();
            if (!timeRepositoryById.isPresent()) {
                Map<String, Object> entityMap = dtoToEntity(reservationDTO, member);

                ReserveTime reserveTime = (ReserveTime) entityMap.get("reserveTime");
                timeRepository.save(reserveTime);

                Reservation reservation = (Reservation) entityMap.get("reservation");
                reservationRepository.save(reservation);
            }
            else {
                log.error(reservationDTO.getRdate() + reservationDTO.getRtime() + ": timeRepository 조회 오류");
            }
        }
        else {
            log.error(reservationDTO.getRid() + ": memberRepository 조회 오류");
        }
    }

    @Transactional
    @Override
    public void modify(ReservationDTO reservationDTO) {
        Optional<Reservation> repositoryById = reservationRepository.findById(reservationDTO.getRid());

        if (repositoryById.isPresent()) {
            Reservation reservation = repositoryById.get();

            /** reservation update (other) */
            reservation.changeName(reservationDTO.getName());
            reservation.changePhone(reservationDTO.getPhone());
            reservation.changeCount(reservationDTO.getCount());
            reservation.changeMessage(reservationDTO.getMessage());
            reservationRepository.save(reservation);

            Optional<ReserveTime> timeRepositoryById = timeRepository.findById(
                    new ReserveTimeID(reservationDTO.getRdate(), reservationDTO.getRtime()));
            if (!timeRepositoryById.isPresent()) {
                /** reserveTime delete */
                timeRepository.deleteById(new ReserveTimeID(
                        reservation.getReserveTime().getRdate(),
                        reservation.getReserveTime().getRtime()));

                /** reserveTime insert */
                ReserveTime reserveTime = ReserveTime.builder()
                        .rdate(reservationDTO.getRdate())
                        .rtime(reservationDTO.getRtime())
                        .build();
                timeRepository.save(reserveTime);

                /** reservation update (reserveTime) */
                reservation.changeTime(reserveTime);
                reservationRepository.save(reservation);
            } else {
                log.error(reservationDTO.getRdate() + reservationDTO.getRtime() + ": timeRepository 조회 오류");
            }
        } else {
            log.error(reservationDTO.getRid() + ": reservationRepository 조회 오류");
        }
    }

    @Override
    public void remove(Long rid) {
        Optional<Reservation> repositoryById = reservationRepository.findById(rid);
        if (repositoryById.isPresent()) {
            ReserveTime reserveTime = repositoryById.get().getReserveTime();
            ReserveTimeID reserveTimeID = new ReserveTimeID(reserveTime.getRdate(), reserveTime.getRtime());
            Optional<ReserveTime> timeRepositoryById = timeRepository.findById(reserveTimeID);
            if (timeRepositoryById.isPresent()) {
                timeRepository.deleteById(reserveTimeID);
                reservationRepository.deleteById(rid);
            } else {
                log.error(reserveTime + ": timeRepository 조회 오류");
            }
        } else {
            log.error(rid + ": reservationRepository 조회 오류");
        }
    }

    @Override
    public List<ReservationDTO> getAll() {
        List<Reservation> reservationList = reservationRepository.findAllOrderByReserveTime();
        List<ReservationDTO> dtoList = new ArrayList<>();
        reservationList.forEach( reservation -> {
            ReservationDTO dto  = entityToDTO(reservation);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public List<ReservationDTO> getList(Long mid) {
        List<Reservation> reservationList = reservationRepository.findByMid(mid);
        List<ReservationDTO> dtoList = new ArrayList<>();
        reservationList.forEach( reservation -> {
            ReservationDTO dto  = entityToDTO(reservation);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public ReservationDTO getOne(Long rid) {
        Optional<Reservation> result = reservationRepository.findById(rid);
        if (result.isPresent()) {
            Reservation reservation = result.get();
            return entityToDTO(reservation);
        }
        return null;
    }

    @Override
    public List<ReservationDTO> getTime(LocalDate date) {
        List<ReservationDTO> dtoList = new ArrayList<>();
        List<Reservation> reservationList = reservationRepository.findByDate(date);
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
