/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers;

import java.util.HashMap;
import me.monstuhs.swordsandsorcery.Utilities.ConfigConstants;
import me.monstuhs.swordsandsorcery.Utilities.MaterialsHelpers;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 *
 * @author James
 */
public class SmithingManager {
    private final ConfigurationManager _configManager;
    
    public SmithingManager(ConfigurationManager configManager){
        _configManager = configManager;
    }
    
    public void repairItem(Player smith, ItemStack itemToRepair){
        int requiredAmountToRepair = getAmountOfMaterialRequired(smith.getLevel(), itemToRepair.getType());
        if(requiredAmountToRepair > 0){
            Material materialRequiredToRepair = MaterialsHelpers.getRequiredMaterialForRepair(itemToRepair.getType());
            
            PlayerInventory inventory = smith.getInventory();
            if (inventory.contains(materialRequiredToRepair, requiredAmountToRepair)){
                
                //Update inventory
                inventory.removeItem(new ItemStack(materialRequiredToRepair, requiredAmountToRepair));
                smith.updateInventory();                
                
                //Repair item
                itemToRepair.setDurability((short)0);
                smith.sendMessage("You used " + requiredAmountToRepair + " " + materialRequiredToRepair + " to repair this " + itemToRepair.getType().name());
            }
            else{
                smith.sendMessage("You need " + requiredAmountToRepair + " " + materialRequiredToRepair + " to repair this " + itemToRepair.getType().name());
            }
            
        }
        
    }
    
    private int getAmountOfMaterialRequired(int playerLevel, Material itemToRepair){
        int unModifiedAmount = MaterialsHelpers.amountOfMaterialRequiredForItem(itemToRepair);
        int minimumAmount = _configManager.getConfigFile().getInt(ConfigConstants.SmithingActivities.ACTIVITY_SMITHING_MINIMUM_REQUIRED_MATERIAL);
        int amountToDecrease = playerLevel / _configManager.getConfigFile().getInt(ConfigConstants.SmithingActivities.ACTIVITY_SMITHING_LEVEL_INTERVAL_TO_DECREASE_COST);
        return Math.max(minimumAmount, unModifiedAmount - amountToDecrease);
    }
}
