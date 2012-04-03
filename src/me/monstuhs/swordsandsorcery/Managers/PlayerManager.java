/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers;

import java.util.HashMap;
import me.monstuhs.swordsandsorcery.Models.SaSPlayerData;
import me.monstuhs.swordsandsorcery.Models.SaSPlayerSkills;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author James
 */
public class PlayerManager {
    
    private static HashMap<Player,SaSPlayerData> _SaSPlayerData;
    
    public void PlayerManager(HashMap<Player,SaSPlayerData> playerData){
        _SaSPlayerData = playerData;
    }    
    
    public static void IncreaseSkill(String player, SaSPlayerSkills skill, int amount){
        if(_SaSPlayerData.containsKey(player)){
            SaSPlayerData thisPlayersData = _SaSPlayerData.get(player);
            switch(skill){
                case MELEE:     thisPlayersData.MeleeLevel      += amount; break;
                case ARCHERY:   thisPlayersData.ArcheryLevel    += amount; break;
                case DEFENSE:   thisPlayersData.DefenseLevel    += amount; break;
                case SORCERY:   thisPlayersData.SorceryLevel    += amount; break;
                default: break;
            }
        }
    }

    public static void BurnMana(Player caster, int amountToExpend, Boolean manaBurn){
        HashMap<Integer, ? extends ItemStack> mana = caster.getInventory().all(Material.REDSTONE_ORE);
        caster.sendMessage("You burned " + amountToExpend + " mana point.");
    }
    
    public static void RegenManaNaturally(){
        for(SaSPlayerData data : _SaSPlayerData.values()){            
            data.ManaPool += data.ManaRegenAmount;
        }
    }
}
