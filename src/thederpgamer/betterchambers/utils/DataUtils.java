package thederpgamer.betterchambers.utils;

import api.common.GameClient;
import api.common.GameCommon;
import thederpgamer.betterchambers.BetterChambers;
import thederpgamer.betterchambers.manager.LogManager;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/05/2022]
 */
public class DataUtils {

	public static String getResourcesPath() {
		return BetterChambers.getInstance().getSkeleton().getResourcesFolder().getPath().replace('\\', '/');
	}

	public static String getWorldDataPath() {
		String universeName = GameCommon.getUniqueContextId();
		if(!universeName.contains(":")) return getResourcesPath() + "/data/" + universeName;
		else {
			try {
				LogManager.logWarning("Client " + GameClient.getClientPlayerState().getName() + " attempted to illegally access server data.", null);
			} catch(Exception ignored) { }
			return null;
		}
	}
}
