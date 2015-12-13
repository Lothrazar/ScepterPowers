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
	public static ItemWandChest wand_chest;
	public static ItemWandCollect wand_collect;
	public static ItemStepHeight wand_step;
	public static ItemWandLaunch wand_launch;
	public static ItemWandFishing wand_fishing;
	
	public static ItemChestSack item_chestsack;
	public static ItemPaperCarbon carbon_paper;
	
	private static void registerItem(Item item, String name) {
		item.setUnlocalizedName(name);
		GameRegistry.registerItem(item, name);
		items.add(item);
	}
	private static void registerWand(BaseWand item, String name) {
		registerItem(item,name);
		item.addRecipe();
	}

	public static void register() {
		wand_fishing = new ItemWandFishing();
		registerWand(wand_fishing, "wand_fishing");
		
		wand_launch = new ItemWandLaunch();
		registerWand(wand_launch, "wand_launch");

		wand_piston = new ItemWandPiston();
		registerWand(wand_piston, "wand_piston");

		wand_scaffold = new ItemWandScaffold();
		registerWand(wand_scaffold, "wand_scaffold");
		
		wand_rotate_creative = new ItemWandRotateState();
		registerWand(wand_rotate_creative, "wand_rotate_creative");
		
		wand_rotate_survival = new ItemWandRotateProperty();
		registerWand(wand_rotate_survival, "wand_rotate_survival");
		
		wand_chest = new ItemWandChest();
		registerWand(wand_chest, "wand_chest");
		
		wand_collect = new ItemWandCollect();
		registerWand(wand_collect, "wand_collect");

		wand_step = new ItemStepHeight();
		registerWand(wand_step, "wand_step");
		
		
		
		
		
		item_chestsack = new ItemChestSack();
		registerItem(item_chestsack, "chest_sack");
		carbon_paper = new ItemPaperCarbon();
		registerItem(carbon_paper, "carbon_paper");
	}
}
