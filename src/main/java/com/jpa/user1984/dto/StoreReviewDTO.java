package com.jpa.user1984.dto;

import com.jpa.user1984.domain.Member;
import com.jpa.user1984.domain.Store;
import com.jpa.user1984.domain.StoreReview;
import com.jpa.user1984.domain.StoreReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreReviewDTO { // 가져오는

    private Long storeReviewId;
    private Member member;
    private String storeReviewDetail;
    private StoreReviewStatus storeReviewStatus;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private Long storeReviewPosition;
    private Integer step;
    private Store store;


    public StoreReviewDTO(StoreReview storeReview) {
        this.storeReviewId = storeReview.getStoreReviewId();
        this.member = storeReview.getMember();
        this.storeReviewDetail = storeReview.getStoreReviewDetail();
        this.createDate = storeReview.getCreateDate();
        this.lastModifiedDate = storeReview.getLastModifiedDate();
        this.storeReviewStatus = storeReview.getStoreReviewStatus();
        this.storeReviewPosition = storeReview.getStoreReviewPosition();
        this.step = storeReview.getStep();
        this.store = storeReview.getStore();
    }

}
