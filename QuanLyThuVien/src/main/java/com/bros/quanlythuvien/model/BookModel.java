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
public class BookModel {
    private Integer id;
    private String title;
    private String author;
    private String description;
    private String publicationYear;
    private String publicationPlace;
    private Date createAt;
    private Integer categoryID;
    private String location;
}
