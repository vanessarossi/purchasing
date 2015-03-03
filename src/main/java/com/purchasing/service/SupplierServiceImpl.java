package com.purchasing.service;

import com.purchasing.dao.AddressDAO;
import com.purchasing.dao.ContactDAO;
import com.purchasing.dao.JuristicPersonDAO;
import com.purchasing.dao.NaturalPersonDAO;
import com.purchasing.dao.StateDAO;
import com.purchasing.dao.SupplierDAO;
import com.purchasing.entity.Address;
import com.purchasing.entity.Contact;
import com.purchasing.entity.JuristicPerson;
import com.purchasing.entity.NaturalPerson;
import com.purchasing.entity.Person;
import com.purchasing.entity.State;
import com.purchasing.entity.Supplier;
import com.purchasing.service.impl.SupplierService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public class SupplierServiceImpl implements SupplierService {

    @Inject private ContactDAO contactDAO;
    @Inject private AddressDAO addressDAO;
    @Inject private NaturalPersonDAO naturalPersonDAO;
    @Inject private JuristicPersonDAO juristicPersonDAO;
    @Inject private SupplierDAO supplierDAO;
    @Inject private StateDAO stateDAO;

    @Override
    public Supplier save(Supplier supplier) {
            Contact contact = new Contact();
            contact.setPhone(supplier.getContact().getPhone());
            contact.setSecondaryPhone(supplier.getContact().getSecondaryPhone());
            contact.setCellPhone(supplier.getContact().getCellPhone());
            contact.setSecondaryCellPhone(supplier.getContact().getSecondaryCellPhone());
            contact.setEmail(supplier.getContact().getEmail());
            contact.setWebsite(supplier.getContact().getWebsite());
            contact.setContactName(supplier.getContact().getContactName());
            contact = contactDAO.save(contact);

            Address address = new Address();
            address.setState(supplier.getAddress().getState());
            address.setStreet(supplier.getAddress().getStreet());
            address.setNeighborhood(supplier.getAddress().getNeighborhood());
            address.setNumber(supplier.getAddress().getNumber());
            address.setZipCode(supplier.getAddress().getZipCode());
            address.setCity(supplier.getAddress().getCity());
            address = addressDAO.save(address);

            Person person = supplier.getPerson();
            switch (supplier.getPerson().getTypePerson()){
                case JuristicPerson:
                    JuristicPerson juristicPerson = person.getJuristicPerson();
                    person.setName(supplier.getPerson().getName());
                    person.setTypePerson(supplier.getPerson().getTypePerson());
                    juristicPerson.setPerson(person);
                    juristicPerson =  juristicPersonDAO.save(juristicPerson);
                    supplier.setPerson(juristicPerson);
                    break;
                case NaturalPerson:
                    NaturalPerson naturalPerson = person.getNaturalPerson();
                    person.setName(supplier.getPerson().getName());
                    person.setTypePerson(supplier.getPerson().getTypePerson());
                    naturalPerson.setPerson(person);
                    naturalPerson = naturalPersonDAO.save(naturalPerson);
                    supplier.setPerson(naturalPerson);
                    break;
            }
            supplier.setAddress(address);
            supplier.setContact(contact);
            supplier = supplierDAO.save(supplier);
            return supplier;
        }

    @Override
    public void delete(Supplier supplier) {
            if (supplier != null){
                supplierDAO.delete(supplier);
            }
        }

    @Override
    public List<Supplier> search(String text) {
            List<Supplier> suppliers = new ArrayList<>();
            if (text != null){
               suppliers = supplierDAO.search(text);
            }
            return suppliers;
        }

    @Override
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
            String search = sSearch == null ? "" : sSearch;
            List<Supplier> suppliers = supplierDAO.pagination(search,iDisplayStart,iDisplayLength);
            List<Object[]> supplierList = new ArrayList<>();
            for (Supplier supplier : suppliers){
                String colCode = supplier.getId().toString() ;
                String colName = supplier.getPerson().getName();
                String colPhone = supplier.getContact().getPhone();
                String colCategory = supplier.getCategory().getDescription();
                String buttonView = "<a onclick='viewSupplier("+supplier.getId()+")'><span class=\"fa fa-eye btn btn-default btn-xs\"></span></a>";
                String buttonEdit = "<a href=/purchasing/fornecedor/editar/"+supplier.getId()+"><span class=\"fa fa-edit btn btn-default btn-xs\"></span></a>";
                String buttonRemove = "<a onclick='confirmDetele("+supplier.getId()+")'><span class=\"fa fa-trash-o btn btn-default btn-xs\"></span></a>";
                String [] row = {
                        colCode,
                        colName,
                        colPhone,
                        colCategory,
                        buttonView,
                        buttonEdit,
                        buttonRemove
                };
                supplierList.add(row);
            }
            return supplierList;
        }

    @Override
    public Integer totalPagination(String sSearch) {
            String search = sSearch == null ? "" : sSearch;
            Integer total= 0;
            total = supplierDAO.totalPagination(search);
            return total;
        }

    @Override
    public List<State> findAllStates() {
            return stateDAO.findAll(State.class);
        }

    @Override
    public Supplier searchById(Supplier supplier) {
            return supplierDAO.findById(Supplier.class,supplier.getId());
        }

    @Override
    public JuristicPerson checkByCPNJ(String cnpj) {
        cnpj = cnpj == null ? "" : cnpj;
        return juristicPersonDAO.findByCNPJ(cnpj);
    }

    @Override
    public NaturalPerson checkByCPF(String cpf) {
        cpf = cpf == null ? "":cpf;
        return naturalPersonDAO.findByCPF(cpf);
    }
}
