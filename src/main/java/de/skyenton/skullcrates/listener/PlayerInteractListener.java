package de.skyenton.skullcrates.listener;

import de.skyenton.skullcrates.crate.Crate;
import de.skyenton.skullcrates.services.CrateService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class PlayerInteractListener implements Listener {

    private CrateService crateService;

    public PlayerInteractListener(CrateService crateService) {
        this.crateService = crateService;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if(event.getItem() != null && event.getItem().getType() == Material.SKULL_ITEM && event.getItem().hasItemMeta()) {
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if(crateService.getCrateByDisplayName(event.getItem().getItemMeta().getDisplayName()) != null) {

                    if(event.getHand() == EquipmentSlot.OFF_HAND) return;
                    event.setCancelled(true);

                    ItemStack item = crateService.getCrateByDisplayName(event.getItem().getItemMeta().getDisplayName()).getCrateItem();
                    Crate crate = crateService.getCrateByDisplayName(event.getItem().getItemMeta().getDisplayName());

                    ItemStack randomItem = crate.getItems().get(new Random().nextInt(crate.getItems().size()));

                    player.sendTitle("§7§m--§8 §c§lCrate §7§m--", "§7§m--§8 §a§lwurde geöffnet §7§m--", 15, 45, 25);
                    player.getItemInHand().setAmount(player.getItemInHand().getAmount()-1);
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3.0F, 1.5F);

                    if(randomItem != null) {
                        player.getInventory().addItem(randomItem);
                    }
                    //player.sendMessage("§aCrate geöffnet!");


                }
            } else if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                if(crateService.getCrateByDisplayName(event.getItem().getItemMeta().getDisplayName()) != null) {

                    if(event.getHand() == EquipmentSlot.OFF_HAND) return;
                    event.setCancelled(true);

                    ItemStack item = crateService.getCrateByDisplayName(event.getItem().getItemMeta().getDisplayName()).getCrateItem();
                    Crate crate = crateService.getCrateByDisplayName(event.getItem().getItemMeta().getDisplayName());

                    Inventory inv = Bukkit.createInventory(null, 9*3, "§cCrate Inventar");
                    inv.setContents(crate.getItems().toArray(new ItemStack[crate.getItems().size()]));
                    player.openInventory(inv);


                }
            }
        }
    }

}
