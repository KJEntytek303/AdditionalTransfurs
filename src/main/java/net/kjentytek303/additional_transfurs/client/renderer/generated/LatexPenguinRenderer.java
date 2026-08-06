package net.kjentytek303.additional_transfurs.client.renderer.generated;

import net.kjentytek303.additional_transfurs.AdditionalTransfurs;
import net.kjentytek303.additional_transfurs.client.renderer.model.LatexPenguinModel;
import net.kjentytek303.additional_transfurs.entity.generated.LatexPenguin;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.client.renderer.model.armor.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LatexPenguinRenderer extends AdvancedHumanoidRenderer<LatexPenguin, LatexPenguinModel> {
	
	public static final ResourceLocation DEFAULT_SKIN_LOCATION = AdditionalTransfurs.modResource("textures/entity/latex_penguin.png");
	
	public LatexPenguinRenderer(EntityRendererProvider.Context context) {
		super(context, new LatexPenguinModel(context.bakeLayer(LatexPenguinModel.LAYER_LOCATION)), ArmorHumanModel.MODEL_SET, 0.5f);
		this.addLayer(new LatexParticlesLayer<>(this, getModel()));
		this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(CustomEyesLayer.builder(this, context.getModelSet())
			.build());
		
		
		
		
		
	}
	
	@Override
	public ResourceLocation getTextureLocation(LatexPenguin entity) {
		return DEFAULT_SKIN_LOCATION;
	}

	
}
