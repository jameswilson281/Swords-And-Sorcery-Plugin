/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Managers.Models.Spells.Base;

import java.util.HashMap;
import me.monstuhs.swordsandsorcery.Utilities.ConfigConstants;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 *
 * @author James
 */
public abstract class Spell {

    protected LivingEntity _caster;
    protected LivingEntity _target;
    protected int _ticks;
    protected SpellNames _name;
    protected int _manaCost;
    private SpellTypes _type;
    protected int _range;
    private boolean _canManaBeBurned;
    private final FileConfiguration _fileConfig;

    public Spell(FileConfiguration fileConfig, LivingEntity caster, LivingEntity target, int timesToCast, String manaCostConfigString, String rangeConfigString, SpellTypes type) {
        _fileConfig = fileConfig;
        _caster = caster;
        _target = target;
        _ticks = timesToCast;
        _manaCost = manaCostConfigString == null ? 0 : _fileConfig.getInt(manaCostConfigString);
        _type = type;
        _range = rangeConfigString == null ? 0 : _fileConfig.getInt(rangeConfigString);

        _canManaBeBurned = _fileConfig.getBoolean(ConfigConstants.Sorcery.SORCERY_ALLOW_MANA_BURN);

    }

    public abstract void Cast();

    protected boolean burnMana() {
        Boolean canCast = true;
        if (_caster instanceof Player) {
            
            Player caster = (Player) _caster;
            PlayerInventory inventory = caster.getInventory();

            HashMap<Integer, ItemStack> couldnotRemove =
                    inventory.removeItem(new ItemStack(Material.REDSTONE, _manaCost));

            int manaBurnAmount = couldnotRemove.isEmpty() ? 0 : couldnotRemove.get(0).getAmount();
            if (manaBurnAmount > 0) {
                if (_canManaBeBurned) {
                    caster.damage(manaBurnAmount);
                    caster.sendMessage("You burned " + manaBurnAmount + " mana.");
                } else {
                    canCast = false;
                    caster.sendMessage("You need " + manaBurnAmount + " more mana to cast that");
                }
            }
            caster.updateInventory();
        }
        return canCast;
    }

    public SpellTypes getType() {
        return _type;
    }
}
