package com.jpa.user1984.dto;

import com.jpa.user1984.domain.Member;
import com.jpa.user1984.domain.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long userNo;
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userPhoneNum;
    private MemberStatus userStatus;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;


    // Entity -> DTO
    public MemberDTO(Member member){
        this.userNo = member.getUserNo();
        this.userId = member.getUserId();
        this.userPassword = member.getUserPassword();
        this.userName = member.getUserName();
        this.userEmail = member.getUserEmail();
        this.userPhoneNum = member.getUserPhoneNum();
        this.createDate = member.getCreateDate();
        this.lastModifiedDate = member.getLastModifiedDate();
        this.userStatus = member.getUserStatus();
    }
}