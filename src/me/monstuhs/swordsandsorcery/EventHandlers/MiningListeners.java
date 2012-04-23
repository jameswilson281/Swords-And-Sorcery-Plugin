/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.EventHandlers;

import me.monstuhs.swordsandsorcery.Managers.MiningManager;
import me.monstuhs.swordsandsorcery.Utilities.MiningHelpers;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author James
 */
public class MiningListeners implements Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onBlockDamage(BlockDamageEvent event) {  
        
        //Insta-break check
        if(MiningHelpers.isOre(event.getBlock().getType()) == false){
            if(MiningManager.getInstaBreakForPlayer(event.getPlayer())){
                event.setInstaBreak(true);
            }            
        }
    }    

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        
        //Double-drop check
        if(MiningHelpers.isOre(event.getBlock().getType()) && MiningManager.getDoubleDropForPlayer(event.getPlayer())){
            Block minedBlock = event.getBlock();
            Material drop = MiningHelpers.getDropTypeForBlock(minedBlock.getType());
            minedBlock.getWorld().dropItemNaturally(minedBlock.getLocation(), new ItemStack(drop));
        }
    }
}
