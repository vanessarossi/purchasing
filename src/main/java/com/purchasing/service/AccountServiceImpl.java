package com.purchasing.service;

import com.purchasing.dao.AccountDAO;
import com.purchasing.entity.Account;
import com.purchasing.enumerator.AddressEnum;
import com.purchasing.service.impl.AccountService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by vanessa on 22/04/2016.
 */
public class AccountServiceImpl implements AccountService {

    @Inject
    private AccountDAO accountDAO;

    @Override
    public void save(Account account) {
        accountDAO.save(account);
    }

    @Override
    public void delete(Account account) {
        accountDAO.delete(account);
    }

    @Override
    public Account searchById(Account account) {
        return accountDAO.findById(Account.class,account.getId());
    }

    @Override
    public List<AddressEnum> getAllAddress() {
        return AddressEnum.getAll();
    }

    @Override
    public String searchPlace(AddressEnum addressEnum) {
        return AddressEnum.returnPlace(addressEnum);
    }

    @Override
    public List<Account> searchByTypeAndCompetenceAndAddress(Account account) {
        return accountDAO.findByTypeAndCompetenceAndAddress(account);
    }

}
