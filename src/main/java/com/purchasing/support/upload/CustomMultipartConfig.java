package com.purchasing.support.upload;

import br.com.caelum.vraptor.observer.upload.DefaultMultipartConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

/**
 * @author vanessa
 */
@Specializes
@ApplicationScoped
public class CustomMultipartConfig extends DefaultMultipartConfig {

    public long getSizeLimit() {
        return 2 * 1024 * 1024;
    }
}
