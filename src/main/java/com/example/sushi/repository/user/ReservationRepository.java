package com.example.sushi.repository.user;

import com.example.sushi.entity.user.Reservation;
import com.example.sushi.entity.user.ReserveTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select r from Reservation r where r.reserveTime.rdate = :date")
    List<Reservation> findByDate(LocalDate date);

    @Query("select r from Reservation r where r.member.mid = :mid")
    List<Reservation> findByMid(Long mid);

    @Query("select r from Reservation r order by r.reserveTime.rdate asc," +
            " r.reserveTime.rtime asc")
    List<Reservation> findAllOrderByReserveTime();
}
