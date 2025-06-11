package Service;
import Entity.Order;
import Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order addOrder(Order order) {
        if (order.getDate() == null) {
            throw new IllegalArgumentException("Order Date can not be null");
        }
        if (order.getUser() == null ) {
            throw new IllegalArgumentException("User id can not be null");
        }
        if(order.getProducts() == null) {
            throw new IllegalArgumentException("Products can not be null");
        }

return orderRepository.save(order);
    }
    @Transactional
    public void deleteOrder(long id) {
        if(!orderRepository.existsById(id)){
            throw new IllegalArgumentException("Order does not exist");
        }
        orderRepository.deleteById(id);

    }
    @Transactional
    public Order updateOrder(Order order) {
        Optional<Order>optionalOrder=orderRepository.findById(order.getId());
        if(!optionalOrder.isPresent()) {
            throw new IllegalArgumentException("Order does not exist");

        }
        if (order.getDate() == null) {
            throw new IllegalArgumentException("Order Date can not be null");
        }
        if (order.getUser() == null ) {
            throw new IllegalArgumentException("User id can not be null");
        }
        if(order.getProducts() == null) {
            throw new IllegalArgumentException("Products can not be null");
        }
Order existingOrder=optionalOrder.get();
        existingOrder.setDate(order.getDate());
        existingOrder.setUser(order.getUser());
        existingOrder.setProducts(order.getProducts());
        return orderRepository.save(existingOrder);
    }

}