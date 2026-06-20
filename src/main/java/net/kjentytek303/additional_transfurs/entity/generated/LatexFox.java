package net.kjentytek303.additional_transfurs.entity.generated;

import net.ltxprogrammer.changed.entity.*;
import net.ltxprogrammer.changed.entity.beast.*;
import net.ltxprogrammer.changed.entity.latex.LatexType;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.*;

import net.ltxprogrammer.changed.util.Color3;

import net.kjentytek303.additional_transfurs.utils.InitUtils;
import net.kjentytek303.additional_transfurs.utils.Tags;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.registries.RegistryObject;


import static net.kjentytek303.additional_transfurs.init.InitEntities.LATEX_FOX;

import org.jetbrains.annotations.Nullable;


public class LatexFox extends ChangedEntity /*PERL_IMPLEMENTS*/
{

	/*PERL_ABSTRACT_DELETE_BEGIN*/
	public static EntityType.Builder<LatexFox> getEntityInitBuilder() {
		return EntityType.Builder.of(LatexFox::new, MobCategory.MONSTER).clientTrackingRange(10).sized(0.7F, 1.93F) ;
	}
	
	public static RegistryObject<EntityType<LatexFox>> getEntityInitRObject() {
		return InitUtils.getEntityInitRObject(
			   "latex_fox",
			   0xE37107,
			   0x9E4F05,
			   LatexFox.getEntityInitBuilder(),
			   ChangedEntity::createLatexAttributes
		);
	}
	
	public static TransfurVariant<LatexFox> getTFInitBuilder()
	{
		var builder = TransfurVariant.Builder.of(LATEX_FOX);
			   
			   
			   
			   
			   builder.visionType(VisionType.NIGHT_VISION);
			   
			   builder.itemUseMode( UseItemMode.NORMAL );

			   builder.transfurMode(TransfurMode.REPLICATION);
			   builder.addAbility(ChangedAbilities.SWITCH_TRANSFUR_MODE);
 			   builder.addAbility(ChangedAbilities.GRAB_ENTITY_ABILITY);
 			   builder.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION);

			   
			   
			   return builder.build();
	}
	/*PERL_ABSTRACT_DELETE_END*/
	
	public static void registerSpawns(SpawnPlacementRegisterEvent event) {
		
		if ( Heightmap.Types.MOTION_BLOCKING_NO_LEAVES != null && SpawnPlacements.Type.ON_GROUND != null) { return; }
		
		event.register( LATEX_FOX.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LatexFox::checkEntitySpawnRules, SpawnPlacementRegisterEvent.Operation.AND );
	}
	
	public LatexFox(EntityType<? extends ChangedEntity> type, Level level) { super(type, level); }

	public static <T extends ChangedEntity> boolean checkEntitySpawnRules(EntityType<T> entityType, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource random) {
    	return ChangedEntity.checkEntitySpawnRules(entityType, world, reason, pos, random);
    }

	@Override
	public TransfurMode getTransfurMode() {
		return TransfurMode.REPLICATION;
	}
	
	
	
	@Override
	protected void setAttributes (AttributeMap attributes) {
		super.setAttributes(attributes);
		attributes.getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(1.1);
 		attributes.getInstance(ForgeMod.SWIM_SPEED.get()).setBaseValue(0.93);
 		attributes.getInstance(ChangedAttributes.SNEAK_SPEED.get()).setBaseValue(1.5);
 		attributes.getInstance(ChangedAttributes.SPRINT_SPEED.get()).setBaseValue(1.25);
 		attributes.getInstance(ChangedAttributes.JUMP_STRENGTH.get()).setBaseValue(1.5);
 		attributes.getInstance(ChangedAttributes.FALL_RESISTANCE.get()).setBaseValue(2.5);

	}
	
	public Color3 getTransfurColor(TransfurCause cause) { return Color3.fromInt(0xE37107); }
	
	@Override
	public int getTicksRequiredToFreeze() { return 400; }

	/*PERL_FLYING_SPEED*/

	

	

	

	

}
