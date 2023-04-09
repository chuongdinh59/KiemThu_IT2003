/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository;

import com.bros.quanlythuvien.entity.LoanSlipEntity;
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.LoanSlipModel;
import com.bros.quanlythuvien.model.ReportModel;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface LoanSlipRepository extends CommonRepository<LoanSlipEntity> {

     List<LoanSlipEntity> findAll(Integer page);

     LoanSlipEntity findReaderById(Integer loanslipId);

     boolean updateBook(LoanSlipModel loanSlip);

     void creatLoanSlip(List<BookModel> LSbookList, int LScheckReader, String LSCustomerID, int online);

     List<LoanSlipEntity> findByCId(Integer id);

     boolean updateBookGive(LoanSlipModel loanSlip);

     void checkOnlineLoanSlip();

     List<LoanSlipEntity> findByBId(Integer id);

     void insertQuantity(Integer quantity, Integer id);

    void deleteQuantity(Integer quantity, Integer id);
    boolean checkQuantity(Integer quantity, Integer id);
     
    List<ReportModel> getReportBorrow();
    
    List<ReportModel> getReportReturn();
}
