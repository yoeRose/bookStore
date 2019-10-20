package itcast.yoe.bootkstore.service.impl;

import itcast.yoe.bootkstore.dao.OrderDao;
import itcast.yoe.bootkstore.dao.OrderItemDao;
import itcast.yoe.bootkstore.dao.ProductDao;
import itcast.yoe.bootkstore.dao.impl.OrderDaoImpl;
import itcast.yoe.bootkstore.dao.impl.OrderItmeDaoImpl;
import itcast.yoe.bootkstore.dao.impl.ProductDaoImpl;
import itcast.yoe.bootkstore.domain.Order;
import itcast.yoe.bootkstore.domain.OrderItem;
import itcast.yoe.bootkstore.service.OrderService;
import itcast.yoe.bootkstore.utils.ManagerThreadLocal;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItmeDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();


    @Override
    public void createOrder(Order order) {

        //开启事务
        ManagerThreadLocal.beginTransaction();

        //插入订单表
        try {
            orderDao.add(order);

            for (OrderItem or : order.getItems()){
                productDao.updateProductNum(or.getProduct().getId(),or.getBuyNum());
            }

            orderItemDao.addOrderItem(order.getItems());

            ManagerThreadLocal.commitTransaction();

        } catch (SQLException e) {
            e.printStackTrace();
            ManagerThreadLocal.rollback();
        }

    }

    @Override
    public List<Order> findOrdersByUserId(String userId) {
        try {
            return orderDao.findOrdersByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order findOrderByOrderId(String orderId) {
        try {
            return orderDao.findOrderByOrderId(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
