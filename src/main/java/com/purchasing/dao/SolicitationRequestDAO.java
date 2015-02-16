package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Solicitation;
import com.purchasing.entity.SolicitationRequest;
import org.hibernate.Session;

import javax.inject.Inject;
import java.util.List;

/**
 * @author vanessa
 */
public class SolicitationRequestDAO extends DAOImpl<SolicitationRequest,Long> {

    @Inject
    public SolicitationRequestDAO(Session session) {
        super(session);
    }

    public List<SolicitationRequest> paginationBySolicitation(Solicitation solicitation, String search, int iDisplayStart, int iDisplayLength) {
        return null;
    }

    public Integer totalPaginationBySolicitation(Solicitation solicitation, String search) {
        return null;
    }

    public List<SolicitationRequest> findSolicitationRequestDelivered(Solicitation solicitation) {
        return null;
    }
}
