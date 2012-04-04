/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.EventHandlers.CombatListeners;

import me.monstuhs.swordsandsorcery.Managers.Combat.CombatManager;
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
    public void onEntityDamaged(EntityDamageEvent event) {

        //See if this is a player
        if (event.getEntity() instanceof Player) {
            Player defender = (Player) event.getEntity();

            if (event instanceof EntityDamageByEntityEvent) {
                EntityDamageByEntityEvent damageEvent = (EntityDamageByEntityEvent) event;
                if (defender.isBlocking()) {
                    CombatManager.HandleBlocking(defender, damageEvent);
                }
            }

        }
    }
}
