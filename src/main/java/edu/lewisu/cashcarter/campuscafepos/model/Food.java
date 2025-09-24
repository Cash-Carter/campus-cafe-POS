package edu.lewisu.cashcarter.campuscafepos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Food.class)
public class Food extends Product {

    @JsonCreator
    public Food(@JsonProperty("id") int id,
            @JsonProperty("name") String name,
            @JsonProperty("price") String basePrice,
            @JsonProperty("modifiers") List<Modifier> modifiers) {
        super(id, name, basePrice, modifiers);
    }
}
