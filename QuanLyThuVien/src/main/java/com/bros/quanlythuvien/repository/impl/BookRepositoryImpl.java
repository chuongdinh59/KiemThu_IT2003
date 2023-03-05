/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository.impl;

import com.bros.quanlythuvien.constant.QueryConstant;
import com.bros.quanlythuvien.entity.BookEntity;
import com.bros.quanlythuvien.repository.BookRepository;
import com.bros.quanlythuvien.utils.QueryBuilderUtils;
import com.bros.quanlythuvien.utils.ValidateUtils;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dinh Chuong
 */
// Tra cuwsu
public class BookRepositoryImpl extends CommonRepositoryImpl<BookEntity> implements BookRepository {

    @Override
    public List<BookEntity> findBooks(Map<String, Object> searchMap, Integer page) {

        if (ValidateUtils.isEmptyMap(searchMap)) {
            return super.findAll();
        }

        /*
            Phân tích search map
        {
            tên sách,
            tên tác giả,
            năm xuất bản
            danh mục.
        }
         */
        StringBuilder query = new StringBuilder("SELECT * FROM book");

        return null;
    }

    private String buildWhereStatementSearchBook(Map<String, Object> searchMap) {
        StringBuilder whereStatement = new StringBuilder("WHERE " + QueryConstant.WHERE_ONE_EQUALS_ONE);
        for (Object key : searchMap.keySet()) {
            System.out.println("Key : " + key.toString() + " Value : " + searchMap.get(key));

            Object value = searchMap.get(key);

            if (value instanceof Integer) {
                String statementChild = QueryBuilderUtils.withOperator(key.toString().toLowerCase(), value, QueryConstant.EQUAL_OPERATOR);
                whereStatement.append(statementChild);
            } else if (value instanceof String) {
                String statementChild = QueryBuilderUtils.withLike(key.toString().toLowerCase(), (String) value);
                whereStatement.append(statementChild);
            }
        }
        return null;

    }

}
