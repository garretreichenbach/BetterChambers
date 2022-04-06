package thederpgamer.betterchambers.effects.offense;

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
 * @version 1.0 - [03/25/2022]
 */
public class AIEffectGroup {

	public static class AIBaseEnhancementEffect extends ConfigEffectGroup {

		public AIBaseEnhancementEffect() {
			super("ai_base_enhancement_effect");
		}

		@Override
		public void createElements(ArrayList<EffectConfigElement> elementList) {
			{ //Set Base Weapon Range
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.WEAPON_RANGE);
				configElement.stackType = ModifierStackType.SET;
				configElement.priority = -1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(1.0f);
				configElement.value = value;
				elementList.add(configElement);
			}

			{ //Set Base Turret Accuracy
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.AI_ACCURACY_TURRET);
				configElement.stackType = ModifierStackType.SET;
				configElement.priority = -1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(1.0f);
				configElement.value = value;
				elementList.add(configElement);
			}

			{ //Set Base PD Accuracy
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.AI_ACCURACY_POINT_DEFENSE);
				configElement.stackType = ModifierStackType.SET;
				configElement.priority = -1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(1.0f);
				configElement.value = value;
				elementList.add(configElement);
			}

			{ //Set Base Drone Accuracy
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.AI_ACCURACY_DRONE);
				configElement.stackType = ModifierStackType.SET;
				configElement.priority = -1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(1.0f);
				configElement.value = value;
				elementList.add(configElement);
			}

			/*
			{ //Weapon Range
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.WEAPON_RANGE);
				configElement.stackType = ModifierStackType.ADD;
				configElement.priority = 1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("ai-base-enhancement-chamber-weapon-range-add", 0.15f));
				configElement.value = value;
				elementList.add(configElement);
			}
			 */

			{ //Turret Accuracy
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.AI_ACCURACY_TURRET);
				configElement.stackType = ModifierStackType.ADD;
				configElement.priority = 1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("ai-base-enhancement-chamber-turret-accuracy-add", 0.2f));
				configElement.value = value;
				elementList.add(configElement);
			}

			{ //PD Accuracy
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.AI_ACCURACY_POINT_DEFENSE);
				configElement.stackType = ModifierStackType.ADD;
				configElement.priority = 1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("ai-base-enhancement-chamber-pd-accuracy-add", 0.2f));
				configElement.value = value;
				elementList.add(configElement);
			}

			{ //Drone Accuracy
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.AI_ACCURACY_DRONE);
				configElement.stackType = ModifierStackType.ADD;
				configElement.priority = 1;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("ai-base-enhancement-chamber-drone-accuracy-add", 0.2f));
				configElement.value = value;
				elementList.add(configElement);
			}
		}
	}
}
