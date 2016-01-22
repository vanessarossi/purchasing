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
public class SeventhLevelAccessRule implements CustomBrutauthRule {

    private UserSession userSession;

    /**
     * @deprecated CDI eyes only
     */
    protected SeventhLevelAccessRule() {
        this(null);
    }

    @Inject
    public SeventhLevelAccessRule(UserSession userSession) {
        this.userSession = userSession;
    }

    public boolean isAllowed() {
        return  userSession.getUser().getRole().getId() == 1 ||
                userSession.getUser().getRole().getId() == 5 ||
                userSession.getUser().getRole().getId() == 6 ||
                userSession.getUser().getRole().getId() == 7;
    }
}
