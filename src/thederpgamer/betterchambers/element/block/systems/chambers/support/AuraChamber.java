package thederpgamer.betterchambers.element.block.systems.chambers.support;

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
			setUpgrade(ElementManager.getChamber("Aura Range Boost 2"));
			ElementManager.getBlock("Reactor Support Chamber").getBlockInfo().chamberChildren.add(getId());
			ElementKeyMap.getInfo(991).chamberChildren.remove(getId());
		}
	}

	public static class AuraRangeBoostChamber2 extends ChamberBlock {

		public AuraRangeBoostChamber2() {
			super("Aura Range Boost 2", (short) 991, 0.10f);
		}

		@Override
		public void initialize() {
			if(GraphicsContext.initialized) {
				short textureId = (short) ResourceManager.getTexture("support-chamber").getTextureId();
				blockInfo.setTextureId(new short[] {textureId, textureId, textureId, textureId, textureId, textureId});
			}
			blockInfo.setDescription("Boosts the maximum range of active auras.");
			blockInfo.setPlacable(false);
			blockInfo.setInRecipe(false);
			blockInfo.reactorHp = 20;
			blockInfo.shoppable = false;
			blockInfo.chamberConfigGroupsLowerCase.add("aura_range_boost_effect_2");
			blockInfo.chamberRoot = ElementManager.getBlock("Reactor Support Chamber").getId();
			setUpgrade(ElementManager.getChamber("Aura Range Boost 3"));
			ElementKeyMap.getInfo(991).chamberChildren.remove(getId());
		}
	}

	public static class AuraRangeBoostChamber3 extends ChamberBlock {

		public AuraRangeBoostChamber3() {
			super("Aura Range Boost 3", (short) 991, 0.15f);
		}

		@Override
		public void initialize() {
			if(GraphicsContext.initialized) {
				short textureId = (short) ResourceManager.getTexture("support-chamber").getTextureId();
				blockInfo.setTextureId(new short[] {textureId, textureId, textureId, textureId, textureId, textureId});
			}
			blockInfo.setDescription("Boosts the maximum range of active auras.");
			blockInfo.setPlacable(false);
			blockInfo.setInRecipe(false);
			blockInfo.reactorHp = 20;
			blockInfo.shoppable = false;
			blockInfo.chamberConfigGroupsLowerCase.add("aura_range_boost_effect_3");
			blockInfo.chamberRoot = ElementManager.getBlock("Reactor Support Chamber").getId();
			ElementKeyMap.getInfo(991).chamberChildren.remove(getId());
		}
	}

	public static class AuraProjectorChamber extends ChamberBlock {

		public AuraProjectorChamber() {
			super("Aura Projector", (short) 991, 0.02f);
		}

		@Override
		public void initialize() {
			if(GraphicsContext.initialized) {
				short textureId = (short) ResourceManager.getTexture("support-chamber").getTextureId();
				blockInfo.setTextureId(new short[] {textureId, textureId, textureId, textureId, textureId, textureId});
			}
			blockInfo.setDescription("Base chamber for Aura systems.");
			blockInfo.setPlacable(false);
			blockInfo.setInRecipe(false);
			blockInfo.reactorHp = 20;
			blockInfo.shoppable = false;
			blockInfo.chamberConfigGroupsLowerCase.add("aura_base_effect");
			blockInfo.chamberRoot = ElementManager.getBlock("Reactor Support Chamber").getId();
			addChildren(ElementManager.getChamber("Shield Aura Base"), ElementManager.getChamber("Offense Aura Base"));
			ElementManager.getBlock("Reactor Support Chamber").getBlockInfo().chamberChildren.add(getId());
			ElementKeyMap.getInfo(991).chamberChildren.remove(getId());
		}
	}

	public static class ShieldAuraBaseChamber extends ChamberBlock {

		public ShieldAuraBaseChamber() {
			super("Shield Aura Base", (short) 991, 0.05f);
		}

		@Override
		public void initialize() {
			if(GraphicsContext.initialized) {
				short textureId = (short) ResourceManager.getTexture("support-chamber").getTextureId();
				blockInfo.setTextureId(new short[] {textureId, textureId, textureId, textureId, textureId, textureId});
			}
			blockInfo.setDescription("Enables shield boosting effects for the Aura Projector.");
			blockInfo.setPlacable(false);
			blockInfo.setInRecipe(false);
			blockInfo.reactorHp = 20;
			blockInfo.shoppable = false;
			blockInfo.chamberConfigGroupsLowerCase.add("shield_aura_base_effect");
			blockInfo.chamberRoot = ElementManager.getBlock("Reactor Support Chamber").getId();
			addExclusives(ElementManager.getChamber("Offense Aura Base"));
			addChildren(ElementManager.getChamber("Shield Aura Capacity 1"));
			ElementKeyMap.getInfo(991).chamberChildren.remove(getId());
		}
	}

	public static class ShieldAuraCapacityChamber1 extends ChamberBlock {

		public ShieldAuraCapacityChamber1() {
			super("Shield Aura Capacity 1", (short) 991, 0.10f);
		}

		@Override
		public void initialize() {
			if(GraphicsContext.initialized) {
				short textureId = (short) ResourceManager.getTexture("support-chamber").getTextureId();
				blockInfo.setTextureId(new short[] {textureId, textureId, textureId, textureId, textureId, textureId});
			}
			blockInfo.setDescription("Boosts the shield capacity of ships affected by this aura.");
			blockInfo.setPlacable(false);
			blockInfo.setInRecipe(false);
			blockInfo.reactorHp = 20;
			blockInfo.shoppable = false;
			blockInfo.chamberConfigGroupsLowerCase.add("shield_aura_cap_1_effect");
			blockInfo.chamberRoot = ElementManager.getBlock("Reactor Support Chamber").getId();
			setUpgrade(ElementManager.getChamber("Shield Aura Capacity 2"));
			ElementKeyMap.getInfo(991).chamberChildren.remove(getId());
		}
	}

	public static class ShieldAuraCapacityChamber2 extends ChamberBlock {

		public ShieldAuraCapacityChamber2() {
			super("Shield Aura Capacity 2", (short) 991, 0.15f);
		}

		@Override
		public void initialize() {
			if(GraphicsContext.initialized) {
				short textureId = (short) ResourceManager.getTexture("support-chamber").getTextureId();
				blockInfo.setTextureId(new short[] {textureId, textureId, textureId, textureId, textureId, textureId});
			}
			blockInfo.setDescription("Further boosts the shield capacity of ships affected by this aura.");
			blockInfo.setPlacable(false);
			blockInfo.setInRecipe(false);
			blockInfo.reactorHp = 20;
			blockInfo.shoppable = false;
			blockInfo.chamberConfigGroupsLowerCase.add("shield_aura_cap_2_effect");
			blockInfo.chamberRoot = ElementManager.getBlock("Reactor Support Chamber").getId();
			ElementKeyMap.getInfo(991).chamberChildren.remove(getId());
		}
	}

	public static class OffenseAuraBaseChamber extends ChamberBlock {

		public OffenseAuraBaseChamber() {
			super("Offense Aura Base", (short) 991, 0.05f);
		}

		@Override
		public void initialize() {
			if(GraphicsContext.initialized) {
				short textureId = (short) ResourceManager.getTexture("support-chamber").getTextureId();
				blockInfo.setTextureId(new short[] {textureId, textureId, textureId, textureId, textureId, textureId});
			}
			blockInfo.setDescription("Enables offensive effects for the Aura Projector.");
			blockInfo.setPlacable(false);
			blockInfo.setInRecipe(false);
			blockInfo.reactorHp = 20;
			blockInfo.shoppable = false;
			blockInfo.chamberConfigGroupsLowerCase.add("offense_aura_base_effect");
			blockInfo.chamberRoot = ElementManager.getBlock("Reactor Support Chamber").getId();
			addExclusives(ElementManager.getChamber("Shield Aura Base"));
			addChildren(ElementManager.getChamber("Offense Aura Targeting 1"));
			ElementKeyMap.getInfo(991).chamberChildren.remove(getId());
		}
	}

	public static class OffenseAuraTargetingChamber1 extends ChamberBlock {

		public OffenseAuraTargetingChamber1() {
			super("Offense Aura Targeting 1", (short) 991, 0.10f);
		}

		@Override
		public void initialize() {
			if(GraphicsContext.initialized) {
				short textureId = (short) ResourceManager.getTexture("support-chamber").getTextureId();
				blockInfo.setTextureId(new short[] {textureId, textureId, textureId, textureId, textureId, textureId});
			}
			blockInfo.setDescription("Boosts the AI range and accuracy of ships and turrets affected by this aura.");
			blockInfo.setPlacable(false);
			blockInfo.setInRecipe(false);
			blockInfo.reactorHp = 20;
			blockInfo.shoppable = false;
			blockInfo.chamberConfigGroupsLowerCase.add("offense_aura_targeting_1_effect");
			blockInfo.chamberRoot = ElementManager.getBlock("Reactor Support Chamber").getId();
			setUpgrade(ElementManager.getChamber("Offense Aura Targeting 2"));
			ElementKeyMap.getInfo(991).chamberChildren.remove(getId());
		}
	}

	public static class OffenseAuraTargetingChamber2 extends ChamberBlock {

		public OffenseAuraTargetingChamber2() {
			super("Offense Aura Targeting 2", (short) 991, 0.15f);
		}

		@Override
		public void initialize() {
			if(GraphicsContext.initialized) {
				short textureId = (short) ResourceManager.getTexture("support-chamber").getTextureId();
				blockInfo.setTextureId(new short[] {textureId, textureId, textureId, textureId, textureId, textureId});
			}
			blockInfo.setDescription("Further boosts the AI range and accuracy of ships and turrets affected by this aura.");
			blockInfo.setPlacable(false);
			blockInfo.setInRecipe(false);
			blockInfo.reactorHp = 20;
			blockInfo.shoppable = false;
			blockInfo.chamberConfigGroupsLowerCase.add("offense_aura_targeting_2_effect");
			blockInfo.chamberRoot = ElementManager.getBlock("Reactor Support Chamber").getId();
			ElementKeyMap.getInfo(991).chamberChildren.remove(getId());
		}
	}
}