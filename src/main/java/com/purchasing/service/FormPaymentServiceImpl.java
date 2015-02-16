package com.purchasing.service;

import com.purchasing.dao.FormPaymentDAO;
import com.purchasing.entity.FormPayment;
import com.purchasing.service.impl.FormPaymentService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public class FormPaymentServiceImpl implements FormPaymentService {

    @Inject private FormPaymentDAO formPaymentDAO;

    @Override
    public FormPayment save(FormPayment formPayment) {
        FormPayment formPaymentSaved = formPaymentDAO.save(formPayment);
        return formPaymentSaved;
    }

    @Override
    public void delete(FormPayment formPayment) {
        if (formPayment != null){
            formPaymentDAO.delete(formPayment);
        }
    }

    @Override
    public FormPayment searchById(FormPayment formPayment) {
        FormPayment formPaymentFound = new FormPayment();
        if (formPayment.getId() != null){
            formPaymentFound = formPaymentDAO.findById(FormPayment.class,formPayment.getId());
        }
        return formPaymentFound;
    }

    @Override
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<FormPayment> formPayments = formPaymentDAO.pagination(search,iDisplayStart,iDisplayLength);
        List<Object[]> formPaymentsList = new ArrayList<>();
        for (FormPayment formPayment : formPayments){
            String colDescription = formPayment.getDescription();
            String buttonEdit = "<a onclick=edit(" + formPayment.getId() + ")><span class=\"fa fa-edit btn btn-default btn-xs\"></span></a>";
            String buttonRemove = "<a onclick='confirmDetele("+formPayment.getId()+")'><span class=\"fa fa-trash-o btn btn-default btn-xs\"></span></a>";
            String [] row = {
                    colDescription,
                    buttonEdit,
                    buttonRemove
            };
            formPaymentsList.add(row);
        }
        return formPaymentsList;
    }

    @Override
    public Integer totalPagination(String sSearch) {
        String search = sSearch == null ? "" : sSearch;
        Integer total= 0;
        total = formPaymentDAO.totalPagination(search);
        return total;
    }

}
