package com.purchasing.service;

import com.purchasing.dao.CategoryDAO;
import com.purchasing.dao.ProductDAO;
import com.purchasing.dao.RequestDeliveredDAO;
import com.purchasing.dao.UnitDAO;
import com.purchasing.entity.Product;
import com.purchasing.entity.RequestDelivered;
import com.purchasing.service.impl.ProductService;
import com.purchasing.support.date.Conversor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public class ProductServiceImpl implements ProductService {

    @Inject private ProductDAO productDAO;
    @Inject private UnitDAO unitDAO;
    @Inject private CategoryDAO categoryDAO;
    @Inject private RequestDeliveredDAO requestDeliveredDAO;

    @Override
    public Product save(Product product) {
        Product productSaved = new Product();
        productSaved = productDAO.save(product);
        return productSaved;
    }

    @Override
    public void delete(Product product) {
        if (product != null){
            productDAO.delete(product);
        }
    }

    @Override
    public List<Product> search(String text) {
        String search = text == null ? "" : text;
        List<Product> products = productDAO.search(search);
        return products;
    }

    @Override
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<Product> products = productDAO.pagination(search,iDisplayStart,iDisplayLength);
        List<Object[]> productsList = new ArrayList<>();
        for (Product product : products){
            String colCode = product.getId().toString() ;
            String colDescription = product.getDescription();
            String colModel = product.getModel() ;
            String colMark = product.getMark();
            String buttonView = "<a onclick='viewProduct("+product.getId()+")' ><span class=\"fa fa-eye btn btn-default btn-xs\"></span></a>";
            String buttonEdit = "<a href=/purchasing/produto/editar/"+product.getId()+"><span class=\"fa fa-edit btn btn-default btn-xs\"></span></a>";
            String buttonRemove = "<a onclick='confirmDetele("+product.getId()+")'><span class=\"fa fa-trash-o btn btn-default btn-xs\"></span></a>";
            String buttonInformationPurchase = "<a href=/purchasing/produto/visualizar/informacao/compra/"+product.getId()+" ><span class=\"fa fa-plus btn btn-default btn-xs\"></span></a>";
            String [] row = {
                    colCode,
                    colDescription,
                    colModel,
                    colMark,
                    buttonView,
                    buttonEdit,
                    buttonRemove,
                    buttonInformationPurchase
            };
            productsList.add(row);
        }
        return productsList;
    }

    @Override
    public Integer totalPagination(String sSearch) {
        String search = sSearch == null ? "" : sSearch;
        Integer total= 0;
        total = productDAO.totalPagination(search);
        return total;
    }

    @Override
    public Product searchById(Product product) {
        return productDAO.findById(Product.class,product.getId());
    }

    @Override
    public List<Object[]> findPaginationInformationPurchase(Product product, String sSearch, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<RequestDelivered> requestsDelivered = requestDeliveredDAO.paginationByProduct(product, search, iDisplayStart, iDisplayLength);
        List<Object[]> requestDeliveredList = new ArrayList<>();

        for (RequestDelivered requestDelivered : requestsDelivered){

            String colDate = Conversor.converterDateTimeInString(requestDelivered.getOrderRequest().getPurchaseOrder().getDateGenerate());
            String colSupplier = requestDelivered.getOrderRequest().getBudgetQuotation().getBudget().getSupplier().getPerson().getName();
            String colQuantity = requestDelivered.getOrderRequest().getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getQuantity().toString().replace(".",",");
            String colUnitPrice = requestDelivered.getOrderRequest().getBudgetQuotation().getUnityPrice().toString().replace(".",",");
            String colDeliveredQuantity = requestDelivered.getQuantity().toString().replace(".",",");
            String colCostCenter = requestDelivered.getOrderRequest().getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getSolicitation().getCostCenter().getDescription();

            String [] row = {
                    colDate,
                    colSupplier,
                    colQuantity,
                    colUnitPrice,
                    colDeliveredQuantity,
                    colCostCenter
            };
            requestDeliveredList.add(row);
        }
        return requestDeliveredList;
    }

    @Override
    public Integer totalPaginationInformationPurchase(Product product, String sSearch) {
        String search = sSearch == null ? "" : sSearch;
        Integer total= 0;
        total = requestDeliveredDAO.totalPaginationByProduct(product,search);
        return total;
    }

}
