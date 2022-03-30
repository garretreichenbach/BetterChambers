package thederpgamer.betterchambers.systems.weapons.auradisruptor;

import org.schema.game.client.data.GameClientState;
import org.schema.game.client.data.GameStateInterface;
import org.schema.game.client.view.gui.structurecontrol.ControllerManagerGUI;
import org.schema.game.common.controller.damage.HitType;
import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;
import org.schema.game.common.controller.elements.beam.BeamUnit;
import org.schema.game.common.controller.elements.beam.harvest.SalvageElementManager;
import thederpgamer.betterchambers.manager.ConfigManager;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/29/2022]
 */
public class AuraDisruptorBeamUnit extends BeamUnit<AuraDisruptorBeamUnit, AuraDisruptorBeamCollectionManager, AuraDisruptorBeamElementManager> {

	@Override
	public float getBeamPowerWithoutEffect() {
		return getBeamPower();
	}

	@Override
	public float getBeamPower() {
		return size() * getBaseBeamPower();
	}

	@Override
	public float getBaseBeamPower() {
		return ConfigManager.getSystemConfig().getConfigurableFloat("aura-disruptor-beam-power-per-unit", 1.0f);
	}

	@Override
	public float getBasePowerConsumption() {
		return ConfigManager.getSystemConfig().getConfigurableFloat("aura-disruptor-beam-power-consumption-per-unit", 12.0f);
	}

	@Override
	public float getPowerConsumption() {
		return size() * getBasePowerConsumption();
	}

	@Override
	public float getPowerConsumptionWithoutEffect() {
		return getBasePowerConsumption();
	}

	@Override
	public HitType getHitType() {
		return HitType.SUPPORT;
	}

	@Override
	public float getDistanceRaw() {
		return SalvageElementManager.DISTANCE * ((GameStateInterface) getSegmentController().getState()).getGameState().getWeaponRangeReference();
	}

	@Override
	public float getDamage() {
		return 0;
	}

	@Override
	public double getPowerConsumedPerSecondResting() {
		return 0;
	}

	@Override
	public double getPowerConsumedPerSecondCharging() {
		return getPowerConsumption();
	}

	@Override
	public PowerConsumerCategory getPowerConsumerCategory() {
		return PowerConsumerCategory.SUPPORT_BEAMS;
	}

	@Override
	public ControllerManagerGUI createUnitGUI(GameClientState gameClientState, ControlBlockElementCollectionManager<?, ?, ?> supportCol, ControlBlockElementCollectionManager<?, ?, ?> effectCol) {
		return elementCollectionManager.getElementManager().getGUIUnitValues(this, elementCollectionManager, supportCol, effectCol);
	}

	@Override
	public void flagBeamFiredWithoutTimeout() {
		elementCollectionManager.flagBeamFiredWithoutTimeout(this);
	}
}
