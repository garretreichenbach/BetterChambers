package thederpgamer.betterchambers.element.block.systems.chambers.offense;

import api.config.BlockConfig;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.element.FactoryResource;
import org.schema.schine.graphicsengine.core.GraphicsContext;
import thederpgamer.betterchambers.element.block.Block;
import thederpgamer.betterchambers.manager.ResourceManager;

import java.util.ArrayList;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/25/2022]
 */
public class ReactorOffenseChamber extends Block {

	public ReactorOffenseChamber() {
		super("Reactor Offense Chamber");
	}

	@Override
	public void initialize() {
		if(GraphicsContext.initialized) {
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

		BlockConfig.addRecipe(blockInfo, ElementKeyMap.getInfo(991).getProducedInFactoryType(), (int) ElementKeyMap.getInfo(991).getFactoryBakeTime(), (new ArrayList<>(ElementKeyMap.getInfo(991).getConsistence()).toArray(new FactoryResource[1])));
		BlockConfig.setElementCategory(blockInfo, ElementKeyMap.getInfo(991).getType());
	}
}
