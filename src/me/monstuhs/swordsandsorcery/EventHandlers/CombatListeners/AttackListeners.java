/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.EventHandlers.CombatListeners;

import me.monstuhs.swordsandsorcery.Managers.Combat.CombatManager;
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
public class AttackListeners implements Listener {
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDamaged(EntityDamageEvent event){
        
        //See if this is a player attacking a mob
        if(event instanceof EntityDamageByEntityEvent && event.getEntity() instanceof LivingEntity){
            
            EntityDamageByEntityEvent damageEvent = (EntityDamageByEntityEvent) event;
            //See if it was a melee weapon
            if(damageEvent.getDamager() instanceof Player){
                CombatManager.HandleMeleeAttack(damageEvent);
            }
        }
    }
}
