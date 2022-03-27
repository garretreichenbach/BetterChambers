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

		public static ShieldAuraBaseEffect instance;

		public ShieldAuraBaseEffect() {
			super("shield_aura_base_effect");
			instance = this;
		}

		@Override
		public void createElements(ArrayList<EffectConfigElement> elementList) {
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

			{ //Set Capacity
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
}
