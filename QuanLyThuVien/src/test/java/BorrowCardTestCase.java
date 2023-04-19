/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.bros.quanlythuvien.model.BorrowCardModel;
import com.bros.quanlythuvien.service.BorrowCardService;
import com.bros.quanlythuvien.service.impl.BorrowCardServiceImpl;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author ADMIN
 */
public class BorrowCardTestCase {

    private BorrowCardService borrowCardService = new BorrowCardServiceImpl();

    @Test
    @DisplayName("Kiểm tra nạp thành công danh sách borrowCard")
    public void testLoadBorrowCard() {
        List<BorrowCardModel> borrowCard = borrowCardService.findAll();
        Assertions.assertNotNull(borrowCard);
    }

    @Test
    @DisplayName("Kiểm tra hàm findBorrowCardByRID khi có readerId trong borrowCard")
    public void testFindBorrowCardByRIDTrue() {
        BorrowCardModel expectedResult = new BorrowCardModel();
        expectedResult.setId(3);
        expectedResult.setReaderID(3);
        BorrowCardModel result = borrowCardService.findBorrowCardByRID(3);

        Assertions.assertEquals(expectedResult.getId(), result.getId());
        Assertions.assertEquals(expectedResult.getReaderID(), result.getReaderID());
    }

    @Test
    @DisplayName("Kiểm tra hàm findBorrowCardByRID khi không có readerId trong borrowCard")
    public void testFindBorrowCardByRIDFalse() {
        BorrowCardModel result = borrowCardService.findBorrowCardByRID(99);

        Assertions.assertNull(result);
    }

    @Test
    @DisplayName("Kiểm tra hàm createBorrowCard False khi tạo cho reader không tồn tại")
    public void testCreateBorrowCardFalse() {
        Boolean result = borrowCardService.createBorrowCard(99);

        Assertions.assertFalse(result);
    }

//    @Test
//    @DisplayName("Kiểm tra hàm createBorrowCard True khi tạo cho reader có tồn tại")
////    Hàm test cẩn thận sinh ra borrowCard trong data base
//    public void testCreateBorrowCardTrue() {
//        Boolean result = borrowCardService.createBorrowCard(24);
//
//        Assertions.assertTrue(result);
//    }

}
