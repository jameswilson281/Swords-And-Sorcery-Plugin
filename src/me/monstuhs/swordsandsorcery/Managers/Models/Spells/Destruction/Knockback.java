/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers.Models.Spells.Destruction;

import java.util.List;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base.Spell;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base.SpellTypes;
import me.monstuhs.swordsandsorcery.Utilities.ConfigConstants;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 *
 * @author James
 */
public class Knockback extends Spell {

    public Knockback(FileConfiguration config, LivingEntity caster) {
        super(config, caster, null, 1, ConfigConstants.Sorcery.SORCERY_DESTRUCTION_SPELLS_KNOCKBACK_MANACOST, 
                ConfigConstants.Sorcery.SORCERY_DESTRUCTION_SPELLS_KNOCKBACK_RANGE_PER_LEVEL, SpellTypes.INSTANT);
    }

    @Override
    public void Cast() {
        if (super.burnMana()) {
            Player caster = (Player) _caster;

            int areaOfEffect = _range * caster.getLevel();
            Vector p = caster.getLocation().toVector();
            List<Entity> entities = caster.getNearbyEntities(areaOfEffect, areaOfEffect, areaOfEffect);

            Vector entityVector, newVector;

            for (Entity entity : entities) {
                if (entity instanceof LivingEntity) {
                    entityVector = entity.getLocation().toVector();
                    newVector = entityVector.subtract(p).normalize().multiply(10 / 10.0 * 5);
                    newVector.setY(newVector.getY() * (5 / 10.0 * 5));

                    if (newVector.getY() > (5 / 10.0)) {
                        newVector.setY(5 / 10.0);
                    }
                    entity.setVelocity(newVector);
                }
            }
        }
    }
}
