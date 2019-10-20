package itcast.yoe.bootkstore.service;


import itcast.yoe.bootkstore.domain.PageResult;
import itcast.yoe.bootkstore.domain.Product;

import java.sql.SQLException;

public interface ProductService {
    PageResult<Product> findPageBooks(int currentPage,int pageSize, String category);
    Product findBookById(String id);
}
