package com.purchasing.printer.report;

import com.purchasing.dao.ReceptionDAO;
import com.purchasing.entity.Reception;
import com.purchasing.printer.impl.BasePrinterImpl;
import com.purchasing.support.date.Conversor;
import com.purchasing.support.report.ReceptionByDateView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.util.List;

/**
 * @author Vanessa
 */
public class ReceptionByDatePrinter extends BasePrinterImpl {

    @Inject private ReceptionDAO receptionDAO;

    public File generateOrder(Date initialDate , Date finalDate) {
        String url = "/jasper/report_reception_by_date.jrxml";
        List<ReceptionByDateView> receptionByDateViews = new ArrayList<>();
        List<Reception> receptions =  receptionDAO.searchByDate(initialDate,finalDate);
        receptionByDateViews = new ReceptionByDateView().generateList(receptions);

        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(receptionByDateViews);

        InputStream inputStream = this.getClass().getResourceAsStream(url);
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(inputStream), getMap(initialDate,finalDate), jrBeanCollectionDataSource);
        } catch (JRException e) {
            e.printStackTrace();
        }
        String nameFile = "relatorio_recepcao";
        this.printer(jasperPrint, nameFile);
        File file = this.download(nameFile);
        return file;
    }

    public Map getMap(Date initialDate, Date finalDate) {
        Map map = new HashMap();
        Image img = new ImageIcon(getClass().getResource("/jasper/img/img.jpg")).getImage();
        map.put("img", img);
        map.put("initialDate", Conversor.converterDateInString(initialDate));
        map.put("finalDate",Conversor.converterDateInString(finalDate));
        return map;
    }


}
