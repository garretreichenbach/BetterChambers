package thederpgamer.betterchambers.systems.chambers.support;

import api.common.GameClient;
import api.common.GameCommon;
import api.common.GameServer;
import api.listener.events.systems.ReactorRecalibrateEvent;
import api.network.packets.PacketUtil;
import api.utils.addon.SimpleAddOn;
import api.utils.game.PlayerUtils;
import api.utils.game.SegmentControllerUtils;
import api.utils.sound.AudioUtils;
import com.bulletphysics.linearmath.Transform;
import org.schema.game.client.data.GameClientState;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ManagerContainer;
import org.schema.game.common.controller.elements.effectblock.EffectElementManager;
import org.schema.game.common.controller.elements.power.reactor.tree.ReactorElement;
import org.schema.game.common.data.ManagedSegmentController;
import org.schema.game.common.data.blockeffects.config.ConfigEntityManager;
import org.schema.game.common.data.blockeffects.config.StatusEffectType;
import org.schema.game.common.data.player.PlayerState;
import org.schema.game.common.data.player.faction.FactionRelation;
import org.schema.game.common.data.world.Sector;
import org.schema.game.common.data.world.SimpleTransformableSendableObject;
import org.schema.game.server.data.GameServerState;
import org.schema.game.server.data.ServerConfig;
import org.schema.schine.graphicsengine.core.GlUtil;
import thederpgamer.betterchambers.BetterChambers;
import thederpgamer.betterchambers.effects.ConfigEffectGroup;
import thederpgamer.betterchambers.element.ElementManager;
import thederpgamer.betterchambers.manager.EffectConfigManager;
import thederpgamer.betterchambers.network.server.SendAuraPulsePacket;
import thederpgamer.betterchambers.utils.EntityUtils;

import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/26/2022]
 */
public class AuraProjectorAddOn extends SimpleAddOn {

	public static final int UPDATE_TIMER = 500;
	public static final int NONE = 0;
	public static final int DEFENSE = 1;
	public static final int OFFENSE = 2;

	private int projectorType = NONE;
	private final ArrayList<ConfigEffectGroup> effectsToApply = new ArrayList<>();
	private final ConcurrentHashMap<SegmentController, Boolean> targetingEntities = new ConcurrentHashMap<>();
	private boolean usable = false;
	private int ticks = 0;

	private int maxTargets = 0;
	private float maxRange = 0.0f;
	private float levelRatio = 1.0f;

	public AuraProjectorAddOn(ManagerContainer<?> managerContainer) {
		super(managerContainer, ElementManager.getChamber("Aura Projector").getId(), BetterChambers.getInstance(), "AuraProjectorChamber");
		onReactorRecalibrate(null);
	}

