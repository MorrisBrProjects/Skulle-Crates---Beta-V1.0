package de.skyenton.skullcrates.crate;

import de.skyenton.skullcrates.config.FileHandler;
import de.skyenton.skullcrates.services.CrateService;

public class CrateConfigSaver {

    private CrateService crateService;
    private FileHandler fileHandler;

    public CrateConfigSaver(CrateService crateService) {
        this.crateService = crateService;
        this.fileHandler = crateService.getCratePlugin().getFileHandler();
    }

    public void saveCreate(Crate crate) {
        fileHandler.getConfig("CrateData.yml").get().set("Crates." + crate.getName(), crate);
        fileHandler.getConfig("CrateData.yml").save();
    }

}
