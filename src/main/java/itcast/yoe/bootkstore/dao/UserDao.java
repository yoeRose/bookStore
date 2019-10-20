package itcast.yoe.bootkstore.dao;

import itcast.yoe.bootkstore.domain.User;

import java.sql.SQLException;

public interface UserDao {
    void add(User user) throws SQLException;

    User findUserByNameAndPassword(String username,String password) throws SQLException;

    User findUserById(String id) throws SQLException;

    void modifyUser(User user) throws SQLException;
}
