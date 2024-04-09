package com.jpa.user1984.dto;

import com.jpa.user1984.domain.PaymentBookHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentBookHistoryDTO {
    private Long orderBookHistoryId;
    private Long isbn;
    private Long orderBookId;

    // Entity -> DTO
    public PaymentBookHistoryDTO(PaymentBookHistory paymentBookHistory) {
        this.orderBookHistoryId = paymentBookHistory.getOrderBookHistoryId();
        this.isbn = paymentBookHistory.getBook().getBookId();
        this.orderBookId = paymentBookHistory.getPaymentBook().getOrderBookId();
    }
}
