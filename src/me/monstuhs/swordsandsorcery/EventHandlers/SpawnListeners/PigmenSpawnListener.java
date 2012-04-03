/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.EventHandlers.SpawnListeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

/**
 *
 * @author James
 */
public class PigmenSpawnListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        Entity spawnee = event.getEntity();
        if (spawnee instanceof Monster) {
            if (spawnee.getType() != EntityType.PIG_ZOMBIE) {
                event.setCancelled(true);
                Location spawnLocation = event.getLocation();
                spawnLocation.getWorld().spawnCreature(spawnLocation, EntityType.PIG_ZOMBIE);
            }
        }
    }
}
