package com.purchasing.support.excel;

import com.purchasing.support.date.Conversor;
import com.purchasing.support.excel.base.BaseFile;
import com.purchasing.support.excel.entity.FinancialManagementByCostCenterReport;
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
public class ExportFinancialManagementByCostCenterReport extends BaseFolderImpl implements BaseFile {

    @Override
    public File generateReport(Collection<Object> objects) {

        File file = null;
        List<FinancialManagementByCostCenterReport> financialManagementByCostCenterReports = new FinancialManagementByCostCenterReport().convertListObjectInList(objects);

        String fileName = "RELATORIO_GASTO_POR_CENTRO_DE_CUSTO_"+ Conversor.converterDateInStringForReport(new Date());
        FileOutputStream fileOutputStream = this.createFileOutputStream(fileName);

        Workbook workbook = new HSSFWorkbook();
        Font font = workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);

        CreationHelper createHelper =workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("GASTO POR DENTRO DE CUSTO");

        Row row = (Row) sheet.createRow((short)0);
        this.generateTitleRow(row,style);
        Integer index = 1;
        for (FinancialManagementByCostCenterReport financialManagementByCostCenterReport : financialManagementByCostCenterReports){
            this.generateContentRow(sheet,financialManagementByCostCenterReport,index);
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

    public void generateContentRow(Sheet sheet , FinancialManagementByCostCenterReport financialManagementByCostCenterReport, Integer i) {
        HSSFRow contentRow = (HSSFRow) sheet.createRow(i);
        contentRow.createCell(0).setCellValue(financialManagementByCostCenterReport.getMonth());
        contentRow.createCell(1).setCellValue(financialManagementByCostCenterReport.getCostCenter());
        contentRow.createCell(2).setCellValue(financialManagementByCostCenterReport.getTotal());

    }
}
