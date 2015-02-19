package com.purchasing.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import com.purchasing.annotation.Public;
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

    @Public
    @Path("/autenticar")
    public void authenticate(User user){
        User userFound = userService.authenticate(user);
        if (userFound != null && userFound.getId() != null){
            httpSession.setAttribute("userLogged",userFound);
            userSession.setUser(userFound);
            result.redirectTo("/home");
        }else{
            result.include("errorSign","message.errorSign");
            result.redirectTo(this).login();
        }
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