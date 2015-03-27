package com.purchasing.support.quotation;

import com.purchasing.entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public class QuotationRequestServiceView {

    private Long id;
    private Service service;
    private Solicitation solicitation;
    private TypeService typeService;
    private CostCenter costCenter;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Solicitation getSolicitation() {
        return solicitation;
    }

    public void setSolicitation(Solicitation solicitation) {
        this.solicitation = solicitation;
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public CostCenter getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(CostCenter costCenter) {
        this.costCenter = costCenter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuotationRequestServiceView> generateList(List<QuotationRequest> quotationRequests){
        List<QuotationRequestServiceView> quotationRequestServiceViews = new ArrayList<>();
            for (QuotationRequest quotationRequest : quotationRequests){

                QuotationRequestServiceView quotationRequestServiceView = new QuotationRequestServiceView();
                quotationRequestServiceView.setId(quotationRequest.getId());
                quotationRequestServiceView.setSolicitation(quotationRequest.getSolicitationRequest().getSolicitation());
                quotationRequestServiceView.setCostCenter(quotationRequest.getSolicitationRequest().getSolicitation().getCostCenter());
                quotationRequestServiceView.setTypeService(quotationRequest.getSolicitationRequest().getService().getTypeService());
                quotationRequestServiceView.setService(quotationRequest.getSolicitationRequest().getService());

                Integer length = 0;
                if (quotationRequest.getSolicitationRequest().getService().getDescription().length() >= 70){
                    length = 70;
                }else{
                    length =  quotationRequest.getSolicitationRequest().getService().getDescription().length();
                }
                quotationRequestServiceView.setDescription(quotationRequest.getSolicitationRequest().getService().getDescription().substring(0,length));
                quotationRequestServiceViews.add(quotationRequestServiceView);
            }

        return quotationRequestServiceViews;
    }

}
