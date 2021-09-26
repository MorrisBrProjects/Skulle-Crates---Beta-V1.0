package de.skyenton.skullcrates.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CratePage {

    private String title;
    private int size;
    private Inventory inventory;



    private ArrayList<ActionItem> actionItems = new ArrayList<>();

    public CratePage(String title, int size) {
        this.title = title;
        this.size = size;
        this.inventory = Bukkit.createInventory(null, size, title);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addItem(ItemStack itemStack) {
        getInventory().addItem(itemStack);
    }

    public void setItem(int slot, ItemStack itemStack) {
        getInventory().setItem(slot, itemStack);
    }

    public void addActionItem(ActionItem actionItem) {
        getInventory().addItem(actionItem.getItem());
        actionItems.add(actionItem);
    }

    public void setActionItem(int slot, ActionItem actionItem) {
        getInventory().setItem(slot, actionItem.getItem());
        actionItems.add(actionItem);
    }

    public void removeActionItem(ActionItem actionItem) {
        getInventory().remove(actionItem.getItem());
        actionItems.remove(actionItem);
    }

    public ActionItem getActionItem(ItemStack itemStack) {
        for (ActionItem actionItem : actionItems) {
            if(itemStack.isSimilar(actionItem.getItem())) {
                return actionItem;
            }
        }
        return null;
    }

    public boolean isActionItem(ItemStack itemStack) {
        for (ActionItem actionItem : actionItems) {
            if(itemStack.isSimilar(actionItem.getItem())) {
                return true;
            }
        }
        return false;
    }

    public void removeItem(ItemStack itemStack) {
        getInventory().removeItem(itemStack);
    }

    public void setContent(ItemStack[] items) {
        getInventory().setContents(items);
    }

    public ArrayList<ActionItem> getActionItems() {
        return actionItems;
    }

    public void setActionItems(ArrayList<ActionItem> actionItems) {
        this.actionItems = actionItems;
    }

    public void fillInventory(ItemStack itemStack) {
        for(int index = 0; index <= getInventory().getSize()-1; index++){
            if(getInventory().getItem(index) == null) {
                getInventory().setItem(index, itemStack);
            }
        }
    }

    public void setBoarderLayout() {
        ItemStack itemFirst = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3);
        ItemMeta itemMetaFirst = itemFirst.getItemMeta();
        itemMetaFirst.setDisplayName(" ");
        itemFirst.setItemMeta(itemMetaFirst);

        ItemStack itemNext = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11);
        ItemMeta itemMetaNext = itemNext.getItemMeta();
        itemMetaNext.setDisplayName(" ");
        itemNext.setItemMeta(itemMetaNext);

        setItem(0, itemFirst);
        setItem(1, itemNext);
        setItem(2, itemFirst);
        setItem(3, itemNext);
        setItem(4, itemFirst);
        setItem(5, itemNext);
        setItem(6, itemFirst);
        setItem(7, itemNext);
        setItem(8, itemFirst);

        setItem(9, itemNext);
        setItem(17, itemNext);

        setItem(18, itemFirst);
        setItem(19, itemNext);
        setItem(20, itemNext);
        setItem(21, itemFirst);
        setItem(22, itemNext);
        setItem(23, itemFirst);
        setItem(24, itemNext);
        setItem(25, itemNext);
        setItem(26, itemFirst);
    }



}
