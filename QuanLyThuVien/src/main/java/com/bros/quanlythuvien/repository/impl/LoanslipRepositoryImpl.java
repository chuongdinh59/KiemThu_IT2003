/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository.impl;

import com.bros.quanlythuvien.entity.LoanSlipEntity;
import com.bros.quanlythuvien.repository.LoanslipRepository;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class LoanslipRepositoryImpl extends CommonRepositoryImpl<LoanSlipEntity> implements LoanslipRepository {

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
    
}
