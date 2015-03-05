package com.purchasing.support.solicitation;

import com.purchasing.entity.SolicitationRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanessa on 3/5/15.
 */
public class SolicitationView {

    private String code;
    private String product;
    private String quantity;
    private String unit;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<SolicitationView> getList(List<SolicitationRequest>solicitationRequests){
        List<SolicitationView>solicitationViews = new ArrayList<>();
        for (SolicitationRequest solicitationRequest :solicitationRequests){
            if (solicitationRequest.getProduct() != null){
                SolicitationView solicitationView = new SolicitationView();

                String description = solicitationRequest.getProduct().getDescription() == null ? "" : solicitationRequest.getProduct().getDescription();
                String model = solicitationRequest.getProduct().getModel() == null ? "" : solicitationRequest.getProduct().getModel();
                String mark = solicitationRequest.getProduct().getMark() == null ? "" : solicitationRequest.getProduct().getMark();

                solicitationView.setCode(solicitationRequest.getProduct().getId().toString());
                solicitationView.setProduct(description+" "+model+" "+mark);
                solicitationView.setQuantity(solicitationRequest.getQuantity().toString());
                solicitationView.setUnit(solicitationRequest.getProduct().getUnit().getDescription());
                solicitationViews.add(solicitationView);
            }
        }
        return solicitationViews;
    }
}
