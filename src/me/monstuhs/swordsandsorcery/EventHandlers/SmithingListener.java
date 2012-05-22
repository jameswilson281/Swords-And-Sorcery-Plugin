/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.EventHandlers;

import me.monstuhs.swordsandsorcery.Managers.SmithingManager;
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
public class SmithingListener implements Listener {
    
    private SmithingManager _smithingManager;
    
    public SmithingListener(SmithingManager smithingManager){
        _smithingManager = smithingManager;
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onRightClickAnvil(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.IRON_BLOCK) {
            _smithingManager.repairItem(event.getPlayer(), event.getItem());
        }
    }
}
