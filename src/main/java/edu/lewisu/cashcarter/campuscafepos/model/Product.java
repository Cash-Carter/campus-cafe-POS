package edu.lewisu.cashcarter.campuscafepos.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import edu.lewisu.cashcarter.campuscafepos.data.ProductDeserializer;

@JsonDeserialize(using = ProductDeserializer.class)
public abstract class Product {

    private int id;
    private String name;
    private BigDecimal basePrice;
    private List<Modifier> modifiers;

    public int getId() {return id;}
    public String getDisplayName() {return name;}
    public BigDecimal getBasePrice() {return basePrice;}
    public List<Modifier> getModifiers() {return modifiers;}

    public Product(int id, String name, String basePrice, List<Modifier> modifiers) {
        this.id = id;
        this.name = name;
        this.basePrice = new BigDecimal(basePrice);
        this.modifiers = modifiers;
    }
}
