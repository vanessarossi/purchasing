package com.purchasing.service.impl;

import com.purchasing.entity.Contract;

import java.io.File;
import java.util.List;

/**
 * @author vanessa
 */
public interface ContractService {

    public Contract save(Contract contract);
    public void delete(Contract contract);
    public File download(Contract contract);
    public Contract searchById(Contract contract);
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);
}
