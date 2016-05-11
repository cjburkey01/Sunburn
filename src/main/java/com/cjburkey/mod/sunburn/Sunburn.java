package com.cjburkey.mod.sunburn;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import com.cjburkey.mod.sunburn.client.OverlayEvent;
import com.cjburkey.mod.sunburn.item.ItemSunscreen;
import com.cjburkey.mod.sunburn.packet.PacketDispatch;
import com.cjburkey.mod.sunburn.potion.PotionSunscreen;
import com.cjburkey.mod.sunburn.world.WorldTick;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;

@Mod(name = "Sunburn", version = "1.7.10_1.0.0", modid = "sunburn")
public class Sunburn {
	
	public static Potion potionSunscreen;
	public static Item itemSunscreen;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent e) {
		PacketDispatch.init();
		fixPotions();
		
		potionSunscreen = new PotionSunscreen(40, false, 0).setIconIndex(0, 0).setPotionName("potion.sunscreen");
		
		itemSunscreen = new ItemSunscreen().setUnlocalizedName("itemSunscreen").setTextureName("sunburn:itemSunscreen");
		GameRegistry.registerItem(itemSunscreen, itemSunscreen.getUnlocalizedName().substring(5));
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		FMLCommonHandler.instance().bus().register(new WorldTick());
		MinecraftForge.EVENT_BUS.register(new OverlayEvent());
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent e) {  }
	
	private static final void fixPotions() {
		Potion[] potionTypes = null;
		for (Field f : Potion.class.getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if(f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
					Field modfield = Field.class.getDeclaredField("modifiers");
					modfield.setAccessible(true);
					modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);
			
					potionTypes = (Potion[]) f.get(null);
					final Potion[] newPotionTypes = new Potion[512];
					System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
					f.set(null, newPotionTypes);
				}
			} catch (Exception err) {
				System.err.println("Severe error, please report this to the mod author(CJ Burkey):");
				System.err.println(err);
			}
		}
	}
	
}