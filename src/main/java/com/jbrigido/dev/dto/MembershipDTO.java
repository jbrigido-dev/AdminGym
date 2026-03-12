package com.jbrigido.dev.dto;

import java.time.LocalDate;

public record MembershipDTO(long id, long customer_id, int type, LocalDate startDate, LocalDate endDate,
                            boolean status) {

    public MembershipDTO(long id, LocalDate startDate, LocalDate endDate, boolean status) {
        this(id, 0, 0, startDate, endDate, status);
    }

    public MembershipDTO(long customer_id, int type, LocalDate startDate, LocalDate endDate) {
        this(0, customer_id, type, startDate, endDate, false);
    }

    public MembershipDTO(long id, LocalDate endDate) {
        this(id, 0, 0, null, endDate, false);
    }
}
