/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Runnables;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;

/**
 *
 * @author James
 */
public class SpawnTask implements Runnable {
    World world;
    
    public SpawnTask(World runWorld){
        world = runWorld;
    }
    
    @Override
    public void run() {
        List<LivingEntity> existingEntities = world.getLivingEntities();
        for(LivingEntity entity : existingEntities){
            if(entity instanceof Monster && entity.getType() != EntityType.PIG_ZOMBIE){
                Location thisLocation = entity.getLocation();
                Location spawnLocation = world.getHighestBlockAt(thisLocation.getBlockX(), thisLocation.getBlockZ()).getLocation();
                world.spawnCreature(spawnLocation, EntityType.PIG_ZOMBIE);
                System.out.println("spawning bonus pig!");
            }
        }
    }
}
