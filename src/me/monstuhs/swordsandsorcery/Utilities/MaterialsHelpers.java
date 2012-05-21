/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Utilities;

import org.bukkit.Material;

/**
 *
 * @author James
 */
public class MaterialsHelpers {
    
    public static Material getDropTypeForBlock(Material blockMat){
        switch(blockMat){
            case COAL_ORE:              return Material.COAL;
            case DIAMOND_ORE:           return Material.DIAMOND;            
            case GLOWING_REDSTONE_ORE:  return Material.REDSTONE;
            case REDSTONE_ORE:          return Material.REDSTONE;
            case GLOWSTONE:             return Material.GLOWSTONE_DUST;
            case LAPIS_ORE:             return Material.REDSTONE;
            default:                    return blockMat;                
        }
    }
    
    public static boolean isOre(Material blockMat){
        switch(blockMat){
            case COAL_ORE:
            case DIAMOND_ORE:
            case GOLD_ORE:
            case GLOWING_REDSTONE_ORE:
            case GLOWSTONE:
            case IRON_ORE:
            case LAPIS_ORE:
            case REDSTONE_ORE:
                return true;
            default:
                return false;
                
        }
    }
    
    public static Material getRequiredMaterialForRepair(Material itemToRepair){
        
        switch(itemToRepair){
            case IRON_AXE:
            case IRON_HOE:
            case IRON_SPADE:
            case IRON_PICKAXE:
            case IRON_SWORD:
                return Material.IRON_ORE;
            case WOOD_AXE:
            case WOOD_HOE:
            case WOOD_SPADE:
            case WOOD_PICKAXE:
            case WOOD_SWORD:
                return Material.WOOD;
            default:
                return Material.AIR;
        }
    }
}
