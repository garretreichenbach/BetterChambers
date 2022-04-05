package thederpgamer.betterchambers.effects;

import org.schema.game.common.data.blockeffects.config.ConfigGroup;
import org.schema.game.common.data.blockeffects.config.EffectConfigElement;

import java.util.ArrayList;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/26/2022]
 */
public abstract class ConfigEffectGroup extends ConfigGroup {

	public ConfigEffectGroup(String effectIdentifier) {
		super(effectIdentifier);
		ArrayList<EffectConfigElement> elementList = new ArrayList<>();
		createElements(elementList);
		elements.addAll(elementList);
	}

	public abstract void createElements(ArrayList<EffectConfigElement> elementList);
}
