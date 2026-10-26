package net.kjentytek303.additional_transfurs.init;

import net.kjentytek303.additional_transfurs.AdditionalTransfurs;
import net.ltxprogrammer.changed.init.ChangedItems;
import net.ltxprogrammer.changed.item.LatexSyringe;
import net.ltxprogrammer.changed.item.Syringe;
import net.ltxprogrammer.changed.util.UniversalDist;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;


public class InitCTTabs {
	public static final DeferredRegister<CreativeModeTab> CT_TAB_REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AdditionalTransfurs.MODID);
	public static final RegistryObject< CreativeModeTab > AT_ENTITIES = register(
		"additional_transfurs",
		(builder) -> builder.icon(
			() -> {
				ItemStack stack = new ItemStack(ChangedItems.LATEX_SYRINGE.get());
				Syringe.setPureVariant(stack, InitTransfurs.LATEX_FOX_VARIANT.get().getFormId());
				return stack;
			}
			).displayItems(
				(params, output) -> {
					Stream<SpawnEggItem> var10000 = InitItems.SPAWN_EGGS.values().stream().map(RegistryObject::get);
					Objects.requireNonNull(output);
					var10000.forEach(output::accept);
					InitTransfurs.TF_REGISTRY.getEntries().forEach((variant) -> {
						ItemStack stack = new ItemStack(ChangedItems.LATEX_SYRINGE.get());
						Syringe.setOwner(stack, UniversalDist.getLocalPlayer());
						Syringe.setPureVariant(stack, variant.getId());
						output.accept(stack);
					} );
				}
			)
			.build()
	);

	private static RegistryObject<CreativeModeTab> register(String id, Function<CreativeModeTab.Builder, CreativeModeTab> finalizer) {
		return CT_TAB_REGISTRY.register(id, () -> finalizer.apply(CreativeModeTab.builder().title(Component.translatable("additional_transfurs.ct_tabs." + id))));
	}

}
