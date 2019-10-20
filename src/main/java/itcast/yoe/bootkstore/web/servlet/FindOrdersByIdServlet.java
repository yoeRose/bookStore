package itcast.yoe.bootkstore.web.servlet;

import itcast.yoe.bootkstore.domain.Order;
import itcast.yoe.bootkstore.domain.User;
import itcast.yoe.bootkstore.service.OrderService;
import itcast.yoe.bootkstore.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findOrdersById")
public class FindOrdersByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        if(user == null){
            resp.getWriter().write("非法访问");
            return;
        }
        OrderService orderService = new OrderServiceImpl();
        List<Order> orders = orderService.findOrdersByUserId(user.getId() + "");

        req.setAttribute("orders",orders);
        req.setAttribute("count",orders.size());

        req.getRequestDispatcher("/orderlist.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
