/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.model;

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

@Getter
@Setter
public class BookReservationModel {

    private Integer id;
    private Integer readerID;
    private Integer bookID;
    private Date revervationDate;
    private Date expriedDate;

}
