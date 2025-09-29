package edu.lewisu.cashcarter.campuscafepos.model;

import java.math.BigDecimal;
import java.util.List;

public final class LineItem {

    private Product product;
    private int quantity;
    private List<LineItemModifier> lineItemModifiers;

    public Product getProduct() {return product;}
    public int getQuantity() {return quantity;}
    public List<LineItemModifier> getLineItemModifiers() {return lineItemModifiers;}

    public LineItem(Product product, int quantity, List<LineItemModifier> lineItemModifiers) {
        this.product = product;
        this.quantity = quantity;
        this.lineItemModifiers = lineItemModifiers;
    }

    public BigDecimal getPrice() {
        return getIndividualPrice().multiply(new BigDecimal(quantity));
    }

    private BigDecimal getIndividualPrice() {
        BigDecimal price = product.getBasePrice();
        
        for (LineItemModifier lineItemModifier : lineItemModifiers) {
            String option = lineItemModifier.getOption();
            price = lineItemModifier.getModifier().apply(price, option);
        }

        return price;
    }
}
