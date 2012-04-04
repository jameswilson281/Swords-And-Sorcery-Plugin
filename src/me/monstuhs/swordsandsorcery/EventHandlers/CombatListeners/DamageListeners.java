/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.EventHandlers.CombatListeners;

import java.util.Random;
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
            
            if(defender.isBlocking()){                
                Boolean blocked = new Random().nextInt(100) < defender.getLevel();
                if(blocked){                    
                    defender.sendMessage(ChatColor.RED + "Damage would have been " + event.getDamage());
                    event.setDamage(event.getDamage() / 2);
                    defender.sendMessage(ChatColor.RED + "You blocked [ " + event.getDamage() + " ]");
                }                
            }
        }
    }
}
