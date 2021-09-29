package de.skyenton.skullcrates.listener;

import de.skyenton.skullcrates.SkullcratesPlugin;
import de.skyenton.skullcrates.crate.Crate;
import de.skyenton.skullcrates.crate.ToSetTypes;
import de.skyenton.skullcrates.services.CrateService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerChatListener implements Listener {

    private CrateService crateService;

    public PlayerChatListener(CrateService crateService) {
        this.crateService = crateService;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        if(crateService.getCratesInMaking().containsKey(player.getUniqueId())) {

            Crate crate = crateService.getCratesInMaking().get(player.getUniqueId());

            if(event.getMessage() == null) {
                player.sendMessage(SkullcratesPlugin.PREFIX + "§cBitte gebe einen namen an!");
            } else {
                switch (crate.getCurrentToSet()) {
                    case DISPLAYNAME:
                        event.setCancelled(true);
                        crate.setDisplayName(event.getMessage().replaceAll("&", "§"));
                        crate.setCurrentToSet(ToSetTypes.NONE);
                        player.sendMessage(SkullcratesPlugin.PREFIX + "§aDisplayname " + crate.getDisplayName() + " §f§agesetzt!");
                        break;
                    case SKULL:
                        event.setCancelled(true);
                        crate.setSkull(event.getMessage());
                        crate.setCurrentToSet(ToSetTypes.NONE);
                        player.sendMessage(SkullcratesPlugin.PREFIX + crate.getSkullName() + " §f§agesetzt!");
                        break;
                    case LORE:
                        event.setCancelled(true);
                        List<String> lore = new ArrayList<>();
                        lore.add(event.getMessage().replaceAll("&", "§"));
                        crate.setLore(lore);
                        crate.setCurrentToSet(ToSetTypes.NONE);
                        player.sendMessage(SkullcratesPlugin.PREFIX + "§aLore §f§agesetzt!");
                        break;
                    default:
                        break;
                }
            }
        }
    }

}
