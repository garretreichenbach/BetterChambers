package thederpgamer.betterchambers.systems.weapons.auradisruptor;

import api.common.GameCommon;
import api.utils.game.SegmentControllerUtils;
import org.schema.common.util.linAlg.Vector3i;
import org.schema.game.client.view.beam.BeamColors;
import org.schema.game.common.controller.BeamHandlerContainer;
import org.schema.game.common.controller.ManagedUsableSegmentController;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.BeamState;
import org.schema.game.common.controller.elements.power.reactor.tree.ReactorElement;
import org.schema.game.common.data.ManagedSegmentController;
import org.schema.game.common.data.SegmentPiece;
import org.schema.game.common.data.element.beam.BeamHandler;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.player.faction.FactionManager;
import org.schema.game.common.data.player.faction.FactionRelation;
import org.schema.game.common.data.world.Segment;
import org.schema.schine.graphicsengine.core.Timer;
import thederpgamer.betterchambers.element.ElementManager;
import thederpgamer.betterchambers.manager.ConfigManager;
import thederpgamer.betterchambers.systems.chambers.support.AuraProjectorAddOn;

import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import java.util.Collection;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/29/2022]
 */
public class AuraDisruptorBeamHandler extends BeamHandler {

	public AuraDisruptorBeamHandler(SegmentController segmentController, BeamHandlerContainer beamHandlerContainer) {
		super(segmentController, beamHandlerContainer);
	}

	@Override
	public boolean canhit(BeamState beamState, SegmentController segmentController, String[] strings, Vector3i vector3i) {
		if(getBeamShooter().getId() != segmentController.getId()) {
			int shooterFactionId = getBeamShooter().getFactionId();
			int entityId = segmentController.getFactionId();
			FactionManager factionManager = GameCommon.getGameState().getFactionManager();
			if(!factionManager.getRelation(shooterFactionId, entityId).equals(FactionRelation.RType.FRIEND) || ConfigManager.getMainConfig().getBoolean("debug-mode")) {
				if(segmentController instanceof ManagedUsableSegmentController<?>) {
					ReactorElement auraProjectorChamber = SegmentControllerUtils.getChamberFromElement((ManagedUsableSegmentController<?>) segmentController, ElementManager.getChamber("Aura Projector").getBlockInfo());
					if(auraProjectorChamber != null && auraProjectorChamber.isAllValidOrUnspecified()) {
						AuraProjectorAddOn addOn = SegmentControllerUtils.getAddon((ManagedSegmentController<?>) segmentController, AuraProjectorAddOn.class);
						return addOn != null && addOn.isActive();
					}
				}
			}
		}
		return false;
	}

	@Override
	public float getBeamTimeoutInSecs() {
		return 0.05f;
	}

	@Override
	public float getBeamToHitInSecs(BeamState beamState) {
		return beamState.getTickRate();
	}

	@Override
	public int onBeamHit(BeamState beamState, int hits, BeamHandlerContainer<SegmentController> beamHandlerContainer, SegmentPiece segmentPiece, Vector3f vector3f, Vector3f vector3f1, Timer timer, Collection<Segment> collection) {
		try {
			ManagedUsableSegmentController<?> target = (ManagedUsableSegmentController<?>) beamState.segmentHit.getSegmentController();
			AuraProjectorAddOn addOn = SegmentControllerUtils.getAddon(target, AuraProjectorAddOn.class);
			ReactorElement auraBase = SegmentControllerUtils.getChamberFromElement(target, ElementManager.getChamber("Aura Projector").getBlockInfo());
			addOn.disrupt(beamState.getTickRate(), 1.0f - Math.max(0.01f, (beamState.getPower() / auraBase.getActualSize()) / 10.0f));
		} catch(Exception ignored) { }
		return hits;
	}

	@Override
	protected boolean onBeamHitNonCube(BeamState beamState, int i, BeamHandlerContainer<SegmentController> beamHandlerContainer, Vector3f vector3f, Vector3f vector3f1, CubeRayCastResult cubeRayCastResult, Timer timer, Collection<Segment> collection) {
		return false;
	}

	@Override
	protected Vector4f getDefaultColor(BeamState beamState) {
		return getColorRange(BeamColors.GREEN);
	}
}
