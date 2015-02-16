package com.purchasing.service;

import com.purchasing.dao.CategoryDAO;
import com.purchasing.dao.ProductDAO;
import com.purchasing.dao.UnitDAO;
import com.purchasing.entity.Product;
import com.purchasing.service.impl.ProductService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public class ProductServiceImpl implements ProductService {

    @Inject private ProductDAO productDAO;
    @Inject  private UnitDAO unitDAO;
    @Inject  private CategoryDAO categoryDAO;

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
            String [] row = {
                    colCode,
                    colDescription,
                    colModel,
                    colMark,
                    buttonView,
                    buttonEdit,
                    buttonRemove
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

}
