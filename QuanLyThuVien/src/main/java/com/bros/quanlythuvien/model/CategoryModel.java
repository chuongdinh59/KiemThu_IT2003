/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Dinh Chuong
 */
//@Getter
//@Setter
public class CategoryModel {

    private Integer categoryID;
    private String code;
    private String value;

    public CategoryModel() {
    }

    public CategoryModel(Integer categoryID, String code, String value) {
        this.categoryID = categoryID;
        this.code = code;
        this.value = value;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
