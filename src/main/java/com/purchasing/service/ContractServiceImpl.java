package com.purchasing.service;

import br.com.caelum.vraptor.observer.upload.UploadedFile;
import com.purchasing.dao.ContractDAO;
import com.purchasing.dao.RenewalContractDAO;
import com.purchasing.entity.Contract;
import com.purchasing.entity.RenewalContract;
import com.purchasing.service.impl.ContractService;
import com.purchasing.support.date.Conversor;
import com.purchasing.support.upload.Document;

import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public class ContractServiceImpl implements ContractService {

    @Inject private ContractDAO contractDAO;
    @Inject private RenewalContractDAO  renewalContractDAO;
    @Inject private Document document;


    @Override
    public void save(Contract contract, UploadedFile uploadedFile) {
        String fileName = document.uploadContract(uploadedFile,contract);
        contract.setFileName(fileName);
        contractDAO.save(contract);
    }

    @Override
    public void delete(Contract contract) {
        contract = contractDAO.findById(Contract.class,contract.getId());
        document.downloadContract(contract);
        contractDAO.delete(contract);
    }

    @Override
    public File download(Contract contract) {
        return document.downloadContract(contract);
    }

    @Override
    public Contract searchById(Contract contract) {
        return contractDAO.findById(Contract.class,contract.getId());
    }

    @Override
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
            List<Contract> contracts = contractDAO.pagination(search,iDisplayStart,iDisplayLength);
            List<Object[]> contractList = new ArrayList<>();
            for (Contract contract : contracts){
                String colSupplier = contract.getSupplier().getPerson().getName();
                String colInitialDate = Conversor.converterDateInString(contract.getInitialDate());
                String colFinalDate = Conversor.converterDateInString(contract.getFinalDate());
                String buttonDownload = "<a href=/purchasing/contrato/download/"+contract.getId()+"><span class=\"fa fa-print btn btn-default btn-xs\"></span></a>";
                String buttonEdit = "<a href=/purchasing/contrato/editar/"+contract.getId()+"><span class=\"fa fa-edit btn btn-default btn-xs\"></span></a>";
                String buttonRemove = "<a onclick='confirmDetele("+contract.getId()+")'><span class=\"fa fa-trash-o btn btn-default btn-xs\"></span></a>";
                String [] row = {
                        colInitialDate,
                        colFinalDate,
                        colSupplier,
                        buttonDownload,
                        buttonEdit,
                        buttonRemove
                };
                contractList.add(row);
            }
        return contractList;
    }

    @Override
    public Integer totalPagination(String sSearch) {
        String search = sSearch == null ? "" : sSearch;
        Integer total= 0;
        total = contractDAO.totalPagination(search);
        return total;
    }


    /* Renewal Contract  */

    @Override
    public void save(RenewalContract renewalContract, UploadedFile uploadedFile) {
        String fileName = document.uploadRenewalContract(uploadedFile, renewalContract);
        renewalContract.setFileName(fileName);
        renewalContractDAO.save(renewalContract);
    }

    @Override
    public void delete(RenewalContract renewalContract) {
        renewalContract = renewalContractDAO.findById(RenewalContract.class, renewalContract.getId());
        document.downloadRenewalContract(renewalContract);
        renewalContractDAO.delete(renewalContract);
    }

    @Override
    public File download(RenewalContract renewalContract) {
        return document.downloadRenewalContract(renewalContract);
    }

    @Override
    public RenewalContract searchById(RenewalContract renewalContract) {
        return renewalContractDAO.findById(RenewalContract.class,renewalContract.getId());
    }

    @Override
    public List<RenewalContract> findByContract(Contract contract) {
        return renewalContractDAO.findByContract(contract);
    }
}
