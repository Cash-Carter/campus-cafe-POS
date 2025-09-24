package edu.lewisu.cashcarter.campuscafepos.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.lewisu.cashcarter.campuscafepos.model.Product;

public class Menu {

    private List<Product> products = new ArrayList<Product>();

    public List<Product> getProducts() {
        return products;
    }

    @JsonCreator
    private Menu(@JsonProperty("products") List<Product> products) throws IOException {
        this.products = products;
    }

    public static Menu fromJson(File menuFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        
        Menu menu = mapper.readValue(menuFile, Menu.class);

        return menu;
    }
}
