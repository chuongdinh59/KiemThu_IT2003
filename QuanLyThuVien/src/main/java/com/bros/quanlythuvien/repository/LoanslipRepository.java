/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository;

import com.bros.quanlythuvien.entity.LoanSlipEntity;
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.LoanSlipModel;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface LoanslipRepository extends CommonRepository<LoanSlipEntity> {

    public List<LoanSlipEntity> findAll(Integer page);

    public LoanSlipEntity findReaderById(Integer loanslipId);

    public boolean updateBook(LoanSlipModel loanSlip);

    public void creatLoanSlip(List<BookModel> LSbookList, int LScheckReader, String LSCustomerID, int online);

    public List<LoanSlipEntity> findByCId(Integer id);

    public boolean updateBookGive(LoanSlipModel loanSlip);
    public void checkOnlineLoanSlip();
    public List<LoanSlipEntity> findByBId(Integer id);
}
