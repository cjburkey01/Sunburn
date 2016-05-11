package com.cjburkey.mod.sunburn.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class OverlayEvent {
	
	public static int time = 0;
	
	@SubscribeEvent
	public void onRenderGUI(RenderGameOverlayEvent e) {
		new OverlayGUI(Minecraft.getMinecraft(), time);
	}
	
}