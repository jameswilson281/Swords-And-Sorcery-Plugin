/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Models.Spells.Destruction;

import me.monstuhs.swordsandsorcery.Models.Spells.Spell;
import me.monstuhs.swordsandsorcery.Models.Spells.SpellMetaData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 *
 * @author James
 */
public class Fireball extends Spell{

    public Fireball(SpellMetaData data) {
        super(data);
    }

    @Override
    public void Cast() {
        Player caster = super.SpellData.Caster;
        int velocity = Math.max(caster.getLevel() / 2, 3);
        float yield = Math.max(caster.getLevel() / 2, 1);
        Vector launchDirection = caster.getLocation().getDirection().multiply(velocity);
        Location spawnLocation = caster.getLocation().add(0, 0.5F, 0);
        org.bukkit.entity.Fireball fireball = caster.getWorld().spawn(spawnLocation, org.bukkit.entity.Fireball.class);
        fireball.setVelocity(launchDirection);
        fireball.setYield(yield);
        fireball.setShooter(caster);
    }
}
