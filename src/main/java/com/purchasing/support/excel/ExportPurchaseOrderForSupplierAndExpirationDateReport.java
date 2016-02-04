package com.purchasing.support.excel;

import com.purchasing.support.date.Conversor;
import com.purchasing.support.excel.base.BaseFile;
import com.purchasing.support.excel.entity.PurchaseOrderForSupplierAndExpirationDateReport;
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
public class ExportPurchaseOrderForSupplierAndExpirationDateReport extends BaseFolderImpl implements BaseFile {

    @Override
    public File generateReport(Collection<Object> objects) {

        File file = null;
        List<PurchaseOrderForSupplierAndExpirationDateReport> purchaseOrderForSupplierAndExpirationDateReports = new PurchaseOrderForSupplierAndExpirationDateReport().convertListObjectInList(objects);

        String fileName = "RELATORIO_RELACAO_ORDEM_COMPRA_POR_FORNECEDOR_E_VENCIMENTO_"+ Conversor.converterDateInStringForReport(new Date());
        FileOutputStream fileOutputStream = this.createFileOutputStream(fileName);

        Workbook workbook = new HSSFWorkbook();
        Font font = workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);

        CreationHelper createHelper =workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("RELAÇÃO DE ORDEM DE COMPRA");

        Row row = (Row) sheet.createRow((short)0);
        this.generateTitleRow(row,style);
        Integer index = 1;
        for (PurchaseOrderForSupplierAndExpirationDateReport purchaseOrderForSupplierAndExpirationDateReport : purchaseOrderForSupplierAndExpirationDateReports){
            this.generateContentRow(sheet,purchaseOrderForSupplierAndExpirationDateReport,index);
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
        cell.setCellValue("NUMERO DA ORDEM");
        row.createCell(1).setCellValue("NUMERO DA NOTA");
        row.createCell(2).setCellValue("VALOR TOTAL");

        row.getCell(0).setCellStyle(cellStyle);
        row.getCell(1).setCellStyle(cellStyle);
        row.getCell(2).setCellStyle(cellStyle);

    }

    public void generateContentRow(Sheet sheet , PurchaseOrderForSupplierAndExpirationDateReport purchaseOrderForSupplierAndExpirationDateReport, Integer i) {
        HSSFRow contentRow = (HSSFRow) sheet.createRow(i);
        contentRow.createCell(0).setCellValue(purchaseOrderForSupplierAndExpirationDateReport.getCode());
        contentRow.createCell(1).setCellValue(purchaseOrderForSupplierAndExpirationDateReport.getTaxCode());
        contentRow.createCell(2).setCellValue(purchaseOrderForSupplierAndExpirationDateReport.getTotal().replace(".",","));

    }
}
