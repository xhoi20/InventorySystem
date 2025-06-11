package Service;

import Entity.Product;
import Repository.CategoryRepository;
import Entity.Category;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;


    }
    @Transactional
    public Category addCategory(Category category){
        if(category.getName()==null||category.getName().trim().isEmpty()){
            throw new IllegalArgumentException("Category name cannot be null or empty");

        }
        if(category.getId()!=null&& categoryRepository.existsById(category.getId())){
            throw new IllegalArgumentException("Category already exists");

        }
        return categoryRepository.save(category);
    }
    @Transactional
    public void deleteCategory(byte id){
        if(!categoryRepository.existsById(id)){
            throw new IllegalArgumentException("Category does not exist");
        }
        categoryRepository.deleteById(id);

    }
    @Transactional
    public Category updateCategory(Category category){
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
        if(!optionalCategory.isPresent()){
            throw new IllegalArgumentException("Category does not exist");
        }
        if(category.getName()==null||category.getName().trim().isEmpty()){
            throw new IllegalArgumentException("Category name cannot be null or empty");

        }
        Category existingCategory=optionalCategory.get();
        existingCategory.setName(category.getName());
        return categoryRepository.save(existingCategory);


    }
@Transactional
    public Category addProductToCategory(Byte categoryId, List<Product> products){
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(!optionalCategory.isPresent()){
            throw new IllegalArgumentException("Category does not exist");
        }
        if(products==null||products.isEmpty()){
            throw new IllegalArgumentException("Product list cannot be null or empty");

        }
        for(Product product:products){
            if(product.getName()==null||product.getName().trim().isEmpty()){
                throw new IllegalArgumentException("Product name cannot be null or empty");
            }
            if (product.getPrice() == null || product.getPrice().compareTo(java.math.BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Çmimi i produktit duhet te jete jo-negativ");
            }
        }
        Category category=optionalCategory.get();
        for(Product product:products){
            product.setCategory(category);
            category.getProducts().add(product);
        }
        return categoryRepository.save(category);
}

}
