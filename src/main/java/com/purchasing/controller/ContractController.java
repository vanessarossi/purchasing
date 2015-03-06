package com.purchasing.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import com.purchasing.entity.Contract;
import com.purchasing.entity.RenewalContract;
import com.purchasing.service.impl.ContractService;
import com.purchasing.support.datatable.DataTableModel;

import javax.inject.Inject;
import javax.validation.Valid;
import java.io.File;
import java.util.List;

/**
 * @author vanessa
 */
@Controller
@Path("/contrato")
public class ContractController {

    private Result result;
    private Validator validator;

    private ContractService contractService;


    @Deprecated ContractController(){};

    @Inject
    public ContractController(Result result, Validator validator, ContractService contractService){
        this.result = result;
        this.validator = validator;
        this.contractService = contractService;
    }

    @Path({ "", "/" })
    public void list() {
        result.include("controller", this.getClass().toString());
    }

    @Path("/formulario")
    public void form() {
        result.include("controller", this.getClass().toString());
    }

    @Post("/salvar")
    public void salvar(@Valid Contract contract, UploadedFile uploadedFile) {
        validator.ensure(contract.getSupplier().getId() != null, new I18nMessage("contract.supplier", "message.error.null"));
        validator.onErrorForwardTo(this).form();
        contractService.save(contract,uploadedFile);
        result.redirectTo(this).list();
    }

    @Get("/deletar/{contract.id}")
    public void delete(Contract contract) {
        contractService.delete(contract);
        result.redirectTo(this).list();
    }

    @Get("/editar/{contract.id}")
    public void edit(Contract contract){
        Contract contractFound = contractService.searchById(contract);
        List<RenewalContract> renewalContracts = contractService.findRenewalByContract(contractFound);
        result.include("contract",contractFound);
        result.include("renewalContracts",renewalContracts);
        result.redirectTo(this).form();
    }

    @Get("/pesquisar/{contract.id}/json")
    public void searchById(Contract contract){
        Contract contractFound = contractService.searchById(contract);
        if (contractFound != null){
            result.use(Results.json()).withoutRoot().from(contractFound).include("supplier").include("supplier.person").serialize();
        }else{
            result.use(Results.json()).withoutRoot().from(false).serialize();
        }
    }

    @Get("/paginar")
    public void pagination(String sSearch, String sEcho, int iDisplayStart, int iDisplayLength){
           List<Object[]> contractObjects = contractService.findPagination(sSearch,iDisplayStart,iDisplayLength);
           DataTableModel dataTableModel = new DataTableModel();
               dataTableModel.setsEcho(sEcho);
               dataTableModel.setiTotalRecords(contractService.totalPagination(sSearch));
               dataTableModel.setiTotalDisplayRecords(contractService.totalPagination(sSearch));
               dataTableModel.setAaData(contractObjects.toArray());
           result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }

    @Get("/download/{contract.id}")
    public File download (Contract contract){
        if(contract.getId() == null){
            result.include("message","Ocorreu um para realizar o download do contrato").redirectTo(this).list();
        }
        return contractService.download(contract);
    }

    /** renovação **/

    @Post("/renovacao/salvar")
    public void salvarRenewal(@Valid RenewalContract renewalContract, UploadedFile renewalUploadedFile) {
        validator.ensure(renewalContract.getContract().getId() != null , new I18nMessage("renewalContract.contract", "message.error.null"));
        validator.onErrorForwardTo(this).form();
        contractService.saveRenewal(renewalContract, renewalUploadedFile);
        result.redirectTo(this).list();
    }

    @Get("/renovacao/deletar/{renewalContract.id}")
    public void deleteRenewal(RenewalContract renewalContract) {
        contractService.deleteRenewal(renewalContract);
        result.redirectTo(this).list();
    }

    @Get("/renovacao/editar/{renewalContract.id}")
    public void editRenewal(RenewalContract renewalContract){
        RenewalContract renewalContractFound = contractService.searchRenewalById(renewalContract);
        result.include("contract",renewalContract);
        result.redirectTo(this).form();
    }

    @Get("/renovacao/download/{renewalContract.id}")
    public File download (RenewalContract renewalContract){
        if(renewalContract.getId() == null){
            result.include("message","Ocorreu um para realizar o download do contrato").redirectTo(this).list();
        }
        return contractService.downloadRenewal(renewalContract);
    }

}
