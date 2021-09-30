package de.skyenton.skullcrates.commands;

import de.skyenton.skullcrates.SkullcratesPlugin;
import de.skyenton.skullcrates.crate.Crate;
import de.skyenton.skullcrates.crate.ToSetTypes;
import de.skyenton.skullcrates.inventory.inventorys.CrateEditInventory;
import de.skyenton.skullcrates.inventory.inventorys.InventoryTypes;
import de.skyenton.skullcrates.runnables.AutoEventSchedule;
import de.skyenton.skullcrates.services.CrateService;
import org.bukkit.Bukkit;
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

                    case "list":
                        for (Crate crate : crateService.getCrates()) {
                            sender.sendMessage("§cName: " + crate.getName());
                        }
                        break;

                    case "autoevent":

                        AutoEventSchedule autoEventSchedule = crateService.getCratePlugin().getAutoEventSchedule();

                        if (!sender.hasPermission("system.crates")) {
                            sender.sendMessage(SkullcratesPlugin.PREFIX + "§cDu hast keine Rechte!");
                            return true;
                        }

                        if (autoEventSchedule.isRunning()) {
                            autoEventSchedule.stop();
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                player.sendTitle("§8| §6§lAuto Crate Event §8|", "§4stopt!", 15, 45, 25);
                            }
                            sender.sendMessage("§aAutoevent gestoppt!");
                        } else {
                            autoEventSchedule.start();
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                player.sendTitle("§8| §6§lAuto Crate Event §8|", "§astartet!", 15, 45, 25);
                            }
                            sender.sendMessage("§aAutoevent gestartet!");
                        }

                    default:
                        break;
                }
                break;

            case 2:
                switch (args[0]) {


                    case "edit":
                        if(!sender.hasPermission("system.crates")) {
                            sender.sendMessage(SkullcratesPlugin.PREFIX + "§cDu hast keine Rechte!");
                            return true;
                        }

                        if(crateService.getCrateByName(args[1]) != null) {
                            Crate crate = crateService.getCrateByName(args[1]);
                            CrateEditInventory editInv = new CrateEditInventory(crateService.getCratePlugin(), crateService, crate);
                            editInv.register();
                            crateService.openCustomInventory(editInv, (Player)sender);
                        } else {
                            sender.sendMessage(SkullcratesPlugin.PREFIX + "§cDiese Crate exestiert nicht!");
                            return true;
                        }

                        break;


                    case "create":

                        if(!sender.hasPermission("system.crates")) {
                            sender.sendMessage(SkullcratesPlugin.PREFIX + "§cDu hast keine Rechte!");
                            return true;
                        }
                        if (args[1] == null) {
                            sender.sendMessage(SkullcratesPlugin.PREFIX + "§cBitte geben sie ein Crate Namen an! §4(Keine Sonderzeichen ODER Farbcodes!!!)");
                        } else {
                            if(!crateService.getCratesInMaking().containsKey(((Player) sender).getUniqueId()) || !crateService.getCratesInMaking().get(((Player) sender).getUniqueId()).getName().equalsIgnoreCase(args[1])) {
                                if(crateService.getCrateByName(args[1]) == null) {
                                    crateService.addMakingCrate(new Crate(), (Player) sender);
                                    Crate crate = crateService.getCratesInMaking().get(((Player) sender).getUniqueId());
                                    crate.setCurrentToSet(ToSetTypes.NONE);
                                    crate.setName(args[1]);
                                    crateService.openRegistedInventory(InventoryTypes.MAIN.name(), (Player) sender);
                                    sender.sendMessage(SkullcratesPlugin.PREFIX + "§aVerbereitung für Crate " + crate.getName() + "...");
                                } else {
                                    sender.sendMessage(SkullcratesPlugin.PREFIX + "§cDiese Crate exestiert bereits!");
                                }
                            } else {
                                //get inv from this inv
                                Crate crate = crateService.getCrateByName(args[1]);
                                crateService.openRegistedInventory(InventoryTypes.MAIN.name(), (Player) sender);
                                sender.sendMessage(SkullcratesPlugin.PREFIX + "§aCrate create menü geöffnet!");
                            }

                        }
                        break;

                    case "delete":

                        if(!sender.hasPermission("system.crates")) {
                            sender.sendMessage(SkullcratesPlugin.PREFIX + "§cDu hast keine Rechte!");
                            return true;
                        }

                        crateService.getCrateSaver().delete(crateService.getCrateByName(args[1]));
                        crateService.getCrates().remove(crateService.getCrateByName(args[1]));
                        sender.sendMessage(SkullcratesPlugin.PREFIX + "§cCrate wurde gelöscht!");
                        break;
                    case "giveall":

                        if(!sender.hasPermission("system.crates")) {
                            sender.sendMessage(SkullcratesPlugin.PREFIX + "§cDu hast keine Rechte!");
                            return true;
                        }

                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.getInventory().addItem(crateService.getCrateByName(args[1]).getCrateItem());
                            player.sendMessage(SkullcratesPlugin.PREFIX + "§cAlle Spieler §bhaben die Crate §e" + args[1] + " §bbekommen!");
                        }
                        break;

                    default:
                        break;
                }
                break;


            case 3:
                switch (args[0]) {
                    case "give":

                        if(!sender.hasPermission("system.crates")) {
                            sender.sendMessage(SkullcratesPlugin.PREFIX + "§cDu hast keine Rechte!");
                            return true;
                        }

                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if(player.getName().equalsIgnoreCase(args[2])) {
                                player.getInventory().addItem(crateService.getCrateByName(args[1]).getCrateItem());
                                player.sendMessage(SkullcratesPlugin.PREFIX + "§aDu §bhast die Crate §e" + args[1] + " §bbekommen!");
                            }
                        }
                        break;
                }

            default:
                break;
        }

        return false;
    }

}
