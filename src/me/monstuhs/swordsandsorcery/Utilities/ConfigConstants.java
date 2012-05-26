/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Utilities;

/**
 *
 * @author James
 */
public class ConfigConstants {

    public class MiningActivities {

        public static final String ACTIVITY_MINING_PpL_INSTABREAK = "activity.mining.PercentageChancePerLevelToInstaBreak";
        public static final String ACTIVITY_MINING_PpL_DOUBLE_DROP = "activity.mining.PercentageChancePerLevelToDoubleDrop";
    }

    public class CombatActivities {

        public static final String ACTIVITY_COMBAT_PpL_DODGE = "activity.combat.PercentageChancePerLevelToDodge";
        public static final String ACTIVITY_COMBAT_PpL_CRIT = "activity.combat.PercentageChancePerLevelToCrit";        
    }
    
    public class SmithingActivities {
        public static final String ACTIVITY_SMITHING_LEVEL_INTERVAL_TO_DECREASE_COST = "activity.smithing.OneLessMaterialPerHowManyLevels";
        public static final String ACTIVITY_SMITHING_MINIMUM_REQUIRED_MATERIAL = "activity.smithing.MinimumAmountOfMaterialToRepair";
    }
    
    public class PassiveActivities {
        public static final String ACTIVITY_PASSIVE_REGEN_HH_PER_LEVEL = "activity.passive.regeneration.HalfheartsPerLevel";
        public static final String ACTIVITY_PASSIVE_REGEN_DELAY = "activity.passive.regeneration.RegenIntervalInSeconds";
    }
    
    public class GlobalSettings {
        
        public static final String WORLD_NAME = "settings.WorldName";
        public static final String SETTINGS_COMBAT_DODGE_MODIFIER = "settings.combat.PercentageDamageFromDodge";
        public static final String SETTINGS_COMBAT_CRIT_MODIFIER = "settings.combat.PercentageDamageFromCrit";
        public static final String SETTINGS_COMBAT_HEADSHOT_MODIFIER = "settings.combat.PercentageDamageFromHeadshot";
        public static final String SETTINGS_COMBAT_MAX_CRIT_CHANCE = "settings.combat.MaximumChanceForCrit";
        public static final String SETTINGS_COMBAT_MAX_DODGE_CHANCE = "settings.combat.MaximumChanceForDodge";
        
        public static final String SETTINGS_WORLD_ORE_IRON_FREQUENCY_PERCENTAGE = "settings.world.ore.iron.PercentageOfNormalFrequency";
    }
    
    public class Commands {
        public static final String COMMANDS_SHOW_STATS = "stats";
        public static final String COMMANDS_SPAWN_DRAGON = "dragon";
    }
    
    public class Sorcery {

        public static final String SORCERY_DESTRUCTION_MANA_MATERIAL = "sorcery.destruction.mana.material";
        public static final String SORCERY_DESTRUCTION_WAND = "sorcery.destruction.wand";
        public static final String SORCERY_DESTRUCTION_SPELLS_FIREBALL_MANACOST = "sorcery.destruction.spells.fireball.manacost";
        public static final String SORCERY_DESTRUCTION_SPELLS_KNOCKBACK_MANACOST = "sorcery.destruction.spells.knockback.manacost";
        public static final String SORCERY_DESTRUCTION_SPELLS_KNOCKBACK_RANGE_PER_LEVEL = "sorcery.destruction.spells.knockback.rangePerLevel";
        public static final String SORCERY_DESCTURCTION_SPELLS_LIGHTING_MANACOST = "sorcery.destruction.spells.lightning.manacost";
        public static final String SORCERY_DESCTURCTION_SPELLS_LIGHTING_RANGE = "sorcery.destruction.spells.lightning.range";
        public static final String SORCERY_DESCTURCTION_SPELLS_PIT_MANACOST = "sorcery.destruction.spells.pit.manacost";
        public static final String SORCERY_HEALING_MANA_MATERIAL = "sorcery.healing.mana.material";
        public static final String SORCERY_HEALING_WAND = "sorcery.healing.wand";
        public static final String SORCERY_HEALING_SPELLS_HEAL_MANACOST = "sorcery.healing.spells.heal.manacost";
        public static final String SORCERY_HEALING_SPELLS_HEAL_RANGE = "sorcery.healing.spells.heal.range";
        public static final String SORCERY_ALLOW_MANA_BURN = "sorcery.allowmanaburn";
        public static final String SORCERY_HEALING_SPELLS_ENDURANCE_MANACOST = "sorcery.healing.spells.endurance.manacost";
    
    }
}
