/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers.Models.Spells.Healing;

import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base.RecurringSpell;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.SpellMetaData;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public class Endurance implements RecurringSpell {

    private int _ticksRemaining;
    private LivingEntity _target;    
    private int _halfHeartsPerTick;
    
    public Endurance(SpellMetaData data, int ticksRemaining, LivingEntity target, int halfHeartsPerTick) {
        _ticksRemaining = ticksRemaining;
        _target = target;        
        _halfHeartsPerTick = halfHeartsPerTick;        
    }

    @Override
    public void Tick() {        
        int currentHealth = _target.getHealth();
        int maxHealth = _target.getMaxHealth();
        int amountToHeal = _halfHeartsPerTick;
        int newHealth = Math.min(maxHealth, currentHealth + amountToHeal);
        _target.setHealth(newHealth);
        if(_target instanceof Player && newHealth <= maxHealth){
            ((Player)_target).sendMessage("Regen for " + amountToHeal + " HP");
        }
        _ticksRemaining--;
    }
}
