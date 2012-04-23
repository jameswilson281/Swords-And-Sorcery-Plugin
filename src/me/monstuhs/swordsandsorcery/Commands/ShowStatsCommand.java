/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Commands;

import me.monstuhs.swordsandsorcery.SwordsAndSorcery;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public class ShowStatsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (sender instanceof Player){
            SwordsAndSorcery.PlayerLvlManager.displayPlayerStats((Player)sender);
        }
        return true;
    }
    
}
