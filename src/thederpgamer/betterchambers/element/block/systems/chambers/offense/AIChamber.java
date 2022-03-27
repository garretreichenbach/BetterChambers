package thederpgamer.betterchambers.element.block.systems.chambers.offense;

import api.config.BlockConfig;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.schine.graphicsengine.core.GraphicsContext;
import thederpgamer.betterchambers.element.ElementManager;
import thederpgamer.betterchambers.element.block.systems.chambers.ChamberBlock;
import thederpgamer.betterchambers.manager.ResourceManager;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/06/2022]
 */
public class AIChamber {

	public static class AIEnhancementChamber extends ChamberBlock {

		public AIEnhancementChamber() {
			super("AI Base Enhancement", (short) 991, 0.05f);
		}

		@Override
		public void initialize() {
			if(GraphicsContext.initialized) {
				blockInfo.setBuildIconNum(ResourceManager.getTexture("offense-chamber-icon").getTextureId());
				short textureId = (short) ResourceManager.getTexture("offense-chamber").getTextureId();
				blockInfo.setTextureId(new short[] {textureId, textureId, textureId, textureId, textureId, textureId});
			}
			blockInfo.setDescription("Base increase for AI turret accuracy.");
			blockInfo.setPlacable(false);
			blockInfo.setInRecipe(false);
			blockInfo.reactorHp = 20;
			blockInfo.shoppable = false;
			blockInfo.chamberRoot = ElementManager.getBlock("Reactor Offense Chamber").getId();
			blockInfo.chamberConfigGroupsLowerCase.add("ai_base_enhancement_effect");
			ElementManager.getBlock("Reactor Offense Chamber").getBlockInfo().chamberChildren.add(getId());
			ElementKeyMap.getInfo(991).chamberChildren.remove(getId());
			BlockConfig.add(blockInfo);
		}
	}
}
