package com.cjburkey.mod.sunburn.world;

import com.cjburkey.mod.sunburn.Sunburn;
import com.cjburkey.mod.sunburn.packet.PacketDispatch;
import com.cjburkey.mod.sunburn.potion.POTIONS;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class WorldTick {
	
	@SubscribeEvent
	public void onWorldTick(WorldTickEvent e) {
		World w = e.world;
		if(w.provider.dimensionId == 0) {
			for(Object player : w.playerEntities) {
				EntityPlayer p = (EntityPlayer) player;
				NBTTagCompound data = p.getEntityData();
				if(w.canBlockSeeTheSky((int) p.posX, (int) p.posY + 1, (int) p.posZ) && isDay(w) && p.getActivePotionEffect(POTIONS.potionSunscreen) == null) {
					data.setInteger(Sunburn.keyName, (data.hasKey(Sunburn.keyName)) ? data.getInteger(Sunburn.keyName) + 1 : 0);
				} else {
					data.setInteger(Sunburn.keyName, (data.hasKey(Sunburn.keyName) && data.getInteger(Sunburn.keyName) > 0) ? data.getInteger(Sunburn.keyName) - 1 : 0);
				}
				
				if(data.getInteger(Sunburn.keyName) > (Sunburn.secondsInSun * Sunburn.ticksPerSecond)) {
					p.setFire(1);
				}
				
				PacketDispatch.sendToPlayer(data.getInteger(Sunburn.keyName), p);
			}
		}
	}
	
	private static final boolean isDay(World w) {
		long time = w.getWorldTime();
		return time < 12300 || time > 23850;
	}
	
}