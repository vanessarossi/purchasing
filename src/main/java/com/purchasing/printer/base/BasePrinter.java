package com.purchasing.printer.base;

import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;

/**
 * @author vanessa
 */
public interface BasePrinter {

    public void printer(JasperPrint jasperPrint , String fileName);
    public File download(String fileName);
}
