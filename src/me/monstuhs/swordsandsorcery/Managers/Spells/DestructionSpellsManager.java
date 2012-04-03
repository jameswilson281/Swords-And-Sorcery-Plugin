/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers.Spells;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 *
 * @author James
 */
public class DestructionSpellsManager {
    
    public static void ShootFireball(Player caster) {
        int velocity = caster.getLevel();
        float yield = Math.max(caster.getLevel() / 2, 1);
        Vector launchDirection = caster.getLocation().getDirection().multiply(velocity);
        Fireball fireball = caster.getWorld().spawn(caster.getLocation(), Fireball.class);
        fireball.setVelocity(launchDirection);
        fireball.setYield(yield);
        fireball.setShooter(caster);
    }

    public static void ShootLightningBalt(Player caster, int range) {
        Location targetBlockLocation = caster.getTargetBlock(null, range).getLocation();
        caster.getWorld().strikeLightning(targetBlockLocation);
        caster.getWorld().strikeLightningEffect(targetBlockLocation);
    }

    public static void Knockback(Player caster) {
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

//    public static void EarthPit(Player caster) {
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
//    }
}
