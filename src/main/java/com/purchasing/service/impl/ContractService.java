package com.purchasing.service.impl;

import com.purchasing.entity.Contract;
import com.purchasing.entity.RenewalContract;

import java.util.List;

/**
 * @author vanessa
 */
public interface ContractService {

    public void save(Contract contract);
    public void delete(Contract contract);
    public Contract searchById(Contract contract);
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);

    /* Renewal contract  */
    public void saveRenewal(RenewalContract renewalContract);
    public void deleteRenewal(RenewalContract renewalContract);
    public RenewalContract searchRenewalById(RenewalContract renewalContract);
    public List<RenewalContract> findRenewalByContract(Contract contract);
}
