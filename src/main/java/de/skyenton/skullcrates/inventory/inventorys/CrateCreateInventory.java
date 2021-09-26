package de.skyenton.skullcrates.inventory.inventorys;

import de.skyenton.skullcrates.inventory.ActionItem;
import de.skyenton.skullcrates.inventory.CrateInventory;
import de.skyenton.skullcrates.inventory.CratePage;
import de.skyenton.skullcrates.services.CrateService;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class CrateCreateInventory extends CrateInventory {

    public CrateCreateInventory(JavaPlugin plugin, CrateService crateService) {
        super(plugin, crateService);
        System.out.println("construktor");
        System.out.println("create");
    }

    @Override
    public void onCreate() {
        CratePage page1 = new CratePage("§e§lCrate§7- §aErstellen - Page1", 9*3);
        page1.addItem(new ItemStack(Material.GLASS, 1));
        ActionItem item1 = new ActionItem(new ItemStack(Material.STAINED_CLAY, 1)) {
            @Override
            public void onItemClick(ItemStack item) {
                openPage(getNextPage());
            }
        };
        page1.addActionItem(item1);
        addPage(page1);
        page1.fillInventory(new ItemStack(Material.STAINED_GLASS));


        CratePage page2 = new CratePage("§e§lEnton§6Crates - Page2", 9*3);
        page2.addItem(new ItemStack(Material.REDSTONE_BLOCK, 1));
        ActionItem item2 = new ActionItem(new ItemStack(Material.GLOWSTONE, 1)) {
            @Override
            public void onItemClick(ItemStack item) {
                openPage(getBackPage());
            }
        };
        page2.addActionItem(item2);

        addPage(page2);
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
    public void onClickInInventory(CratePage clickedPage, ItemStack currentItem, InventoryClickEvent event) {
    }

    @Override
    public void onClick(CratePage clickedPage, ItemStack currentItem, InventoryClickEvent event) {

        //event.setCancelled(true);


        if(!currentItem.hasItemMeta()) return;
        switch (currentItem.getItemMeta().getDisplayName()) {

            case "test":
                System.out.println("test");
                break;

            default:
                break;
        }


        System.out.println("click");
    }

    @Override
    public void onNextPage(CratePage lastPage, CratePage newPage, int lastPageCount) {

    }

    @Override
    public void onBackPage(CratePage lastPage, CratePage newPage, int lastPageCount) {

    }

    @Override
    public void onSetPage(CratePage lastPage, CratePage newPage, int lastPageCount) {

    }

    @Override
    protected void onActionItemClick(CratePage currentPage, ActionItem actionItem, InventoryClickEvent event) {
        System.out.println(actionItem.getItem().getType().name());

    }

    @Override
    public void onClose(CratePage currentPage, InventoryCloseEvent event) {
        System.out.println("close");
        System.out.println(getCurrentPage().getTitle() + " closed");

        //if(getCurrentPageAsCount() == 1) {
         //   closeTempInventory();
        //}

    }
}
