package de.skyenton.skullcrates.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {


    private String displayName;
    private List<String> lore = new ArrayList<>();
    private Material material;
    private short sort;
    private int count;

    public ItemBuilder(String displayName, List<String> lore, Material material, short sort, int count) {
        this.displayName = displayName;
        this.lore = lore;
        this.material = material;
        this.sort = sort;
        this.count = count;
    }

    public ItemBuilder(String displayName, Material material, short sort, int count) {
        this.displayName = displayName;
        this.material = material;
        this.sort = sort;
        this.count = count;
    }

    public ItemBuilder(Material material) {
        this.material = material;
    }

    public ItemBuilder() {}

    public ItemStack build() {
        ItemStack item = new ItemStack(material, count, sort);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }




    public String getDisplayName() {
        return displayName;
    }

    public ItemBuilder setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public List<String> getLore() {
        return lore;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public ItemBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public short getSort() {
        return sort;
    }

    public ItemBuilder setSort(short sort) {
        this.sort = sort;
        return this;
    }

    public int getCount() {
        return count;
    }

    public ItemBuilder setCount(int count) {
        this.count = count;
        return this;
    }
}