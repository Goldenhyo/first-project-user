package com.jpa.user1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BookReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long bookReviewId;
    @Column(nullable = false, unique = true, length = 50)
    private String userId;
    @Column(nullable = false, unique = true, length = 100)
    private String bookReview;
    @Column(nullable = false, length = 200)
    private Long bookReviewPosition;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookStatus bookStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId")
    private Book book;
}
