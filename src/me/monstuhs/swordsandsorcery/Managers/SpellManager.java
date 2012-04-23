/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers;

import java.util.EnumMap;
import java.util.HashMap;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Destruction.Fireball;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Destruction.Knockback;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Destruction.Lightning;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Destruction.Pit;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Healing.Endurance;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Healing.Heal;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.SpellMetaData;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.SpellMetaData.SpellName;
import me.monstuhs.swordsandsorcery.Utilities.ConfigConstants;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author James
 */
public class SpellManager {

    private static EnumMap<SpellName, SpellMetaData> Spells = new EnumMap<SpellName, SpellMetaData>(SpellName.class);
    private static EnumMap<Material, SpellName> SpellWands = new EnumMap<Material, SpellName>(Material.class);
    private static HashMap<Player, SpellName> ActiveSpellList = new HashMap<Player, SpellName>();
    private static Boolean burnMana;

    public static void InitializeSpellManager(JavaPlugin thisPlugin) {

        FileConfiguration config = thisPlugin.getConfig();

        //Add spells
        int fireballCost = config.getInt(ConfigConstants.Sorcery.SORCERY_DESTRUCTION_SPELLS_FIREBALL_MANACOST);
        Spells.put(SpellName.FIREBALL, new SpellMetaData(fireballCost, 0, SpellName.FIREBALL));


        int lightningCost = config.getInt(ConfigConstants.Sorcery.SORCERY_DESCTURCTION_SPELLS_LIGHTING_MANACOST);
        int lightningRange = config.getInt(ConfigConstants.Sorcery.SORCERY_DESCTURCTION_SPELLS_LIGHTING_RANGE);
        Spells.put(SpellName.LIGHTNING, new SpellMetaData(lightningCost, lightningRange, SpellName.LIGHTNING));


        int knockbackCost = config.getInt(ConfigConstants.Sorcery.SORCERY_DESTRUCTION_SPELLS_KNOCKBACK_MANACOST);
        Spells.put(SpellName.KNOCKBACK, new SpellMetaData(knockbackCost, 0, SpellName.KNOCKBACK));

        int healCost = config.getInt(ConfigConstants.Sorcery.SORCERY_HEALING_SPELLS_HEAL_MANACOST);
        int healRange = config.getInt(ConfigConstants.Sorcery.SORCERY_HEALING_SPELLS_HEAL_RANGE);
        Spells.put(SpellName.HEAL, new SpellMetaData(healCost, healRange, SpellName.HEAL));

        int enduranceCost = config.getInt(ConfigConstants.Sorcery.SORCERY_HEALING_SPELLS_ENDURANCE_MANACOST);
        Spells.put(SpellName.ENDURANCE, new SpellMetaData(enduranceCost, 0, SpellName.ENDURANCE));

        //Add wands
        Material destructionWand = Material.getMaterial(config.getString(ConfigConstants.Sorcery.SORCERY_DESTRUCTION_WAND));
        SpellWands.put(destructionWand, SpellName.FIREBALL);

        Material healingWand = Material.getMaterial(config.getString(ConfigConstants.Sorcery.SORCERY_HEALING_WAND));
        SpellWands.put(healingWand, SpellName.HEAL);

        //Add config settings
        burnMana = config.getBoolean(ConfigConstants.Sorcery.SORCERY_ALLOW_MANA_BURN);
    }

    public static void HandleSpellCasting(Player caster) {
        if (ActiveSpellList.containsKey(caster) == false) {
            ActiveSpellList.put(caster, SpellName.FIREBALL);
        }
        SpellName spellToCast = ActiveSpellList.get(caster);
        HandleSpellCasting(caster, spellToCast);
    }

    public static void HandleSpellCasting(Player caster, SpellName spellBeingCast) {

        SpellMetaData thisSpell = Spells.get(spellBeingCast);
        if (thisSpell != null) {
            thisSpell.Caster = caster;
            Boolean canCast = SpellManager.BurnMana(caster, thisSpell, burnMana);

            if (canCast) {
                switch (spellBeingCast) {
                    case FIREBALL:
                        new Fireball(thisSpell).Cast();
                        break;
                    case LIGHTNING:
                        new Lightning(thisSpell).Cast();
                        break;
                    case KNOCKBACK:
                        new Knockback(thisSpell).Cast();
                        break;
                    case PIT:
                        new Pit(thisSpell).Cast();
                        break;
                    case HEAL:
                        new Heal(thisSpell).Cast();
                        break;
                    case ENDURANCE:
                        //new Endurance(thisSpell).Cast();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static void CycleSpellsForPlayer(Player caster) {

        if (ActiveSpellList.containsKey(caster) == false) {
            ActiveSpellList.put(caster, SpellName.FIREBALL);
        }

        SpellName activeSpell = ActiveSpellList.get(caster);

        switch (activeSpell) {
            case FIREBALL:
                activeSpell = SpellName.LIGHTNING;
                break;
            case LIGHTNING:
                activeSpell = SpellName.KNOCKBACK;
                break;
            case KNOCKBACK:
                activeSpell = SpellName.PIT;
                break;
            case PIT:
                activeSpell = SpellName.HEAL;
                break;
            case HEAL:
                activeSpell = SpellName.ENDURANCE;
                break;
            case ENDURANCE:
                activeSpell = SpellName.FIREBALL;
                break;            
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
    
    public static Boolean BurnMana(Player caster, SpellMetaData spellCast, Boolean manaBurn) {

        int remainingManaNeeded = spellCast.ManaCost;
        Boolean canCast = true;
        PlayerInventory inventory = caster.getInventory();

        
        HashMap<Integer, ItemStack> couldnotRemove =
                inventory.removeItem(new ItemStack(Material.REDSTONE, remainingManaNeeded));
        
        int manaBurnAmount = couldnotRemove.isEmpty() ? 0 : couldnotRemove.get(0).getAmount();
        if(manaBurnAmount > 0){
            if(manaBurn){
                caster.damage(manaBurnAmount);                
                caster.sendMessage("You burned " + manaBurnAmount + " mana.");
            }
            else{
                canCast = false;
                caster.sendMessage("You need " + manaBurnAmount + " more mana to cast that");
            }
        }
        caster.updateInventory();        
        return canCast;

    }
}
