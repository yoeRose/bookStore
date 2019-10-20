package itcast.yoe.bootkstore.web.servlet;

import itcast.yoe.bootkstore.domain.User;
import itcast.yoe.bootkstore.exception.UserException;
import itcast.yoe.bootkstore.service.UserService;
import itcast.yoe.bootkstore.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modifyUserInfo")
public class ModifyUserInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        UserService userService = new UserServiceImpl();
        try {
            BeanUtils.populate(user,req.getParameterMap());
            userService.modifyUser(user);

            req.getSession().invalidate();

            resp.sendRedirect(req.getContextPath()+"/modifyUserInfoSuccess.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().print(e.getMessage());
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
