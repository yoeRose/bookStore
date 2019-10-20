package itcast.yoe.bootkstore.dao.impl;

import itcast.yoe.bootkstore.dao.OrderItemDao;
import itcast.yoe.bootkstore.domain.OrderItem;
import itcast.yoe.bootkstore.utils.ManagerThreadLocal;
import itcast.yoe.bootkstore.utils.c3p0Utils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItmeDaoImpl  implements OrderItemDao {
    //c3p0连接池
    private QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());

    @Override
    public void addOrderItem(List<OrderItem> orderItems) throws SQLException {
        String sql = "insert into order_item(order_id,product_id,buynum) values(?,?,?)";
        Object[][] params = new Object[orderItems.size()][];
        for(int i = 0;i<orderItems.size();i++){
            OrderItem item = orderItems.get(i);
            params[i] = new Object[]{item.getOrder().getId(),item.getProduct().getId(),item.getBuyNum()};
        }
        qr.batch(ManagerThreadLocal.getConnection(),sql,params);

    }
//    @Override
//    public void addOrderItem(List<OrderItem> orderItems) throws SQLException {
//        String sql = "insert into order_item(order_id,product_id,buynum) values(?,?,?)";
//        for (OrderItem item : orderItems){
//            qr.update(ManagerThreadLocal.getConnection(),sql,item.getOrder().getId(),item.getProduct().getId(),item.getBuyNum());
//        }
//    }


}
