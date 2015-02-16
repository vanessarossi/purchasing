package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.TypeService;
import org.hibernate.Session;

import javax.inject.Inject;
import java.util.List;

/**
 * @author vanessa
 */
public class TypeServiceDAO extends DAOImpl<TypeService,Long> {

    @Inject
    public TypeServiceDAO(Session session) {
        super(session);
    }

    public List<TypeService> pagination(String search, int iDisplayStart, int iDisplayLength) {
        return null;
    }

    public Integer totalPagination(String search) {
        return null;
    }
}
