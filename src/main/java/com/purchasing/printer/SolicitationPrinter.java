package com.purchasing.printer;


import com.purchasing.dao.SolicitationDAO;
import com.purchasing.entity.Solicitation;
import com.purchasing.entity.SolicitationRequest;
import com.purchasing.enumerator.TypeEnum;
import com.purchasing.printer.base.BasePrinter;
import com.purchasing.printer.impl.PrinterImpl;
import com.purchasing.support.date.Conversor;
import com.purchasing.support.solicitation.SolicitationView;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vanessa
 */
public class SolicitationPrinter extends PrinterImpl implements BasePrinter {

    @Inject
    private SolicitationDAO solicitationDAO;

    @Override
    public File generate(Long code) {
        String url = "/jasper/solicitacao.jrxml";
        Solicitation solicitation = solicitationDAO.findById(Solicitation.class,code);

        List<SolicitationView> solicitationViewList = new SolicitationView().getList(solicitation.getSolicitationRequests());
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(solicitationViewList);
        InputStream inputStream = this.getClass().getResourceAsStream(url);
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(inputStream), getMap(solicitation) , jrBeanCollectionDataSource);
        } catch (JRException e) {
            e.printStackTrace();
        }
        String nameFile = "Solicitação";
        this.printer(jasperPrint,nameFile);
        File file = this.download(nameFile);
        return file;
    }

    public Map getMap(Solicitation solicitation){
        Map map = new HashMap();
        Image img = new ImageIcon(getClass().getResource("/jasper/img/img.jpg")).getImage();
        String number = solicitation.getId().toString();
        String date = Conversor.converterDateTimeInString(solicitation.getInitialDate());
        String user = solicitation.getUser().getName();
        String costCenter = solicitation.getCostCenter().getCode()+" "+solicitation.getCostCenter().getDescription();
        String priority="";
        if ((solicitation.getUrgency() != null && solicitation.getUrgency() == true) && (solicitation.getEmergency() != null && solicitation.getEmergency() == false)){
            priority = "Urgencia";
        }else if ((solicitation.getUrgency() != null && solicitation.getUrgency() == false) && (solicitation.getEmergency() != null && solicitation.getEmergency() == true)){
            priority = "Emergencia";
        }

        String observation = solicitation.getObservation() == null ? "" : solicitation.getObservation();

        String typeService = "";
        String service = "";

        if (! solicitation.getType().equals(TypeEnum.Material)){

            for (SolicitationRequest solicitationRequest : solicitation.getSolicitationRequests()){
                if (solicitationRequest.getService() != null){
                    typeService = solicitationRequest.getService().getTypeService().getDescription();
                    service = solicitationRequest.getService().getDescription();
                }
            }
        }

        map.put("img",img);
        map.put("number",number);
        map.put("date",date);
        map.put("user",user);
        map.put("costCenter",costCenter);
        map.put("priority",priority);
        map.put("observation",observation);
        map.put("typeService",typeService);
        map.put("service",service);

        return map;
    }
}


