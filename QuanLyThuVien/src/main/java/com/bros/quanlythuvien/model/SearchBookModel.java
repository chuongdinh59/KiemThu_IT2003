/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class SearchBookModel {

    public SearchBookModel(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public SearchBookModel(Integer id, String title, String author, String publicationYear, String cate, Integer quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.cate = cate;
        this.quantity = quantity;
    }

    public SearchBookModel() {
    }

    public SearchBookModel(Integer id, String title, String author, String description, String publicationYear, String publicationPlace, String cate, String location) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.publicationYear = publicationYear;
        this.publicationPlace = publicationPlace;
        this.cate = cate;
        this.location = location;
    }
    
     private Integer id;
    private String title;
    private String author;
    private String description;
    private String publicationYear;
    private String publicationPlace;
    private String cate;
    private String location;
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublicationPlace() {
        return publicationPlace;
    }

    public void setPublicationPlace(String publicationPlace) {
        this.publicationPlace = publicationPlace;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

   
}
