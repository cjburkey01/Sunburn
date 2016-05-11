package com.cjburkey.mod.sunburn.potion;

import net.minecraft.potion.Potion;

public class POTIONS {
	
	public static Potion potionSunscreen;
	
	public POTIONS() {
		potionSunscreen = new PotionSunscreen(40, false, 0).setIconIndex(0, 0).setPotionName("potion.sunscreen");
	}
	
}