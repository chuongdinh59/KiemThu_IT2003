/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository;

/**
 *
 * @author Dinh Chuong
 */
import java.util.List;

public interface CommonRepository<T> {
	List<T> findAll();

	T findById(Integer id);
        T findByRId(Integer id);

	List<T> findByCondition(String sql);

	Integer insert(Object object);

	void update(Object object);

	void delete(Integer id);
}
