package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Contract;
import com.purchasing.entity.PurchaseOrder;
import com.purchasing.entity.Supplier;
import com.purchasing.enumerator.StatusEnum;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
        Criteria criteria = getSession().createCriteria(Supplier.class);
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
        criteria.add(disjunction);
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        purchaseOrders.addAll(criteria.list());
        return purchaseOrders;
    }
    public Integer totalPagination(String sSearch) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(Contract.class);
        Disjunction disjunction = Restrictions.disjunction();
        criteria.add(disjunction);
        criteria.setProjection(Projections.rowCount());
        total = Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    public List<PurchaseOrder> paginationMissingAnalyst(String sSearch, int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(PurchaseOrder.class);
            criteria.setFirstResult(iDisplayStart);
            criteria.setMaxResults(iDisplayLength);
            criteria.createAlias("approval", "approval", JoinType.LEFT_OUTER_JOIN);
            criteria.add(Restrictions.isNull("approval.dateFirstApproval"));
        Disjunction disjunction = Restrictions.disjunction();
            criteria.add(disjunction);
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        purchaseOrders.addAll(criteria.list());
        return purchaseOrders;
    }
    public Integer totalPaginationMissingAnalyst(String sSearch) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(Contract.class);
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("approval", "approval", JoinType.LEFT_OUTER_JOIN);
            criteria.add(Restrictions.isNull("approval.dateFirstApproval"));
            criteria.add(disjunction);
            criteria.setProjection(Projections.rowCount());
        total = Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    public List<PurchaseOrder> paginationMissingManager(String sSearch, int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(PurchaseOrder.class);
            criteria.setFirstResult(iDisplayStart);
            criteria.setMaxResults(iDisplayLength);
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("approval", "approval", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("paymentInformation", "paymentInformation", JoinType.LEFT_OUTER_JOIN);
            criteria.add(Restrictions.isNull("approval.dateSecondApproval"));
            criteria.add(Restrictions.isNotNull("approval.dateFirstApproval"));
            criteria.add(Restrictions.ge("paymentInformation.totalFinalPrice", new BigDecimal(300.00)));
            criteria.add(disjunction);
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        purchaseOrders.addAll(criteria.list());
        return purchaseOrders;
    }
    public Integer totalPaginationMissingManager(String sSearch) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(Contract.class);
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("approval", "approval", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("paymentInformation", "paymentInformation", JoinType.LEFT_OUTER_JOIN);
            criteria.add(Restrictions.isNull("approval.dateSecondApproval"));
            criteria.add(Restrictions.isNotNull("approval.dateFirstApproval"));
            criteria.add(Restrictions.ge("paymentInformation.totalFinalPrice", new BigDecimal(300.00)));
            criteria.add(disjunction);
            criteria.setProjection(Projections.rowCount());
        total = Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    public List<PurchaseOrder> paginationMissingDirector(String sSearch, int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(PurchaseOrder.class);
        criteria.setFirstResult(iDisplayStart);
        criteria.setMaxResults(iDisplayLength);
        Disjunction disjunction = Restrictions.disjunction();


        criteria.createAlias("aprovacao", "aprovacao", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("orcamento", "orcamento", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.isNull("aprovacao.dataTerceiraAprovacao"));
        criteria.add(Restrictions.isNotNull("aprovacao.dataSegundaAprovacao"));
        criteria.createAlias("orcamento.informacaoPagamento", "infoPag", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.ge("infoPag.valorTotalDesconto", new BigDecimal(1500.00)));




        criteria.add(disjunction);
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        purchaseOrders.addAll(criteria.list());
        return purchaseOrders;
    }
    public Integer totalPaginationMissingDirector(String sSearch) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(Contract.class);
        Disjunction disjunction = Restrictions.disjunction();
        criteria.add(disjunction);
        criteria.setProjection(Projections.rowCount());
        total = Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    public List<PurchaseOrder> paginationMissingDirectorship(String sSearch, int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(PurchaseOrder.class);
        criteria.setFirstResult(iDisplayStart);
        criteria.setMaxResults(iDisplayLength);
        Disjunction disjunction = Restrictions.disjunction();
        criteria.add(disjunction);
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        purchaseOrders.addAll(criteria.list());
        return purchaseOrders;
    }
    public Integer totalPaginationMissingDirectorship(String sSearch) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(Contract.class);
        Disjunction disjunction = Restrictions.disjunction();
        criteria.add(disjunction);
        criteria.setProjection(Projections.rowCount());
        total = Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }
}

