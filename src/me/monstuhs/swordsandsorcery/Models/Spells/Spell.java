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

    public SpellMetaData SpellData;
    
    public Spell(SpellMetaData data){
        SpellData = data;
    }
    public abstract void Cast();    

    public enum SpellName {

        ENDURANCE,
        FIREBALL,
        LIGHTNING,
        KNOCKBACK,
        HEAL,
        PIT,
    }
}
