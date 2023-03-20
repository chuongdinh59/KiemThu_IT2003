/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository;

import com.bros.quanlythuvien.entity.ReaderEntity;
import java.util.List;
import java.util.Map;

/**
 *
 * @author phu nguyen
 */
public interface ReaderRepository extends CommonRepository<ReaderEntity>{
       public List<ReaderEntity> findReaderById(Integer ReaderID, Integer page);
}