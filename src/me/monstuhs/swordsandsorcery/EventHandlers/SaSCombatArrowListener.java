/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.EventHandlers;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 *
 * @author James
 */
public class SaSCombatArrowListener implements Listener{
    
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityDamaged(EntityDamageEvent event){
        //Check to see if the defender is a player
        if(event instanceof EntityDamageByEntityEvent && event.getEntityType() == EntityType.PLAYER){
            EntityDamageByEntityEvent damageEvent = (EntityDamageByEntityEvent) event;
            
            //Check to see if it is an arrow hitting the player
            if(damageEvent.getDamager() instanceof Arrow){
                Player defender = (Player)damageEvent.getEntity();
                
                //Check to see if the player is blocking
                if(defender.isBlocking()){
                    event.setCancelled(true);
                }
            }
        }
    }
}
