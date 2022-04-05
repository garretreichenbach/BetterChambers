package thederpgamer.betterchambers.manager;

import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
import org.schema.game.common.data.blockeffects.config.ConfigGroup;
import thederpgamer.betterchambers.effects.ConfigEffectGroup;
import thederpgamer.betterchambers.effects.defense.EnvironmentalArmor3Effect;
import thederpgamer.betterchambers.effects.offense.AIEffectGroup;
import thederpgamer.betterchambers.effects.support.AuraEffectGroup;
import thederpgamer.betterchambers.effects.support.OffenseAuraEffectGroup;
import thederpgamer.betterchambers.effects.support.ShieldAuraEffectGroup;

import java.util.HashMap;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/30/2022]
 */
public class EffectConfigManager {

	public static final HashMap<String, ConfigEffectGroup> effectMap = new HashMap<>();

	public static void initializeEffects(ObjectArrayFIFOQueue<ConfigGroup> modConfigGroups) {
		effectMap.put("ai_base_enhancement_effect", new AIEffectGroup.AIBaseEnhancementEffect());
		effectMap.put("aura_base_effect", new AuraEffectGroup.AuraBaseEffect());
		effectMap.put("aura_range_boost_effect_1", new AuraEffectGroup.AuraRangeBoostEffect1());
		effectMap.put("aura_range_boost_effect_2", new AuraEffectGroup.AuraRangeBoostEffect2());
		effectMap.put("aura_range_boost_effect_3", new AuraEffectGroup.AuraRangeBoostEffect3());
		effectMap.put("shield_aura_base_effect", new ShieldAuraEffectGroup.ShieldAuraBaseEffect());
		effectMap.put("shield_aura_cap_1_effect", new ShieldAuraEffectGroup.ShieldAuraCapacity1Effect());
		effectMap.put("shield_aura_cap_2_effect", new ShieldAuraEffectGroup.ShieldAuraCapacity2Effect());
		effectMap.put("offense_aura_base_effect", new OffenseAuraEffectGroup.OffenseAuraBaseEffect());
		effectMap.put("offense_aura_targeting_1_effect", new OffenseAuraEffectGroup.OffenseAuraTargeting1Effect());
		effectMap.put("offense_aura_targeting_2_effect", new OffenseAuraEffectGroup.OffenseAuraTargeting2Effect());
		effectMap.put("environmental_armor_3_effect", new EnvironmentalArmor3Effect());
		for(ConfigEffectGroup effectGroup : effectMap.values()) modConfigGroups.enqueue(effectGroup);
	}
}
