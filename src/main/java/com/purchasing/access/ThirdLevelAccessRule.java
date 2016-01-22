package com.purchasing.access;

import br.com.caelum.brutauth.auth.rules.CustomBrutauthRule;
import com.purchasing.support.user.UserSession;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * @author Vanessa
 * @date 1/21/16
 */
@Dependent
public class ThirdLevelAccessRule implements CustomBrutauthRule {

    private UserSession userSession;

    /**
     * @deprecated CDI eyes only
     */
    protected ThirdLevelAccessRule() {
        this(null);
    }

    @Inject
    public ThirdLevelAccessRule(UserSession userSession) {
        this.userSession = userSession;
    }

    public boolean isAllowed() {
        return  userSession.getUser().getRole().getId() == 1 ||
                userSession.getUser().getRole().getId() == 3 ||
                userSession.getUser().getRole().getId() == 4 ||
                userSession.getUser().getRole().getId() == 5 ||
                userSession.getUser().getRole().getId() == 7;
    }
}
