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

    public static Material getDropTypeForBlock(Material blockMat) {
        switch (blockMat) {
            case COAL_ORE:
                return Material.COAL;
            case DIAMOND_ORE:
                return Material.DIAMOND;
            case GLOWING_REDSTONE_ORE:
                return Material.REDSTONE;
            case REDSTONE_ORE:
                return Material.REDSTONE;
            case GLOWSTONE:
                return Material.GLOWSTONE_DUST;
            case LAPIS_ORE:
                return Material.REDSTONE;
            default:
                return blockMat;
        }
    }

    public static boolean isOre(Material blockMat) {
        switch (blockMat) {
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

    public static Material getRequiredMaterialForRepair(Material itemToRepair) {

        switch (itemToRepair) {
            case LEATHER_HELMET:
            case LEATHER_CHESTPLATE:
            case LEATHER_LEGGINGS:
            case LEATHER_BOOTS:
                return Material.LEATHER;
            case IRON_AXE:
            case IRON_HOE:
            case IRON_SPADE:
            case IRON_PICKAXE:
            case IRON_SWORD:
            case IRON_HELMET:
            case IRON_CHESTPLATE:
            case IRON_LEGGINGS:
            case IRON_BOOTS:
                return Material.IRON_ORE;
            case WOOD_AXE:
            case WOOD_HOE:
            case WOOD_SPADE:
            case WOOD_PICKAXE:
            case WOOD_SWORD:
                return Material.WOOD;
            case STONE_AXE:
            case STONE_HOE:
            case STONE_SPADE:
            case STONE_PICKAXE:
            case STONE_SWORD:
                return Material.COBBLESTONE;
            case GOLD_AXE:
            case GOLD_HOE:
            case GOLD_SPADE:
            case GOLD_PICKAXE:
            case GOLD_SWORD:
            case GOLD_HELMET:
            case GOLD_CHESTPLATE:
            case GOLD_LEGGINGS:
            case GOLD_BOOTS:
                return Material.GOLD_ORE;
            case DIAMOND_AXE:
            case DIAMOND_HOE:
            case DIAMOND_SPADE:
            case DIAMOND_PICKAXE:
            case DIAMOND_SWORD:
            case DIAMOND_HELMET:
            case DIAMOND_CHESTPLATE:
            case DIAMOND_LEGGINGS:
            case DIAMOND_BOOTS:
                return Material.DIAMOND;
            case BOW:
                return Material.STRING;
            default:
                return Material.AIR;
        }
    }

    public static int amountOfMaterialRequiredForItem(Material item) {

        switch (item) {
            case IRON_AXE:
            case IRON_HOE:
            case IRON_SPADE:
            case IRON_PICKAXE:
            case WOOD_AXE:
            case WOOD_HOE:
            case WOOD_SPADE:
            case WOOD_PICKAXE:
            case STONE_AXE:
            case STONE_HOE:
            case STONE_SPADE:
            case STONE_PICKAXE:
            case GOLD_AXE:
            case GOLD_HOE:
            case GOLD_SPADE:
            case GOLD_PICKAXE:
            case DIAMOND_AXE:
            case DIAMOND_HOE:
            case DIAMOND_SPADE:
            case DIAMOND_PICKAXE:
                return 3;
            case IRON_SWORD:
            case DIAMOND_SWORD:
            case WOOD_SWORD:
            case STONE_SWORD:
            case GOLD_SWORD:
                return 2;
            case IRON_HELMET:
            case DIAMOND_HELMET:
            case GOLD_HELMET:
                return 5;
            case IRON_CHESTPLATE:
            case DIAMOND_CHESTPLATE:
            case GOLD_CHESTPLATE:
                return 8;
            case IRON_BOOTS:
            case DIAMOND_BOOTS:
            case GOLD_BOOTS:
                return 4;
            case IRON_LEGGINGS:
            case DIAMOND_LEGGINGS:
            case GOLD_LEGGINGS:
                return 7;
            default:
                return -1;
        }
    }
}
