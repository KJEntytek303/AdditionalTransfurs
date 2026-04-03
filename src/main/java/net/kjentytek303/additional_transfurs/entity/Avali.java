package net.kjentytek303.additional_transfurs.entity;

import net.kjentytek303.additional_transfurs.init.utils.InitUtils;
import net.ltxprogrammer.changed.entity.*;
import net.ltxprogrammer.changed.entity.latex.LatexType;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.*;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.registries.RegistryObject;

import static net.kjentytek303.additional_transfurs.init.InitEntities.AVALI;
import static net.kjentytek303.additional_transfurs.init.InitEntities.LATEX_FOX;

public class Avali extends ChangedEntity {
	
	public static EntityType.Builder<Avali> getEntityInitBuilder() {
		return EntityType.Builder
			   .of(Avali::new, MobCategory.MONSTER)
			   .clientTrackingRange(10)
			   .sized(0.7F, 1.93F);
	}
	
	public static RegistryObject<EntityType<Avali>> getEntityInitRObject() {
		return InitUtils.getEntityInitRObject (
			   "avali",
			   0x00FF00,
			   0x9E4F05,
			   Avali.getEntityInitBuilder(),
			   ChangedEntity::createLatexAttributes
		);
	}
	
	public static void registerSpawns(SpawnPlacementRegisterEvent event) {
		
		if ( Heightmap.Types.MOTION_BLOCKING_NO_LEAVES != null && SpawnPlacements.Type.ON_GROUND != null) { return; }
		
		event.register( LATEX_FOX.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Avali::checkEntitySpawnRules, SpawnPlacementRegisterEvent.Operation.OR );
	}
	
	public static TransfurVariant<Avali> getTFInitBuilder()
	{
		return TransfurVariant.Builder
			   .of(AVALI)
			   .breatheMode(TransfurVariant.BreatheMode.NORMAL)
			   .glide(false)
			   .extraJumps(0)
			   .canClimb(false)
			   .visionType(VisionType.NIGHT_VISION)
			   .miningStrength(MiningStrength.NORMAL)
			   .itemUseMode(UseItemMode.NORMAL)
			   .glide(true)
			   //.scares(AbstractVillager.class)
			   .transfurMode(TransfurMode.REPLICATION)
			   .addAbility(ChangedAbilities.SWITCH_TRANSFUR_MODE)
			   .addAbility(ChangedAbilities.GRAB_ENTITY_ABILITY)
			   .addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
			   .cameraZOffset(0.0f)
			   .sound(ChangedSounds.TRANSFUR_BY_LATEX.getId())
			   .build();
	}
	
	public Avali (EntityType<? extends ChangedEntity> type, Level level) { super(type, level); }
	
	
	
	@Override
	public TransfurMode getTransfurMode() {
		return TransfurMode.REPLICATION;
	}
	
	@Override	//redundant - Changed Entity defaults to none.
	public LatexType getLatexType() { return ChangedLatexTypes.NONE.get(); }
	
	@Override
	protected void setAttributes (AttributeMap attributes) {
		super.setAttributes(attributes);
		attributes.getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(1.1);
		attributes.getInstance(ForgeMod.SWIM_SPEED.get()).setBaseValue(0.93);
		attributes.getInstance(ChangedAttributes.SNEAK_SPEED.get()).setBaseValue(1.5D);
		attributes.getInstance(ChangedAttributes.SPRINT_SPEED.get()).setBaseValue(1.25);
		attributes.getInstance(ChangedAttributes.JUMP_STRENGTH.get()).setBaseValue(1.5);
		attributes.getInstance(ChangedAttributes.FALL_RESISTANCE.get()).setBaseValue(1.5);
	}
	
	@Override
	public Color3 getTransfurColor(TransfurCause cause) {
		return Color3.fromInt(0xE37107);
	}
	
	@Override
	public int getTicksRequiredToFreeze() { return 540; }
	
}
