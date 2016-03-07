package com.purchasing.support.excel;

import com.purchasing.support.date.Conversor;
import com.purchasing.support.excel.base.BaseFile;
import com.purchasing.support.excel.entity.PurchasedServiceTypeByCostCenterReport;
import com.purchasing.support.excel.entity.principal.Report;
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
 * @date 3/7/16
 */
public class ExportPurchasedServiceTypeByCostCenterReport extends BaseFolderImpl implements BaseFile {
    @Override
    public File generateReport(Collection<Object> objects) {
        File file = null;
        List<PurchasedServiceTypeByCostCenterReport> purchasedServiceTypeByCostCenterReports = new PurchasedServiceTypeByCostCenterReport().convertListObjectInList(objects);

        String fileName = "RELATORIO_COMPRAS_CLASSIFICADAS_POR_TIPO_SERVIÇO_CENTRO_CUSTO_"+ Conversor.converterDateInStringForReport(new Date());
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
        for (PurchasedServiceTypeByCostCenterReport purchasedServiceTypeByCostCenterReport : purchasedServiceTypeByCostCenterReports){
            this.generateContentRow(sheet,purchasedServiceTypeByCostCenterReport,index);
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

    @Override
    public File generateReportWithMultipleSearch(Report report) {
        return null;
    }

    public void generateTitleRow(Row row, CellStyle cellStyle){
        Cell cell = row.createCell(0);
        cell.setCellValue("MÊS");
        row.createCell(1).setCellValue("CENTRO DE CUSTO");
        row.createCell(2).setCellValue("TIPO DE SERVIÇO");
        row.createCell(3).setCellValue("VALOR TOTAL");
        row.createCell(4).setCellValue("QUANTIDADE TOTAL");

        row.getCell(0).setCellStyle(cellStyle);
        row.getCell(1).setCellStyle(cellStyle);
        row.getCell(2).setCellStyle(cellStyle);
        row.getCell(3).setCellStyle(cellStyle);
        row.getCell(4).setCellStyle(cellStyle);

    }

    public void generateContentRow(Sheet sheet , PurchasedServiceTypeByCostCenterReport purchasedServiceTypeByCostCenterReport, Integer i) {
        HSSFRow contentRow = (HSSFRow) sheet.createRow(i);
        contentRow.createCell(0).setCellValue(purchasedServiceTypeByCostCenterReport.getMonth());
        contentRow.createCell(1).setCellValue(purchasedServiceTypeByCostCenterReport.getCostCenter());
        contentRow.createCell(2).setCellValue(purchasedServiceTypeByCostCenterReport.getTypeService());
        contentRow.createCell(3).setCellValue(purchasedServiceTypeByCostCenterReport.getValue());
        contentRow.createCell(4).setCellValue(purchasedServiceTypeByCostCenterReport.getTotal());
    }
}

