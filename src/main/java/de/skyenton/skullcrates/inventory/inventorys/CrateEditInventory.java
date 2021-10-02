package de.skyenton.skullcrates.inventory.inventorys;

import com.google.common.collect.FluentIterable;
import de.skyenton.skullcrates.SkullcratesPlugin;
import de.skyenton.skullcrates.crate.Crate;
import de.skyenton.skullcrates.crate.ToSetTypes;
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

import java.util.Arrays;

public class CrateEditInventory extends CrateInventory {

    private Crate crate;

    public CrateEditInventory(JavaPlugin plugin, CrateService crateService) {
        super(plugin, crateService);
        //System.out.println("construktor");
        //System.out.println("create");
    }

    public CrateEditInventory(JavaPlugin plugin, CrateService crateService, Crate crate) {
        super(plugin, crateService);
        //System.out.println("construktor");
        //System.out.println("create");
        this.crate = crate;
    }

    @Override
    public void onCreate() {
        CratePage page1 = new CratePage("§e§lCrate§7- §aBearbeiten", 9*3);
        page1.addItem(new ItemStack(Material.GLASS, 1));
        page1.setBoarderLayout();
        ActionItem nameAction1 = new ActionItem(new ItemBuilder("§7Itemname", Material.NAME_TAG, (short) 0, 1).build()) {
            @Override
            public void onItemClick(ItemStack item) {

                //getInvOwner().sendMessage("Crdddate: " + crate.getName());
                crate.setCurrentToSet(ToSetTypes.DISPLAYNAME);

                getInvOwner().closeInventory();
                getInvOwner().sendMessage(SkullcratesPlugin.PREFIX + "§aGeben sie im Chat den Namen ein!");
            }
        };
        page1.addActionItem(nameAction1);
        ActionItem loreAction1 = new ActionItem(new ItemBuilder("§7Lore", Material.BOOK_AND_QUILL, (short) 0, 1).build()) {
            @Override
            public void onItemClick(ItemStack item) {

                crate.setCurrentToSet(ToSetTypes.LORE);

                getInvOwner().closeInventory();
                getInvOwner().sendMessage(SkullcratesPlugin.PREFIX + "§aGeben sie im Chat die Lore ein!");
            }
        };
        page1.addActionItem(loreAction1);
        ActionItem skullAction1 = new ActionItem(new ItemBuilder("§7SkullName", Material.SKULL_ITEM, (short) 0, 1).build()) {
            @Override
            public void onItemClick(ItemStack item) {
                crate.setCurrentToSet(ToSetTypes.SKULL);
                getInvOwner().closeInventory();
                getInvOwner().sendMessage(SkullcratesPlugin.PREFIX + "§aGeben sie im Chat den Skull Namen ein!");
            }
        };
        page1.addActionItem(skullAction1);
        ActionItem itemsAction1 = new ActionItem(new ItemBuilder("§7Items", Material.CHEST, (short) 0, 1).build()) {
            @Override
            public void onItemClick(ItemStack item) {
                // Crate crate = new Crate();
                // crate.setSkull("MorrisBr");
                //crate.setName("test");
                // System.out.println(crate);
                // getCrateService().getCrateSaver().saveCreate(crate);
                // getInvOwner().sendMessage(getCrateService().getCrateLoader().loadCratebyName("test").getName());
                //getInvOwner().closeInventory();
                openPage(getPage(2));

                for (int i = 0; i < crate.getItems().size(); i++) {
                    getCurrentPage().getInventory().setItem(i, crate.getItems().get(i));
                }
            }
        };
        page1.addActionItem(itemsAction1);
        ActionItem createAction1 = new ActionItem(new ItemBuilder("§aSpeichern", Material.CONCRETE, (short) 5, 1).build()) {
            @Override
            public void onItemClick(ItemStack item) {

                //System.out.println(crate.getItems());
                //getPage(2).getInventory().setContents(crate.getItems());

                    //crate.getLore().add("Nur bypass zum Testen!");
                //System.out.println(crate.getSkull());
                getCrateService().createCrate(crate);
                getInvOwner().getInventory().addItem(crate.getCrateItem());
                //getPage(2).getInventory().clear();
                getInvOwner().sendMessage(SkullcratesPlugin.PREFIX + "§aCrate wurde gespeichert!");
                getInvOwner().closeInventory();
            }
        };
        page1.addActionItem(createAction1);
        page1.addItem(new ItemBuilder("§6Abbrechen", Material.CONCRETE, (short) 4, 1).build());
        ActionItem verwerfenAction1 = new ActionItem(new ItemBuilder("§cVerwerfen", Material.CONCRETE, (short) 14, 1).build()) {
            @Override
            public void onItemClick(ItemStack item) {

            }
        };
        page1.addActionItem(verwerfenAction1);




        CratePage page2 = new CratePage("§e§lEnton§6Crates - Page2", 9*3);
        page2.addItem(new ItemStack(Material.REDSTONE_BLOCK, 1));
        ActionItem item2 = new ActionItem(new ItemStack(Material.GLOWSTONE, 1)) {
            @Override
            public void onItemClick(ItemStack item) {
                //openPage(getBackPage());
                //getCrateService().addMakingCrate(new Crate(), getInvOwner());
            }
        };
        page2.addActionItem(item2);


        CratePage page3 = new CratePage("§e§lCrate§7- §aItems", 9*3);
        //page3.getInventory().setContents();
        //page3.getInventory().setContents(crate.getItems().toArray(new ItemStack[0]));

        addPage(page1);
        addPage(page2);
        addPage(page3);
    }

    @Override
    public void onOpen(Player uuid) {
        //uuid.sendMessage("Crate: " + crate.getName());
        //getCrateService().addMakingCrate(new Crate(), uuid);
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

        if(getCurrentPage().getInventory().equals(event.getClickedInventory()) && getCurrentPageAsCount() != 2) {
            event.setCancelled(true);
        }


        if(!currentItem.hasItemMeta()) return;
        switch (currentItem.getItemMeta().getDisplayName()) {

            case "test":
                //System.out.println("test");
                break;

            default:
                break;
        }


        //System.out.println("click");
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
        //System.out.println(actionItem.getItem().getType().name());

    }

    @Override
    public void onClose(CratePage currentPage, InventoryCloseEvent event) {
        //System.out.println("close");
        //System.out.println(getCurrentPage().getTitle() + " closed");


        if(getCurrentPageAsCount() == 2) {
            crate.setItems(Arrays.asList(getPage(2).getInventory().getContents()));
            getInvOwner().sendMessage(SkullcratesPlugin.PREFIX + "§aInventar wurde gespeichert!");
            setPage(0);
        }

    }

    public Crate getCrate() {
        return crate;
    }
}
