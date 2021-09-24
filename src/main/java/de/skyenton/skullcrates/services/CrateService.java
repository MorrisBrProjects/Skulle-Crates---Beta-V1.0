package de.skyenton.skullcrates.services;

import de.skyenton.skullcrates.inventory.CrateInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CrateService {

    private HashMap<String, CrateInventory> crateInventorys = new HashMap<>();
    private HashMap<UUID, CrateInventory> usedInventorys = new HashMap<>();

    public void registerInventory(String nameID, CrateInventory crateInventory) {
        crateInventorys.put(nameID, crateInventory);
        //crateInventory.register(); // kann registed seiN!
    }

    public void unregisterInventory(String nameID) {
        getCrateInventorys().get(nameID).unregister();
        crateInventorys.remove(nameID);
    }

    public void openRegistedInventory(String nameID, Player player) {
        crateInventorys.get(nameID).onOpen(player);
        System.out.println(crateInventorys.get(nameID).getCurrentPage().getInventory().getTitle());
        player.openInventory(crateInventorys.get(nameID).getCurrentPage().getInventory());
    }

    public void openCustomInventory(CrateInventory crateInventory, Player player) {
        usedInventorys.put(player.getUniqueId(), crateInventory);
        crateInventory.onOpen(player);
        player.openInventory(crateInventory.getCurrentPage().getInventory());
        System.out.println("dgffdgdf");
    }

    public CrateInventory getRegistedCrateInventory(String nameID) {
        return crateInventorys.get(nameID);
    }

    public CrateInventory getOpenedCrateInventory(UUID uuid) {
        return usedInventorys.get(uuid);
    }

    public HashMap<String, CrateInventory> getCrateInventorys() {
        return crateInventorys;
    }

    public void setCrateInventorys(HashMap<String, CrateInventory> crateInventorys) {
        this.crateInventorys = crateInventorys;
    }
}
