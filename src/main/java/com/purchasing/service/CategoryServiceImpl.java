package com.purchasing.service;

import com.purchasing.dao.CategoryDAO;
import com.purchasing.entity.Category;
import com.purchasing.service.impl.CategoryService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public class CategoryServiceImpl implements CategoryService {

    @Inject
    private CategoryDAO categoryDAO;

    @Override
    public Category save(Category category) {
        Category categorySaved = categoryDAO.save(category);
        return categorySaved;
    }

    @Override
    public void delete(Category category) {
        if (category != null){
            categoryDAO.delete(category);
        }
    }

    @Override
    public Category searchById(Category category) {
        Category categoryFound = new Category();
        if (category != null && category.getId() != null){
            categoryFound = categoryDAO.findById(Category.class,category.getId());
        }
        return categoryFound;
    }

    @Override
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<Category> categories = categoryDAO.pagination(search,iDisplayStart,iDisplayLength);
        List<Object[]> categoriesList= new ArrayList<>();
        for (Category category : categories){
            String colDescription = category.getDescription();
            String buttonEdit = "<a onclick=edit("+ category.getId() + ")><span class=\"fa fa-edit btn btn-default btn-xs\"></span></a>";
            String buttonRemove = "<a onclick='confirmDetele("+category.getId()+")' ><span class=\"fa fa-trash-o btn btn-default btn-xs\"></span></a>";
            String [] row = {
                    colDescription,
                    buttonEdit,
                    buttonRemove
            };
            categoriesList.add(row);
        }
        return categoriesList;
    }

    @Override
    public Integer totalPagination(String sSearch) {
        String search = sSearch == null ? "" : sSearch;
        Integer total= 0;
        total = categoryDAO.totalPagination(search);
        return total;
    }

    @Override
    public List<Category> findAllOrderName() {
        return categoryDAO.findAllOrderName();
    }
}
