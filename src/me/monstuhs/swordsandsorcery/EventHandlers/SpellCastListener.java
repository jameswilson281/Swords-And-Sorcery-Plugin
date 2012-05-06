/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.EventHandlers;

import me.monstuhs.swordsandsorcery.Managers.SpellManager;
import me.monstuhs.swordsandsorcery.SwordsAndSorcery;
import org.bukkit.Material;
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

    private SpellManager _spellManager;
    
    public SpellCastListener(SpellManager spellManager){
        _spellManager = spellManager;
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onCastSpell(PlayerInteractEvent event) {
        if (event.getMaterial() == Material.BLAZE_ROD && event.getAction() == Action.LEFT_CLICK_AIR) {
            _spellManager.useWand(event.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onChangeActiveSpell(PlayerInteractEvent event) {
        if (event.getMaterial() == Material.BLAZE_ROD && event.getAction() == Action.RIGHT_CLICK_AIR) {
            _spellManager.cycleSpellsForPlayer(event.getPlayer().getName());
        }
    }
}
