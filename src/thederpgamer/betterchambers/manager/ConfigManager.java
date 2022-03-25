package thederpgamer.betterchambers.manager;

import api.mod.config.FileConfiguration;
import thederpgamer.betterchambers.BetterChambers;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/05/2022]
 */
public class ConfigManager {

	private static FileConfiguration mainConfig;
	private static final String[] defaultMainConfig = {
			"debug-mode: false",
			"max-world-logs: 5"
	};

	private static FileConfiguration systemConfig;
	private static final String[] defaultSystemConfig = {
			"ai-base-enhancement-chamber-turret-accuracy-multiplier: 0.2",
			"ai-base-enhancement-chamber-pd-accuracy-multiplier: 0.3"
	};

	public static void initialize(BetterChambers instance) {
		mainConfig = instance.getConfig("config");
		mainConfig.saveDefault(defaultMainConfig);

		systemConfig = instance.getConfig("system-config");
		systemConfig.saveDefault(defaultSystemConfig);
	}

	public static FileConfiguration getMainConfig() {
		return mainConfig;
	}

	public static FileConfiguration getSystemConfig() {
		return systemConfig;
	}
}
