/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers.Skills;

import java.util.Collections;
import java.util.HashMap;
import me.monstuhs.swordsandsorcery.Models.DamagedBlock;
import me.monstuhs.swordsandsorcery.SaSUtilities;
import me.monstuhs.swordsandsorcery.SwordsAndSorcery;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDamageEvent;

/**
 *
 * @author James
 */
public class MiningManager {
    
    private static HashMap<Integer, DamagedBlock>  _blockDamageTracker;
    
    public static boolean checkForExtraSwingFromFasterMiningRate(BlockDamageEvent miningEvent){
        if(_blockDamageTracker == null){
            _blockDamageTracker = new HashMap<Integer,DamagedBlock>();
        }
        boolean extraSwing = false;
        Block thisBlock = miningEvent.getBlock();
        Player miner = miningEvent.getPlayer();
        int blockHash = thisBlock.hashCode();       
        int playerHash = miner.hashCode();
        
        if(_blockDamageTracker.get(playerHash).Id != blockHash){
            //New block
            _blockDamageTracker.put(blockHash, new DamagedBlock(blockHash, 0));
        }
        
        DamagedBlock damagedBlock = _blockDamageTracker.get(blockHash);
        damagedBlock.Damage += getAdditionalMiningDamage(miner);
        
        if(damagedBlock.Damage >= 100){            
            damagedBlock.Damage = 0;
            extraSwing = true;
        }
        return extraSwing;
    }
    
    public static void removeBlockFromDamageTracker(Block block){
        //We need to remove this block from ALL player lists not just one player
        _blockDamageTracker.values().removeAll(Collections.singleton(block.hashCode()));
    }
    
    public static int getAdditionalMiningDamage(Player miner){
        double modifier = SwordsAndSorcery.getConfigFile().getDouble(SaSUtilities.SkillConstants.SKILL_MINING_PLAYER_LEVEL_BLOCK_DAMAGE_MODIFIER);
        return (int) ((miner.getLevel() * modifier) * 100);
    }
}
