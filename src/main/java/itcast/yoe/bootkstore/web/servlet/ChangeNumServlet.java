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
import java.util.Map;

@WebServlet("/changeNum")
public class ChangeNumServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductService productService = new ProductServiceImpl();

        String id = req.getParameter("id"); //购物车中单个商品ID
        String num = req.getParameter("num"); //购物车中单个商品数量


        if(id == null || num == null){
            resp.getWriter().write("非法访问");
            return;
        }
        //通过ID查找书本

        Product book = productService.findBookById(id);
        if(book == null || Integer.parseInt(num) > book.getPnum()){
            resp.getWriter().write("非法访问");
            return;
        }

        Map<Product,Integer> cart = (Map<Product,Integer>)req.getSession().getAttribute("cart");
        if(cart == null){
            resp.getWriter().write("非法访问");
            return;
        }
        if(cart.containsKey(book)){
            if(num.equals("0")){
                cart.remove(book);

            }else {
                cart.put(book,Integer.parseInt(num));
            }
        }

        req.getSession().setAttribute("cart",cart);
        resp.sendRedirect(req.getContextPath()+"/cart.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
