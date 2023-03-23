/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.entity;

import com.bros.quanlythuvien.annotation.Column;
import com.bros.quanlythuvien.annotation.Entity;
import com.bros.quanlythuvien.annotation.Table;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Dinh Chuong
 */
@Entity
@Table(name = "borrowcards")
//@Getter
//@Setter
public class BorrowCardEntity {

    @Column(name = "id")
    private Integer id;
    @Column(name = "ReaderID")
    private Integer readerID;
    @Column(name = "IssuedDate")
    private Date issuedDate;
    @Column(name = "ExpiryDate")
    private Date expiredDate;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setReaderID(Integer readerID) {
        this.readerID = readerID;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Integer getId() {
        return id;
    }

    public Integer getReaderID() {
        return readerID;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

}
