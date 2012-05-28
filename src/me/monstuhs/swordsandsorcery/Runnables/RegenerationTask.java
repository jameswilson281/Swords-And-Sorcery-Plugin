/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Runnables;

import java.util.List;
import java.util.ListIterator;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public class RegenerationTask implements Runnable {
    private static World _world;
    private static double _regenPerLevel;

    public RegenerationTask(World world, double regenPerLevel) {
        _world = world;
        _regenPerLevel = regenPerLevel;
    }

    @Override
    public void run() {        
        
        List<Player> iList = _world.getPlayers();
        for(ListIterator<Player> itr = iList.listIterator(); itr.hasNext();) {
        //for (Player player : _world.getPlayers()) {            
            Player player = itr.next();
            int currentHealth = player.getHealth();
            if (currentHealth < player.getMaxHealth()) {
                int amountToRegen = (int) (player.getLevel() * _regenPerLevel);
                if (amountToRegen > 0) {
                    int newHealth = Math.min(currentHealth + amountToRegen, player.getMaxHealth());
                    player.setHealth(newHealth);
                    //player.sendMessage(ChatColor.AQUA + "You regened " + amountToRegen + " health (" + currentHealth + " / " + newHealth + ")");
                }
            }
        }
    }
}