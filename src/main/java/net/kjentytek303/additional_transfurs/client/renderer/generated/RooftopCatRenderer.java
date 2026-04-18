package net.kjentytek303.additional_transfurs.client.renderer.generated;

import net.kjentytek303.additional_transfurs.AdditionalTransfurs;
import net.kjentytek303.additional_transfurs.client.renderer.model.RooftopCatModel;
import net.kjentytek303.additional_transfurs.entity.generated.RooftopCat;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.client.renderer.model.armor.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class RooftopCatRenderer extends AdvancedHumanoidRenderer<RooftopCat, RooftopCatModel> {
	
	public static final ResourceLocation DEFAULT_SKIN_LOCATION = AdditionalTransfurs.modResource("textures/entity/rooftop_cat.png");
	
	public RooftopCatRenderer(EntityRendererProvider.Context context) {
		super(context, new RooftopCatModel(context.bakeLayer(RooftopCatModel.LAYER_LOCATION)), ArmorLatexMaleCatModel.MODEL_SET, 0.5f);
		this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(CustomEyesLayer.builder(this, context.getModelSet())
			.build());
		
		
		
		
		
	}
	
	@Override
	public ResourceLocation getTextureLocation(RooftopCat entity) {
		return DEFAULT_SKIN_LOCATION;
	}

	
}