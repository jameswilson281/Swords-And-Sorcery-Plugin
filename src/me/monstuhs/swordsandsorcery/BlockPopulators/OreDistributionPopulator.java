/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.BlockPopulators;

import java.util.Random;
import me.monstuhs.swordsandsorcery.Managers.ConfigurationManager;
import me.monstuhs.swordsandsorcery.Utilities.ConfigConstants;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

/**
 *
 * @author James
 */
public class OreDistributionPopulator extends BlockPopulator {

    private final ConfigurationManager _configManager;

    public OreDistributionPopulator(ConfigurationManager configManager) {
        _configManager = configManager;
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {

        for (int chunkY = 10; chunkY < 64; chunkY++) {
            for (int chunkX = 0; chunkX < 16; chunkX++) {
                for (int chunkZ = 0; chunkZ < 16; chunkZ++) {
                    Block thisBlock = chunk.getBlock(chunkX, chunkY, chunkZ);

                    if (thisBlock.getType() == Material.IRON_ORE) {
                        if (random.nextInt(100) < _configManager.getConfigFile().getInt(ConfigConstants.GlobalSettings.SETTINGS_WORLD_ORE_IRON_FREQUENCY_PERCENTAGE)) {
                            Material newType = thisBlock.getRelative(BlockFace.DOWN).getType();
                            thisBlock.setType(newType);
                        }
                    }
                }
            }
        }

    }
}
