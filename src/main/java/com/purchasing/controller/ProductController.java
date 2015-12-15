package com.purchasing.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import com.purchasing.annotation.Admin;
import com.purchasing.annotation.Analyst;
import com.purchasing.annotation.Purchaser;
import com.purchasing.entity.Product;
import com.purchasing.service.impl.CategoryService;
import com.purchasing.service.impl.ProductService;
import com.purchasing.service.impl.UnitService;
import com.purchasing.support.datatable.DataTableModel;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * @author vanessa
 */
@Controller
@Path("/produto")
public class ProductController {
    private Result result;
    private Validator validator;

    private ProductService productService;
    private UnitService unitService;
    private CategoryService categoryService;

    @Deprecated ProductController(){};

     @Inject
     public ProductController(Result result, Validator validator, ProductService productService, UnitService unitService, CategoryService categoryService){
         this.result = result;
         this.validator = validator;
         this.productService = productService;
         this.unitService = unitService;
         this.categoryService = categoryService;
     }

    @Purchaser
    @Analyst
    @Admin
    @Path("")
    public void list() {
         result.include("controller", this.getClass().toString());
     }

    @Purchaser
    @Analyst
    @Admin
    @Path("/formulario")
    public void form() {
        result.include("units",unitService.findAllOrderDescription());
        result.include("categories",categoryService.findAllOrderName());
        result.include("controller", this.getClass().toString());
    }

    @Purchaser
    @Analyst
    @Admin
    @Path("/informacao/compra")
    public void viewInformationPurchase(){
    }

    @Post("/salvar")
    public void save(@Valid Product product){
        validator.ensure(product.getCategory().getId() != null , new I18nMessage("product.category","message.notBlank"));
        validator.ensure(product.getUnit().getId() != null, new I18nMessage("product.unit","message.notBlank"));
        validator.onErrorForwardTo(this).form();
        productService.save(product);
        result.redirectTo(this).list();
    }

    @Get("/deletar/{product.id}")
    public void delete(Product product) {
        productService.delete(product);
        result.use(Results.json()).withoutRoot().from(true).serialize();
    }

    @Get("/editar/{product.id}")
    public void edit(Product product){
           Product productFound = productService.searchById(product);
           result.include("product",productFound);
           result.redirectTo(this).form();
    }

    @Get("/visualizar/informacao/compra/{product.id}")
    public void visualizeInformationPurchase(Product product){
        Product productFound = productService.searchById(product);
        result.include("product",productFound);
        result.redirectTo(this).viewInformationPurchase();
    }

    @Get("/pesquisar/{product.id}/json")
    public void searchById(Product product){
        Product productFound = productService.searchById(product);
        if (productFound != null){
            result.use(Results.json()).withoutRoot().from(productFound).include("category").include("unit").serialize();
        }else{
            result.use(Results.json()).withoutRoot().from(false).serialize();
        }
    }

    @Get("/paginar")
    public void pagination(String sSearch, String sEcho, int iDisplayStart, int iDisplayLength){
        List<Object[]> productObjects = productService.findPagination(sSearch,iDisplayStart,iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
            dataTableModel.setsEcho(sEcho);
            dataTableModel.setiTotalRecords(productService.totalPagination(sSearch));
            dataTableModel.setiTotalDisplayRecords(productService.totalPagination(sSearch));
            dataTableModel.setAaData(productObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }

    @Get("/paginar/informacao/pagamento/{product.id}")
    public void paginationRequestDeliveredByProduct(Product product,String sSearch, String sEcho, int iDisplayStart, int iDisplayLength){
        List<Object[]> productObjects = productService.findPaginationInformationPurchase(product, sSearch, iDisplayStart,iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
        dataTableModel.setsEcho(sEcho);
        dataTableModel.setiTotalRecords(productService.totalPaginationInformationPurchase(product,sSearch));
        dataTableModel.setiTotalDisplayRecords(productService.totalPaginationInformationPurchase(product,sSearch));
        dataTableModel.setAaData(productObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }

    @Post("/pesquisar/json")
    public void search(String text){
        List<Product> products = productService.search(text);
        result.use(Results.json()).withoutRoot().from(products).include("category").include("unit").serialize();
    }

}
