package com.purchasing.support.excel;

import com.purchasing.support.date.Conversor;
import com.purchasing.support.excel.base.BaseFile;
import com.purchasing.support.excel.entity.PurchasedProductClassificationReport;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Vanessa
 * @date 2/3/16
 */
public class ExportPurchasedProductClassificationReport  extends BaseFolderImpl implements BaseFile {

    @Override
    public File generateReport(Collection<Object> objects) {
        File file = null;
        List<PurchasedProductClassificationReport> purchasedProductClassificationReports = new PurchasedProductClassificationReport().convertListObjectInList(objects);

        String fileName = "RELATORIO_COMPRAS_CLASSIFICADAS_POR_CATEGORIA_PRODUTO_"+ Conversor.converterDateInStringForReport(new Date());
        FileOutputStream fileOutputStream = this.createFileOutputStream(fileName);

        Workbook workbook = new HSSFWorkbook();
        Font font = workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);

        CreationHelper createHelper =workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("CLASSIFIAÇÃO POR CATEGORIA");

        Row row = (Row) sheet.createRow((short)0);
        this.generateTitleRow(row,style);
        Integer index = 1;
        for (PurchasedProductClassificationReport purchasedProductClassificationReport : purchasedProductClassificationReports){
            this.generateContentRow(sheet,purchasedProductClassificationReport,index);
            index ++;
        }

        try {
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            file = this.download(fileName,".xls");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public void generateTitleRow(Row row, CellStyle cellStyle){
        Cell cell = row.createCell(0);
        cell.setCellValue("MÊS");
        row.createCell(1).setCellValue("CATEGORIA");
        row.createCell(2).setCellValue("VALOR TOTAL");
        row.createCell(3).setCellValue("QUANTIDADE TOTAL");

        row.getCell(0).setCellStyle(cellStyle);
        row.getCell(1).setCellStyle(cellStyle);
        row.getCell(2).setCellStyle(cellStyle);
        row.getCell(3).setCellStyle(cellStyle);

    }

    public void generateContentRow(Sheet sheet , PurchasedProductClassificationReport purchasedProductClassificationReport, Integer i) {
        HSSFRow contentRow = (HSSFRow) sheet.createRow(i);
        contentRow.createCell(0).setCellValue(purchasedProductClassificationReport.getMonth());
        contentRow.createCell(1).setCellValue(purchasedProductClassificationReport.getCategory());
        contentRow.createCell(2).setCellValue(purchasedProductClassificationReport.getValue());
        contentRow.createCell(3).setCellValue(purchasedProductClassificationReport.getTotal());
    }
}
