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

@WebServlet("/productInfo")
public class ProductInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductServiceImpl();
        String id = req.getParameter("id");
        Product book = productService.findBookById(id);
        if(book != null){
            req.setAttribute("book",book);
        }
        req.getRequestDispatcher("/product_info.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
