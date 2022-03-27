package thederpgamer.betterchambers.element.block.systems.chambers.defense;

import api.config.BlockConfig;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.schine.graphicsengine.core.GraphicsContext;
import thederpgamer.betterchambers.element.block.systems.chambers.ChamberBlock;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/26/2022]
 */
public class EnvironmentalArmorChamber3 extends ChamberBlock {

	public EnvironmentalArmorChamber3() {
		super("Environmental Armor 3", (short) 33, 0.10f);
	}

	@Override
	public void initialize() {
		if(GraphicsContext.initialized) {
			blockInfo.setBuildIconNum(ElementKeyMap.getInfo(33).getBuildIconNum());
			blockInfo.setTextureId(new short[] {644, 644, 644, 644, 644, 644});
		}
		blockInfo.setDescription("Grants your armor blocks complete immunity from any environmental based damage.");
		blockInfo.setPlacable(false);
		blockInfo.setInRecipe(false);
		blockInfo.reactorHp = 20;
		blockInfo.shoppable = false;
		blockInfo.chamberParent = 33;
		blockInfo.chamberConfigGroupsLowerCase.add("environmental_armor_3_effect");
		ElementKeyMap.getInfo(33).chamberChildren.add(getId());
		ElementKeyMap.getInfo(33).chamberUpgradesTo = getId();
		BlockConfig.add(blockInfo);
	}
}
