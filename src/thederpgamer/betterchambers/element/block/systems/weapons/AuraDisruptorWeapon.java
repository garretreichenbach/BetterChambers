package thederpgamer.betterchambers.element.block.systems.weapons;

import api.config.BlockConfig;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.element.FactoryResource;
import org.schema.schine.graphicsengine.core.GraphicsContext;
import thederpgamer.betterchambers.manager.ResourceManager;

import java.util.ArrayList;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/29/2022]
 */
public class AuraDisruptorWeapon extends WeaponBlock {

	public AuraDisruptorWeapon() {
		super("Aura Disruptor Computer", "Aura Disruptor Module", ElementKeyMap.getInfo(4).getType());
	}

	@Override
	public void initializeComputer() {
		if(GraphicsContext.initialized) {
			//computerInfo.setBuildIconNum(ResourceManager.getTexture("aura-disruptor-computer-icon").getTextureId());
			short frontTexture = (short) ResourceManager.getTexture("aura-disruptor-computer-front").getTextureId();
			short backTexture = ElementKeyMap.getInfo(4).getTextureId(0);
			short sideTexture = (short) ResourceManager.getTexture("aura-disruptor-computer-side").getTextureId();
			short topTexture = ElementKeyMap.getInfo(4).getTextureId(2);
			short bottomTexture = ElementKeyMap.getInfo(4).getTextureId(3);
			computerInfo.setTextureId(new short[] {backTexture, frontTexture, topTexture, bottomTexture, sideTexture, sideTexture});
		}
		computerInfo.setDescription("Used to control Aura Disruptor Systems. Simply aim and fire at the enemy ship that is projecting the aura, and the effects of the aura will be reduced based on the size of the disruptor system relative to the strength of the aura itself.");
		computerInfo.setPlacable(true);
		computerInfo.canActivate = true;
		computerInfo.setInRecipe(true);
		computerInfo.shoppable = true;
		computerInfo.price = ElementKeyMap.getInfo(4).price;
		computerInfo.mass = ElementKeyMap.getInfo(4).mass;
		computerInfo.volume = ElementKeyMap.getInfo(4).volume;

		BlockConfig.addRecipe(computerInfo, ElementKeyMap.getInfo(4).getProducedInFactoryType(), (int) ElementKeyMap.getInfo(4).getFactoryBakeTime(), (new ArrayList<>(ElementKeyMap.getInfo(4).getConsistence()).toArray(new FactoryResource[1])));
		BlockConfig.add(computerInfo);
	}

	@Override
	public void initializeModule() {
		if(GraphicsContext.initialized) {
			//moduleInfo.setBuildIconNum(ResourceManager.getTexture("aura-disruptor-module-icon").getTextureId());
			short frontTexture = (short) ResourceManager.getTexture("aura-disruptor-module-front").getTextureId();
			short sideTexture = (short) ResourceManager.getTexture("aura-disruptor-module-side").getTextureId();
			short topTexture = (short) ResourceManager.getTexture("aura-disruptor-module-top").getTextureId();
			moduleInfo.setTextureId(new short[] {frontTexture, sideTexture, topTexture, topTexture, sideTexture, sideTexture});
		}
		moduleInfo.setDescription("Acts as an output for Aura Disruptor Systems. Link to an Aura Disruptor Computer to use.");
		moduleInfo.setPlacable(true);
		moduleInfo.canActivate = true;
		moduleInfo.setInRecipe(true);
		moduleInfo.shoppable = true;
		moduleInfo.price = ElementKeyMap.getInfo(24).price;
		moduleInfo.mass = ElementKeyMap.getInfo(24).mass;
		moduleInfo.volume = ElementKeyMap.getInfo(24).volume;

		BlockConfig.addRecipe(moduleInfo, ElementKeyMap.getInfo(24).getProducedInFactoryType(), (int) ElementKeyMap.getInfo(24).getFactoryBakeTime(), (new ArrayList<>(ElementKeyMap.getInfo(24).getConsistence()).toArray(new FactoryResource[1])));
		BlockConfig.add(moduleInfo);
	}
}
