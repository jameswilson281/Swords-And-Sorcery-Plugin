/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.EventHandlers;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;

/**
 *
 * @author James
 */
public class OrePlacementListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onChunkPopulate(ChunkPopulateEvent event) {
        Bukkit.getServer().broadcastMessage("hello world");
        Chunk thisChunk = event.getChunk();

        int highestY = thisChunk.getWorld().getHighestBlockAt(8, 8).getY();
        if (highestY < 64) {
            for (int chunkY = 1; chunkY < highestY; chunkY++) {
                for (int chunkX = 0; chunkX < 16; chunkX++) {
                    for (int chunkZ = 0; chunkZ < 16; chunkZ++) {
                        Block thisBlock = thisChunk.getBlock(chunkX, chunkY, chunkZ);
                        if (thisBlock.getType() == Material.IRON_ORE) {

                            Material newType = thisBlock.getRelative(BlockFace.DOWN).getType();
                            //thisBlock.setType(newType);
                            thisBlock.setType(Material.BOOKSHELF);
                            Bukkit.getServer().broadcastMessage("Changed IRON_ORE to " + newType);
                        }
                    }
                }
            }
        }
    }
}
