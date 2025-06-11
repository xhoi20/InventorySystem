package Controller;

import Entity.Category;
import Entity.Product;
import Repository.CategoryRepository;
import Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }
    @DeleteMapping({"Id"})
    public void deleteCategory(@PathVariable byte id) {
         categoryService.deleteCategory( id);
    }
    @PutMapping
    public Category updateCategory(@PathVariable byte id, @RequestBody Category category) {
        category.setId(id);
        return categoryService.updateCategory(category);
    }
    @PostMapping({"id/product"})
    public Category addProductToCategory(@PathVariable Byte id, @RequestBody List<Product> products) {
        return categoryService.addProductToCategory(id, products);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException3.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException3 ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
class ResourceNotFoundException3 extends RuntimeException {
    public ResourceNotFoundException3(String message) {
        super(message);
    }
}



