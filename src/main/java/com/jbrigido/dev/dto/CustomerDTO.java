package com.jbrigido.dev.dto;

import java.time.LocalDate;

public record CustomerDTO(long id, String name, String lastName, String motherName, String phoneNumber,
                          String email, LocalDate birthday, String address,
                          boolean status) {
    public CustomerDTO(long id, String name, String lastName, String motherName, String phoneNumber, boolean status) {
        this(id, name, lastName, motherName, phoneNumber, "", null, "", status);
    }
}
