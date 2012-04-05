/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Models.Spells;

import me.monstuhs.swordsandsorcery.Models.Spells.Spell.SpellName;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public final class SpellMetaData {
    public int ManaCost;
    public int Range;
    public SpellName Name;
    public Player Caster;
    
    public SpellMetaData(int manaCost, int range, SpellName spellName){
        ManaCost = manaCost;
        Range = range;
        Name = spellName;        
    }
}
