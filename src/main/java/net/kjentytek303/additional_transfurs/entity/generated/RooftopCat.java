package net.kjentytek303.additional_transfurs.entity.generated;

import net.ltxprogrammer.changed.entity.*;
import net.ltxprogrammer.changed.entity.latex.LatexType;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.*;

import net.ltxprogrammer.changed.util.Color3;

import net.kjentytek303.additional_transfurs.utils.InitUtils;
import net.kjentytek303.additional_transfurs.utils.Tags;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.registries.RegistryObject;


import static net.kjentytek303.additional_transfurs.init.InitEntities.ROOFTOP_CAT;

import org.jetbrains.annotations.Nullable;


public class RooftopCat extends ChangedEntity /*PERL_IMPLEMENTS*/
{

	/*PERL_ABSTRACT_DELETE_BEGIN*/
	public static EntityType.Builder<RooftopCat> getEntityInitBuilder() {
		return EntityType.Builder.of(RooftopCat::new, MobCategory.CREATURE).clientTrackingRange(10).sized(0.7F, 1.93F) ;
	}
	
	public static RegistryObject<EntityType<RooftopCat>> getEntityInitRObject() {
		return InitUtils.getEntityInitRObject(
			   "rooftop_cat",
			   0xb0a69a,
			   0x333333,
			   RooftopCat.getEntityInitBuilder(),
			   ChangedEntity::createLatexAttributes
		);
	}
	
	public static TransfurVariant<RooftopCat> getTFInitBuilder()
	{
		return TransfurVariant.Builder
			   .of(ROOFTOP_CAT)
			   
			   
			   
			   
			   .visionType(VisionType.NIGHT_VISION)
			   .miningStrength( MiningStrength.STRONG )
			   .itemUseMode( UseItemMode.NORMAL )
			   .scares(net.minecraft.world.entity.monster.Creeper.class)

			   .transfurMode(TransfurMode.REPLICATION)
			   .addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
 			   .addAbility(net.kjentytek303.additional_transfurs.init.InitAbilities.PURRING)

			   
			   
			   .build();
	}
	/*PERL_ABSTRACT_DELETE_END*/
	
	public static void registerSpawns(SpawnPlacementRegisterEvent event) {
		
		if ( Heightmap.Types.MOTION_BLOCKING != null && SpawnPlacements.Type.ON_GROUND != null) { return; }
		
		event.register( ROOFTOP_CAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, RooftopCat::checkEntitySpawnRules, SpawnPlacementRegisterEvent.Operation.OR );
	}
	
	public RooftopCat(EntityType<? extends ChangedEntity> type, Level level) { super(type, level); }
	
	@Override
	public TransfurMode getTransfurMode() {
		return TransfurMode.REPLICATION;
	}
	
	
	
	@Override
	protected void setAttributes (AttributeMap attributes) {
		super.setAttributes(attributes);
		attributes.getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(1.25);
 		attributes.getInstance(Attributes.MAX_HEALTH).setBaseValue(30);
 		attributes.getInstance(Attributes.ARMOR).setBaseValue(8);
 		attributes.getInstance(ChangedAttributes.JUMP_STRENGTH.get()).setBaseValue(1.35);
 		attributes.getInstance(ChangedAttributes.FALL_RESISTANCE.get()).setBaseValue(2.75);
 		attributes.getInstance(ChangedAttributes.SPRINT_SPEED.get()).setBaseValue(1.1);

	}
	
	public Color3 getTransfurColor(TransfurCause cause) { return Color3.fromInt(0x333333); }
	
	

	/*PERL_FLYING_SPEED*/

	

	

	

	

}
