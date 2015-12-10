package com.lothrazar.scepterpowers.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.lothrazar.scepterpowers.ItemRegistry;
import com.lothrazar.scepterpowers.ModScepterPowers; 
import com.lothrazar.scepterpowers.UtilMoveBlock;

public class ItemWandPiston extends ItemWandAbstract{

	public static int DURABILITY;
	public ItemWandPiston()
	{
		super();
		this.setCreativeTab(ModScepterPowers.tabSamsContent);
		this.setMaxDamage(DURABILITY);
		this.setMaxStackSize(1);
	
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		if(worldIn.isRemote == false){
		
			System.out.println("click");
			UtilMoveBlock.moveBlock(worldIn, playerIn, pos, side);
			
		}
		
    	return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ); 
    } 
	public void addRecipe() {

		GameRegistry.addRecipe(new ItemStack(ItemRegistry.wand_piston), 
				"  p", 
				" i ", 
				"b  ", 
				'p', Blocks.piston, 'i', Blocks.iron_block, 'b', Items.blaze_rod);
		
	}
	
}
