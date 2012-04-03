/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery;

import me.monstuhs.swordsandsorcery.EventHandlers.CombatListeners.ArrowListeners;
import me.monstuhs.swordsandsorcery.EventHandlers.MagicListeners.SpellCastListener;
import org.bukkit.Bukkit;
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
        
        _manager.registerEvents(new ArrowListeners(), this);
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
        getConfig().addDefault("Pigmen.spawn.spawnindaylight",  true);
        getConfig().addDefault("Pigmen.spawn.spawninworld",     true);
        getConfig().addDefault("Pigmen.spawn.spawngroupsize",   5);
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    
    
}
