package net.kjentytek303.additional_transfurs.client.renderer.generated;

import net.kjentytek303.additional_transfurs.AdditionalTransfurs;
import net.kjentytek303.additional_transfurs.client.renderer.model.LatexFoxModel;
import net.kjentytek303.additional_transfurs.entity.generated.LatexFox;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.client.renderer.model.armor.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LatexFoxRenderer extends AdvancedHumanoidRenderer<LatexFox, LatexFoxModel> {
	
	public static final ResourceLocation DEFAULT_SKIN_LOCATION = AdditionalTransfurs.modResource("textures/entity/latex_fox.png");
	
	public LatexFoxRenderer(EntityRendererProvider.Context context) {
		super(context, new LatexFoxModel(context.bakeLayer(LatexFoxModel.LAYER_LOCATION)), ArmorLatexMaleWolfModel.MODEL_SET, 0.5f);
		this.addLayer(new LatexParticlesLayer<>(this, getModel()));
		this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(CustomEyesLayer.builder(this, context.getModelSet())
			.build());
		
		
		
		
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}
	
	@Override
	public ResourceLocation getTextureLocation(LatexFox entity) {
		return DEFAULT_SKIN_LOCATION;
	}

	
}
