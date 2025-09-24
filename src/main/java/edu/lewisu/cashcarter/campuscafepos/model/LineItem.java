package edu.lewisu.cashcarter.campuscafepos.model;

import java.util.List;

public final class LineItem {

    private Product product;
    private int quantity;
    private List<LineItemModifier> lineItemModifiers;

    public LineItem(Product product, int quantity, List<LineItemModifier> lineItemModifiers) {
        this.product = product;
        this.quantity = quantity;
        this.lineItemModifiers = lineItemModifiers;
    }
}
