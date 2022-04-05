package thederpgamer.betterchambers.systems.weapons.auradisruptor;

import api.utils.game.module.CustomModuleUtils;
import api.utils.sound.AudioUtils;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.shorts.ShortOpenHashSet;
import org.schema.common.util.StringTools;
import org.schema.game.client.data.GameClientState;
import org.schema.game.client.view.gui.structurecontrol.ControllerManagerGUI;
import org.schema.game.client.view.gui.structurecontrol.ModuleValueEntry;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;
import org.schema.game.common.controller.elements.beam.BeamElementManager;
import org.schema.game.common.controller.elements.combination.BeamCombiSettings;
import org.schema.game.common.controller.elements.combination.CombinationAddOn;
import org.schema.game.common.data.SegmentPiece;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.player.PlayerState;
import org.schema.game.server.data.GameServerState;
import org.schema.schine.common.language.Lng;
import thederpgamer.betterchambers.element.ElementManager;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/29/2022]
 */
public class AuraDisruptorBeamElementManager extends BeamElementManager<AuraDisruptorBeamUnit, AuraDisruptorBeamCollectionManager, AuraDisruptorBeamElementManager> {

	public AuraDisruptorBeamElementManager(SegmentController segmentController) {
		super(ElementManager.getWeapon("Aura Disruptor Computer").getComputerId(), ElementManager.getWeapon("Aura Disruptor Module").getModuleId(), segmentController);
		CustomModuleUtils.setElementManager(this, getComputerInfo().getId(), getModuleInfo().getId());
	}

	public ElementInformation getComputerInfo() {
		return ElementManager.getWeapon("Aura Disruptor Computer").getComputerInfo();
	}

	public ElementInformation getModuleInfo() {
		return ElementManager.getWeapon("Aura Disruptor Module").getModuleInfo();
	}

	@Override
	public float getTickRate() {
		return 100f;
	}

	@Override
	public float getCoolDown() {
		return 3.0f;
	}

	@Override
	public float getBurstTime() {
		return 3.0f;
	}

	@Override
	public float getInitialTicks() {
		return 1.0f;
	}

	@Override
	public double getRailHitMultiplierParent() {
		return 3.0f;
	}

	@Override
	public double getRailHitMultiplierChild() {
		return 3.0f;
	}

	@Override
	public void updateActivationTypes(ShortOpenHashSet shortOpenHashSet) {
		shortOpenHashSet.add(getModuleInfo().getId());
	}

	@Override
	protected String getTag() {
		return "mainreactor";
	}

	@Override
	public AuraDisruptorBeamCollectionManager getNewCollectionManager(SegmentPiece segmentPiece, Class<AuraDisruptorBeamCollectionManager> aClass) {
		return new AuraDisruptorBeamCollectionManager(segmentPiece, getSegmentController(), this);
	}

	@Override
	public String getManagerName() {
		return "Aura Disruptor System Collective";
	}

	@Override
	protected void playSound(AuraDisruptorBeamUnit auraDisruptorBeamUnit, Transform transform) {
		if(getState() instanceof GameServerState) {
			for(PlayerState playerState : getAttachedPlayers()) {
				AudioUtils.serverPlaySound("0022_spaceship user - special synthetic weapon fire 2", transform.origin.x, transform.origin.y, transform.origin.z, 10.0f, 1.0f, playerState);
			}
		}
	}

	@Override
	public ControllerManagerGUI getGUIUnitValues(AuraDisruptorBeamUnit firingUnit, AuraDisruptorBeamCollectionManager col, ControlBlockElementCollectionManager<?, ?, ?> supportCol, ControlBlockElementCollectionManager<?, ?, ?> effectCol) {
		return ControllerManagerGUI.create((GameClientState) getState(), Lng.str("Beam Unit"), firingUnit,
		                                   new ModuleValueEntry("Disruptor Power", StringTools.formatPointZero(firingUnit.getBeamPower())),
		                                   new ModuleValueEntry(Lng.str("TickRate"), StringTools.formatPointZeroZero(firingUnit.getTickRate())),
		                                   new ModuleValueEntry(Lng.str("Range"), StringTools.formatPointZero(firingUnit.getDistance())),
		                                   new ModuleValueEntry(Lng.str("PowerConsumptionResting"), firingUnit.getPowerConsumedPerSecondResting()),
		                                   new ModuleValueEntry(Lng.str("PowerConsumptionCharging"), firingUnit.getPowerConsumedPerSecondCharging()));
	}

	@Override
	public CombinationAddOn<AuraDisruptorBeamUnit, AuraDisruptorBeamCollectionManager, ? extends AuraDisruptorBeamElementManager, BeamCombiSettings> getAddOn() {
		return null;
	}
}
