package itcast.yoe.bootkstore.service.impl;

import itcast.yoe.bootkstore.dao.ProductDao;
import itcast.yoe.bootkstore.dao.impl.ProductDaoImpl;
import itcast.yoe.bootkstore.domain.PageResult;
import itcast.yoe.bootkstore.domain.Product;
import itcast.yoe.bootkstore.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl  implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public PageResult<Product> findPageBooks(int currentPage, int pageSize, String category) {

        try {
            /**
             * 创建pageresult
             */
            PageResult<Product> pageResult = new PageResult<Product>();
            /**
             * 获取对应category分类的总记录数
             */
            long totalCount = productDao.count(category);
            /**
             * 计算对应category分类的总页数
             */
            int totalPage = (int)Math.ceil(totalCount * 1.0 / pageSize);

            /**
             * 查询数据库获取对应category分类的所有记录
             */
            List<Product> list = productDao.findBooks(currentPage, pageSize, category);

            /**
             * 设置pageResult
             */
            //设置当前页
            pageResult.setCurrentPage(currentPage);
            //设置一页的显示记录数
            pageResult.setPageSize(pageSize);
            //设置记录实体
            pageResult.setList(list);
            //设置总记录数
            pageResult.setTotalCount(totalCount);
            //设置总页数
            pageResult.setTotalPage(totalPage);

            return pageResult;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Product findBookById(String id) {
        try {
            return productDao.findBookById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
