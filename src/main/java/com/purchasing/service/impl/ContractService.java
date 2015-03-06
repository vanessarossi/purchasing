package com.purchasing.service.impl;

import br.com.caelum.vraptor.observer.upload.UploadedFile;
import com.purchasing.entity.Contract;
import com.purchasing.entity.RenewalContract;

import java.io.File;
import java.util.List;

/**
 * @author vanessa
 */
public interface ContractService {

    public void save(Contract contract, UploadedFile uploadedFile);
    public void delete(Contract contract);
    public File download(Contract contract);
    public Contract searchById(Contract contract);
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);

    /* Renewal contract  */
    public void saveRenewal(RenewalContract renewalContract, UploadedFile uploadedFile);
    public void deleteRenewal(RenewalContract renewalContract);
    public File downloadRenewal(RenewalContract renewalContract);
    public RenewalContract searchRenewalById(RenewalContract renewalContract);
    public List<RenewalContract> findRenewalByContract(Contract contract);
}
