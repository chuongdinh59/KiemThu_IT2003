/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dinh Chuong
 */
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.CategoryModel;
import com.bros.quanlythuvien.service.BookService;
import com.bros.quanlythuvien.service.CategoryService;
import com.bros.quanlythuvien.service.impl.BookServiceImpl;
import com.bros.quanlythuvien.service.impl.CategoryServiceImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.provider.MethodSource;

public class BookTestCase {

    private BookService bookService = new BookServiceImpl();

//    @Test
//    @DisplayName("Kiểm tra nạp thành công danh sách sách")
//    public void testLoadListBook() {
//        List<BookModel> books = bookService.findAll(null);
//        Assertions.assertNotNull(books);
//    }
//
//    @Test
//    @DisplayName("Kiểm tra thêm sách")
//    public void testInsertBook() {
//        BookModel book = new BookModel();
//        CategoryService categoryService = new CategoryServiceImpl();
//        List<CategoryModel> categories = categoryService.findAll(); // trong db có dữ liệu rồi 
//        book.setAuthor("Tác giả A");
//        book.setCategoryID(1);
//        book.setPublicationYear(2012);
//        book.setPublicationPlace("Hà Nội");
//        book.setTitle("Sách của tác giả A");
//        book.setQuantity(212);
//        book.setLocation("Dãy A");
//        book.setDescription("Sách của ngày A");
//
//        BookModel rs = bookService.insertBook(book);
//        // Lúc này rs đã tồn tại dưới db
//        BookModel target = bookService.findById(rs.getId());
//        Assertions.assertEquals(rs.getId(), target.getId());
//    }
//
//    @Test
//    @DisplayName("Kiểm tra xóa sách")
//    public void testDeleteBook() {
//        BookModel book = bookService.findAll(null).get(0);
//        if (book != null) {
//            Boolean rs = bookService.deleteBook(book.getId());
//            if (rs) {
//                BookModel b = bookService.findById(book.getId());
//                Assertions.assertNull(b);
//            }
//        }
//    }
//
//    @Test
//    @DisplayName("Kiểm update sách sách")
//    public void testUpdateBook() {
//        final String mockTest = "Sách này đã qua chỉnh sửa";
//        final Integer quantityTest = 120;
//        BookModel book = bookService.findAll(null).get(0);
//        book.setTitle(mockTest);
//        book.setQuantity(120);
//        Boolean rs = bookService.updateBook(book);
//        BookModel book_db = bookService.findById(book.getId());
//        Assertions.assertTrue(rs);
//        Assertions.assertEquals(mockTest, book_db.getTitle());
//        Assertions.assertEquals(quantityTest, quantityTest);
//    }
//    
    static Stream<Map<String, Object>> getMapSearch() {
// Do trên giao diện đã fix cứng những field search nên em tự cho dữ liệu phù hợp 
        return Stream.of(
                Map.of("booktitle", "A"),
                Map.of("publicationyear", 2021),
                Map.of("author", "A"),
                Map.of("categoryid", 1)
        );
    }

    @ParameterizedTest
    @DisplayName("Kiểm tra số không phải là số nguyên tố")
    @MethodSource("getMapSearch")
    public void testSearchBook(Map<String, Object>  searchMap) {
        Assertions.assertTrue(bookService.findBooks(searchMap, null).size() > 0);
    }

}
