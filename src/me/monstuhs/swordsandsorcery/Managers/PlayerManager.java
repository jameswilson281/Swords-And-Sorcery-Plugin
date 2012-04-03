/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers;

import java.util.HashMap;
import me.monstuhs.swordsandsorcery.Models.SaSPlayerData;
import me.monstuhs.swordsandsorcery.Models.SaSPlayerSkills;
import org.bukkit.entity.Player;

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
        SaSPlayerData data = _SaSPlayerData.get(caster);
        int currentMana = data.ManaPool;
        int newMana = currentMana - amountToExpend;
        if(newMana < 0 && manaBurn){
            caster.damage(-1*newMana);            
        }
        _SaSPlayerData.put(caster, data);
    }
    
    public static Boolean PlayerHasMana(Player caster){
        return _SaSPlayerData.get(caster).ManaPool > 0;
    }
    
    public static void RegenManaNaturally(){
        for(SaSPlayerData data : _SaSPlayerData.values()){            
            data.ManaPool += data.ManaRegenAmount;
        }
    }
}
