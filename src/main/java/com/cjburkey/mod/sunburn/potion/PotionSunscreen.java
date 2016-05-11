package com.cjburkey.mod.sunburn.potion;

import net.minecraft.potion.Potion;

public class PotionSunscreen extends Potion {

	public PotionSunscreen(int arg0, boolean arg1, int arg2) {
		super(arg0, arg1, arg2);
	}
	
	public Potion setIconIndex(int par1, int par2) {
		super.setIconIndex(par1, par2);
		return this;
	}
	
	public boolean isBadEffect() {
		return false;
	}
	
}