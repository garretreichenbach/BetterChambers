package thederpgamer.betterchambers;

import api.common.GameClient;
import api.config.BlockConfig;
import api.listener.Listener;
import api.listener.events.input.KeyPressEvent;
import api.listener.events.register.RegisterAddonsEvent;
import api.listener.events.register.RegisterConfigGroupsEvent;
import api.mod.StarLoader;
import api.mod.StarMod;
import api.network.packets.PacketUtil;
import api.utils.game.PlayerUtils;
import api.utils.registry.UniversalRegistry;
import api.utils.textures.GraphicsOperator;
import api.utils.textures.StarLoaderTexture;
import org.apache.commons.io.IOUtils;
import org.schema.game.common.controller.ManagedUsableSegmentController;
import org.schema.schine.input.KeyboardMappings;
import org.schema.schine.resource.ResourceLoader;
import thederpgamer.betterchambers.effects.defense.EnvironmentalArmor3Effect;
import thederpgamer.betterchambers.effects.offense.AIEffectGroup;
import thederpgamer.betterchambers.effects.support.AuraEffectGroup;
import thederpgamer.betterchambers.effects.support.OffenseAuraEffectGroup;
import thederpgamer.betterchambers.effects.support.ShieldAuraEffectGroup;
import thederpgamer.betterchambers.element.ElementManager;
import thederpgamer.betterchambers.element.block.systems.chambers.defense.EnvironmentalArmorChamber3;
import thederpgamer.betterchambers.element.block.systems.chambers.offense.AIChamber;
import thederpgamer.betterchambers.element.block.systems.chambers.offense.ReactorOffenseChamber;
import thederpgamer.betterchambers.element.block.systems.chambers.support.AuraChamber;
import thederpgamer.betterchambers.element.block.systems.chambers.support.ReactorSupportChamber;
import thederpgamer.betterchambers.manager.ConfigManager;
import thederpgamer.betterchambers.manager.LogManager;
import thederpgamer.betterchambers.manager.ResourceManager;
import thederpgamer.betterchambers.network.client.SendThrustBlastPacket;
import thederpgamer.betterchambers.systems.chambers.support.OffenseAuraAddOn;
import thederpgamer.betterchambers.systems.chambers.support.ShieldAuraAddOn;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/05/2022]
 */
public class BetterChambers extends StarMod {

	//Instance
	private static BetterChambers instance;
	public static BetterChambers getInstance() {
		return instance;
	}
	public static void main(String[] args) {

	}
	public BetterChambers() {

	}

	//Other
	private final String[] overwriteClasses = {
			"EffectAddOn",
			"StatusEffectCategory",
			"StatusEffectType"
	};
	public static long lastInputMs = 0;
	public static int lastInput = -1;
	public static final KeyboardMappings[] movementKeys = {
			KeyboardMappings.FORWARD_SHIP, KeyboardMappings.BACKWARDS_SHIP,
			KeyboardMappings.UP_SHIP, KeyboardMappings.DOWN_SHIP,
			KeyboardMappings.STRAFE_LEFT_SHIP, KeyboardMappings.STRAFE_RIGHT_SHIP
	};

	@Override
	public void onEnable() {
		instance = this;
		ConfigManager.initialize(this);
		LogManager.initialize();
		registerListeners();
		registerPackets();
		overwriteTextures();
	}

	@Override
	public byte[] onClassTransform(String className, byte[] byteCode) {
		for (String name : overwriteClasses) if (className.endsWith(name)) return overwriteClass(className, byteCode);
		return super.onClassTransform(className, byteCode);
	}

	@Override
	public void onResourceLoad(ResourceLoader loader) {
		ResourceManager.loadResources();
	}

	@Override
	public void onBlockConfigLoad(BlockConfig config) {
		ElementManager.addBlock(new ReactorOffenseChamber());
		ElementManager.addBlock(new ReactorSupportChamber());
		ElementManager.addChamber(new AIChamber.AIEnhancementChamber());
		ElementManager.addChamber(new AuraChamber.AuraRangeBoostChamber1());
		ElementManager.addChamber(new AuraChamber.AuraRangeBoostChamber2());
		ElementManager.addChamber(new AuraChamber.AuraRangeBoostChamber3());
		ElementManager.addChamber(new AuraChamber.ShieldAuraBaseChamber());
		ElementManager.addChamber(new AuraChamber.ShieldAuraCapacityChamber1());
		ElementManager.addChamber(new AuraChamber.ShieldAuraCapacityChamber2());
		ElementManager.addChamber(new AuraChamber.OffenseAuraBaseChamber());
		ElementManager.addChamber(new AuraChamber.OffenseAuraTargetingChamber1());
		ElementManager.addChamber(new AuraChamber.OffenseAuraTargetingChamber2());
		ElementManager.addChamber(new EnvironmentalArmorChamber3());
		ElementManager.initialize();
	}

