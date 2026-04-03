package net.kjentytek303.additional_transfurs.client.renderer;

import net.kjentytek303.additional_transfurs.AdditionalTransfurs;
import net.kjentytek303.additional_transfurs.client.renderer.model.AvaliModel;
import net.kjentytek303.additional_transfurs.client.renderer.model.LatexFoxModel;
import net.kjentytek303.additional_transfurs.entity.Avali;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.GasMaskLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TransfurCapeLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWolfModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class AvaliRenderer extends AdvancedHumanoidRenderer <Avali, AvaliModel> {
	
	public static final ResourceLocation DEFAULT_SKIN_LOCATION = AdditionalTransfurs.modResource("textures/entity/avali.png");
	
	public AvaliRenderer(EntityRendererProvider.Context context) {
		super(context, new AvaliModel(context.bakeLayer(AvaliModel.LAYER_LOCATION)), ArmorLatexMaleWolfModel.MODEL_SET, 0.5f);
		this.addLayer(new LatexParticlesLayer<>(this, getModel()));
		this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(CustomEyesLayer.builder(this, context.getModelSet()).build());
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}
	
	@Override
	public ResourceLocation getTextureLocation(Avali entity) {
		return DEFAULT_SKIN_LOCATION;
	}
}
