package itcast.yoe.bootkstore.dao.impl;

import itcast.yoe.bootkstore.dao.ProductDao;
import itcast.yoe.bootkstore.domain.Product;
import itcast.yoe.bootkstore.utils.ManagerThreadLocal;
import itcast.yoe.bootkstore.utils.c3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    //c3p0连接池
    QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());

    @Override
    /**
     * 如果category为null，则表示查找所有书本的记录数。
     */
    public long count(String category) throws SQLException {
        long count = 0;
        String sql = "select count(*) from products where 1=1   ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList();

        if(category != null && !"".equals(category)){
            sb.append(" and category = ? ");
            params.add(category);
        }
        sql = sb.toString();
        count = (long)qr.query(sql,new ScalarHandler<>(),params.toArray());
        return count;
    }


    @Override
    public List<Product> findBooks(int currentPage,int pageSize,String category) throws SQLException {
        String sql  = "select * from products where 1 = 1  ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();

        if(category != null && !"".equals(category)){
            sb.append(" and category = ? ");
            params.add(category);
        }

        int start = (currentPage -1 )*pageSize;
        int length = pageSize;
        sb.append(" limit ?,? ");

        params.add(start);
        params.add(length);

        sql = sb.toString();
        return  qr.query(sql,new BeanListHandler<Product>(Product.class),params.toArray());
    }

    @Override
    public Product findBookById(String id) throws SQLException {
        String sql = "select * from products where id = ?";
        return qr.query(sql,new BeanHandler<Product>(Product.class),id);
    }

    @Override
    public void updateProductNum(int id, int num) throws SQLException {
        String sql = "update products set pnum = pnum - ? where id = ?";
        qr.update(ManagerThreadLocal.getConnection(),sql,num,id);
    }
}
