package net.brown_bakers.bakers_transfurs.client.renderer;

import net.brown_bakers.bakers_transfurs.BakersTransfurs;
import net.brown_bakers.bakers_transfurs.client.renderer.model.LatexFoxModel;
import net.brown_bakers.bakers_transfurs.entity.LatexFox;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.GasMaskLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TransfurCapeLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWolfModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LatexFoxRenderer extends AdvancedHumanoidRenderer<LatexFox, LatexFoxModel> {
	
	public static final ResourceLocation DEFAULT_SKIN_LOCATION = BakersTransfurs.modResource("textures/entity/latex_fox.png");
	
	public LatexFoxRenderer(EntityRendererProvider.Context context) {
		super(context, new LatexFoxModel(context.bakeLayer(LatexFoxModel.LAYER_LOCATION)), ArmorLatexMaleWolfModel.MODEL_SET, 0.5f);
		this.addLayer(new LatexParticlesLayer<>(this, getModel()));
		this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(CustomEyesLayer.builder(this, context.getModelSet())
			   .withSclera(Color3.WHITE).withIris(Color3.fromInt(0x43b44e)).build());
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}
	
	@Override
	public ResourceLocation getTextureLocation(LatexFox entity) {
		return DEFAULT_SKIN_LOCATION;
	}
}