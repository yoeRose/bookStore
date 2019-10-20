package itcast.yoe.bootkstore.web.servlet;

import itcast.yoe.bootkstore.domain.User;
import itcast.yoe.bootkstore.exception.UserException;
import itcast.yoe.bootkstore.service.UserService;
import itcast.yoe.bootkstore.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserById")
public class FindUserByIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();

        /**
         * 获取ID
         */
        String userId = req.getParameter("id");

        /**
         * 查数据库
         */
        try {
            /**
             * el表达式取数据的顺序：page，request，session，application
             */
            User user = userService.findUserById(userId);
            req.setAttribute("userinfo",user);
            req.getRequestDispatcher("/modifyuserinfo.jsp").forward(req,resp);

        } catch (UserException e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
