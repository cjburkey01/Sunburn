package com.cjburkey.mod.sunburn.recipe;

import com.cjburkey.mod.sunburn.item.ITEMS;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RECIPES {
	
	public RECIPES() {
		addShapeless(new ItemStack(ITEMS.itemSunscreen, 1), new Object[] {
			"slimeball", "slimeball", "dyeRed", "dyeYellow", new ItemStack(Items.glass_bottle),
		});
		
		addShapeless(new ItemStack(ITEMS.itemSunscreen, 1), new Object[] {
			"slimeball", "slimeball", "dyeOrange", "dyeOrange", new ItemStack(Items.glass_bottle),
		});
		
		addShapeless(new ItemStack(ITEMS.itemSunscreen, 1), new Object[] {
			"slimeball", "slimeball", "dyeWhite", "dyeWhite", new ItemStack(Items.glass_bottle),
		});
	}
	
	private static final void add(ItemStack out, Object... recipe) {
		GameRegistry.addRecipe(new ShapedOreRecipe(out, recipe));
	}
	
	private static final void addShapeless(ItemStack out, Object... recipe) {
		GameRegistry.addRecipe(new ShapelessOreRecipe(out, recipe));
	}
	
}