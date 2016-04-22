package com.purchasing.service.impl;

import com.purchasing.entity.Account;
import com.purchasing.enumerator.AddressEnum;

import java.util.List;

/**
 * Created by vanessa on 22/04/2016.
 */
public interface AccountService {

    public void save(Account account);
    public void delete(Account account);
    public Account searchById(Account account);
    public List<AddressEnum> getAllAddress();
    public String searchPlace(AddressEnum addressEnum);
    public List<Account> searchByTypeAndCompetenceAndAddress(Account account);
}
