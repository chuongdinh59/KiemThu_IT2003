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
@Table(name = "books")
@Getter
@Setter
public class BookEntity {

    @Column(name = "BookID")
    private Integer id;
    @Column(name = "BookTitle")
    private String title;
    @Column(name = "Author")
    private String author;
    @Column(name = "Description")
    private String description;
    @Column(name = "PublicationYear")
    private String publicationYear;
    @Column(name = "PublicationPlace")
    private String publicationPlace;
    @Column(name = "CreateAt")
    private Date createAt;
    @Column(name = "CategoryID")
    private Integer categoryID;
    @Column(name = "Location")
    private String location;
}
