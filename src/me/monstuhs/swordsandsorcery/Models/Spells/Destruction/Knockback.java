/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Models.Spells.Destruction;

import java.util.List;
import me.monstuhs.swordsandsorcery.Models.Spells.Spell;
import me.monstuhs.swordsandsorcery.Models.Spells.SpellMetaData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 *
 * @author James
 */
public class Knockback extends Spell {

    public Knockback(SpellMetaData data) {
        super(data);
    }

    @Override
    public void Cast() {
        Player caster = this.SpellData.Caster;
        int areaOfEffect = caster.getLevel() * 3;
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
