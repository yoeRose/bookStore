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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        try{

            /**
             * 验证码的校验
             */
            String checkcode = req.getParameter("checkcode");
            HttpSession session = req.getSession();
            String checkcode_server = (String)session.getAttribute("checkcode_session");
            session.removeAttribute("checkcode_session");
            if(checkcode == null || !checkcode.equalsIgnoreCase(checkcode_server)){
                req.setAttribute("ccode_msg","验证码错误");
                req.getRequestDispatcher("/register.jsp").forward(req,resp);
                return;
            }

            //将参数转化为user模型
            BeanUtils.populate(user,req.getParameterMap());
            userService.register(user);

            //注册成功跳转到成功页面
            req.getRequestDispatcher("/registersuccess.jsp").forward(req,resp);

        }catch (IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UserException e) {
            e.printStackTrace();
            //注册不成功
            req.setAttribute("register_msg",e.getMessage());
            req.getRequestDispatcher("/register.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

}
