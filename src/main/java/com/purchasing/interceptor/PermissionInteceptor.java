package com.purchasing.interceptor;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import com.purchasing.annotation.*;
import com.purchasing.entity.User;
import com.purchasing.support.user.UserSession;

import javax.inject.Inject;
import static br.com.caelum.vraptor.view.Results.http;

/**
 * Created by Vanessa on 4/30/15.
 */

@Intercepts(after=LoginInterceptor.class)
public class PermissionInteceptor {

    private Result result;
    private UserSession userSession;
    private Boolean status = null;

    @Deprecated PermissionInteceptor(){}

    @Inject
    public PermissionInteceptor(Result result , UserSession userSession){
        this.result = result;
        this.userSession = userSession;
    }

    @AroundCall
    public void isAllowed (InterceptorStack interceptorStack, ControllerMethod controllerMethod, SimpleInterceptorStack simpleInterceptorStack) throws InterceptionException {

        Admin methodPermissionAdmin = controllerMethod.getMethod().getAnnotation(Admin.class);
        Council methodPermissionCouncil = controllerMethod.getMethod().getAnnotation(Council.class);
        Director methodPermissionDirector = controllerMethod.getMethod().getAnnotation(Director.class);
        Manager methodPermissionManager = controllerMethod.getMethod().getAnnotation(Manager.class);
        Analyst methodPermissionAnalyst = controllerMethod.getMethod().getAnnotation(Analyst.class);
        Coordinato methodPermissionCoordinato = controllerMethod.getMethod().getAnnotation(Coordinato.class);
        Purchaser methodPermissionPurchaser = controllerMethod.getMethod().getAnnotation(Purchaser.class);
        SolicitorReceptionist methodPermissionSolicitorReceptionist = controllerMethod.getMethod().getAnnotation(SolicitorReceptionist.class);
        Solicitor methodPermissionSolicitor = controllerMethod.getMethod().getAnnotation(Solicitor.class);

        if (methodPermissionAdmin != null && isAdmin(getUser()) != true){
            status = false;
        }else if(methodPermissionAdmin != null && isAdmin(getUser()) == true){
            status = true;
        }if (methodPermissionCouncil != null && isCouncil(getUser())!= true && status == null){
            status = false;
        }else if(methodPermissionCouncil != null && isCouncil(getUser()) == true){
            status = true;
        }if (methodPermissionDirector != null && isDirector(getUser()) != true && status == null){
            status = false;
        }else if(methodPermissionDirector != null && isDirector(getUser()) == true){
            status = true;
        } if (methodPermissionAdmin != null && isAdmin(getUser()) != true){
            status = false;
        }else if(methodPermissionAdmin != null && isAdmin(getUser()) == true){
            status = true;
        }if (methodPermissionManager != null && isManager(getUser())!= true && status == null){
            status = false;
        }else if(methodPermissionManager != null && isManager(getUser()) == true){
            status = true;
        }if (methodPermissionAnalyst != null && isAnalyst(getUser()) != true && status == null){
            status = false;
        }else if(methodPermissionAnalyst != null && isAnalyst(getUser()) == true){
            status = true;
        } if (methodPermissionCoordinato != null && isCoordinato(getUser()) != true){
            status = false;
        }else if(methodPermissionCoordinato != null && isCoordinato(getUser()) == true){
            status = true;
        }if (methodPermissionPurchaser != null && isPurchaser(getUser())!= true && status == null){
            status = false;
        }else if(methodPermissionPurchaser != null && isPurchaser(getUser()) == true){
            status = true;
        }if (methodPermissionSolicitorReceptionist != null && isSolicitorReceptionist(getUser()) != true && status == null){
            status = false;
        }else if(methodPermissionSolicitorReceptionist != null && isSolicitorReceptionist(getUser()) == true){
            status = true;
        } if (methodPermissionAdmin != null && isAdmin(getUser()) != true){
            status = false;
        }else if(methodPermissionAdmin != null && isAdmin(getUser()) == true){
            status = true;
        }if (methodPermissionSolicitor != null && isSolicitor(getUser())!= true && status == null){
            status = false;
        }else if(methodPermissionSolicitor != null && isSolicitor(getUser()) == true){
            status = true;
        }
        if (status != null && status == false){
            result.use(http()).sendError(403);
        }else{
            simpleInterceptorStack.next();
        }
    }

    @Accepts
    public boolean isAnnotation(ControllerMethod controllerMethod) {
        return !(controllerMethod.getMethod().isAnnotationPresent(Public.class));
    }

    public boolean isAdmin(User user){
        return user.getRole().getId().equals(1) ? true : false;
    }

    public boolean isCouncil(User user){
        return user.getRole().getId().equals(2) ? true : false;
    }

    public boolean isDirector(User user){
        return user.getRole().getId().equals(3) ? true : false;
    }

    public boolean isManager(User user){
        return user.getRole().getId().equals(4) ? true : false;
    }

    public boolean isAnalyst(User user){
        return user.getRole().getId().equals(5) ? true : false;
    }

    public boolean isCoordinato(User user){
        return user.getRole().getId().equals(6) ? true : false;
    }

    public boolean isPurchaser(User user){
        return user.getRole().getId().equals(7) ? true : false;
    }

    public boolean isSolicitorReceptionist(User user){
        return user.getRole().getId().equals(8) ? true : false;
    }

    public boolean isSolicitor(User user){
        return user.getRole().getId().equals(9) ? true : false;
    }

    public User getUser(){
        User user = userSession.getUser();
        return user;
    }
}
