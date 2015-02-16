package com.purchasing.service.impl;

import com.purchasing.entity.Role;
import com.purchasing.entity.User;

import java.util.List;

/**
 * @author vanessa
 */
public interface UserService {

    public User authenticate(User user);
    public User save(User user) throws Exception;
    public void delete(User user);
    public User searchById(User user);
    public User searchByUsername(User user);
    public User sendNewPassword(User user);
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);
    public void logoff();

    public List<Role> findAllRole();

}
