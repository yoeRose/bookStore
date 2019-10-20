package itcast.yoe.bootkstore.web.servlet;

import itcast.yoe.bootkstore.domain.Product;
import itcast.yoe.bootkstore.service.ProductService;
import itcast.yoe.bootkstore.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/addCart")
public class AddCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductServiceImpl();

        /**
         * 获取图书ID
         */
        String id = req.getParameter("id");
        /**
         * 数据库查找书本
         */
        Product book = productService.findBookById(id);

        /**
         * 查看当前是否已经有购物车session，没有则创建
         */
        Map<Product,Integer> cart = (Map<Product, Integer>) req.getSession().getAttribute("cart");

        if(cart == null){
            cart = new HashMap<Product,Integer>();
        }

        /**
         * 如果书本已经存在，则在购买数量上+1，前提是书本要重写equals方法
         */
        if(cart.containsKey(book)){
            cart.put(book,cart.get(book)+1);
        }else {
            cart.put(book,1);
        }

        req.getSession().setAttribute("cart",cart);

        String html = "<a href='"+ req.getContextPath() +"/showProductByPage'>继续购物</a>";
        html += "&nbsp;&nbsp;&nbsp;&nbsp;<a href='" + req.getContextPath()  + "/cart.jsp'>查看购物车</a>";

        resp.getWriter().write(html);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
