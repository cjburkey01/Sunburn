package com.cjburkey.mod.sunburn.item;

import com.cjburkey.mod.sunburn.potion.POTIONS;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemSunscreen extends Item {
	
	public ItemSunscreen() {
		this.maxStackSize = 1;
	}
	
	public int getMaxDamage() {
		return 15;
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(player.getActivePotionEffect(POTIONS.potionSunscreen) == null) {
			stack.damageItem(1, player);
			player.addPotionEffect(new PotionEffect(POTIONS.potionSunscreen.id, 3600, 0));
			if(stack.getItemDamage() >= stack.getMaxDamage()) {
				stack.stackSize = 0;
			}
		}
		
		return stack;
	}
	
}