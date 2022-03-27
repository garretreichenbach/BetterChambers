package thederpgamer.betterchambers.element.block.systems.chambers.support;

import api.config.BlockConfig;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.schine.graphicsengine.core.GraphicsContext;
import thederpgamer.betterchambers.element.ElementManager;
import thederpgamer.betterchambers.element.block.systems.chambers.ChamberBlock;
import thederpgamer.betterchambers.manager.ResourceManager;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/26/2022]
 */
public class AuraChamber {

	public static class AuraRangeBoostChamber1 extends ChamberBlock {

		public AuraRangeBoostChamber1() {
			super("Aura Range Boost 1", (short) 991, 0.05f);
		}

		@Override
		public void initialize() {
			if(GraphicsContext.initialized) {
				blockInfo.setBuildIconNum(ResourceManager.getTexture("support-chamber-icon").getTextureId());
				short textureId = (short) ResourceManager.getTexture("support-chamber").getTextureId();
				blockInfo.setTextureId(new short[] {textureId, textureId, textureId, textureId, textureId, textureId});
			}
			blockInfo.setDescription("Boosts the maximum range of active auras.");
			blockInfo.setPlacable(false);
			blockInfo.setInRecipe(false);
			blockInfo.reactorHp = 20;
			blockInfo.shoppable = false;
			blockInfo.chamberConfigGroupsLowerCase.add("aura_range_boost_effect_1");
			blockInfo.chamberRoot = ElementManager.getBlock("Reactor Support Chamber").getId();
			ElementManager.getBlock("Reactor Support Chamber").getBlockInfo().chamberChildren.add(getId());
			ElementKeyMap.getInfo(991).chamberChildren.remove(getId());
			BlockConfig.add(blockInfo);
		}
	}

	public static class AuraRangeBoostChamber2 extends ChamberBlock {

		public AuraRangeBoostChamber2() {
			super("Aura Range Boost 2", (short) 991, 0.10f);
		}

		@Override
		public void initialize() {
			if(GraphicsContext.initialized) {
				blockInfo.setBuildIconNum(ResourceManager.getTexture("support-chamber-icon").getTextureId());
				short textureId = (short) ResourceManager.getTexture("support-chamber").getTextureId();
				blockInfo.setTextureId(new short[] {textureId, textureId, textureId, textureId, textureId, textureId});
			}
			blockInfo.setDescription("Boosts the maximum range of active auras.");
			blockInfo.setPlacable(false);
			blockInfo.setInRecipe(false);
			blockInfo.reactorHp = 20;
			blockInfo.shoppable = false;
			blockInfo.chamberConfigGroupsLowerCase.add("aura_range_boost_effect_2");
			blockInfo.chamberParent = ElementManager.getChamber("Aura Range Boost 1").getId();
			blockInfo.chamberRoot = ElementManager.getBlock("Reactor Support Chamber").getId();
			ElementManager.getChamber("Aura Range Boost 1").getBlockInfo().chamberChildren.add(getId());
			ElementManager.getChamber("Aura Range Boost 1").getBlockInfo().chamberUpgradesTo = getId();
			ElementKeyMap.getInfo(991).chamberChildren.remove(getId());
			BlockConfig.add(blockInfo);
		}
	}

	public static class AuraRangeBoostChamber3 extends ChamberBlock {

		public AuraRangeBoostChamber3() {
			super("Aura Range Boost 3", (short) 991, 0.10f);
		}

		@Override
		public void initialize() {
			if(GraphicsContext.initialized) {
				blockInfo.setBuildIconNum(ResourceManager.getTexture("support-chamber-icon").getTextureId());
				short textureId = (short) ResourceManager.getTexture("support-chamber").getTextureId();
				blockInfo.setTextureId(new short[] {textureId, textureId, textureId, textureId, textureId, textureId});
			}
			blockInfo.setDescription("Boosts the maximum range of active auras.");
			blockInfo.setPlacable(false);
			blockInfo.setInRecipe(false);
			blockInfo.reactorHp = 20;
			blockInfo.shoppable = false;
			blockInfo.chamberConfigGroupsLowerCase.add("aura_range_boost_effect_3");
			blockInfo.chamberParent = ElementManager.getChamber("Aura Range Boost 2").getId();
			blockInfo.chamberRoot = ElementManager.getBlock("Reactor Support Chamber").getId();
			ElementManager.getChamber("Aura Range Boost 2").getBlockInfo().chamberChildren.add(getId());
			ElementManager.getChamber("Aura Range Boost 2").getBlockInfo().chamberUpgradesTo = getId();
			ElementKeyMap.getInfo(991).chamberChildren.remove(getId());
			BlockConfig.add(blockInfo);
		}
	}

	public static class ShieldAuraBaseChamber extends ChamberBlock {

		public ShieldAuraBaseChamber() {
			super("Shield Aura Base", (short) 991, 0.05f);
		}

		@Override
		public void initialize() {
			if(GraphicsContext.initialized) {
				blockInfo.setBuildIconNum(ResourceManager.getTexture("support-chamber-icon").getTextureId());
				short textureId = (short) ResourceManager.getTexture("support-chamber").getTextureId();
				blockInfo.setTextureId(new short[] {textureId, textureId, textureId, textureId, textureId, textureId});
			}
			blockInfo.setDescription("Base chamber for Shield Aura systems.");
			blockInfo.setPlacable(false);
			blockInfo.setInRecipe(false);
			blockInfo.reactorHp = 20;
			blockInfo.shoppable = false;
			blockInfo.chamberConfigGroupsLowerCase.add("shield_aura_base_effect");
			blockInfo.chamberRoot = ElementManager.getBlock("Reactor Support Chamber").getId();
			ElementManager.getBlock("Reactor Support Chamber").getBlockInfo().chamberChildren.add(getId());
			ElementKeyMap.getInfo(991).chamberChildren.remove(getId());
			BlockConfig.add(blockInfo);
		}
	}
}