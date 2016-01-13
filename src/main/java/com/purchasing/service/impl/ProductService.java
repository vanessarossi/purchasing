package com.purchasing.service.impl;

import com.purchasing.entity.Product;

import java.util.List;

/**
 * @author vanessa
 */
public interface ProductService {

    public Product save(Product product);
    public void delete (Product product);
    public List<Product> search(String text);
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);
    public Product searchById(Product product);

    public List<Object[]> findPaginationInformationPurchase(Product product,String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPaginationInformationPurchase(Product product,String sSearch);
    public List<Product> searchSimilarProduct(Product product);
}
