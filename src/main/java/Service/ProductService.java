package Service;
import Entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import Repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;
@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product addProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Product price cannot be empty");
        }
        if (product.getCategory() == null || product.getCategory().getId() == null) {
            throw new IllegalArgumentException("Kategoria është e detyrueshme dhe duhet të ketë një ID");
        }
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("produkti me ID" + id + "nuk gjendet");
        }
        productRepository.deleteById(id);


    }

    @Transactional
    public Product updateProduct(Product product) {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if (!optionalProduct.isPresent()) {
            throw new IllegalArgumentException("produkti me ID" + product.getId() + "nuk gjendet");

        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Çmimi duhet të jetë një vlerë jo-negative");
        }
        if (product.getCategory() == null || product.getCategory().getId() == null) {
            throw new IllegalArgumentException("Kategoria është e detyrueshme dhe duhet të ketë një ID");
        }
        if (product.getCategory() == null || product.getCategory().getId() == null) {
            throw new IllegalArgumentException("Kategoria është e detyrueshme dhe duhet të ketë një ID");
        }
        Product existingProduct = optionalProduct.get();
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        return productRepository.save(existingProduct);
    }
}