package com.purchasing.interceptor;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import com.purchasing.annotation.Public;
import com.purchasing.support.user.UserSession;

import javax.inject.Inject;

/**
 * Created by Vanessa on 4/30/15.
 */

@Intercepts(after=LoginInterceptor.class)
public class PermissionInteceptor {

    private Result result;
    private UserSession userSession;
    private String controller;

    @Deprecated PermissionInteceptor(){}

    @Inject
    public PermissionInteceptor(Result result , UserSession userSession){
        this.result = result;
        this.userSession = userSession;
    }

    @AroundCall
    public void isAllowed (InterceptorStack interceptorStack, ControllerMethod controllerMethod, SimpleInterceptorStack simpleInterceptorStack) throws InterceptionException {
    }

    @Accepts
    public boolean isAnnotation(ControllerMethod controllerMethod) {
        return !(controllerMethod.getMethod().isAnnotationPresent(Public.class));
    }
    
}
