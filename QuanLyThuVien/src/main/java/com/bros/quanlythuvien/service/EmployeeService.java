/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service;

import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.BorrowCardModel;
import com.bros.quanlythuvien.model.LoanSlipModel;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author phu nguyen
 */
public interface EmployeeService {

    public void loadBookColumn(TableView<BookModel> tb_SearchBook);

    public void loadReaderColumn(TableView<BorrowCardModel> tbReader);

    public void loadReaderInfo(Integer id, TableView<BorrowCardModel> tbReader);

    public void loadLoanslipColumn(TableView<LoanSlipModel> returnLoanslipTB);

    public List<LoanSlipModel> loadLoanslipInfo();
    public LoanSlipModel findById(Integer id);
}
