package com.purchasing.service.impl;

import com.purchasing.entity.Category;

import java.util.List;

/**
 * @author vanessa
 */
public interface CategoryService {

    public Category save(Category category);
    public void delete(Category category);
    public Category searchById(Category category);
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);
    public List<Category> findAllOrderName();
}
