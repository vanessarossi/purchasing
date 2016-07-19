package com.purchasing.printer;

import com.purchasing.dao.PurchaseOrderDAO;
import com.purchasing.entity.PurchaseOrder;
import com.purchasing.enumerator.TypeEnum;
import com.purchasing.enumerator.TypePersonEnum;
import com.purchasing.printer.base.BasePrinter;
import com.purchasing.printer.impl.PrinterImpl;
import com.purchasing.service.impl.PurchaseOrderService;
import com.purchasing.support.date.Conversor;
import com.purchasing.support.purchaseOrder.printer.OrderRequestProductViewPrinter;
import com.purchasing.support.purchaseOrder.printer.OrderRequestServiceViewPrinter;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vanessa on 4/23/15.
 */
public class OrderPrinter extends PrinterImpl implements BasePrinter {

    @Inject
    private PurchaseOrderDAO purchaseOrderDAO;

    @Override
    public File generateOrder(Long code,PurchaseOrderService purchaseOrderService) {
        String url = "";
        List<OrderRequestProductViewPrinter> solicitationViewList = new ArrayList<>();
        List<OrderRequestServiceViewPrinter> orderRequestServiceViewList = new ArrayList<>();
        JRBeanCollectionDataSource jrBeanCollectionDataSource = null;

        PurchaseOrder purchaseOrder = purchaseOrderDAO.findById(PurchaseOrder.class, code);

        if (purchaseOrder.getBudget().getQuotation().getType().equals(TypeEnum.Material)){
            url = "/jasper/purchase_request_product.jrxml";
            solicitationViewList = new OrderRequestProductViewPrinter().generateList(purchaseOrderService.groupByProduct(purchaseOrder));
            jrBeanCollectionDataSource = new JRBeanCollectionDataSource(solicitationViewList);
        }else{
            url = "/jasper/purchase_request_service.jrxml";
            orderRequestServiceViewList = new OrderRequestServiceViewPrinter().generateList(purchaseOrder.getOrderRequests());
            jrBeanCollectionDataSource = new JRBeanCollectionDataSource(orderRequestServiceViewList);
        }

        InputStream inputStream = this.getClass().getResourceAsStream(url);
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(inputStream), getMap(purchaseOrder), jrBeanCollectionDataSource);
        } catch (JRException e) {
            e.printStackTrace();
        }
        String nameFile = "pedido_compra";
        this.printer(jasperPrint, nameFile);
        File file = this.download(nameFile);
        return file;
    }

    public Map getMap(PurchaseOrder purchaseOrder) {
        Map map = new HashMap();
        Image img = new ImageIcon(getClass().getResource("/jasper/img/img.jpg")).getImage();
        String corporate_name = purchaseOrder.getBudget().getSupplier().getPerson().getName();
        String document_supplier = (purchaseOrder.getBudget().getSupplier().getPerson().getTypePerson().equals(TypePersonEnum.JuristicPerson)) ? purchaseOrder.getBudget().getSupplier().getPerson().getCnpj(): purchaseOrder.getBudget().getSupplier().getPerson().getCpf();

        String number_request = purchaseOrder.getId().toString();
        String date_purchase_request = purchaseOrder.getDateGenerate() == null ? "" : Conversor.converterDateTimeInString(purchaseOrder.getDateGenerate());

        String number_budget = purchaseOrder.getBudget().getNumberBudget();
        String date_budget = Conversor.converterDateInString(purchaseOrder.getBudget().getDate());


        /** Payment **/

        String mean_payment = purchaseOrder.getPaymentInformation().getMeanPayment().getDescription();
        String date_first_installment = purchaseOrder.getPaymentInformation().getDateFirstInstallment()== null ? "" :  Conversor.converterDateInString(purchaseOrder.getPaymentInformation().getDateFirstInstallment());
        String date_last_installment = purchaseOrder.getPaymentInformation().getDateLastInstallment() == null ? "" : Conversor.converterDateInString(purchaseOrder.getPaymentInformation().getDateLastInstallment());
        String date_input = purchaseOrder.getPaymentInformation().getDateInput() == null ? "" : Conversor.converterDateInString(purchaseOrder.getPaymentInformation().getDateInput());
        String expiration_date = purchaseOrder.getPaymentInformation().getExpirationDate() == null ? "" : Conversor.converterDateInString(purchaseOrder.getPaymentInformation().getExpirationDate());
        String share_price = purchaseOrder.getPaymentInformation().getSharePrice() == null ? "" : purchaseOrder.getPaymentInformation().getSharePrice().toString();
        String total_price = purchaseOrder.getPaymentInformation().getTotalPrice().toString().replace(".",",");
        String discount_percentage = purchaseOrder.getPaymentInformation().getDiscountPercentage().toString().replace(".",",");
        String total_final_price = purchaseOrder.getPaymentInformation().getTotalFinalPrice().toString().replace(".", ",");

        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setGroupingUsed(false);

        String freight = decimalFormat.format(purchaseOrder.getPaymentInformation().getFreight());


        String form_payment = purchaseOrder.getPaymentInformation().getFormPayment().getDescription();
        String input_price = purchaseOrder.getPaymentInformation().getInputPrice() == null ? "" : purchaseOrder.getPaymentInformation().getInputPrice().toString().replace(".",",");

        /** Delivery **/
        String place_delivery = purchaseOrder.getDeliveryInformation().getPlace();
        String street_delivery = purchaseOrder.getDeliveryInformation().getStreet();
        String neighborhood_delivery = purchaseOrder.getDeliveryInformation().getNeighborhood();
        String number_delivery = purchaseOrder.getDeliveryInformation().getNumber();
        String receptor_name_delivery = purchaseOrder.getDeliveryInformation().getReceptorName();
        String city = purchaseOrder.getDeliveryInformation().getCity();
        String zip_code = purchaseOrder.getDeliveryInformation().getZipCode();
        String cnpj = purchaseOrder.getDeliveryInformation().getCnpj()== null ? " " : purchaseOrder.getDeliveryInformation().getCnpj();


        map.put("img", img);
        map.put("corporate_name",corporate_name);
        map.put("document_supplier",document_supplier);
        map.put("number_request",number_request);
        map.put("number_budget",number_budget);
        map.put("date_budget",date_budget);
        map.put("place_delivery",place_delivery);
        map.put("street_delivery",street_delivery);
        map.put("neighborhood_delivery",neighborhood_delivery);
        map.put("number_delivery",number_delivery);
        map.put("receptor_name_delivery",receptor_name_delivery);

        map.put("city",city);
        map.put("zip_code",zip_code);

        map.put("cnpj",cnpj);

        map.put("mean_payment",mean_payment);
        map.put("date_first_installment",date_first_installment);
        map.put("date_last_installment",date_last_installment);
        map.put("date_input",date_input);
        map.put("expiration_date",expiration_date);
        map.put("share_price",share_price.replace(".", ","));
        map.put("total_price",total_price.replace(".", ","));
        map.put("discount_percentage",discount_percentage);
        map.put("total_final_price",total_final_price.replace(".", ","));
        map.put("date_purchase_request",date_purchase_request);
        map.put("form_payment",form_payment);
        map.put("input_price",input_price.replace(".",","));
        map.put("freight",freight.replace(".",","));

        return map;
    }

}
