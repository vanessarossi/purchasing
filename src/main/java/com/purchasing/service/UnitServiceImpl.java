package com.purchasing.service;

import com.purchasing.dao.UnitDAO;
import com.purchasing.entity.Unit;
import com.purchasing.service.impl.UnitService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public class UnitServiceImpl implements UnitService{

    @Inject
    private UnitDAO unitDAO;

    @Override
    public Unit save(Unit unit) {
        Unit unitSaved = unitDAO.save(unit);
        return null;
    }

    @Override
    public void delete(Unit unit) {
        if (unit.getId() != null){
            unitDAO.delete(unit);
        }
    }

    @Override
    public Unit searchById(Unit unit) {
        Unit unitFound = new Unit();
        if (unit.getId() != null){
            unitFound = unitDAO.findById(Unit.class,unit.getId());
        }
        return unitFound;
    }

    @Override
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<Unit> units = unitDAO.pagination(search,iDisplayStart,iDisplayLength);
        List<Object[]> unitList = new ArrayList<>();
        for (Unit unit : units){
            String colDescription = unit.getDescription();
            String buttonEdit = "<a onclick='edit("+unit.getId()+")'><span class=\"fa fa-edit btn btn-default btn-xs\"></span></a>";
            String buttonRemove = "<a onclick='confirmDetele("+unit.getId()+")'><span class=\"fa fa-trash-o btn btn-default btn-xs\"></span></a>";
            String [] row = {
                    colDescription,
                    buttonEdit,
                    buttonRemove
            };
            unitList.add(row);
        }
        return unitList;
    }

    @Override
    public Integer totalPagination(String sSearch) {
        String search = sSearch == null ? "" : sSearch;
        Integer total= 0;
        total = unitDAO.totalPagination(search);
        return total;
    }

}
