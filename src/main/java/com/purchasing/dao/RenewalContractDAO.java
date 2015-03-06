package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Contract;
import com.purchasing.entity.RenewalContract;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public class RenewalContractDAO extends DAOImpl<RenewalContract,Long> {

    @Inject
    public RenewalContractDAO(Session session) {
        super(session);
    }

    public List<RenewalContract> findByContract(Contract contract) {
        List<RenewalContract>  renewalContracts = new ArrayList<>();
        Criteria criteria = getSession().createCriteria(RenewalContract.class);
                            criteria.add(Restrictions.eq("contract", contract));
                            criteria.addOrder(Order.desc("finalDate"));
        renewalContracts = criteria.list();
        return renewalContracts;
    }
}
