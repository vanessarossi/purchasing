package com.purchasing.error;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.DefaultControllerNotFoundHandler;
import br.com.caelum.vraptor.controller.HttpMethod;
import br.com.caelum.vraptor.events.ControllerNotFound;
import br.com.caelum.vraptor.http.MutableRequest;
import br.com.caelum.vraptor.http.MutableResponse;
import br.com.caelum.vraptor.http.route.ControllerNotFoundException;
import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.view.Results;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.servlet.FilterChain;

/**
 * @author vanessa
 */
@RequestScoped
@Specializes
public class Error404 extends DefaultControllerNotFoundHandler {

    private final Router router;
    private final Result result;

    /**
     * @deprecated CDI eyes only
     */
    @Deprecated
    protected Error404() {
        this(null, null, null);
    }

    @Inject
    public Error404(Router router, Result result, Event<ControllerNotFound> event) {
        super(event);
        this.router = router;
        this.result = result;
    }

    @Override
    public void couldntFind(FilterChain chain,
            MutableRequest request, MutableResponse response) {
        try {
            String uri = request.getRequestedUri();
            if (uri.endsWith("/")) {
                tryMovePermanentlyTo(request, uri.substring(0, uri.length()-1));
            } else {
                tryMovePermanentlyTo(request, uri + "/");
            }
        } catch (ControllerNotFoundException ex) {
            super.couldntFind(chain, request, response);
        }
    }

    private void tryMovePermanentlyTo(MutableRequest request, String newUri) {
        router.parse(newUri, HttpMethod.of(request), request);
        result.use(Results.status()).movedPermanentlyTo(newUri);
    }
}
