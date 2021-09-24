package de.skyenton.skullcrates.inventory;

import de.skyenton.skullcrates.SkullcratesPlugin;
import de.skyenton.skullcrates.services.CrateService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public abstract class CrateInventory implements Listener {

    private int currentPage;
    private HashMap<Integer, CratePage> pages = new HashMap<>();
    private JavaPlugin plugin;
    private CrateService crateService;
    private Player player;

    public CrateInventory(JavaPlugin plugin, CrateService crateService) {
        this.plugin = plugin;
        this.crateService = crateService;
        onCreate();
    }





    public void onOpen(Player uuid) {
        currentPage = 0;
    }

    public void onClose(CratePage currentPage, InventoryCloseEvent event) {

    }

    public abstract void onCreate();
    public abstract void onRegister();
    public abstract void onUnRegister();
    public abstract void onClickInInventory(CratePage clickedPage, ItemStack currentItem, InventoryClickEvent event);
    public abstract void onClick(CratePage clickedPage, ItemStack currentItem, InventoryClickEvent event);


    @EventHandler
    public void onClickEvent(InventoryClickEvent event) {
        if(event.getInventory() != null
                && event.getClickedInventory() != null
                && event.getCurrentItem() != null && crateService.getOpenedCrateInventory(event.getWhoClicked().getUniqueId()) != null
                && crateService.getOpenedCrateInventory(event.getWhoClicked().getUniqueId()).getCurrentPage().getTitle().
                equals(event.getClickedInventory().
                        getTitle())) {
            onClickInInventory(getCurrentPage(), event.getCurrentItem(), event);
        }

        if(event.getInventory() != null
                && event.getClickedInventory() != null
                && event.getCurrentItem() != null && crateService.getOpenedCrateInventory(event.getWhoClicked().getUniqueId()) != null
                && crateService.getOpenedCrateInventory(event.getWhoClicked().getUniqueId()).getCurrentPage().getTitle().
                equals(event.getInventory().
                        getTitle())) {
            onClick(getCurrentPage(), event.getCurrentItem(), event);
        }
    }

    @EventHandler
    public void onCloseEvent(InventoryCloseEvent event) {
        if(crateService.getOpenedCrateInventory(event.getPlayer().getUniqueId()) != null && crateService.getOpenedCrateInventory(event.getPlayer().getUniqueId()).getCurrentPage().getTitle().equals(event.getInventory().getTitle())) {
            crateService.getOpenedCrateInventory(event.getPlayer().getUniqueId()).onClose(getCurrentPage(), event);
        }
    }


    public void register() {
        onRegister();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void unregister() {
        onUnRegister();
        HandlerList.unregisterAll(this);
    }

    public CratePage getCurrentPage() {
        return pages.get(currentPage);
    }

    public Integer getCurrentPageAsCount() {
        return currentPage;
    }

    public void nextPage() {
        currentPage++;
    }

    public void backPage(UUID playerUuid) {
        currentPage--;
    }

    public CratePage getNextPage() {
        return pages.get(getCurrentPageAsCount()+1);
    }

    public CratePage getBackPage() {
        return pages.get(getCurrentPageAsCount()-1);
    }

    public void addPage(CratePage page) {
        pages.put(pages.size()+1, page);
    }

    public void setPage(int index, CratePage page) {
        pages.put(index, page);
    }

    public void close() {
        player.closeInventory();
    }

}
