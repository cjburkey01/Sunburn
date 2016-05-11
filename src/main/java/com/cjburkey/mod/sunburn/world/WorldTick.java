package com.cjburkey.mod.sunburn.world;

import com.cjburkey.mod.sunburn.Sunburn;
import com.cjburkey.mod.sunburn.packet.PacketDispatch;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class WorldTick {
	
	private static final String keyName = "sunburn_timeInSun";
	public static final int secondsInSun = 60;
	
	@SubscribeEvent
	public void onWorldTick(WorldTickEvent e) {
		World w = e.world;
		if(w.provider.dimensionId == 0) {
			for(Object player : w.playerEntities) {
				EntityPlayer p = (EntityPlayer) player;
				NBTTagCompound data = p.getEntityData();
				if(w.canBlockSeeTheSky((int) p.posX, (int) p.posY, (int) p.posZ) && isDay(w) && p.getActivePotionEffect(Sunburn.potionSunscreen) == null) {
					data.setInteger(keyName, (data.hasKey(keyName)) ? data.getInteger(keyName) + 1 : 0);
				} else {
					data.setInteger(keyName, (data.hasKey(keyName) && data.getInteger(keyName) > 0) ? data.getInteger(keyName) - 1 : 0);
				}
				
				if(data.getInteger(keyName) > (secondsInSun * 20)) {
					p.setFire(1);
				}
				
				System.out.println(data.getInteger(keyName));
				PacketDispatch.sendToPlayer(data.getInteger(keyName), p);
			}
		}
	}
	
	private static final boolean isDay(World w) {
		long time = w.getWorldTime();
		return time < 12300 || time > 23850;
	}
	
}