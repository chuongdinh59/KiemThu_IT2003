/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.entity;

import com.bros.quanlythuvien.annotation.Column;
import com.bros.quanlythuvien.annotation.Entity;
import com.bros.quanlythuvien.annotation.Table;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "loanslip")
public class LoanSlipEntity {

    public LoanSlipEntity() {
    }

    public LoanSlipEntity(Integer id, Integer customerID, Integer bookID, String bookName, String bookAuthor, Date borrowedDate, Date expirationDate, Integer quantity, Integer isReturned, Integer isOnline) {
        this.id = id;
        this.customerID = customerID;
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.borrowedDate = borrowedDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.isReturned = isReturned;
        this.isOnline = isOnline;
    }

   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(Integer isReturned) {
        this.isReturned = isReturned;
    }
    @Column(name = "id")
    private Integer id;
    @Column(name = "CustomerID")
    private Integer customerID;
    @Column(name = "BookID")
    private Integer bookID;
    @Column(name = "BookName")
    private String bookName;
    @Column(name = "BookAuthor")
    private String bookAuthor;
    @Column(name = "BorrowedDate")
    private Date borrowedDate;
    @Column(name = "ExpirationDate")
    private Date expirationDate;
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name = "isReturned")
    private Integer isReturned;
    @Column(name = "isOnline")
    private Integer isOnline;

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }
}
