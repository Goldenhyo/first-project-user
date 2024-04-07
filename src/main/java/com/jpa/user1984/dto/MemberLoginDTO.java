package com.jpa.user1984.dto;

import com.jpa.user1984.domain.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginDTO {
    private String userId;
    private String userPassword;
    private MemberStatus userStatus = MemberStatus.USER;
}
