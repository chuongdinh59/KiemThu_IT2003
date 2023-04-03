/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository.impl;

import com.bros.quanlythuvien.constant.QueryConstant;
import com.bros.quanlythuvien.entity.BookEntity;
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.repository.BookRepository;
import static com.bros.quanlythuvien.utils.ConnectionUtils.getConnection;
import com.bros.quanlythuvien.utils.QueryBuilderUtils;
import com.bros.quanlythuvien.utils.ValidateUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Alert;

/**
 *
 * @author Dinh Chuong
 */
// Tra cuwsu
public class BookRepositoryImpl extends CommonRepositoryImpl<BookEntity> implements BookRepository {

    @Override
    public List<BookEntity> findAll(Integer page) {
        List<BookEntity> b = super.findAll();
        return b;
    }

    private PreparedStatement statement;
    Connection connect = getConnection();

    @Override
    public boolean updateBook(BookModel book) {

        if (book == null) {
            return false;
        }

        try {
            String sql = "UPDATE books SET BookTitle = ?, Author = ?, Description = ?, PublicationYear = ?, PublicationPlace = ?, Location = ?, CategoryID = ?,Quantity = ? WHERE id = ?";
            statement = connect.prepareStatement(sql);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getDescription());
            statement.setInt(4, book.getPublicationYear());
            statement.setString(5, book.getPublicationPlace());
            statement.setString(6, book.getLocation());
            statement.setInt(7, book.getCategoryID());
            statement.setInt(8, book.getQuantity());
            statement.setInt(9, book.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean insertBook(BookModel book) {
//        INSERT INTO `librarymanagement`.`books` (`BookTitle`, `Author`, `Description`, `PublicationYear`, `PublicationPlace`, `Location`, `CategoryID`) VALUES ('c', 'c', 'c', '2132', 'c', 'c', '2');

        if (book == null) {
            return false;
        }

        try {
            String sql = "INSERT INTO books (BookTitle, Author, Description, PublicationYear, PublicationPlace, Location, CategoryID,Quantity) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            statement = connect.prepareStatement(sql);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getDescription());
            statement.setInt(4, book.getPublicationYear());
            statement.setString(5, book.getPublicationPlace());
            statement.setString(6, book.getLocation());
            statement.setInt(7, book.getCategoryID());
            statement.setInt(8, book.getQuantity());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
    @Override
     public boolean deleteBook(Integer id) {

        if (id == null) {
            return false;
        }

        try {
            String sql = "DELETE FROM books WHERE (id = ?);";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

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
        StringBuilder query = new StringBuilder("SELECT * FROM books ");
        query.append(this.buildWhereStatementSearchBook(searchMap));
        System.out.print(query.toString());
        return super.findByCondition(query.toString());
    }

    public String buildWhereStatementSearchBook(Map<String, Object> searchMap) {
        StringBuilder whereStatement = new StringBuilder(QueryConstant.WHERE_ONE_EQUALS_ONE);
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
        System.out.print(whereStatement.toString() + "\n");
        return whereStatement.toString();

    }

//    public static void main(String[] args) {
//        BookRepositoryImpl bookRepository = new BookRepositoryImpl();
//        Map<String, Object> g = new HashMap<>();
//        g.put("author", "A");
////        g.put("booktitle", "A");
////        g.put("PublicationYear", 2020);
//        System.out.println(bookRepository.buildWhereStatementSearchBook(g).toString());
//        System.out.println(bookRepository.findBooks(g, null).get(0).getTitle());
//    }
}
