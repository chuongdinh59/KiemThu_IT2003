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
@Table(name = "readers")
@Getter
@Setter
public class ReaderEntity {

    @Column(name = "ReaderID")
    private Integer id;
    @Column(name = "FullName")
    private String fullName;
    @Column(name = "Gender")
    private String gender;
    @Column(name = "DateOfBirth")
    private Date dateOfBirth;
    @Column(name = "ReaderType")
    private String readerType;
}
