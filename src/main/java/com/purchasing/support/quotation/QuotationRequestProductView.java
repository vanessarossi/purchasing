package com.purchasing.support.quotation;

import com.purchasing.entity.Product;
import com.purchasing.entity.QuotationRequest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author vanessa
 */
public class QuotationRequestProductView  implements Comparator<QuotationRequestProductView> {

    private Long id;
    private Product product;
    private Float quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compare(QuotationRequestProductView o1, QuotationRequestProductView o2) {
        return 0;
    }

    public List<QuotationRequestProductView> generateList(List<QuotationRequest> quotationRequests){
        List<QuotationRequestProductView> requestProductViews = new ArrayList<>();
        for (QuotationRequest quotationRequest : quotationRequests){
            QuotationRequestProductView quotationRequestProductView = new QuotationRequestProductView();
                quotationRequestProductView.setId(quotationRequest.getId());
                quotationRequestProductView.setProduct(quotationRequest.getSolicitationRequest().getProduct());
                quotationRequestProductView.setQuantity(quotationRequest.getSolicitationRequest().getQuantity());
            requestProductViews.add(quotationRequestProductView);
        }
        return requestProductViews;
    }
}
