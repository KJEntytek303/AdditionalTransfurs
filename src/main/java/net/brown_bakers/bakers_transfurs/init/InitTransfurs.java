package net.brown_bakers.bakers_transfurs.init;

import net.brown_bakers.bakers_transfurs.BakersTransfurs;
import net.brown_bakers.bakers_transfurs.entity.AbstractLatexCheetah;
import net.brown_bakers.bakers_transfurs.entity.LatexCheetahFemale;
import net.brown_bakers.bakers_transfurs.entity.LatexCheetahMale;
import net.brown_bakers.bakers_transfurs.entity.LatexFox;
import net.ltxprogrammer.changed.entity.variant.GenderedPair;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.ChangedRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.ltxprogrammer.changed.init.ChangedTransfurVariants.Gendered.registerPair;

public class InitTransfurs
{
	public static DeferredRegister<TransfurVariant<?>> TF_REGISTRY = ChangedRegistry.TRANSFUR_VARIANT.createDeferred(BakersTransfurs.MODID);
	
	public static final RegistryObject<TransfurVariant<LatexFox>> LATEX_FOX_VARIANT = TF_REGISTRY.register("latex_fox", LatexFox::getTFInitBuilder);
	public static final RegistryObject<TransfurVariant<LatexCheetahMale>> LATEX_CHEETAH_MALE_VARIANT = TF_REGISTRY.register("latex_cheetah/male", LatexCheetahMale::getTFInitBuilder);
	public static final RegistryObject<TransfurVariant<LatexCheetahFemale>> LATEX_CHEETAH_FEMALE_VARIANT = TF_REGISTRY.register("latex_cheetah/female", LatexCheetahFemale::getTFInitBuilder);
	
	
	public static final GenderedPair<LatexCheetahMale, LatexCheetahFemale> LATEX_CHEETAHS = registerPair(LATEX_CHEETAH_MALE_VARIANT, LATEX_CHEETAH_FEMALE_VARIANT);
}
