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
		super("Environmental Armor 3", (short) 1046, 0.10f);
	}

	@Override
	public void initialize() {
		if(GraphicsContext.initialized) {
			blockInfo.setBuildIconNum(ElementKeyMap.getInfo(33).getBuildIconNum());
			blockInfo.setTextureId(new short[] {644, 644, 644, 644, 644, 644});
		}
		blockInfo.setDescription("Grants complete immunity to environmental (star) damage.");
		blockInfo.setPlacable(false);
		blockInfo.setInRecipe(false);
		blockInfo.shoppable = false;
		blockInfo.chamberPrerequisites.clear();
		blockInfo.chamberPrerequisites.add((short) 33);
		blockInfo.chamberConfigGroupsLowerCase.add("environmental_armor_3_effect");
		ElementKeyMap.getInfo(33).chamberChildren.add(getId());
		BlockConfig.add(blockInfo);
	}
}
