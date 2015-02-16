package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public class UserDAO extends DAOImpl<User,Long> {

    @Inject
    public UserDAO(Session session) {
        super(session);
    }

    public User findByUsernamePassword(String username, String password){
        Criteria criteria = getSession().createCriteria(User.class);
            criteria.add(Restrictions.eq("username", username));
            criteria.add(Restrictions.eq("password", password));
            criteria.add(Restrictions.eq("active", true));
        User user  = (User) criteria.uniqueResult();
        return user;
    }

    public User findByUsername(String username){
        Criteria criteria = getSession().createCriteria(User.class).add(Restrictions.eq("username", username));
        User user = (User) criteria.uniqueResult();
        return user;
    }

    public User findByEmail(String email){
        Criteria criteria = getSession().createCriteria(User.class).add(Restrictions.eq("email", email));
        User user = (User) criteria.uniqueResult();
        return user;
    }

    public List<User> pagination(String sSearch,int iDisplayStart, int iDisplayLength){
        Criteria criteria = getSession().createCriteria(User.class);
        Disjunction disjunction = Restrictions.disjunction();
                   disjunction.add(Restrictions.ilike("name",sSearch, MatchMode.ANYWHERE));
                   disjunction.add(Restrictions.ilike("username",sSearch, MatchMode.ANYWHERE));
                   disjunction.add(Restrictions.ilike("email",sSearch, MatchMode.ANYWHERE));

            criteria.setFirstResult(iDisplayStart);
            criteria.setMaxResults(iDisplayLength);
            criteria.add(disjunction);
            criteria.addOrder(Order.asc("name"));
        List<User>users = new ArrayList<>();
        users.addAll(criteria.list());
        return users;
    }

    public Integer totalPagination(String sSearch){
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(User.class);
        Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.ilike("name",sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("username",sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("email",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.setProjection(Projections.rowCount());
        total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }
}
