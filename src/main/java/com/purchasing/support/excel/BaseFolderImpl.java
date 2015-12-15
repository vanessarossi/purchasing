package com.purchasing.support.excel;

import com.purchasing.support.excel.base.BaseFolder;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Vanessa on 12/10/15.
 */
public class BaseFolderImpl implements BaseFolder {
    @Override
    public FileOutputStream createFileOutputStream(String fileName) {
        return null;
    }

    @Override
    public void createFile(JasperPrint jasperPrint, String fileName) {

    }

    @Override
    public File download(String fileName, String type) {
        return null;
    }
}