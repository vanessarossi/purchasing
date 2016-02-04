package com.purchasing.support.excel;

import com.purchasing.dao.ReportDAO;
import com.purchasing.support.date.Conversor;
import com.purchasing.support.excel.base.BaseFile;
import com.purchasing.support.excel.entity.PurchaseOrderAndSolicitationReport;
import com.purchasing.support.excel.entity.principal.Report;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Vanessa
 * @date 2/4/16
 */
public class ExportPurchaseOrderAndSolicitationReport extends BaseFolderImpl implements BaseFile {

    @Inject
    private ReportDAO reportDAO;

    @Override
    public File generateReport(Collection<Object> objects) {
        return null;
    }

    @Override
    public File generateReportWithMultipleSearch(Report report) {
        File file = null;

        String fileName = "RELATORIO_ACOMPANHAMENTO_DE_SOLICITAÇÃO_E_ORDEM_"+ Conversor.converterDateInStringForReport(new Date());
        FileOutputStream fileOutputStream = this.createFileOutputStream(fileName);

        Workbook workbook = new HSSFWorkbook();
        Font font = workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);

        CreationHelper createHelper =workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("ACOMPANHAMENTO");

        /** CRIANDO TITULOS SOLICITACAO **/
        Row row = (Row) sheet.createRow((short)0);
        createMessageTitle(sheet,row,style,0,0,0,0,1,"SOLICITAÇÕES INICIADAS");
        createMessageTitle(sheet,row,style,3,0,0,3,4,"SOLICITAÇÕES RECUSADAS");
        createMessageTitle(sheet,row,style,6,0,0,6,7,"SOLICITAÇÕES CANCELADAS");
        createMessageTitle(sheet,row,style,9,0,0,9,10,"SOLICITAÇÕES FINALIZADAS");

        Row rowTitle = (Row) sheet.createRow((short)1);
        this.generateTitleRow(0,1,rowTitle,style);
        this.generateTitleRow(3,4,rowTitle,style);
        this.generateTitleRow(6,7,rowTitle,style);
        this.generateTitleRow(9,10,rowTitle,style);

        /** CRIANDO TITULOS ORDEM COMPRA **/
        Row rowPurchaseOrder = (Row) sheet.createRow((short)14);
        createMessageTitle(sheet,rowPurchaseOrder,style,0,14,14,0,1,"ORDENS INICIADAS");
        createMessageTitle(sheet,rowPurchaseOrder,style,3,14,14,3,4,"ORDENS RECUSADAS");
        createMessageTitle(sheet,rowPurchaseOrder,style,6,14,14,6,7,"ORDENS CANCELADAS");
        createMessageTitle(sheet,rowPurchaseOrder,style,9,14,14,9,10,"ORDENS FINALIZADAS");

        Row rowTitlePurchaseOrder = (Row) sheet.createRow((short)15);
        this.generateTitleRow(0,1,rowTitlePurchaseOrder,style);
        this.generateTitleRow(3,4,rowTitlePurchaseOrder,style);
        this.generateTitleRow(6,7,rowTitlePurchaseOrder,style);
        this.generateTitleRow(9,10,rowTitlePurchaseOrder,style);

        /**SOLICITACOES**/
        Collection<Object> objects = reportDAO.getTotalSolicitationReport(report);
        Collection<Object> objectsRefused =  reportDAO.getTotalSolicitationRefusedReport(report);
        Collection<Object> objectsCancelled = reportDAO.getTotalSolicitationCancelledReport(report);
        Collection<Object> objectsFinished = reportDAO.getTotalSolicitationFinishedReport(report);

        List<PurchaseOrderAndSolicitationReport> solicitationReports = new PurchaseOrderAndSolicitationReport().convertListObjectInList(objects);
        List<PurchaseOrderAndSolicitationReport> solicitationReportsRefused = new PurchaseOrderAndSolicitationReport().convertListObjectInList(objectsRefused);
        List<PurchaseOrderAndSolicitationReport> solicitationReportsCancelled = new PurchaseOrderAndSolicitationReport().convertListObjectInList(objectsCancelled);
        List<PurchaseOrderAndSolicitationReport> solicitationReportsFinished = new PurchaseOrderAndSolicitationReport().convertListObjectInList(objectsFinished);

