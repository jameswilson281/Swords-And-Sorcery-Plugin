/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Models.Spells;

import me.monstuhs.swordsandsorcery.Models.Spells.Base.Spell;

/**
 *
 * @author James
 */
    public class Heal extends Spell {

        public int Range;
        public Heal(int manaCost, int range) {
            super(SpellName.HEAL, manaCost);
            Range = range;
        }
    }
