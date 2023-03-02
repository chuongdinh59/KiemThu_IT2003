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
@Table(name = "borrowdetails")
@Getter
@Setter
public class BorrowDetailsEntity {

    @Column(name = "BorrowDetailID")
    private Integer id;
    @Column(name = "BorrowCardID")
    private Integer borrowCardID;
    @Column(name = "BookID")
    private Integer bookID;
    @Column(name = "BorrowDate")
    private Date borrowDate;
    @Column(name = "DueDate")
    private Date dueDate;
    @Column(name = "ReturnDate")
    private Date returnDate;

}
