package com.jpa.user1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Entity
@Getter @Setter
public class PaymentBookHistory extends TimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_book_history_id")
    private Long orderBookHistoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_book_no")
    private PaymentBook paymentBook;

    public void setPaymentBook(PaymentBook paymentBook){
        this.paymentBook = paymentBook;
        this.paymentBook.getOrderBookHistories().add(this);
    }
}
