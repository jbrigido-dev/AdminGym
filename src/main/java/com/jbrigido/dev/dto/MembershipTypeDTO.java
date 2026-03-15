package com.jbrigido.dev.dto;

public record MembershipTypeDTO(int id, String description, int duration, float price) {

    public MembershipTypeDTO(String description, float price) {
        this(0, description, 0, price);
    }

    @Override
    public String toString() {
        return description;
    }
}
