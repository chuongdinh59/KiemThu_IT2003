/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository.impl;

import com.bros.quanlythuvien.entity.LoanSlipEntity;
import com.bros.quanlythuvien.model.LoanSlipModel;
import com.bros.quanlythuvien.repository.LoanslipRepository;
import static com.bros.quanlythuvien.utils.ConnectionUtils.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class LoanslipRepositoryImpl extends CommonRepositoryImpl<LoanSlipEntity> implements LoanslipRepository {

    private PreparedStatement statement;

    @Override
    public List<LoanSlipEntity> findAll(Integer page) {
        List<LoanSlipEntity> r = super.findAll();
        return r;
    }

    @Override
    public LoanSlipEntity findReaderById(Integer loanslipId) {
        LoanSlipEntity r = super.findById(loanslipId);
        return r;
    }

    @Override
    public boolean updateBook(LoanSlipModel loanSlip) {
        Connection connect = getConnection();
        if (loanSlip == null) {
            return false;
        }

        try {
            String sql = "UPDATE loanslip SET isReturned = '1' WHERE (id = ?);";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, loanSlip.getId());

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

}
