package com.jbrigido.dev.dto;

import java.time.LocalTime;

public record AttendanceDTO(long id, long customerId, LocalTime localDate, long userId) {

    public AttendanceDTO(long customerId, LocalTime localDate) {
        this(0, customerId, localDate, 0);

    }

    public AttendanceDTO(long customerId, long userId) {
        this(0, customerId, null, userId);
    }
}
