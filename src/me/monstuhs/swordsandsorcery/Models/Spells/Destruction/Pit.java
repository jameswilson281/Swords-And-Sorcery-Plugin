/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Models.Spells.Destruction;

import me.monstuhs.swordsandsorcery.Models.Spells.Spell;
import me.monstuhs.swordsandsorcery.Models.Spells.SpellMetaData;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public class Pit extends Spell {
    
    public Pit(SpellMetaData data){
        super(data);
    }

    @Override
    public void Cast() {
        Player caster = this.SpellData.Caster;
        caster.sendMessage(this.SpellData.Name + " is not implemented yet");
//        Block targetBlock = caster.getTargetBlock(null, 200);
//        Entity target = SaSUtilities.GetTargetedEntity(caster, 200);
//        if (target != null && (target instanceof LivingEntity)) {
//            if (targetBlock.getType() != Material.AIR && !targetBlock.isLiquid()) {
//                List<Block> AirBlocks = SaSUtilities.GetSurroundingBlocks(targetBlock.getRelative(BlockFace.DOWN, 1), true);
//                AirBlocks.addAll(SaSUtilities.GetSurroundingBlocks(targetBlock.getRelative(BlockFace.DOWN, 2), true));
//
//                List<Block> LavaBlocks = SaSUtilities.GetSurroundingBlocks(targetBlock.getRelative(BlockFace.DOWN, 3), true);
//
//                for (Block airBlock : AirBlocks) {
//                    airBlock.setType(Material.AIR);
//                }
//                for (Block lavaBlock : LavaBlocks) {
//                    lavaBlock.setType(Material.LAVA);
//                }
//            }
//        }
    }
    
    
}
