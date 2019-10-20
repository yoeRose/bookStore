package itcast.yoe.bootkstore.service;

import itcast.yoe.bootkstore.domain.User;
import itcast.yoe.bootkstore.exception.UserException;

import java.sql.SQLException;

public interface UserService {
    void register(User user) throws SQLException, UserException;
    User login(String username,String password) throws SQLException, UserException;

    User findUserById(String id) throws UserException;

    void modifyUser(User user) throws SQLException, UserException;
}