	@Override
	public void onUniversalRegistryLoad() {
		UniversalRegistry.registerURV(UniversalRegistry.RegistryType.PLAYER_USABLE_ID, getSkeleton(), "ShieldAuraAddOn");
		UniversalRegistry.registerURV(UniversalRegistry.RegistryType.PLAYER_USABLE_ID, getSkeleton(), "OffenseAuraAddOn");
	}

	private void registerListeners() {
		StarLoader.registerListener(RegisterConfigGroupsEvent.class, new Listener<RegisterConfigGroupsEvent>() {
			@Override
			public void onEvent(RegisterConfigGroupsEvent event) {
				event.getModConfigGroups().enqueue(new AIEffectGroup.AIBaseEnhancementEffect());
				event.getModConfigGroups().enqueue(new AuraEffectGroup.AuraBaseEffect());
				event.getModConfigGroups().enqueue(new AuraEffectGroup.AuraRangeBoostEffect1());
				event.getModConfigGroups().enqueue(new AuraEffectGroup.AuraRangeBoostEffect2());
				event.getModConfigGroups().enqueue(new AuraEffectGroup.AuraRangeBoostEffect3());
				event.getModConfigGroups().enqueue(new ShieldAuraEffectGroup.ShieldAuraBaseEffect());
				event.getModConfigGroups().enqueue(new ShieldAuraEffectGroup.ShieldAuraCapacity1Effect());
				event.getModConfigGroups().enqueue(new ShieldAuraEffectGroup.ShieldAuraCapacity2Effect());
				event.getModConfigGroups().enqueue(new OffenseAuraEffectGroup.OffenseAuraBaseEffect());
				event.getModConfigGroups().enqueue(new OffenseAuraEffectGroup.OffenseAuraTargeting1Effect());
				event.getModConfigGroups().enqueue(new OffenseAuraEffectGroup.OffenseAuraTargeting2Effect());
				event.getModConfigGroups().enqueue(new EnvironmentalArmor3Effect());
			}
		}, this);

		StarLoader.registerListener(RegisterAddonsEvent.class, new Listener<RegisterAddonsEvent>() {
			@Override
			public void onEvent(RegisterAddonsEvent event) {
				event.addModule(new ShieldAuraAddOn(event.getContainer()));
				event.addModule(new OffenseAuraAddOn(event.getContainer()));
			}
		}, this);

		StarLoader.registerListener(KeyPressEvent.class, new Listener<KeyPressEvent>() {
			@Override
			public void onEvent(KeyPressEvent event) {
				int key = KeyboardMappings.getEventKeySingle(event.getRawEvent());
				if(lastInput == key) {
					if(System.currentTimeMillis() - lastInputMs < 150 && lastInputMs > 0) {
						if(GameClient.getClientState() != null  && PlayerUtils.getCurrentControl(GameClient.getClientPlayerState()) instanceof ManagedUsableSegmentController<?> && GameClient.getClientState().isInFlightMode()) {
							for(KeyboardMappings keyboardMapping : movementKeys) {
								if(keyboardMapping.getMapping() == key && keyboardMapping.isDown(GameClient.getClientState())) {
									PacketUtil.sendPacketToServer(new SendThrustBlastPacket((ManagedUsableSegmentController<?>) PlayerUtils.getCurrentControl(GameClient.getClientPlayerState())));
									break;
								}
							}
						}
					}
					lastInputMs = System.currentTimeMillis();
				}
				lastInput = key;
			}
		}, this);
	}

	private void registerPackets() {
		PacketUtil.registerPacket(SendThrustBlastPacket.class);
	}

	private void overwriteTextures() {
		StarLoaderTexture.addSpriteChange("gui/ingame/UI 64px ChamberTabs-4x4-gui-", new GraphicsOperator() {
			@Override
			public void apply(BufferedImage bufferedImage, Graphics graphics) {
				bufferedImage.setData(getJarBufferedImage("thederpgamer/betterchambers/resources/sprites/reactor-chamber-tab-icons.png").getData());
			}
		});
	}

	private byte[] overwriteClass(String className, byte[] byteCode) {
		byte[] bytes = null;
		try {
			ZipInputStream file =
					new ZipInputStream(new FileInputStream(this.getSkeleton().getJarFile()));
			while (true) {
				ZipEntry nextEntry = file.getNextEntry();
				if (nextEntry == null) break;
				if (nextEntry.getName().endsWith(className + ".class")) bytes = IOUtils.toByteArray(file);
			}
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (bytes != null) return bytes;
		else return byteCode;
	}
}
