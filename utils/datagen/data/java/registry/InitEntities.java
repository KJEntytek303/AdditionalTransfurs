package net.kjentytek303.additional_transfurs.init;

import com.mojang.datafixers.util.Pair;

import net.kjentytek303.additional_transfurs.AdditionalTransfurs;

 /*PERL_ENTITY_IMPORTS*/

import net.ltxprogrammer.changed.entity.ChangedEntity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class InitEntities
{
	//Global Entity registry
	public static final DeferredRegister<EntityType<?>> ENTITY_REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AdditionalTransfurs.MODID);
	public static final HashMap<String, Pair<Integer, Integer>> ENTITY_COLORS = new HashMap<>();
	public static final List<Pair<Supplier<EntityType<? extends ChangedEntity>>, Supplier<AttributeSupplier.Builder>>> INIT_ATTRIBS = new ArrayList<>();

/*PERL_ENTITIES*/
	
	@SubscribeEvent
	public static void registerSpawns(SpawnPlacementRegisterEvent event) {
/*PERL_REGISTER_SPAWNS*/
	}
	
	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		INIT_ATTRIBS.forEach((pair) -> event.put(pair.getFirst().get(), pair.getSecond().get().build()));
	}
}
