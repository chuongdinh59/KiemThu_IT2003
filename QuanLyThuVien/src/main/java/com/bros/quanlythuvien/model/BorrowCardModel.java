/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.model;

import com.bros.quanlythuvien.annotation.Column;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Dinh Chuong
 */
//@Getter
//@Setter
public class BorrowCardModel {

    private Integer id;
    private Integer readerID;
    private String issuedDate;
    private String expiredDate;
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public Integer getReaderID() {
        return readerID;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setReaderID(Integer readerID) {
        this.readerID = readerID;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

}
