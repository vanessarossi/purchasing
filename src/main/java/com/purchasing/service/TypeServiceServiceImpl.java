package com.purchasing.service;

import com.purchasing.dao.TypeServiceDAO;
import com.purchasing.entity.TypeService;
import com.purchasing.service.impl.TypeServiceService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public class TypeServiceServiceImpl implements TypeServiceService {

    @Inject
    private TypeServiceDAO typeServiceDAO;

    @Override
    public TypeService save(TypeService typeService) {
        TypeService typeServiceSaved = typeServiceDAO.save(typeService);
        return typeServiceSaved;
    }

    @Override
    public void delete(TypeService typeService) {
        if (typeService != null){
            typeServiceDAO.delete(typeService);
        }
    }

    @Override
    public TypeService searchById(TypeService typeService) {
        TypeService typeServiceFound = new TypeService();
        if (typeService.getId() != null){
            typeServiceFound = typeServiceDAO.findById(TypeService.class,typeService.getId());
        }
        return typeServiceFound;
    }

    @Override
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<TypeService> typeServices = typeServiceDAO.pagination(search,iDisplayStart,iDisplayLength);
        List<Object[]> typeServicesList = new ArrayList<>();
        for (TypeService typeService : typeServices){
            String colDescription = typeService.getDescription();
            String buttonEdit = "<a onclick=edit(" + typeService.getId() + ")><span class=\"fa fa-edit btn btn-default btn-xs\"></span></a>";
            String buttonRemove = "<a onclick='confirmDetele("+typeService.getId()+")'><span class=\"fa fa-trash-o btn btn-default btn-xs\"></span></a>";
            String [] row = {
                    colDescription,
                    buttonEdit,
                    buttonRemove
            };
            typeServicesList.add(row);
        }
        return typeServicesList;
       }

    @Override
    public Integer totalPagination(String sSearch) {
        String search = sSearch == null ? "" : sSearch;
        Integer total= 0;
        total = typeServiceDAO.totalPagination(search);
        return total;
    }

    @Override
    public List<TypeService> findAll() {
        return typeServiceDAO.findAll(TypeService.class);
    }
}
