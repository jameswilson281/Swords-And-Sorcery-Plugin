/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers.Models.Spells.Destruction;

import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base.Spell;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base.SpellTypes;
import me.monstuhs.swordsandsorcery.Utilities.ConfigConstants;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 *
 * @author James
 */
public class Fireball extends Spell {

    public Fireball(FileConfiguration config, LivingEntity caster) {
        super(config, caster, null, 1, ConfigConstants.Sorcery.SORCERY_DESTRUCTION_SPELLS_FIREBALL_MANACOST, null, SpellTypes.INSTANT);
    }

    @Override
    public void Cast() {
        Player caster = (Player) super._caster;
        if (super.burnMana()) {
            int velocity = Math.max(Math.min(caster.getLevel() / 2, 15), 3);
            float yield = Math.max(Math.min(caster.getLevel() / 3, 10), 1);

            Vector launchDirection = caster.getLocation().getDirection().multiply(velocity);
            Location spawnLocation = caster.getLocation().add(0, 1.0F, 0);
            Vector castInFront = spawnLocation.getDirection().multiply(2);
            spawnLocation.add(castInFront);

            org.bukkit.entity.Fireball fireball = caster.getWorld().spawn(spawnLocation, org.bukkit.entity.Fireball.class);
            fireball.setVelocity(launchDirection);
            fireball.setYield(yield);
            fireball.setShooter(caster);
        }
    }
}
