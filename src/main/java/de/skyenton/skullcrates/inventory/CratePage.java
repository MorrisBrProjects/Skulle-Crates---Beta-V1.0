package de.skyenton.skullcrates.inventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CratePage {

    private String title;
    private int size;
    private Inventory inventory;

    public CratePage(String title, int size) {
        this.title = title;
        this.size = size;
        this.inventory = Bukkit.createInventory(null, size, title);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addItem(ItemStack itemStack) {
        getInventory().addItem(itemStack);
    }

    public void removeItem(ItemStack itemStack) {
        getInventory().removeItem(itemStack);
    }



}
