package thederpgamer.betterchambers.element.block.systems.chambers;

import api.config.BlockConfig;
import org.schema.game.common.data.blockeffects.config.StatusEffectType;
import org.schema.game.common.data.element.ElementInformation;
import thederpgamer.betterchambers.BetterChambers;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/05/2022]
 */
public abstract class ChamberBlock {

	protected ElementInformation blockInfo;

	public ChamberBlock(String name, short generalId, float chamberCapacity, StatusEffectType... statusEffects) {
		blockInfo = BlockConfig.newChamber(BetterChambers.getInstance(), name, generalId, statusEffects);
		blockInfo.chamberCapacity = chamberCapacity;
		//blockInfo.setBlockStyle(BlockStyle.NORMAL.id);
	}

	public ElementInformation getBlockInfo() {
		return blockInfo;
	}

	public short getId() {
		return blockInfo.getId();
	}

	public void addChildren(ChamberBlock... children) {
		for(ChamberBlock child : children) {
			child.blockInfo.chamberParent = getId();
			child.blockInfo.chamberPrerequisites.add(getId());
			blockInfo.chamberChildren.add(child.getId());
		}
	}

	public void setUpgrade(ChamberBlock upgrade) {
		addChildren(upgrade);
		upgrade.blockInfo.chamberMutuallyExclusive.addAll(blockInfo.chamberMutuallyExclusive);
		blockInfo.chamberUpgradesTo = upgrade.getId();
	}

	public void addExclusives(ChamberBlock... exclusives) {
		for(ChamberBlock exclusive : exclusives) {
			exclusive.blockInfo.chamberMutuallyExclusive.add(getId());
			blockInfo.chamberMutuallyExclusive.add(exclusive.getId());
		}
	}

	public abstract void initialize();
}
