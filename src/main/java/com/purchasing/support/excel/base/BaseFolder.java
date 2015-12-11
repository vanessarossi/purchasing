package com.purchasing.support.excel.base;

import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Vanessa on 12/10/15.
 */
public interface BaseFolder {

    public FileOutputStream createFileOutputStream(String fileName);
    public void createFile(JasperPrint jasperPrint , String fileName);
    public File download(String fileName,String type);
}
