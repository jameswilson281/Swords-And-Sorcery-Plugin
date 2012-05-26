/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery;

import me.monstuhs.swordsandsorcery.BlockPopulators.OreDistributionPopulator;
import me.monstuhs.swordsandsorcery.Commands.ShowStatsCommand;
import me.monstuhs.swordsandsorcery.Commands.SpawnCommands;
import me.monstuhs.swordsandsorcery.EventHandlers.*;
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

    private ConfigurationManager _configManager;
    private PlayerLevelManager _playerLvlManager;
    private MiningManager _miningManager;
    private CombatManager _combatManager;
    private SpellManager _spellManager;
    private SmithingManager _smithingManager;
    private PluginManager _pluginManager = Bukkit.getPluginManager();
    private World _thisWorld;

    @Override
    public void onEnable() {
        _configManager      = new ConfigurationManager(this);
        _playerLvlManager   = new PlayerLevelManager(_configManager);
        _miningManager      = new MiningManager(_configManager);
        _combatManager      = new CombatManager(_configManager);
        _spellManager       = new SpellManager(_configManager.getConfigFile());
        _smithingManager    = new SmithingManager(_configManager);
        
        String worldName = _configManager.getConfigFile().getString(ConfigConstants.GlobalSettings.WORLD_NAME);
        _thisWorld = worldName.isEmpty() ? Bukkit.getServer().getWorlds().get(0) : Bukkit.getServer().getWorld(worldName);
        _thisWorld.getPopulators().add(new OreDistributionPopulator(_configManager));

        _pluginManager.registerEvents(new MiningListeners(), this);
        _pluginManager.registerEvents(new CombatListeners(), this);
        _pluginManager.registerEvents(new SpellCastListener(_spellManager), this);
        _pluginManager.registerEvents(new SmithingListener(_smithingManager), this);

        registerCommands();
        startRegenTicker();
    }

    @Override
    public void onDisable() {
    }

    public void saveConfigurationFile() {
        saveConfig();
    }

    private void registerCommands() {
        this.getCommand(ConfigConstants.Commands.COMMANDS_SHOW_STATS).setExecutor(new ShowStatsCommand(_playerLvlManager));
        this.getCommand(ConfigConstants.Commands.COMMANDS_SPAWN_DRAGON).setExecutor(new SpawnCommands());
    }

    private void startRegenTicker() {
        long initialDelay = BukkitHelpers.getDelay(10);
        long repeatDelay = BukkitHelpers.getDelay(_configManager.getConfigFile().getLong(ConfigConstants.PassiveActivities.ACTIVITY_PASSIVE_REGEN_DELAY));
        double regenRate = _playerLvlManager.getRegenHalfHeartsPerLevel();
        this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new RegenerationTask(_thisWorld, regenRate), initialDelay, repeatDelay);
    }

    public ConfigurationManager getConfigManager() {
        return _configManager;
    }

    public void setConfigManager(ConfigurationManager configManager) {
        this._configManager = configManager;
    }

    public PlayerLevelManager getPlayerLvlManager() {
        return _playerLvlManager;
    }

    public MiningManager getMiningManager() {
        return _miningManager;
    }

    public CombatManager getCombatManager() {
        return _combatManager;
    }

    public SpellManager getSpellManager() {
        return _spellManager;
    }
    
    
}
