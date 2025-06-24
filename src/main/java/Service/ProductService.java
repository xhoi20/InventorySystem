package Service;

import Entity.Category;
import Entity.Order;
import Entity.Product;
import Entity.User;
import Service.Interfaces.IProductService;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import Repository.ProductRepository;
import Repository.CategoryRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService implements IProductService {
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
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
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