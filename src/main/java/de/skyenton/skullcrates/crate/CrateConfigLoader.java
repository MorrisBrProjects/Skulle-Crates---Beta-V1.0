package de.skyenton.skullcrates.crate;

import de.skyenton.skullcrates.config.FileHandler;
import de.skyenton.skullcrates.services.CrateService;

import java.util.ArrayList;

public class CrateConfigLoader {

    private CrateService crateService;
    private FileHandler fileHandler;

    public CrateConfigLoader(CrateService crateService) {
        this.crateService = crateService;
        this.fileHandler = crateService.getCratePlugin().getFileHandler();
    }

    public Crate loadCratebyName(String name) {
        FileHandler.Config config = fileHandler.getConfig("CrateData.yml");
        return (Crate) config.get("Crates." + name);
    }

    /**
     * Unperformant, es muss alle Crates aus der Config lasen umm DisplayName Crate zu suchen!
     * @param displayName
     * @return
     */
    public Crate loadCratebyDisplayName(String displayName) {
        for (Crate crate : loadAllCrates()) {
            if(crate.getDisplayName().equals(displayName)) {
                return crate;
            }
        }
        return null;
    }

    public ArrayList<Crate> loadAllCrates() {
        ArrayList<Crate> crates = new ArrayList<>();
        for (String crate : fileHandler.getConfig("CrateData.yml").get().getConfigurationSection("Crates").getKeys(false)) {
            System.out.println(crate);
            crates.add((Crate) fileHandler.getConfig("CrateData.yml").get().get("Crates." + crate));
        }
        System.out.println(crates);
        return crates;
    }

}
