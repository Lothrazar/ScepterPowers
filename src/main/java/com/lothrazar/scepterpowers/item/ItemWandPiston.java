package com.lothrazar.scepterpowers.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.lothrazar.scepterpowers.ItemRegistry;
import com.lothrazar.scepterpowers.ModScepterPowers; 

public class ItemWandPiston extends ItemWandAbstract{

	public static int DURABILITY;
	public ItemWandPiston()
	{
		super();
		this.setCreativeTab(ModScepterPowers.tabSamsContent);
		this.setMaxDamage(DURABILITY);
		this.setMaxStackSize(1);
	
	}
	
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
		if(worldIn.isRemote){
			System.out.println("right click on server");
		}
        return super.onItemRightClick(itemStackIn, worldIn, playerIn);
    }

	public void addRecipe() {

		GameRegistry.addRecipe(new ItemStack(ItemRegistry.wand_piston), 
				"  p", 
				" i ", 
				"b  ", 
				'p', Blocks.piston, 'i', Blocks.iron_block, 'b', Items.blaze_rod);
		
	}
	
}
