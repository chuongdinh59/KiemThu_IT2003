/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.model;


import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Dinh Chuong
 */

@Getter
@Setter
public class ReaderModel {

    private Integer id;
    private String fullName;
    private String gender;
    private Date dateOfBirth;
    private String readerType;
}
