package com.lothrazar.scepterpowers.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.lothrazar.scepterpowers.BlockRegistry;
import com.lothrazar.scepterpowers.ItemRegistry;
import com.lothrazar.scepterpowers.ModScepterPowers;

public class ItemWandScaffold extends ItemWandAbstract{

	public static int DURABILITY;
	public ItemWandScaffold(){
		super();
		this.setCreativeTab(ModScepterPowers.tabSamsContent);
		this.setMaxDamage(DURABILITY);
		this.setMaxStackSize(1);
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ){
		BlockPos offset = pos.offset(side);
		
		worldIn.setBlockState(offset, BlockRegistry.block_fragile.getDefaultState());
		
    	return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ); 
    }

	@Override
	public void addRecipe() {

		GameRegistry.addRecipe(new ItemStack(ItemRegistry.wand_piston), 
				"  p", 
				" i ", 
				"b  ", 
				'p', Blocks.piston, 'i', Blocks.clay, 'b', Items.blaze_rod);
	} 
}
