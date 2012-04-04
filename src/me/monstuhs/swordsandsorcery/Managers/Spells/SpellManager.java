/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers.Spells;

import java.util.EnumMap;
import java.util.HashMap;
import me.monstuhs.swordsandsorcery.Managers.PlayerManager;
import me.monstuhs.swordsandsorcery.Models.Spells.Base.Spell;
import me.monstuhs.swordsandsorcery.Models.Spells.Base.Spell.SpellName;
import me.monstuhs.swordsandsorcery.Models.Spells.Fireball;
import me.monstuhs.swordsandsorcery.Models.Spells.Heal;
import me.monstuhs.swordsandsorcery.Models.Spells.Knockback;
import me.monstuhs.swordsandsorcery.Models.Spells.Lightning;
import me.monstuhs.swordsandsorcery.SaSUtilities;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author James
 */
public class SpellManager {

    private static EnumMap<SpellName, Spell> Spells = new EnumMap<SpellName, Spell>(SpellName.class);
    private static EnumMap<Material, SpellName> SpellWands = new EnumMap<Material, SpellName>(Material.class);
    private static HashMap<Player, SpellName> ActiveSpellList = new HashMap<Player, SpellName>();

    public static void InitializeSpellManager(JavaPlugin thisPlugin) {

        FileConfiguration config = thisPlugin.getConfig();

        //Add spells
        int fireballCost = config.getInt(SaSUtilities.SORCERY_DESTRUCTION_SPELLS_FIREBALL_MANACOST);
        Spell fireball = new Fireball(fireballCost);
        Spells.put(SpellName.FIREBALL, fireball);

        int lightningCost = config.getInt(SaSUtilities.SORCERY_DESCTURCTION_SPELLS_LIGHTING_MANACOST);
        int lightningRange = config.getInt(SaSUtilities.SORCERY_DESCTURCTION_SPELLS_LIGHTING_RANGE);
        Spell lightning = new Lightning(lightningCost, lightningRange);
        Spells.put(SpellName.LIGHTNING, lightning);

        int knockbackCost = config.getInt(SaSUtilities.SORCERY_DESTRUCTION_SPELLS_KNOCKBACK_MANACOST);
        Spell knockback = new Knockback(knockbackCost);
        Spells.put(SpellName.KNOCKBACK, knockback);

        int healCost = config.getInt(SaSUtilities.SORCERY_HEALING_SPELLS_HEAL_MANACOST);
        int healRange = config.getInt(SaSUtilities.SORCERY_HEALING_SPELLS_HEAL_RANGE);
        Spell heal = new Heal(healCost, healRange);
        Spells.put(SpellName.HEAL, heal);


        //Add wands
        Material destructionWand = Material.getMaterial(config.getString(SaSUtilities.SORCERY_DESTRUCTION_WAND));
        SpellWands.put(destructionWand, SpellName.FIREBALL);

        Material healingWand = Material.getMaterial(config.getString(SaSUtilities.SORCERY_HEALING_WAND));
        SpellWands.put(healingWand, SpellName.HEAL);
    }

    public static void HandleSpellCasting(Player caster) {
        SpellName spellToCast = ActiveSpellList.get(caster);
        HandleSpellCasting(caster, spellToCast);
    }

    public static void HandleSpellCasting(Player caster, SpellName spellBeingCast) {

        Spell spell = Spells.get(spellBeingCast);
        ExpendMana(caster, spell);

        switch (spellBeingCast) {
            case ENDURANCE:
                HealingSpellsManager.CastEndurance(caster);
                break;
            case FIREBALL:
                DestructionSpellsManager.ShootFireball(caster);
                break;
            case LIGHTNING:
                DestructionSpellsManager.ShootLightningBalt(caster, Spells.get(SpellName.LIGHTNING).Range);
                break;
            case KNOCKBACK:
                DestructionSpellsManager.Knockback(caster);
                break;
            case HEAL:
                Entity target = SaSUtilities.GetTargetedEntity(caster, Spells.get(SpellName.HEAL).Range);
                if (target != null) {
                    if (target instanceof LivingEntity) {
                        HealingSpellsManager.HealTarget(caster, (LivingEntity) target);
                    }
                }
            default:
                break;
        }
    }

    public static void CycleSpellsForPlayer(Player caster) {

        if (ActiveSpellList.containsKey(caster) == false) {
            ActiveSpellList.put(caster, SpellName.FIREBALL);
        }

        SpellName activeSpell = ActiveSpellList.get(caster);

        switch (activeSpell) {
            case ENDURANCE:
                activeSpell = SpellName.FIREBALL;
                break;
            case FIREBALL:
                activeSpell = SpellName.LIGHTNING;
                break;
            case LIGHTNING:
                activeSpell = SpellName.KNOCKBACK;
                break;
            case KNOCKBACK:
                activeSpell = SpellName.HEAL;
                break;
            case HEAL:
                activeSpell = SpellName.ENDURANCE;
            default:
                break;
        }

        ActiveSpellList.put(caster, activeSpell);
        caster.sendMessage("You are now casting " + activeSpell.toString());
    }

    public static SpellName GetSpellByWand(Material wand) {
        //TODO: Change this to return the spell school
        return SpellWands.get(wand);
    }

    private static void ExpendMana(Player caster, Spell spellCast) {
        PlayerManager.BurnMana(caster, spellCast.ManaCost, Boolean.TRUE);
    }
}
