package com.purchasing.support.excel;

import com.purchasing.support.date.Conversor;
import com.purchasing.support.excel.base.BaseFile;
import com.purchasing.support.excel.entity.AccountReport;
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
 * Created by vanessa on 08/08/2016.
 */
public class ExportAccountReport extends BaseFolderImpl implements BaseFile {

    @Override
    public File generateReport(Collection<Object> objects) {
        File file = null;
        List<AccountReport> accountReports = new AccountReport().convertListObjectReport(objects);

        String fileName = "RELATORIO_LANÇAMENTO_DE_CONTAS_"+ Conversor.converterDateInStringForReport(new Date());
        FileOutputStream fileOutputStream = this.createFileOutputStream(fileName);

        Workbook workbook = new HSSFWorkbook();
        Font font = workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);

        CreationHelper createHelper =workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("LANÇAMENTO DE CONTAS");

        Row row = (Row) sheet.createRow((short)0);
        this.generateTitleRow(row,style);
        Integer index = 1;
        for (AccountReport accountReport : accountReports){
            this.generateContentRow(sheet,accountReport,index);
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
        cell.setCellValue("COMPETENCIA");
        row.createCell(1).setCellValue("TIPO DE CONTA");
        row.createCell(2).setCellValue("LOCAL");
        row.createCell(3).setCellValue("TELEFONE");
        row.createCell(4).setCellValue("TIPO DE ASSINATURA");
        row.createCell(5).setCellValue("TIPO DE SERVIÇO");
        row.createCell(6).setCellValue("VALOR");
        row.createCell(7).setCellValue("DESCONTO");
        row.createCell(8).setCellValue("VALOR TOTAL");
        row.createCell(9).setCellValue("DATA DE VENCIMENTO");
        row.createCell(10).setCellValue("OBSERVAÇÃO");

        row.getCell(0).setCellStyle(cellStyle);
        row.getCell(1).setCellStyle(cellStyle);
        row.getCell(2).setCellStyle(cellStyle);
        row.getCell(3).setCellStyle(cellStyle);
        row.getCell(4).setCellStyle(cellStyle);
        row.getCell(5).setCellStyle(cellStyle);
        row.getCell(6).setCellStyle(cellStyle);
        row.getCell(7).setCellStyle(cellStyle);
        row.getCell(8).setCellStyle(cellStyle);
        row.getCell(9).setCellStyle(cellStyle);
        row.getCell(10).setCellStyle(cellStyle);

    }

    public void generateContentRow(Sheet sheet , AccountReport accountReport, Integer i) {
        HSSFRow contentRow = (HSSFRow) sheet.createRow(i);
        contentRow.createCell(0).setCellValue(accountReport.getCompetence());
        contentRow.createCell(1).setCellValue(accountReport.getType());
        contentRow.createCell(2).setCellValue(accountReport.getPlace());
        contentRow.createCell(3).setCellValue(accountReport.getPhone());
        contentRow.createCell(4).setCellValue(accountReport.getSignatureType());
        contentRow.createCell(5).setCellValue(accountReport.getTypeService());
        contentRow.createCell(6).setCellValue(accountReport.getValue());
        contentRow.createCell(7).setCellValue(accountReport.getDiscount());
        contentRow.createCell(8).setCellValue(accountReport.getTotalValue());
        contentRow.createCell(9).setCellValue(accountReport.getExpirationDate());
        contentRow.createCell(10).setCellValue(accountReport.getObservation());
    }
}
