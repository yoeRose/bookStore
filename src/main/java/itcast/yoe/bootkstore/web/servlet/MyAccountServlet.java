package itcast.yoe.bootkstore.web.servlet;

import itcast.yoe.bootkstore.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/myaccount")
public class MyAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        /**
         * 用户没有登录则跳转到登录页面
         */
        if(user == null){
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
        }else{
            /**
             * 登录了跳转myAccount.jsp
             */
            //req.getRequestDispatcher("/myAccount.jsp").forward(req,resp);
            resp.sendRedirect(req.getContextPath()+"/myAccount.jsp");
        }
    }
}
