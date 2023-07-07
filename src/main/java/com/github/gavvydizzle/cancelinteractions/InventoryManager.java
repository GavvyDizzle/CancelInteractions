package com.github.gavvydizzle.cancelinteractions;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashSet;

public class InventoryManager implements Listener {

    private final CancelInteractions instance;
    private final HashSet<Material> disabledBlocks;

    public InventoryManager(CancelInteractions instance) {
        this.instance = instance;
        disabledBlocks = new HashSet<>();

        reload(false);
    }

    public void reload(boolean outputList) {
        FileConfiguration config = instance.getConfig();
        config.options().copyDefaults(true);
        config.addDefault("types", new ArrayList<>());
        instance.saveConfig();

        disabledBlocks.clear();
        for (String s : config.getStringList("types")) {
            try {
                disabledBlocks.add(Material.valueOf(s.toUpperCase()));
            } catch (Exception e) {
                instance.getLogger().warning("Unknown material: " + s.toUpperCase());
            }
        }

        if (outputList) {
            instance.getLogger().info("Cancelling interactions for: " + disabledBlocks);
        }
    }

    @EventHandler
    private void stopInventoryOpening(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock() != null && disabledBlocks.contains(e.getClickedBlock().getType())) {
            if (!e.getPlayer().hasPermission("cancelinteractions.bypass")) {
                e.setCancelled(true);
            }
        }
    }

}
