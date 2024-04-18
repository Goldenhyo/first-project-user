package com.jpa.user1984.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BannerStatus {
    ON("STATUS_ON"), OFF("STATUS_OFF");
    private final String value;

    public String getValue(){
        return value;
    }
}
