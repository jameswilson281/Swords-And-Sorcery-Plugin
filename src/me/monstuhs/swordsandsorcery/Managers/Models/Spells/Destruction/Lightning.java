/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers.Models.Spells.Destruction;

import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base.Spell;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.SpellMetaData;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public class Lightning extends Spell {

    public Lightning(SpellMetaData data) {
        super(data);
    }
 
    @Override
    public void Cast() {
        Player caster = this.SpellData.Caster;
        int range = this.SpellData.Range;
        float explosionSize = Math.max(caster.getLevel() / 2, 2);
        Location targetBlockLocation = caster.getTargetBlock(null, range).getLocation();
        World world = caster.getWorld();
        world.strikeLightning(targetBlockLocation);
        world.createExplosion(targetBlockLocation, explosionSize, true);
        world.strikeLightningEffect(targetBlockLocation);
    }

        
    }
