/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base.Spell;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base.SpellNames;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base.SpellTypes;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Destruction.Fireball;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Destruction.Knockback;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Destruction.Lightning;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Destruction.Pit;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Healing.Endurance;
import me.monstuhs.swordsandsorcery.Managers.Models.Spells.Healing.HealArea;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public class SpellManager {
    
    private HashMap<String, SpellNames> ActiveSpellList;
    private static List<Spell> SpellQueue = new ArrayList<Spell>();
    private FileConfiguration _configFile;

    public static List<Spell> getSpellQueue() {
        return SpellQueue;
    }

    public SpellManager(FileConfiguration configFile) {        
        ActiveSpellList = new HashMap<String, SpellNames>();
        _configFile = configFile;
    }

    public void useWand(Player caster) {
        if(ActiveSpellList.containsKey(caster.getName()) == false){
            cycleSpellsForPlayer(caster.getName());
        }
        
        SpellNames activeSpell = ActiveSpellList.get(caster.getName());
        Spell spellToCast = null;
        switch (activeSpell) {
            case FIREBALL:
                spellToCast = new Fireball(_configFile, caster);
                break;
            case LIGHTNING:
                spellToCast = new Lightning(_configFile, caster);
                break;
            case KNOCKBACK:
                spellToCast = new Knockback(_configFile, caster);
                break;
            case PIT:
                spellToCast = new Pit(_configFile, caster);
                break;
            case ENDURANCE:
                spellToCast = new Endurance(_configFile, caster);
                break;
            case HEAL_AREA:
                spellToCast = new HealArea(_configFile, caster);
                break;
            default:
                break;
        }
        if (spellToCast != null) {
            castSpell(spellToCast);
        }
    }

    public void cycleSpellsForPlayer(String casterName) {

        if (ActiveSpellList.containsKey(casterName) == false) {
            ActiveSpellList.put(casterName, SpellNames.FIREBALL);
        }

        SpellNames activeSpell = ActiveSpellList.get(casterName);

        switch (activeSpell) {
            case FIREBALL:
                activeSpell = SpellNames.LIGHTNING;
                break;
            case LIGHTNING:
                activeSpell = SpellNames.KNOCKBACK;
                break;
            case KNOCKBACK:
                activeSpell = SpellNames.PIT;
                break;
            case PIT:
                activeSpell = SpellNames.HEAL_AREA;
                break;
            case HEAL_AREA:
                activeSpell = SpellNames.ENDURANCE;
                break;
            case ENDURANCE:
                activeSpell = SpellNames.FIREBALL;
                break;
            default:
                activeSpell = SpellNames.FIREBALL;
                break;
        }

        ActiveSpellList.put(casterName, activeSpell);
        Bukkit.getServer().getPlayer(casterName).sendMessage("You are now casting " + ChatColor.RED + activeSpell.toString());
    }
    
    private void castSpell(Spell spellToCast) {
        if (spellToCast.getType() == SpellTypes.RECURRING) {
            SpellQueue.add(spellToCast);
        } else {
            spellToCast.Cast();
        }
    }
}
