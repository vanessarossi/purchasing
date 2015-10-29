package com.purchasing.printer;

import com.purchasing.dao.QuotationDAO;
import com.purchasing.entity.Quotation;
import com.purchasing.enumerator.TypeEnum;
import com.purchasing.printer.base.BasePrinter;
import com.purchasing.printer.impl.PrinterImpl;
import com.purchasing.service.impl.QuotationService;
import com.purchasing.support.orderBudget.OrderBudgetViewProduct;
import com.purchasing.support.orderBudget.OrderBudgetViewService;
import com.purchasing.support.quotation.QuotationRequestProductView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.util.List;

/**
 * @author Vanessa
 */
public class OrderBudgetPrinter extends PrinterImpl implements BasePrinter {

    @Inject
    private QuotationDAO quotationDAO;

    public File generateOrderBudget(Long code, QuotationService quotationService){
        String url = "";
        List<OrderBudgetViewProduct> orderBudgetViewProducts = new ArrayList<>();
        List<OrderBudgetViewService> orderBudgetViewServices = new ArrayList<>();
        JRBeanCollectionDataSource jrBeanCollectionDataSource = null;

        Quotation quotation = quotationDAO.findById(Quotation.class,code);

        if (quotation.getType().equals(TypeEnum.Material)){
            url = "/jasper/order_budget_product.jrxml";
            List<QuotationRequestProductView> quotationRequestProductViews =  quotationService.groupByProduct(quotation);
            orderBudgetViewProducts= new OrderBudgetViewProduct().generateList(quotationRequestProductViews);

            Collections.sort(orderBudgetViewProducts, new Comparator<OrderBudgetViewProduct>() {
                @Override
                public int compare(OrderBudgetViewProduct o1, OrderBudgetViewProduct o2) {
                    return o1.getDescription().compareTo(o2.getDescription());
                }
            });

            jrBeanCollectionDataSource = new JRBeanCollectionDataSource(orderBudgetViewProducts);

        }else{
            url = "/jasper/order_budget_service.jrxml";
            orderBudgetViewServices= new OrderBudgetViewService().generateList(quotation.getQuotationRequests());
            jrBeanCollectionDataSource = new JRBeanCollectionDataSource(orderBudgetViewServices);
        }

        InputStream inputStream = this.getClass().getResourceAsStream(url);
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(inputStream), getMap(quotation), jrBeanCollectionDataSource);
        } catch (JRException e) {
            e.printStackTrace();
        }
        String nameFile = "pedido_orcamento";
        this.printer(jasperPrint, nameFile);
        File file = this.download(nameFile);
        return file;
    }

    public Map getMap(Quotation quotation) {
        Map map = new HashMap();
        Image img = new ImageIcon(getClass().getResource("/jasper/img/img.jpg")).getImage();
        map.put("img", img);
        map.put("number_quotation",quotation.getId().toString());
        map.put("observation",quotation.getObservation() == null ? "" : quotation.getObservation());
        return map;
    }



}
