/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers.Models.Spells.Healing;

import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base.Spell;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base.SpellTypes;
import me.monstuhs.swordsandsorcery.Utilities.ConfigConstants;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public class Endurance extends Spell {

    private int _halfHeartsPerTick;    
    
    public Endurance(FileConfiguration config, LivingEntity caster) {
        super(config, caster, null, 5, ConfigConstants.Sorcery.SORCERY_HEALING_SPELLS_ENDURANCE_MANACOST, null, SpellTypes.RECURRING);
        
        //TODO: CONFIG
        _halfHeartsPerTick = 2;
        
    }

    @Override
    public void Cast() {
        if(super.burnMana()){
            int currentHealth = _target.getHealth();
            int maxHealth = _target.getMaxHealth();
            int amountToHeal = _halfHeartsPerTick;
            int newHealth = Math.min(maxHealth, currentHealth + amountToHeal);
            _target.setHealth(newHealth);
            if(_target instanceof Player && newHealth <= maxHealth){
                ((Player)_target).sendMessage("Regen for " + amountToHeal + " HP");
            }
            _ticks--;
        }
    }
}
