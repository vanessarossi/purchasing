package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.support.excel.entity.principal.Report;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Vanessa on 12/29/15.
 */
public class ReportDAO extends DAOImpl<Object,Integer> {

    @Inject
    public ReportDAO(Session session) {
        super(session);
    }

    public Collection<Object> getPaymentForecastReport(){
        Collection<Object> objects = getSession().createSQLQuery("SELECT * FROM payment_forecast_report").list();
       return  objects ;
    }

    public Collection<Object> getFinancialManagementReport(Report report){
        Collection<Object> objects = new ArrayList<>();
        SQLQuery sqlQuery =  getSession().createSQLQuery("CALL financial_management_report_monthly(:year,:initialMonth,:lastMonth)");
        sqlQuery.setParameter("year",report.getYear());
        sqlQuery.setParameter("initialMonth",report.getInitialMonth());
        sqlQuery.setParameter("lastMonth",report.getLastMonth());
        objects = sqlQuery.list();
        return  objects ;
    }

    public Collection<Object> getTotalPurchasedProductClassificationReport(Report report){
        Collection<Object> objects = new ArrayList<>();
        SQLQuery sqlQuery =  getSession().createSQLQuery("CALL total_purchased_product_classification_report_monthly(:year,:initialMonth,:lastMonth)");
        sqlQuery.setParameter("year",report.getYear());
        sqlQuery.setParameter("initialMonth",report.getInitialMonth());
        sqlQuery.setParameter("lastMonth",report.getLastMonth());
        objects = sqlQuery.list();
        return  objects ;
    }

    public Collection<Object> getTotalPurchasedServiceTypeReport(Report report){
        Collection<Object> objects = new ArrayList<>();
        SQLQuery sqlQuery =  getSession().createSQLQuery("CALL total_purchased_service_type_report_monthly(:year,:initialMonth,:lastMonth)");
        sqlQuery.setParameter("year",report.getYear());
        sqlQuery.setParameter("initialMonth",report.getInitialMonth());
        sqlQuery.setParameter("lastMonth",report.getLastMonth());
        objects = sqlQuery.list();
        return  objects ;
    }

    public Collection<Object> getFinancialManagementByCostCenterReport(Report report,Long costCenter){
        Collection<Object> objects = new ArrayList<>();
        SQLQuery sqlQuery =  getSession().createSQLQuery("CALL financial_management_by_cost_center_report_monthly(:year,:initialMonth,:lastMonth,:costCenter)");
        sqlQuery.setParameter("year",report.getYear());
        sqlQuery.setParameter("initialMonth",report.getInitialMonth());
        sqlQuery.setParameter("lastMonth",report.getLastMonth());
        sqlQuery.setParameter("costCenter",costCenter);
        objects = sqlQuery.list();
        return  objects ;
    }

    public Collection<Object> getPurchaseOrderForSupplierAndExpirationDateReport(Report report){
        Collection<Object> objects = new ArrayList<>();
        SQLQuery sqlQuery =  getSession().createSQLQuery("CALL purchase_order_for_supplier_and_expiration_date_report(:supplier,:expirationDate)");
        sqlQuery.setParameter("supplier",report.getSupplier());
        sqlQuery.setParameter("expirationDate",report.getExpirationDate());
        objects = sqlQuery.list();
        return  objects ;
    }

    public Collection<Object> getTotalSolicitationReport(Report report){
        Collection<Object> objects = new ArrayList<>();
        SQLQuery sqlQuery =  getSession().createSQLQuery("CALL total_solicitation_report_monthly(:year,:initialMonth,:lastMonth)");
        sqlQuery.setParameter("year",report.getYear());
        sqlQuery.setParameter("initialMonth",report.getInitialMonth());
        sqlQuery.setParameter("lastMonth",report.getLastMonth());
        objects = sqlQuery.list();
        return  objects ;
    }

