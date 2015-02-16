package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.FormPayment;
import org.hibernate.Session;

import javax.inject.Inject;
import java.util.List;

/**
 * @author vanessa
 */
public class FormPaymentDAO extends DAOImpl<FormPayment,Long> {

    @Inject
    public FormPaymentDAO(Session session) {
        super(session);
    }

    public List<FormPayment> pagination(String search, int iDisplayStart, int iDisplayLength) {
        return null;
    }

    public Integer totalPagination(String search) {
        return null;
    }
}
