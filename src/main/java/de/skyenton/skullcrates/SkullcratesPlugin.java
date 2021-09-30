package de.skyenton.skullcrates;

import de.skyenton.skullcrates.commands.CratesExecutor;
import de.skyenton.skullcrates.config.FileHandler;
import de.skyenton.skullcrates.crate.Crate;
import de.skyenton.skullcrates.inventory.CrateInventory;
import de.skyenton.skullcrates.inventory.inventorys.CrateCreateInventory;
import de.skyenton.skullcrates.inventory.inventorys.CrateEditInventory;
import de.skyenton.skullcrates.inventory.inventorys.InventoryTypes;
import de.skyenton.skullcrates.listener.PlayerChatListener;
import de.skyenton.skullcrates.listener.PlayerInteractListener;
import de.skyenton.skullcrates.listener.PlayerJoinListener;
import de.skyenton.skullcrates.runnables.AutoEventSchedule;
import de.skyenton.skullcrates.services.CrateService;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkullcratesPlugin extends JavaPlugin {

    public static final String PREFIX = "§7[§c§lCrates§7] ";

    private final FileHandler fileHandler = new FileHandler(this);
    private CrateService crateService = new CrateService(this);
    private AutoEventSchedule autoEventSchedule = new AutoEventSchedule(this, crateService);

    static {
        ConfigurationSerialization.registerClass(Crate.class, "Crate");
    }


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(crateService, this), this);
        getServer().getPluginManager().registerEvents(new PlayerChatListener(crateService), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(crateService), this);
        getServer().getPluginCommand("crate").setExecutor(new CratesExecutor(crateService));
        crateService.registerInventory(InventoryTypes.MAIN.name(), new CrateCreateInventory(this, crateService));

        for (Crate crate : crateService.getCrateLoader().loadAllCrates()) {
            crateService.getCrates().add(crate);
        }

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

    public AutoEventSchedule getAutoEventSchedule() {
        return autoEventSchedule;
    }
}
