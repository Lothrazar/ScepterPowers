package com.lothrazar.scepterpowers;

import java.util.ArrayList;
import com.lothrazar.scepterpowers.item.*;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistry {

	public static ArrayList<Item> items = new ArrayList<Item>();

	public static ItemWandPiston wand_piston;
	public static ItemWandScaffold wand_scaffold;
	public static ItemWandRotateState wand_rotate_creative;
	public static ItemWandRotateProperty wand_rotate_survival;

	public static void registerItem(Item item, String name) {
		item.setUnlocalizedName(name);
		GameRegistry.registerItem(item, name);
		items.add(item);
	}
	public static void registerWand(ItemWandBase item, String name) {
		registerItem(item,name);
		item.addRecipe();
	}

	public static void register() {

		wand_piston = new ItemWandPiston();
		registerWand(wand_piston, "wand_piston");

		wand_scaffold = new ItemWandScaffold();
		registerWand(wand_scaffold, "wand_scaffold");
		
		wand_rotate_creative = new ItemWandRotateState();
		registerWand(wand_rotate_creative, "wand_rotate_creative");
		
		wand_rotate_survival = new ItemWandRotateProperty();
		registerWand(wand_rotate_survival, "wand_rotate_survival");
	}
}
