/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.bros.quanlythuvien.model.CategoryModel;
import com.bros.quanlythuvien.service.CategoryService;
import com.bros.quanlythuvien.service.impl.CategoryServiceImpl;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author ADMIN
 */
public class CategoryTestCase {

    private CategoryService categoryService = new CategoryServiceImpl();

    @Test
    @DisplayName(value = "Kiểm tra tên category không trùng nhau")
    public void testNameNotDuplicated() throws SQLException {
        List<CategoryModel> cates = categoryService.findAll();
        List<String> catesName = cates.stream().
                map(mapper -> mapper.getValue()).collect(Collectors.toList());
        Set<String> setCatesName = new HashSet<>(catesName);

        Assertions.assertEquals(catesName.size(), setCatesName.size());
    }

    @Test
    @DisplayName(value = "Kiểm tra tên category không rỗng")
    public void testNotNullOrEmptyName() throws SQLException {
        List<CategoryModel> cates = categoryService.findAll();
        long t = cates.stream().filter(c -> !c.getValue().isBlank()).count();
        Assertions.assertTrue(t == cates.size());
    }

    @Test
    @DisplayName("Kiểm tra nạp thành công danh sách category")
    public void testLoadCategory() {
        List<CategoryModel> cate = categoryService.findAll();
        Assertions.assertNotNull(cate);
    }

    @Test
    @DisplayName("Kiểm tra hàm findById với id hợp lệ")
    public void testFindByIdTrue() {
        CategoryModel expectedResult = new CategoryModel(2, "2", "Sách");
        CategoryModel result = categoryService.findById(2);

        Assertions.assertEquals(expectedResult.getCategoryID(), result.getCategoryID());
        Assertions.assertEquals(expectedResult.getCode(), result.getCode());
        Assertions.assertEquals(expectedResult.getValue(), result.getValue());
    }
    
    @Test
    @DisplayName("Kiểm tra hàm findById với id không có")
    public void testFindByIdFalse() {
        CategoryModel result = categoryService.findById(99);

        Assertions.assertNull(result);
      
    }
}
