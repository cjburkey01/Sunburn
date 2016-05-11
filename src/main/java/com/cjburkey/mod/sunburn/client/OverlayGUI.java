package com.cjburkey.mod.sunburn.client;

import com.cjburkey.mod.sunburn.Sunburn;
import com.cjburkey.mod.sunburn.world.WorldTick;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class OverlayGUI extends Gui {
	
	public OverlayGUI(Minecraft mc, int time) {
		this.drawString(mc.fontRenderer, "Sunburn: " + ((int) ((float) time / (float) Sunburn.ticksPerSecond)) + " of " + Sunburn.secondsInSun, 5, 5, Integer.parseInt("C42D2D", 16));
	}
	
}