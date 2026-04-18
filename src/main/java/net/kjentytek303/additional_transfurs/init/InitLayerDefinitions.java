package net.kjentytek303.additional_transfurs.init;

 import net.kjentytek303.additional_transfurs.client.renderer.model.AvaliModel;
 import net.kjentytek303.additional_transfurs.client.renderer.model.LatexFoxModel;
 import net.kjentytek303.additional_transfurs.client.renderer.model.LatexPlantDragonModel;
 import net.kjentytek303.additional_transfurs.client.renderer.model.RooftopCatModel;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class InitLayerDefinitions {
	
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {

		event.registerLayerDefinition(AvaliModel.LAYER_LOCATION, AvaliModel::createBodyLayer);
 		event.registerLayerDefinition(LatexFoxModel.LAYER_LOCATION, LatexFoxModel::createBodyLayer);
 		event.registerLayerDefinition(LatexPlantDragonModel.LAYER_LOCATION, LatexPlantDragonModel::createBodyLayer);
 		event.registerLayerDefinition(RooftopCatModel.LAYER_LOCATION, RooftopCatModel::createBodyLayer);

	}
}
