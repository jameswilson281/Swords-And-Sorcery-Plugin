/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Models.Spells.Healing;

import me.monstuhs.swordsandsorcery.Models.Spells.Spell;

/**
 *
 * @author James
 */
public class Endurance extends Spell{
    public Endurance(int manaCost) {
        super(SpellName.ENDURANCE, manaCost);
    }
}
