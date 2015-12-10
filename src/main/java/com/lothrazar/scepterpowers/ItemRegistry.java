package com.lothrazar.scepterpowers;

import java.util.ArrayList;
import com.lothrazar.scepterpowers.item.ItemWandPiston;
import com.lothrazar.scepterpowers.item.ItemWandScaffold;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistry {

	public static ArrayList<Item> items = new ArrayList<Item>();

	public static ItemWandPiston wand_piston;
	public static ItemWandScaffold wand_scaffold;

	public static void registerItem(Item item, String name) {
		item.setUnlocalizedName(name);
		GameRegistry.registerItem(item, name);
		items.add(item);
	}

	public static void register() {

		wand_piston = new ItemWandPiston();
		ItemRegistry.registerItem(wand_piston, "wand_piston");
		wand_piston.addRecipe();

		wand_scaffold = new ItemWandScaffold();
		ItemRegistry.registerItem(wand_scaffold, "wand_scaffold");
		wand_scaffold.addRecipe();
		//wand rotate: 
		/*
	    public ImmutableList<IBlockState> getValidStates()
	    {
	        return this.validStates;
	    }*/

	}
}
