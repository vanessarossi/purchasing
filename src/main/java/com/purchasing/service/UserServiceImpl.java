package com.purchasing.service;

import br.com.caelum.vraptor.simplemail.Mailer;
import com.purchasing.dao.CompanyDAO;
import com.purchasing.dao.RoleDAO;
import com.purchasing.dao.UserDAO;
import com.purchasing.entity.Company;
import com.purchasing.entity.Role;
import com.purchasing.entity.User;
import com.purchasing.service.impl.UserService;
import com.purchasing.support.converter.Decrypter;
import com.purchasing.support.user.BodyEmail;
import com.purchasing.support.user.PasswordGenerator;
import com.purchasing.support.user.UserSession;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author vanessa
 */
public class UserServiceImpl implements UserService {

    @Inject private UserDAO userDAO;
    @Inject private Mailer mailer;
    @Inject private RoleDAO roleDAO;
    @Inject private CompanyDAO companyDAO;
    @Inject private UserSession userSession;

    @Override
    public User authenticate(User user) {
        User userFound = new User();
        if (user.getUsername() != null && user.getPassword() != null){
            try {
                userFound = userDAO.findByUsernamePassword(user.getUsername().toLowerCase(), Decrypter.encrypt(user.getPassword()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (userFound!= null && userFound.getId() != null){
                userFound.setLastAccess(new Timestamp(new Date().getTime()));
                userDAO.save(userFound);
            }
        }
        return userFound;
    }

    @Override
    public User save(User user){
        User newUser = user;
         newUser.setId(1l);

        if (user.getId() != null && user.getPassword() == null){
            User userEdit = searchById(user);
            newUser.setPassword(userEdit.getPassword());
        }else{
            try {
                newUser.setPassword(Decrypter.encrypt(user.getPassword()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        newUser.setUsername(user.getUsername().toLowerCase());
        newUser = userDAO.save(newUser);
        return newUser;
    }

    @Override
    public void delete(User user) {
        if (user.getId() != null){
            userDAO.delete(user);
        }
    }

    @Override
    public User searchByUsername(User user) {
        User userFound = new User();
        if (user.getUsername() != null && user.getPassword() != null){
            userFound = userDAO.findByUsername(user.getUsername().toLowerCase());
        }
        return userFound;
    }

    @Override
    public User sendNewPassword(User user) {
        User userFound = new User();
        if (user.getEmail() != null){
            String newPassword = PasswordGenerator.generate();
            userFound = userDAO.findByEmail(user.getEmail());
            if (userFound != null && userFound.getId() != null){
                try {
                    userFound.setPassword(Decrypter.encrypt(newPassword));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                user = userDAO.save(userFound);
                BodyEmail bodyEmail = new BodyEmail(user.getName(),newPassword);
                Email email = new SimpleEmail();
                email.setSubject("Nova senha - Sistema de Compras");
                try {
                    email.addTo(user.getEmail());
                } catch (EmailException e) {
                    e.printStackTrace();
                }
                email.setCharset("UTF-8");
                email.setContent(bodyEmail.getBody(),"text/html");
                try {
                    mailer.send(email);
                } catch (EmailException e) {
                    e.printStackTrace();
                }
            }
        }
        return userFound;
        }

    @Override
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<User> users = userDAO.pagination(search,iDisplayStart,iDisplayLength);
        List<Object[]> userList = new ArrayList<>();
        for (User user : users){
            String colName = user.getName();
            String colUsername = user.getUsername() ;
            String colRole = user.getRole().getDescription() ;
            String colActive = user.getActive() == true ? "Sim" : "Não";
            String buttonView = "<a onclick='viewUser("+user.getId()+")'><span class=\"fa fa-eye btn btn-default btn-xs\"></span></a>";
            String buttonEdit = "<a href=/purchasing/usuario/editar/"+user.getId()+"><span class=\"fa fa-edit btn btn-default btn-xs\"></span></a>";
            String buttonRemove = "<a onclick='confirmDetele("+user.getId()+")'><span class=\"fa fa-trash-o btn btn-default btn-xs\"></span></a>";
            String [] row = {
                    colName,
                    colUsername,
                    colRole,
                    colActive,
                    buttonView,
                    buttonEdit,
                    buttonRemove
            };
            userList.add(row);
        }
        return userList;
    }

    @Override
    public Integer totalPagination(String sSearch) {
        String search = sSearch == null ? "" : sSearch;
        Integer total= 0;
        total = userDAO.totalPagination(search);
        return total;
    }

    @Override
    public List<Role> findAllRole() {
        return roleDAO.findAll(Role.class);
    }

    @Override
    public List<Company> findAllCompany() {
        return companyDAO.findAll(Company.class);
    }

    @Override
    public User searchById(User user) {
        return userDAO.findById(User.class,user.getId());
    }

    @Override
    public void logoff() {
        userSession.logout();
    }
}
