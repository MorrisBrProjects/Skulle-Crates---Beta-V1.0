package de.skyenton.skullcrates.services;

import de.skyenton.skullcrates.SkullcratesPlugin;
import de.skyenton.skullcrates.crate.Crate;
import de.skyenton.skullcrates.crate.CrateConfigLoader;
import de.skyenton.skullcrates.crate.CrateConfigSaver;
import de.skyenton.skullcrates.inventory.CrateInventory;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CrateService {

    private HashMap<String, CrateInventory> crateInventorys = new HashMap<>();
    private final HashMap<UUID, CrateInventory> usedInventorys = new HashMap<>();

    private final ArrayList<Crate> crates = new ArrayList<>();

    private SkullcratesPlugin cratePlugin;

    private CrateConfigLoader crateLoader = new CrateConfigLoader(this);
    private CrateConfigSaver crateSaver = new CrateConfigSaver(this);

    public CrateService(SkullcratesPlugin cratePlugin) {
        this.cratePlugin = cratePlugin;
    }


    public ArrayList<Crate> getCrates() {
        return crates;
    }

    public Crate getCrateByName(String name) {
        for (Crate crate : getCrates()) {
            if(crate.getName().equals(name)) {
                return crate;
            }
        }
        return null;
    }

    public Crate getCrateByDisplayName(String displayName) {
        for (Crate crate : getCrates()) {
            if(crate.getDisplayName().equals(displayName)) {
                return crate;
            }
        }
        return null;
    }

    public HashMap<UUID, CrateInventory> getUsedInventorys() {
        return usedInventorys;
    }

    public void registerInventory(String nameID, CrateInventory crateInventory) {
        crateInventorys.put(nameID, crateInventory);
        crateInventory.register(); // kann registed seiN!
    }

    public void unregisterInventory(String nameID) {
        getCrateInventorys().get(nameID).unregister();
        crateInventorys.remove(nameID);
    }

    public void openRegistedInventory(String nameID, Player player) {
        HashMap<String, CrateInventory> crateInv = (HashMap<String, CrateInventory>)crateInventorys.clone();
        crateInv.get(nameID).onOpen(player);
        usedInventorys.put(player.getUniqueId(), crateInv.get(nameID));
        //System.out.println(crateInv.get(nameID).getCurrentPage().getInventory().getTitle());
       // player.openInventory(Bukkit.createInventory(null, 9*3, "test"));
        player.openInventory(crateInv.get(nameID).getCurrentPage().getInventory());
    }

    public void openSyncInventory(String nameID, Player player) {
        crateInventorys.get(nameID).onOpen(player);
        System.out.println(crateInventorys.get(nameID).getCurrentPage().getInventory().getTitle());
        // player.openInventory(Bukkit.createInventory(null, 9*3, "test"));
        player.openInventory(getRegistedCrateInventory(nameID).getCurrentPage().getInventory());
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

    public SkullcratesPlugin getCratePlugin() {
        return cratePlugin;
    }

    public CrateConfigLoader getCrateLoader() {
        return crateLoader;
    }

    public CrateConfigSaver getCrateSaver() {
        return crateSaver;
    }
}
