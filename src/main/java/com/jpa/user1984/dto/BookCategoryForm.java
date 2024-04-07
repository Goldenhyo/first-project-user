package com.jpa.user1984.dto;

import com.jpa.user1984.domain.BookCategory;
import com.jpa.user1984.domain.BookCategoryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCategoryForm {
    private Long bookCategoryId;
    private String bookCategoryName;
    private BookCategoryStatus bookCategoryStatus;
    private LocalDateTime lastModifiedDate;
    private LocalDateTime createDate;

    //DTO -> Entity
    public BookCategory toEntity(){
        BookCategory bookCategory = new BookCategory();
        bookCategory.setBookCategoryId(bookCategoryId);
        bookCategory.setBookCategoryName(bookCategoryName);
        bookCategory.setBookCategoryStatus(BookCategoryStatus.ON);
        return bookCategory;
    }
}
