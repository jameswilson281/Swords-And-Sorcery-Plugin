/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Models.Spells;

/**
 *
 * @author James
 */
public abstract class Spell {

    public SpellName Name;
    public int ManaCost;
    public int Range;

    public enum SpellName {

        FIREBALL,
        LIGHTNING,
        KNOCKBACK,
        HEAL
    }
}
