/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.EventHandlers.SorceryListeners;

import me.monstuhs.swordsandsorcery.Managers.Sorcery.SpellManager;
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
        if (SpellManager.GetSpellByWand(event.getMaterial()) != null) {
            
            Player caster = event.getPlayer();
            
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                SpellManager.CycleSpellsForPlayer(event.getPlayer());
            } else if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                SpellManager.HandleSpellCasting(caster);
            }
        }
    }
}
