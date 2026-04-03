package net.kjentytek303.additional_transfurs.client.renderer.generated;

import net.kjentytek303.additional_transfurs.AdditionalTransfurs;
import net.kjentytek303.additional_transfurs.client.renderer.model.PERL_ENTITY_NAMEModel;
import net.kjentytek303.additional_transfurs.entity.generated.PERL_ENTITY_NAME;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.client.renderer.model.armor.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class PERL_ENTITY_NAMERenderer extends AdvancedHumanoidRenderer<PERL_ENTITY_NAME, PERL_ENTITY_NAMEModel> {
	
	public static final ResourceLocation DEFAULT_SKIN_LOCATION = AdditionalTransfurs.modResource("textures/entity/PERL_LOWERCASE_NAME.png");
	
	public PERL_ENTITY_NAMERenderer(EntityRendererProvider.Context context) {
		super(context, new PERL_ENTITY_NAMEModel(context.bakeLayer(PERL_ENTITY_NAMEModel.LAYER_LOCATION)), PERL_ARMOR_MODEL, PERL_SHADOW_SIZEf);
		this.addLayer(new LatexParticlesLayer<>(this, getModel()));
		this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		/*PERL_CUSTOM_EYES*/
		/*PERL_EMISSIVE_LAYER*/
		/*PERL_TRANSLUCENT_LAYER*/
		/*PERL_MULTIHANDED*/
		/*PERL_CENTAURS*/
		/*PERL_GAS_MASK_LAYER*/
	}
	
	@Override
	public ResourceLocation getTextureLocation(PERL_ENTITY_NAME entity) {
		return DEFAULT_SKIN_LOCATION;
	}

	/*PERL_MODEL_SCALE*/
}
