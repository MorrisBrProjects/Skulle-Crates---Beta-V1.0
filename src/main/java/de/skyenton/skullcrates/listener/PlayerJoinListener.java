package de.skyenton.skullcrates.listener;

import de.skyenton.skullcrates.SkullcratesPlugin;
import de.skyenton.skullcrates.inventory.CratePage;
import de.skyenton.skullcrates.inventory.inventorys.CrateCreateInventory;
import de.skyenton.skullcrates.inventory.inventorys.InventoryTypes;
import de.skyenton.skullcrates.services.CrateService;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerJoinListener implements Listener {

    private CrateService crateService;
    private SkullcratesPlugin plugin;

    public PlayerJoinListener(CrateService crateService, SkullcratesPlugin skullcratesPlugin) {
        this.crateService = crateService;
        this.plugin = skullcratesPlugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        crateService.openRegistedInventory(InventoryTypes.MAIN.name(), event.getPlayer());
    }

}
