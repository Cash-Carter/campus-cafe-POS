package edu.lewisu.cashcarter.campuscafepos;

import java.io.File;
import java.math.BigDecimal;
import java.text.NumberFormat;

import edu.lewisu.cashcarter.campuscafepos.data.Menu;
import edu.lewisu.cashcarter.campuscafepos.model.Food;
import edu.lewisu.cashcarter.campuscafepos.model.Product;

public class Main {
    
    public static void main(String[] args) throws Exception {
        Menu menu = Menu.fromJson(new File("src/main/resources/Menu.json"));

        System.out.println("Welcome to Campus Cafe!");
        System.out.println();
        System.out.println("Menu:");

        for (Product product : menu.getProducts()) {
            System.out.printf("    %s (%s%d) %s\n",
                    product.getDisplayName(),
                    product instanceof Food ? "F" : "B",
                    product.getId(),
                    currencyFormat(product.getBasePrice()));
            System.out.println(product.getModifiers());
        }

        System.out.print("Enter item id (or 'done'): ");
    }

    public static String currencyFormat(BigDecimal n) {
        return NumberFormat.getCurrencyInstance().format(n);
    }
}
