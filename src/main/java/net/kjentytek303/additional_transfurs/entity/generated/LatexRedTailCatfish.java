package net.kjentytek303.additional_transfurs.entity.generated;

import net.ltxprogrammer.changed.entity.*;
import net.ltxprogrammer.changed.entity.beast.*;
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


import static net.kjentytek303.additional_transfurs.init.InitEntities.LATEX_RED_TAIL_CATFISH;

import org.jetbrains.annotations.Nullable;


public class LatexRedTailCatfish extends net.ltxprogrammer.changed.entity.beast.AbstractAquaticEntity /*PERL_IMPLEMENTS*/
{

	/*PERL_ABSTRACT_DELETE_BEGIN*/
	public static EntityType.Builder<LatexRedTailCatfish> getEntityInitBuilder() {
		return EntityType.Builder.of(LatexRedTailCatfish::new, MobCategory.MONSTER).clientTrackingRange(10).sized(0.7F, 1.93F) ;
	}
	
	public static RegistryObject<EntityType<LatexRedTailCatfish>> getEntityInitRObject() {
		return InitUtils.getEntityInitRObject(
			   "latex_red_tail_catfish",
			   0x191e1a,
			   0x0d110e,
			   LatexRedTailCatfish.getEntityInitBuilder(),
			   ChangedEntity::createLatexAttributes
		);
	}
	
	public static TransfurVariant<LatexRedTailCatfish> getTFInitBuilder()
	{
		return TransfurVariant.Builder
			   .of(LATEX_RED_TAIL_CATFISH)
			   .breatheMode(TransfurVariant.BreatheMode.ANY)
			   
			   
			   
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
		
		event.register( LATEX_RED_TAIL_CATFISH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LatexRedTailCatfish::checkEntitySpawnRules, SpawnPlacementRegisterEvent.Operation.AND );
	}
	
	public LatexRedTailCatfish(EntityType<? extends AbstractAquaticEntity> type, Level level) { super(type, level); }
	
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
	
	public Color3 getTransfurColor(TransfurCause cause) { return Color3.fromInt(0x191e1a); }
	
	

	/*PERL_FLYING_SPEED*/

	

	

	

	

}
