package edu.lewisu.cashcarter.campuscafepos;

import java.io.File;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import edu.lewisu.cashcarter.campuscafepos.data.Menu;
import edu.lewisu.cashcarter.campuscafepos.model.Beverage;
import edu.lewisu.cashcarter.campuscafepos.model.Food;
import edu.lewisu.cashcarter.campuscafepos.model.LineItem;
import edu.lewisu.cashcarter.campuscafepos.model.LineItemModifier;
import edu.lewisu.cashcarter.campuscafepos.model.Modifier;
import edu.lewisu.cashcarter.campuscafepos.model.Order;
import edu.lewisu.cashcarter.campuscafepos.model.Product;

public class Main {
    
    private Menu menu;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        new Main(Menu.fromJson(new File("src/main/resources/Menu.json")))
                .start();
    }

    public Main(Menu menu) {
        this.menu = menu;
    }

    public void start() {
        System.out.println("Welcome to Campus Cafe!");
        System.out.println();

        loop();
    }

    private void loop() {
        while (true) {

            printMenu();

            Order order = new Order();

            while (true) {
                System.out.print("Enter item id (or 'done'): ");

                String in = scanner.next()
                        .trim()
                        .toLowerCase();
                if (in.equals("done")) break;

                Product product = getProductFromId(in);
                if (product == null) {
                    System.out.printf("%s is not a valid product id or 'done'.\n", in);
                    continue;
                }

                List<LineItemModifier> lineItemModifiers = new ArrayList<LineItemModifier>();
                for (Modifier modifier : product.getModifiers()) {
                    Set<String> choices = modifier.getChoices();

                    while (true) {
                        System.out.printf("%s, [%s]: ",
                                modifier.getOption(),
                                modifierChoicesFormat(choices));

                        in = scanner.next()
                                .trim()
                                .toLowerCase();
                        
                        if (choices.contains(in)) break;

                        System.out.printf("%s is not a valid choice\n", in);
                    }

                    lineItemModifiers.add(new LineItemModifier(in, modifier));
                }

                int quantity = 0;
                while (true) {
                    System.out.print("Quantity: ");

                    try {
                        quantity = Integer.parseInt(scanner.next());
                    } catch (Exception e) {
                        System.out.printf("that is not a valid quantity\n");
                        continue;
                    }

                    if (quantity > 0) break;

                    System.out.printf("%d is not a valid quantity\n", quantity);
                }

                LineItem lineItem = new LineItem(product, quantity, lineItemModifiers);
                order.addLineItem(lineItem);
            }

            System.out.println("Receipt:");
            for (LineItem lineItem : order.getLineItems()) {
                Product product = lineItem.getProduct();
                System.out.printf("    %dx %s (%s%d) %s\n",
                        lineItem.getQuantity(),
                        product.getDisplayName(),
                        product instanceof Food ? "F" : "B",
                        product.getId(),
                        currencyFormat(lineItem.getPrice())
                        );
            }
            BigDecimal subtotal = order.getSubtotal();
            BigDecimal tax = subtotal.multiply(new BigDecimal("0.0625"));
            BigDecimal total = subtotal.add(tax);
            System.out.printf("Subtotal: %s\n", currencyFormat(subtotal));
            System.out.printf("Tax: %s\n", currencyFormat(tax));
            System.out.printf("Total: %s\n", currencyFormat(total));
        }
    }

    private void printMenu() {
        System.out.println("Menu:");

        List<Product> products = menu.getProducts();

        for (Product product : products)
            System.out.printf("    %s (%s%d) %s\n",
                    product.getDisplayName(),
                    product instanceof Food ? "F" : "B",
                    product.getId(),
                    currencyFormat(product.getBasePrice()));
    }

    private Product getProductFromId(String s) {
        Class<? extends Product> productClass;
        if (s.startsWith("f")) productClass = Food.class;
        else if (s.startsWith("b")) productClass = Beverage.class;
        else return null;

        int id;
        try {
            id = Integer.parseInt(s.substring(1));
        } catch (NumberFormatException e) {
            return null;
        }

        List<Product> products = menu.getProducts();

        for (Product product : products)
            if (product.getClass() == productClass && product.getId() == id)
                return product;
        return null;
    }

    private static String modifierChoicesFormat(Set<String> choices) {
        return String.join("/", choices);
    }

    private static String currencyFormat(BigDecimal n) {
        return NumberFormat.getCurrencyInstance().format(n);
    }
}
