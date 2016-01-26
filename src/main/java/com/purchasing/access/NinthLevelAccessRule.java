package com.purchasing.access;

import br.com.caelum.brutauth.auth.rules.CustomBrutauthRule;
import com.purchasing.dao.CostCenterDAO;
import com.purchasing.entity.CostCenter;
import com.purchasing.support.user.UserSession;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * @author Vanessa
 * @date 1/21/16
 */
@Dependent
public class NinthLevelAccessRule implements CustomBrutauthRule {

    private UserSession userSession;

    @Inject
    private CostCenterDAO costCenterDAO;

    /**
     * @deprecated CDI eyes only
     */
    protected NinthLevelAccessRule() {
        this(null);
    }

    @Inject
    public NinthLevelAccessRule(UserSession userSession) {
        this.userSession = userSession;
    }

    public boolean isAllowed() {
        CostCenter costCenter = costCenterDAO.findById(CostCenter.class,new Long(18));
        return  userSession.getUser().getRole().getId() == 1 ||
                userSession.getUser().getRole().getId() == 5 ||
                (   userSession.getUser().getRole().getId() == 6
                        &&
                    userSession.getUser().containsCostCenter(costCenter)
                ) ||
                userSession.getUser().getRole().getId() == 7;
    }
}
