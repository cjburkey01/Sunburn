package com.cjburkey.mod.sunburn.client;

import com.cjburkey.mod.sunburn.Sunburn;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class OverlayGUI extends Gui {
	
	public static int time = 0;
	private Minecraft mc;
	
	public OverlayGUI(Minecraft mc) {
		super();
		this.mc = mc;
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderGUI(RenderGameOverlayEvent.Post e) {
		FontRenderer fr = mc.fontRenderer;
		if(Sunburn.gui) {
			this.drawString(fr, "Sunburn: " + ((int) ((float) time / (float) Sunburn.ticksPerSecond)) + " of " + Sunburn.secondsInSun, 5, 5, hexToInt("C42D2D"));
		}
	}
	
	private static final int hexToInt(String hex) {
		return Integer.parseInt(hex, 16);
	}
	
}