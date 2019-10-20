package itcast.yoe.bootkstore.dao.impl;

import itcast.yoe.bootkstore.dao.UserDao;
import itcast.yoe.bootkstore.domain.User;
import itcast.yoe.bootkstore.utils.c3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    //c3p0连接池
    QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());

    /**
     * 用户注册功能
     * @param user
     * @throws SQLException
     */
    @Override
    public void add(User user) throws SQLException {
        String sql = "insert into user "+
                "(username,password,gender,email,telephone,introduce,activeCode,state,role,registTime) " +
                "values(?,?,?,?,?,?,?,?,?,?)";
        List<Object> list = new ArrayList<>();
        list.add(user.getUsername());
        list.add(user.getPassword());
        list.add(user.getGender());
        list.add(user.getEmail());
        list.add(user.getTelephone());
        list.add(user.getIntroduce());
        list.add(user.getActiveCode());
        list.add(user.getState());
        list.add(user.getRole());
        list.add(user.getRegistTime());

        qr.update(sql,list.toArray());
    }

    /**
     * 用户登录功能
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    @Override
    public User findUserByNameAndPassword(String username, String password) throws SQLException {
        String sql = "select * from user where username = ? and password = ?";
        return qr.query(sql,new BeanHandler<User>(User.class),username,password);
    }

    @Override
    public User findUserById(String id) throws SQLException {
        String sql = "select * from user where id = ?";
        return qr.query(sql,new BeanHandler<User>(User.class),id);
    }

    @Override
    public void modifyUser(User user) throws SQLException {
        String sql = "update user set password = ? ,gender = ? , telephone = ?  where id = ?";
        List<Object> list = new ArrayList<>();
        list.add(user.getPassword());
        list.add(user.getGender());
        list.add(user.getTelephone());
        list.add(user.getId());
        qr.update(sql,list.toArray());

    }

}
