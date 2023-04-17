/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service;

import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.LoanSlipModel;
import com.bros.quanlythuvien.model.ReportModel;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author phu nguyen
 */
public interface LoanSlipService {

    public List<LoanSlipModel> loadLoanslipInfo();

    public LoanSlipModel findById(Integer id);

    public Boolean updateBook(LoanSlipModel loanSlip);

    public void checkOnlineLoanSlip();

    public List<LoanSlipModel> findByBId(Integer id);

    public List<LoanSlipModel> findByCId(Integer id);

    public Boolean updateBookGive(LoanSlipModel loanSlip);

    public void creatLoanSlip(List<BookModel> LSbookList, int LScheckReader, String LSCustomerID, int online);

    public List<ReportModel> getReportBorrow();

    public List<ReportModel> getReportReturn();
    
    public List<LoanSlipModel> findByBookIDAndReaderID(Integer bookID, Integer readerID);
}
