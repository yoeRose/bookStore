package itcast.yoe.bootkstore.service.impl;

import itcast.yoe.bootkstore.dao.UserDao;
import itcast.yoe.bootkstore.dao.impl.UserDaoImpl;
import itcast.yoe.bootkstore.domain.User;
import itcast.yoe.bootkstore.exception.UserException;
import itcast.yoe.bootkstore.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();


    /**
     * 用户注册逻辑代码
     * @param user
     * @throws UserException
     */
    @Override
    public void register(User user) throws UserException {
        try {
            userDao.add(user);
            //发送激活邮件
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new UserException("注册失败");
        }
    }

    /**
     * 用户登录逻辑代码
     * @param username
     * @param password
     * @return
     * @throws UserException
     */
    @Override
    public User login(String username, String password) throws UserException {
        try {
          User user = userDao.findUserByNameAndPassword(username,password);
          if(user == null){
              throw  new UserException("用户名或密码不正确");
          }
//          if(user.getState() == 0){
//              throw  new UserException("用户未激活");
//          }
          return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new UserException("登录失败");
        }
    }

    @Override
    public User findUserById(String id) throws UserException{
        User user = null;
        try {
            user = userDao.findUserById(id);
            if(user == null){
                throw  new UserException("用户不存在");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new UserException("未知错误");
        }
        return user;
    }

    @Override
    public void modifyUser(User user) throws UserException{
        try {
            userDao.modifyUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new UserException("修改用户失败");
        }
    }


}
