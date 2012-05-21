/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author James
 */
public class SmithingManager {
    private final ConfigurationManager _configManager;
    
    public SmithingManager(ConfigurationManager configManager){
        _configManager = configManager;
    }
    
    public static void repairItem(Player smith, ItemStack itemToRepair){
        short currentDurability = itemToRepair.getDurability();
        short maxDurability = itemToRepair.getType().getMaxDurability();
        int difference = maxDurability - currentDurability;
        
    }
}
