/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public class SpawnCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] strings) {
        if(sender instanceof Player){
            Location loc = ((Player)sender).getLocation().add(0, 30, 0);
            ((Player)sender).getWorld().spawnCreature(loc, EntityType.ENDER_DRAGON);            
        }
        return true;
    }
    
}
