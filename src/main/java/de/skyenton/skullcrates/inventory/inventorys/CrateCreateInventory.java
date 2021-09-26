package de.skyenton.skullcrates.inventory.inventorys;

import de.skyenton.skullcrates.crate.Crate;
import de.skyenton.skullcrates.inventory.ActionItem;
import de.skyenton.skullcrates.inventory.CrateInventory;
import de.skyenton.skullcrates.inventory.CratePage;
import de.skyenton.skullcrates.services.CrateService;
import de.skyenton.skullcrates.utils.ItemBuilder;
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
        CratePage page1 = new CratePage("§e§lCrate§7- §aErstellen - Erstellen", 9*3);
        page1.addItem(new ItemStack(Material.GLASS, 1));
        page1.setBoarderLayout();
        page1.addItem(new ItemBuilder("§4Create", Material.EMERALD_ORE, (short) 0, 1).build());
        ActionItem item1 = new ActionItem(new ItemStack(Material.GLOWSTONE, 1)) {
            @Override
            public void onItemClick(ItemStack item) {
                Crate crate = new Crate();
                crate.setSkull("MorrisBr");
                crate.setName("test");
                System.out.println(crate);
                getCrateService().getCrateSaver().saveCreate(crate);
                getInvOwner().sendMessage(getCrateService().getCrateLoader().loadCratebyName("test").getName());
                //getInvOwner().closeInventory();
            }
        };
        page1.addActionItem(item1);
        addPage(page1);


        CratePage page2 = new CratePage("§e§lEnton§6Crates - Page2", 9*3);
        page2.addItem(new ItemStack(Material.REDSTONE_BLOCK, 1));
        ActionItem item2 = new ActionItem(new ItemStack(Material.GLOWSTONE, 1)) {
            @Override
            public void onItemClick(ItemStack item) {
                //openPage(getBackPage());
                Crate crate = new Crate();
                crate.setSkull("MorrisBr");
                crate.setName("test");
                getCrateService().getCrateSaver().saveCreate(crate);
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

        event.setCancelled(true);


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
