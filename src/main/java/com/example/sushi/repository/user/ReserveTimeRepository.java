package com.example.sushi.repository.user;

import com.example.sushi.entity.user.ReserveTime;
import com.example.sushi.entity.user.ReserveTimeID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReserveTimeRepository extends JpaRepository<ReserveTime, ReserveTimeID> {
    @Query("select rdate from ReserveTime group by rdate having count(rdate) = 6")
    List<LocalDate> getFullReservedDate();
}
