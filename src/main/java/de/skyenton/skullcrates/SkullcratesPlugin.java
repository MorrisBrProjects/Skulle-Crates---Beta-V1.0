package de.skyenton.skullcrates;

import de.skyenton.skullcrates.commands.CratesExecutor;
import de.skyenton.skullcrates.config.FileHandler;
import de.skyenton.skullcrates.inventory.CrateInventory;
import de.skyenton.skullcrates.inventory.inventorys.CrateCreateInventory;
import de.skyenton.skullcrates.inventory.inventorys.InventoryTypes;
import de.skyenton.skullcrates.listener.PlayerJoinListener;
import de.skyenton.skullcrates.services.CrateService;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkullcratesPlugin extends JavaPlugin {


    private CrateService crateService = new CrateService();
    private FileHandler fileHandler = new FileHandler(this);

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(crateService, this), this);
        getServer().getPluginCommand("crate").setExecutor(new CratesExecutor(crateService));
        crateService.registerInventory(InventoryTypes.MAIN.name(), new CrateCreateInventory(this, crateService));
    }

    @Override
    public void onDisable() {
        crateService.unregisterInventory(InventoryTypes.MAIN.name());
    }


    public CrateService getCrateService() {
        return crateService;
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }
}
