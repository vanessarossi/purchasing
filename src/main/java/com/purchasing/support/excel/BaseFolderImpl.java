package com.purchasing.support.excel;

import com.purchasing.support.excel.base.BaseFolder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Vanessa on 12/10/15.
 */
public class BaseFolderImpl implements BaseFolder {

    File folder;

    public BaseFolderImpl() {
        //catalina.base
        String folderPath = System.getProperty("catalina.home") + "/webapps/relatorios";
        folder = new File(folderPath);
        if (!folder.exists()){
            folder.mkdir();
        }
    }

    @Override
    public FileOutputStream createFileOutputStream(String fileName) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(folder+"/"+fileName+".xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileOutputStream;
    }

    @Override
    public void createFile(JasperPrint jasperPrint, String fileName) {
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
    public File download(String fileName, String type) {
        File file = new File(folder, "/relatorios"+fileName+type);
        return (file.exists()) ? file :new File(folder, "/"+fileName+type);
    }
}