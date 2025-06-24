package Service.Interfaces;


import Entity.Category;
import Entity.Product;

import java.util.List;

public interface ICategoryService {
    Iterable<Category> getAllCategories();
    Category addCategory(Category category);
    void deleteCategory(long id);
    Category updateCategory(Category category);
    Category addProductToCategory(long categoryId, List<Product> products);

}
