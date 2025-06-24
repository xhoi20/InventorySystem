package Service.Interfaces;
import Entity.Product;
import Entity.User;

import java.util.Optional;
public interface IProductService {

    Product addProduct(Product product);
    Iterable<Product> getAllProducts();
    void deleteProduct(long id);
    Product updateProduct(Product product);
}
