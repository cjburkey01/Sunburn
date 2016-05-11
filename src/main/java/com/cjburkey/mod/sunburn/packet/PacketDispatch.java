package com.cjburkey.mod.sunburn.packet;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketDispatch {
	
	private static SimpleNetworkWrapper network;
	
	public static final void init() {
		network = NetworkRegistry.INSTANCE.newSimpleChannel("sunburn");
		network.registerMessage(SunburnMessage.Handler.class, SunburnMessage.class, 1, Side.CLIENT);
	}
	
	public static final void sendToPlayer(int time, EntityPlayer player) {
		network.sendTo(new SunburnMessage(time + ""), (EntityPlayerMP) player);
	}
	
}