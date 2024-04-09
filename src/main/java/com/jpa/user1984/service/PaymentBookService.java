package com.jpa.user1984.service;

import com.jpa.user1984.dto.PaymentBookForm;
import com.jpa.user1984.domain.PaymentBook;
import com.jpa.user1984.domain.PaymentBookHistory;
import com.jpa.user1984.domain.PaymentBookStatus;
import com.jpa.user1984.repository.BookRepository;
import com.jpa.user1984.repository.MemberRepository;
import com.jpa.user1984.repository.PaymentBookHistoryRepository;
import com.jpa.user1984.repository.PaymentBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PaymentBookService {

    private final PaymentBookRepository paymentBookRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final PaymentBookHistoryRepository paymentBookHistoryRepository;

    // 책 주문 등록
    public void saveOrder(Long userNo, PaymentBookForm paymentBookForm) {

        PaymentBook order = new PaymentBook();
        order.setOrderBookId(paymentBookForm.getOrderBookId());
        order.setMember(memberRepository.findByUserId("")); // 추후 수정 필요
        order.setOrderBookMethod(paymentBookForm.getOrderBookMethod());
        order.setPaymentBookStatus(PaymentBookStatus.COMPLETE);
        paymentBookRepository.save(order);

        // 책 주문 내역 등록
        List<PaymentBookHistory> histories = new ArrayList<>();
        for (String bookList : paymentBookForm.getSelectedBooks()) {
            PaymentBookHistory history = new PaymentBookHistory();
            long bookId = Long.parseLong(bookList);
            // history.setPaymentBook(paymentBookRepository.findById(bookId).orElse(null));
            history.setPaymentBook(paymentBookRepository.findById(paymentBookForm.getOrderBookId()).orElse(null));
            history.setBook(bookRepository.findById(bookId).orElse(null));
            histories.add(history);
            paymentBookHistoryRepository.save(history);
        }


    }
}
