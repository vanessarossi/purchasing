package com.purchasing.interceptor;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import com.purchasing.annotation.Public;
import com.purchasing.controller.LoginController;
import com.purchasing.support.user.UserSession;

import javax.inject.Inject;


/**
 * Created by Vanessa on 4/7/15.
 */
@Intercepts
public class LoginInterceptor {

    private Result result;
    private UserSession userSession;

    @Inject
    public LoginInterceptor(Result result, UserSession userSession) {
        this.result = result;
        this.userSession = userSession;
    }

    @Deprecated
    LoginInterceptor() {
    }

    @AroundCall
    public void isLogged(SimpleInterceptorStack simpleInterceptorStack) throws InterceptionException {
        if (userSession.isLogged()) {
            simpleInterceptorStack.next();
        } else {
            result.redirectTo(LoginController.class).login();
        }
    }

    @Accepts
    public boolean isPublic(ControllerMethod controllerMethod) {
        return !(controllerMethod.getMethod().isAnnotationPresent(Public.class));
    }

}