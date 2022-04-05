package thederpgamer.betterchambers.effects.defense;

import org.schema.game.common.data.blockeffects.config.EffectConfigElement;
import org.schema.game.common.data.blockeffects.config.StatusEffectType;
import org.schema.game.common.data.blockeffects.config.elements.ModifierStackType;
import org.schema.game.common.data.blockeffects.config.parameter.StatusEffectFloatValue;
import thederpgamer.betterchambers.effects.ConfigEffectGroup;

import java.util.ArrayList;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/26/2022]
 */
public class EnvironmentalArmor3Effect extends ConfigEffectGroup {

	public EnvironmentalArmor3Effect() {
		super("environmental_armor_3_effect");
	}

	@Override
	public void createElements(ArrayList<EffectConfigElement> elementList) {
		{ //Set Base
			EffectConfigElement configElement = new EffectConfigElement();
			configElement.init(StatusEffectType.ARMOR_DEFENSE_ENVIRONMENTAL);
			configElement.stackType = ModifierStackType.SET;
			configElement.priority = 0;
			StatusEffectFloatValue value = new StatusEffectFloatValue();
			value.value.set(0.0f);
			configElement.value = value;
			elementList.add(configElement);
		}

		{ //Add Effect
			EffectConfigElement configElement = new EffectConfigElement();
			configElement.init(StatusEffectType.ARMOR_DEFENSE_ENVIRONMENTAL);
			configElement.stackType = ModifierStackType.ADD;
			configElement.priority = 0;
			StatusEffectFloatValue value = new StatusEffectFloatValue();
			value.value.set(1.0f);
			configElement.value = value;
			elementList.add(configElement);
		}
	}
}
