/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers.Combat;

import java.util.Random;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 *
 * @author James
 */
public class CombatManager {

    public static void HandleBlocking(Player defender, EntityDamageByEntityEvent event) {
        Boolean blocked = new Random().nextInt(100) < defender.getLevel();
        if (blocked) {
            event.setDamage(event.getDamage() / 2);
        }
    }

    public static void HandleMeleeAttack(EntityDamageByEntityEvent damageEvent) {
        Player attacker = (Player) damageEvent.getDamager();
        int newDamage = damageEvent.getDamage() + Math.max(attacker.getLevel() / 3, 0);
        damageEvent.setDamage(newDamage);
    }
}
