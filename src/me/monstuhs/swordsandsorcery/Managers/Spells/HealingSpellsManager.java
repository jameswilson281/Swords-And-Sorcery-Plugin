/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers.Spells;

import me.monstuhs.swordsandsorcery.Models.Spells.Base.Spell.SpellName;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public class HealingSpellsManager {
    
    public static void HealTarget(Player caster, LivingEntity target){
        int maxHealth = target.getMaxHealth();
        int currentHealth = target.getHealth();
        int maxHealAmount = GetHealAmount(caster, SpellName.HEAL);
        int newHealth = Math.min(maxHealth, currentHealth + maxHealAmount);
        int amountHealed = newHealth - currentHealth;
        
        target.setHealth(newHealth);
        caster.sendMessage("You healed " + target.getType().toString() + " for " + amountHealed);
        
        if(target instanceof Player){
            Player healee = (Player)target;
            healee.sendMessage(caster.getDisplayName() + " healed you for " + amountHealed);
        }
    }
    
    private static int GetHealAmount(Player caster, SpellName healingSpell){
        int healAmount = 0;
        switch(healingSpell){
            case HEAL: healAmount = caster.getLevel() * 1;
                break;
            default: break;
        }
        return healAmount;
    }
}
