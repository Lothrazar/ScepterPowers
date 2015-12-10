package com.lothrazar.scepterpowers;

import java.util.ArrayList;
import com.lothrazar.scepterpowers.item.ItemWandPiston;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistry {

	public static ArrayList<Item> items = new ArrayList<Item>();

	public static ItemWandPiston wand_piston;

	public static void registerItem(Item item, String name) {
		item.setUnlocalizedName(name);
		GameRegistry.registerItem(item, name);
		items.add(item);
	}

	public static void register() {

		wand_piston = new ItemWandPiston();
		ItemRegistry.registerItem(wand_piston, "wand_piston");
		wand_piston.addRecipe();

	}
}
