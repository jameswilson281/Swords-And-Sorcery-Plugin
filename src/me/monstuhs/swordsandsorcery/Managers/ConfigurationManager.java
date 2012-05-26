/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers;

import me.monstuhs.swordsandsorcery.Utilities.ConfigConstants;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author James
 */
public class ConfigurationManager {
    private static JavaPlugin _plugin;
    private static FileConfiguration _configFile;

    /**
     * @return the _configFile
     */
    public FileConfiguration getConfigFile() {
        return _configFile;
    }
    
    public ConfigurationManager(JavaPlugin thisPlugin){        
        _plugin = thisPlugin;
        _configFile = _plugin.getConfig();
        createOrLoadConfig();
    }
    
    private void createOrLoadConfig() {        
        _configFile.options().copyDefaults(true);
        
        
        _configFile.addDefault(ConfigConstants.GlobalSettings.WORLD_NAME, _plugin.getServer().getWorlds().get(0).getName());

        _configFile.addDefault(ConfigConstants.MiningActivities.ACTIVITY_MINING_PpL_DOUBLE_DROP, 1);
        _configFile.addDefault(ConfigConstants.MiningActivities.ACTIVITY_MINING_PpL_INSTABREAK, 1);
        
        _configFile.addDefault(ConfigConstants.SmithingActivities.ACTIVITY_SMITHING_LEVEL_INTERVAL_TO_DECREASE_COST, 10);
        _configFile.addDefault(ConfigConstants.SmithingActivities.ACTIVITY_SMITHING_MINIMUM_REQUIRED_MATERIAL, 1);
        
        _configFile.addDefault(ConfigConstants.CombatActivities.ACTIVITY_COMBAT_PpL_DODGE, 2);
        _configFile.addDefault(ConfigConstants.CombatActivities.ACTIVITY_COMBAT_PpL_CRIT, 2);
        
        _configFile.addDefault(ConfigConstants.PassiveActivities.ACTIVITY_PASSIVE_REGEN_DELAY, 20);
        _configFile.addDefault(ConfigConstants.PassiveActivities.ACTIVITY_PASSIVE_REGEN_HH_PER_LEVEL, 0.5);
        
        _configFile.addDefault(ConfigConstants.GlobalSettings.SETTINGS_COMBAT_DODGE_MODIFIER, 75);
        _configFile.addDefault(ConfigConstants.GlobalSettings.SETTINGS_COMBAT_CRIT_MODIFIER, 200);
        _configFile.addDefault(ConfigConstants.GlobalSettings.SETTINGS_COMBAT_HEADSHOT_MODIFIER, 200);
        _configFile.addDefault(ConfigConstants.GlobalSettings.SETTINGS_COMBAT_MAX_CRIT_CHANCE, 75);
        _configFile.addDefault(ConfigConstants.GlobalSettings.SETTINGS_COMBAT_MAX_DODGE_CHANCE, 75);
        _configFile.addDefault(ConfigConstants.GlobalSettings.SETTINGS_WORLD_ORE_IRON_FREQUENCY_PERCENTAGE, 75);
        
        _configFile.addDefault(ConfigConstants.Sorcery.SORCERY_DESTRUCTION_MANA_MATERIAL, Material.REDSTONE_ORE.toString());
        _configFile.addDefault(ConfigConstants.Sorcery.SORCERY_DESTRUCTION_WAND, Material.BLAZE_ROD.toString());
        _configFile.addDefault(ConfigConstants.Sorcery.SORCERY_DESTRUCTION_SPELLS_FIREBALL_MANACOST, 1);
        _configFile.addDefault(ConfigConstants.Sorcery.SORCERY_DESTRUCTION_SPELLS_KNOCKBACK_MANACOST, 1);
        _configFile.addDefault(ConfigConstants.Sorcery.SORCERY_DESTRUCTION_SPELLS_KNOCKBACK_RANGE_PER_LEVEL, 3);
        _configFile.addDefault(ConfigConstants.Sorcery.SORCERY_DESCTURCTION_SPELLS_LIGHTING_MANACOST, 1);
        _configFile.addDefault(ConfigConstants.Sorcery.SORCERY_DESCTURCTION_SPELLS_LIGHTING_RANGE, 50);
        _configFile.addDefault(ConfigConstants.Sorcery.SORCERY_DESCTURCTION_SPELLS_PIT_MANACOST, 1);

        _configFile.addDefault(ConfigConstants.Sorcery.SORCERY_HEALING_MANA_MATERIAL, Material.REDSTONE_ORE.toString());
        _configFile.addDefault(ConfigConstants.Sorcery.SORCERY_HEALING_WAND, Material.BLAZE_ROD.toString());
        _configFile.addDefault(ConfigConstants.Sorcery.SORCERY_HEALING_SPELLS_HEAL_MANACOST, 1);
        _configFile.addDefault(ConfigConstants.Sorcery.SORCERY_HEALING_SPELLS_HEAL_RANGE, 50);        
        _configFile.addDefault(ConfigConstants.Sorcery.SORCERY_ALLOW_MANA_BURN, Boolean.TRUE);
        
        _plugin.saveConfig();
    }
}
