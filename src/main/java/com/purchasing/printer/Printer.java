package com.purchasing.printer;

import java.io.File;

/**
 * @author vanessa
 */
public interface Printer {

    public File generate(Long code);
}