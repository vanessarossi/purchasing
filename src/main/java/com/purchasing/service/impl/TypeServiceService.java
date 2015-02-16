package com.purchasing.service.impl;

import com.purchasing.entity.TypeService;

import java.util.List;

/**
 * @author vanessa
 */
public interface TypeServiceService {

    public TypeService save(TypeService typeService);
    public void delete(TypeService typeService);
    public TypeService searchById(TypeService typeService);
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);
    public List<TypeService> findAll();
}
