package thederpgamer.betterchambers.manager;

import api.utils.textures.StarLoaderTexture;
import org.schema.schine.graphicsengine.forms.Sprite;
import thederpgamer.betterchambers.BetterChambers;

import java.util.HashMap;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/05/2022]
 */
public class ResourceManager {

	private static final String[] textureNames = {
			"offense-chamber",
			"offense-chamber-active",
			"support-chamber",
			"support-chamber-active",
			"aura-disruptor-computer-front",
			"aura-disruptor-computer-side",
			"aura-disruptor-module-front",
			"aura-disruptor-module-side",
			"aura-disruptor-module-top"
	};

	private static final String[] spriteNames = {
			"reactor-chamber-tab-icons"
	};

	private static final HashMap<String, StarLoaderTexture> textureMap = new HashMap<>();
	private static final HashMap<String, Sprite> spriteMap = new HashMap<>();

	public static void loadResources() {
		StarLoaderTexture.runOnGraphicsThread(new Runnable() {
			@Override
			public void run() {
				//Load Textures
				for(String texturePath : textureNames) {
					String textureName = texturePath.substring(texturePath.lastIndexOf('/') + 1);
					try {
						if(textureName.endsWith("icon")) {
							textureMap.put(textureName, StarLoaderTexture.newIconTexture(BetterChambers.getInstance().getJarBufferedImage("thederpgamer/betterchambers/resources/textures/" + texturePath + ".png")));
						} else {
							textureMap.put(textureName, StarLoaderTexture.newBlockTexture(BetterChambers.getInstance().getJarBufferedImage("thederpgamer/betterchambers/resources/textures/" + texturePath + ".png")));
						}
					} catch(Exception exception) {
						LogManager.logException("Failed to load texture \"" + texturePath + "\"", exception);
					}
				}

				//Load Sprites
				for(String spriteName : spriteNames) {
					try {
						spriteMap.put(spriteName, StarLoaderTexture.newSprite(BetterChambers.getInstance().getJarBufferedImage("thederpgamer/betterchambers/resources/sprites/" + spriteName + ".png"), BetterChambers.getInstance(), spriteName, false, false));
						spriteMap.get(spriteName).setName(spriteName);
					} catch(Exception exception) {
						LogManager.logException("Failed to load sprite \"" + spriteName + "\"", exception);
					}
				}
			}
		});
	}

	public static StarLoaderTexture getTexture(String name) {
		return textureMap.get(name);
	}

	public static Sprite getSprite(String name) {
		return spriteMap.get(name);
	}
}
