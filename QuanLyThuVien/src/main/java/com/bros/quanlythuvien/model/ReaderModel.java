/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.model;

/**
 *
 * @author Dinh Chuong
 */
public class ReaderModel {

    private Integer id;
    private String fullname;
    private String gender;
    private String dateOfBirth;
    private String readerType;
    private String phone;

    public Integer getId() {
        return id;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public String getGender() {
        return gender;
    }

    public String getReaderType() {
        return readerType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setReaderType(String readerType) {
        this.readerType = readerType;
    }

}
