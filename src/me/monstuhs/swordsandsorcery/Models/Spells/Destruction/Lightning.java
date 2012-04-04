/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Models.Spells.Destruction;

import me.monstuhs.swordsandsorcery.Models.Spells.Spell;

/**
 *
 * @author James
 */
public class Lightning extends Spell {

        public int Range;
        
        public Lightning(int manaCost, int range) {
            super(SpellName.LIGHTNING, manaCost);                        
            Range = range;
        }
    }
