package thederpgamer.betterchambers.effects.support;

import org.schema.game.common.data.blockeffects.config.EffectConfigElement;
import org.schema.game.common.data.blockeffects.config.StatusEffectType;
import org.schema.game.common.data.blockeffects.config.elements.ModifierStackType;
import org.schema.game.common.data.blockeffects.config.parameter.StatusEffectFloatValue;
import thederpgamer.betterchambers.effects.ConfigEffectGroup;
import thederpgamer.betterchambers.manager.ConfigManager;

import java.util.ArrayList;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/26/2022]
 */
public class ShieldAuraEffectGroup {

	public static class ShieldAuraBaseEffect extends ConfigEffectGroup {

		public ShieldAuraBaseEffect() {
			super("shield_aura_base_effect");
		}

		@Override
		public void createElements(ArrayList<EffectConfigElement> elementList) {
			{ //Set Level Ratio
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.AURA_REACTOR_LEVEL_RATIO);
				configElement.stackType = ModifierStackType.SET;
				configElement.priority = -1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("aura-chamber-base-effect-reactor-level-ratio", 0.2f));
				configElement.value = value;
				elementList.add(configElement);
			}

			{ //Set Capacity
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.SHIELD_CAPACITY);
				configElement.stackType = ModifierStackType.SET;
				configElement.priority = -1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(1.0f);
				configElement.value = value;
				elementList.add(configElement);
			}

			{ //Set Upkeep
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.SHIELD_CAPACITY_UPKEEP);
				configElement.stackType = ModifierStackType.SET;
				configElement.priority = -1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(1.0f);
				configElement.value = value;
				elementList.add(configElement);
			}

			{ //Add Capacity
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.SHIELD_CAPACITY);
				configElement.stackType = ModifierStackType.ADD;
				configElement.priority = 1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("shield-boost-aura-chamber-capacity-add", 0.15f));
				configElement.value = value;
				elementList.add(configElement);
			}

			{ //Reduce Upkeep
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.SHIELD_CAPACITY_UPKEEP);
				configElement.stackType = ModifierStackType.ADD;
				configElement.priority = 1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(-ConfigManager.getSystemConfig().getConfigurableFloat("shield-boost-aura-chamber-upkeep-reduction-add", 0.15f));
				configElement.value = value;
				elementList.add(configElement);
			}
		}
	}

	public static class ShieldAuraCapacity1Effect extends ConfigEffectGroup {

		public ShieldAuraCapacity1Effect() {
			super("shield_aura_cap_1_effect");
		}

		@Override
		public void createElements(ArrayList<EffectConfigElement> elementList) {
			{ //Set Level Ratio
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.AURA_REACTOR_LEVEL_RATIO);
				configElement.stackType = ModifierStackType.SET;
				configElement.priority = -1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("aura-chamber-1-effect-reactor-level-ratio", 0.25f));
				configElement.value = value;
				elementList.add(configElement);
			}

			{ //Add Capacity
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.SHIELD_CAPACITY);
				configElement.stackType = ModifierStackType.ADD;
				configElement.priority = 2;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("shield-capacity-1-aura-chamber-add", 0.1f));
				configElement.value = value;
				elementList.add(configElement);
			}
		}
	}

	public static class ShieldAuraCapacity2Effect extends ConfigEffectGroup {

		public ShieldAuraCapacity2Effect() {
			super("shield_aura_cap_2_effect");
		}

		@Override
		public void createElements(ArrayList<EffectConfigElement> elementList) {
			{ //Set Level Ratio
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.AURA_REACTOR_LEVEL_RATIO);
				configElement.stackType = ModifierStackType.SET;
				configElement.priority = -1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("aura-chamber-2-effect-reactor-level-ratio", 0.3f));
				configElement.value = value;
				elementList.add(configElement);
			}

			{ //Add Capacity
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.SHIELD_CAPACITY);
				configElement.stackType = ModifierStackType.ADD;
				configElement.priority = 2;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("shield-capacity-2-aura-chamber-add", 0.2f));
				configElement.value = value;
				elementList.add(configElement);
			}
		}
	}
}
