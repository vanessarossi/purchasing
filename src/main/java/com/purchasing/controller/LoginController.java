package com.purchasing.controller;

import br.com.caelum.brutauth.auth.annotations.Public;
import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.view.Results;
import com.purchasing.entity.User;
import com.purchasing.service.impl.UserService;
import com.purchasing.support.user.UserSession;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author vanessa
 */
@Controller
@Path("/login")
public class LoginController {

    private Result result;
    private UserSession userSession;
    private HttpSession httpSession;
    private UserService userService;

    @Deprecated
    public LoginController(){}

    @Inject
    public LoginController(Result result, UserSession userSession, HttpSession httpSession, UserService userService) {
        this.result = result;
        this.userSession = userSession;
        this.httpSession = httpSession;
        this.userService = userService;
    }

    @Public
    @Path("")
    public void login() {
        result.include("controller", this.getClass().toString());
    }

    @Path("/formulario/perfil")
    public void formPerfil() {
        result.include("controller", this.getClass().toString());
    }

    @Public
    @Post("/autenticar")
    public void authenticate(User user){
        User userFound = userService.authenticate(user);
        if (userFound != null && userFound.getId() != null){
            httpSession.setAttribute("userLogged",userFound);
            userSession.setUser(userFound);
            if (userFound.getLastAccess() == null){
                userFound.setLastAccess(new Timestamp(new Date().getTime()));
                userService.save(userFound);
                result.include("user",userFound);
                result.redirectTo(this).formPerfil();
            }else{
                result.redirectTo("/home");
            }
        }else{
            result.include("errorSign","message.errorSign");
            result.redirectTo(this).login();
        }
    }

    @Post("/confirma/nova/senha")
    public void confirmNewPassword(User user){
        User userFound = userService.searchById(user);
        userFound.setPassword(user.getPassword());
        userService.saveNewPassword(userFound);
        result.redirectTo("/home");
    }

    @Get("/meu/perfil/{user.id}")
    public void myPerfil(User user){
        user = userService.searchById(user);
        result.include("user",user);
        result.redirectTo(this).formPerfil();
    }

    @Public
    @Path("/nova/senha")
    public void newPassword(User user){
        User userFound = userService.sendNewPassword(user);
        if (userFound != null){
            result.use(Results.json()).from(true).serialize();
        }else{
            result.use(Results.json()).from(false).serialize();
        }
    }

    @Get("/logoff")
    public void logoff() {
        httpSession.removeAttribute("userLogged");
        userService.logoff();
        result.redirectTo(this).login();
    }
}