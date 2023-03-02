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

@Getter
@Setter
public class BorrowCardModel {
    private Integer id;
    private Integer readerID;
    private Date issuedDate;
    private Date expiredDate;
}
