package com.purchasing.controller;

import br.com.caelum.brutauth.auth.annotations.Public;
import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import com.purchasing.entity.User;
import com.purchasing.service.impl.UserService;
import com.purchasing.support.user.UserSession;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

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
    private Validator validator;

    @Deprecated
    public LoginController(){}

    @Inject
    public LoginController(Result result, UserSession userSession, HttpSession httpSession, UserService userService, Validator validator) {
        this.result = result;
        this.userSession = userSession;
        this.httpSession = httpSession;
        this.userService = userService;
        this.validator = validator;
    }

    @Public
    @Path("")
    public void login() {
        result.include("controller", this.getClass().toString());
    }

    @Public
    @Path("/esqueceu/senha")
    public void formNewPassword() {
        result.include("controller", this.getClass().toString());
    }


    @Path("/formulario/perfil")
    public void formPerfil() {
        result.include("controller", this.getClass().toString());
    }

    @Public
    @Post("/autenticar")
    public void authenticate(User user){
        validator.addIf(user.getUsername() == null  || user.getPassword() == null, new I18nMessage("message.not.blank.login","message.not.blank.login"));
        validator.onErrorForwardTo(this).login();
        User userFound = userService.authenticate(user);
        if (userFound != null && userFound.getId() != null){
            httpSession.setAttribute("userLogged",userFound);
            userSession.setUser(userFound);
            if (userFound.getFirstAccess() == true){
                userFound.setFirstAccess(false);
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
    @Post("/nova/senha")
    public void newPassword(User user){
        validator.addIf(user.getEmail() == null , new I18nMessage("message.not.blank.email","message.not.blank.email"));
        validator.onErrorForwardTo(this).formNewPassword();
        User userFound = userService.sendNewPassword(user);
        result.include("sendEmail","message.sentEmail");
        result.include("user",userFound);
        result.redirectTo(this).formNewPassword();
    }

    @Get("/logoff")
    public void logoff() {
        httpSession.removeAttribute("userLogged");
        userService.logoff();
        result.redirectTo(this).login();
    }
}