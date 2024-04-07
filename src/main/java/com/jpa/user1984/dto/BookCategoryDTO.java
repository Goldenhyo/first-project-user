package com.jpa.user1984.dto;

import com.jpa.user1984.domain.BookCategory;
import com.jpa.user1984.domain.BookCategoryStatus;
import lombok.Data;

import java.time.LocalDateTime;


@Data // 화면에 목록을 전달
public class BookCategoryDTO {
    private Long bookCategoryId;
    private String bookCategoryName;
    private BookCategoryStatus bookCategoryStatus;
    private LocalDateTime lastModifiedDate;
    private LocalDateTime createDate;

    //Entity -> DTO
    public BookCategoryDTO(BookCategory bookCategory){
        this.bookCategoryId = bookCategory.getBookCategoryId();
        this.bookCategoryName = bookCategory.getBookCategoryName();
        this.bookCategoryStatus = bookCategory.getBookCategoryStatus();
        this.lastModifiedDate = bookCategory.getLastModifiedDate();
        this.createDate = bookCategory.getCreateDate();
    }
}
