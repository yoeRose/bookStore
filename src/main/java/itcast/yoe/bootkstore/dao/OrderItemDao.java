package itcast.yoe.bootkstore.dao;

import itcast.yoe.bootkstore.domain.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemDao {
    void addOrderItem(List<OrderItem> orderItems) throws SQLException;
}
