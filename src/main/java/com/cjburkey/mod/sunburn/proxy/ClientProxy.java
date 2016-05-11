package com.cjburkey.mod.sunburn.proxy;

import com.cjburkey.mod.sunburn.client.OverlayGUI;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
	
	public void registerGuis() {
		MinecraftForge.EVENT_BUS.register(new OverlayGUI(Minecraft.getMinecraft()));
	}
	
}