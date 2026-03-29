package com.jbrigido.dev.dto;

import java.time.LocalTime;

public record TransactionDTO(long id, LocalTime currentdate, String concept, float amount, long userId,
                             long customerId,String type) {
    public TransactionDTO(String concept, float amount, long userId, long customerId, String type) {
        this(0, null, concept, amount, userId, customerId, type);
    }

    public TransactionDTO(String concept, float amount, long userId, long customerId) {
        this(0, null, concept, amount, userId, customerId, "");
    }
}
