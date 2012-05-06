/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers.Models.Spells.Destruction;

import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base.Spell;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base.SpellTypes;
import me.monstuhs.swordsandsorcery.Utilities.ConfigConstants;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public class Lightning extends Spell {

    public Lightning(FileConfiguration config, LivingEntity caster) {
        super(config, caster, null, 1, ConfigConstants.Sorcery.SORCERY_DESCTURCTION_SPELLS_LIGHTING_MANACOST, 
                ConfigConstants.Sorcery.SORCERY_DESCTURCTION_SPELLS_LIGHTING_RANGE, SpellTypes.INSTANT);
    }

    @Override
    public void Cast() {
        if (super.burnMana()) {
            Player caster = (Player) super._caster;
            int range = _range;
            float explosionSize = Math.max(Math.min(caster.getLevel() / 4, 10), 2);
            Location targetBlockLocation = caster.getTargetBlock(null, range).getLocation();
            World world = caster.getWorld();
            world.strikeLightning(targetBlockLocation);
            world.createExplosion(targetBlockLocation, explosionSize, true);
            world.strikeLightningEffect(targetBlockLocation);
        }
    }
}
