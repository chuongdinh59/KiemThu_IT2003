/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dinh Chuong
 */
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.LoanSlipModel;
import com.bros.quanlythuvien.service.BookService;
import com.bros.quanlythuvien.service.LoanSlipService;
import com.bros.quanlythuvien.service.impl.BookServiceImpl;
import com.bros.quanlythuvien.service.impl.LoanSlipServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.provider.MethodSource;

public class LoanSlipTestCase {

    private LoanSlipService loanSlipService = new LoanSlipServiceImpl();

//    @DisplayName("Kiểm tra tìm kiếm phiếu mượn bằng đọc giả id")
//    @ParameterizedTest
//    @ValueSource(ints = {2, 1, 3, 11})
//    public void testFindLoanSlipIDByCustomerID(Integer id) {
//        Assertions.assertNotNull(loanSlipService.findByCId(id));
//    }
//
//    @DisplayName("Kiểm tra tìm kiếm phiếu mượn bằng sách id")
//    @ParameterizedTest
//    @ValueSource(ints = {2, 3, 6})
//    public void testFindLoanSlipIDByBookID(Integer id) {
//        Assertions.assertNotNull(loanSlipService.findByBId(id));
//    }
//    
//    static Stream<Map<String, Integer>> getMapSearch() {
//// Do trên giao diện đã fix cứng những field search nên em tự cho dữ liệu phù hợp 
//        return Stream.of(
//                Map.of( "bookid", 2),
//                Map.of("readerid", 1 ),
//                Map.of("readerid", 3, "bookid", 6)
//        );
//    }
//    @ParameterizedTest
//    @DisplayName("Kiểm tra tìm kiếm phiếu mượn bằng readerid và bookid thành công")
//    @MethodSource("getMapSearch")
//    public void testSearchBookByBookIDAndReaderIDSuccess(Map<String, Integer> searchMap) {
//        Integer readerID = searchMap.getOrDefault("readerid", null);
//        Integer bookID = searchMap.getOrDefault("bookid", null);
//        Assertions.assertTrue(loanSlipService.findByBookIDAndReaderID(bookID, readerID).size() > 0);
//    }
//
//    @Test
//    @DisplayName("Kiểm tra tìm kiếm phiếu mượn bằng readerid và bookid thất bại")
//    public void testSearchBookByBookIDAndReaderIDFail() {
//        Assertions.assertFalse(loanSlipService.findByBookIDAndReaderID(999, 999).size() > 0);
//    }
//    @Test
//    @DisplayName("Kiểm tra đưa sách cho người nhận")
//    public void testUpdateBookGive() {
//        LoanSlipModel loanSlip1 = loanSlipService.findByCId(2).get(0);
//        Integer rs1 = loanSlipService.updateBookGive(loanSlip1);
//        // Vì sách này đã trả rồi 
//        Assertions.assertEquals(rs1,0);
//        LoanSlipModel loanSlip2 = loanSlipService.findByCId(3).get(0);
//        Integer rs2 = loanSlipService.updateBookGive(loanSlip2);
//        Assertions.assertEquals(rs2,1);
//    }
//    @Test
//    @DisplayName("Kiểm tra nạp phiếu mượn")
//    public void testUpdateBookGive() {
//        List<LoanSlipModel> books = loanSlipService.loadLoanslipInfo();
//        Assertions.assertTrue(books.size() > 0);
//    }
//    @Test
//    @DisplayName("Kiểm tra sau 48h có bị xóa đơn không ")
//    public void testExpireDay() {
//        Integer rs = loanSlipService.checkOnlineLoanSlip();
//        // Hiện tại không cái nào vi phạm thì nó là 0
//        Assertions.assertEquals(rs, 0);
//    }
//    @Test
//    @DisplayName("Kiểm tra Tạo phiếu mượn")
//    public void testCreateLoanSlip() {
//        
//        //-1 --> Tạo lỗi 
//        // 1 --> Thành công 
//        // 2 --> Thư viện không đủ sách
//        // 3 --> Khách hàng không tồn tại hoặc bạn chưa thêm sách để tạo phiếu mượn
//        BookService bookService = new BookServiceImpl();
//        List<BookModel> listBook = bookService.findAll(null);
//        List<BookModel> orderBook = new ArrayList<>();
//        listBook.get(0).setQuantity(1);
//        orderBook.add(listBook.get(0));
//        listBook.get(1).setQuantity(1);
//        orderBook.add(listBook.get(1));
//        Integer rs1 = loanSlipService.creatLoanSlip(listBook, 1, "2" , 0);
//        // 1 --> Thành công 
//        Assertions.assertEquals(rs1, 1);
//        
//        // 2 --> Thư viện không đủ sách
//        orderBook.get(0).setQuantity(999);
//        Integer rs2 = loanSlipService.creatLoanSlip(listBook, 1, "2" , 0);
//        Assertions.assertEquals(rs2, 2);
//        
//        Integer rs3 = loanSlipService.creatLoanSlip(listBook, 0, "2" , 0);
//        // 3 --> Khách hàng không tồn tại hoặc bạn chưa thêm sách để tạo phiếu mượn
//        Assertions.assertEquals(rs3, 3);
//    }
//    @Test
//    @DisplayName("Kiểm tra cập nhật trạng thái")
//    public void testUpdateStatusBook() {
//
//        // -2 --> trả sách rồi 
//        LoanSlipModel loanSlip1 = loanSlipService.findByCId(2).get(0);
//        Integer rs1 = loanSlipService.updateBook(loanSlip1);
//        Assertions.assertEquals(rs1, -2);
//        // -1 --> sự cố lỗi, khi phiếu mượn không có hoặc exception
//        LoanSlipModel loanSlip2 = null;
//        Integer rs2 = loanSlipService.updateBook(loanSlip2);
//        Assertions.assertEquals(rs2, -1);
//        // 0 --> thành công
//        LoanSlipModel loanSlip3 = loanSlipService.findByBId(7).get(2);
//        Integer rs3 = loanSlipService.updateBook(loanSlip3);
//        Assertions.assertEquals(rs3, 0);
////      // >0 --> thành công + phạt tiền
//        LoanSlipModel loanSlip4 = loanSlipService.findByCId(2).get(1);
//        Integer rs4 = loanSlipService.updateBook(loanSlip4);
//        Assertions.assertTrue(rs4 > 0);
//        
//    }

}
