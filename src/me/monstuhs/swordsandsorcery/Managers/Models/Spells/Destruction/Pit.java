/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers.Models.Spells.Destruction;

import java.util.List;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base.Spell;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base.SpellTypes;
import me.monstuhs.swordsandsorcery.Utilities.BukkitHelpers;
import me.monstuhs.swordsandsorcery.Utilities.ConfigConstants;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public class Pit extends Spell {

    public Pit(FileConfiguration config, LivingEntity caster) {
        super(config, caster, null, 1, ConfigConstants.Sorcery.SORCERY_DESCTURCTION_SPELLS_PIT_MANACOST, null, SpellTypes.INSTANT);
    }

    @Override
    public void Cast() {
        if (super.burnMana()) {
            Player caster = (Player) super._caster;
            Block targetBlock = caster.getTargetBlock(null, 100);
            if (targetBlock.getType() != Material.AIR && !targetBlock.isLiquid()) {
                List<Block> AirBlocks = BukkitHelpers.GetSurroundingBlocks(targetBlock.getRelative(BlockFace.DOWN, 1), true);
                AirBlocks.addAll(BukkitHelpers.GetSurroundingBlocks(targetBlock.getRelative(BlockFace.DOWN, 2), true));
                
                for (Block airBlock : AirBlocks) {
                    airBlock.setType(Material.AIR);
                }
            }
        }
    }
}
