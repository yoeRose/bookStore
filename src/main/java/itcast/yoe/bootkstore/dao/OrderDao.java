package itcast.yoe.bootkstore.dao;

import itcast.yoe.bootkstore.domain.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    void add(Order order) throws SQLException;

    List<Order> findOrdersByUserId(String userId) throws SQLException;

    Order findOrderByOrderId(String orderId) throws SQLException;
}
