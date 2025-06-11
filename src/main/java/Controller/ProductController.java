package Controller;

import Entity.Product;
import Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("{ID}")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);

    }

    @DeleteMapping
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

    }

    @PutMapping
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return  productService.updateProduct(product);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException1.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException1 ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
    class ResourceNotFoundException1 extends RuntimeException {
        public ResourceNotFoundException1(String message) {
            super(message);
        }

    }
