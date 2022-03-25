package thederpgamer.betterchambers.systems.chambers.offense;

import org.schema.game.common.data.blockeffects.config.ConfigGroup;
import org.schema.game.common.data.blockeffects.config.EffectConfigElement;
import org.schema.game.common.data.blockeffects.config.StatusEffectType;
import org.schema.game.common.data.blockeffects.config.elements.ModifierStackType;
import org.schema.game.common.data.blockeffects.config.parameter.StatusEffectFloatValue;
import thederpgamer.betterchambers.manager.ConfigManager;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/25/2022]
 */
public class AIEffectGroup {

	public static class AIBaseEnhancementEffect extends ConfigGroup {

		public AIBaseEnhancementEffect() {
			super("ai_base_enhancement_effect");
			{ //Turret Accuracy
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.AI_ACCURACY_TURRET);
				configElement.stackType = ModifierStackType.MULT;
				configElement.priority = 0;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("ai-base-enhancement-chamber-turret-accuracy-multiplier", 0.2f));
				configElement.value = value;
				elements.add(configElement);
			}

			/*
			{ //PD Accuracy
				EffectConfigElement configElement = new EffectConfigElement();
				configElement.init(StatusEffectType.AI_ACCURACY_POINT_DEFENSE);
				configElement.stackType = ModifierStackType.MULT;
				configElement.priority = 0;
				StatusEffectFloatValue value = new StatusEffectFloatValue();
				value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("ai-base-enhancement-chamber-pd-accuracy-multiplier", 1.3f));
				configElement.value = value;
				elements.add(configElement);
			}
			 */
		}
	}
}
