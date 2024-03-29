/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.bros.quanlythuvien.model.AccountModel;
import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.service.ReaderService;
import com.bros.quanlythuvien.service.impl.ReaderServiceImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author ADMIN
 */
public class ReaderTestCase {

    private ReaderService readerService = new ReaderServiceImpl();

    @Test
    @DisplayName("Kiểm tra nạp thành công danh sách reader")
    public void testLoadReader() {
        List<ReaderModel> reader = readerService.findAll();
        Assertions.assertNotNull(reader);
    }

    @Test
    @DisplayName("Kiểm tra hàm findById với id hợp lệ")
    public void testFindByIdTrue() {
        ReaderModel expectedResult = new ReaderModel();
        expectedResult.setId(10);
        expectedResult.setFullname("Trần Văn Lượng");

        ReaderModel result = readerService.findById(10);

        Assertions.assertEquals(expectedResult.getId(), result.getId());
        Assertions.assertEquals(expectedResult.getFullname(), result.getFullname());
    }

    @Test
    @DisplayName("Kiểm tra hàm findById với id không có")
    public void testFindByIdFalse() {
        ReaderModel result = readerService.findById(99);

        Assertions.assertNull(result);
    }

//    @Test
//    @DisplayName("Kiểm tra hàm checkReader với thẻ thư viện hết hạn")
//    Hàm test xong phải comment lại vì phía sau sẽ thay đổi data base
//    public void testCheckReaderExpiredCard() {
//        Integer id = 2;
//        int expectedResult = 0;
//        int result = readerService.checkReader(id);
//        Assertions.assertEquals(expectedResult, result);
//    }

//    @Test
//    @DisplayName("Kiểm tra hàm checkReader người dùng không tồn tại hoặc chưa tạo thẻ thư viện")
//    //    Hàm test xong phải comment lại vì phía sau sẽ thay đổi data base
//    public void testCheckReaderNone() {
//        Integer id = 24;
//        int expectedResult = 2;
//        int result = readerService.checkReader(id);
//        Assertions.assertEquals(expectedResult, result);
//    }

