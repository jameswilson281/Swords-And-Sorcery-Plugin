/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Models.Spells.Healing;

import me.monstuhs.swordsandsorcery.Models.Spells.Spell;
import me.monstuhs.swordsandsorcery.Models.Spells.SpellMetaData;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public class Endurance extends Spell {

    public Endurance(SpellMetaData data) {
        super(data);
    }

    @Override
    public void Cast() {
        Player caster = this.SpellData.Caster;
        caster.sendMessage(this.SpellData.Name + " is not implemented yet");
    }
}
