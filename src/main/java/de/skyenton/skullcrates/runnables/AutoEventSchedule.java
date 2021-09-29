package de.skyenton.skullcrates.runnables;

import de.skyenton.skullcrates.SkullcratesPlugin;
import de.skyenton.skullcrates.crate.Crate;
import de.skyenton.skullcrates.services.CrateService;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoEventSchedule {

    private int runnableID;
    private JavaPlugin plugin;
    private CrateService crateService;
    private boolean running = false;

    public AutoEventSchedule(JavaPlugin plugin, CrateService crateService) {
        this.plugin = plugin;
        this.crateService = crateService;
    }


    public int getRunnableID() {
        return runnableID;
    }

    public void stop() {
        plugin.getServer().getScheduler().cancelTask(runnableID);
        setRunning(false);
    }

    public void start() {
        runnableID = plugin.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {

            @Override
            public void run() {
                Crate crate1 = crateService.getCrateByName("Money");
                Crate crate2 = crateService.getCrateByName("Case");
                Crate crate3 = crateService.getCrateByName("Bloecke");
                Crate crate4 = crateService.getCrateByName("Erze");

                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.getInventory().addItem(crate1.getCrateItem());
                    player.getInventory().addItem(crate2.getCrateItem());
                    player.getInventory().addItem(crate3.getCrateItem());
                    player.getInventory().addItem(crate4.getCrateItem());

                    player.sendMessage(SkullcratesPlugin.PREFIX + "§cAlle Spieler §bhaben die Crate §e" + crate1.getName() + " §bbekommen!");
                    player.sendMessage(SkullcratesPlugin.PREFIX + "§cAlle Spieler §bhaben die Crate §e" + crate2.getName() + " §bbekommen!");
                    player.sendMessage(SkullcratesPlugin.PREFIX + "§cAlle Spieler §bhaben die Crate §e" + crate3.getName() + " §bbekommen!");
                    player.sendMessage(SkullcratesPlugin.PREFIX + "§cAlle Spieler §bhaben die Crate §e" + crate4.getName() + " §bbekommen!");
                }
            }
        },  0L, 1200L);

        setRunning(true);
    }

    public void setRunnableID(int runnableID) {
        this.runnableID = runnableID;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
