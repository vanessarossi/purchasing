package com.purchasing.access;

import br.com.caelum.brutauth.auth.annotations.GlobalRule;
import br.com.caelum.brutauth.auth.annotations.HandledBy;
import br.com.caelum.brutauth.auth.rules.CustomBrutauthRule;
import com.purchasing.support.user.UserSession;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * @author Vanessa
 * @date 1/21/16
 */
@RequestScoped
@HandledBy(LoggedHandler.class)
@GlobalRule
public class LoggedAccessRule implements CustomBrutauthRule {

    private UserSession userSession;

    /**
     * @deprecated CDI eyes only
     */
    protected LoggedAccessRule() {
        this(null);
    }

    @Inject
    public LoggedAccessRule(UserSession userSession) {
        this.userSession = userSession;
    }

    public boolean isAllowed() {
        return userSession.isLogged();
    }
}
