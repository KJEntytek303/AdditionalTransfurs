package net.kjentytek303.additional_transfurs.client.renderer.generated;

import net.kjentytek303.additional_transfurs.AdditionalTransfurs;
import net.kjentytek303.additional_transfurs.client.renderer.model.LatexRedTailCatfishModel;
import net.kjentytek303.additional_transfurs.entity.generated.LatexRedTailCatfish;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.client.renderer.model.armor.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LatexRedTailCatfishRenderer extends AdvancedHumanoidRenderer<LatexRedTailCatfish, LatexRedTailCatfishModel> {
	
	public static final ResourceLocation DEFAULT_SKIN_LOCATION = AdditionalTransfurs.modResource("textures/entity/latex_red_tail_catfish.png");
	
	public LatexRedTailCatfishRenderer(EntityRendererProvider.Context context) {
		super(context, new LatexRedTailCatfishModel(context.bakeLayer(LatexRedTailCatfishModel.LAYER_LOCATION)), ArmorLatexMaleSharkModel.MODEL_SET, 0.5f);
		this.addLayer(new LatexParticlesLayer<>(this, getModel()));
		this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(CustomEyesLayer.builder(this, context.getModelSet())
			.build());
		
		
		
		
		this.addLayer(GasMaskLayer.forLargeSnouted(this, context.getModelSet()));
	}
	
	@Override
	public ResourceLocation getTextureLocation(LatexRedTailCatfish entity) {
		return DEFAULT_SKIN_LOCATION;
	}

	
}
