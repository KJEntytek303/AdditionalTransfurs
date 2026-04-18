package net.kjentytek303.additional_transfurs.init;

import net.kjentytek303.additional_transfurs.client.renderer.*;
import net.kjentytek303.additional_transfurs.client.renderer.generated.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.ltxprogrammer.changed.init.ChangedEntityRenderers.registerHumanoid;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class InitRenderers {
	
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {

		registerHumanoid(event, InitEntities.AVALI.get(), AvaliRenderer::new);
 		registerHumanoid(event, InitEntities.LATEX_FOX.get(), LatexFoxRenderer::new);
 		registerHumanoid(event, InitEntities.LATEX_PLANT_DRAGON.get(), LatexPlantDragonRenderer::new);
 		registerHumanoid(event, InitEntities.ROOFTOP_CAT.get(), RooftopCatRenderer::new);

	}
}
