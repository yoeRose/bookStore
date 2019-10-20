package itcast.yoe.bootkstore.service;

import itcast.yoe.bootkstore.domain.Order;

import java.util.List;

public interface OrderService {
    void createOrder(Order order);
    List<Order> findOrdersByUserId(String userId);
    Order findOrderByOrderId(String orderId);
}
