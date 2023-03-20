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
@Getter
@Setter
public class BorrowCardEntity {
    @Column(name = "id")
    private Integer id;
    @Column(name = "ReaderID")
    private Integer readerID;
    @Column(name = "IssuedDate")
    private Date issuedDate;
    @Column(name = "ExpiryDate")
    private Date expiredDate;
}
