package com.jpa.user1984.dto;

import com.jpa.user1984.domain.StoreStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreLoginForm {
    private String storeLoginId;
    private String storePassword;
    private StoreStatus storeStatus = StoreStatus.STORE;
}
