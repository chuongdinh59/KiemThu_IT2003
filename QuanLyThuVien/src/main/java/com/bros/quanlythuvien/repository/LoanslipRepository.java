/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository;

import com.bros.quanlythuvien.entity.LoanSlipEntity;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface LoanslipRepository extends CommonRepository<LoanSlipEntity> {
     public List<LoanSlipEntity> findAll(Integer page);
     public LoanSlipEntity findReaderById(Integer loanslipId);
}
