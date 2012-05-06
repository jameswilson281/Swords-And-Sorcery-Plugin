/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Commands;

import me.monstuhs.swordsandsorcery.Managers.PlayerLevelManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public class ShowStatsCommand implements CommandExecutor {

    private final PlayerLevelManager _playerLvlManager;
    
    public ShowStatsCommand(PlayerLevelManager playerLvlManager){
        _playerLvlManager = playerLvlManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (sender instanceof Player){
            _playerLvlManager.displayPlayerStats((Player)sender);
        }
        return true;
    }
    
}
