package de.skyenton.skullcrates.crate;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Crate {

    private String name;
    private String skullName;
    private ArrayList<String> lore = new ArrayList<>();
    private ArrayList<ItemStack> items = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkullName() {
        return skullName;
    }

    public void setSkullName(String skullName) {
        this.skullName = skullName;
    }

    public ArrayList<String> getLore() {
        return lore;
    }

    public void setLore(ArrayList<String> lore) {
        this.lore = lore;
    }

    public ArrayList<ItemStack> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemStack> items) {
        this.items = items;
    }

}
