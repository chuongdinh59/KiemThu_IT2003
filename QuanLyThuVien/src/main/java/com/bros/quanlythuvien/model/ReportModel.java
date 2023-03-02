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

@Setter
@Getter
public class ReportModel {
    private Integer id;
    private Date report_time;
    private Integer borrowed;
    private Integer returned;
    private Integer onLoan;
    private Integer overdue;
    private Double fine;
}
