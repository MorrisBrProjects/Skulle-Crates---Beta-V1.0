package de.skyenton.skullcrates.inventory;

import de.skyenton.skullcrates.services.CrateService;
import org.bukkit.Bukkit;
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
        //plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void onClose(CratePage currentPage, InventoryCloseEvent event) {

    }

    public abstract void onCreate();
    public abstract void onRegister();
    public abstract void onUnRegister();
    public abstract void onClickInInventory(CratePage clickedPage, ItemStack currentItem, InventoryClickEvent event);
    public abstract void onClick(CratePage clickedPage, ItemStack currentItem, InventoryClickEvent event);
    public abstract void onNextPage(CratePage lastPage, CratePage newPage, int lastPageCount);
    public abstract void onBackPage(CratePage lastPage, CratePage newPage, int lastPageCount);
    public abstract void onSetPage(CratePage lastPage, CratePage newPage, int lastPageCount);


    @EventHandler
    public void onClickEvent(InventoryClickEvent event) {


        if(event.getInventory() != null && event.getClickedInventory() != null) {
            if(event.getInventory().getTitle().equals("§cCrate Inventar")) {
                event.setCancelled(true);
            }
        }


        if(event.getClickedInventory() == null || !getCurrentPage().getInventory().equals(event.getClickedInventory())) return;

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
                equals(event.getClickedInventory().
                        getTitle())) {

            if(getCurrentPage().isActionItem(event.getCurrentItem()) && event.getCurrentItem().isSimilar(getCurrentPage().getActionItem(event.getCurrentItem()).getItem())) {
                onActionItemClick(getCurrentPage(), getCurrentPage().getActionItem(event.getCurrentItem()), event);
                getCurrentPage().getActionItem(event.getCurrentItem()).onItemClick(event.getCurrentItem());
            }
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

    protected abstract void onActionItemClick(CratePage currentPage, ActionItem actionItem, InventoryClickEvent event);

    @EventHandler
    public void onCloseEvent(InventoryCloseEvent event) {

        if(event.getInventory() == null || !getCurrentPage().getInventory().equals(event.getInventory())) return;

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

    public int getCurrentPageAsCount() {
        return currentPage;
    }

    public void nextPage() {
        onNextPage(getCurrentPage(), getNextPage(), getCurrentPageAsCount());
        currentPage++;
    }

    public void backPage(UUID playerUuid) {
        onBackPage(getCurrentPage(), getBackPage(), getCurrentPageAsCount());
        currentPage--;
    }

    public CratePage getNextPage() {
        return pages.get(getCurrentPageAsCount()+1);
    }

    public CratePage getBackPage() {
        return pages.get(getCurrentPageAsCount()-1);
    }

    public CratePage getPage(int index) {
        return pages.get(index);
    }

    public void openPage(CratePage page) {
        for (UUID uuid : crateService.getUsedInventorys().keySet()) {
            if(crateService.getUsedInventorys().get(uuid).equals(this)) {
                //Bukkit.getPlayer(uuid).closeInventory();


                Bukkit.getPlayer(uuid).openInventory(page.getInventory());

                for (Integer index : pages.keySet()) {
                    if(pages.get(index).equals(page)) {
                        currentPage = index;
                    }
                }
            }
        }
    }

    public void addPage(CratePage page) {
        if(!pages.isEmpty()) {
            //System.out.println(pages.size());
            pages.put(pages.size(), page);
        } else {
            //System.out.println(pages.size() + "df");
            pages.put(0, page);
        }
    }

    public void setPage(int index) {
        onSetPage(getCurrentPage(), pages.get(index), getCurrentPageAsCount());
        this.currentPage = index;
    }

    /**
    Close inventory, but not the GUI.
    Player will removed in Inventory Open Temp!
     **/
    public void closeTempInventory() {
        for (UUID uuid : crateService.getUsedInventorys().keySet()) {
            if (crateService.getUsedInventorys().get(uuid).equals(this)) {
                crateService.getUsedInventorys().remove(uuid, this);
                //System.out.println("gfjhgzhkgh");
                currentPage = 0; //copierte liste muss ich zugreifen zum bearbeiten / löschen
                break;
            }
        }
    }

    public Player getInvOwner() {
        for (UUID uuid : crateService.getUsedInventorys().keySet()) {
            if (crateService.getUsedInventorys().get(uuid).equals(this)) {
                return Bukkit.getPlayer(uuid);
            }
        }
        return null;
    }

    public void close() {
        for (UUID uuid : crateService.getUsedInventorys().keySet()) {
            if (crateService.getUsedInventorys().get(uuid).equals(this)) {
                Bukkit.getPlayer(uuid).closeInventory();
                crateService.getUsedInventorys().remove(uuid);
                break;
            }
        }
    }

    public HashMap<Integer, CratePage> getPages() {
        return pages;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public CrateService getCrateService() {
        return crateService;
    }

}
