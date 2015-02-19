package com.purchasing.service.impl;

import com.purchasing.entity.Unit;

import java.util.List;

/**
 * @author vanessa
 */
public interface UnitService {

    public Unit save(Unit unit);
    public void delete(Unit unit);
    public Unit searchById(Unit unit);
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);

    public List<Unit> findAllOrderDescription();
}
