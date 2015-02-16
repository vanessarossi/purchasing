package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Company;
import com.purchasing.entity.CostCenter;
import org.hibernate.Session;

import javax.inject.Inject;
import java.util.List;

/**
 * @author vanessa
 */
public class CostCenterDAO extends DAOImpl<CostCenter,Long> {

    @Inject
    public CostCenterDAO(Session session) {
        super(session);
    }

    public List<CostCenter> findByCompany(Company company) {
        return null;
    }

    public List<CostCenter> pagination(String search, int iDisplayStart, int iDisplayLength) {
        return null;
    }

    public Integer totalPagination(String search) {
        return null;
    }
}
