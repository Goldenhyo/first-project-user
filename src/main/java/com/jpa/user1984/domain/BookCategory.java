package com.jpa.user1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString
public class BookCategory extends TimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookCategoryId;
    @Column(nullable = false)
    private String bookCategoryName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookCategoryStatus BookCategoryStatus;
}
