package de.skyenton.skullcrates.crate;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.Skull;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Crate implements ConfigurationSerializable {

    private String name;
    private String displayName;
    private String skullName;
    private ItemStack skull;



    private ItemStack crateItem;
    private List<String> lore = new ArrayList<>();
    private List<ItemStack> items = new ArrayList<>();

    private ToSetTypes currentToSet;

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

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public void setItems(List<ItemStack> items) {
        this.items = items;
    }

    public ItemStack getSkull() {
        return skull;
    }

    public void setSkull(String skullName) {
        this.skullName = skullName;
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(skullName);
        skull.setItemMeta((ItemMeta)meta);
        this.skull = skull;
    }

    public void setSkullField(ItemStack skull) {
        this.skull = skull;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", getName());
        map.put("displayName", getDisplayName());
        map.put("skullName", getSkullName());
        map.put("skull", getSkull());
        map.put("crateItem", getCrateItem());
        map.put("lore", getLore());
        map.put("items", getItems());
        return map;
    }

    public static Crate deserialize(Map<String, Object> args) {

        Crate crate = new Crate();
        crate.setName((String) args.get("name"));
        crate.setDisplayName((String) args.get("displayName"));
        crate.setSkullName((String) args.get("skullName"));
        crate.setSkullField((ItemStack) args.get("skull"));
        crate.setCrateItem((ItemStack) args.get("crateItem"));
        crate.setLore((List<String>) args.get("lore"));
        crate.setItems((List<ItemStack>) args.get("items"));
        return crate;
    }


    public ToSetTypes getCurrentToSet() {
        return currentToSet;
    }

    public void setCurrentToSet(ToSetTypes currentToSet) {
        this.currentToSet = currentToSet;
    }

    public ItemStack getCrateItem() {
        this.crateItem = getSkull().clone();
        ItemMeta meta = crateItem.getItemMeta();
        meta.setDisplayName(getDisplayName());
        meta.setLore(getLore());
        this.crateItem.setItemMeta(meta);

        return crateItem;
    }

    public void setCrateItem(ItemStack crateItem) {
        this.crateItem = crateItem;
    }
}
