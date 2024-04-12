package com.jpa.user1984.dto;

import com.jpa.user1984.domain.Book;
import com.jpa.user1984.domain.BookCategory;
import com.jpa.user1984.domain.BookStatus;
import com.jpa.user1984.domain.Store;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookDTO {
    private Long bookId;
    private String isbn;
    private String bookImgOrg;
    private String bookImgStored;
    private String bookFileOrg;
    private String bookFileStored;
    private String bookTitle;
    private String bookWriter;

    private Store store;

    private String bookPub;
    private String bookPaperPrice;
    private String bookEbookPrice;

    private BookCategory bookCategory;

    private String bookIntro;
    private String bookIndex;
    private String bookReview;
    private String bookWriterProfile;
    private BookStatus bookStatus;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    //Entity -> DTO
    public BookDTO(Book book){
        this.bookId = book.getBookId();
        this.isbn = book.getIsbn();

        this.bookImgOrg = book.getBookImgOrg();
        this.bookImgStored = book.getBookImgStored();
        this.bookFileOrg = book.getBookFileOrg();
        this.bookFileStored = book.getBookFileStored();

        this.bookTitle = book.getBookTitle();
        this.bookWriter = book.getBookWriter();

        this.store = book.getStore();

        this.bookPub = book.getBookPub();
        this.bookPaperPrice = book.getBookPaperPrice();
        this.bookEbookPrice = book.getBookEbookPrice();

        this.bookCategory = book.getBookCategory();

        this.bookIntro = book.getBookIntro();
        this.bookIndex = book.getBookIndex();
        this.bookReview = book.getBookReview();
        this.bookWriterProfile = book.getBookWriterProfile();
        this.bookStatus = book.getBookStatus();
        this.createDate = book.getCreateDate();
        this.lastModifiedDate = book.getLastModifiedDate();
    }

}
