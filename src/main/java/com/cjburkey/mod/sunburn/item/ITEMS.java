package com.cjburkey.mod.sunburn.item;

import com.cjburkey.mod.sunburn.Sunburn;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ITEMS {

	public static Item itemSunscreen;
	
	public ITEMS() {
		itemSunscreen = new ItemSunscreen().setUnlocalizedName("itemSunscreen");
		
		register(itemSunscreen);
	}
	
	public static final void register(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
		item.setTextureName(Sunburn.ID + ":" + item.getUnlocalizedName().substring(5));
	}
	
}