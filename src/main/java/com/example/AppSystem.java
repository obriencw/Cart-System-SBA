package com.example;

import java.util.Map;

public class AppSystem extends TheSystem {
    AppSystem() {
    }

    @Override
    public void display() {
        System.out.printf("AppSystem Inventory:\n%-20s %-20s %-10s %-10s\n", "Name","Description","Price","Available Quantity");
        for (Map.Entry<String, Item> entry : this.getItemCollection().entrySet()) {
            Item item = entry.getValue();
            System.out.printf("%-20s %-20s %-10.2f %-10d\n", entry.getKey(), item.getItemDesc(), item.getItemPrice(), item.getAvailableQuantity());
        }
//        System.out.printf("%-20s",entry.getKey() + " " + item.getItemDesc() + " " + item.getItemPrice() + " " + item.getAvailableQuantity());

    }

    @Override      // this overwrites the parents class add method
    public Boolean add(Item item) {
        if (item == null) {
            return false;
        }
        if (getItemCollection().containsKey(item.getItemName())) {
            System.out.println(item.getItemName() + " is already in the App System");
            return false;
        }
        else {
            this.getItemCollection().put(item.getItemName(), item);
            return true;
        }

    }


    public Item reduceAvailableQuantity(String item_name) {
        if (this.getItemCollection().containsKey(item_name)){
            Item collectItem = this.getItemCollection().get(item_name);
            int currentQuantity = collectItem.getAvailableQuantity() - 1;
            if (currentQuantity == 0) {
                getItemCollection().remove(item_name);
                return null;
            } else {
                collectItem.setAvailableQuantity(currentQuantity);
                return collectItem;
            }
        } else {
            return null;
        }

    }
}
