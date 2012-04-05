/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers;

import java.util.HashMap;
import me.monstuhs.swordsandsorcery.Models.Spells.SpellMetaData;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 *
 * @author James
 */
public class PlayerManager {

    public static Boolean BurnMana(Player caster, SpellMetaData spellCast, Boolean manaBurn) {

        int remainingManaNeeded = spellCast.ManaCost;
        Boolean canCast = true;
        PlayerInventory inventory = caster.getInventory();

        
        HashMap<Integer, ItemStack> couldnotRemove =
                inventory.removeItem(new ItemStack(Material.REDSTONE, remainingManaNeeded));
        
        int manaBurnAmount = couldnotRemove.isEmpty() ? 0 : couldnotRemove.get(0).getAmount();
        if(manaBurnAmount > 0){
            if(manaBurn){
                caster.damage(manaBurnAmount);                
                caster.sendMessage("You burned " + manaBurnAmount + " mana.");
            }
            else{
                canCast = false;
                caster.sendMessage("You need " + manaBurnAmount + " more mana to cast that");
            }
        }
        caster.updateInventory();        
        return canCast;

    }
}
