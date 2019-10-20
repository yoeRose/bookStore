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
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User user = userService.login(username, password);
            String path;
            /**
             * 这里做一个身份的判断，不同身份有不同的页面
             */
            if("管理员".equals(user.getRole())){
                path = "/admin/login/home.jsp";
            }else {
                path = "/index.jsp";
            }
            /**
             * 登录成功存session
             */
            req.getSession().setAttribute("user",user);
           // req.getRequestDispatcher(path).forward(req,resp);
            resp.sendRedirect(req.getContextPath()+path);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UserException e) {
            e.printStackTrace();
            req.setAttribute("login_msg",e.getMessage());
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
