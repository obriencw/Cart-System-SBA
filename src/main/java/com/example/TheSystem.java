package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public abstract class TheSystem {

    private HashMap<String, Item> itemCollection;


    TheSystem() {
        itemCollection = new HashMap<String, Item>();
        if (getClass().getSimpleName().equals("AppSystem")) {
            try {
                FileReader file = new FileReader(new File("resources/sample.txt"));
                Scanner filescan = new Scanner(file);
                while (filescan.hasNextLine()) {
                    String[] itemInfo = filescan.nextLine().split("  ");
                    Item it = new Item();
                    it.setItemName(itemInfo[0]);
                    it.setItemDesc(itemInfo[1]);
                    it.setItemPrice(Double.parseDouble(itemInfo[2]));
                    it.setAvailableQuantity(Integer.parseInt(itemInfo[3]));
                    itemCollection.put(it.getItemName(), it);
                }
                filescan.close();
            }
            catch (FileNotFoundException ex) {
                System.out.println("Unable to load file");
                ex.printStackTrace();


            }

        }
    }

    public HashMap<String, Item> getItemCollection(){
        return itemCollection;
    }

    public Boolean checkAvailability(Item item) {
        if (item.getAvailableQuantity() <= item.getQuantity()) {
            String notAvailable = "System is unable to add " + item.getItemName() + " to the cart. System only has "
                    + item.getAvailableQuantity() + " " + item.getItemName() + "s.";
            System.out.println(notAvailable);
            return false;
        } else {
            return true;
        }
    }

    public Boolean add(Item item) {
        if (item == null) {
            return false;
        } else if (this.itemCollection.containsKey(item.getItemName())){
            if (checkAvailability(item)) {
                Item collectItem = this.itemCollection.get(item.getItemName());
                int currentQuantity = collectItem.getQuantity();
                collectItem.setQuantity(currentQuantity + 1);
                this.itemCollection.put(item.getItemName(), collectItem);
                return true;
            } else {
                return false;
            }
        } else {
            this.itemCollection.put(item.getItemName(), item);
            return true;
        }
    }

    public Item remove(String itemName) {
        if (this.itemCollection.containsKey(itemName)) {
            return this.itemCollection.remove(itemName);
        } else {
            return null;
        }
    }

    public abstract void display();
}
