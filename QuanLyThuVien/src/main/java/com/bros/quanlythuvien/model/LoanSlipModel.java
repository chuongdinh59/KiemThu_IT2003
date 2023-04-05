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
public class LoanSlipModel {

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

    public String getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(String borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
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

    public LoanSlipModel(Integer id, Integer customerID, Integer bookID, String bookName, String bookAuthor, String borrowedDate, String expirationDate, Integer quantity, Integer isReturned, Integer isOnline) {
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

    public LoanSlipModel() {
    }
    private Integer id;
    private Integer customerID;
    private Integer bookID;
    private String bookName;
    private String bookAuthor;
    private String borrowedDate;
    private String expirationDate;
    private Integer quantity;
    private Integer isReturned;
    private Integer isOnline;

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }
}
