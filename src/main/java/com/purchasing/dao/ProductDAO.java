package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Product;
import org.hibernate.Session;

import javax.inject.Inject;
import java.util.List;

/**
 * @author vanessa
 */
public class ProductDAO extends DAOImpl<Product,Long> {

    @Inject
    public ProductDAO(Session session) {
        super(session);
    }

    public Integer totalPagination(String search) {
        return null;
    }

    public List<Product> search(String search) {
        return null;
    }

    public List<Product> pagination(String search, int iDisplayStart, int iDisplayLength) {
        return null;
    }
}
