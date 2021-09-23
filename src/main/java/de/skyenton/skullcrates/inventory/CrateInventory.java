package de.skyenton.skullcrates.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public abstract class CrateInventory implements Listener {

    private HashMap<UUID, Integer> currentPage;
    private HashMap<Integer, CratePage> pages = new HashMap<>();
    private JavaPlugin plugin;

    public CrateInventory(JavaPlugin plugin) {
        this.plugin = plugin;
    }




    public void onOpen(UUID uuid) {
        currentPage.put(uuid, 0);
    }

    public void onClose(UUID uuid) {
        currentPage.remove(uuid);
    }

    public abstract void onCreate();
    public abstract void onRemove();
    public abstract void onClick(CratePage clickedPage, ItemStack currentItem, InventoryClickEvent event);


    @EventHandler
    public void onPlayerJoin(InventoryClickEvent event) {
        if(event.ge)
        onClick(getCurrentPage(), event.getCurrentItem(), event);
    }


    public void register() {
        onCreate();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void unregister() {
        onRemove();
        HandlerList.unregisterAll(this);
    }

    public CratePage getCurrentPage() {
        return pages.get(currentPage.get(playerUuid));
    }

    public Integer getCurrentPageAsCount() {
        return currentPage.get(playerUuid);
    }

    public void nextPage() {
        currentPage.put(playerUuid, currentPage.get(playerUuid)+1);
    }

    public void backPage() {
        currentPage.put(playerUuid, currentPage.get(playerUuid)-1);
    }

    public CratePage getNextPage() {
        return pages.get(getCurrentPageAsCount()+1);
    }

    public CratePage getBackPage() {
        return pages.get(currentPage-1);
    }

}
