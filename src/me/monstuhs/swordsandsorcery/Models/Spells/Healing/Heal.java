/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Models.Spells.Healing;

import java.util.List;
import me.monstuhs.swordsandsorcery.Managers.PlayerManager;
import me.monstuhs.swordsandsorcery.Models.Spells.Spell;
import me.monstuhs.swordsandsorcery.Models.Spells.SpellMetaData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public class Heal extends Spell {
    
    public Heal(SpellMetaData data){
        super(data);        
    }
    
    @Override
    public void Cast() {
        Player caster = this.SpellData.Caster;
        int areaOfEffect = caster.getLevel() * 3;
        List<Entity> nearbyPlayers = caster.getNearbyEntities(areaOfEffect, areaOfEffect, areaOfEffect);
        for (Entity bro : nearbyPlayers) {
            if (bro instanceof Player) {
                Player target = (Player) bro;
                if (PlayerManager.BurnMana(caster, this.SpellData, Boolean.FALSE)) {                    
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
    
    private static int GetHealAmount(Player caster) {
        return caster.getLevel() * 1;
    }
}
