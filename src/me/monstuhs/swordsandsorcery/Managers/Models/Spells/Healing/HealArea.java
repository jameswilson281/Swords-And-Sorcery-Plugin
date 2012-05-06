/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers.Models.Spells.Healing;

import java.util.List;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base.Spell;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base.SpellTypes;
import me.monstuhs.swordsandsorcery.Utilities.ConfigConstants;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public class HealArea extends Spell {
    
    private int _healPerLevel;
    
    public HealArea(FileConfiguration config, LivingEntity caster) {
        super(config, caster, null, 1, ConfigConstants.Sorcery.SORCERY_HEALING_SPELLS_HEAL_MANACOST, 
                ConfigConstants.Sorcery.SORCERY_HEALING_SPELLS_HEAL_RANGE, SpellTypes.INSTANT);
        
        //TODO: CONFIG
        _healPerLevel = 1;
    }
    
    @Override
    public void Cast() {
        Player caster = (Player)super._caster;
        int areaOfEffect = _range;
        List<Entity> nearbyPlayers = caster.getNearbyEntities(areaOfEffect, areaOfEffect, areaOfEffect);
        for (Entity bro : nearbyPlayers) {
            if (bro instanceof Player) {
                Player target = (Player) bro;
                if (super.burnMana()) {
                    int maxHealth = target.getMaxHealth();
                    int currentHealth = target.getHealth();
                    int maxHealAmount = GetHealAmount(caster);
                    int newHealth = Math.min(maxHealth, currentHealth + maxHealAmount);
                    int amountHealed = newHealth - currentHealth;
                    target.sendMessage(caster.getDisplayName() + " healed you for " + amountHealed);
                    caster.sendMessage("You healed " + target.getType().toString() + " for " + amountHealed);
                }
                else
                {                    
                    caster.sendMessage("Not enough mana to heal " + target.getDisplayName());
                }
            }
        }
    }
    
    private int GetHealAmount(Player caster) {
        return caster.getLevel() * _healPerLevel;
    }
}
