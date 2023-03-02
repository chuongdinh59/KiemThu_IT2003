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
@Table(name = "report")
@Setter
@Getter
public class ReportEntity {
    @Column(name = "ReportID")
    private Integer id;
    @Column(name = "report_time")
    private Date report_time;
    @Column(name = "borrowed")
    private Integer borrowed;
    @Column(name = "returned")
    private Integer returned;
    @Column(name = "on_loan")
    private Integer onLoan;
    @Column(name = "overdue")
    private Integer overdue;
    @Column(name = "fine")
    private Double fine;
}
