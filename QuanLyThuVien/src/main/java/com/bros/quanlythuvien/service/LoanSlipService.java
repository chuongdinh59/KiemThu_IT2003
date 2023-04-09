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

    public void loadLoanslipColumn(TableView<LoanSlipModel> returnLoanslipTB);

    public List<LoanSlipModel> loadLoanslipInfo();

    public LoanSlipModel findById(Integer id);

    public void updateBook(LoanSlipModel loanSlip);

    public void checkOnlineLoanSlip();

    public List<LoanSlipModel> findByBId(Integer id);

    public List<LoanSlipModel> findByCId(Integer id);

//    public List<LoanSlipModel> findByCId(Integer id);

    public void updateBookGive(LoanSlipModel loanSlip);

    public void creatLoanSlip(List<BookModel> LSbookList, int LScheckReader, String LSCustomerID, int online);
    
    List<ReportModel> getReportBorrow();
    
    List<ReportModel> getReportReturn();
}
