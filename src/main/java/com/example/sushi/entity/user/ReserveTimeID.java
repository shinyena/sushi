package com.example.sushi.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveTimeID implements Serializable {
    private LocalDate rdate;
    private String rtime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReserveTimeID that = (ReserveTimeID) o;
        return Objects.equals(rdate, that.rdate) && Objects.equals(rtime, that.rtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rdate, rtime);
    }
}
