package edu.lewisu.cashcarter.campuscafepos.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<LineItem> lineItems = new ArrayList<LineItem>();

    public List<LineItem> getLineItems() {return lineItems;}

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = new BigDecimal(0);

        for (LineItem lineItem : lineItems)
            subtotal = subtotal.add(lineItem.getPrice());
        
        return subtotal;
    }

    public Order() {}

    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }
}
