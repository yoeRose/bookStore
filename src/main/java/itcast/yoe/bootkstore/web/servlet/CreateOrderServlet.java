package itcast.yoe.bootkstore.web.servlet;

import itcast.yoe.bootkstore.domain.Order;
import itcast.yoe.bootkstore.domain.OrderItem;
import itcast.yoe.bootkstore.domain.Product;
import itcast.yoe.bootkstore.domain.User;
import itcast.yoe.bootkstore.service.OrderService;
import itcast.yoe.bootkstore.service.impl.OrderServiceImpl;
import itcast.yoe.bootkstore.utils.UuidUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/createOrder")
public class CreateOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double totalPrice = 0;

        Order order = new Order();
        //获取用户
        User user = (User)req.getSession().getAttribute("user");
        //获取购物车session
        Map<Product,Integer> cart = (Map<Product, Integer>)req.getSession().getAttribute("cart");
        if(cart == null || cart.size() == 0){
            resp.getWriter().write("购物车没有商品");
            return;
        }
        if(user == null){
            resp.getWriter().write("非法访问");
            return;
        }
        try {
            //把表单封装为order
            BeanUtils.populate(order,req.getParameterMap());

            //order设置user
            order.setUser(user);

            //order设置orderTime
            order.setOrderTime(new Date());

            //生成order唯一ID
            order.setId(UuidUtil.getUuid());

            //封装(订单-商品)详情
            List<OrderItem> orderItems = new ArrayList<>();
            for(Map.Entry<Product,Integer> entry : cart.entrySet()){

                OrderItem orderItem = new OrderItem();
                orderItem.setBuyNum(entry.getValue());

                //用product代替product_id
                orderItem.setProduct(entry.getKey());

                //用order代替order_id
                orderItem.setOrder(order);

                totalPrice += entry.getKey().getPrice() * entry.getValue();

                orderItems.add(orderItem);

            }

            //order设置总金额
            order.setMoney(totalPrice);

            //order 封装 items
            order.setItems(orderItems);

            OrderService orderService = new OrderServiceImpl();

            orderService.createOrder(order);

            //购物成功，移除购物车session
            req.getSession().removeAttribute("cart");

            req.getRequestDispatcher("/pay.jsp").forward(req,resp);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
