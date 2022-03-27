package thederpgamer.betterchambers.effects.support;

import org.schema.game.common.data.blockeffects.config.EffectConfigElement;
import org.schema.game.common.data.blockeffects.config.StatusEffectType;
import org.schema.game.common.data.blockeffects.config.elements.ModifierStackType;
import org.schema.game.common.data.blockeffects.config.parameter.StatusEffectFloatValue;
import thederpgamer.betterchambers.manager.ConfigManager;
import thederpgamer.betterchambers.effects.ConfigEffectGroup;

import java.util.ArrayList;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/26/2022]
 */
public class AuraEffectGroup {

	public static class AuraRangeBoostEffect1 extends ConfigEffectGroup {

		public AuraRangeBoostEffect1() {
			super("aura_range_boost_effect_1");
		}

		@Override
		public void createElements(ArrayList<EffectConfigElement> elementList) {
			{ //Add Range
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.AURA_RANGE);
				configElement.stackType = ModifierStackType.ADD;
				configElement.priority = 1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("aura-range-chamber-1-range-add", 0.5f));
				configElement.value = value;
				elementList.add(configElement);
			}
		}
	}

	public static class AuraRangeBoostEffect2 extends ConfigEffectGroup {

		public AuraRangeBoostEffect2() {
			super("aura_range_boost_effect_2");
		}

		@Override
		public void createElements(ArrayList<EffectConfigElement> elementList) {
			{ //Add Range
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.AURA_RANGE);
				configElement.stackType = ModifierStackType.ADD;
				configElement.priority = 1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("aura-range-chamber-2-range-add", 0.5f));
				configElement.value = value;
				elementList.add(configElement);
			}
		}
	}

	public static class AuraRangeBoostEffect3 extends ConfigEffectGroup {

		public AuraRangeBoostEffect3() {
			super("aura_range_boost_effect_3");
		}

		@Override
		public void createElements(ArrayList<EffectConfigElement> elementList) {
			{ //Add Range
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.AURA_RANGE);
				configElement.stackType = ModifierStackType.ADD;
				configElement.priority = 1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("aura-range-chamber-3-range-add", 0.5f));
				configElement.value = value;
				elementList.add(configElement);
			}
		}
	}
}
