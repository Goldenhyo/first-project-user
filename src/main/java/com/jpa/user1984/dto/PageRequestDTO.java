package com.jpa.user1984.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO { // 구 Pager
    private int page = 1; // 요청하는 페이지번호
    private int size = 8; // 한페이지에 보여줄 글 개수
    private int pagingSize = 5; // 페이지번호 몇개씩 보여줄지
    private String keyword; // 검색 키워드
    private String searchType; // 검색 유형
    private String dateOrder; // 최신순, 이전순
    private String datePeriod; // 검색기간
    private String startDate; // 검색 시작날
    private String endDate; // 검색 마지막날

    public PageRequestDTO(int page) {
        this.page = page;
    }
}
