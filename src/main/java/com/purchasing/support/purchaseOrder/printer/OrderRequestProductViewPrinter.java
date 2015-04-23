package com.purchasing.support.purchaseOrder.printer;

import com.purchasing.support.purchaseOrder.OrderRequestProductView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Vanessa on 4/23/15.
 */
public class OrderRequestProductViewPrinter implements Comparator<OrderRequestProductViewPrinter> {

    private String item;
    private String description;
    private String quantity;
    private String unity;
    private String unity_price;
    private String total_price;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public String getUnity_price() {
        return unity_price;
    }

    public void setUnity_price(String unity_price) {
        this.unity_price = unity_price;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    @Override
    public int compare(OrderRequestProductViewPrinter o1, OrderRequestProductViewPrinter o2) {
        return 0;
    }

    public List<OrderRequestProductViewPrinter> generateList(List<OrderRequestProductView> orderRequestsOrderRequestProductViews) {
        List<OrderRequestProductViewPrinter> orderRequestProductV = new ArrayList<>();
        Integer count = 1;
        for (OrderRequestProductView orderRequestProductView : orderRequestsOrderRequestProductViews) {

            OrderRequestProductViewPrinter orderRequestProductViewPrinter = new OrderRequestProductViewPrinter();
            String description = orderRequestProductView.getProduct().getDescription();
            String model = orderRequestProductView.getProduct().getModel() == null ? "" : orderRequestProductView.getProduct().getModel();
            String mark = orderRequestProductView.getProduct().getMark();
            String unity = orderRequestProductView.getProduct().getUnit().getDescription();

                orderRequestProductViewPrinter.setItem(count.toString());
                orderRequestProductViewPrinter.setDescription(description + " " + model + " " + mark);
                orderRequestProductViewPrinter.setQuantity(orderRequestProductView.getQuantity().toString().replace(".",","));
                orderRequestProductViewPrinter.setUnity(unity);
                orderRequestProductViewPrinter.setUnity_price(orderRequestProductView.getUnityPrice().toString().replace(".", ","));
                orderRequestProductViewPrinter.setTotal_price(orderRequestProductView.getTotalPrice().toString().replace(".", ","));

            orderRequestProductV.add(orderRequestProductViewPrinter);
            count += 1;
        }
        return orderRequestProductV;
    }

}
