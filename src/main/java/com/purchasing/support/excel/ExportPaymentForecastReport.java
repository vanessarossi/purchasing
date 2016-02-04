package com.purchasing.support.excel;

import com.purchasing.support.date.Conversor;
import com.purchasing.support.excel.base.BaseFile;
import com.purchasing.support.excel.entity.PaymentForecastReport;
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
 * Created by Vanessa on 12/30/15.
 */
public class ExportPaymentForecastReport extends BaseFolderImpl implements BaseFile {

    @Override
    public File generateReport(Collection<Object> objects) {

        File file = null;
        List<PaymentForecastReport> paymentForecastReports = new PaymentForecastReport().convertListObjectInList(objects);

        String fileName = "RELATORIO_PREVISAO_PAGAMENTO_SISTEMA_COMPRAS_"+ Conversor.converterDateInStringForReport(new Date());
        FileOutputStream fileOutputStream = this.createFileOutputStream(fileName);

        Workbook workbook = new HSSFWorkbook();
        Font font = workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);

        CreationHelper createHelper =workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("COMPRAS A RECEBER - SISTEMA DE COMPRAS");

        Row row = (Row) sheet.createRow((short)0);
        this.generateTitleRow(row,style);
        Integer index = 1;
        for (PaymentForecastReport paymentForecastReport : paymentForecastReports){
            this.generateContentRow(sheet,paymentForecastReport,index);
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
        row.createCell(1).setCellValue("FORNECEDOR");
        row.createCell(2).setCellValue("CENTRO DE CUSTO");
        row.createCell(3).setCellValue("VALOR DO CENTRO DE CUSTO");
        row.createCell(4).setCellValue("COMPRA REALIZADA ANTES DA ORDEM");
        row.createCell(5).setCellValue("COMPRA DE INVESTIMENTO");
        row.createCell(6).setCellValue("FORMA DE PAGAMENTO");
        row.createCell(7).setCellValue("DATA PRIMEIRA PARCELA");
        row.createCell(8).setCellValue("DATA ULTIMA PARCELA");
        row.createCell(9).setCellValue("DATA DE ENTRADA");
        row.createCell(10).setCellValue("VENCIMENTO");
        row.createCell(11).setCellValue("VALOR DA ENTRADA");
        row.createCell(12).setCellValue("VALOR PARCELA");
        row.createCell(13).setCellValue("VALOR TOTAL DA COMPRA");


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
        row.getCell(11).setCellStyle(cellStyle);
        row.getCell(12).setCellStyle(cellStyle);
        row.getCell(13).setCellStyle(cellStyle);


    }

    public void generateContentRow(Sheet sheet , PaymentForecastReport paymentForecastReport, Integer i) {
        HSSFRow contentRow = (HSSFRow) sheet.createRow(i);
        contentRow.createCell(0).setCellValue(paymentForecastReport.getOrderNumber());
        contentRow.createCell(1).setCellValue(paymentForecastReport.getSupplier());
        contentRow.createCell(2).setCellValue(paymentForecastReport.getCostCenter());
        contentRow.createCell(3).setCellValue(paymentForecastReport.getTotalPriceCostCenter());
        contentRow.createCell(4).setCellValue(paymentForecastReport.getAlreadyPurchased());
        contentRow.createCell(5).setCellValue(paymentForecastReport.getInvestmentPurchase());
        contentRow.createCell(6).setCellValue(paymentForecastReport.getFormPayment());
        contentRow.createCell(7).setCellValue(paymentForecastReport.getDateFirstInstallment());
        contentRow.createCell(8).setCellValue(paymentForecastReport.getDateLastInstallment());
        contentRow.createCell(9).setCellValue(paymentForecastReport.getDateInput());
        contentRow.createCell(10).setCellValue(paymentForecastReport.getExpirateDate());
        contentRow.createCell(11).setCellValue(paymentForecastReport.getInputPrice());
        contentRow.createCell(12).setCellValue(paymentForecastReport.getSharePrice());
        contentRow.createCell(13).setCellValue(paymentForecastReport.getTotalFinalPrice());

    }

}
