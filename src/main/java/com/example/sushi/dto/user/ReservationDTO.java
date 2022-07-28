package com.example.sushi.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private Long rid;
    private String email;
    private String name;
    private String phone;
    private int count;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rdate;
    private String rtime;
    private String message;
}
