package com.purchasing.printer;

import com.purchasing.dao.BudgetDAO;
import com.purchasing.dao.PurchaseOrderDAO;
import com.purchasing.entity.Approval;
import com.purchasing.entity.Budget;
import com.purchasing.entity.PurchaseOrder;
import com.purchasing.entity.Reception;
import com.purchasing.enumerator.TypePersonEnum;
import com.purchasing.printer.base.BasePrinter;
import com.purchasing.printer.impl.PrinterImpl;
import com.purchasing.service.impl.PurchaseOrderService;
import com.purchasing.support.date.Conversor;
import com.purchasing.support.purchaseOrder.printer.PurchaseOrderViewPrinter;
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
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Vanessa on 4/28/15.
 */
public class PurchaseOrderPrinter extends PrinterImpl implements BasePrinter {

    @Inject private PurchaseOrderDAO purchaseOrderDAO;
    @Inject private BudgetDAO budgetDAO;

    @Override
    public File generatePurchaseOrder(PurchaseOrder purchaseOrder, Reception reception, PurchaseOrderService purchaseOrderService) {
        String url = "/jasper/purchase_order.jrxml";

        List<PurchaseOrderViewPrinter>  purchaseOrderViewPrinters  = new ArrayList<>();
        List<PurchaseOrderViewPrinter> purchaseOrderViewPrinterArrayList = new ArrayList<>();

        purchaseOrderViewPrinters = new PurchaseOrderViewPrinter().generateList(reception.getRequestDelivereds());

        purchaseOrderViewPrinterArrayList = purchaseOrderService.groupByCostCenter(purchaseOrderViewPrinters);
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(purchaseOrderViewPrinterArrayList);

        purchaseOrder = purchaseOrderDAO.findById(PurchaseOrder.class, purchaseOrder.getId());

        InputStream inputStream = this.getClass().getResourceAsStream(url);
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(inputStream), getMap(purchaseOrder,reception), jrBeanCollectionDataSource);
        } catch (JRException e) {
            e.printStackTrace();
        }
        String nameFile = "ordem_compra";
        this.printer(jasperPrint, nameFile);
        File file = this.download(nameFile);
        return file;
    }

    public Map getMap(PurchaseOrder purchaseOrder, Reception reception) {
        Map map = new HashMap();
        Image img = new ImageIcon(getClass().getResource("/jasper/img/img.jpg")).getImage();

        String corporate_name = purchaseOrder.getBudget().getSupplier().getPerson().getName();
        String document_supplier = (purchaseOrder.getBudget().getSupplier().getPerson().getTypePerson().equals(TypePersonEnum.JuristicPerson)) ? purchaseOrder.getBudget().getSupplier().getPerson().getCnpj(): purchaseOrder.getBudget().getSupplier().getPerson().getCpf();
        String name_contact = purchaseOrder.getBudget().getSupplier().getContact().getContactName() == null ? "" : purchaseOrder.getBudget().getSupplier().getContact().getContactName();
        String phone_contact = "";

        String phone = purchaseOrder.getBudget().getSupplier().getContact().getPhone();
        String cell_phone = purchaseOrder.getBudget().getSupplier().getContact().getCellPhone();

        if (phone == null || cell_phone != null){
            phone_contact = cell_phone;
        }if (phone != null || cell_phone == null){
            phone_contact = phone;
        }if (phone != null || cell_phone != null){
            phone_contact = phone;
        }

        String number_request = purchaseOrder.getId().toString();
        String date_purchase_request = purchaseOrder.getDateGenerate() == null ? "" : Conversor.converterDateTimeInString(purchaseOrder.getDateGenerate());

        /** Payment **/
        String mean_payment = reception.getPaymentInformation().getMeanPayment().getDescription();
        String date_first_installment = reception.getPaymentInformation().getDateFirstInstallment()== null ? "" :  Conversor.converterDateInString(reception.getPaymentInformation().getDateFirstInstallment());
        String date_last_installment = reception.getPaymentInformation().getDateLastInstallment() == null ? "" : Conversor.converterDateInString(reception.getPaymentInformation().getDateLastInstallment());
        String date_input = reception.getPaymentInformation().getDateInput() == null ? "" : Conversor.converterDateInString(reception.getPaymentInformation().getDateInput());
        String expiration_date = reception.getPaymentInformation().getExpirationDate() == null ? "" : Conversor.converterDateInString(reception.getPaymentInformation().getExpirationDate());
        String share_price = reception.getPaymentInformation().getSharePrice() == null ? "" : reception.getPaymentInformation().getSharePrice().toString();
        String total_price = reception.getPaymentInformation().getTotalPrice().toString();
         DecimalFormat decimalFormat = new DecimalFormat();
                                        decimalFormat.setMaximumFractionDigits(2);
                                        decimalFormat.setMinimumFractionDigits(2);
                                        decimalFormat.setGroupingUsed(false);
        String discount_percentage = decimalFormat.format((reception.getPaymentInformation().getTotalPrice().multiply(new BigDecimal(reception.getPaymentInformation().getDiscountPercentage()))).divide(new BigDecimal(100)));
        String total_final_price = reception.getPaymentInformation().getTotalFinalPrice().toString();

        String form_payment = reception.getPaymentInformation().getFormPayment().getDescription();
        String input_price = reception.getPaymentInformation().getInputPrice() == null ? "" : purchaseOrder.getPaymentInformation().getInputPrice().toString();

        /** Budget **/
        List<Budget> budgets = budgetDAO.findByQuotationOrderPaymentInformation(purchaseOrder.getBudget().getQuotation());
        Integer size = budgets.size();

        String supplier_one = "";
        String total_price_one = "";

        String supplier_two = "";
        String total_price_two = "";

        String supplier_three = "";
        String total_price_three = "";

        switch (size){
            case 1:
                 supplier_one = budgets.get(0).getSupplier().getPerson().getName();
                 total_price_one = budgets.get(0).getPaymentInformationBudgets().get(0).getPaymentInformation().getTotalPrice().toString().replace(".",",");
                break;

            case 2:
                supplier_one = budgets.get(0).getSupplier().getPerson().getName();
                total_price_one = budgets.get(0).getPaymentInformationBudgets().get(0).getPaymentInformation().getTotalPrice().toString().replace(".",",");

                supplier_two = budgets.get(1).getSupplier().getPerson().getName();
                total_price_two = budgets.get(1).getPaymentInformationBudgets().get(0).getPaymentInformation().getTotalPrice().toString().replace(".",",");
                break;

            case 3:
                supplier_one = budgets.get(0).getSupplier().getPerson().getName();
                total_price_one = budgets.get(0).getPaymentInformationBudgets().get(0).getPaymentInformation().getTotalPrice().toString().replace(".", ",");
                supplier_two = budgets.get(1).getSupplier().getPerson().getName();
                total_price_two =budgets.get(1).getPaymentInformationBudgets().get(0).getPaymentInformation().getTotalPrice().toString().replace(".", ",");
                supplier_three = budgets.get(2).getSupplier().getPerson().getName();
                total_price_three = budgets.get(2).getPaymentInformationBudgets().get(0).getPaymentInformation().getTotalPrice().toString().replace(".",",");
                break;

            default:
                supplier_one = budgets.get(0).getSupplier().getPerson().getName();
                total_price_one = budgets.get(0).getPaymentInformationBudgets().get(0).getPaymentInformation().getTotalPrice().toString().replace(".", ",");
                supplier_two = budgets.get(1).getSupplier().getPerson().getName();
                total_price_two =budgets.get(1).getPaymentInformationBudgets().get(0).getPaymentInformation().getTotalPrice().toString().replace(".",",");
                supplier_three = budgets.get(2).getSupplier().getPerson().getName();
                total_price_three = budgets.get(2).getPaymentInformationBudgets().get(0).getPaymentInformation().getTotalPrice().toString().replace(".",",");
        }

        /** Approval **/
        Approval approval = purchaseOrder.getApproval();
        String name_user_approval = "";
        String date_approval= "";

        if (approval.getSecondApproval() == null && approval.getDateSecondApproval() == null){
            name_user_approval = approval.getUserFirstApproval();
            date_approval = Conversor.converterDateTimeInString(approval.getDateFirstApproval());
        }else if (approval.getThirdApproval() == null && approval.getDateThirdApproval() == null){
            name_user_approval = approval.getUserSecondApproval();
            date_approval = Conversor.converterDateTimeInString(approval.getDateSecondApproval());
        }else if (approval.getFourthApproval() == null && approval.getDateFourthApproval() == null){
            name_user_approval = approval.getUserThirdApproval();
            date_approval = Conversor.converterDateTimeInString(approval.getDateThirdApproval());
        }else if (approval.getFourthApproval() != null && approval.getDateFourthApproval() != null){
            name_user_approval = approval.getUserFourthApproval();
            date_approval = Conversor.converterDateTimeInString(approval.getDateFourthApproval());
        }

        map.put("img", img);
        map.put("corporate_name",corporate_name);
        map.put("document_supplier",document_supplier);
        map.put("name_contact",name_contact);
        map.put("phone_contact",phone_contact);
        map.put("number_request",number_request);

        map.put("mean_payment",mean_payment);
        map.put("date_first_installment",date_first_installment);
        map.put("date_last_installment",date_last_installment);
        map.put("date_input",date_input);
        map.put("expiration_date",expiration_date);
        map.put("share_price",share_price.replace(".", ","));
        map.put("total_price",total_price.replace(".", ","));
        map.put("discount_percentage",discount_percentage.replace(".",","));
        map.put("total_final_price",total_final_price.replace(".", ","));
        map.put("date_purchase_request",date_purchase_request);
        map.put("form_payment",form_payment);
        map.put("input_price",input_price.replace(".",","));

        map.put("supplier_one",supplier_one);
        map.put("total_price_one",total_price_one);
        map.put("supplier_two",supplier_two);
        map.put("total_price_two",total_price_two);
        map.put("supplier_three",supplier_three);
        map.put("total_price_three",total_price_three);

        map.put("name_user_approval",name_user_approval);
        map.put("date_approval",date_approval);

        return map;
    }

}


