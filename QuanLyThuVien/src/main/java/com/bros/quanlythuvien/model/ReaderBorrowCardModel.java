/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class ReaderBorrowCardModel {
    
     public ReaderBorrowCardModel() {
       
    }

    public ReaderBorrowCardModel(Integer id, String fullname, Integer readerID, String issuedDate, String expiredDate) {
        this.id = id;
        this.fullname = fullname;
        this.readerID = readerID;
        this.issuedDate = issuedDate;
        this.expiredDate = expiredDate;
    }
    private Integer id;
    private String fullname;
    private Integer readerID;
    private String issuedDate;
    private String expiredDate;

    public Integer getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
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

    public void setFullname(String fullname) {
        this.fullname = fullname;
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
