package com.jpa.user1984.security.domain;

import com.jpa.user1984.domain.Member;
import com.jpa.user1984.dto.MemberDTO;
import com.jpa.user1984.dto.MemberForm;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;

@Getter
@Slf4j
public class CustomMember extends User {

    private MemberDTO member;

    public CustomMember(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomMember(Member member){
        super(member.getUserId(), member.getUserPassword(), Arrays.asList(new SimpleGrantedAuthority(member.getUserStatus().getValue())));
        log.info("******* member.getUserStatus().getValue() = {}", member.getUserStatus().getValue());
//        this.member = new MemberForm(member);
    }

}
