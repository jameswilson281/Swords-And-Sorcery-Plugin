/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers.Spells;

import java.util.HashMap;
import me.monstuhs.swordsandsorcery.Managers.PlayerManager;
import me.monstuhs.swordsandsorcery.Models.Spells.Base.Spell;
import me.monstuhs.swordsandsorcery.Models.Spells.Base.Spell.SpellName;
import me.monstuhs.swordsandsorcery.Models.Spells.Fireball;
import me.monstuhs.swordsandsorcery.Models.Spells.Heal;
import me.monstuhs.swordsandsorcery.Models.Spells.Knockback;
import me.monstuhs.swordsandsorcery.Models.Spells.Lightning;
import me.monstuhs.swordsandsorcery.SaSUtilities;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public class SpellManager {

    private static HashMap<Player, SpellName> ActiveSpellList = new HashMap<Player, SpellName>();

    public static void HandleSpellCasting(Player caster) {
        SpellName spellToCast = ActiveSpellList.get(caster);
        HandleSpellCasting(caster, spellToCast);
    }

    public static void HandleSpellCasting(Player caster, SpellName spellBeingCast) {

        Spell spell = GetSpellByName(spellBeingCast);
        
        if (ExpendMana(caster, spell)) {
            switch (spellBeingCast) {
                case FIREBALL:
                    DestructionSpellsManager.ShootFireball(caster);
                    break;
                case LIGHTNING:
                    DestructionSpellsManager.ShootLightningBalt(caster, spell.Range);
                    break;
                case KNOCKBACK:
                    DestructionSpellsManager.Knockback(caster);
                    break;
                case HEAL:
                    Entity target = SaSUtilities.GetTargetedEntity(caster, spell.Range);
                    if (target != null) {
                        if (target instanceof LivingEntity) {
                            HealingSpellsManager.HealTarget(caster, (LivingEntity) target);
                        }
                    }
                default:
                    break;
            }
        } else {
            caster.sendMessage("You do not have enough mana to cast " + spellBeingCast.toString());
        }
    }

    public static void CycleSpellsForPlayer(Player caster) {

        if (ActiveSpellList.containsKey(caster) == false) {
            ActiveSpellList.put(caster, SpellName.FIREBALL);
        }

        SpellName activeSpell = ActiveSpellList.get(caster);

        switch (activeSpell) {
            case FIREBALL:
                activeSpell = SpellName.FIREBALL;
                break;
            case LIGHTNING:
                activeSpell = SpellName.KNOCKBACK;
                break;
            case KNOCKBACK:
                activeSpell = SpellName.HEAL;
                break;
            case HEAL:
                activeSpell = SpellName.FIREBALL;
            default:
                break;
        }

        ActiveSpellList.put(caster, activeSpell);
        caster.sendMessage("You are now casting " + activeSpell.toString());
    }

    private static Boolean ExpendMana(Player caster, Spell spellCast) {
        Boolean canCast = false;
        if (PlayerManager.PlayerHasMana(caster)) {
            canCast = true;
            PlayerManager.BurnMana(caster, spellCast.ManaCost, Boolean.TRUE);
        }
        return canCast;
    }

    private static Spell GetSpellByName(SpellName name) {
        Spell thisSpell;
        switch (name) {
            case FIREBALL:
                thisSpell = new Fireball();
                break;
            case LIGHTNING:
                thisSpell = new Lightning();
                break;
            case KNOCKBACK:
                thisSpell = new Knockback();
                break;
            case HEAL:
                thisSpell = new Heal();
                break;
            default:
                thisSpell = new Fireball();
                break;
        }
        return thisSpell;
    }
}



    

    






