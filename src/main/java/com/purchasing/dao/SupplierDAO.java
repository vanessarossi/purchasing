package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Supplier;
import org.hibernate.Session;

import javax.inject.Inject;
import java.util.List;

/**
 * @author vanessa
 */
public class SupplierDAO extends DAOImpl<Supplier,Long> {

    @Inject
    public SupplierDAO(Session session) {
        super(session);
    }

    public List<Supplier> pagination(String search, int iDisplayStart, int iDisplayLength) {
        return null;
    }

    public List<Supplier> search(String text) {
        return null;
    }

    public Integer totalPagination(String search) {
        return null;
    }
}
