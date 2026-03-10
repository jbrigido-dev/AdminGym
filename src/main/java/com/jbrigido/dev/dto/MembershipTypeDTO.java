package com.jbrigido.dev.dto;

public record MembershipTypeDTO(int id, String description, int duration, float price) {


    @Override
    public String toString() {
        return description;
    }
}
