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
public class Fireball extends Spell {

    public Fireball() {
        Name = SpellName.FIREBALL;
        ManaCost = 1;
    }
}
