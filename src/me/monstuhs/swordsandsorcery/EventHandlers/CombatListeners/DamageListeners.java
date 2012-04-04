/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.EventHandlers.CombatListeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
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
public class DamageListeners implements Listener {
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDamaged(EntityDamageEvent event){
        
        //See if this is a player
        if(event.getEntity() instanceof Player){
            Player defender = (Player) event.getEntity();
            defender.sendMessage(ChatColor.BLUE + " [ " + defender.getHealth() + " / " + defender.getMaxHealth() + " ] HP");            
        }
    }
}
