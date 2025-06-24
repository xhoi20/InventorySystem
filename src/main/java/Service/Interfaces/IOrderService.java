package Service.Interfaces;

import Entity.Order;


public interface IOrderService {

    Order addOrder(Order order);
    Iterable<Order> getAllOrders();
    void deleteOrder(long id);
    Order updateOrder(Order order);
}
