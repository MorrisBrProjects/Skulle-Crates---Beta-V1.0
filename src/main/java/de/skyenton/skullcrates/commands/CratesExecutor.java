package de.skyenton.skullcrates.commands;

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

            case 1:
                switch (args[0]) {

                    case "create":
                        crateService.openRegistedInventory(InventoryTypes.MAIN.name(), (Player) sender);
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
