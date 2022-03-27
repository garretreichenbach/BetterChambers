package thederpgamer.betterchambers.effects.support;

import org.schema.game.common.data.blockeffects.config.EffectConfigElement;
import org.schema.game.common.data.blockeffects.config.StatusEffectType;
import org.schema.game.common.data.blockeffects.config.elements.ModifierStackType;
import org.schema.game.common.data.blockeffects.config.parameter.StatusEffectFloatValue;
import org.schema.game.common.data.blockeffects.config.parameter.StatusEffectIntValue;
import thederpgamer.betterchambers.manager.ConfigManager;
import thederpgamer.betterchambers.effects.ConfigEffectGroup;

import java.util.ArrayList;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/26/2022]
 */
public class ShieldAuraEffectGroup {

	public static class ShieldAuraBaseEffect extends ConfigEffectGroup implements AuraEffect {

		public ShieldAuraBaseEffect() {
			super("shield_aura_base_effect");
		}

		@Override
		public void createElements(ArrayList<EffectConfigElement> elementList) {
			{ //Set Range
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.AURA_RANGE);
				configElement.stackType = ModifierStackType.SET;
				configElement.priority = 0;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("shield-boost-aura-base-chamber-range-set", 0.5f));
				configElement.value = value;
				elementList.add(configElement);
			}

			{ //Set Max Targets
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.AURA_MAX_TARGETS);
				configElement.stackType = ModifierStackType.SET;
				configElement.priority = 0;
				StatusEffectIntValue value = new StatusEffectIntValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableInt("shield-boost-aura-base-chamber-max-targets-set", 1));
				configElement.value = value;
				elementList.add(configElement);
			}

			{ //Add Capacity
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.AURA_SHIELD_CAP);
				configElement.stackType = ModifierStackType.ADD;
				configElement.priority = 0;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("shield-boost-aura-base-chamber-capacity-add", 0.15f));
				configElement.value = value;
				elementList.add(configElement);
			}

			{ //Reduce Upkeep
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.AURA_SHIELD_UPKEEP_REDUCTION);
				configElement.stackType = ModifierStackType.ADD;
				configElement.priority = 0;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("shield-boost-aura-base-chamber-upkeep-reduction-add", 0.15f));
				configElement.value = value;
				elementList.add(configElement);
			}
		}

		@Override
		public ConfigEffectGroup getTargetEffectGroup() {
			return ShieldAuraBaseTargetEffect.instance;
		}
	}

	public static class ShieldAuraBaseTargetEffect extends ConfigEffectGroup {

		public ShieldAuraBaseTargetEffect() {
			super("shield_aura_base_target_effect");
		}

		@Override
		public void createElements(ArrayList<EffectConfigElement> elementList) {
			{ //Add Capacity
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.SHIELD_CAPACITY);
				configElement.stackType = ModifierStackType.ADD;
				configElement.priority = 1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("shield-boost-aura-base-chamber-capacity-add", 0.15f));
				configElement.value = value;
				elementList.add(configElement);
			}

			{ //Reduce Upkeep
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.SHIELD_CAPACITY_UPKEEP);
				configElement.stackType = ModifierStackType.ADD;
				configElement.priority = 1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(-ConfigManager.getSystemConfig().getConfigurableFloat("shield-boost-aura-base-chamber-upkeep-reduction-add", 0.15f));
				configElement.value = value;
				elementList.add(configElement);
			}
		}
	}
}
