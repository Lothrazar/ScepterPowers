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

public class ItemWandScaffold extends BaseWand {

	public static int DURABILITY = 999;
	public ItemWandScaffold(){
		super(DURABILITY);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ){
		BlockPos offset = pos.offset(side);
		
		if(worldIn.setBlockState(offset, BlockRegistry.block_fragile.getDefaultState())){

			this.onSuccess(playerIn, stack);
		}
		
    	return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ); 
    }

	@Override
	public void addRecipe() {

		GameRegistry.addRecipe(new ItemStack(this), 
				"  p", 
				" i ", 
				"b  ", 
				'p', Blocks.piston, 'i', Blocks.clay, 'b', Items.blaze_rod);
	} 
}
