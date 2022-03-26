package thederpgamer.betterchambers.element.block.systems.chambers.offense;

import api.config.BlockConfig;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.schine.graphicsengine.core.GraphicsContext;
import thederpgamer.betterchambers.element.block.Block;
import thederpgamer.betterchambers.manager.ResourceManager;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/25/2022]
 */
public class ReactorOffenseChamber extends Block {

	public ReactorOffenseChamber() {
		super("Reactor Offense Chamber", ElementKeyMap.getInfo(991).getType());
	}

	@Override
	public void initialize() {
		if(GraphicsContext.initialized) {
			blockInfo.setBuildIconNum(ResourceManager.getTexture("offense-chamber-icon").getTextureId());
			short textureId = (short) ResourceManager.getTexture("offense-chamber").getTextureId();
			blockInfo.setTextureId(new short[] {textureId, textureId, textureId, textureId, textureId, textureId});
		}
		blockInfo.setDescription("Reactor Chamber to enhance offensive capabilities.\nNeeds to be physically connected with a Power Reactor by using Reactor Conduit blocks.");
		blockInfo.setPlacable(true);
		blockInfo.canActivate = true;
		blockInfo.setInRecipe(true);
		blockInfo.shoppable = true;
		blockInfo.price = ElementKeyMap.getInfo(991).price;
		blockInfo.mass = ElementKeyMap.getInfo(991).mass;
		blockInfo.volume = ElementKeyMap.getInfo(991).volume;

		blockInfo.chamberPermission = 1;
		blockInfo.blockResourceType = 2;
		blockInfo.chamberGeneral = true;
		blockInfo.reactorGeneralIconIndex = 10;
		BlockConfig.add(blockInfo);
	}
}
