package com.jpa.user1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Inquiry extends TimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userNo")
    private Member member;
    @Column(nullable = false)
    private String inquiryTitle;
    @Column(nullable = false)
    private String inquiryDetail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId")
    private Store store;
    @OneToOne(mappedBy = "inquiry")
    private Answer answer;
}
