/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.model;

/**
 *
 * @author Dinh Chuong
 */
public class ReportModel {
    private Integer year;
    private String quarter;
    private Integer quantity;
    
        public void setYear(Integer year) {
        this.year = year;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getYear() {
        return year;
    }

    public String getQuarter() {
        return quarter;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
