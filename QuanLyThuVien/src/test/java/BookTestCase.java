/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dinh Chuong
 */
import com.bros.quanlythuvien.entity.BookEntity;
import com.bros.quanlythuvien.model.BookModel;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
public class BookTestCase {
    
    
    @Test
    public void  findBooks(Map<String, Object> searchMap, Integer page){
        
    }
    @Test
    public void  findById(Integer id){
        
    }
    @Test
    public void  findAll( Integer page){
//        // given
//        List<BookEntity> bookEntities = new ArrayList<>();
//        bookEntities.add(new BookEntity(1L, "Title 1", "Author 1", 1L));
//        bookEntities.add(new BookEntity(2L, "Title 2", "Author 2", 2L));
//
//        when(bookRepository.findAll(null)).thenReturn(bookEntities);
//
//        CategoryEntity categoryEntity1 = new CategoryEntity(1L, "Category 1");
//        CategoryEntity categoryEntity2 = new CategoryEntity(2L, "Category 2");
//
//        when(categoryRepository.findById(1L)).thenReturn(categoryEntity1);
//        when(categoryRepository.findById(2L)).thenReturn(categoryEntity2);
//
//        BookModel bookModel1 = new BookModel(1L, "Title 1", "Author 1", "Category 1");
//        BookModel bookModel2 = new BookModel(2L, "Title 2", "Author 2", "Category 2");
//
//        when(bookConverter.entityToModel(categoryEntity1, bookEntities.get(0), BookModel.class)).thenReturn(bookModel1);
//        when(bookConverter.entityToModel(categoryEntity2, bookEntities.get(1), BookModel.class)).thenReturn(bookModel2);
//
//        // when
//        List<BookModel> resultsBookModel = bookService.findAll(null);
//
//        // then
//        assertEquals(2, resultsBookModel.size());
//        assertEquals(bookModel1, resultsBookModel.get(0));
//        assertEquals(bookModel2, resultsBookModel.get(1));
    }

    @Test
    public void  getSearchMap(String strTitle, String strAuthor, Integer strCate, String strPublish){
        
    }
    @Test
    public void updateBook(BookModel book){
        
    }
    @Test
    public void  insertBook(BookModel book){
        
    }
    @Test
    public void  deleteBook(Integer id){
        
    }
    @Test
    public void  saveImage(Integer id, String url){
        
    }
    @Test
    public void  getImageById(Integer id){
        
    }
}
