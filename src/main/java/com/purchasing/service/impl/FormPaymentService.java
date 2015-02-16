package com.purchasing.service.impl;

import com.purchasing.entity.FormPayment;

import java.util.List;

/**
 * @author vanessa
 */
public interface FormPaymentService {

    public FormPayment save(FormPayment formPayment);
    public void delete(FormPayment formPayment);
    public FormPayment searchById(FormPayment formPayment);
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);
}