    @Test
    @DisplayName("Kiểm tra hàm checkReader người dùng chưa trả sách")
    public void testCheckReaderReturn() {
        Integer id = 10;
        int expectedResult = 3;
        int result = readerService.checkReader(id);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Kiểm tra hàm checkReader hợp lệ")
    public void testCheckReaderPass() {
        Integer id = 6;
        int expectedResult = 1;
        int result = readerService.checkReader(id);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Kiểm tra hàm updateReader hợp lệ")
    public void testUpdateReaderPass() {
        ReaderModel reader = new ReaderModel();
        reader.setId(24);
        reader.setFullname("phu2");
        reader.setGender("Nam");

        Boolean rs = readerService.updateReader(reader);
        Assertions.assertTrue(rs);
    }

    @Test
    @DisplayName("Kiểm tra hàm updateReader với reader không tồn tại")
    public void testUpdateReaderFalse() {
        ReaderModel reader = new ReaderModel();
        reader.setId(99);
        reader.setFullname("phan1");
        reader.setGender("Nữ");

        Boolean rs = readerService.updateReader(reader);
        Assertions.assertFalse(rs);
    }

    @Test
    @DisplayName("Kiểm tra hàm findReaderNotHaveBorrowCard không có thẻ thư viện hoặc thẻ thư viện hết hạn")
    public void testFindReaderNotHaveBorrowCardFalse() {
        ReaderModel reader = new ReaderModel();
        reader.setId(24);
        reader.setFullname("phu");
        
         ReaderModel reader1 = new ReaderModel();
        reader.setId(2);
        reader.setFullname("Đình Chương");

        List<ReaderModel> expected = Arrays.asList(reader,reader1);

        List<ReaderModel> actual = readerService.findReaderNotHaveBorrowCard();

        Assertions.assertEquals(expected.size(), actual.size());

    }
//    @Test
//    @DisplayName("Kiểm tra hàm findReaderNotHaveBorrowCard với tất cả người dùng đều hợp lệ")
//    Sửa data base trước khi test
//    public void testFindReaderNotHaveBorrowCardTrue() {
//
//        List<ReaderModel> expected = new ArrayList<>();
//
//        List<ReaderModel> actual = readerService.findReaderNotHaveBorrowCard();
//
//        Assertions.assertEquals(expected.size(), actual.size());
//
//    }

    @Test
    @DisplayName("Kiểm tra hàm findAccountByRId với readerId hợp lệ")
    public void testFindAccountByRIdTrue() {
        AccountModel expectedResult = new AccountModel();
        expectedResult.setId(2);
        expectedResult.setUserName("cho");
        expectedResult.setFullName("Đình Chương ");
        expectedResult.setEmail("chuong59@gmail.com");
        expectedResult.setType("Customer");

        AccountModel result = readerService.findAccountByRId(2);

        Assertions.assertEquals(expectedResult.getId(), result.getId());
        Assertions.assertEquals(expectedResult.getUserName(), result.getUserName());
        Assertions.assertEquals(expectedResult.getFullName(), result.getFullName());
        Assertions.assertEquals(expectedResult.getEmail(), result.getEmail());
        Assertions.assertEquals(expectedResult.getType(), result.getType());
    }

    @Test
    @DisplayName("Kiểm tra hàm findAccountByRId với id không có")
    public void testFindAccountByRIdFalse() {
        AccountModel result = readerService.findAccountByRId(99);

        Assertions.assertNull(result);
    }

    @Test
    @DisplayName("Kiểm tra hàm login với admin")
    public void testLoginAdmin() {
        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("type", "Admin");

        Map<String, Object> actualResult = new HashMap<>();
        actualResult = readerService.login("chuot", "123");
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Kiểm tra hàm login với employee")
    public void testLoginEmployee() {
        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("type", "Employee");

        Map<String, Object> actualResult = new HashMap<>();
        actualResult = readerService.login("gaucon", "123");
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Kiểm tra hàm login với Customer")
    public void testLoginCustomer() {
        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("type", "Customer");
        expectedResult.put("readerId", 2);

        Map<String, Object> actualResult = new HashMap<>();
        actualResult = readerService.login("cho", "123");
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Kiểm tra hàm login bị lỗi")
    public void testLoginFalse() {
        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("type", "Error");

        Map<String, Object> actualResult = new HashMap<>();
        actualResult = readerService.login("chohoanghon", "123213123");
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Kiểm tra hàm register điền trống field")
    public void testRegisterBlank() {
        int expectedResult = 1;

        int actualResult = readerService.register("phuLib", "123", "", "phuLib@gmail.com");
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Kiểm tra hàm register bị trùng username")
    public void testRegisterDuplicatedUserName() {
        int expectedResult = 2;

        int actualResult = readerService.register("cho", "123", "phu", "phuLib@gmail.com");
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Kiểm tra hàm register bị trùng email")
    public void testRegisterDuplicatedEmail() {
        int expectedResult = 2;

        int actualResult = readerService.register("phuLib", "123", "phu", "a@gmail.com");
        Assertions.assertEquals(expectedResult, actualResult);
    }

//    @Test
//    @DisplayName("Kiểm tra hàm register thành công")
////    Hàm test xong phải comment lại vì nó sẽ tạo tài khoản dưới database
//    public void testRegisterPass() {
//        int expectedResult = 3;
//
//        int actualResult = readerService.register("phuLib2","123","phulib2","phuLib2@gmail.com");
//        Assertions.assertEquals(expectedResult, actualResult);
//    }
    
  
    @Test
    @DisplayName("Kiểm tra hàm updateRoleAccount thành công")
    public void testUpdateRoleAccountPass() {
        Boolean actualResult = readerService.updateRoleAccount("Employee", 13);
        Assertions.assertTrue(actualResult);
    }

    @Test
    @DisplayName("Kiểm tra hàm updateRoleAccount thất bại")
    public void testUpdateRoleAccountFalse() {
        Boolean actualResult = readerService.updateRoleAccount("Employee", 99);
        Assertions.assertFalse(actualResult);
    }

}
