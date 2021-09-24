package de.skyenton.skullcrates.inventory.inventorys;

import de.skyenton.skullcrates.inventory.CrateInventory;
import de.skyenton.skullcrates.inventory.CratePage;
import de.skyenton.skullcrates.services.CrateService;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class CrateCreateInventory extends CrateInventory {

    public CrateCreateInventory(JavaPlugin plugin, CrateService crateService) {
        super(plugin, crateService);
        System.out.println("construktor");
        CratePage page = new CratePage("test", 9*3);
        setPage(0, page);
        System.out.println("create");
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onOpen(Player uuid) {

    }

    @Override
    public void onRegister() {
        System.out.println("register");
    }

    @Override
    public void onUnRegister() {
        System.out.println("unregister");
    }

    @Override
    public void onClick(CratePage clickedPage, ItemStack currentItem, InventoryClickEvent event) {
        System.out.println("click");
    }

    @Override
    public void onClose(CratePage currentPage, InventoryCloseEvent event) {
        System.out.println("close");
        System.out.println(getCurrentPage().getTitle() + " closed");
    }
}
