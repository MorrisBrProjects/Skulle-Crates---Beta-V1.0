package de.skyenton.skullcrates.inventory.inventorys;

import de.skyenton.skullcrates.inventory.CrateInventory;
import de.skyenton.skullcrates.inventory.CratePage;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class CrateCreateInventory extends CrateInventory {

    public CrateCreateInventory(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onRemove() {

    }

    @Override
    public void onClick(CratePage clickedPage, ItemStack currentItem, InventoryClickEvent event) {

    }

}
