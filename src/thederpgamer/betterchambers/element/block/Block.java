package thederpgamer.betterchambers.element.block;

import api.config.BlockConfig;
import org.schema.game.common.data.element.ElementInformation;
import thederpgamer.betterchambers.BetterChambers;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/05/2022]
 */
public abstract class Block {

	protected ElementInformation blockInfo;

	public Block(String name) {
		blockInfo = BlockConfig.newElement(BetterChambers.getInstance(), name, new short[6]);
		//blockInfo.setBlockStyle(BlockStyle.NORMAL.id);
	}

	public final ElementInformation getBlockInfo() {
		return blockInfo;
	}

	public final short getId() {
		return blockInfo.getId();
	}

	public abstract void initialize();
}