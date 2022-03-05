package thederpgamer.betterchambers.element;

import api.config.BlockConfig;
import org.apache.commons.lang3.StringUtils;
import org.schema.game.common.data.element.ElementCategory;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import thederpgamer.betterchambers.element.block.Block;
import thederpgamer.betterchambers.element.block.systems.chambers.ChamberBlock;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/05/2022]
 */
public class ElementManager {

	public enum FactoryType {NONE, CAPSULE_REFINERY, MICRO_ASSEMBLER, BASIC_FACTORY, STANDARD_FACTORY, ADVANCED_FACTORY}

	private static ArrayList<Block> blockList = new ArrayList<>();
	private static ArrayList<ChamberBlock> chamberList = new ArrayList<>();
	public static HashMap<String, Integer> factories = new HashMap<>();

	public static void initialize() {
		for(Block blockElement : blockList) blockElement.initialize();
		for(ChamberBlock chamber : chamberList) chamber.initialize();
	}

	public static ArrayList<Block> getAllBlocks() {
		return blockList;
	}

	public static ArrayList<ChamberBlock> getChamberList() {
		return chamberList;
	}

	public static Block getBlock(short id) {
		for(Block blockElement : getAllBlocks()) if(blockElement.getBlockInfo().getId() == id) return blockElement;
		return null;
	}

	public static Block getBlock(String blockName) {
		for(Block block : getAllBlocks()) {
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

	public static void addBlock(Block block) {
		blockList.add(block);
	}

	public static void addChamber(ChamberBlock chamber) {
		chamberList.add(chamber);
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

	public static ElementInformation getInfo(String name) {
		Block block = getBlock(name);
		if(block != null) return block.getBlockInfo();
		else {
			for(ElementInformation elementInfo : ElementKeyMap.getInfoArray()) {
				if(elementInfo.getName().equalsIgnoreCase(name)) return elementInfo;
			}
		}
		return null;
	}
}
