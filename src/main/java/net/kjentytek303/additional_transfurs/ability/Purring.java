package net.kjentytek303.additional_transfurs.ability;

import net.ltxprogrammer.changed.ability.AbstractAbility;
import net.ltxprogrammer.changed.ability.IAbstractChangedEntity;
import net.minecraft.resources.ResourceLocation;

import static net.kjentytek303.additional_transfurs.utils.Constants.TICKS_IN_SECOND;

public class Purring extends AbstractAbility<PurringAbilityInstance> {
	public Purring() { super( PurringAbilityInstance::new ); }
	
	@Override
	public AbstractAbility.UseType getUseType(IAbstractChangedEntity entity) {
		return UseType.HOLD;
	}
	
	@Override
	public int getCoolDown(IAbstractChangedEntity entity) {
		return 30 * TICKS_IN_SECOND;
	}
}
