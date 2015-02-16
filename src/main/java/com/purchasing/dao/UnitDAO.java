package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Unit;
import org.hibernate.Session;

import javax.inject.Inject;
import java.util.List;

/**
 * @author vanessa
 */
public class UnitDAO extends DAOImpl<Unit,Long> {

    @Inject
    public UnitDAO(Session session) {
        super(session);
    }

    public List<Unit> pagination(String search, int iDisplayStart, int iDisplayLength) {
        return null;
    }

    public Integer totalPagination(String search) {
        return null;
    }
}
