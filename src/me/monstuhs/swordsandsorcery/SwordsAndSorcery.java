/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery;

import me.monstuhs.swordsandsorcery.EventHandlers.CombatListeners.AttackListeners;
import me.monstuhs.swordsandsorcery.EventHandlers.CombatListeners.DamageListeners;
import me.monstuhs.swordsandsorcery.EventHandlers.MagicListeners.SpellCastListener;
import me.monstuhs.swordsandsorcery.Managers.Spells.SpellManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author James
 */
public class SwordsAndSorcery extends JavaPlugin {

    private static PluginManager _manager = Bukkit.getPluginManager();

    @Override
    public void onEnable() {
        createorloadconfig();
        SpellManager.InitializeSpellManager(this);
        
        _manager.registerEvents(new AttackListeners(), this);
        _manager.registerEvents(new DamageListeners(), this);
        _manager.registerEvents(new SpellCastListener(), this);
    }

    @Override
    public void onDisable() {
    }

    private void WriteMessageToConsole(String message) {
        System.out.println(message);
    }

    public void createorloadconfig() {
        WriteMessageToConsole("Creating/loading config");        
        getConfig().options().copyDefaults(true);        

        getConfig().addDefault(SaSUtilities.SORCERY_DESTRUCTION_MANA_MATERIAL, Material.REDSTONE_ORE.toString());
        getConfig().addDefault(SaSUtilities.SORCERY_DESTRUCTION_WAND, Material.BLAZE_ROD.toString());
        getConfig().addDefault(SaSUtilities.SORCERY_DESTRUCTION_SPELLS_FIREBALL_MANACOST, 1);
        getConfig().addDefault(SaSUtilities.SORCERY_DESTRUCTION_SPELLS_KNOCKBACK_MANACOST, 1);
        getConfig().addDefault(SaSUtilities.SORCERY_DESCTURCTION_SPELLS_LIGHTING_MANACOST, 1);
        getConfig().addDefault(SaSUtilities.SORCERY_DESCTURCTION_SPELLS_LIGHTING_RANGE, 50);

        getConfig().addDefault(SaSUtilities.SORCERY_HEALING_MANA_MATERIAL, Material.REDSTONE_ORE.toString());
        getConfig().addDefault(SaSUtilities.SORCERY_HEALING_WAND, Material.BLAZE_ROD.toString());
        getConfig().addDefault(SaSUtilities.SORCERY_HEALING_SPELLS_HEAL_MANACOST, 1);
        getConfig().addDefault(SaSUtilities.SORCERY_HEALING_SPELLS_HEAL_RANGE, 50);
        
        getConfig().addDefault(SaSUtilities.SORCERY_ALLOW_MANA_BURN, Boolean.TRUE);
        
        saveConfig();
    }
}
