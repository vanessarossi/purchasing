package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.PurchaseOrder;
import com.purchasing.entity.Supplier;
import com.purchasing.enumerator.StatusEnum;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public class PurchaseOrderDAO extends DAOImpl<PurchaseOrder,Long> {

    @Inject
    public PurchaseOrderDAO(Session session) {
        super(session);
    }

    public List<PurchaseOrder> findBySupplierOrderDate(Supplier supplier){
        Criteria criteria = getSession().createCriteria(PurchaseOrder.class);
        Disjunction disjunction = Restrictions.disjunction();
        criteria.createAlias("budget", "budget");
        disjunction.add(Restrictions.eq("budget.supplier", supplier));
        disjunction.add(Restrictions.in("status", StatusEnum.getStatusForSearchPurchaseOrderBySupplier()));
        criteria.add(disjunction);
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        purchaseOrders.addAll(criteria.list());
        return purchaseOrders;
    }

    public List<PurchaseOrder> pagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(PurchaseOrder.class);
        criteria.setFirstResult(iDisplayStart);
        criteria.setMaxResults(iDisplayLength);
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("budget", "budget");
            criteria.createAlias("budget.supplier", "supplier");
            criteria.createAlias("supplier.person", "person");
            disjunction.add(Restrictions.ilike("person.name", sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.addOrder(Order.desc("id"));
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        purchaseOrders.addAll(criteria.list());
        return purchaseOrders;
    }
    public Integer totalPagination(String sSearch) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(PurchaseOrder.class);
        criteria.setProjection(Projections.rowCount());
        total = Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    /** analista **/
    public List<PurchaseOrder> paginationMissingAnalyst(String sSearch, int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(PurchaseOrder.class);
            criteria.setFirstResult(iDisplayStart);
            criteria.setMaxResults(iDisplayLength);
            criteria.createAlias("approval", "approval", JoinType.LEFT_OUTER_JOIN);
            criteria.add(Restrictions.isNull("approval.dateFirstApproval"));
            criteria.addOrder(Order.desc("id"));
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("budget", "budget");
            criteria.createAlias("budget.supplier", "supplier");
            criteria.createAlias("supplier.person", "person");
            disjunction.add(Restrictions.ilike("person.name", sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.addOrder(Order.desc("id"));
            criteria.add(disjunction);
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        purchaseOrders.addAll(criteria.list());
        return purchaseOrders;
    }
    public Integer totalPaginationMissingAnalyst(String sSearch) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(PurchaseOrder.class);
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("approval", "approval", JoinType.LEFT_OUTER_JOIN);
            criteria.add(Restrictions.isNull("approval.dateFirstApproval"));
            criteria.createAlias("budget", "budget");
            criteria.createAlias("budget.supplier", "supplier");
            criteria.createAlias("supplier.person", "person");
            disjunction.add(Restrictions.ilike("person.name", sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.addOrder(Order.desc("id"));
            criteria.add(disjunction);
            criteria.setProjection(Projections.rowCount());
        total = Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    /**  gerente **/
    public List<PurchaseOrder> paginationMissingManager(String sSearch, BigDecimal maximumValue,  int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(PurchaseOrder.class);
        Disjunction disjunction = Restrictions.disjunction();
            criteria.setFirstResult(iDisplayStart);
            criteria.setMaxResults(iDisplayLength);
            criteria.createAlias("approval", "approval", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("paymentInformation", "paymentInformation", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("budget", "budget");
            criteria.createAlias("budget.supplier", "supplier");
            criteria.createAlias("supplier.person", "person");
            criteria.add(Restrictions.isNull("approval.dateSecondApproval"));
            criteria.add(Restrictions.isNotNull("approval.dateFirstApproval"));
            criteria.add(Restrictions.le("paymentInformation.totalPrice", maximumValue));
            disjunction.add(Restrictions.ilike("person.name", sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.addOrder(Order.desc("id"));
            criteria.add(disjunction);
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        purchaseOrders.addAll(criteria.list());
        return purchaseOrders;
    }
    public Integer totalPaginationMissingManager(String sSearch, BigDecimal maximumValue) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(PurchaseOrder.class);
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("approval", "approval", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("paymentInformation", "paymentInformation", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("budget", "budget");
            criteria.createAlias("budget.supplier", "supplier");
            criteria.createAlias("supplier.person", "person");
            criteria.add(Restrictions.isNull("approval.dateSecondApproval"));
            criteria.add(Restrictions.isNotNull("approval.dateFirstApproval"));
            criteria.add(Restrictions.le("paymentInformation.totalPrice", maximumValue));
            disjunction.add(Restrictions.ilike("person.name", sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.addOrder(Order.desc("id"));
            criteria.add(disjunction);
            criteria.setProjection(Projections.rowCount());
        total = Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    /** diretor  **/
    public List<PurchaseOrder> paginationMissingDirector(String sSearch,BigDecimal maximumValue , int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(PurchaseOrder.class);
        Disjunction disjunction = Restrictions.disjunction();
        criteria.setFirstResult(iDisplayStart);
        criteria.setMaxResults(iDisplayLength);
            criteria.createAlias("approval", "approval", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("paymentInformation", "paymentInformation", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("budget", "budget");
            criteria.createAlias("budget.supplier", "supplier");
            criteria.createAlias("supplier.person", "person");
            criteria.add(Restrictions.isNull("approval.dateThirdApproval"));
            criteria.add(Restrictions.isNotNull("approval.dateSecondApproval"));
            criteria.add(Restrictions.le("paymentInformation.totalPrice", maximumValue));
            disjunction.add(Restrictions.ilike("person.name", sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.addOrder(Order.desc("id"));
        criteria.add(disjunction);
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        purchaseOrders.addAll(criteria.list());
        return purchaseOrders;
    }
    public Integer totalPaginationMissingDirector(String sSearch, BigDecimal maximumValue) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(PurchaseOrder.class);
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("approval", "approval", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("paymentInformation", "paymentInformation", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("budget", "budget");
            criteria.createAlias("budget.supplier", "supplier");
            criteria.createAlias("supplier.person", "person");
            criteria.add(Restrictions.isNull("approval.dateThirdApproval"));
            criteria.add(Restrictions.isNotNull("approval.dateSecondApproval"));
            criteria.add(Restrictions.le("paymentInformation.totalPrice", maximumValue));
            disjunction.add(Restrictions.ilike("person.name", sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.addOrder(Order.desc("id"));
        criteria.add(disjunction);
        criteria.setProjection(Projections.rowCount());
        total = Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    /** diretoria **/
    public List<PurchaseOrder> paginationMissingDirectorship(String sSearch,BigDecimal maximumValue, int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(PurchaseOrder.class);
        Disjunction disjunction = Restrictions.disjunction();
        criteria.setFirstResult(iDisplayStart);
        criteria.setMaxResults(iDisplayLength);
            criteria.createAlias("approval", "approval", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("paymentInformation", "paymentInformation", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("budget", "budget");
            criteria.createAlias("budget.supplier", "supplier");
            criteria.createAlias("supplier.person", "person");
            criteria.add(Restrictions.isNull("approval.dateFourthApproval"));
            criteria.add(Restrictions.isNotNull("approval.dateThirdApproval"));
            criteria.add(Restrictions.ge("paymentInformation.totalPrice", maximumValue));
            disjunction.add(Restrictions.ilike("person.name", sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.addOrder(Order.desc("id"));
        criteria.add(disjunction);
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        purchaseOrders.addAll(criteria.list());
        return purchaseOrders;
    }
    public Integer totalPaginationMissingDirectorship(String sSearch, BigDecimal maximumValue) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(PurchaseOrder.class);
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("approval", "approval", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("paymentInformation", "paymentInformation", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("budget", "budget");
            criteria.createAlias("budget.supplier", "supplier");
            criteria.createAlias("supplier.person", "person");
            criteria.add(Restrictions.isNull("approval.dateFourthApproval"));
            criteria.add(Restrictions.isNotNull("approval.dateThirdApproval"));
            criteria.add(Restrictions.ge("paymentInformation.totalPrice", maximumValue));
            disjunction.add(Restrictions.ilike("person.name", sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.addOrder(Order.desc("id"));
        criteria.add(disjunction);
        criteria.setProjection(Projections.rowCount());
        total = Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    public PurchaseOrder findByConference(Long id) {
        List<StatusEnum> status = new ArrayList<>();
        Criteria criteria = getSession().createCriteria(PurchaseOrder.class);
        criteria.add(Restrictions.in("status", StatusEnum.getStatusSearchPurchaseOrderForConference()));
        criteria.add(Restrictions.idEq(id));
        PurchaseOrder purchaseOrder = (PurchaseOrder) criteria.uniqueResult();
        return purchaseOrder;
    }
}

