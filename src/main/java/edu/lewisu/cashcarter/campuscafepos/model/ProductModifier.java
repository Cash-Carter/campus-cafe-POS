package edu.lewisu.cashcarter.campuscafepos.model;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = ProductModifier.class)
public class ProductModifier extends Modifier {
    
    @JsonCreator
    private ProductModifier(@JsonProperty("option") String option, @JsonProperty("options") LinkedHashMap<String, String> options) {
        super(option, stringValuesToBigDecimal(options));
    }

    @Override
    public BigDecimal apply(BigDecimal value, String option) {
        return value.multiply(options.get(option));
    }

}
