package thederpgamer.betterchambers.element;

import api.config.BlockConfig;
import org.apache.commons.lang3.StringUtils;
import org.schema.game.common.data.element.ElementCategory;
import org.schema.game.common.data.element.ElementKeyMap;
import thederpgamer.betterchambers.element.block.Block;
import thederpgamer.betterchambers.element.block.systems.chambers.ChamberBlock;
import thederpgamer.betterchambers.element.block.systems.weapons.WeaponBlock;

import java.util.ArrayList;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/05/2022]
 */
public class ElementManager {

	public enum FactoryType {NONE, CAPSULE_REFINERY, MICRO_ASSEMBLER, BASIC_FACTORY, STANDARD_FACTORY, ADVANCED_FACTORY}

	private static final ArrayList<Block> blockList = new ArrayList<>();
	private static final ArrayList<ChamberBlock> chamberList = new ArrayList<>();
	private static final ArrayList<WeaponBlock> weaponList = new ArrayList<>();

	public static void initialize() {
		for(Block blockElement : blockList) blockElement.initialize();
		for(ChamberBlock chamberElement : chamberList) chamberElement.initialize();
		for(WeaponBlock weaponElement : weaponList) {
			weaponElement.initializeComputer();
			weaponElement.initializeModule();
			BlockConfig.registerComputerModulePair(weaponElement.getComputerId(), weaponElement.getModuleId());
		}
	}

	public static ArrayList<Block> getBlockList() {
		return blockList;
	}

	public static ArrayList<ChamberBlock> getChamberList() {
		return chamberList;
	}

	public static ArrayList<WeaponBlock> getWeaponList() {
		return weaponList;
	}

	public static Block getBlock(short id) {
		for(Block blockElement : getBlockList()) if(blockElement.getBlockInfo().getId() == id) return blockElement;
		return null;
	}

	public static Block getBlock(String blockName) {
		for(Block block : getBlockList()) {
			if(block.getBlockInfo().getName().equalsIgnoreCase(blockName)) return block;
		}
		return null;
	}

	public static ChamberBlock getChamber(short id) {
		for(ChamberBlock chamber : getChamberList()) {
			if(chamber.getBlockInfo().getId() == id) return chamber;
		}
		return null;
	}

	public static ChamberBlock getChamber(String name) {
		for(ChamberBlock chamber : getChamberList()) {
			if(chamber.getBlockInfo().getName().equalsIgnoreCase(name)) return chamber;
		}
		return null;
	}

	public static WeaponBlock getWeapon(short id) {
		for(WeaponBlock weapon : getWeaponList()) {
			if(weapon.getComputerId() == id || weapon.getModuleId() == id) return weapon;
		}
		return null;
	}

	public static WeaponBlock getWeapon(String name) {
		for(WeaponBlock weapon : getWeaponList()) {
			if(weapon.getComputerInfo().getName().equalsIgnoreCase(name) || weapon.getModuleInfo().getName().equalsIgnoreCase(name)) return weapon;
		}
		return null;
	}

	public static void addBlock(Block block) {
		blockList.add(block);
	}

	public static void addChamber(ChamberBlock chamber) {
		chamberList.add(chamber);
	}

	public static void addWeapon(WeaponBlock weapon) {
		weaponList.add(weapon);
	}

	public static ElementCategory getCategory(String path) {
		String[] split = path.split("\\.");
		ElementCategory category = ElementKeyMap.getCategoryHirarchy();
		for(String s : split) {
			boolean createNew = false;
			if(category.hasChildren()) {
				for(ElementCategory child : category.getChildren()) {
					if(child.getCategory().equalsIgnoreCase(s)) {
						category = child;
						break;
					}
					createNew = true;
				}
			} else createNew = true;
			if(createNew) category = BlockConfig.newElementCategory(category, StringUtils.capitalize(s));
		}
		return category;
	}
}
