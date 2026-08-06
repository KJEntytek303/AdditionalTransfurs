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


import static net.kjentytek303.additional_transfurs.init.InitEntities.LATEX_PENGUIN;

import org.jetbrains.annotations.Nullable;


public class LatexPenguin extends net.ltxprogrammer.changed.entity.beast.AbstractAquaticEntity /*PERL_IMPLEMENTS*/
{

	/*PERL_ABSTRACT_DELETE_BEGIN*/
	public static EntityType.Builder<LatexPenguin> getEntityInitBuilder() {
		return EntityType.Builder.of(LatexPenguin::new, MobCategory.MONSTER).clientTrackingRange(10).sized(0.7F, 1.93F) ;
	}
	
	public static RegistryObject<EntityType<LatexPenguin>> getEntityInitRObject() {
		return InitUtils.getEntityInitRObject(
			   "latex_penguin",
			   0x040404,
			   0xf3f3f3,
			   LatexPenguin.getEntityInitBuilder(),
			   ChangedEntity::createLatexAttributes
		);
	}
	
	public static TransfurVariant<LatexPenguin> getTFInitBuilder()
	{
		var builder = TransfurVariant.Builder.of(LATEX_PENGUIN);
			   builder.breatheMode(TransfurVariant.BreatheMode.ANY);
			   
			   
			   
			   builder.visionType(VisionType.NORMAL);
			   
			   builder.itemUseMode( UseItemMode.NORMAL );

			   builder.transfurMode(TransfurMode.REPLICATION);
			   builder.addAbility(ChangedAbilities.SWITCH_TRANSFUR_MODE);
 			   builder.addAbility(ChangedAbilities.GRAB_ENTITY_ABILITY);

			   
			   
			   return builder.build();
	}
	/*PERL_ABSTRACT_DELETE_END*/
	
	public static void registerSpawns(SpawnPlacementRegisterEvent event) {
		
		event.register( LATEX_PENGUIN.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LatexPenguin::checkEntitySpawnRules, SpawnPlacementRegisterEvent.Operation.AND );
	}
	
	public LatexPenguin(EntityType<? extends net.ltxprogrammer.changed.entity.beast.AbstractAquaticEntity > type, Level level) { super(type, level); }

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
		attributes.getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(0.875);
 		attributes.getInstance(Attributes.MAX_HEALTH).setBaseValue(24.0);
 		attributes.getInstance(ForgeMod.SWIM_SPEED.get()).setBaseValue(1.48);

	}
	
	public Color3 getTransfurColor(TransfurCause cause) { return Color3.fromInt(0x040404); }
	
	

	/*PERL_FLYING_SPEED*/

	

	

	

	

}
