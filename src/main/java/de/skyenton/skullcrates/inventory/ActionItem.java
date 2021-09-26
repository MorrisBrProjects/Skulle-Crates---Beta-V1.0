package de.skyenton.skullcrates.inventory;

import org.bukkit.inventory.ItemStack;

public class ActionItem {

    private ItemStack item;

    public ActionItem() {

    }
    public ActionItem(ItemStack itemStack) {
        this.item = itemStack;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }



    public void onItemClick(ItemStack item) {}
}
