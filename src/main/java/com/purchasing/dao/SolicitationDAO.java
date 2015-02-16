package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.CostCenter;
import com.purchasing.entity.Solicitation;
import com.purchasing.entity.User;
import org.hibernate.Session;

import javax.inject.Inject;
import java.util.List;

/**
 * @author vanessa
 */
public class SolicitationDAO extends DAOImpl<Solicitation,Long> {

    @Inject
    public SolicitationDAO(Session session) {
        super(session);
    }

    public Integer totalMissingPaginationAnalyst(String search, List<CostCenter> costCenters) {
        return null;
    }

    public Integer totalMissingPaginationCoordinator(String search, List<CostCenter> costCenters) {
        return null;
    }

    public List<Solicitation> findMissingPaginationAnalyst(String search, int iDisplayStart, int iDisplayLength, List<CostCenter> costCenters) {
        return null;
    }

    public List<Solicitation> findMissingPaginationCoordinator(String search, int iDisplayStart, int iDisplayLength, List<CostCenter> costCenters) {
        return null;
    }

    public Integer totalIndividualPagination(String search, User userLogged) {
        return null;
    }

    public Integer totalIndividualPaginationCoordinator(String search, List<CostCenter> costCenters) {
        return null;
    }

    public List<Solicitation> findPaginationIndividual(String search, int iDisplayStart, int iDisplayLength, User userLogged) {
        return null;
    }

    public List<Solicitation> findPaginationIndividualCoordinator(String search, int iDisplayStart, int iDisplayLength, List<CostCenter> costCenters) {
        return null;
    }

    public Integer totalPagination(String search) {
        return null;
    }

    public List<Solicitation> pagination(String search, int iDisplayStart, int iDisplayLength) {
        return null;
    }
}
