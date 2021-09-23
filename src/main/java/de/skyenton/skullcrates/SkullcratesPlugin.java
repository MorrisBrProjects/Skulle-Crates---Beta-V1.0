package de.skyenton.skullcrates;

import de.skyenton.skullcrates.config.FileHandler;
import de.skyenton.skullcrates.inventory.CrateInventory;
import de.skyenton.skullcrates.inventory.inventorys.CrateCreateInventory;
import de.skyenton.skullcrates.services.CrateService;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkullcratesPlugin extends JavaPlugin {


    private CrateService crateService = new CrateService();
    private FileHandler fileHandler = new FileHandler(this);

    @Override
    public void onEnable() {
        crateService.registerInventory("crate", new CrateCreateInventory(this));
    }

    @Override
    public void onDisable() {
        crateService.unregisterInventory("crate");
    }


    public CrateService getCrateService() {
        return crateService;
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }
}
