package com.jpa.user1984.repository;

import com.jpa.user1984.domain.PaymentBookHistory;
import com.jpa.user1984.dto.PageRequestDTO;

import java.util.List;

public interface PaymentBookHistoryCustomRepository {
    // storeId로 주문 목록 조회 판매자 ver
    List<PaymentBookHistory> findListByStoreId(Long storeId, PageRequestDTO pageRequestDTO);

    Long countHistoryListByStoreId(Long storeId, PageRequestDTO pageRequestDTO);

    String method(PageRequestDTO pageRequestDTO);
}
