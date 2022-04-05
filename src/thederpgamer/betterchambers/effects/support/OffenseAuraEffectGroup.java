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
 * @version 1.0 - [03/28/2022]
 */
public class OffenseAuraEffectGroup {

    public static class OffenseAuraBaseEffect extends ConfigEffectGroup {

        public OffenseAuraBaseEffect() {
            super("offense_aura_base_effect");
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

            { //Turret Accuracy
                EffectConfigElement configElement = new EffectConfigElement();
                configElement.init(StatusEffectType.AI_ACCURACY_TURRET);
                configElement.stackType = ModifierStackType.ADD;
                configElement.priority = 1;
                StatusEffectFloatValue value = new StatusEffectFloatValue();
                value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("offense-aura-chamber-turret-accuracy-add", 0.15f));
                configElement.value = value;
                elementList.add(configElement);
            }

            { //PD Accuracy
                EffectConfigElement configElement = new EffectConfigElement();
                configElement.init(StatusEffectType.AI_ACCURACY_POINT_DEFENSE);
                configElement.stackType = ModifierStackType.ADD;
                configElement.priority = 1;
                StatusEffectFloatValue value = new StatusEffectFloatValue();
                value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("offense-aura-chamber-pd-accuracy-add", 0.15f));
                configElement.value = value;
                elementList.add(configElement);
            }

            { //Drone Accuracy
                EffectConfigElement configElement = new EffectConfigElement();
                configElement.init(StatusEffectType.AI_ACCURACY_DRONE);
                configElement.stackType = ModifierStackType.ADD;
                configElement.priority = 1;
                StatusEffectFloatValue value = new StatusEffectFloatValue();
                value.value.set(ConfigManager.getSystemConfig().getConfigurableFloat("offense-aura-chamber-drone-accuracy-add", 0.15f));
                configElement.value = value;
                elementList.add(configElement);
            }
        }
    }

    public static class OffenseAuraTargeting1Effect extends ConfigEffectGroup {

        public OffenseAuraTargeting1Effect() {
            super("offense_aura_targeting_1_effect");
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
            //Todo
        }
    }

    public static class OffenseAuraTargeting2Effect extends ConfigEffectGroup {

        public OffenseAuraTargeting2Effect() {
            super("offense_aura_targeting_2_effect");
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
            //Todo
        }
    }
}
