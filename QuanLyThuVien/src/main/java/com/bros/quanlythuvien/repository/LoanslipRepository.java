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

    public List<LoanSlipEntity> findAll(Integer page);

    public LoanSlipEntity findReaderById(Integer loanslipId);

    public boolean updateBook(LoanSlipModel loanSlip);

    public Boolean creatLoanSlip(List<BookModel> LSbookList, int LScheckReader, String LSCustomerID, int online);

    public List<LoanSlipEntity> findByCId(Integer id);

    public boolean updateBookGive(LoanSlipModel loanSlip);

    // Check 24h
    public int checkOnlineLoanSlip();

    public List<LoanSlipEntity> findByBId(Integer id);

    public void insertQuantity(Integer quantity, Integer id);

    public void deleteQuantity(Integer quantity, Integer id);

    public Boolean checkQuantity(Integer quantity, Integer id);

    public List<ReportModel> getReportBorrow();

    public List<ReportModel> getReportReturn();
    
    public List<LoanSlipEntity> findByBookIDAndReaderID(Integer bookID, Integer readerID);
}
