package Controller;

import Entity.Category;
import Entity.Order;
import Service.Interfaces.IOrderService;
import Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderController {
    private IOrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping
    public Iterable<Order> getAllOrder() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public Order addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }
    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable long id) {

        orderService.deleteOrder(id);
    }
    @PutMapping("{id}")
    public Order updateOrder(@PathVariable long id, @RequestBody Order order) {
        order.setId(id);
        return orderService.updateOrder(order);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException2.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException2 ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    }
    class ResourceNotFoundException2 extends RuntimeException {
        public ResourceNotFoundException2(String message) {
            super(message);
        }

    }


