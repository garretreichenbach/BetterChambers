package thederpgamer.betterchambers.systems.chambers.support;

import api.common.GameCommon;
import api.common.GameServer;
import api.listener.events.systems.ReactorRecalibrateEvent;
import api.utils.addon.SimpleAddOn;
import api.utils.game.SegmentControllerUtils;
import api.utils.sound.AudioUtils;
import org.schema.game.common.controller.ManagedUsableSegmentController;
import org.schema.game.common.controller.elements.ManagerContainer;
import org.schema.game.common.controller.elements.power.reactor.tree.ReactorElement;
import org.schema.game.common.data.blockeffects.config.ConfigEntityManager;
import org.schema.game.common.data.blockeffects.config.StatusEffectType;
import org.schema.game.common.data.player.faction.FactionRelation;
import org.schema.game.common.data.world.Sector;
import org.schema.game.common.data.world.SimpleTransformableSendableObject;
import org.schema.game.server.data.ServerConfig;
import thederpgamer.betterchambers.BetterChambers;
import thederpgamer.betterchambers.effects.support.ShieldAuraEffectGroup;
import thederpgamer.betterchambers.element.ElementManager;
import thederpgamer.betterchambers.utils.EntityUtils;

import java.util.ArrayList;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/26/2022]
 */
public class ShieldAuraAddOn extends SimpleAddOn {

	public static final int UPDATE_TIMER = 300;

	private boolean usable = false;
	private int ticks = 0;
	private int maxTargets = 0;
	private float maxRange = 0.0f;
	//private float shieldCapBoost = 1.0f;
	//private float shieldUpkeepReduction = 0.0f;

	private final ArrayList<ManagedUsableSegmentController<?>> targetingEntities = new ArrayList<>();

	public ShieldAuraAddOn(ManagerContainer<?> managerContainer) {
		super(managerContainer, ElementManager.getBlock("Reactor Support Chamber").getId(), BetterChambers.getInstance(), "ShieldAuraAddOn");
		onReactorRecalibrate(null);
	}

	@Override
	public void onReactorRecalibrate(ReactorRecalibrateEvent event) {
		try {
			ReactorElement shieldAura = SegmentControllerUtils.getChamberFromElement(getManagerUsableSegmentController(), ElementManager.getChamber("Shield Aura Base").getBlockInfo());
			usable = shieldAura != null && shieldAura.isAllValid();
			ConfigEntityManager configManager = getManagerUsableSegmentController().getConfigManager();
			if(configManager.getModules().containsKey(StatusEffectType.AURA_MAX_TARGETS)) maxTargets = configManager.getModules().get(StatusEffectType.AURA_MAX_TARGETS).getIntValue();
			if(configManager.getModules().containsKey(StatusEffectType.AURA_RANGE)) maxRange = (configManager.getModules().get(StatusEffectType.AURA_RANGE).getFloatValue()) * (Integer) ServerConfig.SECTOR_SIZE.getCurrentState();
			//if(configManager.getModules().containsKey(StatusEffectType.AURA_SHIELD_CAP)) shieldCapBoost = configManager.getModules().get(StatusEffectType.AURA_SHIELD_CAP).getFloatValue();
			//if(configManager.getModules().containsKey(StatusEffectType.AURA_SHIELD_UPKEEP_REDUCTION)) shieldUpkeepReduction = configManager.getModules().get(StatusEffectType.AURA_SHIELD_UPKEEP_REDUCTION).getFloatValue();
		} catch (Exception ignored) { }
	}

	@Override
	public boolean isDeactivatableManually() {
		return isActive();
	}

	@Override
	public float getChargeRateFull() {
		return 15;
	}

	@Override
	public double getPowerConsumedPerSecondResting() {
		return 0;
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
			ticks = 0;
		} else ticks ++;
	}

	@Override
	public void onInactive() {
		if(ticks >= UPDATE_TIMER) {
			removeEntityEffects();
			targetingEntities.clear();
			onReactorRecalibrate(null);
			ticks = 0;
		} else ticks ++;
	}

	@Override
	public String getName() {
		return "Shield Boost Aura";
	}

	public void updateTargetList() {
		if(isOnServer()) {
			ArrayList<ManagedUsableSegmentController<?>> toRemove = new ArrayList<>();
			for(ManagedUsableSegmentController<?> target : targetingEntities) {
				float distance = EntityUtils.getDistance(target, getSegmentController());
				if(distance > maxRange) toRemove.add(target);
			}
			//Remove invalid entries
			if(!toRemove.isEmpty()) {
				for(ManagedUsableSegmentController<?> target : toRemove) {
					if(target.getId() != getSegmentController().getId()) {
						target.getConfigManager().removeEffectAndSend(ShieldAuraEffectGroup.ShieldAuraBaseEffect.instance, true, target.getNetworkObject());
					}
				}
			}

			addEntityEffects(); //Add any new valid targets
		}
	}

	public void addEntityEffects() {
		int targetCount = targetingEntities.size();
		Sector sector = GameServer.getUniverse().getSector(getSegmentController().getSectorId());
		if(sector != null) {
			for(SimpleTransformableSendableObject<?> object : sector.getEntities()) {
				if(object instanceof ManagedUsableSegmentController<?>) {
					ManagedUsableSegmentController<?> entity = (ManagedUsableSegmentController<?>) object;
					if(entity.getId() != getManagedSegmentController().getSegmentController().getId()) {
						int currentFactionId = getSegmentController().getFactionId();
						int entityFactionId = entity.getFactionId();
						if(currentFactionId > 0 && entityFactionId > 0 && GameCommon.getGameState().getFactionManager().getRelation(currentFactionId, entityFactionId).equals(FactionRelation.RType.FRIEND)) {
							float distance = EntityUtils.getDistance(entity, getSegmentController());
							if(distance <= maxRange && targetCount < maxTargets && !targetingEntities.contains(entity)) {
								entity.getConfigManager().addEffectAndSend(ShieldAuraEffectGroup.ShieldAuraBaseEffect.instance, true, entity.getNetworkObject());
								targetingEntities.add(entity);
								targetCount ++;
							} else if(targetCount >= maxTargets) return;
						}
					}
				}
			}
		}
	}

	public void removeEntityEffects() {
		Sector sector = GameServer.getUniverse().getSector(getSegmentController().getSectorId());
		if(sector != null) {
			for(ManagedUsableSegmentController<?> entity : targetingEntities) {
				if(entity.getId() != getSegmentController().getId()) {
					entity.getConfigManager().removeEffectAndSend(ShieldAuraEffectGroup.ShieldAuraBaseEffect.instance, true, entity.getNetworkObject());
				}
			}
		}
	}
}