    public Collection<Object> getTotalSolicitationRefusedReport(Report report){
        Collection<Object> objects = new ArrayList<>();
        SQLQuery sqlQuery =  getSession().createSQLQuery("CALL total_solicitation_refused_report_monthly(:year,:initialMonth,:lastMonth)");
        sqlQuery.setParameter("year",report.getYear());
        sqlQuery.setParameter("initialMonth",report.getInitialMonth());
        sqlQuery.setParameter("lastMonth",report.getLastMonth());
        objects = sqlQuery.list();
        return  objects ;
    }

    public Collection<Object> getTotalSolicitationCancelledReport(Report report){
        Collection<Object> objects = new ArrayList<>();
        SQLQuery sqlQuery =  getSession().createSQLQuery("CALL total_solicitation_cancelled_report_monthly(:year,:initialMonth,:lastMonth)");
        sqlQuery.setParameter("year",report.getYear());
        sqlQuery.setParameter("initialMonth",report.getInitialMonth());
        sqlQuery.setParameter("lastMonth",report.getLastMonth());
        objects = sqlQuery.list();
        return  objects ;
    }

    public Collection<Object> getTotalSolicitationFinishedReport(Report report){
        Collection<Object> objects = new ArrayList<>();
        SQLQuery sqlQuery =  getSession().createSQLQuery("CALL total_solicitation_finished_report_monthly(:year,:initialMonth,:lastMonth)");
        sqlQuery.setParameter("year",report.getYear());
        sqlQuery.setParameter("initialMonth",report.getInitialMonth());
        sqlQuery.setParameter("lastMonth",report.getLastMonth());
        objects = sqlQuery.list();
        return  objects ;
    }

    public Collection<Object> getTotalPurchaseOrderReport(Report report){
        Collection<Object> objects = new ArrayList<>();
        SQLQuery sqlQuery =  getSession().createSQLQuery("CALL total_purchase_order_report_monthly(:year,:initialMonth,:lastMonth)");
        sqlQuery.setParameter("year",report.getYear());
        sqlQuery.setParameter("initialMonth",report.getInitialMonth());
        sqlQuery.setParameter("lastMonth",report.getLastMonth());
        objects = sqlQuery.list();
        return  objects ;
    }

    public Collection<Object> getTotalPurchaseOrderRefusedReport(Report report){
        Collection<Object> objects = new ArrayList<>();
        SQLQuery sqlQuery =  getSession().createSQLQuery("CALL total_purchase_order_refused_report_monthly(:year,:initialMonth,:lastMonth)");
        sqlQuery.setParameter("year",report.getYear());
        sqlQuery.setParameter("initialMonth",report.getInitialMonth());
        sqlQuery.setParameter("lastMonth",report.getLastMonth());
        objects = sqlQuery.list();
        return  objects ;
    }

    public Collection<Object> getTotalPurchaseOrderCancelledReport(Report report){
        Collection<Object> objects = new ArrayList<>();
        SQLQuery sqlQuery =  getSession().createSQLQuery("CALL total_purchase_order_cancelled_report_monthly(:year,:initialMonth,:lastMonth)");
        sqlQuery.setParameter("year",report.getYear());
        sqlQuery.setParameter("initialMonth",report.getInitialMonth());
        sqlQuery.setParameter("lastMonth",report.getLastMonth());
        objects = sqlQuery.list();
        return  objects ;
    }

    public Collection<Object> getTotalPurchaseOrderFinishedReport(Report report){
        Collection<Object> objects = new ArrayList<>();
        SQLQuery sqlQuery =  getSession().createSQLQuery("CALL total_purchase_order_finished_report_monthly(:year,:initialMonth,:lastMonth)");
        sqlQuery.setParameter("year",report.getYear());
        sqlQuery.setParameter("initialMonth",report.getInitialMonth());
        sqlQuery.setParameter("lastMonth",report.getLastMonth());
        objects = sqlQuery.list();
        return  objects ;
    }
}
