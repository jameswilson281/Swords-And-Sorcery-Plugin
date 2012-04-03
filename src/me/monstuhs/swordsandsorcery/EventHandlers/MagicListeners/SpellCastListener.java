/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.EventHandlers.MagicListeners;

import me.monstuhs.swordsandsorcery.Managers.Spells.SpellManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 *
 * @author James
 */
public class SpellCastListener implements Listener {

    //TODO: Make this configurable
    Material magicWand = Material.STICK;
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onRightClick(PlayerInteractEvent event) {        
        if (event.getMaterial() == magicWand) {
            Player caster = event.getPlayer();
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                SpellManager.CycleSpellsForPlayer(event.getPlayer());
            } else if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                SpellManager.HandleSpellCasting(caster);
            }
        }
    }
    
    public class RegeneratingPlayer {
        
        private Player target;
        private int regenAmount;
        private int remainingTicks;
        private String healMessage;
        public Boolean removeMe;
        
        public RegeneratingPlayer(Player regenTarget, int strenghInHalfHearts, int initialTicks, String message) {
            target = regenTarget;
            regenAmount = strenghInHalfHearts;
            remainingTicks = initialTicks;
            healMessage = message;
            removeMe = false;
        }
        
        public void Regen() {
            int currentHealth = target.getHealth();
            target.sendMessage(healMessage);
            target.setHealth(Math.min(target.getMaxHealth(), currentHealth + regenAmount));
            remainingTicks--;
            if (remainingTicks <= 0) {
                removeMe = true;
            }
        }
    }
}
