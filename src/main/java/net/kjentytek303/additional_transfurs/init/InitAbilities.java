package net.kjentytek303.additional_transfurs.init;

import net.kjentytek303.additional_transfurs.AdditionalTransfurs;
import net.kjentytek303.additional_transfurs.ability.Purring;
import net.ltxprogrammer.changed.ability.AbstractAbility;
import net.ltxprogrammer.changed.init.ChangedRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class InitAbilities {
	public static final DeferredRegister<AbstractAbility<?>> ABILITY_REGISTRY = ChangedRegistry.ABILITY.createDeferred(AdditionalTransfurs.MODID);
	
	public static final RegistryObject<Purring> PURRING = ABILITY_REGISTRY.register("purring", Purring::new );
}
