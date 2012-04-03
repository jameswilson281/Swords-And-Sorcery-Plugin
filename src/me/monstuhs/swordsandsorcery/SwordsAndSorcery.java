/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery;

import me.monstuhs.swordsandsorcery.EventHandlers.SaSMagicListener;
import me.monstuhs.swordsandsorcery.EventHandlers.SaSCombatArrowListener;
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
        
        _manager.registerEvents(new SaSCombatArrowListener(), this);
        _manager.registerEvents(new SaSMagicListener(), this);        
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