        Integer index = 2;
        for (int i = 0; i < 12; i++ ){
            Row rowContent = (Row) sheet.createRow(index);
            if (i < solicitationReports.size()){
                this.generateContentRow(0,1,rowContent,solicitationReports.get(i),index);
            }
            if (i < solicitationReportsRefused.size()){
                this.generateContentRow(3,4,rowContent,solicitationReportsRefused.get(i),index);
            }
            if (i < solicitationReportsCancelled.size()){
                this.generateContentRow(6,7,rowContent,solicitationReportsCancelled.get(i),index);
            }
            if (i < solicitationReportsFinished.size()){
                this.generateContentRow(9,10,rowContent,solicitationReportsFinished.get(i),index);
            }
            index ++;
        }

        /**SOLICITACOES**/
        Collection<Object> objectsPurchaseOrder = reportDAO.getTotalPurchaseOrderReport(report);
        Collection<Object> objectsPurchaseOrderRefused =  reportDAO.getTotalPurchaseOrderRefusedReport(report);
        Collection<Object> objectsPurchaseOrderCancelled = reportDAO.getTotalPurchaseOrderCancelledReport(report);
        Collection<Object> objectsPurchaseOrderFinished = reportDAO.getTotalPurchaseOrderFinishedReport(report);

        List<PurchaseOrderAndSolicitationReport> purchasedOrder = new PurchaseOrderAndSolicitationReport().convertListObjectInList(objectsPurchaseOrder);
        List<PurchaseOrderAndSolicitationReport> purchasedOrderRefused = new PurchaseOrderAndSolicitationReport().convertListObjectInList(objectsPurchaseOrderRefused);
        List<PurchaseOrderAndSolicitationReport> purchasedOrderCancelled = new PurchaseOrderAndSolicitationReport().convertListObjectInList(objectsPurchaseOrderCancelled);
        List<PurchaseOrderAndSolicitationReport> purchasedOrderFinished = new PurchaseOrderAndSolicitationReport().convertListObjectInList(objectsPurchaseOrderFinished);

        index = 16;
        for (int i = 0; i < 12; i++ ){
            Row rowContent = (Row) sheet.createRow(index);
            if (i < purchasedOrder.size()){
                this.generateContentRow(0,1,rowContent,purchasedOrder.get(i),index);
            }
            if (i < purchasedOrderRefused.size()){
                this.generateContentRow(3,4,rowContent,purchasedOrderRefused.get(i),index);
            }
            if (i < purchasedOrderCancelled.size()){
                this.generateContentRow(6,7,rowContent,purchasedOrderCancelled.get(i),index);
            }
            if (i < purchasedOrderFinished.size()){
                this.generateContentRow(9,10,rowContent,purchasedOrderFinished.get(i),index);
            }
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

    public void createMessageTitle(Sheet sheet,Row row,CellStyle cellStyle,Integer initialCell,Integer initialRow,Integer lastRow,Integer initialColumn,Integer lastColumn,String title){
        Cell cell = row.createCell(initialCell);
        cell.setCellValue(title);
        cell.setCellStyle(cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(
                initialRow, //first row (0-based)
                lastRow, //last row  (0-based)
                initialColumn, //first column (0-based)
                lastColumn  //last column  (0-based)
        ));
    }

    public void generateTitleRow(Integer initialCell,Integer lastCell,Row row, CellStyle cellStyle){
        Cell cell = row.createCell(initialCell);
        cell.setCellValue("MÊS");
        row.createCell(lastCell).setCellValue("TOTAL");

        row.getCell(initialCell).setCellStyle(cellStyle);
        row.getCell(lastCell).setCellStyle(cellStyle);
    }

    public void generateContentRow(Integer initialCell,Integer lastCell,Row row,PurchaseOrderAndSolicitationReport purchaseOrderAndSolicitationReport,Integer i) {
        row.createCell(initialCell).setCellValue(purchaseOrderAndSolicitationReport.getMonth());
        row.createCell(lastCell).setCellValue(purchaseOrderAndSolicitationReport.getTotal());
    }



}
