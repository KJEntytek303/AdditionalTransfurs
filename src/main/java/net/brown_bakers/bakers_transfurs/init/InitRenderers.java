package net.brown_bakers.bakers_transfurs.init;

import net.brown_bakers.bakers_transfurs.client.renderer.LatexFoxRenderer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.ltxprogrammer.changed.init.ChangedEntityRenderers.registerHumanoid;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class InitRenderers {
	
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		registerHumanoid(event, InitEntities.LATEX_FOX.get(), LatexFoxRenderer::new );
	}
}
