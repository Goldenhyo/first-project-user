package com.jpa.user1984.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentBookStatus {
    COMPLETE("ORDER_COMPLETE"), CANCEL("ORDER_CANCEL");
    private final String value;
}
