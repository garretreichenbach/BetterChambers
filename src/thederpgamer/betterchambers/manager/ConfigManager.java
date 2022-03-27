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
			"ai-base-enhancement-chamber-turret-accuracy-add: 0.2",
			"aura-base-chamber-range-set: 0.5",
			"aura-base-chamber-max-targets-set: 1",
			"aura-range-chamber-1-range-add: 0.5",
			"aura-range-chamber-2-range-add: 0.5",
			"aura-range-chamber-3-range-add: 0.5",
			"shield-boost-aura-chamber-capacity-add: 0.15",
			"shield-boost-aura-chamber-upkeep-reduction-add: 0.15"
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
