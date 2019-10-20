package itcast.yoe.bootkstore.dao;

import itcast.yoe.bootkstore.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    long count(String category) throws SQLException;
    List<Product> findBooks(int currentPage,int pageSize,String category) throws SQLException;

    Product findBookById(String id) throws SQLException;

    void updateProductNum(int id , int num) throws SQLException;
}
