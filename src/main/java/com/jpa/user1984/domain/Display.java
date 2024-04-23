package com.jpa.user1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Display {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long displayId;
    @Column
    private String aboutImg01Org;
    @Column
    private String aboutImg01Stored;
    @Column
    private String aboutText01;
    @Column
    private String aboutText02;
    @Column
    private String aboutText03;
    @Column
    private String aboutText04;

    @Column
    private String aboutImg02Org;
    @Column
    private String aboutImg02Stored;
    @Column
    private String aboutText05;
    @Column
    private String aboutText06;

    @ManyToOne
    @JoinColumn(name = "mainStore01")
    private Store mainStore01;
    @ManyToOne
    @JoinColumn(name = "mainStore02")
    private Store mainStore02;
    @ManyToOne
    @JoinColumn(name = "mainStore03")
    private Store mainStore03;
    @ManyToOne
    @JoinColumn(name = "mainStore04")
    private Store mainStore04;
    @ManyToOne
    @JoinColumn(name = "mainStore05")
    private Store mainStore05;

    @ManyToOne
    @JoinColumn(name = "mainBook01")
    private Book mainBook01;
    @ManyToOne
    @JoinColumn(name = "mainBook02")
    private Book mainBook02;
    @ManyToOne
    @JoinColumn(name = "mainBook03")
    private Book mainBook03;
    @ManyToOne
    @JoinColumn(name = "mainBook04")
    private Book mainBook04;
    @ManyToOne
    @JoinColumn(name = "mainBook05")
    private Book mainBook05;
}
