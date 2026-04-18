package net.kjentytek303.additional_transfurs.ability;

import net.ltxprogrammer.changed.ability.AbstractAbility;
import net.ltxprogrammer.changed.ability.AbstractAbilityInstance;
import net.ltxprogrammer.changed.ability.IAbstractChangedEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import static net.kjentytek303.additional_transfurs.utils.Constants.TICKS_IN_SECOND;

public class PurringAbilityInstance extends AbstractAbilityInstance {
	
	protected RandomSource random = RandomSource.create();
	protected int ticks_used = 0;
	private boolean had_regeneration = false;
	private final IAbstractChangedEntity entity;
	
	PurringAbilityInstance(AbstractAbility<PurringAbilityInstance> ability, IAbstractChangedEntity entity) {
		super(ability, entity );
		this.entity = entity;
	}
	
	@Override
	public boolean canUse() {
		return true;
	}
	
	@Override
	public boolean canKeepUsing() {
		if (ticks_used > 15 * TICKS_IN_SECOND) {
			ticks_used = 0;
			return false;
		}
		return true;
	}
	
	@Override
	public void startUsing() {
		var previous_regen = entity.getEntity().getEffect(MobEffects.REGENERATION);
		var new_regen = new MobEffectInstance(MobEffects.REGENERATION, 15 * TICKS_IN_SECOND, 0);
		
		if (previous_regen == null) { entity.getEntity().addEffect(new_regen); }
		else { previous_regen.update(new_regen); had_regeneration=true; }
	}
	
	@Override
	public void tick() {
		if ( ticks_used % 6 == 0 ) {
			entity.getEntity().playSound( SoundEvents.CAT_PURR, 0.6F + 0.4F * ( this.random.nextFloat() - this.random.nextFloat() ), 1.0F);
		}
		ticks_used++;
	}
	
	@Override
	public void stopUsing() {
		if (!had_regeneration) { entity.getEntity().removeEffect(MobEffects.REGENERATION); }	//What if stop using before cooldown?
		ticks_used=0;
	}
}
