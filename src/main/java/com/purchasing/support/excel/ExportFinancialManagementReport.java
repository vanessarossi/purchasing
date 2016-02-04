package com.purchasing.support.excel;

import com.purchasing.support.date.Conversor;
import com.purchasing.support.excel.base.BaseFile;
import com.purchasing.support.excel.entity.FinancialManagementReport;
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
 * @date 2/3/16
 */
public class ExportFinancialManagementReport extends BaseFolderImpl implements BaseFile {

    @Override
    public File generateReport(Collection<Object> objects) {

        File file = null;
        List<FinancialManagementReport> financialManagementReports = new FinancialManagementReport().convertListObjectInList(objects);

        String fileName = "RELATORIO_GERENCIAL_FINANCEIRO_"+ Conversor.converterDateInStringForReport(new Date());
        FileOutputStream fileOutputStream = this.createFileOutputStream(fileName);

        Workbook workbook = new HSSFWorkbook();
        Font font = workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);

        CreationHelper createHelper =workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("RELATÓRIO GERENCIAL FINANCEIRO");

        Row row = (Row) sheet.createRow((short)0);
        this.generateTitleRow(row,style);
        Integer index = 1;
        for (FinancialManagementReport financialManagementReport : financialManagementReports){
            this.generateContentRow(sheet,financialManagementReport,index);
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
        row.createCell(2).setCellValue("VALOR TOTAL");

        row.getCell(0).setCellStyle(cellStyle);
        row.getCell(1).setCellStyle(cellStyle);
        row.getCell(2).setCellStyle(cellStyle);

    }

    public void generateContentRow(Sheet sheet , FinancialManagementReport financialManagementReport, Integer i) {
        HSSFRow contentRow = (HSSFRow) sheet.createRow(i);
        contentRow.createCell(0).setCellValue(financialManagementReport.getMonth());
        contentRow.createCell(1).setCellValue(financialManagementReport.getCostCenter());
        contentRow.createCell(2).setCellValue(financialManagementReport.getValue());

    }
}
