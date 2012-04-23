/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base;

import me.monstuhs.swordsandsorcery.Managers.Models.Spells.SpellMetaData;

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
}
