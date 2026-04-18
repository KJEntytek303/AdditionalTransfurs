package net.kjentytek303.additional_transfurs.init;

import net.kjentytek303.additional_transfurs.AdditionalTransfurs;

import net.kjentytek303.additional_transfurs.entity.*;
import net.kjentytek303.additional_transfurs.entity.generated.*;

import net.ltxprogrammer.changed.entity.variant.GenderedPair;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.ChangedRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.ltxprogrammer.changed.init.ChangedTransfurVariants.Gendered.registerPair;

public class InitTransfurs
{
	public static DeferredRegister<TransfurVariant<?>> TF_REGISTRY = ChangedRegistry.TRANSFUR_VARIANT.createDeferred(AdditionalTransfurs.MODID);
	
	public static final RegistryObject<TransfurVariant<Avali>> AVALI_VARIANT= TF_REGISTRY.register("avali", Avali::getTFInitBuilder);
 	public static final RegistryObject<TransfurVariant<LatexFox>> LATEX_FOX_VARIANT= TF_REGISTRY.register("latex_fox", LatexFox::getTFInitBuilder);
 	public static final RegistryObject<TransfurVariant<LatexPlantDragon>> LATEX_PLANT_DRAGON_VARIANT= TF_REGISTRY.register("latex_plant_dragon", LatexPlantDragon::getTFInitBuilder);
 	public static final RegistryObject<TransfurVariant<RooftopCat>> ROOFTOP_CAT_VARIANT= TF_REGISTRY.register("rooftop_cat", RooftopCat::getTFInitBuilder);



}
