/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery;

import me.monstuhs.swordsandsorcery.EventHandlers.CombatListeners.AttackListeners;
import me.monstuhs.swordsandsorcery.EventHandlers.CombatListeners.DamageListeners;
import me.monstuhs.swordsandsorcery.EventHandlers.SkillsListeners.MiningListeners;
import me.monstuhs.swordsandsorcery.EventHandlers.SorceryListeners.SpellCastListener;
import me.monstuhs.swordsandsorcery.Managers.Sorcery.SpellManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author James
 */
public class SwordsAndSorcery extends JavaPlugin {

    private static PluginManager _manager = Bukkit.getPluginManager();
    private static FileConfiguration _configFile;

    /**
     * @return the _configFile
     */
    public static FileConfiguration getConfigFile() {
        return _configFile;
    }

    @Override
    public void onEnable() {
        _configFile = getConfig();
        createorloadconfig();
        SpellManager.InitializeSpellManager(this);
        
        _manager.registerEvents(new AttackListeners(), this);
        _manager.registerEvents(new DamageListeners(), this);
        _manager.registerEvents(new SpellCastListener(), this);
        _manager.registerEvents(new MiningListeners(), this);
    }

    @Override
    public void onDisable() {
    }



    public void createorloadconfig() {
        SaSUtilities.WriteMessageToConsole("Creating/loading config");        
        _configFile.options().copyDefaults(true);        

        _configFile.addDefault(SaSUtilities.SorceryConstants.SORCERY_DESTRUCTION_MANA_MATERIAL, Material.REDSTONE_ORE.toString());
        _configFile.addDefault(SaSUtilities.SorceryConstants.SORCERY_DESTRUCTION_WAND, Material.BLAZE_ROD.toString());
        _configFile.addDefault(SaSUtilities.SorceryConstants.SORCERY_DESTRUCTION_SPELLS_FIREBALL_MANACOST, 1);
        _configFile.addDefault(SaSUtilities.SorceryConstants.SORCERY_DESTRUCTION_SPELLS_KNOCKBACK_MANACOST, 1);
        _configFile.addDefault(SaSUtilities.SorceryConstants.SORCERY_DESCTURCTION_SPELLS_LIGHTING_MANACOST, 1);
        _configFile.addDefault(SaSUtilities.SorceryConstants.SORCERY_DESCTURCTION_SPELLS_LIGHTING_RANGE, 50);

        _configFile.addDefault(SaSUtilities.SorceryConstants.SORCERY_HEALING_MANA_MATERIAL, Material.REDSTONE_ORE.toString());
        _configFile.addDefault(SaSUtilities.SorceryConstants.SORCERY_HEALING_WAND, Material.BLAZE_ROD.toString());
        _configFile.addDefault(SaSUtilities.SorceryConstants.SORCERY_HEALING_SPELLS_HEAL_MANACOST, 1);
        _configFile.addDefault(SaSUtilities.SorceryConstants.SORCERY_HEALING_SPELLS_HEAL_RANGE, 50);
        
        _configFile.addDefault(SaSUtilities.SorceryConstants.SORCERY_ALLOW_MANA_BURN, Boolean.TRUE);
        
        _configFile.addDefault(SaSUtilities.SkillConstants.SKILL_MINING_PLAYER_LEVEL_BLOCK_DAMAGE_MODIFIER, 0.25);
        _configFile.addDefault(SaSUtilities.SkillConstants.SKILL_MINING_PLAYER_LEVEL_ORE_DOUBLE_DROP_PERCENTAGE, 0.01);
        
        saveConfig();
    }
}
