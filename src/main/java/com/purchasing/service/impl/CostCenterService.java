package com.purchasing.service.impl;

import com.purchasing.entity.Company;
import com.purchasing.entity.CostCenter;

import java.util.List;

/**
 * @author vanessa
 */
public interface CostCenterService {

    public CostCenter save(CostCenter costCenter);
    public void delete(CostCenter costCenter);
    public CostCenter searchById(CostCenter costCenter);
    public List<CostCenter> searchByCompany(Company company);
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);

    public List<Company> findAllCompany();
}
