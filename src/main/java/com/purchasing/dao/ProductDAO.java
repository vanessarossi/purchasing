package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Product;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class ProductDAO extends DAOImpl<Product,Long> {

    @Inject
    public ProductDAO(Session session) {
        super(session);
    }
}
