package thederpgamer.betterchambers;

import api.common.GameClient;
import api.config.BlockConfig;
import api.listener.Listener;
import api.listener.events.input.KeyPressEvent;
import api.mod.StarLoader;
import api.mod.StarMod;
import api.utils.element.Blocks;
import api.utils.game.PlayerUtils;
import api.utils.game.SegmentControllerUtils;
import org.apache.commons.io.IOUtils;
import org.schema.game.client.data.PlayerControllable;
import org.schema.game.common.controller.ManagedUsableSegmentController;
import org.schema.game.common.controller.elements.RecharchableSingleModule;
import org.schema.game.common.controller.elements.power.reactor.tree.ReactorElement;
import org.schema.schine.input.KeyboardMappings;
import org.schema.schine.resource.ResourceLoader;
import thederpgamer.betterchambers.element.ElementManager;
import thederpgamer.betterchambers.manager.ConfigManager;
import thederpgamer.betterchambers.manager.LogManager;
import thederpgamer.betterchambers.manager.ResourceManager;

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
			"EffectAddOn"
	};
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
		ElementManager.initialize();
	}

	private void registerListeners() {
		StarLoader.registerListener(KeyPressEvent.class, new Listener<KeyPressEvent>() {
			@Override
			public void onEvent(KeyPressEvent event) {
				int key = KeyboardMappings.getEventKeySingle(event.getRawEvent());
				if(lastInput != -1 && lastInput == key) {
					for(KeyboardMappings keyboardMapping : movementKeys) {
						if(keyboardMapping.getMapping() == key && keyboardMapping.isDown(GameClient.getClientState())) {
							PlayerControllable object = PlayerUtils.getCurrentControl(GameClient.getClientPlayerState());
							if(object instanceof ManagedUsableSegmentController && GameClient.getClientState().isInFlightMode()) {
								ManagedUsableSegmentController<?> segmentController = (ManagedUsableSegmentController<?>) object;
								ReactorElement reactorElement = SegmentControllerUtils.getChamberFromElement(segmentController, Blocks.THRUST_BLAST_1.getInfo());
								if(reactorElement != null && reactorElement.isAllValid()) {
									RecharchableSingleModule module = segmentController.getManagerContainer().getEffectAddOnManager().get(reactorElement.getId());
									if(module.getMaxCharges() > 1 && module.getCharges() < module.getMaxCharges()) module.setAutoChargeOn(true);
									if(module.canExecute()) module.executeModule();
									break;
								} else return;
							} else return;
						}
					}
				}
				lastInput = key;
			}
		}, this);
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
