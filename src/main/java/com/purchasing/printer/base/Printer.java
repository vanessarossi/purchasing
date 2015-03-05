package com.purchasing.printer.base;

import java.io.File;

/**
 * @author vanessa
 */
public interface Printer {

    public File generate(Long code);
}