	@Override
	public void onReactorRecalibrate(ReactorRecalibrateEvent event) {
		try {
			ReactorElement auraBase = SegmentControllerUtils.getChamberFromElement(getManagerUsableSegmentController(), ElementManager.getChamber("Aura Projector").getBlockInfo());
			if(auraBase != null && auraBase.isAllValidOrUnspecified()) {
				usable = true;
				effectsToApply.clear();
				ConfigEntityManager configManager = getManagerUsableSegmentController().getConfigManager();
				if(configManager.getModules().containsKey(StatusEffectType.AURA_MAX_TARGETS)) maxTargets = configManager.getModules().get(StatusEffectType.AURA_MAX_TARGETS).getIntValue();
				if(configManager.getModules().containsKey(StatusEffectType.AURA_RANGE)) maxRange = (configManager.getModules().get(StatusEffectType.AURA_RANGE).getFloatValue()) * (Integer) ServerConfig.SECTOR_SIZE.getCurrentState();
				if(configManager.getModules().containsKey(StatusEffectType.AURA_REACTOR_LEVEL_RATIO)) levelRatio = configManager.getModules().get(StatusEffectType.AURA_REACTOR_LEVEL_RATIO).getFloatValue();
				projectorType = NONE;

				ReactorElement shieldAuraBase = SegmentControllerUtils.getChamberFromElement(getManagerUsableSegmentController(), ElementManager.getChamber("Shield Aura Base").getBlockInfo());
				if(shieldAuraBase != null && shieldAuraBase.isAllValidOrUnspecified()) {
					projectorType = DEFENSE;
					effectsToApply.add(EffectConfigManager.effectMap.get("shield_aura_base_effect"));

					ReactorElement shieldAuraCap1 = SegmentControllerUtils.getChamberFromElement(getManagerUsableSegmentController(), ElementManager.getChamber("Shield Aura Capacity 1").getBlockInfo());
					if(shieldAuraCap1 != null && shieldAuraCap1.isAllValidOrUnspecified()) effectsToApply.add(EffectConfigManager.effectMap.get("shield_aura_cap_1_effect"));

					ReactorElement shieldAuraCap2 = SegmentControllerUtils.getChamberFromElement(getManagerUsableSegmentController(), ElementManager.getChamber("Shield Aura Capacity 2").getBlockInfo());
					if(shieldAuraCap2 != null && shieldAuraCap2.isAllValidOrUnspecified()) effectsToApply.add(EffectConfigManager.effectMap.get("shield_aura_cap_2_effect"));
				}

				ReactorElement offenseAuraBase = SegmentControllerUtils.getChamberFromElement(getManagerUsableSegmentController(), ElementManager.getChamber("Offense Aura Base").getBlockInfo());
				if(offenseAuraBase != null && offenseAuraBase.isAllValidOrUnspecified()) {
					projectorType = OFFENSE;
					effectsToApply.add(EffectConfigManager.effectMap.get("offense_aura_base_effect"));

					ReactorElement offenseAuraTargeting1 = SegmentControllerUtils.getChamberFromElement(getManagerUsableSegmentController(), ElementManager.getChamber("Offense Aura Targeting 1").getBlockInfo());
					if(offenseAuraTargeting1 != null && offenseAuraTargeting1.isAllValidOrUnspecified()) effectsToApply.add(EffectConfigManager.effectMap.get("offense_aura_targeting_1_effect"));

					ReactorElement offenseAuraTargeting2 = SegmentControllerUtils.getChamberFromElement(getManagerUsableSegmentController(), ElementManager.getChamber("Offense Aura Targeting 2").getBlockInfo());
					if(offenseAuraTargeting2 != null && offenseAuraTargeting2.isAllValidOrUnspecified()) effectsToApply.add(EffectConfigManager.effectMap.get("offense_aura_targeting_2_effect"));
				}
			}
		} catch(Exception ignored) { }
	}

	@Override
	public float getChargeRateFull() {
		return 15;
	}

	@Override
	public double getPowerConsumedPerSecondResting() {
		double powerConsumed = 0.0;
		try {
			int selfReactorLevel = getManagedSegmentController().getManagerContainer().getPowerInterface().getActiveReactor().getLevel();
			if(isActive() && !targetingEntities.isEmpty()) {
				for(SegmentController entity : targetingEntities.keySet()) {
					if(entity instanceof ManagedSegmentController<?>) {
						float powerToAdd = entity.getMass() / 2.0f;
						int targetReactorLevel = ((ManagedSegmentController<?>) entity).getManagerContainer().getPowerInterface().getActiveReactor().getLevel();
						if(targetReactorLevel > selfReactorLevel) {
							float actualRatio = (float) selfReactorLevel / targetReactorLevel;
							if(actualRatio > levelRatio) {
								//If ratio between the reactor level of the current entity and the target entity is greater than the maximum ratio supported by the chamber setup,
								//increase power consumption by powerToAdd + (powerToAdd * difference)
								float difference = actualRatio - levelRatio;
								powerToAdd += powerToAdd * difference;
							}
						}
						powerConsumed += powerToAdd;
					}
				}
			}
		} catch(Exception ignored) { }
		return powerConsumed;
	}

	@Override
	public double getPowerConsumedPerSecondCharging() {
		return getSegmentController().getMass() * 2.0f;
	}

	@Override
	public boolean isPlayerUsable() {
		return usable;
	}

