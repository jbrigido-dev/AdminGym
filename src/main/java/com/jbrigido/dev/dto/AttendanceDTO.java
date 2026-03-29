package com.jbrigido.dev.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record AttendanceDTO(long id, long customerId, LocalDateTime localDate, long userId) {

    public AttendanceDTO(long customerId, LocalDateTime localDate) {
        this(0, customerId, localDate, 0);

    }

    public AttendanceDTO(long customerId, long userId) {
        this(0, customerId, null, userId);
    }
}
