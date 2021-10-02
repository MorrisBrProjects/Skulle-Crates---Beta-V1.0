package de.skyenton.skullcrates.listener;

import de.skyenton.skullcrates.SkullcratesPlugin;
import de.skyenton.skullcrates.crate.Crate;
import de.skyenton.skullcrates.inventory.CratePage;
import de.skyenton.skullcrates.inventory.inventorys.CrateCreateInventory;
import de.skyenton.skullcrates.inventory.inventorys.InventoryTypes;
import de.skyenton.skullcrates.services.CrateService;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PlayerJoinListener implements Listener {

    private CrateService crateService;
    private SkullcratesPlugin plugin;

    public PlayerJoinListener(CrateService crateService, SkullcratesPlugin skullcratesPlugin) {
        this.crateService = crateService;
        this.plugin = skullcratesPlugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        if (event.getPlayer().hasPlayedBefore()) {
            executorService.schedule(new Runnable() {
                @Override
                public void run() {

                    Player player = event.getPlayer();
                    //player.getInventory().addItem(crateService.getCrateByName("hallo").getCrateItem());
                    //crateService.openCustomInventory(new CrateCreateInventory((JavaPlugin) plugin, crateService), event.getPlayer());
                }
            }, 1, TimeUnit.SECONDS);
        }
    }

}
