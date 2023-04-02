/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository.impl;

/**
 *
 * @author Dinh Chuong
 */
import com.bros.quanlythuvien.annotation.Column;
import com.bros.quanlythuvien.annotation.Entity;
import com.bros.quanlythuvien.annotation.Table;
import com.bros.quanlythuvien.mapper.ResultSetMapper;
import com.bros.quanlythuvien.repository.CommonRepository;
import com.bros.quanlythuvien.utils.ConnectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommonRepositoryImpl<T> implements CommonRepository<T> {

    private Class<T> tClass;
    private ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();

    @SuppressWarnings("unchecked")
    public CommonRepositoryImpl() {
        tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public List<T> findAll() {
        String tableName = getTableName();
        if (tableName == null) {
            return null;
        }

        List<T> results = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionUtils.getConnection();
            String sql = "SELECT * FROM " + tableName;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            results = resultSetMapper.mapRow(rs, tClass);

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public T findById(Integer id) {
        String tableName = getTableName();
        if (tableName == null) {
            return null;
        }

        List<T> results = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionUtils.getConnection();
            String sql = "SELECT * FROM " + tableName + "  WHERE id = " + id;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            results = resultSetMapper.mapRow(rs, tClass);

            return results.size() > 0 ? results.get(0) : null;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public T findByRId(Integer id) {
        String tableName = getTableName();
        if (tableName == null) {
            return null;
        }

        List<T> results = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionUtils.getConnection();
            String sql = "SELECT * FROM " + tableName + "  WHERE ReaderID = " + id;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            results = resultSetMapper.mapRow(rs, tClass);

            return results.size() > 0 ? results.get(0) : null;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<T> findByCondition(String sql) {
        List<T> results;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionUtils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            results = resultSetMapper.mapRow(rs, tClass);

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<T> findByCondition(PreparedStatement pstmt) {
        List<T> results;
        ResultSet rs = null;

        try {
            rs = pstmt.executeQuery();
            results = resultSetMapper.mapRow(rs, tClass);

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public void delete(Integer id) {
        String tableName = getTableName();
        if (tableName == null) {
            return;
        }
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionUtils.getConnection();
            String sql = "DELETE FROM " + tableName + "  WHERE id = " + id;
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object object) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConnectionUtils.getConnection();
            StringBuilder sql = createSqlUpdate();
            pstmt = conn.prepareStatement(sql.toString());

            Class<?> zClass = object.getClass();
            Field[] fields = zClass.getDeclaredFields();
            int paramIndex = 1;
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Column.class)) {
                    continue;
                }
                field.setAccessible(true);
                pstmt.setObject(paramIndex++, field.get(object));
            }

            // Scan parent's fields
            Class<?> parentClass = tClass.getSuperclass();
            Field[] parentFields = parentClass.getDeclaredFields();
            Integer id = null;
            while (parentClass != null && parentClass.isAnnotationPresent(Entity.class)) {
                for (Field field : parentFields) {
                    field.setAccessible(true);
                    if (!field.isAnnotationPresent(Column.class)) {
                        continue;
                    }
                    if (!field.getName().equals("id")) {
                        pstmt.setObject(paramIndex++, field.get(object));
                    } else {
                        id = (Integer) field.get(object);
                    }
                }
                parentClass = parentClass.getSuperclass();
            }
            // set ID
            if (id != null) {
                pstmt.setObject(paramIndex, id);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer insert(Object object) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionUtils.getConnection();
            StringBuilder sql = createSqlInsert();
            if (sql == null) {
                return null;
            }
            pstmt = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            Class<?> zClass = object.getClass();
            Field[] fields = zClass.getDeclaredFields();
            int paramIndex = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                pstmt.setObject(paramIndex++, field.get(object));
            }

            // Scan parent's fields
            Class<?> parentClass = tClass.getSuperclass();
            Field[] parentFields = parentClass.getDeclaredFields();
            int paramParentIndex = fields.length + 1;
            while (parentClass != null && parentClass.isAnnotationPresent(Entity.class)) {
                for (Field field : parentFields) {
                    if (!field.isAnnotationPresent(Column.class) || field.getName().equals("id")) {
                        continue;
                    }
                    field.setAccessible(true);
                    pstmt.setObject(paramParentIndex++, field.get(object));
                }
                parentClass = parentClass.getSuperclass();
            }

            int flag = pstmt.executeUpdate();
            if (flag > 0) {
                rs = pstmt.getGeneratedKeys();
                return rs.next() ? rs.getInt(1) : null;
            }

        } catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    private StringBuilder createSqlInsert() {
        String tableName = getTableName();
        if (tableName == null) {
            return null;
        }

        StringBuilder fields = new StringBuilder();
        StringBuilder params = new StringBuilder();

        for (Field field : tClass.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }
            if (fields.length() > 1) {
                fields.append(", ");
                params.append(", ");
            }
            Column column = field.getAnnotation(Column.class);
            fields.append(column.name());
            params.append("?");
        }

        // Scan parent's fields
        Class<?> parentClass = tClass.getSuperclass();
        Field[] parentFields = parentClass.getDeclaredFields();
        while (parentClass != null && parentClass.isAnnotationPresent(Entity.class)) {
            for (Field field : parentFields) {
                if (!field.isAnnotationPresent(Column.class) || field.getName().equals("id")) {
                    continue;
                }
                fields.append(", ");
                params.append(", ");
                Column column = field.getAnnotation(Column.class);
                fields.append(column.name());
                params.append("?");
            }
            parentClass = parentClass.getSuperclass();
        }

        return new StringBuilder(
                "INSERT INTO " + tableName + "(" + fields.toString() + ") VALUES(" + params.toString() + ")");
    }

    private StringBuilder createSqlUpdate() {
        String tableName = getTableName();
        if (tableName == null) {
            return null;
        }

        StringBuilder fieldAndParams = new StringBuilder("");
        for (Field field : tClass.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }
            if (fieldAndParams.length() > 1) {
                fieldAndParams.append(", ");
            }
            Column column = field.getAnnotation(Column.class);
            fieldAndParams.append(column.name()).append(" = ?");
        }

        // Scan parent's fields
        Class<?> parentClass = tClass.getSuperclass();
        Field[] parentFields = parentClass.getDeclaredFields();
        while (parentClass != null && parentClass.isAnnotationPresent(Entity.class)) {
            for (Field field : parentFields) {
                if (!field.isAnnotationPresent(Column.class) || field.getName().equals("id")) {
                    continue;
                }
                Column column = field.getAnnotation(Column.class);
                fieldAndParams.append(", ").append(column.name()).append(" = ?");
            }
            parentClass = parentClass.getSuperclass();
        }
        return new StringBuilder("UPDATE " + tableName + " SET " + fieldAndParams + " WHERE id = ?");
    }

    private String getTableName() {
        if (tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
            Table table = tClass.getAnnotation(Table.class);
            return table.name();
        }
        return null;
    }

}
