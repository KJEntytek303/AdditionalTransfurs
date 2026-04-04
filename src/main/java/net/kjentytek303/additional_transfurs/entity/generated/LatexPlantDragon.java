package net.kjentytek303.additional_transfurs.entity.generated;

import net.kjentytek303.additional_transfurs.AdditionalTransfurs;

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
import net.minecraft.server.level.ServerPlayer;
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


import static net.kjentytek303.additional_transfurs.init.InitEntities.LATEX_PLANT_DRAGON;

import org.jetbrains.annotations.Nullable;


public class LatexPlantDragon extends ChangedEntity /*PERL_IMPLEMENTS*/
{

	/*PERL_ABSTRACT_DELETE_BEGIN*/
	public static EntityType.Builder<LatexPlantDragon> getEntityInitBuilder() {
		return EntityType.Builder.of(LatexPlantDragon::new, MobCategory.MONSTER).clientTrackingRange(10).sized(0.7F, 1.93F) ;
	}
	
	public static RegistryObject<EntityType<LatexPlantDragon>> getEntityInitRObject() {
		return InitUtils.getEntityInitRObject(
			   "latex_plant_dragon",
			   0x29441b,
			   0x1c2f13,
			   LatexPlantDragon.getEntityInitBuilder(),
			   ChangedEntity::createLatexAttributes
		);
	}
	
	public static TransfurVariant<LatexPlantDragon> getTFInitBuilder()
	{
		return TransfurVariant.Builder
			   .of(LATEX_PLANT_DRAGON)
			   
			   .glide(true)
			   
			   
			   .visionType(VisionType.NORMAL)
			   
			   .itemUseMode( UseItemMode.NORMAL )

			   .transfurMode(TransfurMode.REPLICATION)
			   .addAbility(ChangedAbilities.SWITCH_TRANSFUR_MODE)
 			   .addAbility(ChangedAbilities.GRAB_ENTITY_ABILITY)

			   
			   
			   .build();
	}
	/*PERL_ABSTRACT_DELETE_END*/
	
	public static void registerSpawns(SpawnPlacementRegisterEvent event) {
		
		if ( Heightmap.Types.MOTION_BLOCKING_NO_LEAVES != null && SpawnPlacements.Type.ON_GROUND != null) { return; }
		
		event.register( LATEX_PLANT_DRAGON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LatexPlantDragon::checkEntitySpawnRules, SpawnPlacementRegisterEvent.Operation.OR );
	}
	
	public LatexPlantDragon(EntityType<? extends ChangedEntity> type, Level level) { super(type, level); }
	
	@Override
	public TransfurMode getTransfurMode() {
		return TransfurMode.REPLICATION;
	}
	
	
	
	@Override
	protected void setAttributes (AttributeMap attributes) {
		super.setAttributes(attributes);
		attributes.getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(1.0);
 		attributes.getInstance(Attributes.MAX_HEALTH).setBaseValue(24.0);
 		attributes.getInstance(ForgeMod.SWIM_SPEED.get()).setBaseValue(0.85);

	}
	
	public Color3 getTransfurColor(TransfurCause cause) { return Color3.fromInt(0x213915); }
	
	

	/*PERL_FLYING_SPEED*/

	

	

	

	@Override
	public void tick() {
	    super.tick();
		var player = this.getUnderlyingPlayer();
		if ( player == null ) { return; }

		try {
			if ( ! this.level().isClientSide()
			/*
			    player instanceof ServerPlayer playerS  &&
			    this.getBlockStateOn().is(Tags.ATBlockTags.SATURATES_PLANT_TFS) &&
				playerS.getFoodData().needsFood() &&
				(player.tickCount % 1) == 0*/ //TODO Replace with a serverconfig. Might throw DIV0 excpt.
			) { player.getFoodData().eat(2, 1.0f); }
			//TODO: play sounds.
		}
		catch ( java.lang.ArithmeticException e ) { AdditionalTransfurs.LOGGER.atWarn().log("Caught exception in PlantDerg: " + e.getMessage()); }

	}
}
