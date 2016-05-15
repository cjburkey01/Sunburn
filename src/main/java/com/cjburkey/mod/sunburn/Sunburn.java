package com.cjburkey.mod.sunburn;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import com.cjburkey.mod.sunburn.item.ITEMS;
import com.cjburkey.mod.sunburn.packet.PacketDispatch;
import com.cjburkey.mod.sunburn.potion.POTIONS;
import com.cjburkey.mod.sunburn.proxy.CommonProxy;
import com.cjburkey.mod.sunburn.recipe.RECIPES;
import com.cjburkey.mod.sunburn.world.WorldTick;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.config.Configuration;

@Mod(name = "Sunburn", version = "1.7.10_1.0.5", modid = Sunburn.ID)
public class Sunburn {
	
	public static final String ID = "sunburn";
	
	public static String keyName;
	public static int secondsInSun;
	public static int ticksPerSecond;
	public static boolean gui;
	
	@SidedProxy(clientSide = "com.cjburkey.mod.sunburn.proxy.ClientProxy", serverSide = "com.cjburkey.mod.sunburn.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance(ID)
	public static Sunburn instance;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent e) {
		configInit(e);
		PacketDispatch.init();
		fixPotions();
		
		new ITEMS();
		new POTIONS();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		FMLCommonHandler.instance().bus().register(new WorldTick());
		proxy.registerGuis();
		
		new RECIPES();
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent e) {  }
	
	private static final void configInit(FMLPreInitializationEvent e) {
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		
		keyName = config.getString("keyName", "NBT", "sunburn_timeInSun", "The NBT name of the sunburn time.");
		secondsInSun = config.getInt("secondsInSun", "Sunburn", 60, 10, 600, "The max time in the sun before burning.");
		ticksPerSecond = config.getInt("ticksPerSecond", "Sunburn", 30, 10, 30, "Number of world ticks per second.");
		gui = config.getBoolean("enableGui", "GUI", true, "Whether or not the text displays ingame.");
		
		config.save();
	}
	
	private static final void fixPotions() {
		Potion[] potionTypes = null;
		System.out.println("Start fixing potions.");
		for (Field f : Potion.class.getDeclaredFields()) {
			System.out.println("Checking field: " + f.getName());
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
					System.out.println("Finish fixing potions");
					break;
				}
			} catch (Exception err) {
				System.err.println("Severe error, please report this to the mod author(CJ Burkey):");
				System.err.println(err);
			}
		}
	}
	
}