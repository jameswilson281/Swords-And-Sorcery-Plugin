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
public class Fireball extends Spell {

    public Fireball(int manaCost) {
        super(SpellName.FIREBALL, manaCost);
    }
}
