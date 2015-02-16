package com.purchasing.service;

import com.purchasing.entity.Contract;
import com.purchasing.service.impl.ContractService;

import java.io.File;
import java.util.List;

/**
 * @author vanessa
 */
public class ContractServiceImpl implements ContractService {

    @Override
    public Contract save(Contract contract) {
        return null;
    }

    @Override
    public void delete(Contract contract) {

    }

    @Override
    public File download(Contract contract) {
        return null;
    }

    @Override
    public Contract searchById(Contract contract) {
        return null;
    }

    @Override
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        return null;
    }

    @Override
    public Integer totalPagination(String sSearch) {
        return null;
    }
}
