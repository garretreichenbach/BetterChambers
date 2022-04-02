package thederpgamer.betterchambers.element.block.systems.weapons;

import api.config.BlockConfig;
import org.schema.game.common.data.element.ElementInformation;
import thederpgamer.betterchambers.BetterChambers;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/29/2022]
 */
public abstract class WeaponBlock {

	protected ElementInformation computerInfo;
	protected ElementInformation moduleInfo;

	public WeaponBlock(String computerName, String moduleName) {
		computerInfo = BlockConfig.newElement(BetterChambers.getInstance(), computerName, new short[6]);
		moduleInfo = BlockConfig.newElement(BetterChambers.getInstance(), moduleName, new short[6]);
	}

	public final ElementInformation getComputerInfo() {
		return computerInfo;
	}

	public final ElementInformation getModuleInfo() {
		return moduleInfo;
	}

	public final short getComputerId() {
		return computerInfo.getId();
	}

	public final short getModuleId() {
		return moduleInfo.getId();
	}

	public abstract void initializeComputer();
	public abstract void initializeModule();
}
