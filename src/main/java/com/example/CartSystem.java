package com.example;

import java.util.Map;

public class CartSystem extends TheSystem {
    CartSystem() {
    }

    @Override
    public void display() {
        System.out.println("Cart:");
        Double cartSubTotal = 0.0;
        System.out.printf("%-20s %-20s %-10s %-10s %-10s\n", "Name","Description","Price","Quantity", "Sub Total");
        for (Map.Entry<String, Item> entry : this.getItemCollection().entrySet()) {
            Item item = entry.getValue();
            Double subTotal = item.getItemPrice() * item.getQuantity();
            System.out.printf("%-20s %-20s %-10.2f %-10d %-10.2f\n", entry.getKey(),
                    item.getItemDesc(), item.getItemPrice(), item.getQuantity(), subTotal);
            cartSubTotal += subTotal;
        }
        Double tax = cartSubTotal * 0.05;
        System.out.printf("%-20s %-20.2f\n", "Pre-tax Total", cartSubTotal);
        System.out.printf("%-20s %-20.2f\n", "Tax", tax);
        System.out.printf("%-20s %-20.2f\n", "Total", tax + cartSubTotal);
    }
}
