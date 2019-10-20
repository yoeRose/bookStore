package itcast.yoe.bootkstore.web.servlet;

import itcast.yoe.bootkstore.domain.Order;
import itcast.yoe.bootkstore.service.OrderService;
import itcast.yoe.bootkstore.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findOrderItemsByOrderId")
public class FindOrderItemsByOrderIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");

        OrderService orderService = new OrderServiceImpl();

        Order order = orderService.findOrderByOrderId(orderId);

        req.setAttribute("order",order);
        req.getRequestDispatcher("/orderInfo.jsp").forward(req,resp);

        System.out.println(order);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
