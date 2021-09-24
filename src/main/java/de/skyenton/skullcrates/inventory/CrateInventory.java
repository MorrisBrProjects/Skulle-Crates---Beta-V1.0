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
    public void onClickEvent(InventoryClickEvent event) {
        if(event.getInventory() != null && event.getClickedInventory() != null && event.getCurrentItem() != null) {
            onClick(getCurrentPage(event.getWhoClicked().getUniqueId()), event.getCurrentItem(), event);
        }
    }


    public void register() {
        onCreate();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void unregister() {
        onRemove();
        HandlerList.unregisterAll(this);
    }

    public CratePage getCurrentPage(UUID playerUuid) {
        return pages.get(currentPage.get(playerUuid));
    }

    public Integer getCurrentPageAsCount(UUID playerUuid) {
        return currentPage.get(playerUuid);
    }

    public void nextPage(UUID playerUuid) {
        currentPage.put(playerUuid, currentPage.get(playerUuid)+1);
    }

    public void backPage(UUID playerUuid) {
        currentPage.put(playerUuid, currentPage.get(playerUuid)-1);
    }

    public CratePage getNextPage(UUID playerUuid) {
        return pages.get(getCurrentPageAsCount(playerUuid)+1);
    }

    public CratePage getBackPage(UUID playerUuid) {
        return pages.get(getCurrentPageAsCount(playerUuid)-1);
    }

    public void addPage(CratePage page) {
        pages.put(pages.size()+1, page);
    }

}
