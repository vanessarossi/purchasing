package com.purchasing.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import com.purchasing.entity.Address;
import com.purchasing.entity.Category;
import com.purchasing.entity.Contact;
import com.purchasing.entity.JuristicPerson;
import com.purchasing.entity.NaturalPerson;
import com.purchasing.entity.Person;
import com.purchasing.entity.State;
import com.purchasing.entity.Supplier;
import com.purchasing.enumerator.TypePersonEnum;
import com.purchasing.service.impl.CategoryService;
import com.purchasing.service.impl.SupplierService;
import com.purchasing.support.datatable.DataTableModel;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author vanessa
 */
@Controller
@Path("/fornecedor")
public class SupplierController {

    private Result result;
    private SupplierService supplierService;
    private CategoryService categoryService;
    private Validator validator;

    @Deprecated SupplierController(){};

    @Inject
    public SupplierController(Result result, HttpSession httpSession, SupplierService supplierService, CategoryService categoryService, Validator validator){
        this.result = result;
        this.supplierService = supplierService;
        this.categoryService = categoryService;
        this.validator = validator;
    }


    @Path("")
    public void list() {
        result.include("controller", this.getClass().toString());
    }

    @Path("/formulario")
    public void form() {
        TypePersonEnum[] typesPerson  = TypePersonEnum.values();
        List<Category> categories = categoryService.findAllOrderName();
        List<State> states = supplierService.findAllStates();
        result.include("typesPerson",typesPerson);
        result.include("categories",categories);
        result.include("states",states);
        result.include("controller", this.getClass().toString());
    }

    @Post("/salvar")
    public void save(@Valid Supplier supplier, @Valid Contact contact , @Valid Address address , @Valid Person person , NaturalPerson naturalPerson , JuristicPerson juristicPerson){
        validator.ensure(supplier.getCategory().getId() != null , new I18nMessage("supplier.category","message.notBlank"));
        validator.ensure(address.getState().getId() != null, new I18nMessage("address.state","message.notBlank"));

        validator.addIf(person.getTypePerson().equals(TypePersonEnum.NaturalPerson) &&  naturalPerson.getCpf() == null,new I18nMessage("naturalPerson.cpf","message.notBlank"));
        validator.addIf(person.getTypePerson().equals(TypePersonEnum.NaturalPerson) &&  naturalPerson.getRg() == null,new I18nMessage("naturalPerson.rg","message.notBlank"));
        validator.addIf(person.getTypePerson().equals(TypePersonEnum.NaturalPerson) &&  naturalPerson.getEmittingOrgan() == null,new I18nMessage("naturalPerson.emittingOrgan","message.notBlank"));

        validator.addIf(person.getTypePerson().equals(TypePersonEnum.JuristicPerson) && juristicPerson.getCompanyName() == null,new I18nMessage("juristicPerson.companyName","message.notBlank"));
        validator.addIf(person.getTypePerson().equals(TypePersonEnum.JuristicPerson) && juristicPerson.getCnpj() == null,new I18nMessage("juristicPerson.cnpj","message.notBlank"));

        validator.onErrorForwardTo(this).form();
        person.setNaturalPerson(naturalPerson);
        person.setJuristicPerson(juristicPerson);
        supplier.setPerson(person);
        supplier.setAddress(address);
        supplier.setContact(contact);
        supplierService.save(supplier);
        result.redirectTo(this).list();
    }

    @Get("/deletar/{supplier.id}")
    public void delete(Supplier supplier) {
       supplierService.delete(supplier);
        result.use(Results.json()).withoutRoot().from(true).serialize();
    }

    @Get("/editar/{supplier.id}")
    public void edit(Supplier supplier){
        Supplier supplierFound = supplierService.searchById(supplier);
        result.include("supplier",supplierFound);
        result.include("contact",supplierFound.getContact());
        result.include("address",supplierFound.getAddress());
        result.include("person",supplierFound.getPerson());
        if (supplierFound.getPerson().getTypePerson().equals(TypePersonEnum.JuristicPerson)){
            result.include("juristicPerson",supplierFound.getPerson());
        }else{
            result.include("naturalPerson",supplierFound.getPerson());
        }
        result.redirectTo(this).form();
    }


    @Get("/paginar")
    public void pagination(String sSearch, String sEcho, int iDisplayStart, int iDisplayLength){
        List<Object[]> supplierObjects = supplierService.findPagination(sSearch,iDisplayStart,iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
            dataTableModel.setsEcho(sEcho);
            dataTableModel.setiTotalRecords(supplierService.totalPagination(sSearch));
            dataTableModel.setiTotalDisplayRecords(supplierService.totalPagination(sSearch));
            dataTableModel.setAaData(supplierObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }

    @Get("/pesquisar/{supplier.id}/json")
    public void searchById(Supplier supplier){
        Supplier supplierFound = supplierService.searchById(supplier);
        result.use(Results.json()).withoutRoot().from(supplierFound).include("person").include("person.typePerson").include("contact").include("address").include("address.state").include("category").serialize();
    }

    @Post("/pesquisar/cnpj/json")
    public void searchByCNPJ(String cnpj){
        JuristicPerson juristicPerson = supplierService.checkByCPNJ(cnpj);
        if (juristicPerson != null){
            result.use(Results.json()).withoutRoot().from(juristicPerson).serialize();
        }else{
            result.use(Results.json()).withoutRoot().from(false).serialize();
        }
    }

    @Post("/pesquisar/cpf/json")
    public void searchByCPF(String cpf){
        NaturalPerson naturalPerson = supplierService.checkByCPF(cpf);
        if (naturalPerson != null){
            result.use(Results.json()).withoutRoot().from(naturalPerson).serialize();
        }else{
            result.use(Results.json()).withoutRoot().from(false).serialize();
        }
    }
}
