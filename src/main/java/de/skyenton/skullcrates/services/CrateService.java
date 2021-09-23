package de.skyenton.skullcrates.services;

import de.skyenton.skullcrates.inventory.CrateInventory;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

public class CrateService {

    private HashMap<String, CrateInventory> crateInventorys = new HashMap<>();

    public void registerInventory(String nameID, CrateInventory crateInventory) {
        crateInventory.register();
        crateInventorys.put(nameID, crateInventory);
    }

    public void unregisterInventory(String nameID) {
        getCrateInventorys().get(nameID).unregister();
        crateInventorys.remove(nameID);
    }

    public void openInventory(String nameID, UUID uuid) {
        crateInventorys.get(nameID).onOpen(uuid);
        Bukkit.getPlayer(uuid).openInventory(getCrateInventory(nameID).getCurrentPage(uuid).getInventory());
    }

    public CrateInventory getCrateInventory(String nameID) {
        return crateInventorys.get(nameID);
    }

    public HashMap<String, CrateInventory> getCrateInventorys() {
        return crateInventorys;
    }

    public void setCrateInventorys(HashMap<String, CrateInventory> crateInventorys) {
        this.crateInventorys = crateInventorys;
    }

}
