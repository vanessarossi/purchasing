package com.purchasing.access;

import br.com.caelum.brutauth.auth.handlers.RuleHandler;
import br.com.caelum.vraptor.Result;
import com.purchasing.controller.LoginController;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * @author Vanessa
 * @date 1/21/16
 */
@RequestScoped
public class LoggedHandler implements RuleHandler {
    private final Result result;

    /**
     * @deprecated CDI eyes only
     */
    protected LoggedHandler() {
        this(null);
    }

    @Inject
    public LoggedHandler(Result result) {
        this.result = result;
    }

    @Override
    public void handle() {
        result.redirectTo(LoginController.class).login();
    }
}