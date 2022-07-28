package com.example.sushi.entity.admin;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Information {
    @Id
    private String adminId;

    private String password;
    private String location;
    private String open;
    private String close;
    private String instagram;
    private String phone;
    private String title1;
    @Column(length = 1000)
    private String content1;
    private String title2;
    @Column(length = 1000)
    private String content2;
    private String title3;
    @Column(length = 1000)
    private String content3;
    private String notice;

    public void changePassword(String password) {
        this.password = password;
    }
    public void changeLocation(String location) {
        this.location = location;
    }
    public void changeOpen(String open) {
        this.open = open;
    }
    public void changeClose(String close) {
        this.close = close;
    }
    public void changeInstagram(String instagram) {
        this.instagram = instagram;
    }
    public void changeCall(String phone) {
        this.phone = phone;
    }
    public void changeTitle1(String title1) {
        this.title1 = title1;
    }
    public void changeTitle2(String title2) {
        this.title2 = title2;
    }
    public void changeTitle3(String title3) {
        this.title3 = title3;
    }
    public void changeContent1(String content1) {
        this.content1 = content1;
    }
    public void changeContent2(String content2) {
        this.content2 = content2;
    }
    public void changeContent3(String content3) {
        this.content3 = content3;
    }
    public void changeNotice(String notice) {
        this.notice = notice;
    }
}
