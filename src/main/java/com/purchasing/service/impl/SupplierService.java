package com.purchasing.service.impl;

import com.purchasing.entity.JuristicPerson;
import com.purchasing.entity.NaturalPerson;
import com.purchasing.entity.State;
import com.purchasing.entity.Supplier;

import java.util.List;

/**
 * @author vanessa
 */
public interface SupplierService {

    public Supplier save(Supplier supplier);
    public void delete(Supplier supplier);
    public List<Supplier> search(String text);
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);
    public List<State> findAllStates();
    public Supplier searchById(Supplier supplier);
    public JuristicPerson checkByCPNJ(String cnpj);
    public NaturalPerson checkByCPF(String cpf);
}
