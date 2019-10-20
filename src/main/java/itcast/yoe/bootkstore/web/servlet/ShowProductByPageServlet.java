package itcast.yoe.bootkstore.web.servlet;


import itcast.yoe.bootkstore.domain.PageResult;
import itcast.yoe.bootkstore.domain.Product;
import itcast.yoe.bootkstore.service.ProductService;
import itcast.yoe.bootkstore.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showProductByPage")
public class ShowProductByPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        ProductService productService = new ProductServiceImpl();
        //获取参数
        String category = req.getParameter("category");
        String page = req.getParameter("page");

        int currentPage = 1;
        int pageSize = 4;

        if(page != null && !"".equals(page)){
            currentPage = Integer.parseInt(page);
        }

        PageResult<Product> pageSesult = productService.findPageBooks(currentPage, pageSize, category);

        req.setAttribute("pageResult",pageSesult);
        req.setAttribute("category",category);

        //页面跳转
        req.getRequestDispatcher("/product_list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
