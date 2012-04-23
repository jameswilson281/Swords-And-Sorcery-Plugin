/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery;

import me.monstuhs.swordsandsorcery.Commands.ShowStatsCommand;
import me.monstuhs.swordsandsorcery.EventHandlers.CombatListeners;
import me.monstuhs.swordsandsorcery.EventHandlers.MiningListeners;
import me.monstuhs.swordsandsorcery.EventHandlers.SpellCastListener;
import me.monstuhs.swordsandsorcery.Managers.*;
import me.monstuhs.swordsandsorcery.Runnables.RegenerationTask;
import me.monstuhs.swordsandsorcery.Utilities.BukkitHelpers;
import me.monstuhs.swordsandsorcery.Utilities.ConfigConstants;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author James
 */
public class SwordsAndSorcery extends JavaPlugin {    
    
    public static ConfigurationManager ConfigManager;
    public static PlayerLevelManager PlayerLvlManager;
    public static MiningManager MiningManager;
    public static CombatManager CombatManager;    
    private static PluginManager _pluginManager = Bukkit.getPluginManager();
    private static World _thisWorld;

    @Override
    public void onEnable() {
        ConfigManager = new ConfigurationManager(this);
        PlayerLvlManager = new PlayerLevelManager(ConfigManager);
        MiningManager = new MiningManager();
        CombatManager = new CombatManager();
        SpellManager.InitializeSpellManager(this);
        
        String worldName = ConfigManager.getConfigFile().getString(ConfigConstants.GlobalSettings.WORLD_NAME);
        _thisWorld = worldName.isEmpty() ? Bukkit.getServer().getWorlds().get(0) : Bukkit.getServer().getWorld(worldName);
        
        
        _pluginManager.registerEvents(new MiningListeners(), this);
        _pluginManager.registerEvents(new CombatListeners(), this);
        _pluginManager.registerEvents(new SpellCastListener(), this);
        
        registerCommands();
        startRegenTicker();
    }

    @Override
    public void onDisable() {
    }
    
    public void saveConfigurationFile(){
        saveConfig();
    }
    
    private void registerCommands(){
        this.getCommand(ConfigConstants.Commands.COMMANDS_SHOW_STATS).setExecutor(new ShowStatsCommand());
    }
    
    private void startRegenTicker(){
        long initialDelay = BukkitHelpers.getDelay(10);
        long repeatDelay = BukkitHelpers.getDelay(ConfigManager.getConfigFile().getLong(ConfigConstants.PassiveActivities.ACTIVITY_PASSIVE_REGEN_DELAY));        
        double regenRate = PlayerLvlManager.getRegenHalfHeartsPerLevel();
        this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new RegenerationTask(_thisWorld, regenRate), initialDelay, repeatDelay);
    }
}