	@Override
	public float getDuration() {
		return -1;
	}

	@Override
	public void onAttemptToExecute() {
		if(getState() instanceof GameServerState) {
			if(isActive()) AudioUtils.serverPlaySound("0022_spaceship user - special synthetic weapon recharged 1", 10.0f, 1.0f, getAttachedPlayers());
			else AudioUtils.serverPlaySound("0022_spaceship user - special synthetic weapon recharged 2", 10.0f, 1.0f, getAttachedPlayers());
		}
	}

	@Override
	public boolean executeModule() {
		return super.executeModule();
	}

	@Override
	public boolean onExecuteServer() {
		removeEntityEffects();
		targetingEntities.clear();
		addEntityEffects();
		AudioUtils.serverPlaySound("0022_spaceship user - turbo boost large", 10F, 1F, getAttachedPlayers());
		return true;
	}

	@Override
	public boolean onExecuteClient() {
		return true;
	}

	@Override
	public void onDeactivateFromTime() {
		removeEntityEffects();
		targetingEntities.clear();
	}

	@Override
	public void onActive() {
		if(ticks >= UPDATE_TIMER) {
			updateTargetList();
			//drawPulse();
			ticks = 0;
		} else ticks++;
	}

	@Override
	public void onInactive() {
		if(ticks >= UPDATE_TIMER) {
			removeEntityEffects();
			targetingEntities.clear();
			onReactorRecalibrate(null);
			ticks = 0;
		} else ticks++;
	}

	@Override
	public String getName() {
		return "Aura Projector";
	}

	@Override
	public boolean isDeactivatableManually() {
		return isActive();
	}

	public void drawPulse() {
		float radius = getSegmentController().getBoundingSphere().radius * 10.0f;
		if(getState() instanceof GameClientState && GameClient.getClientState() != null) {
			Transform transform = new Transform(segmentController.getWorldTransform());
			Vector3f forward = new Vector3f();
			Vector3f reverse = new Vector3f();
			GlUtil.getForwardVector(forward, transform);
			GlUtil.getBackVector(reverse, transform);
			forward.sub(reverse);
			Vector3f direction = new Vector3f(forward);
			Vector3f color = GameCommon.getGameState().getFactionManager().getRelation(GameClient.getClientPlayerState().getFactionId(), getSegmentController().getFactionId()).defaultColor;
			GameClient.getClientState().getPulseController().addPushPulse(segmentController.getWorldTransform(), direction, getSegmentController(), 0.0f, radius, getUsableId(), new Vector4f(color.x, color.y, color.z, 0.75f));
		} else if(GameServer.getServerState() != null) {
			for(PlayerState playerState : GameServer.getServerState().getPlayerStatesByName().values()) {
				if(playerState.getCurrentSectorId() == getSegmentController().getSectorId()) PacketUtil.sendPacket(playerState, new SendAuraPulsePacket(getSegmentController(), radius, usableId));
			}
		}
	}

	public void updateTargetList() {
		ArrayList<SegmentController> toRemove = new ArrayList<>();
		for(Map.Entry<SegmentController, Boolean> entry : targetingEntities.entrySet()) {
			SegmentController target = entry.getKey();
			if(entry.getValue()) {
				for(ConfigEffectGroup configGroup : effectsToApply) target.getConfigManager().addEffectAndSend(configGroup, true, target.getNetworkObject());
				entry.setValue(false);
			}
			float distance = EntityUtils.getDistance(target, getSegmentController());
			if(distance > maxRange) toRemove.add(target);
		}
		//Remove invalid entries
		if(!toRemove.isEmpty()) {
			for(SegmentController target : toRemove) {
				if(target.getId() != getSegmentController().getId()) {
					for(ConfigEffectGroup configGroup : effectsToApply) target.getConfigManager().removeEffectAndSend(configGroup, true, target.getNetworkObject());
				}
			}
		}
		addEntityEffects(); //Add any new valid targets
	}

