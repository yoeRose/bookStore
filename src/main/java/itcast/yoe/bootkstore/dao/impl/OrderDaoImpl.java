package itcast.yoe.bootkstore.dao.impl;

import itcast.yoe.bootkstore.dao.OrderDao;
import itcast.yoe.bootkstore.domain.Order;
import itcast.yoe.bootkstore.domain.OrderItem;
import itcast.yoe.bootkstore.domain.Product;
import itcast.yoe.bootkstore.utils.ManagerThreadLocal;
import itcast.yoe.bootkstore.utils.c3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    //c3p0连接池
    QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());

    @Override
    public void add(Order order) throws SQLException {
        String sql = "insert into orders(id,money,receiverAddress,receiverName,receiverPhone,paystate,ordertime,user_id) " +
                    "values(?,?,?,?,?,?,?,?)";
        List<Object> list = new ArrayList<>();
        list.add(order.getId());
        list.add(order.getMoney());
        list.add(order.getReceiverAddress());
        list.add(order.getReceiverName());
        list.add(order.getReceiverPhone());
        list.add(order.getPayState());
        list.add(order.getOrderTime());
        list.add(order.getUser().getId());

        qr.update(ManagerThreadLocal.getConnection(),sql,list.toArray());
    }

    /**
     * 通过用户id查找订单
     * @param userId
     * @return
     * @throws SQLException
     */
    @Override
    public List<Order> findOrdersByUserId(String userId) throws SQLException {
        String sql = "select * from orders where user_id = ?";
        return qr.query(sql,new BeanListHandler<Order>(Order.class),userId);
    }

    /**
     * 通过orderId查找订单
     * @param orderId
     * @return
     */
    @Override
    public Order findOrderByOrderId(String orderId) throws SQLException {
        String sql = "select * from orders where id = ?";
        Order order = qr.query(sql,new BeanHandler<Order>(Order.class),orderId);

        sql = "select o.*,p.name,p.price from order_item o,products p where o.product_id = p.id and o.order_id = ?";

        List<OrderItem> orderItems = qr.query(sql, new ResultSetHandler<List<OrderItem>>() {
            @Override
            public List<OrderItem> handle(ResultSet resultSet) throws SQLException {
                List<OrderItem> items = new ArrayList<>();
                while (resultSet.next()){
                    OrderItem item = new OrderItem();
                    item.setBuyNum(resultSet.getInt("buyNum"));

                    Product product = new Product();
                    product.setName(resultSet.getString("name"));
                    product.setPrice(resultSet.getDouble("price"));

                    item.setProduct(product);

                    items.add(item);
                }
                return items;
            }
        },orderId);

        order.setItems(orderItems);

        return order;
    }
}
