/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers;

import java.util.HashMap;
import java.util.Map.Entry;
import me.monstuhs.swordsandsorcery.Utilities.BukkitHelpers;
import me.monstuhs.swordsandsorcery.Utilities.ConfigConstants;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public class PlayerLevelManager {
    
    private double _regenHalfHeartsPerLevel;
    private long _regenDelay;
    
    public PlayerLevelManager(ConfigurationManager configManager){
        _regenHalfHeartsPerLevel = configManager.getConfigFile().getDouble(ConfigConstants.PassiveActivities.ACTIVITY_PASSIVE_REGEN_HH_PER_LEVEL);        
        _regenDelay = configManager.getConfigFile().getLong(ConfigConstants.PassiveActivities.ACTIVITY_PASSIVE_REGEN_DELAY);
    }
    
    public void displayPlayerStats(Player player){
        HashMap<String, Integer> playerStats = new HashMap<String, Integer>();
        int playerLevel = player.getLevel();
        
        playerStats.put("Chance to Crit:  ", CombatManager.getChanceToCritPerLevel() * playerLevel);
        playerStats.put("Chance to Dodge: ", CombatManager.getChanceToDodgePerLevel() * playerLevel);        
        playerStats.put("Chance to double-drop: ", MiningManager.getChanceToDoubleDropPerLevel() * playerLevel);
        playerStats.put("Chance to insta-break: ", MiningManager.getChanceToBreakPerLevel() * playerLevel);
        
        for(Entry<String, Integer> stat : playerStats.entrySet()){
            player.sendMessage(stat.getKey() + stat.getValue() + "%");
        }
        double regenPerSecond = player.getLevel() * getRegenHalfHeartsPerLevel() / _regenDelay;
        player.sendMessage("Extra Regen: " + BukkitHelpers.formatDouble(regenPerSecond) + "/second)");
    }
    
    public int getPlayerRegenAmount(Player player){
        return (int) (player.getLevel() * getRegenHalfHeartsPerLevel());
    }

    public double getRegenHalfHeartsPerLevel() {
        return _regenHalfHeartsPerLevel;
    }
}
