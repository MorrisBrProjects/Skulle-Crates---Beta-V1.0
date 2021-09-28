package de.skyenton.skullcrates.listener;

import de.skyenton.skullcrates.crate.Crate;
import de.skyenton.skullcrates.services.CrateService;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
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

                    event.setCancelled(true);

                    ItemStack item = crateService.getCrateByDisplayName(event.getItem().getItemMeta().getDisplayName()).getCrateItem();
                    Crate crate = crateService.getCrateByDisplayName(event.getItem().getItemMeta().getDisplayName());

                    ItemStack randomItem = crate.getItems().get(new Random().nextInt(crate.getItems().size()));

                    if(randomItem != null) {
                        player.getInventory().addItem(randomItem);
                    }
                    player.sendMessage("§aCrate geöffnet!");
                }
            }
        }
    }

}
