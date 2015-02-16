package com.purchasing.service;

import com.purchasing.dao.CompanyDAO;
import com.purchasing.dao.CostCenterDAO;
import com.purchasing.entity.Company;
import com.purchasing.entity.CostCenter;
import com.purchasing.service.impl.CostCenterService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public class CostCenterServiceImpl implements CostCenterService {

    @Inject private CostCenterDAO costCenterDAO;
    @Inject private CompanyDAO companyDAO;

    @Override
    public CostCenter save(CostCenter costCenter) {
        CostCenter costCenterSaved = costCenterDAO.save(costCenter);
        return costCenterSaved;
    }

    @Override
    public void delete(CostCenter costCenter) {
        if (costCenter != null && costCenter.getId() != null){
            costCenterDAO.delete(costCenter);
        }
    }

    @Override
    public CostCenter searchById(CostCenter costCenter) {
        CostCenter costCenterFound = new CostCenter();
        if (costCenter.getId() != null){
            costCenterFound = costCenterDAO.findById(CostCenter.class,costCenter.getId());
        }
        return costCenterFound;
    }

    @Override
    public List<CostCenter> searchByCompany(Company company) {
        List<CostCenter>costCenters = new ArrayList<>();
        if (company != null){
            costCenters = costCenterDAO.findByCompany(company);
        }
        return costCenters;
    }

    @Override
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<CostCenter> costCenters = costCenterDAO.pagination(search,iDisplayStart,iDisplayLength);
        List<Object[]> costCentersList= new ArrayList<>();
        for (CostCenter costCenter : costCenters){
            String colCode = costCenter.getCode();
            String colDescription = costCenter.getDescription();
            String colCompany = costCenter.getCompany().getCompanyName();
            String buttonEdit = "<a onclick=edit(" + costCenter.getId() + ")><span class=\"fa fa-edit btn btn-default btn-xs\"></span></a>";
            String buttonRemove = "<a onclick='confirmDetele("+costCenter.getId()+")' ><span class=\"fa fa-trash-o btn btn-default btn-xs\"></span></a>";
            String [] row = {
                    colCode,
                    colDescription,
                    colCompany,
                    buttonEdit,
                    buttonRemove
            };
            costCentersList.add(row);
        }
        return costCentersList;
    }

    @Override
    public Integer totalPagination(String sSearch) {
        String search = sSearch == null ? "" : sSearch;
        Integer total= 0;
        total = costCenterDAO.totalPagination(search);
        return total;
    }

}
