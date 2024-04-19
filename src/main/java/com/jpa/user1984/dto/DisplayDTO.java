package com.jpa.user1984.dto;

import com.jpa.user1984.domain.Book;
import com.jpa.user1984.domain.Display;
import com.jpa.user1984.domain.Store;
import lombok.Data;

@Data
public class DisplayDTO {
    private Long displayId;

    private String aboutImg01Org;
    private String aboutImg01Stored;
    private String aboutText01;
    private String aboutText02;
    private String aboutText03;
    private String aboutText04;

    private String aboutImg02Org;
    private String aboutImg02Stored;
    private String aboutText05;
    private String aboutText06;

    private Store mainStore01;
    private Store mainStore02;
    private Store mainStore03;
    private Store mainStore04;
    private Store mainStore05;

    private Book mainBook01;
    private Book mainBook02;
    private Book mainBook03;
    private Book mainBook04;
    private Book mainBook05;

    //Entity -> DTO
    public DisplayDTO(Display display){
        this.displayId = display.getDisplayId();
        this.aboutImg01Org = display.getAboutImg01Org();
        this.aboutImg01Stored = display.getAboutImg01Stored();
        this.aboutText01 = display.getAboutText01();
        this.aboutText02 = display.getAboutText02();
        this.aboutText03 = display.getAboutText03();
        this.aboutText04 = display.getAboutText04();

        this.aboutImg02Org = display.getAboutImg02Org();
        this.aboutImg02Stored = display.getAboutImg02Stored();
        this.aboutText05 = display.getAboutText05();
        this.aboutText06 = display.getAboutText06();

        this.mainStore01 = display.getMainStore01();
        this.mainStore02 = display.getMainStore02();
        this.mainStore03 = display.getMainStore03();
        this.mainStore04 = display.getMainStore04();
        this.mainStore05 = display.getMainStore05();

        this.mainBook01 = display.getMainBook01();
        this.mainBook02 = display.getMainBook02();
        this.mainBook03 = display.getMainBook03();
        this.mainBook04 = display.getMainBook04();
        this.mainBook05 = display.getMainBook05();
    }

}