	public void addEntityEffects() {
		int targetCount = targetingEntities.size();
		Sector sector = GameServer.getUniverse().getSector(getSegmentController().getSectorId());
		if(sector != null) {
			for(SimpleTransformableSendableObject<?> object : sector.getEntities()) {
				if(object instanceof SegmentController) {
					SegmentController entity = (SegmentController) object;
					if(entity.getId() != getSegmentController().getId() && !entity.equals(getSegmentController())) {
						int currentFactionId = getSegmentController().getFactionId();
						int entityFactionId = entity.getFactionId();
						if(currentFactionId > 0 && entityFactionId > 0 && GameCommon.getGameState().getFactionManager().getRelation(currentFactionId, entityFactionId).equals(FactionRelation.RType.FRIEND)) {
							float distance = EntityUtils.getDistance(entity, getSegmentController());
							if(distance <= maxRange && targetCount < maxTargets && !targetingEntities.contains(entity)) {
								for(ConfigEffectGroup configGroup : effectsToApply) entity.getConfigManager().addEffectAndSend(configGroup, true, entity.getNetworkObject());
								targetingEntities.put(entity, false);
								targetCount++;
							} else if(targetCount >= maxTargets) return;
						}
					} else {
						for(ConfigEffectGroup configGroup : effectsToApply) entity.getConfigManager().removeEffectAndSend(configGroup, true, entity.getNetworkObject());
					}
				}
			}
		}
	}

	public void removeEntityEffects() {
		for(SegmentController entity : targetingEntities.keySet()) {
			if(entity.getId() != getSegmentController().getId()) {
				for(ConfigEffectGroup configGroup : effectsToApply) entity.getConfigManager().removeEffectAndSend(configGroup, true, entity.getNetworkObject());
			}
		}
	}

	public void disrupt(float hits, float disruptAmount) {
		if(isActive() && !targetingEntities.isEmpty()) {
			ticks = (int) (-100 * disruptAmount);
			if(getState() instanceof GameServerState) AudioUtils.serverPlaySound("0022_spaceship user - special synthetic weapon recharged 1", 10.0f, 1.0f, getAttachedPlayers());
			else {
				if(PlayerUtils.getCurrentControl(GameClient.getClientPlayerState()).equals(getSegmentController())) {
					((GameClientState) getState()).getWorldDrawer().getGuiDrawer().notifyEffectHit(getSegmentController(), EffectElementManager.OffensiveEffects.STOP);
				}
			}
			for(Map.Entry<SegmentController, Boolean> entry : targetingEntities.entrySet()) {
				SegmentController entity = entry.getKey();
				if(!entry.getValue()) { //Todo: Figure out how to change config effect values per entity?
					for(ConfigEffectGroup configGroup : effectsToApply) entity.getConfigManager().removeEffectAndSend(configGroup, true, entity.getNetworkObject());
					entry.setValue(true);
				}
			}
			/*
			for(Map.Entry<SegmentController, Boolean> entry : targetingEntities.entrySet()) {
				SegmentController entity = entry.getKey();
				if(!entry.getValue()) {
					for(EffectModule effectModule : entity.getConfigManager().getModulesList()) {
						if(projectorType == DEFENSE) {
							switch(effectModule.getType()) {
								case SHIELD_CAPACITY:
									effectModule.setFloatValue(effectModule.getFloatValue() * disruptAmount);
									break;
								case SHIELD_CAPACITY_UPKEEP:
									effectModule.setFloatValue(effectModule.getFloatValue() + (effectModule.getFloatValue() * disruptAmount));
									break;
							}
						}
						entry.setValue(true);
					}
				}

			 */

				/*
				for(ConfigGroup configGroup : entity.getConfigManager().getConfigPool().pool) {
					if(configGroup.id.contains("aura") && entity.getConfigManager().isActive(configGroup)) {
						for(EffectConfigElement element : configGroup.elements) {
							if(element.stackType.equals(ModifierStackType.ADD)) {
								if(element.value instanceof StatusEffectFloatValue) {
									StatusEffectFloatValue value = (StatusEffectFloatValue) element.value;
									value.value.set(value.getValue() * disruptAmount);
								}
							}
						}
					}
				}
				 */
		}
	}
}