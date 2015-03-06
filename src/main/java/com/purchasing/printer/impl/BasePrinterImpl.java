package com.purchasing.printer.impl;

import com.purchasing.printer.base.BasePrinter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author vanessa
 */
public class BasePrinterImpl implements BasePrinter {

    File folder;

    public BasePrinterImpl() {
           //catalina.base
        String folderPath = System.getProperty("catalina.home") + "/webapps/relatorios";
        folder = new File(folderPath);
        if (!folder.exists()){
            folder.mkdir();
        }
    }

    @Override
    public void printer(JasperPrint jasperPrint, String fileName) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(folder+"/"+fileName+".pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, fileOutputStream);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File download(String fileName) {
        File file = new File(folder, "/relatorios"+fileName+".pdf");
        return (file.exists()) ? file :new File(folder, "/"+fileName+".pdf");
    }
}
