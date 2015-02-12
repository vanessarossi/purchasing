package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.DeliveryInformation;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class DeliveryInformationDAO extends DAOImpl<DeliveryInformation,Long> {

    @Inject
    public DeliveryInformationDAO(Session session) {
        super(session);
    }
}
