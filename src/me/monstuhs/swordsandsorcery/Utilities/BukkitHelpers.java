/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Utilities;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

/**
 *
 * @author James
 */
public class BukkitHelpers {
    private static final long TASK_TICKS_PER_SECOND = 20L;    
    
    public static long getDelay(long intervalInSeconds){
        return TASK_TICKS_PER_SECOND * intervalInSeconds;
    }
    
    public static long getSecondsFromTics(long ticks){
        return ticks/TASK_TICKS_PER_SECOND;
    }
    
    public static String formatDouble(double number){
        DecimalFormat threeDec = new DecimalFormat("0.000");
        threeDec.setGroupingUsed(false);
        return threeDec.format(number);
    }
    
    public static List<Block> GetSurroundingBlocks(Block target, boolean includeTarget) {
        List<Block> neighbors = new ArrayList<Block>();

        if (includeTarget && target.getType() != Material.BEDROCK) {
            neighbors.add(target);
        }

        Block nBlock = target.getRelative(BlockFace.NORTH);
        if (nBlock.getType() != Material.BEDROCK) {
            neighbors.add(nBlock);
        }

        Block sBlock = target.getRelative(BlockFace.SOUTH);
        if (sBlock.getType() != Material.BEDROCK) {
            neighbors.add(sBlock);
        }

        Block eBlock = target.getRelative(BlockFace.EAST);
        if (eBlock.getType() != Material.BEDROCK) {
            neighbors.add(eBlock);
        }

        Block wBlock = target.getRelative(BlockFace.WEST);
        if (wBlock.getType() != Material.BEDROCK) {
            neighbors.add(wBlock);
        }

        Block neBlock = target.getRelative(BlockFace.NORTH_EAST);
        if (neBlock.getType() != Material.BEDROCK) {
            neighbors.add(neBlock);
        }

        Block nwBlock = target.getRelative(BlockFace.NORTH_WEST);
        if (nwBlock.getType() != Material.BEDROCK) {
            neighbors.add(nwBlock);
        }

        Block seBlock = target.getRelative(BlockFace.SOUTH_EAST);
        if (seBlock.getType() != Material.BEDROCK) {
            neighbors.add(seBlock);
        }

        Block swBlock = target.getRelative(BlockFace.SOUTH_WEST);
        if (swBlock.getType() != Material.BEDROCK) {
            neighbors.add(swBlock);
        }

        return neighbors;
    }

    public static Entity GetTargetedEntity(Player targeter, int range) {
        BlockIterator lineOfSight = new BlockIterator(targeter, range);
        List<Entity> nearbyEntities = targeter.getNearbyEntities(range, range, range);
        Entity target = null;

        Block block;
        Location loc;
        int bx, by, bz;
        double ex, ey, ez;

        while (lineOfSight.hasNext()) {
            block = lineOfSight.next();
            bx = block.getX();
            by = block.getY();
            bz = block.getZ();
            // check for entities near this block in the line of sight
            for (Entity e : nearbyEntities) {
                loc = e.getLocation();
                ex = loc.getX();
                ey = loc.getY();
                ez = loc.getZ();
                if ((bx - 1.00 <= ex && ex <= bx + 2.00)
                        && (bz - 1.00 <= ez && ez <= bz + 2.00)
                        && (by - 1 <= ey && ey <= by + 2.5)) {
                    // entity is close enough, set target and stop
                    target = e;
                    break;
                }
            }
        }

//        if (target == null) {
//            System.out.println(" no target");
//        } else {
//            System.out.println(targeter.getDisplayName() + " targeted " + target.getType().toString());
//        }



        return target;
    }
}
