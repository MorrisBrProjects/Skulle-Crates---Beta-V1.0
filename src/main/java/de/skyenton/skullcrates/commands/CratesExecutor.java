package de.skyenton.skullcrates.commands;

import de.skyenton.skullcrates.crate.Crate;
import de.skyenton.skullcrates.crate.ToSetTypes;
import de.skyenton.skullcrates.inventory.inventorys.InventoryTypes;
import de.skyenton.skullcrates.services.CrateService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CratesExecutor implements CommandExecutor {

    private CrateService crateService;

    public CratesExecutor(CrateService crateService) {
        this.crateService = crateService;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        switch (args.length) {

            case 0:
                sender.sendMessage("/crate  ........ hilfe ..... | Crate");
                break;


            case 2:
                switch (args[0]) {

                    case "create":
                        if (args[1] == null) {
                            sender.sendMessage("§cBitte geben sie ein Crate Namen an! §4(Keine Sonderzeichen ODER Farbcodes!!!)");
                        } else {
                            if(!crateService.getCratesInMaking().containsKey(((Player) sender).getUniqueId()) || !crateService.getCratesInMaking().get(((Player) sender).getUniqueId()).getName().equalsIgnoreCase(args[1])) {
                                if(crateService.getCrateByName(args[1]) == null) {
                                    crateService.addMakingCrate(new Crate(), (Player) sender);
                                    Crate crate = crateService.getCratesInMaking().get(((Player) sender).getUniqueId());
                                    crate.setCurrentToSet(ToSetTypes.NONE);
                                    crate.setName(args[1]);
                                    crateService.openRegistedInventory(InventoryTypes.MAIN.name(), (Player) sender);
                                    sender.sendMessage("§aVerbereitung für Crate " + crate.getName() + "...");
                                } else {
                                    sender.sendMessage("§cDiese Crate exestiert bereits!");
                                }
                            } else {
                                Crate crate = crateService.getCratesInMaking().get(((Player) sender).getUniqueId());
                                crateService.openRegistedInventory(InventoryTypes.MAIN.name(), (Player) sender);
                                sender.sendMessage("§aCrate edit menü geöffnet!");
                            }

                        }
                        break;

                    default:
                        break;
                }
                break;

            default:
                break;
        }

        return false;
    }

}
